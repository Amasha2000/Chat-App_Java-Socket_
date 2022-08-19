package client;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static client.LoginFormController.*;

public class ChatRoomFormController extends Thread implements Initializable {

    public Pane profile;
    public Label fullName;
    public Label phoneNo;
    public ImageView proImage;
    public TextField fileChoosePath;
    public TextArea msgRoom;
    public TextField msgField;
    public Label clientName;
    public Button profileBtn;
    public Circle showProPic;
    public Pane chat;
    public AnchorPane sideBar;
    public ImageView newImage;

    private FileChooser fileChooser;
    private File filePath;
    private File imagePath;
    public boolean toggleChat = false, toggleProfile = false;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    ObjectInputStream input;
    ObjectOutputStream output;


    public void chooseImageButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        fileChoosePath.setText(filePath.getPath());
        saveControl = true;
    }

    public void saveImage(ActionEvent event) {
        if (saveControl) {
            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                proImage.setImage(image);
                showProPic.setFill(new ImagePattern(image));
                saveControl = false;
                fileChoosePath.setText("");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public void sendMessageByKey(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    public void handleSendEvent(MouseEvent mouseEvent) {
        send();
        for (User user : users) {
            System.out.println(user.name);
        }
    }

    public void send() {
        String msg = msgField.getText();
        writer.println(LoginFormController.username + ": " + msg);
        msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        msgRoom.appendText("Me: " + msg + "\n");
        msgField.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    public boolean saveControl = false;

    public void handleProfileBtn(ActionEvent event) {
        if (event.getSource().equals(profileBtn) && !toggleProfile) {
            profile.toFront();
            chat.toBack();
            toggleProfile = true;
            toggleChat = false;
            profileBtn.setText("Back");
            setProfile();
        } else if (event.getSource().equals(profileBtn) && toggleProfile) {
            chat.toFront();
            profile.toBack();
            toggleProfile = false;
            toggleChat = false;
            profileBtn.setText("Profile");
        }
    }

    public void setProfile() {
        for (User user : users) {
            if (LoginFormController.username.equalsIgnoreCase(user.name)) {
                fullName.setText(user.fullName);
                fullName.setOpacity(1);
                phoneNo.setText(user.phoneNo);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showProPic.setStroke(Color.valueOf("#90a4ae"));
        Image image;
        image = new Image("icons/user.png", false);
        proImage.setImage(image);
        showProPic.setFill(new ImagePattern(image));
        clientName.setText(LoginFormController.username);
        connectSocket();
    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(LoginFormController.username + ":")) {
                    continue;
                } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                msgRoom.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseImageOnAction(MouseEvent event) throws IOException, InterruptedException, ClassNotFoundException {

          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          fileChooser = new FileChooser();
          this.imagePath = fileChooser.showOpenDialog(stage);

//        BufferedImage bufferedImage = ImageIO.read(filePath);
        //ImageIcon imageIcon = new ImageIcon("C:\\Users\\Amasha\\Desktop\\socket\\chatApp\\src\\icons\\female.png");

//        OutputStream outputStream = imageSocket.getOutputStream();
//        BufferedImage image = ImageIO.read(new File("C:\\Users\\Amasha\\Desktop\\socket\\chatApp\\src\\icons\\female.png"));
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(image,"jpg",byteArrayOutputStream);
//        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//        outputStream.write(size);
//        outputStream.write(byteArrayOutputStream.toByteArray());
//        outputStream.flush();
//
//        Thread.sleep(120000);

        //imageSocket.close();


//        BufferedImage bufferedImage = ImageIO.read(file);
//        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage,"jpg",byteArrayOutputStream);
//        outputStream.write(byteArrayOutputStream.toByteArray());
        // outputStream.flush();

    }
}

