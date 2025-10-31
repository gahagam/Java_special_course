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
        // Читаем все содержимое в строку
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = in.read()) != -1) {
            sb.append((char) ch);
        }
        String line = sb.toString().trim();

        if (line.isEmpty()) {
            return null;
        }

        // Разбиваем строку на токены по пробелам
        String[] tokens = line.split("\\s+");
        if (tokens.length < 3) {
            throw new IOException("Ошибка формата: недостаточно данных в строке: " + line);
        }

        // Первый токен - название (заменяем подчеркивания на пробелы)
        String title = tokens[0].replace("_", " ");

        // Второй токен - specialValue
        int specialValue;
        try {
            specialValue = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new IOException("Ошибка формата specialValue: " + tokens[1]);
        }

        // Третий токен - количество треков
        int trackCount;
        try {
            trackCount = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            throw new IOException("Ошибка формата количества треков: " + tokens[2]);
        }

        // Проверяем, что достаточно данных для всех длительностей треков
        if (tokens.length < 3 + trackCount) {
            throw new IOException("Недостаточно данных для длительностей треков. Ожидалось: " + trackCount +
                    ", найдено: " + (tokens.length - 3));
        }

        // Читаем длительности треков
        int[] durations = new int[trackCount];
        for (int i = 0; i < trackCount; i++) {
            try {
                durations[i] = Integer.parseInt(tokens[3 + i]);
            } catch (NumberFormatException e) {
                throw new IOException("Ошибка формата длительности трека " + (i + 1) + ": " + tokens[3 + i]);
            }
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