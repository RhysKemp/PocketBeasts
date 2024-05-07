package com.mycompany.p3.c2471361.chat.interfaces;

/**
 * Interface for communication between client and server
 *
 * @author Rhys Kemp
 */
public interface CommunicationInterface {

    /**
     * Connect to a server
     *
     * @param ip IP address of server
     * @param port Port of server
     */
    void connect(String ip, int port);

    /**
     * Send a message to the server
     *
     * @param message Message to send
     */
    void send(String message);

    /**
     * Receive a message from the server
     *
     * @return Message from server
     */
    String receive();

    /**
     * Disconnect from the server
     */
    void disconnect();

    /**
     * Check if the client is connected to the server
     *
     * @return {@code boolean} True if connected, false if not connected
     */
    boolean isConnected();
}
