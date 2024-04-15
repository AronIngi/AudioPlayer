package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Askrifandi;
import hi.verkefni.vinnsla.Lagalistar;
import hi.verkefni.vinnsla.Lagalisti;
import hi.verkefni.vinnsla.NewList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class PlayerController {

    @FXML
    private ObservableList<String> listi;

    @FXML
    private ListView<String> fxList;
    @FXML
    private Label fxAskrLabel;
    @FXML
    private Button newButton;
    private AskrifandiDialogue ad;
    private NewListDialogue listDialogue;


    private void getListFiles(final File dir)
    {
        System.out.println(dir);
        for(final File fileEntry : dir.listFiles())
                Lagalistar.addListi(fileEntry.getPath());
    }

    @FXML
    private void initialize()
    {
        listi = FXCollections.observableArrayList();

        if(Lagalistar.size() == 0)
            getListFiles(new File("src/main/resources/hi/verkefni/vidmot/lists"));
        for(int i = 0; i < Lagalistar.size(); i++)
            listi.add(Lagalistar.getListi(i).getName());

        fxList.setOnMouseClicked(this::onVeljaLista);
        fxList.setItems(listi);
    }

    @FXML
    private void onLogin() throws IOException {
        ad = new AskrifandiDialogue();
        Optional<Askrifandi> askrifandi = ad.showAndWait();
        askrifandi.ifPresent(value -> fxAskrLabel.setText(value.get()));
    }

    @FXML private void newList() throws IOException
    {
        listDialogue = new NewListDialogue();
        Optional<NewList> newList = listDialogue.showAndWait();
        newList.ifPresent(value -> {
                File f = new File("src/main/resources/hi/verkefni/vidmot/lists/"+value.get()+".list");
                boolean createdFile = false;
                try {
                    createdFile = f.createNewFile();
                    PrintWriter pw = new PrintWriter(f);

                    pw.print("#"+value.get());
                    pw.close();
                }
                catch(IOException ioe)
                {
                    System.out.println("io");
                }
                Lagalistar.addListi(f.getPath());
                listi.add(value.get());
        });

    }

    private void onVeljaLista(MouseEvent event)
    {
        Lagalistar.setIndex(fxList.getSelectionModel().getSelectedIndex());
        ViewSwitcher.switchTo(View.LISTI);
    }

}
