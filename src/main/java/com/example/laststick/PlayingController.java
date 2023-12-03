package com.example.laststick;

import javafx.animation.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.beans.property.BooleanProperty;
import javafx.beans.binding.BooleanBinding;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
//import javafx.scene.media.AudioClip;


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
    Rectangle stick1;
    public static boolean timeline_stopped = false;
    private boolean animation_over = false;
    private boolean isRotating = false;

    @FXML
    private Rectangle start;
    private double angle=0.0;

    private Stick stick;
    private Timeline timeline;
    private boolean stickisfalling = false;
    private boolean falling_over = false;
    private AnimationTimer animationTimer;

    @FXML
    private Text score;
    @FXML
    private Text cherry_count;
    public static int points=0;
    public Rectangle getStart() {
        return start;
    }
    private boolean bool = false;
    private double start_pos_y;
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
    public static int  num_cherries=0;
    private double start_pos;

    public static int highest_score=0;
    private int max_score;
    public static boolean movement_over;

    public static boolean stick_over;
    private BooleanProperty space_bar_pressed = new SimpleBooleanProperty();

    public void setSpace_bar_pressed(boolean space_bar_pressed) {
        this.space_bar_pressed.set(space_bar_pressed);
    }

    private BooleanProperty A_pressed = new SimpleBooleanProperty();
    private BooleanProperty StickGrowing = new SimpleBooleanProperty();

    private Timeline game_loop;



    private Random random = new Random();
    private AnimationTimer game_timer;

    public boolean isDown_Pressed() {
        return Down_Pressed.get();
    }

    public BooleanProperty Down_PressedProperty() {
        return Down_Pressed;
    }

    public void setDown_Pressed(boolean up_Pressed) {
        this.Down_Pressed.set(up_Pressed);
    }

    private BooleanProperty Down_Pressed = new SimpleBooleanProperty();

    @FXML
    private Button gameOver_btn;


    public boolean isStickGrowing() {
        return StickGrowing.get();
    }

    public BooleanProperty StickGrowingProperty() {
        return StickGrowing;
    }

    public void setStickGrowing(boolean isStickGrowing) {
        this.StickGrowing.set(isStickGrowing);
    }

    public boolean isA_pressed() {
        return A_pressed.get();
    }

    public BooleanProperty a_pressedProperty() {
        return A_pressed;
    }

    public void setA_pressed(boolean a_pressed) {
        this.A_pressed.set(a_pressed);
    }

    public void initialize() throws InterruptedException {
        score.setText(Integer.toString(points));

        blocks = new Blocks();
        cherries  = new Cherries(pane);
        h= new Hero(hero,blocks);
        start_pos = start.getLayoutX();
        start_pos_y =start.getLayoutY();
        stick = new Stick(stick1);
        movement_setup();;

        get_block();
        stickAnimation();
        timeline.play();


        game_loop = new Timeline(new KeyFrame(Duration.seconds(0.005),e->{
            cherry_count.setText(Integer.toString(num_cherries));
            if (h.bool && h.hero_alive){
                angle = 0;
                System.out.println(second_block.getX());


                start.setWidth(second_block.getWidth());
                start.setLayoutX(137 - second_block.getWidth());
                start.setLayoutY(second_block.getY());
                second_block.setOpacity(0);

                start_pos = start.getLayoutX();
                movement_setup();
                points++;

                score.setText(Integer.toString(points));
                highest_score = Math.max(points,highest_score);
                h.bool =false;

                get_block();
                stickAnimation();
                timeline.play();
            }
            else if(h.bool && !h.hero_alive){
                stop_game_loop();
                gameOver_btn.fire();
            }
        }));
        game_loop.setCycleCount(Animation.INDEFINITE);
        game_loop.play();

    }
    public void stop_game_loop(){
        game_loop.stop();
    }
    private void stop_timeline(){
        timeline.stop();
    }

    public void stickAnimation() {

        AnimationTimer animationTimer = new AnimationTimer() {
            public void stop()
            {
                setA_pressed(false);
                setStickGrowing(false);
                super.stop();
            }

            @Override
            public void handle(long now) {

                if (isA_pressed() && isStickGrowing() && !isRotating)
                {
                    stick.update();
                }
                else if (isRotating){
                    //Starts rotation
                    if(angle<=90.0 ){
                        double pivotX = stick.getShape().getX() + stick.getShape().getWidth() / 2;
                        double pivotY = (stick.getShape().getY()+stick.getShape().getHeight());
                        stick.getShape().getTransforms().clear();
                        Rotate r =new Rotate(angle,pivotX,pivotY);
                        stick.getShape().getTransforms().add(r);
                        angle= angle+1.5;

                    }
                    else{
                        System.out.println(stick.getShape().getY());
                        isRotating=false;
                        animation_over=true;
                        this.stop();

                    }
                }
                else if (!isA_pressed())
                {
                    this.stop();
                }
            }
        };
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), e-> {

            animationTimer.start();

            if (animation_over){
                animation_over=false;
                stop_timeline();
                if(stick.getHeight()+start.getLayoutX()+start.getWidth() >= second_block.getX()&& stick.getHeight()+start.getLayoutX()+start.getWidth() <= second_block.getX()+ second_block.getWidth()) {
                    h.move_hero(this.pane, start, second_block, stick, start_pos,second_block.getX()+second_block.getWidth()- hero.getFitWidth()-15,cherries.cherry);
                    h.moving_hero();
                    System.out.println(timeline_stopped);


                }
                else{
                    h.move_hero(this.pane, start, second_block, stick, start_pos,stick.getHeight()+start.getLayoutX()+start.getWidth()-hero.getFitWidth(),cherries.cherry);
                    h.moving_hero();
                    System.out.println(timeline_stopped);

                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }


    public void get_block(){
        second_block =blocks.create_obstacles(start,pane);

        second_block.setOpacity(0.0);pane.getChildren().add(second_block);


        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.75), second_block);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        if(!(random.nextInt(2)==0)){
            cherries.put_cherry(start.getLayoutX()+start.getWidth(),second_block.getX());
        }
        else{
            cherries.cherry.setOpacity(0);
        }
    }
    public void pause(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Opening_screen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void game_over_screen(){

    }

    public void game_over(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver_screen.fxml")));
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
        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                setA_pressed(true);
                setStickGrowing(true);
            }
        });
        pane.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.A) {
                setA_pressed(false);
                isRotating = true;
            }
        });

    }
//    private void make_stick(){
//        pane.setOnKeyReleased(e->{
//
//        });
//    }


}
