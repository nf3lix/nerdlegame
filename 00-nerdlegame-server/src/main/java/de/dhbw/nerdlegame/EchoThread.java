package de.dhbw.nerdlegame;

import java.io.*;
import java.net.Socket;

public class EchoThread extends Thread {

    protected Socket socket;

    public EchoThread(final Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        InputStream inputStream;
        BufferedReader bufferedReader;
        DataOutputStream outputStream;
        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String line;
        while(true) {
            try {
                line = bufferedReader.readLine();
                if(line == null || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    outputStream.writeBytes(line + "\n\r");
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
