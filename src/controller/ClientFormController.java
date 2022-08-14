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


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",3000);

    }

    public void sendOnAction(ActionEvent actionEvent) {

    }
}
