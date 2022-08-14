package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController{

    public Label lblName;
    public JFXTextArea serverTextArea;
    public JFXTextField txtServer;
    public JFXButton btnServer;
    Socket clientSocket=null;
    ServerSocket serverSocket = null;

    public void initialize() throws IOException {
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(5000);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Client Connected");
            }
        }).start();

    }

    public void sendOnAction(ActionEvent actionEvent) {
    }
}
