# Simple Java Swing-based Chat Application

This is a simple Java Swing-based chat application that allows two or more separate instances to communicate via sockets.

## Features

- **Server class**: Handles client connections and broadcasts messages to all connected clients.
- **Client class**: Connects to the server and sends/receives messages.
- **ChatWindow class**: Provides a GUI for the client to send and receive messages.
- **CommunicationInterface**: An interface that abstracts the underlying communication method (sockets in this case).
- **SocketCommunication class**: A concrete implementation of the CommunicationInterface that uses a socket connection to send and receive messages between a client and server.

## Future Work

- Handle concurrency issues correctly.
- Ensure the application is stable and reliable.
- Demonstrate the use of named pipes in addition to sockets for inter-process communication.
- Redundant methods applied to PocketBeasts game

## How to Run

1. Clone the repository.
2. Open the project in your preferred IDE (developed in IntelliJ IDEA).
3. Run the `Main` class to start the server and clients.

## Author

Rhys Kemp
