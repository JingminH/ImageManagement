package com.example.imagemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;


public class ImageController {
    @FXML
    ImageView imageView = new ImageView();

    @FXML
    private TextField heightTextField = new TextField();

    @FXML
    private TextField widthTextField = new TextField();

    @FXML
    private TextField pathTextField = new TextField();

    @FXML
    private ChoiceBox<String> typeSelector = new ChoiceBox<>();

    private String[] picTypes = {"JPG", "PNG", "JPEG", "BMP", "GIF"};

    FileChooser fc = new FileChooser();

    File tmp;

    @FXML
    public void choseFile(ActionEvent event) {
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Type", "*.jpg", "*.png", "*.jpeg", "*.gif", "*.bmp"));
        tmp = fc.showOpenDialog(null);

        if (tmp != null) {
            Image img = new Image(tmp.toURI().toString());
            imageView.setImage(img);
            Double h = img.getHeight();
            Double w = img.getWidth();
            heightTextField.setText(h.toString());
            widthTextField.setText(w.toString());
            pathTextField.setText(tmp.getPath());

            // Show picture types only if there is a picture
            if (typeSelector.getItems().isEmpty()) {
                addPictureTypes();
            }
        } else {
            // Do not show picture types if there is no picture
            removePictureTypes();
        }
    }

    public void download(ActionEvent event) {
        FileChooser ofc = new FileChooser();
        File output = ofc.showSaveDialog(null);
        FormatConvertor convertor = new FormatConvertor();
        convertor.convert(tmp, output, typeSelector.getValue());
    }

    public void addPictureTypes() {
        typeSelector.getItems().addAll(picTypes);
    }

    public void removePictureTypes() {
        typeSelector.getItems().removeAll();
    }
}
