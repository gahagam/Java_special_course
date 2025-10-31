import java.io.*;
import java.util.Arrays;

public class Playlist implements MusicCollection, Serializable {
    private static final long serialVersionUID = 1L;

    private int[] trackDurations;
    private String title;
    private int skipThreshold; //пропуск

    public Playlist() {
        this.trackDurations = new int[1];
        this.title = "Без названия";
        this.skipThreshold = 0;
    }

    public Playlist(int[] trackDurations, String title, int skipThreshold) {
        if (trackDurations == null || trackDurations.length == 0) {
            throw new MusicValidationException("Массив длительностей треков не может быть пустым или null");
        }

        for (int duration : trackDurations) {
            if (duration <= 0) {
                throw new MusicValidationException("Длительность каждого трека должна быть положительной: " + duration);
            }
        }

        if (title == null || title.trim().isEmpty()) {
            throw new MusicValidationException("Название плейлиста не может быть пустым");
        }

        if (skipThreshold < 0) {
            throw new MusicValidationException("Порог пропуска трека не может быть отрицательным");
        }

        this.trackDurations = trackDurations.clone();
        this.title = title;
        this.skipThreshold = skipThreshold;
    }

    @Override
    public int[] getTrackDurations() {
        return trackDurations;
    }

    @Override
    public void setTrackDurations(int[] durations) {
        if (durations == null || durations.length == 0) {
            throw new MusicValidationException("Плейлист не может быть пустым");
        }
        this.trackDurations = durations.clone();
    }

    @Override
    public int getTrackElement(int index) {
        if (index < 0 || index >= trackDurations.length) {
            throw new MusicValidationException("Неверный индекс трека");
        }
        return trackDurations[index];
    }

    @Override
    public void setTrackElement(int index, int value) {
        if (index < 0 || index >= trackDurations.length) {
            throw new MusicValidationException("Неверный индекс трека");
        }
        if (value <= 0) {
            throw new MusicValidationException("Длительность должна быть положительной");
        }
        trackDurations[index] = value;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new MusicValidationException("Название не может быть пустым");
        }
        this.title = title;
    }

    @Override
    public int getSpecialValue() {
        return skipThreshold;
    }

    @Override
    public void setSpecialValue(int value) {
        if (value < 0) {
            throw new MusicValidationException("Порог пропуска не может быть отрицательным");
        }
        this.skipThreshold = value;
    }

    @Override
    public int calculateEffectiveListeningTime() throws MusicBusinessException {
        int total = 0;
        for (int i = 0; i < trackDurations.length; i++) {
            if (trackDurations[i] >= skipThreshold) {
                total += trackDurations[i];
            }
        }
        if (total == 0) {
            throw new MusicBusinessException("Все треки были пропущены");
        }
        return total;
    }

    @Override
    public String toString() {
        return "Плейлист '" + title + "', треков: " + trackDurations.length + ", порог пропуска: " + skipThreshold + ", длительности: " + Arrays.toString(trackDurations);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Playlist other = (Playlist) obj;
        return skipThreshold == other.skipThreshold &&
                title.equals(other.title) &&
                Arrays.equals(trackDurations, other.trackDurations);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + skipThreshold;
        result = 31 * result + Arrays.hashCode(trackDurations);
        return result;
    }


    @Override
    public void output(OutputStream out) throws IOException {
        DataOutputStream text = new DataOutputStream(out);
        text.writeUTF(title);
        text.writeInt(skipThreshold);
        text.writeInt(trackDurations.length);
        for (int v : trackDurations) text.writeInt(v);
        text.flush();
    }

    @Override
    public void write(Writer out) throws IOException{
        PrintWriter pw = new PrintWriter(out);
        // Заменяем пробелы в названии на подчеркивания
        String escapedTitle = title.replace(" ", "_");
        pw.print(escapedTitle + " " + skipThreshold + " " + trackDurations.length);
        for (int v  : trackDurations) pw.print(" " + v);
        pw.println();
        pw.flush();
    }
}



