package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Askrifandi;
import hi.verkefni.vinnsla.NewList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;

public class NewListDialogue extends Dialog<NewList>{
    private Askrifandi askrifandi;
    @FXML
    private TextField nameField;

    public NewListDialogue()
    {
        setDialogPane(lesaDialog());
        setResultConverter(b -> {
            if(b != null && b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new NewList(nameField.getText());
            }
            else
                return null;
        });
    }

    private DialogPane lesaDialog()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newlist-view.fxml"));
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