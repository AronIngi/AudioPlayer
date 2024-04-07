package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Askrifandi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;

public class AskrifandiDialogue extends Dialog<Askrifandi>{
    private Askrifandi askrifandi;
    @FXML
    private TextField nameField;

    public AskrifandiDialogue()
    {
        setDialogPane(lesaDialog());
        setResultConverter(b -> {
            if(b != null && b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new Askrifandi(nameField.getText());
            }
            else
                return null;
        });
    }

    private DialogPane lesaDialog()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("askrifandi-view.fxml"));
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
