import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

public interface MusicCollection extends Serializable  {

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

    int length();
}
