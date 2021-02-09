package com.example.marketwatchsystem.Model;


public class UserType {
    private static UserType UserTypeInstance=null;
    public String type;

    private UserType() {

    }

    public static UserType getUserTypeInstance() {
        if(UserTypeInstance==null) {
            UserType ut = new UserType();
            UserTypeInstance=ut;
        }
        return UserTypeInstance;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
