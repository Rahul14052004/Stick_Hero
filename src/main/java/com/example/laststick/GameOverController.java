package com.example.laststick;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.laststick.PlayingController.*;

public class GameOverController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Text fixed_score;
    @FXML
    private Text fixed_best;
    @FXML
    private Text GameOver_text;

    @FXML
    private Rectangle revive_rectangle;
    @FXML
    private Text revive_Text;


    @FXML
    private Text score;
    @FXML
    private Text high_score;
    @FXML
    private AnchorPane pane;

    @FXML
    private Button restart_btn;

    @FXML
    private Button home_btn;

    @FXML
    private Button revive_btn;
    @FXML
    private Button yes_btn;

    @FXML
    private Button no_btn;

    @FXML
    private Text y_t;
    @FXML
    private Text n_t;

    @FXML
    private Rectangle y_r;
    @FXML
    private Rectangle n_r;

    @FXML
    private ImageView img;
    @FXML
    private Text text;

    public void initialize(){
        Audio.over_audio.play();
        score.setText(Integer.toString(points));
        high_score.setText(Integer.toString(highest_score));
        score.setOpacity(1);high_score.setOpacity(1);


    }

    public void over_playing(ActionEvent event) throws IOException {
        Audio.over_audio.stop();
        points =0;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playing_screen.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void over_home(ActionEvent event) throws IOException {
        Audio.over_audio.stop();
        points =0;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Opening_screen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void over_home_revive(ActionEvent event) throws IOException{
        revive_rectangle.setOpacity(1);
        revive_Text.setOpacity(1);
        img.setOpacity(1);
        n_r.setOpacity(1);
        y_r.setOpacity(1);
        y_t.setOpacity(1);n_t.setOpacity(1);
        yes_btn.setOnAction(e->{
            Parent root = null;
            if(num_cherries>=10) {
                num_cherries-=10;
                Thread saveGame = new Thread(new ProgressThread(points,highest_score,num_cherries));
                saveGame.start();
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playing_screen.fxml")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                revive_rectangle.setOpacity(0);
                revive_Text.setOpacity(0);
                img.setOpacity(0);
                n_r.setOpacity(0);
                y_r.setOpacity(0);
                y_t.setOpacity(0);n_t.setOpacity(0);
                text.setOpacity(1);


            }
        });

        no_btn.setOnAction(e->{
            try {
                over_home(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


    }

}
