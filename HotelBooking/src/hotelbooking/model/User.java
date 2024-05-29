/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.model;
import java.io.Serializable;

public class User implements Serializable {
    private String userID;
    private String userName;
    private String fullname;
    private String userEmail;
    private String userAddress;
    private String phoneNumber;
    private String password;
    private String confirmPasswordString;
    
    public User() {
    }
    
    public User(String userName, String fullname, String userEmail, String userAddress, String phoneNumber, String password, String confirmPasswordString) {
        this.userName = userName;
        this.fullname = fullname;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPasswordString = confirmPasswordString;
    }

    public User(String userID, String userName, String fullname, String userEmail, String userAddress, String phoneNumber) {
        this.userID = userID;
        this.userName = userName;
        this.fullname = fullname;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.phoneNumber = phoneNumber;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPasswordString(String confirmPasswordString) {
        this.confirmPasswordString = confirmPasswordString;
    }

    public String getPassword(char[] password1) {
        return password;
    }

    public String getConfirmPasswordString(char[] password1) {
        return confirmPasswordString;
    }
}
