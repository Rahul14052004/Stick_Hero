package com.example.laststick;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;
public class Blocks {
    public static boolean bool = false;

    private Random rand = new Random();
    Timeline scene_move;
    private int max_width =  100;
    private int min_width = 60;
    private int height=960;
    private int ceiling = 300; //max distance from last block
    private int starting_x = 154;
    private Hero hero;



    public Rectangle create_obstacles(Rectangle start_block, AnchorPane pane){

        return new Rectangle(rand.nextInt((int)start_block.getLayoutX()+200,(int)start_block.getLayoutX()+450),start_block.getLayoutY(),rand.nextInt(min_width,max_width),start_block.getHeight());
    }
    public void move_scene(Rectangle start,Rectangle second_block,double start_pos,Hero h,Stick stick){

        scene_move= new Timeline(new KeyFrame(Duration.seconds(0.002), event-> {

            if( second_block.getX()+ second_block.getWidth() > 137 ) {

                start.setLayoutX(start.getLayoutX() - 1);
                second_block.setX(second_block.getX() - 1);
                h.hero.setX(h.hero.getX() - 1);

            }
            else{
                stopping_scene();
                h.bool = true;


                h.hero.setX(second_block.getX()-h.hero.getFitWidth());
            }



        }));
    }
    public void moving_scene(){
        scene_move.setCycleCount(Animation.INDEFINITE);
        scene_move.play();
    }
    public void stopping_scene(){
        scene_move.stop();



    }
}
