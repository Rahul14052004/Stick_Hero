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
    private Blocks b;
    private boolean upside_down; // true when inverted otherwise false
    public Hero(ImageView hero,Blocks b){
        this.upside_down=false;
        this.hero = hero;
        this.b=b;
    }

    public void make_stick(){

    }


    Image i1 =new Image("C:\\Users\\rahul\\Desktop\\LastStick\\src\\main\\resources\\com\\example\\laststick\\bb8_f1.png");
    Image i2 = new Image("C:\\Users\\rahul\\Desktop\\LastStick\\src\\main\\resources\\com\\example\\laststick\\bb8-f2.png");

    public void move_hero(Rectangle start,Rectangle second_block,Stick stick,double start_pos){



        hero_move= new Timeline(new KeyFrame(Duration.seconds(0.005), event-> {

            if( this.hero.getX() < start.getLayoutX() + stick.getHeight()) {
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
                System.out.println("sup");
                System.out.println(stick.getShape().getLayoutY());
                System.out.println(second_block.getX());
                System.out.println((stick.getHeight() < second_block.getX()));
                if(hero_status(stick,second_block,start)){
                    System.out.println("sup");
                    b.move_scene(start, second_block, start_pos,this,stick);
                    b.moving_scene();
                }

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

    public boolean hero_status(Stick stick,Rectangle second_block,Rectangle start){
        return !((start.getLayoutX()+ start.getWidth()+ stick.getHeight() < second_block.getX()) || start.getLayoutX()+start.getWidth() +stick.getHeight()> second_block.getX()+second_block.getWidth());
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
