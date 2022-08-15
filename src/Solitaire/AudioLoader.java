package Solitaire;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioLoader {

    private AudioLoader() {}

    /**
     * Load audio file in .WAV format.
     * @param fileName filename of the file in the resource folder
     * @return target file as AudioInputSream object
     */
    public static AudioInputStream loadAudioFile(String fileName) {


        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                            AudioLoader.class.getClassLoader().getResourceAsStream(fileName
                    ));
            return audioInputStream;

        } catch (Exception e) {
            System.out.println("An error has occurred while loading Audio file!");

        }

        return null;

    }

    /**
     * Play the AudioInputStream object sound
     * @param sound the AudioInputStream object to be played
     */
    public static void playAudioFile(AudioInputStream sound) {

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();

        } catch (Exception e) {
            System.out.println("An error has occurred while playing Audio file!");

        }

    }

}
