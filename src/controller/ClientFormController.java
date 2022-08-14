package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.Socket;

public class ClientFormController {
    public Label lblName;
    public JFXTextArea clientTextArea;
    public JFXTextField txtClient;
    public JFXButton btnClient;

//    Socket socket=null;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
    }

    public void initialize() throws IOException {
//        new  Thread(()->{
//            try {
//                socket = new Socket("localhost",5000);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) {

    }
}
