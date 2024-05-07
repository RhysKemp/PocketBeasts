package com.mycompany.p3.c2471361.chat;

import com.mycompany.p3.c2471361.chat.interfaces.CommunicationInterface;

/**
 * Client class for connecting to a server
 *
 * @author Rhys
 * @see CommunicationInterface
 */
public class Client {
    private final CommunicationInterface communicationInterface;

    /**
     * Constructor for Client
     *
     * @param communicationInterface CommunicationInterface to use
     */
    public Client(CommunicationInterface communicationInterface) {
        this.communicationInterface = communicationInterface;
    }

    /**
     * Start a connection to a server
     *
     * @param ip IP address of server
     * @param port Port of server
     */
    public void startConnection(String ip, int port) {
        communicationInterface.connect(ip, port);
    }

    /**
     * Send a message to the server
     *
     * @param message Message to send
     */
    public void sendMessage(String message) {
        communicationInterface.send(message);
    }

    /**
     * Receive a message from the server
     *
     * @return Message from server
     */
    public String receiveMessage() {
        return communicationInterface.receive();
    }

    /**
     * Stop the connection to the server
     */
    public void stopConnection() {
        communicationInterface.disconnect();
    }

    /**
     * Check if the client is connected to the server
     *
     * @return {@code boolean} True if connected, false if not connected
     */
    boolean isConnected() {
        return communicationInterface.isConnected();
    }
}
