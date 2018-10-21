package com.example.romantichate.guayababeta;

public class User {
    //String  id;
    String  name;
    String  gender;


    public  User(){

    }

    public User(String name, String gender) {
        //this.id = id;
        this.name = name;
        this.gender = gender;
    }
    /*
    public String getId() {
        return id;
    }
    */

    public String getName() {
        return name;
    }
    public void setName(String name) {
       this.name = name;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
