package sound;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {

    URL soundUrl;
//    Clip clip;
    public Sound(String name){
        this.soundUrl= getClass().getResource("/sound/"+name+".wav");

    }

    public void loadFile(Clip clip){
        try {
            clip.close();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.soundUrl);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void play(Clip clip){
        clip.loop(1);
        clip.start();
    }
    public void stop(Clip clip){
        clip.stop();
    }
}
