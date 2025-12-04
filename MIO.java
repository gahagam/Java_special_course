import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MIO {

    private static MusicFactory factory = new FactoryAlbum();

    public static void setMusicFactory(MusicFactory v) {
        if (v == null) {
            throw new IllegalArgumentException("Ошибка! Нулевое значение!");
        }
        factory = v;
    }

    public static MusicCollection createInstance() {
        return factory.createInstance();
    }

    public static MusicCollection createInstance(String title, int[] array, int introDuration) {
        return factory.createInstance(title, array, introDuration);
    }

    //6 лр 1
    public static <T extends Comparable<T>> void sortContent(T[] array) {
        Arrays.sort(array);
    }

    //6 лр 2
    public static <E extends MusicCollection> void sortContent(E[] array, Comparator<? super E> comp) {
        if (array == null || array.length < 2) {
            return;
        }
        Arrays.sort(array, comp);
    }

    //6 лр 4
    public static MusicCollection unmodifiable(MusicCollection c) {
        if (c == null) {
            throw new IllegalArgumentException("Ошибка. Null значение.");
        }
        return new Decorator(c);
    }

    public static MusicCollection synchronizedContent(MusicCollection i) {
        return new WrapperContent(i);
    }

    // Запись в байтовый поток
    public static void outputMusicCollection(MusicCollection o, OutputStream out) throws IOException {
        o.output(out);
    }

    // Чтение из байтового потока
    public static MusicCollection inputMusicCollection(InputStream in) throws IOException {
        if (in.available() <= 0) {
            return null;
        }
        DataInputStream doc = new DataInputStream(in);
        String title = doc.readUTF();
        int specialValue = doc.readInt();
        int trackCount = doc.readInt();
        int[] durations = new int[trackCount];
        for (int i = 0; i < trackCount; i++) {
            durations[i] = doc.readInt();
        }
        //return new Album(durations, title, specialValue);
        return createInstance(title, durations, specialValue);
    }

    // Запись в символьный поток
    public static void writeMusicCollection(MusicCollection o, Writer out) throws IOException {
        o.write(out);
    }

    // Чтение из символьного потока
    public static MusicCollection readMusicCollection(Reader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = in.read()) != -1) {
            sb.append((char) ch);
        }
        String line = sb.toString().trim();

        if (line.isEmpty()) {
            return null;
        }

        String[] tokens = line.split("\\s+");
        if (tokens.length < 3) {
            throw new IOException("Ошибка формата: недостаточно данных в строке: " + line);
        }

        String title = tokens[0].replace("_", " ");
        int specialValue = Integer.parseInt(tokens[1]);
        int trackCount = Integer.parseInt(tokens[2]);

        if (tokens.length < 3 + trackCount) {
            throw new IOException("Недостаточно данных для длительностей треков. Ожидалось: " + trackCount +
                    ", найдено: " + (tokens.length - 3));
        }

        int[] durations = new int[trackCount];
        for (int i = 0; i < trackCount; i++) {
            durations[i] = Integer.parseInt(tokens[3 + i]);
        }
        //return new Album(durations, title, specialValue);
        return createInstance(title, durations, specialValue);
    }

    // Вывод сериализованных объектов
    public static void serializeMusicCollection(MusicCollection o, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(o);
        oos.flush();
    }

    // Ввод десериализованного объекта
    public static MusicCollection deserializeMusicCollection(InputStream in) throws ClassNotFoundException, IOException {
        ObjectInputStream doc = new ObjectInputStream(in);
        return (MusicCollection) doc.readObject();
    }

    // Вывод объектов
    public static void writeFormatMusicCollection(MusicCollection o, Writer out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.printf("%s;%d;%d", o.getTitle(), o.getSpecialValue(), o.getTrackDurations().length);
        for (int duration : o.getTrackDurations()) {
            pw.printf(";%d", duration);
        }
        pw.println();
        pw.flush();
    }

    public static MusicCollection readFormatMusicCollection(Scanner in) throws IOException {
        String line = in.nextLine();
        String[] parts = line.split(";");
        if (parts.length < 3) {
            throw new IOException("Недостаточно данных в строке: " + line);
        }
        String title = parts[0];
        int specialValue = Integer.parseInt(parts[1]);
        int trackCount = Integer.parseInt(parts[2]);
        int[] durations = new int[trackCount];
        for (int i = 0; i < trackCount && (3 + i) < parts.length; i++) {
            durations[i] = Integer.parseInt(parts[3 + i]);
        }
        //return new Album(durations, title, specialValue);
        return createInstance(title, durations,specialValue);
    }
}
