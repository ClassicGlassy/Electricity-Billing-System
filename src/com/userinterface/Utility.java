package com.userinterface;

import javax.swing.*;
import java.awt.*;


public class Utility {

    public ImageIcon convertToIcon(String ImageURL, int width, int height){

        ImageIcon ImageInSystem = new ImageIcon(ClassLoader.getSystemResource(ImageURL));
        Image ConvertImage = ImageInSystem.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);

        return new ImageIcon(ConvertImage);
    }

    public Font convertToFont(String FontURL,float FontSize){
        Font customFont = null;
        try{
            customFont = Font.createFont(Font.TRUETYPE_FONT,ClassLoader.getSystemResourceAsStream(FontURL));
            customFont = customFont.deriveFont(FontSize);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return customFont;
    }


    
}
