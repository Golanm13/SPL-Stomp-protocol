Project Description
This project implements an Emergency Service Platform that allows users to subscribe to specific emergency channels (e.g., fire, medical, police, natural disasters) to report emergencies and receive relevant updates. It fosters effective community collaboration during crises.

The system is implemented using the STOMP (Simple Text-Oriented Messaging Protocol) for communication between a Java-based server and a C++ client. The platform supports:

Thread-Per-Client (TPC): Each client connection is handled in a separate thread.
Reactor Pattern: Event-driven model for efficiently managing multiple client connections.
Features
Server (Java)
Centralized STOMP server.
Supports:
TPC Mode: Dedicated thread for each client.
Reactor Mode: Event-driven architecture.
Handles client subscriptions, message broadcasts, and STOMP frame processing.
Client (C++)
User interaction via console.
Supports STOMP frame communication:
Login and Logout.
Subscribe/Unsubscribe to/from emergency channels.
Report emergencies from JSON files.
Summarize and save channel-specific emergency updates.
Multi-threaded implementation for simultaneous keyboard input and server communication.
Installation and Setup
Prerequisites
Java 17 or higher.
Maven (for building the server).
C++17 or higher.
Make (for building the client).
Linux-based environment or Docker.
Server Setup
Navigate to the server directory.
Compile the project:
bash
Copy
Edit
mvn compile
Run the server:
bash
Copy
Edit
mvn exec:java -Dexec.mainClass="bgu.spl.net.impl.stomp.StompServer" -Dexec.args="<port> <mode>"
Replace <port> with the desired port number (e.g., 7777).
Replace <mode> with either tpc or reactor.
Client Setup
Navigate to the client directory.
Compile the client:
bash
Copy
Edit
make
Run the client:
bash
Copy
Edit
./bin/StompEMIClient
Commands and Usage
Client Commands
Login: login {host:port} {username} {password}
Join Channel: join {channel_name}
Exit Channel: exit {channel_name}
Report Emergency: report {file_path}
Summarize Channel: summary {channel_name} {user} {file}
Logout: logout
Server Modes
TPC Mode: Each client is assigned a dedicated thread for communication.
Reactor Mode: A single-threaded, non-blocking mechanism for high scalability.
Testing
Server
Use the provided StompServer.jar to test the client before implementing your server:

bash
Copy
Edit
java -jar server/target/StompServer.jar <port> <mode>
Client
Run the pre-compiled executable (StompESClient) in the bin directory for initial server testing.

Project Structure
css
Copy
Edit
EmergencyServicePlatform/
├── server/
│   ├── src/
│   ├── target/
│   ├── pom.xml
│   └── ...
├── client/
│   ├── src/
│   ├── include/
│   ├── bin/
│   └── Makefile
└── README.md
Contribution
Bonus: Working with Git
Earn up to 5 bonus points for effectively using Git:

3 points: Maintain a Git repository with meaningful commits.
2 points: Use clear and descriptive commit messages.
References
STOMP Protocol Documentation
C++ Multithreading Guide
Git Best Practices
