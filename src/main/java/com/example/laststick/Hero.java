package com.example.laststick;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Hero {

    public ImageView hero;
    private double start_val = 0;
    Timeline hero_move;

    private boolean upside_down; // true when inverted otherwise false
    public Hero(ImageView hero){
        this.upside_down=false;
        this.hero = hero;
    }

    public void make_stick(){

    }


    Image i1 =new Image("C:\\Users\\rahul\\Desktop\\LastStick\\src\\main\\resources\\com\\example\\laststick\\bb8_f1.png");
    Image i2 = new Image("C:\\Users\\rahul\\Desktop\\LastStick\\src\\main\\resources\\com\\example\\laststick\\bb8-f2.png");

    public void move_hero(Rectangle second_block){



         hero_move= new Timeline(new KeyFrame(Duration.seconds(0.005), event-> {

            if( this.hero.getX() < second_block.getX() - second_block.getWidth()) {
                if((int)start_val %2 == 0) {
                    this.hero.setImage(i1);

                }
                else{
                    this.hero.setImage(i2);

                }
                start_val=start_val+0.05;
                this.hero.setX(hero.getX() + 1);

            }
            else{
                stopping_hero();

            }



        }));
    }

    public void moving_hero(){
        hero_move.setCycleCount(Animation.INDEFINITE);
        hero_move.play();
    }
    private void stopping_hero(){
        hero_move.stop();
    }

    public boolean hero_status(double x1,double x2){
        return true;
    }

    public void flip_hero(){

    }

    public boolean get_upside_down(){

        return upside_down;
    }
    public void turn_upside_down(){
        this.upside_down = !this.upside_down;
    }


}
