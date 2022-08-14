package Solitaire;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;

public class CardSound {

    private static final String cardMoveSoundEffectFilePath = "\\card_move_sound_effects\\";
    private static final String cardShuffleSoundEffectFilePath = "\\card_shuffle_sound_effects\\";

    private static final String[] cardMoveSoundEffects = new String[] {
            "zapsplat_card_move_sound_effect_01.wav",
            "zapsplat_card_move_sound_effect_02.wav",
            "zapsplat_card_move_sound_effect_03.wav",
            "zapsplat_card_move_sound_effect_04.wav",
            "zapsplat_card_move_sound_effect_05.wav",
            "zapsplat_card_move_sound_effect_06.wav",
            "zapsplat_card_move_sound_effect_07.wav"
    };

    private static final String[] cardShuffleSoundEffects = new String[] {
            "zapsplat_card_shuffle_sound_effect_01.wav",
            "zapsplat_card_shuffle_sound_effect_02.wav",
            "zapsplat_card_layout_sound_effect.wav"

    };

    private static final Random rand = new Random();

    private static boolean playWithSound = false;

    private CardSound() {}

    public static void playRandomCardShuffleSoundEffect() {

        if (playWithSound) {
            int index = rand.nextInt(cardShuffleSoundEffects.length - 1);
            long duration = 0;

            try {
                Clip cardShuffle = AudioSystem.getClip();
                cardShuffle.open(AudioLoader.loadAudioFile(cardShuffleSoundEffectFilePath + cardShuffleSoundEffects[index]));
                duration = cardShuffle.getMicrosecondLength();

            } catch (Exception e) {


            }

            playCardShuffleSoundEffect(index);
            long startTime = System.currentTimeMillis();
            long endTime = startTime + (duration / 1000);

            if (duration > 0) {
                while (System.currentTimeMillis() < endTime) {}

                playCardShuffleSoundEffect(2);

            }

        }

    }

    public static void playRandomCardMoveSoundEffect() {

        if (playWithSound) {
            int index = rand.nextInt(cardMoveSoundEffects.length);
            playCardMoveSoundEffect(index);

        }

    }

    public static void playCardMoveSoundEffect(int index) {

        AudioLoader.playAudioFile(AudioLoader.loadAudioFile(cardMoveSoundEffectFilePath + cardMoveSoundEffects[index]));

    }

    public static void playCardShuffleSoundEffect(int index) {

        AudioLoader.playAudioFile(AudioLoader.loadAudioFile(cardShuffleSoundEffectFilePath + cardShuffleSoundEffects[index]));

    }

    public static void setPlayWithSound(boolean value) {

        playWithSound = value;

    }

}
