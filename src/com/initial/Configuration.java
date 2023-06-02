package com.initial;

import java.io.Serializable;

public class Configuration implements Serializable {
    public String Username, Password,Port;
    public Configuration(String username, String password, String port){
        this.Username = username;
        this.Password = password;
        this.Port = port;
    }
}
