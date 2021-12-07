package com.example.imagemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;


public class ImageController {
    @FXML
    ImageView imageView = new ImageView();//要在这里new，why？？

    @FXML
    private TextField height = new TextField();

    @FXML
    private TextField width = new TextField();

    @FXML TextField path = new TextField();

    @FXML
    private ChoiceBox<String> Type = new ChoiceBox<>();

    private String[] picType = {"JPG", "PNG", "JPEG", "BMP", "GIF"};

    FileChooser fc = new FileChooser();
    File tmp;

    @FXML
    public void choseFile(ActionEvent event) {
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Type","*.jpg","*.png","*.gif", "*.bmp"));
        tmp = fc.showOpenDialog(null);

        if (tmp != null) {
            Image img = new Image(tmp.toURI().toString());
            imageView.setImage(img);
            Double h = img.getHeight();
            Double w = img.getWidth();
            height.setText(h.toString());
            width.setText(w.toString());
            path.setText(tmp.getPath());
        }
        if (Type.getItems().isEmpty()) {
            pictureType();
        }
    }

    public void choseMultipleFile(ActionEvent event) {
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Type","*.jpg","*.png","*.jpeg"));
        List<File> tmps = fc.showOpenMultipleDialog(null);

        for (File tmp : tmps) {
            if (tmp != null) {

            }
        }
        if (!Type.hasProperties()) {
            pictureType();
        }
    }

    public void download(ActionEvent event) {
        FileChooser ofc = new FileChooser();
        ofc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Tpye", "*." + Type.getValue().toLowerCase()));
        File output = ofc.showSaveDialog(null);
        FormatConversion conversion = new FormatConversion();
        conversion.Conversion(tmp, output, Type.getValue());
    }

    public void pictureType() {
        for (String t : picType) {
            Type.getItems().add(t);
        }
    }
}
