package com.initial;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeFile {
    public String user;
    public String password;
    public String port;

    public DeserializeFile(){
        Configuration object1;

        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("config.cfg");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (Configuration) in.readObject();

            in.close();
            file.close();

            user = object1.Username;
            password = object1.Password;
            port = object1.Port;
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
