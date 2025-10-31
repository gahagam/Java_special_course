import java.io.*;

public interface MusicCollection {

    int[] getTrackDurations();

    void setTrackDurations(int[] durations);

    int getTrackElement(int index);

    void setTrackElement(int index, int value);

    String getTitle();

    void setTitle(String title);

    int getSpecialValue();

    void setSpecialValue(int value);

    int calculateEffectiveListeningTime() throws MusicBusinessException;

    void output(OutputStream out) throws IOException;
    void write(Writer out) throws IOException;
}
