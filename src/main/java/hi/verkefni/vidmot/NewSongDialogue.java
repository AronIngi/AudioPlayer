package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Askrifandi;
import hi.verkefni.vinnsla.NewList;
import hi.verkefni.vinnsla.NewSong;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewSongDialogue extends Dialog<NewSong>{
    private NewSong askrifandi;
    @FXML
    private TextField nameField;
    @FXML
    private TextField pathField;

    public NewSongDialogue()
    {
        setDialogPane(lesaDialog());
        setResultConverter(b -> {
            if(b != null && b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new NewSong(nameField.getText(), pathField.getText());
            }
            else
                return null;
        });
    }

    private DialogPane lesaDialog()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newsong-view.fxml"));
        try{
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        }
        catch(IOException ioException)
        {
            throw new RuntimeException(ioException);
        }
    }
}