package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.SQLException;


public class HeaderController {
    public static Group root;

    @FXML
    Button loginButton;

    @FXML
    Label email;

    @FXML
    TextField searchText;

    @FXML
    Button logoutButton;

    @FXML
    public void initialize() {
       if(!Main.emailId.equals("")) {
           loginButton.setOpacity(0);
           email.setText(Main.emailId);
       }
    }

    @FXML
    public void login(MouseEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

        Main.root.getChildren().add(loginPage);
    }

    @FXML
    public void search(MouseEvent event) throws IOException, SQLException {

        Header newHeader = new Header();
        ProductPage products = new ProductPage();
        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(50);
        productPane.setLayoutY(100);
        productPane.getChildren().add(products.showProductByName(searchText.getText()));
        Main.root.getChildren().clear();
        Main.root.getChildren().addAll(newHeader.root,productPane);
    }

    @FXML
    public void logout(MouseEvent event) throws IOException, SQLException {
        if(logoutButton.getOpacity() == 0) {
            logoutButton.setOpacity(1);
            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Main.emailId = "";
                    try {

                        Header header = new Header();
                        Main.root.getChildren().add(header.root);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else {
            logoutButton.setOpacity(0);
        }
    }
}
