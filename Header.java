package com.example.supplychain;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Header {

    public Pane root;
    Header() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Header.fxml"));
    }
}
