import java.io.*;
import java.util.Scanner;

class MIO {

    //запись в байтовый поток
    public static void outputMusicCollection(MusicCollection o, OutputStream out) throws IOException {
        o.output(out);
    }

    //чтение из байтового потока
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
        return new Album(durations, title, specialValue);
    }

    //запись в символьный поток
    public static void writeMusicCollection(MusicCollection o, Writer out) throws IOException {
        o.write(out);
    }

    //чтение из символьного потока
    public static MusicCollection readMusicCollection(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);

        // Настраиваем tokenizer для правильной работы
        tokenizer.resetSyntax();
        tokenizer.wordChars('a', 'z');
        tokenizer.wordChars('A', 'Z');
        tokenizer.wordChars('0', '9');
        tokenizer.wordChars('_', '_');
        tokenizer.wordChars(128 + 32, 255);
        tokenizer.whitespaceChars(' ', ' ');
        tokenizer.whitespaceChars('\t', '\t');
        tokenizer.whitespaceChars('\n', '\n');
        tokenizer.whitespaceChars('\r', '\r');

        // Чтение названия
        StringBuilder titleBuilder = new StringBuilder();
        while (tokenizer.nextToken() == StreamTokenizer.TT_WORD) {
            if (titleBuilder.length() > 0) {
                titleBuilder.append(" ");
            }
            titleBuilder.append(tokenizer.sval);
        }

        if (tokenizer.ttype != StreamTokenizer.TT_NUMBER) {
            throw new IOException("Ожидалось число для specialValue после названия");
        }
        String title = titleBuilder.toString().replace("_", " ");
        int specialValue = (int) tokenizer.nval;

        // чтение количества треков
        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            throw new IOException("Ожидалось число для количества треков");
        }
        int trackCount = (int) tokenizer.nval;

        // чтение длительностей треков
        int[] durations = new int[trackCount];
        for (int i = 0; i < trackCount; i++) {
            if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
                throw new IOException("Ожидалось число для длительности трека " + (i + 1));
            }
            durations[i] = (int) tokenizer.nval;
        }

        return new Album(durations, title, specialValue);
    }

    //вывод сериализованных объектов
    public static void serializeMusicCollection(MusicCollection o, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(o);
        oos.flush();
    }

    //ввод десериализованного объекта
    public static MusicCollection deserializeMusicCollection(InputStream in) throws ClassNotFoundException, IOException {
        ObjectInputStream doc = new ObjectInputStream(in);
        return (MusicCollection) doc.readObject();
    }

    //вывод объектов
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
        return new Album(durations, title, specialValue);
    }
}