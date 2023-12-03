package com.example.laststick;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Cherries {
    Random random = new Random();
    public ImageView cherry= new ImageView();
    public AnchorPane pane;

    Cherries(AnchorPane pane){
        this.cherry.setImage(new Image("C:\\Users\\rahul\\Desktop\\LastStick\\src\\main\\resources\\com\\example\\laststick\\cherry2-removebg-preview.png"));
        this.cherry.setY(314);
        this.cherry.setX(0);
        this.cherry.setFitHeight(21);this.cherry.setFitWidth(25);
        this.cherry.setOpacity(0);
        this.pane = pane;
        pane.getChildren().add(cherry);


    }

    public void put_cherry(double x1,double x2){
        cherry.setX(random.nextDouble(x1+30,x2-20));
        cherry.setOpacity(1);






    }
}
