package snake.io.midia;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;

public class AudioPlayer extends Thread {

    Clip clip;

    public AudioPlayer() {
    }

    public void play(String filePath) {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(
                            new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(filePath)));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(String.format("error on play %s by %s", filePath, e.getMessage()));
        }
    }

    public void playGameMusic() {
        String[] gameMusics = {"sounds/Game1.mid", "sounds/Game2.mid", "sounds/Game3.mid"};
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = MidiSystem.getSequence(getClass().getClassLoader().getResourceAsStream(gameMusics[0]));
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(50);
            sequencer.start();
        } catch (Exception e) {
            System.out.println(String.format("error on play MIDI by %s", e.getMessage()));
        }
    }
}
