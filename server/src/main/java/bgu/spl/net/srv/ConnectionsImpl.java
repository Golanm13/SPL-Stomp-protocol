package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;
/* This class is used to manage the connections between the server and the clients.
 ConcurrentHashMap.newKeySet() is used to create the subscriber list.
This ensures that the same connectionId cannot be added more than once to a topic.*/
 public class ConnectionsImpl<T> implements Connections<T> {
    private final ConcurrentHashMap<Integer, ConnectionHandler<T>> clients = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ConcurrentHashMap.KeySetView<Integer, Boolean>> topics = new ConcurrentHashMap<>();

    // Send a message to a specific client
    @Override
    public boolean send(int connectionId, T msg) {
        ConnectionHandler<T> handler = clients.get(connectionId);
        if (handler != null) {
            handler.send(msg);
            return true;
        }
        return false;
    }

    // Send a message to all clients subscribed to a specific topic
    @Override
    public void send(String channel, T msg) {
        ConcurrentHashMap.KeySetView<Integer, Boolean> subscribers = topics.get(channel);
        if (subscribers != null) {
            for (int id : subscribers) {
                send(id, msg);
            }
        }
    }

    // Remove a client from the server
    @Override
    public void disconnect(int connectionId) {
        clients.remove(connectionId);
        // Remove the client from all topics they subscribed to
        for (ConcurrentHashMap.KeySetView<Integer, Boolean> subscribers : topics.values()) {
            subscribers.remove(connectionId);
        }
    }

    // Add a client to the server
    public void registerClient(int connectionId, ConnectionHandler<T> handler) {
        clients.put(connectionId, handler);
    }

    // Subscribe a client to a topic
    public void subscribe(int connectionId, String topic) {
        topics.computeIfAbsent(topic, k -> ConcurrentHashMap.newKeySet()).add(connectionId);
    }

    // Unsubscribe a client from a topic
    public void unsubscribe(int connectionId, String topic) {
        ConcurrentHashMap.KeySetView<Integer, Boolean> subscribers = topics.get(topic);
        if (subscribers != null) {
            subscribers.remove(connectionId);
        }
    }
}