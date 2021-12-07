package com.example.imagemanagement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public class FormatConversion {
//    public static final String JPG = ".jpg";
//    public static final String GIF = ".gif";
//    public static final String PNG = ".png";
//    public static final String BMP = ".bmp";


    public void Conversion(File input, File output, String outputFormat){
        try {
            BufferedImage bim = ImageIO.read(input);
            ImageIO.write(bim, outputFormat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}