package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Scanner;

public class ListiController {
    @FXML
    private ListView<Lag> fxListView;
    @FXML
    private Slider fxSlider;
    @FXML
    private Button fxPlayButton;

    @FXML
    private Button fxHomeButton;
    private Lagalisti listi;
    private MediaPlayer mediaPlayer;
    private NewSongDialogue songDialogue;

    @FXML
    private void initialize() throws URISyntaxException, MalformedURLException {
        listi = Lagalistar.getListi(Lagalistar.getIndex());
        fxListView.setOnMouseClicked(event -> {
            try {
                onVeljaLag(event);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });

        try {listi.lesaLog("src/main/resources/hi/verkefni/vidmot/lists/"+listi.getName()+".list");}
        catch (IOException io){ io.printStackTrace();}

        if(!listi.getListi().isEmpty()) {
            fxListView.setItems(listi.getListi());

            mediaPlayer = new MediaPlayer(new Media(getClass().getResource(listi.getIndex().getFileName()).toURI().toURL().toString()));
            setupMediaPlayer(mediaPlayer);
        }
        System.out.println(Lagalistar.getListi(Lagalistar.getIndex()));
        System.out.println(listi.toString());
    }

    @FXML
    public void onNewButton() throws IOException
    {
        songDialogue = new NewSongDialogue();
        Optional<NewSong> newSong = songDialogue.showAndWait();
        newSong.ifPresent(value -> {
            String name = Lagalistar.getListi(Lagalistar.getIndex()).getName();
            File f = new File("src/main/resources/hi/verkefni/vidmot/lists/"+name+".list");
            try{
                FileWriter pw = new FileWriter(f, true);

                pw.write("\n"+value.getPath() + "," + value.getNafn());
                pw.close();
            }
            catch(IOException io)
            { System.out.println("io"); }

            listi.addLag(value.getPath() + "," + value.getNafn());
        });
    }

    /**
     * Býr til nýjan media player þegar ýtt er á lag
     * @param event
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    private void onVeljaLag(MouseEvent event) throws URISyntaxException, MalformedURLException {
        Lag selectedLag = fxListView.getSelectionModel().getSelectedItem();
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(selectedLag.getFileName()).toURI().toURL().toString()));
        setupMediaPlayer(mediaPlayer);
        listi.setIndex(fxListView.getSelectionModel().getSelectedIndex());
    }

    /**
     * Spilar næsta lag þegar þetta lag er búið
     */
    private void onLagStop()
    {
        try {
            mediaPlayer = new MediaPlayer(new Media(getClass().getResource(listi.getNext().getFileName()).toURI().toURL().toString()));
            setupMediaPlayer(mediaPlayer);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.play();
    }
    @FXML
    private void onHome()
    {
        if(listi.getListi().size() > 0) {
            listi.deleteList();
            mediaPlayer.dispose();
        }
        ViewSwitcher.switchTo(View.MAIN);
    }

    /**
     * spilar lagið þegar ýtt er á play takkan
     * @param actionEvent
     */
    private void onPlayButton(ActionEvent actionEvent)
    {
        mediaPlayer.play();
    }

    /**
     * pásar lagið þegar ýtt er á pásu takkan
     * @param actionEvent
     */
    private void onPauseButton(ActionEvent actionEvent)
    {
        mediaPlayer.pause();
    }

    /**
     * býr til bindings og addlisteners á media player fallið fær sem parameter
     * @param mPlayer media player sem á að stilla
     */
    private void setupMediaPlayer(MediaPlayer mPlayer)
    {
        mPlayer.setOnEndOfMedia((Runnable)() -> mPlayer.stop());
        mPlayer.setOnStopped(this::onLagStop);
        fxSlider.maxProperty().bind(Bindings.createDoubleBinding(
                () -> mPlayer.getTotalDuration().toSeconds(),
                mPlayer.totalDurationProperty()));
        mPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) ->{
            fxSlider.setValue(newValue.toSeconds());
        });

        mediaPlayer.setOnReady((Runnable)() -> fxPlayButton.setOnAction(this::onPlayButton));
        mediaPlayer.setOnPlaying((Runnable)() -> {
            fxPlayButton.setText("Pause");
            fxPlayButton.setOnAction(this::onPauseButton);
        });
        mediaPlayer.setOnPaused((Runnable)() -> {
            fxPlayButton.setText("Play");
            fxPlayButton.setOnAction(this::onPlayButton);
        });
    }
}
