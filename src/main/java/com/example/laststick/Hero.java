package com.example.laststick;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Objects;

public class Hero {

    public ImageView hero;


    private double start_val = 0;
    Timeline hero_move;

    Timeline hero_die;
    private Blocks b;

    private boolean cherry_found = false;
    public boolean hero_alive = true;
    public boolean bool = false;
    public boolean isS_Pressed() {

        return S_Pressed.get();
    }

    public BooleanProperty S_PressedProperty() {
        return S_Pressed;
    }

    public void setS_Pressed(boolean down_Pressed) {
        this.S_Pressed.set(down_Pressed);
    }

    private BooleanProperty S_Pressed = new SimpleBooleanProperty();
    private boolean upside_down; // true when inverted otherwise false
    public Hero(ImageView hero,Blocks b){
        this.upside_down=false;
        this.hero = hero;
        this.b=b;

    }

    String image  = String.format("/com/example/laststick/bb8_f1.png");
    String image2 = String.format("/com/example/laststick/bb8-f2.png");
    Image i1 =new Image(Objects.requireNonNull(getClass().getResourceAsStream(image)));
    Image i2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(image2)));

    public void move_hero(AnchorPane pane,  Rectangle start, Rectangle second_block, Stick stick, double start_pos,double end_pos,ImageView cherry){

        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.S) {
                setS_Pressed(true);
                upside_down = true;

            }
        });
        pane.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.S) {
                setS_Pressed(false);
                upside_down=false;

            }
        });

        hero_move= new Timeline(new KeyFrame(Duration.seconds(0.005), event-> {

            if( this.hero.getX() < end_pos-45) {


                if(cherry_found ==false &&isS_Pressed() && ((this.hero.getX()+60+80-(int)(this.hero.getFitWidth()/2) >=(int)cherry.getX()-15) && this.hero.getX()+60+80-(int)(this.hero.getFitWidth()/2) <=(int)cherry.getX()+(int)cherry.getFitWidth())){
                    Audio.cherry_audio.play();
                    cherry_found=true;
                    PlayingController.num_cherries++;
                    cherry.setOpacity(0);

                }

                if(upside_down && (this.hero.getX()+this.hero.getLayoutX() == (second_block.getX())-this.hero.getFitWidth()+25)){
                    stopping_hero();
                    hero_dies(start,stick);
                    killing_hero();
                }
                if(isS_Pressed()){
                    flip_hero(second_block);
                }
                if(!isS_Pressed()){
                    flip_back_hero();

                }

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
                if(hero_status(stick,second_block,start)){

                    b.move_scene(start, second_block, start_pos,this,stick,cherry);
                    b.moving_scene();
                    stick.reset_stick();
                }
                else{
                    hero_dies(start,stick);
                    killing_hero();
                }

            }
        }));
    }
    public void hero_dies(Rectangle start, Stick stick){
        final double[] angle = {90};
        hero_die = new Timeline(new KeyFrame(Duration.seconds(0.005), event-> {
            Audio.death_audio.play();
            if( this.hero.getY() < start.getLayoutY() + start.getHeight()) {
                if((int)start_val %2 == 0) {
                    this.hero.setImage(i1);

                }
                else{
                    this.hero.setImage(i2);

                }
                start_val=start_val+0.05;
                this.hero.setY(hero.getY() +2 );


                if (angle[0] >=90 && angle[0] <=180){
                    double pivotX = stick.getShape().getX() + stick.getShape().getWidth() / 2;
                    double pivotY = (stick.getShape().getY()+stick.getShape().getHeight());
                    stick.getShape().getTransforms().clear();
                    Rotate r =new Rotate(angle[0],pivotX,pivotY);
                    stick.getShape().getTransforms().add(r);
                    angle[0] = angle[0] +1.5;
                }
                else{
                    angle[0] =0;
                }
            }
            else{
                stopping_death();
                bool = true;
                }

            }
        ));
    }

    private void stopping_death(){
        hero_die.stop();

    }


    public void killing_hero(){
        hero_alive=false;
        hero_die.setCycleCount(Animation.INDEFINITE);
        hero_die.play();
    }

    public void moving_hero(){
        hero_move.setCycleCount(Animation.INDEFINITE);
        hero_move.play();
    }
    private void stopping_hero(){

        hero_move.stop();
        cherry_found=false;
    }

    public boolean hero_status(Stick stick,Rectangle second_block,Rectangle start){
        hero_alive = !((start.getLayoutX()+ start.getWidth()+ stick.getHeight() < second_block.getX()) || start.getLayoutX()+start.getWidth() +stick.getHeight()> second_block.getX()+second_block.getWidth() || (this.isS_Pressed() && this.hero.getLayoutX()==second_block.getX()));
        return !((start.getLayoutX()+ start.getWidth()+ stick.getHeight() < second_block.getX()) || start.getLayoutX()+start.getWidth() +stick.getHeight()> second_block.getX()+second_block.getWidth() || (this.isS_Pressed() && this.hero.getLayoutX()==second_block.getX()));
    }

    public void flip_hero(Rectangle second_block){
        if(this.hero.getX()<=second_block.getWidth()+second_block.getX() && this.hero.getX() >= second_block.getX()) {
            hero.setLayoutY(295);
            hero.setScaleY(-1);
        }
        else{
            hero.setLayoutY(295);
            hero.setScaleY(-1);
        }

    }
    public void flip_back_hero(){
        hero.setLayoutY(240);
        hero.setScaleY(1);
    }

    public boolean get_upside_down(){

        return upside_down;
    }
    public void turn_upside_down(){
        this.upside_down = !this.upside_down;
    }


}
