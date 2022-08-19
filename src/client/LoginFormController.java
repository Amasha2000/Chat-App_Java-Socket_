package client;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class LoginFormController {

    public Pane pnSignUp;
    public ImageView btnBack;
    public TextField regFirstName;
    public Button getStarted;
    public Label controlRegLabel;
    public Label success;
    public TextField regName;
    public Label nameExists;
    public TextField regPhoneNo;
    public Pane pnSignIn;
    public TextField userName;
    public Button btnSignUp;
    public Label loginNotifier;

    public static String username;
    public static ArrayList<User> loggedInUser = new ArrayList();
    public static ArrayList<User> users = new ArrayList();

    public void login(ActionEvent actionEvent) {
        username = userName.getText();
        boolean login = false;
        for (User x : users) {
            if (x.name.equalsIgnoreCase(username)) {
                login = true;
                loggedInUser.add(x);
                System.out.println(x.name);
                break;
            }
        }
        if (login) {
            changeWindow();
        } else {
            loginNotifier.setOpacity(1);
        }
    }

    public void changeWindow() {
        try {
            Stage stage = (Stage) userName.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getResource("ChatRoomForm.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(username + "");
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnBack) {
            new FadeTransition().play();
            pnSignIn.toFront();
        }
        regName.setText("");
    }

    public void registration(ActionEvent actionEvent) {
        if (!regName.getText().equalsIgnoreCase("")
                && !regFirstName.getText().equalsIgnoreCase("")
                && !regPhoneNo.getText().equalsIgnoreCase("")) {
            if (checkUser(regName.getText())) {
                User newUser = new User();
                newUser.name = regName.getText();
                newUser.fullName = regFirstName.getText();
                newUser.phoneNo = regPhoneNo.getText();
                users.add(newUser);
                btnBack.setOpacity(1);
                success.setOpacity(1);
                makeDefault();
                if (controlRegLabel.getOpacity() == 1) {
                    controlRegLabel.setOpacity(0);
                }
                if (nameExists.getOpacity() == 1) {
                    nameExists.setOpacity(0);
                }
            } else {
                nameExists.setOpacity(1);
                setOpacity(success, btnBack, controlRegLabel);
            }
        } else {
            controlRegLabel.setOpacity(1);
            setOpacity(success, btnBack, nameExists);
        }
    }

    private void setOpacity(Label a, ImageView b, Label c) {
        if (a.getOpacity() == 1 || b.getOpacity() == 1 || c.getOpacity() == 1) {
            a.setOpacity(0);
            b.setOpacity(0);
            c.setOpacity(0);
        }
    }

    private void makeDefault() {
        regName.setText("");
        regFirstName.setText("");
        regPhoneNo.setText("");
        setOpacity(controlRegLabel, btnBack, nameExists);
    }

    private boolean checkUser(String username) {
        for (User user : users) {
            if (user.name.equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource().equals(btnSignUp)) {
            new FadeTransition().play();
            pnSignUp.toFront();
            pnSignIn.toBack();
        }
        if (event.getSource().equals(getStarted)) {
            new FadeTransition().play();
            pnSignIn.toFront();
            pnSignUp.toBack();
        }
        loginNotifier.setOpacity(0);
        userName.setText("");
    }
}
