package com.example.laststick;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;


import static java.lang.Thread.sleep;

public class HelloController {
    @FXML
    private Text Opening_Text;
    @FXML
    private AnchorPane pane;

    @FXML
    private Rectangle hero;

    private Blocks blocks;

    @FXML
    private Rectangle start;
    private Rectangle second_block;

    public void initialize() throws InterruptedException {
        BackgroundFill bg = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY,Insets.EMPTY);
        pane.setBackground(new Background(bg));
        hero.setFill(Color.GRAY);
        Opening_Text.setText("Click Q to start Playing!");
        Thread.sleep(1000);
        //make_stick();

        blocks = new Blocks();second_block =blocks.create_obstacles();pane.getChildren().add(second_block);
        move_hero();






    }

    private boolean is_dead(){
        return (hero.getX()>= second_block.getX() && hero.getX() <= second_block.getX()+ second_block.getWidth());


    }

    private void move_hero(){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(hero);
        translate.setDuration(Duration.millis(1500));
        System.out.println(second_block.getX());
        System.out.println(hero.getX());
        System.out.println(second_block.getWidth());
        System.out.println(second_block.getX());
        translate.setToX(second_block.getX()-start.getWidth()/2);
        translate.play();





    }

    private void move_block(){
        TranslateTransition t1 = new TranslateTransition();
        t1.setNode(pane);
        t1.setDuration(Duration.millis(1500));
        t1.setByX(-1000);
        t1.play();

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


}