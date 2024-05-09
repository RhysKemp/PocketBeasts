package com.mycompany.chatapp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server class for chat application
 * <p>
 * This class is responsible for handling incoming connections from clients and broadcasting messages to all connected clients.
 * It listens on a specified port for incoming connections and creates a new thread to handle each client,
 * each client is assigned a PrintWriter to send messages to the client and a BufferedReader to receive messages from the client.
 * The server maintains a list of client writers to broadcast messages to all connected clients, it stops listening for connections when the stop method is called.
 * The server also stops listening for messages from a client when the client sends a '.' message.
 *
 * @author Rhys Kemp
 * @see Client
 * @see SocketCommunication
 * @see ChatWindow
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final List<PrintWriter> clientWriters = new ArrayList<>();

    /**
     * Start the server on the specified port
     *
     * @param port Port to listen on
     */
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handle a client connection
     *
     * @param clientSocket Socket for client connection
     */
    private void handleClient(Socket clientSocket) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            clientWriters.add(out);
            out.println("Connected to server. Type something and press Enter. Type '.' to exit.");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("Goodbye.");
                    break;
                }
                broadcastMessage(inputLine);
            }
            stopClient(in, out, clientSocket);
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Stop listening for connections
     */
    private void stopClient(BufferedReader in, PrintWriter out, Socket clientSocket) {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Broadcast a message to all connected clients
     *
     * @param message Message to broadcast
     */
    private void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    /**
     * Stop the server
     */
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
