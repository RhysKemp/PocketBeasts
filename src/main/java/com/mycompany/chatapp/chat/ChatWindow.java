package com.mycompany.chatapp.chat;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Chat window for sending and receiving messages
 * <p>
 * This class creates a new window with a text field for sending messages and a text area for displaying received messages.
 * It starts a new thread to listen for messages from the client and displays them in the text area,
 * messages can be sent by typing in the text field and pressing Enter.
 *
 * @author Rhys Kemp
 * @see Client
 * @see Server
 * @see SocketCommunication
 */
public class ChatWindow {
    private final JTextField textField;
    private final JTextArea textArea;

    /**
     * Create a new chat window
     *
     * @param client Client to use for sending and receiving messages
     */
    public ChatWindow(Client client) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        textField = new JTextField(40);
        textField.addActionListener(e -> {
            client.sendMessage(textField.getText());
            textField.setText("");
        });

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);


        frame.getContentPane().add(textField, "South");
        frame.getContentPane().add(new JScrollPane(textArea), "Center");

        frame.pack();
        frame.setVisible(true);

        // Start a new thread to listen for messages
        executorService.submit(() -> {
            while (true) {
                String message;
                while ((message = client.receiveMessage()) != null && client.isConnected()) {
                    display(message);
                }
            }
        });
    }

    /**
     * Display a message in the chat window
     *
     * @param message Message to display
     */
    public void display(String message) {
        textArea.append(message + "\n");
    }
}
