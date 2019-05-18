package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mainpackage.Assembler;


public class Controller {

    public Controller(){
    }

    @FXML
    public TextArea textArea;

    @FXML
    public MenuBar menuBar;

    Model model;

    public TextArea getTextArea() {
        return textArea;
    }

    public Pane getBorderPane() {
        return borderPane;
    }

    @FXML
    public Pane borderPane;

    @FXML
    public ScrollPane scrollPane;


    public void onOpenClick(ActionEvent actionEvent) {
        model.openFile();
    }

    public void onSaveClick(ActionEvent actionEvent) {
        model.saveToFile(model.getFilePath());
    }

    public void onCloseClick(ActionEvent actionEvent) {
        model.closeFile();
    }

    public void onSaveAsClick(ActionEvent actionEvent) {
        model.saveAs();
    }

    public void onNewClick(ActionEvent actionEvent) {
        model.newFile();
    }

    @FXML
    public void initialize() {
        menuBar.setBackground(new Background(new BackgroundFill(Color.rgb(60,63,65 ), CornerRadii.EMPTY, Insets.EMPTY)));
        borderPane.setBackground(new Background(new BackgroundFill(Color.rgb(60,63,65 ), CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(60,63,65 ), CornerRadii.EMPTY, Insets.EMPTY)));
        model = new Model();
        model.setController(this);
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if(model.isSourceFileViewed())
                model.setChangesMade(true);
        });
        menuBar.getMenus().get(1).getItems().get(1).setDisable(true);
        menuBar.getMenus().get(1).getItems().get(2).setDisable(true);
        menuBar.getMenus().get(1).getItems().get(3).setDisable(true);
    }

    public void onAssembleClick(ActionEvent actionEvent) {
        model.assembleClick();
    }

    public void onViewListFileClick(ActionEvent actionEvent) {
        model.viewListFile();
    }

    public void onViewObjectFileClick(ActionEvent actionEvent) {
        model.viewObjectFile();

    }

    public void onViewSourceFileClick(ActionEvent actionEvent) {
        model.viewSourceFile();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}

