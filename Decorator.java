import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;

public class Decorator implements MusicCollection {
    private final MusicCollection music;

    public Decorator(MusicCollection music) {
        this.music = music;
    }

    @Override
    public String getTitle() {
        return music.getTitle();
    }

    @Override
    public int getSpecialValue() {
        return music.getSpecialValue();
    }

    @Override
    public int getTrackElement(int index) {
        return music.getTrackElement(index);
    }

    @Override
    public int[] getTrackDurations() {
        int[] original = music.getTrackDurations();
        return original != null ? Arrays.copyOf(original, original.length) : null;
    }

    @Override
    public void output(OutputStream out) throws IOException {
        music.output(out);
    }

    @Override
    public void write(Writer out) throws IOException {
        music.write(out);
    }

    @Override
    public int length() {
        return music.length();
    }

    @Override
    public Iterator<Integer> iterator() {
        return music.iterator();
    }

    @Override
    public int compareTo(MusicCollection o) {
        return music.compareTo(o);
    }

    @Override
    public int calculateEffectiveListeningTime() throws MusicBusinessException {
        return music.calculateEffectiveListeningTime();
    }

    /**@Override
    public List<Integer> getElements() {
        return music.getElements();
    }*/

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }

    @Override
    public void setTrackDurations(int[] durations) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }

    @Override
    public void setTrackElement(int index, int value) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }

    @Override
    public void setSpecialValue(int value) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }
}
