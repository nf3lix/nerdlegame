package de.dhbw.nerdlegame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public Client(final String address, final int port) {
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        Socket socket = null;
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            inputStream = new DataInputStream(System.in);
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
        while(!line.equals("Over")) {
            try {
                line = inputStream.readLine();
                outputStream.writeUTF(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
