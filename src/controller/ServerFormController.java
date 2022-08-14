package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket clientSocket = serverSocket.accept();

        System.out.println("Client Connected");
    }
}
