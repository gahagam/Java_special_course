import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WrapperContent implements MusicCollection {
    private final MusicCollection wrappedContent;

    public WrapperContent(MusicCollection content) {
        this.wrappedContent = content;
    }

    @Override
    public synchronized int[] getTrackDurations() {
        int[] original = wrappedContent.getTrackDurations();
        if (original == null) {
            return new int[0];
        } else {
            return original.clone();
        }
    }

    @Override
    public synchronized void setTrackDurations(int[] durations) {
        int[] copy;
        if (durations == null) {
            copy = new int[0];
        } else {
            copy = durations.clone();
        }
        wrappedContent.setTrackDurations(copy);
    }

    @Override
    public synchronized int getTrackElement(int index) {
        return wrappedContent.getTrackElement(index);
    }

    @Override
    public synchronized void setTrackElement(int index, int value) {
        wrappedContent.setTrackElement(index, value);
    }

    @Override
    public synchronized String getTitle() {
        return wrappedContent.getTitle();
    }

    @Override
    public synchronized void setTitle(String title) {
        wrappedContent.setTitle(title);
    }

    @Override
    public synchronized int getSpecialValue() {
        return wrappedContent.getSpecialValue();
    }

    @Override
    public synchronized void setSpecialValue(int value) {
        wrappedContent.setSpecialValue(value);
    }

    @Override
    public synchronized int calculateEffectiveListeningTime() throws MusicBusinessException {
        return wrappedContent.calculateEffectiveListeningTime();
    }

    @Override
    public synchronized void output(OutputStream out) throws IOException {
        wrappedContent.output(out);
    }

    @Override
    public synchronized void write(Writer out) throws IOException {
        wrappedContent.write(out);
    }

    @Override
    public synchronized int length() {
        return wrappedContent.length();
    }

    @Override
    public synchronized int hashCode() {
        return wrappedContent.hashCode();
    }

    @Override
    public synchronized boolean equals(Object obj) {
        return wrappedContent.equals(obj);
    }

    @Override
    public synchronized String toString() {
        return wrappedContent.toString();
    }

    public synchronized MusicCollection getWrappedContent() {
        return wrappedContent;
    }

    //6 лр 1
    @Override
    public int compareTo(MusicCollection v2) {
        if(this.calculateEffectiveListeningTime()>v2.calculateEffectiveListeningTime()){
            return 1;
        }
        else if(this.calculateEffectiveListeningTime()==v2.calculateEffectiveListeningTime()){
            return 0;
        }
        else{return -1;}
    }

    //6 лр 3
    public List<Integer> getElements() {
        List<Integer> list = new ArrayList<>();
        for (int i : wrappedContent.getTrackDurations()) {
            list.add(i);
        }
        return list;
    }

    @Override
    public Iterator<Integer> iterator() {
        return getElements().iterator(); // базовая реализация на основе списка
    }
}