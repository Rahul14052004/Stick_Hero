package com.example.laststick;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class Audio {
    static String stick_sound = "src\\main\\resources\\com\\example\\laststick\\laser-sword-science-fiction-sound-9830-[AudioTrimmer.com].wav";
    static String opening_sound = "src\\main\\resources\\com\\example\\laststick\\OpeningAudio.mp3";

    static String playling_sound ="src\\main\\resources\\com\\example\\laststick\\PlayingAudio.mp3";
    static String cherry_sound ="src\\main\\resources\\com\\example\\laststick\\CherryAudio.wav";
    static String death_sound ="src\\main\\resources\\com\\example\\laststick\\DeathSound.wav";

    static String over_sound ="src\\main\\resources\\com\\example\\laststick\\GameOverAudio.mpeg";

    static MediaPlayer over_audio = new MediaPlayer(new Media(new File(over_sound).toURI().toString()));

    static MediaPlayer death_audio = new MediaPlayer(new Media(new File(death_sound).toURI().toString()));
    static MediaPlayer cherry_audio = new MediaPlayer(new Media(new File(cherry_sound).toURI().toString()));

     static MediaPlayer stick_growing = new MediaPlayer(new Media(new File(stick_sound).toURI().toString()));
    static MediaPlayer opening_track = new MediaPlayer(new Media(new File(opening_sound).toURI().toString()));

    static MediaPlayer playing_track = new MediaPlayer(new Media(new File(playling_sound).toURI().toString()));


}
