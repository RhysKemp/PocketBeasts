package com.mycompany.chatapp.chat;

import com.mycompany.chatapp.chat.interfaces.CommunicationInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Socket communication implementation of CommunicationInterface
 * <p>
 * This class implements the CommunicationInterface using a socket connection
 * to send and receive messages between a client and server.
 * It connects to a server, sends messages, receives messages, and disconnects.
 *
 * @author Rhys Kemp
 * @see CommunicationInterface
 * @see Socket
 * @see PrintWriter
 * @see BufferedReader
 * @see IOException
 * @see InputStreamReader
 */
public class SocketCommunication implements CommunicationInterface {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Connect to a server
     *
     * @param ip   IP address of server
     * @param port Port of server
     */
    @Override
    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Socket error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Send a message to the server
     *
     * @param message Message to send
     */
    @Override
    public void send(String message) {
        out.println(message);
    }

    /**
     * Receive a message from the server
     *
     * @return Message from server
     */
    @Override
    public String receive() {
        try {
            String message = in.readLine();
            return message;
        } catch (IOException e) {
            System.out.println("Socket error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Disconnect from the server
     */
    @Override
    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Socket error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Check if the client is connected to the server
     *
     * @return {@code boolean} True if connected, false otherwise
     */
    @Override
    public boolean isConnected() {
        return socket != null && !socket.isClosed();
    }
}
