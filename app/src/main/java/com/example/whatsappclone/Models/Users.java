package com.example.whatsappclone.Models;

public class Users {
    String profilePic, userName, Password, UserId, LastMessage, Status, Mail;

    public Users(){

    }

    public Users(String profilePic, String userName, String password, String userId, String lastMessage, String status, String mail) {
        this.profilePic = profilePic;
        this.userName = userName;
        Password = password;
        UserId = userId;
        LastMessage = lastMessage;
        Status = status;
        Mail = mail;
    }

    public Users(String userName, String password, String mail) {
        this.userName = userName;
        Password = password;
        Mail = mail;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getLastMessage() {
        return LastMessage;
    }

    public void setLastMessage(String lastMessage) {
        LastMessage = lastMessage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }
}
