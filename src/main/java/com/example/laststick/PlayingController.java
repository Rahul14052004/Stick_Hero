package com.example.laststick;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.beans.property.BooleanProperty;
import javafx.beans.binding.BooleanBinding;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class PlayingController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Text Opening_Text;
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView hero;

    @FXML

    private Hero h;
    private Blocks blocks;

    private Cherries cherries;

    @FXML
    private Rectangle start;

    public Rectangle getStart() {
        return start;
    }

    public void setStart(Rectangle start) {
        this.start = start;
    }

    public Rectangle getSecond_block() {
        return second_block;
    }

    public void setSecond_block(Rectangle second_block) {
        this.second_block = second_block;
    }

    private Rectangle second_block;
    private static int  num_cherries;
    private double start_pos;

    private int highest_score;
    private int max_score;
    private BooleanProperty space_bar_pressed = new SimpleBooleanProperty();

    public void setSpace_bar_pressed(boolean space_bar_pressed) {
        this.space_bar_pressed.set(space_bar_pressed);
    }

    public void initialize() throws InterruptedException {
        h= new Hero(hero);
        blocks = new Blocks();
        start_pos = start.getLayoutX();
        get_block();
        System.out.println(second_block.getLayoutX());
        System.out.println(second_block.getX());
        System.out.println(start_pos);
        //h.move_hero(second_block);h.moving_hero();
        blocks.move_scene(this.start,this.second_block,start_pos,h);blocks.moving_scene();





    }

    public void get_block(){
        second_block =blocks.create_obstacles(start,pane);second_block.setOpacity(0.0);pane.getChildren().add(second_block);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.75), second_block);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
    }
    public void pause(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Opening_screen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void revive(Hero h){

    }


    private void movement_setup(){
        pane.setOnKeyReleased(e->{
            if(e.getCode() == KeyCode.SPACE){
                space_bar_pressed.set(true);
            }
        });
        pane.setOnKeyReleased(e->{
            if(e.getCode() == KeyCode.SPACE){
                space_bar_pressed.set(false);
            }
        });
    }
    private void make_stick(){
        pane.setOnKeyReleased(e->{

        });
    }


}
