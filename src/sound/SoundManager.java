package sound;

import main.Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.util.HashMap;

public class SoundManager {
    Game game;
    HashMap<String, Sound> sounds;
    Clip clip;

    public SoundManager(Game game){
        this.game = game;

        this.sounds = new HashMap<>();
        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        this.loadSounds();

    }
    //todo: audio files shouldn't be set here
    // but somewhere else where we put our custom game stuffs.
    // this should be more generic
    public void loadSounds(){

        Sound chest = new Sound("chest");
        Sound door = new Sound("door");
        this.sounds.put("chest", chest);
        this.sounds.put("door", door);
    }

    public void play(String name){
        Sound sound = this.sounds.get(name);
        if(sound == null){
            return;
        }
        sound.loadFile(this.clip);
        sound.play(this.clip);
    }

    //todo: stop what song?
     public void stop(String name){
         Sound sound = this.sounds.get(name);
         if(sound == null){
             return;
         }
         sound.stop(this.clip);
     }

     //todo: if a background song is created make it loop
}
