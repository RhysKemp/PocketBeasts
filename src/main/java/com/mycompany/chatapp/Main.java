/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.chatapp;

import com.mycompany.chatapp.chat.ChatWindow;
import com.mycompany.chatapp.chat.Client;
import com.mycompany.chatapp.chat.Server;
import com.mycompany.chatapp.chat.SocketCommunication;
import com.mycompany.chatapp.chat.interfaces.CommunicationInterface;

/**
 *
 * @author Rhys
 */
public class Main {

    public static void main(String[] args) {
        // Start server in a new thread
        new Thread(() -> {
            Server server = new Server();
            server.start(12345);
        }).start();

        // Allow server to start
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start first client
        CommunicationInterface communication1 = new SocketCommunication();
        Client client1 = new Client(communication1);
        client1.startConnection("localhost", 12345);
        ChatWindow chatWindow1 = new ChatWindow(client1);

        // Start second client
        CommunicationInterface communication2 = new SocketCommunication();
        Client client2 = new Client(communication2);
        client2.startConnection("localhost", 12345);
        ChatWindow chatWindow2 = new ChatWindow(client2);

        // Start third client
        CommunicationInterface communication3 = new SocketCommunication();
        Client client3 = new Client(communication3);
        client3.startConnection("localhost", 12345);
        ChatWindow chatWindow3 = new ChatWindow(client3);


    }
}
