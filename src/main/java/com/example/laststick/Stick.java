package com.example.laststick;



import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

class Stick {
    private final Rectangle shape;
    private double height;
    private double angle=0.0;


    public Stick(Rectangle r) {
        this.shape = r;
        this.height = 10; // Initial stick length
    }
    public Rectangle getShape() {
        return shape;
    }
    public void setHeight(double length) {
        this.height = length;
    }
    public double getHeight() {
        return height;
    }

    public void update() {
        this.shape.setY(shape.getY()-2);
        this.height+=2;
        this.shape.setHeight(this.height);
    }

    public void rotate(){
//        RotateTransition r = new RotateTransition();
//        r.setAxis(Rotate.Z_AXIS);
//        r.setByAngle(90);
//        r.setDelay(Duration.seconds(1));
//        r.setCycleCount(1);
//        r.setDuration(Duration.millis(1000));
//        r.setNode(this.shape);
//        r.play();
//
//        TranslateTransition t =new TranslateTransition();
//        t.setToX(this.height+this.shape.getX());
//        t.setDelay(Duration.seconds(1));
//        t.setCycleCount(1);
//        t.setNode(this.shape);
//        t.play();

        Timeline rotateTimeline = new Timeline(new KeyFrame(Duration.seconds(0.004),event->{
            if(angle<=90.0){
                double pivotX = this.shape.getX() + this.shape.getWidth() / 2;
                double pivotY = (this.shape.getY()+this.shape.getHeight());
                this.shape.getTransforms().clear();
                Rotate r =new Rotate(angle,pivotX,pivotY);
                this.shape.getTransforms().add(r);
                angle= angle+1.5;
            }
            else{

                angle=0;
            }
        }));
        rotateTimeline.setCycleCount(1);
        rotateTimeline.play();
    }

}