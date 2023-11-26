package com.example.laststick;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class Controller {

    @FXML
    private AnchorPane pane;


    private Event event;
    private Stage stage;
    private Scene scene;
    public void initialize() throws InterruptedException {








    }

   /* private boolean is_dead(){
        return (hero.getX()>= second_block.getX() && hero.getX() <= second_block.getX()+ second_block.getWidth());


    } */

    /*private void move_hero(){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(hero);
        translate.setDuration(Duration.millis(1500));
        System.out.println(second_block.getX());
        System.out.println(hero.getX());
        System.out.println(second_block.getWidth());
        System.out.println(second_block.getX());
        translate.setToX(second_block.getX()-start.getWidth()/2);
        translate.play();





    } */

    private void move_scene(){
        TranslateTransition t1 = new TranslateTransition();
        t1.setNode(pane);
        t1.setDuration(Duration.millis(1500));
        t1.setByX(-1000);
        t1.play();

    }

    private void update_points(){

    }

/*
    private void make_stick(){
        Line stick = new Line(hero.getX(),hero.getY(),5,);
        pane.getChildren().add(stick);

        pane.setOnKeyPressed(event ->{
            while(event.getCode()== KeyCode.SPACE){
                stick.setEndY(stick.getEndY()+1);
            }
        });

        pane.setOnKeyReleased(event ->{
            while(event.getCode()==KeyCode.SPACE){
                return;
            }
        });
    }
*/
    public void start_game(){

    }

    public void end_game(){

    }
    public void opening_playing(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playing_screen.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}