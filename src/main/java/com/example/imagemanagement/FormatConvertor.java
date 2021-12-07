package com.example.imagemanagement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public class FormatConvertor {
    public void convert(File input, File output, String outputFormat){
        try {
            BufferedImage bim = ImageIO.read(input);
            ImageIO.write(bim, outputFormat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}