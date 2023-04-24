package com.example.logingui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class LoginController {

    // Location to save account files to
    public String location = "C:\\Users\\conno\\IdeaProjects\\JFX\\LoginGUI\\src\\Accounts\\";

    // Create temp account details
    private String username = "user";
    private String password = "1234";

    public boolean validUsername(String user){
        String[] invalidCharacters = {"<",">",":","\"","/","\\","|","?","*"};

        // Checks if a username has been entered
        if(user.length()!=0){
            // Checks entered username for any invalid characters
            for (int i = 0; i < invalidCharacters.length; i++){
                if(user.contains(invalidCharacters[i])){
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }


    public boolean validPassword(String pass){
        // Checks if a password has been entered
        if(pass.length()!=0){
            return true;
        }
        else {
            return false;
        }
    }

    // Registers account by creating file named after username containing the user and password
    public boolean registerAccount(String user, String pass) throws IOException{

        // Creates variable for filename from user input
        String fileName = location+user.toLowerCase().trim()+".txt";
        File account = new File(fileName);
        //Checks account doesn't already exist
        if(!account.exists()){
            // Creates account
            if(account.createNewFile()){
                System.out.println("Account created");
                // Calls method to write username and password to file
                recordAccountDetails(user, pass, fileName);
                setUsername(user);
                setPassword(pass);
                return true;
            }else{
                System.out.println("Account could not be created");
                return false;
            }
        }
        else{
            System.out.println("Account with that name already exists");
            return false;
        }
    }

    // Writes username and password into account file
    public boolean recordAccountDetails(String user, String pass, String file){
        try{
            FileWriter myWriter = new FileWriter(file);
            // Writes username and password
            myWriter.write(user + pass);
            myWriter.close();
            System.out.println("Successfully wrote to file");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
            return false;
        }
    }

    // Check account details are correct
    public boolean checkAccount(String user, String pass) throws IOException
    {

        // Creates variable for filename from user input
        String fileName = location+user.toLowerCase().trim()+".txt";
        File account = new File(fileName);
        try{
            Scanner reader = new Scanner(account);
            String data = "";
            // Checks account exists
            if(account.exists()){
                while (reader.hasNextLine())
                {
                    // Sets variable to contents of file
                    data = reader.nextLine();
                }
                reader.close();
                // Checks variable has the correct username and password
                if(data.equals(user+pass)){
                    setUsername(user);
                    setPassword(pass);
                    return true;
                }
                else {
                    return false;
                }
            }else {
                return false;
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
            return false;
        }
    }

    // Deletes account file if confirmation given
    public boolean deleteAccount(){
        String fileName = location+username.toLowerCase().trim()+".txt";
        File account = new File(fileName);
        // Checks account exists
        if(account.exists())
        {
            Scanner input = new Scanner(System.in);
            // Check the user want the file deleted
            System.out.println("Are you sure you want to delete the account " + username + "?");
            System.out.print("Please type the account name to confirm: ");
            String confirm = input.nextLine().trim();
            if(confirm.equals(username))
            {
                // Deletes file
                if(account.delete())
                {
                    System.out.println("Deleted the account: " + username);
                    return true;
                }
                else {
                    System.out.println("Failed to delete account");
                    return false;
                }
            }
            else {
                System.out.println("Incorrect entry cancelling account deletion");
                return false;
            }
        }
        else {
            System.out.println("Account does not exist");
            return false;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
