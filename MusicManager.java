import java.io.*;
import java.util.*;

public class MusicManager {
    private static Scanner scanner = new Scanner(System.in);
    private static List<MusicCollection> collections = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Лабораторная работа 4. Хархавкина Мария 6301");
        System.out.println("Music Collection");

        boolean x = true;
        while (x) {
            System.out.println("1 - Работа с коллекциями ");
            System.out.println("2 - Байтовые потоки №1");
            System.out.println("3 - Символьные потоки №1");
            System.out.println("4 - Сериализация  №2");
            System.out.println("5 - Форматный ввод/вывод  №3");
            System.out.println("0 - Выход");
            int choice = getInt("Ваш выбор: ");
            switch (choice) {
                case 1:
                    lR3();
                    break;
                case 2:
                    workWithByteStreams();
                    break;
                case 3:
                    workWithCharStreams();
                    break;
                case 4:
                    workWithSerialization();
                    break;
                case 5:
                    workWithFormattedIO();
                    break;
                case 0:
                    System.out.println("Выход");
                    x = false;
                    break;
                default:
                    System.out.println("Неправильный ввод");
            }
        }
    }

    private static void lR3() {
        boolean x1 = true;
        while (x1) {
            System.out.println("1 - Добавить коллекцию");
            System.out.println("2 - Показать все коллекции");
            System.out.println("3 - Группировать по результату");
            System.out.println("4 - Разделить по типам");
            System.out.println("0 - Назад");
            int choice = getInt("Ваш выбор: ");
            switch (choice) {
                case 1:
                    addCollection();
                    break;
                case 2:
                    showCollections();
                    break;
                case 3:
                    groupByResult();
                    break;
                case 4:
                    splitByType();
                    break;
                case 0:
                    x1 = false;
                    break;
                default:
                    System.out.println("Неправильный ввод");
            }
        }
    }

    private static void addCollection() {
        System.out.println("1 - Альбом");
        System.out.println("2 - Плейлист");
        int type = getInt("Выберите тип: ");

        try {
            String title = getString("Введите название: ");
            int special = getInt("Введите " + (type == 1 ? "длительность вступления: " : "порог пропуска: "));
            int n = getInt("Сколько треков: ");
            int[] durations = new int[n];
            for (int i = 0; i < n; i++) {
                durations[i] = getInt("Длительность трека " + (i+1) + ": ");
            }

            MusicCollection mc;
            if (type == 1) {
                mc = new Album(durations, title, special);
            } else {
                mc = new Playlist(durations, title, special);
            }

            collections.add(mc);
            System.out.println("Добавлено успешно!");
        } catch (MusicValidationException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showCollections() {
        if (collections.size() == 0) {
            System.out.println("Коллекций нет.");
            return;
        }
        for (int i = 0; i < collections.size(); i++) {
            MusicCollection mc = collections.get(i);
            System.out.println(mc);
            try {
                System.out.println("Эффективное время прослушивания: " + mc.calculateEffectiveListeningTime());
            } catch (MusicBusinessException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static void groupByResult() {
        Map<Integer, List<MusicCollection>> groups = new HashMap<Integer, List<MusicCollection>>();

        for (int i = 0; i < collections.size(); i++) {
            MusicCollection mc = collections.get(i);
            try {
                int res = mc.calculateEffectiveListeningTime();
                if (!groups.containsKey(res)) {
                    groups.put(res, new ArrayList<MusicCollection>());
                }
                groups.get(res).add(mc);
            } catch (MusicBusinessException e) {
                System.out.println("Пропуск: " + mc.getTitle());
            }
        }

        for (Integer key : groups.keySet()) {
            System.out.println("Результат " + key + ":");
            List<MusicCollection> list = groups.get(key);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("  - " + list.get(i).getTitle());
            }
        }
    }

    private static void splitByType() {
        List<Album> albums = new ArrayList<Album>();
        List<Playlist> playlists = new ArrayList<Playlist>();

        for (int i = 0; i < collections.size(); i++) {
            MusicCollection mc = collections.get(i);
            if (mc instanceof Album) {
                albums.add((Album) mc);
            } else if (mc instanceof Playlist) {
                playlists.add((Playlist) mc);
            }
        }

        System.out.println("Альбомов: " + albums.size());
        for (int i = 0; i < albums.size(); i++) {
            System.out.println("  - " + albums.get(i).getTitle());
        }

        System.out.println("Плейлистов: " + playlists.size());
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println("  - " + playlists.get(i).getTitle());
        }
    }


    private static void workWithByteStreams() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\nБайтовые потоки");
            System.out.println("1 - Запись в байтовый поток");
            System.out.println("2 - Чтение из байтового потока");
            System.out.println("0 - Назад");

            int choice = getInt("Ваш выбор: ");
            switch (choice) {
                case 1:
                    byteStreamOutput();
                    break;
                case 2:
                    byteStreamInput();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void byteStreamOutput() {
        if (collections.isEmpty()) {
            System.out.println("Нет коллекций для записи!");
            return;
        }

        String filename = "music_collections.dat";
        try {
            System.out.println("Запись " + collections.size() + " коллекций в байтовый поток...");
            FileOutputStream fos = new FileOutputStream(filename);
            for (MusicCollection mc : collections) {
                MIO.outputMusicCollection(mc, fos);
            }
            fos.close();
            System.out.println("Запись завершена! Файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }

    private static void byteStreamInput() {
        String filename = "music_collections.dat";
        try {
            System.out.println("Чтение коллекций из байтового потока...");
            FileInputStream fis = new FileInputStream(filename);
            List<MusicCollection> readCollections = new ArrayList<>();

            MusicCollection mc;
            while ((mc = MIO.inputMusicCollection(fis)) != null) {
                readCollections.add(mc);
            }
            fis.close();

            System.out.println("Прочитано " + readCollections.size() + " коллекций:");
            for (MusicCollection collection : readCollections) {
                System.out.println("  - " + collection);
                collections.add(collection); // Добавляем в основную коллекцию
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }

    private static void workWithCharStreams() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\nСимвольные потоки");
            System.out.println("1 - Запись в символьный поток");
            System.out.println("2 - Чтение из символьного потока");
            System.out.println("0 - Назад");

            int choice = getInt("Ваш выбор: ");
            switch (choice) {
                case 1:
                    charStreamOutput();
                    break;
                case 2:
                    charStreamInput();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void charStreamOutput() {
        if (collections.isEmpty()) {
            System.out.println("Нет коллекций для записи!");
            return;
        }

        String filename = "music_collections.txt";
        try {
            System.out.println("Запись " + collections.size() + " коллекций в символьный поток...");
            FileWriter fw = new FileWriter(filename);
            for (MusicCollection mc : collections) {
                MIO.writeMusicCollection(mc, fw);
            }
            fw.close();
            System.out.println("Запись завершена! Файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void charStreamInput() {
        String filename = "music_collections.txt";
        try {
            System.out.println("Чтение коллекций из символьного потока");
            FileReader fr = new FileReader(filename);

            collections.clear();

            MusicCollection mc;
            while ((mc = MIO.readMusicCollection(fr)) != null) {
                collections.add(mc);
            }
            fr.close();

            System.out.println("Прочитано " + collections.size() + " коллекций:");
            for (MusicCollection collection : collections) {
                System.out.println("  - " + collection);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }

    private static void workWithSerialization() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\nСериализация");
            System.out.println("1 - Сериализация");
            System.out.println("2 - Десериализация");
            System.out.println("0 - Назад");

            int choice = getInt("Ваш выбор: ");
            switch (choice) {
                case 1:
                    serializationOutput();
                    break;
                case 2:
                    serializationInput();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void serializationOutput() {
        if (collections.isEmpty()) {
            System.out.println("Нет коллекций для сериализации!");
            return;
        }

        String filename = "music_collections.ser";
        try {
            System.out.println("Сериализация " + collections.size() + " коллекций");
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (MusicCollection mc : collections) {
                MIO.serializeMusicCollection(mc, oos);
            }
            oos.close();
            System.out.println("Сериализация завершена! Файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка сериализации: " + e.getMessage());
        }
    }

    private static void serializationInput() {
        String filename = "music_collections.ser";
        try {
            System.out.println("Десериализация коллекций");
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<MusicCollection> deserializedCollections = new ArrayList<>();

            try {
                while (true) {
                    MusicCollection mc = MIO.deserializeMusicCollection(ois);
                    deserializedCollections.add(mc);
                }
            } catch (EOFException e) {
                // конец
            }
            ois.close();

            System.out.println("Десериализовано " + deserializedCollections.size() + " коллекций:");
            for (MusicCollection mc : deserializedCollections) {
                System.out.println("  - " + mc);
                collections.add(mc); // Добавляем в основную коллекцию
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка десериализации: " + e.getMessage());
        }
    }

    private static void workWithFormattedIO() {
        System.out.println("\nФорматный ввод/вывод");

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("1 - Форматный вывод в файл");
            System.out.println("2 - Форматный ввод с консоли");
            System.out.println("0 - Назад");

            int choice = getInt("Ваш выбор: ");
            switch (choice) {
                case 1:
                    formattedOutputToFile();
                    break;
                case 2:
                    formattedInputFromConsole();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void formattedOutputToFile() {
        if (collections.isEmpty()) {
            System.out.println("Нет коллекций для вывода!");
            return;
        }

        String filename = "music_collections_formatted.txt";

        try {
            System.out.println("Форматный вывод в файл");
            FileWriter fw = new FileWriter(filename);
            for (MusicCollection mc : collections) {
                MIO.writeFormatMusicCollection(mc, fw);
            }
            fw.close();
            System.out.println("Вывод завершен! Файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void formattedInputFromConsole() {
        System.out.println("Форматный ввод с консоли:");
        try {
            MusicCollection mc = MIO.readFormatMusicCollection(scanner);
            collections.add(mc);
            System.out.println("Коллекция добавлена: " + mc);
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        } catch (MusicValidationException e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
        }
    }

    private static int getInt(String text) {
        while (true) {
            try {
                System.out.print(text);
                int x = scanner.nextInt();
                scanner.nextLine();
                return x;
            } catch (InputMismatchException e) {
                System.out.println("Введите число!");
                scanner.nextLine();
            }
        }
    }

    private static String getString(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }
}