package com.example.imagemanagement;

import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.*;

public class ImageController {
    @FXML
    ImageView imageView = new ImageView();

    @FXML
    private TextField heightTextField = new TextField();

    @FXML
    private TextField widthTextField = new TextField();

    @FXML
    private TextField cameraTextField = new TextField();

    @FXML
    private TextField datetimeTextField = new TextField();

    @FXML
    public TextField exposureTextField = new TextField();

    @FXML
    public TextField fNumberTextField = new TextField();

    @FXML
    public TextField isoTextField = new TextField();;

    @FXML
    public TextField lensTextField = new TextField();;

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
            try {
                Metadata metadata = ImageMetadataReader.readMetadata(tmp);
                for (Directory directory : metadata.getDirectories()) {
                    for (Tag tag : directory.getTags()) {
                        System.out.println(tag);
                    }
                }
                // obtain the Exif directory
                ExifIFD0Directory ifd0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                // query the tag's value
                String camera = ifd0Directory.getDescription(ExifSubIFDDirectory.TAG_MODEL);
                cameraTextField.setText(camera);
                String datetime = ifd0Directory.getDescription(ExifSubIFDDirectory.TAG_DATETIME);
                datetimeTextField.setText(datetime);
                // obtain the Exif subdirectory
                ExifSubIFDDirectory subIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                String exposure = subIFDDirectory.getDescription(ExifSubIFDDirectory.TAG_EXPOSURE_TIME);
                exposureTextField.setText(exposure);
                String fNumber = subIFDDirectory.getDescription(ExifSubIFDDirectory.TAG_FNUMBER);
                fNumberTextField.setText(fNumber);
                String iso = subIFDDirectory.getDescription(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT);
                isoTextField.setText(iso);
                String lens = subIFDDirectory.getDescription(ExifSubIFDDirectory.TAG_LENS_MODEL);
                lensTextField.setText(lens);
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        ofc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Type", "*." + typeSelector.getValue().toLowerCase()));
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
