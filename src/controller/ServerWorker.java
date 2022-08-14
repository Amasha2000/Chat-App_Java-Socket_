package controller;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ServerWorker extends Thread{

    private final Socket clientSocket;

    public ServerWorker(Socket clientSocket){
        this.clientSocket=clientSocket;
    }

    @Override
    public void run(){
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleClientSocket() throws IOException, InterruptedException {
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line= reader.readLine())!=null) {
//            String[] values = StringUtils.split(line);
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }
            String message = line + "\n";
            outputStream.write(message.getBytes());
        }
        clientSocket.close();
    }
}
