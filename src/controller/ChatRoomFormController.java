package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ChatRoomFormController extends Thread{
    public AnchorPane chatRoomContext;
    public JFXTextArea txtTextArea;
    public JFXTextField txtTextField;
    public Label lblUser;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

//    final int PORT=5000;
//
//    ServerSocket serverSocket;
//    Socket socket;
//    DataInputStream dataInputStream;
//    DataOutputStream dataOutputStream;
//    String message ="";



    public void initialize(){

//        new Thread(() -> {
//            try {
//                socket=new Socket("localhost",PORT);
//                dataOutputStream=new DataOutputStream(socket.getOutputStream());
//                dataInputStream=new DataInputStream(socket.getInputStream());
//
//                while (!message.equals("bye")){
//                    message=dataInputStream.readUTF();
//                    //System.out.println(message);
//                    txTextArea.appendText("\nserver: "+message);
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();

        //lblName.setText(UserLoginFormController.txtUserName.getText());
        String userName=UserLoginFormController.userName;
        lblName.setText(String.valueOf(userName));
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
    public void run(){
        try {
            while (true) {

                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                // txTextArea.appendText(cmd+"\n");
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }
                System.out.println(fullMsg);
                //txTextArea.appendText(cmd+" "+fullMsg+"\n");
//                if (cmd.equalsIgnoreCase("Client" + ":")) {
//                    continue;
//                } else if (fullMsg.toString().equalsIgnoreCase("bye")) {
//                    break;
//                }

                System.out.println("cmd="+cmd+"-----"+"UserName"+lblName.getText());
                if(!cmd.equalsIgnoreCase(lblName.getText()+":")){
                    txTextArea.appendText(msg + "\n");
                }

            }
//            reader.close();
//            writer.close();
//            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Uwani, [8/9/2022 10:39 PM]
//    public void imgSendMsgOnAction(MouseEvent mouseEvent) throws IOException {
//
//        String msg = txtTextMsg.getText();
//        writer.println(txtUserName.getText() + ": " + txtTextMsg.getText());
//        txtTextArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
//        txtTextArea.appendText("Me: " + msg + "\n");
//        txtTextMsg.clear();
//        if(msg.equalsIgnoreCase("BYE")  (msg.equalsIgnoreCase("logout"))) {
////            System.exit(0);
//            Stage stage = (Stage) txtTextMsg.getScene().getWindow();
//            stage.close();
//        }
//    }

//    public void btnGoOnAction(ActionEvent actionEvent) {
//        txtUserName.setText(txtNicName.getText().trim());
//        pnePopUp.setVisible(false);
//        apnChatForm.setVisible(true);
//    }
//
//    public void AddClientOnAction(MouseEvent mouseEvent) throws IOException {
//        Stage stage=new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChatForm.fxml"))));
//        stage.setResizable(false);
//        //primaryStage.getIcons().add(new Image("location"));
//        stage.setTitle("sample title");
//        stage.centerOnScreen();
//        stage.show();
//
//    }


    public void btnSendOnAction(ActionEvent actionEvent) {
        send();
    }



    public void send(){
        String msg = txtMessage.getText();
        writer.println(lblName.getText() + ": " + txtMessage.getText());
        txTextArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txTextArea.appendText("Me: " + msg + "\n");
        txtMessage.clear();
        if(msg.equalsIgnoreCase("BYE")  (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
//            Stage stage = (Stage) txtTextMsg.getScene().getWindow();
//            stage.close();
        }
    }

    public void chooseImageOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void txtSendMessageOnAction(ActionEvent actionEvent) {
        send();
    }
}
