# 🚨 Emergency Service Platform

## Overview
The Emergency Service Platform enables users to **subscribe to emergency channels** (fire, medical, police, natural disasters) to **report incidents** and **receive real-time updates**.  
It leverages the **STOMP protocol** for communication between a **Java-based server** and a **C++ client**, fostering effective community collaboration during crises.

## Architecture
- **Server (Java)**
  - Implements a centralized STOMP server.
  - Two concurrency models supported:
    - **Thread-Per-Client (TPC):** Each client runs on its own thread.
    - **Reactor Pattern:** Event-driven, non-blocking I/O for high scalability.
  - Handles client subscriptions, message broadcasting, and STOMP frame processing.

- **Client (C++)**
  - Console-based user interaction.
  - Supports STOMP frame communication:
    - Login / Logout
    - Subscribe / Unsubscribe to emergency channels
    - Report emergencies from JSON files
    - Summarize and save channel-specific updates
  - Multi-threaded: separates keyboard input from server communication.

## Features
- Real-time emergency reporting and updates
- Channel-based subscription model
- Two server execution modes: **TPC** and **Reactor**
- JSON-based reporting for structured emergency data
- Summary generation and export per channel

## Installation & Setup

### Prerequisites
- **Java 17+**
- **Maven** (for building the server)
- **C++17+**
- **Make** (for building the client)
- Linux environment (or Docker)

### Server Setup
```bash
cd server
mvn compile
mvn exec:java -Dexec.mainClass="bgu.spl.net.impl.stomp.StompServer" -Dexec.args="<7777> <tpc|reactor>"


Client Setup
cd client
make
./bin/StompESClient

Client Commands
login {host:port} {username} {password}
join {channel_name}
exit {channel_name}
report {file_path}
summary {channel_name} {user} {file}
logout

Testing

Use the provided StompServer.jar for initial testing:

java -jar server/target/StompServer.jar


Run the precompiled client (StompESClient) from the bin directory.

Project Structure
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

References

STOMP Protocol Documentation

C++ Multithreading Best Practices

Java Concurrency Patterns
