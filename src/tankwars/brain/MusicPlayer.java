package tankwars.brain;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.InputStream;


public class MusicPlayer {

    public static void playMusic(){
        try{
            InputStream in = MusicPlayer.class.getResourceAsStream("/Music/GameMusic.wav");
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

}
