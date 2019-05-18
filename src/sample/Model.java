package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Optional;

public class Model {

    private boolean changesMade = false;
    private String filePath = "";
    private String filename = "Untitled";
    private File file;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Model() {
    }

    public void openFile() {
        int result;
        //open "Do you want to save changes" dialog
        if(!changesMade)
        {
            openFileChooser();
        }
        else {
            result = showSaveDialog();
            if(result==0)
                openFileChooser();
        }
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(controller.getBorderPane().getScene().getWindow());

        if(file == null)
            System.out.println("no file selected");
        else
        {
            this.file = file;
            filePath = file.getPath();
            filename = file.getName();
            Main.primaryStage.setTitle(filename);
            readFromFile(file);
        }
    }

    private void readFromFile(File file) {

        //reads from text file line by line and writes into text area
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = bufferedReader.readLine();
            controller.getTextArea().clear();

            while (line != null) {
                controller.getTextArea().setText(controller.getTextArea().getText() + line + "\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int showSaveDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to save changes to " + filename + "?");

        ButtonType buttonTypeOne = new ButtonType("Save");
        ButtonType buttonTypeTwo = new ButtonType("Don't Save");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            System.out.println("save");
            //do save stuff
            saveToFile(filePath);
            return 1;
        } else if (result.get() == buttonTypeTwo) {
            System.out.println("don't save");
            return 0;
        } else {
            System.out.println("cancel/close");
            return -1;
        }
    }

    //save as
    //opens file chooser, creates text file and writes from textarea to text file
    public void saveAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(controller.borderPane.getScene().getWindow());

        //save to file
        if (file != null) {
            this.file = file;
            filePath = file.getPath();
            filename = file.getName();
            Main.primaryStage.setTitle(filename);
            System.out.println("filename = " + filename);
            System.out.println("path = " + filePath);
            saveToFile(filePath);

        }

    }

    public void saveToFile(String filepath) {

        if(filepath.equals(""))
            saveAs();

        String text = controller.getTextArea().getText();

        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(this.filePath));
            buffer.write(text);
            buffer.close();
            changesMade = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        if(!changesMade)
            System.exit(0);
        else
        if(showSaveDialog()==0)
            System.exit(0);
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isChangesMade() {
        return changesMade;
    }

    public void setChangesMade(boolean changesMade) {
        this.changesMade = changesMade;
    }

    public void newFile() {
        int result;

        if(changesMade)
        {
            result = showSaveDialog();

            if(result == 1)
                saveToFile(filePath);
            else if(result == 0)
            {
                controller.getTextArea().clear();
                Main.primaryStage.setTitle("Untitled.txt");
            }

        }
        else{
            controller.getTextArea().clear();
            Main.primaryStage.setTitle("Untitled.txt");
        }

    }
}
