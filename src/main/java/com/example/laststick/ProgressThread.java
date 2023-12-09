package com.example.laststick;

import java.io.*;

public class ProgressThread implements Runnable{

    private int points;
    private int highest_score;
    private int num_cherries;
    public ProgressThread(int points,int highest_score,int num_cherries){
        this.highest_score= highest_score;
        this.points = points;
        this.num_cherries = num_cherries;
    }
    public void run(){
        try {
            saveProgess(points,highest_score,num_cherries);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void saveProgess(int points,int highest_score,int num_cherries) throws IOException {
        PrintWriter fp = new PrintWriter(new FileWriter("SaveProgress.txt"));
        fp.println(points); //stores the latest score
        fp.println(highest_score); //stores highest score in file
        fp.println(num_cherries); // stores number of cherries the game
        fp.close();
    }


}
