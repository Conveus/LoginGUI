package com.example.logingui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class GUIController {

    // Sets up loginController
    LoginController loginController = new LoginController();

    // Sets up connections to view components
    @FXML
    private Label mainTxt;
    @FXML
    private Label messageTxt;

    @FXML
    private TextField usernameEntry;
    @FXML
    private PasswordField passwordEntry;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        // Calls method to check if username and password is valid, if not displays error message
        if(loginController.validUsername(usernameEntry.getText())
                && loginController.validPassword(passwordEntry.getText()))
        {
            // Calls method to create account if it doesn't exist, if it already does displays error message
            if(loginController.registerAccount(usernameEntry.getText(),passwordEntry.getText())){
                messageTxt.setText("Successful registration, please login");
            }
            else{
                messageTxt.setText("Account already exists");
            }
        }
        else{
            messageTxt.setText("Invalid username or password");
        }
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        // Calls method to check if username and password is valid, if not displays error message
        if(loginController.validUsername(usernameEntry.getText())
                && loginController.validPassword(passwordEntry.getText()))
        {
            // Calls method to check account exists, and logs in if data entered matches or if not displays error message
            if(loginController.checkAccount(usernameEntry.getText(),passwordEntry.getText())){
                mainTxt.setText("Welcome "+loginController.getUsername()+"!");
                messageTxt.setText("This is your account");
                usernameEntry.setVisible(false);
                passwordEntry.setVisible(false);
                registerBtn.setVisible(false);
                loginBtn.setVisible(false);
            }
            else {
                messageTxt.setText("Incorrect username or password");
            }
        }
        else{
            messageTxt.setText("Invalid username or password");
        }
    }

}