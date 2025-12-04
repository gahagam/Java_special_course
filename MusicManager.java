import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class MusicManager {
    private static Scanner scanner = new Scanner(System.in);
    private static List<MusicCollection> collections = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Лабораторная 5. Хархавкина Мария 6301");
        System.out.println("Music Collection");

        boolean x = true;
        while (x) {
            System.out.println("Лабораторная 3");
            System.out.println("1 - Работа с коллекциями ");
            System.out.println("Лабораторная 4");
            System.out.println("2 - Байтовые потоки №1");
            System.out.println("3 - Символьные потоки №1");
            System.out.println("4 - Сериализация  №2");
            System.out.println("5 - Форматный ввод/вывод  №3");
            System.out.println("Лабораторная 5");
            System.out.println("6 - Classes ThreadWrite, ThreadRead");
            System.out.println("7 - Runnable");
            System.out.println("8 - Class WrapperContent");
            System.out.println("Лабораторная 6");
            System.out.println("9 - Comparable");
            System.out.println("10 - Comparator");
            System.out.println("11 - Iterator");
            System.out.println("12 - Decorator");
            System.out.println("13 - Factory");
            System.out.println("0 - Выход");
            int choice = getInt("Ваш выбор: ");
            switch (choice) {
                case 1:
                    Lr3();
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
                case 6:
                    classesThread();
                    break;
                case 7:
                    classesRunnable();
                    break;
                case 8:
                    classesWrapper();
                    break;
                case 9:
                    Comparable();
                    break;
                case 10:
                    Comparator();
                    break;
                case 11:
                    Iterator();
                    break;
                case 12:
                    Decorator();
                    break;
                case 13:
                    Factory();
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

    private static void Comparable(){
        int[] tracks1 = new int[]{180, 200, 150};
        int[] tracks2 = new int[]{120, 300, 210, 90};
        int[] tracks3 = new int[]{60, 70, 80, 90};
        int[] tracks4 = new int[]{200, 100, 150};
        int[] tracks5 = new int[]{100, 110, 90, 120, 80};
        int[] tracks6 = new int[]{50, 60, 70};

        MusicCollection album1 = new Album(tracks1, "Album1", 30);
        MusicCollection album2 = new Album(tracks2, "Album2", 50);
        MusicCollection album3 = new Album(tracks3, "Album3", 10);
        MusicCollection album4 = new Album(tracks4, "Album4", 20);
        MusicCollection album5 = new Album(tracks5, "Album5", 25);
        MusicCollection album6 = new Album(tracks6, "Album6", 5);

        MusicCollection[] albums = new MusicCollection[]{album1, album2, album3, album4, album5, album6};

        System.out.println("\nИсходный массив Album:");
        for (MusicCollection album : albums) {
            System.out.println(album.getTitle() + ": " + Arrays.toString(album.getTrackDurations()) + ", Эфф. длительность: " + album.calculateEffectiveListeningTime() + " сек");
        }

        Arrays.sort(albums);

        System.out.println("\nСортированный массив Album:");
        for (MusicCollection album : albums) {
            System.out.println(album.getTitle() + ": " + Arrays.toString(album.getTrackDurations()) + ", Эфф. длительность: " + album.calculateEffectiveListeningTime() + " сек");
        }
    }

    private static void Comparator() {
        int[] array1 = new int[]{180, 200, 150};
        MusicCollection c1 = new Album(array1, "Album One", 5);

        int[] array2 = new int[]{210, 120, 130, 140};
        MusicCollection c2 = new Album(array2, "Album Two", 4);
        MusicCollection c3 = new Album(array2, "Album Three", 3);

        int[] array4 = new int[]{100, 90, 80, 70, 60};
        MusicCollection c4 = new Album(array4, "Album Four", 2);

        int[] array5 = new int[]{200, 180, 160, 140, 120};
        MusicCollection c5 = new Album(array5, "Album Five", 1);

        int[] array6 = new int[]{300, 300, 300};
        MusicCollection c6 = new Album(array6, "Album Six", 5);

        MusicCollection[] arrMusic = new MusicCollection[]{c1, c2, c3, c4, c5, c6};

        System.out.println("\nИсходный массив типа MusicCollection:");
        for (MusicCollection mc : arrMusic) {
            try {
                System.out.println(mc.getTitle() + ": вступление: " + mc.getSpecialValue() + ", " + Arrays.toString(mc.getTrackDurations()) + " Результат бизнес-метода: " + mc.calculateEffectiveListeningTime());
            } catch (MusicBusinessException e) {
                System.out.println(mc.getTitle() + ": ошибка расчета бизнес-метода");
            }
        }

        System.out.println("\nМассив, сортированный по убыванию результата бизнес-метода:");
        MIO.sortContent(arrMusic, new DecreaseBusiness());
        for (MusicCollection mc : arrMusic) {
            try {
                System.out.println(mc.getTitle() + ": вступление: " + mc.getSpecialValue() + ", " + Arrays.toString(mc.getTrackDurations()) + " Результат бизнес-метода: " + mc.calculateEffectiveListeningTime());
            } catch (MusicBusinessException e) {
                System.out.println(mc.getTitle() + ": ошибка расчета бизнес-метода");
            }
        }

        System.out.println("\nМассив, сортированный по увеличению поля \"Вступление\":");
        MIO.sortContent(arrMusic, new IncreaseField());
        for (MusicCollection mc : arrMusic) {
            try {
                System.out.println(mc.getTitle() + ": вступление: " + mc.getSpecialValue() + ", " + Arrays.toString(mc.getTrackDurations()) + " Результат бизнес-метода: " + mc.calculateEffectiveListeningTime());
            } catch (MusicBusinessException e) {
                System.out.println(mc.getTitle() + ": ошибка расчета бизнес-метода");
            }
        }
    }

    public static void Iterator() {
        int[] array1 = new int[]{180, 200, 150};
        MusicCollection c = new Album(array1, "Album1", 10);

        System.out.println("\nFor-each:");
        for (int duration : c) {
            System.out.println(duration);
        }

        System.out.println("\nWhile:");
        Iterator<Integer> i = c.iterator();
        while (i.hasNext()) {
            Integer duration = i.next();
            System.out.println(duration);
        }
    }

    /**
    public static void Decorator() {
        MusicCollection originalAlbum = new Album(new int[]{180, 200, 150}, "Original Album", 5);
        MusicCollection originalPlaylist = new Playlist(new int[]{120, 130, 140}, "Original Playlist", 3);;

        MusicCollection r1 = MIO.unmodifiable(originalAlbum);
        MusicCollection r2 = MIO.unmodifiable(originalPlaylist);

        System.out.println("\n" + r1.getTitle());
        System.out.println(r2.getTitle());

        try {
            // замена setRating на setSpecialValue
            r1.setSpecialValue(3); // должно выбросить UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Внимание! \n" + e);
        }
    }*/


    private static void Decorator() {
        MusicCollection originalAlbum = new Album(new int[]{180, 200, 150}, "Original Album", 5);
        MusicCollection originalPlaylist = new Playlist(new int[]{120, 130, 140}, "Original Playlist", 3);

        System.out.println("Оригинал Album: " + originalAlbum);
        System.out.println("Оригинал Playlist: " + originalPlaylist);

        MusicCollection unmodifiableAlbum = MIO.unmodifiable(originalAlbum);
        MusicCollection unmodifiablePlaylist = MIO.unmodifiable(originalPlaylist);

        System.out.println("\nUnmodifiable Album: " + unmodifiableAlbum);
        System.out.println("Unmodifiable Playlist: " + unmodifiablePlaylist);

        System.out.println("\nПопытка изменить title:");
        try {
            unmodifiableAlbum.setTitle("New Album Title");
            System.out.println("Изменение прошло успешно");
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException - правильно!");
        }

        System.out.println("\nПопытка изменить specialValue:");
        try {
            unmodifiableAlbum.setSpecialValue(10);
            System.out.println("Изменение прошло успешно");
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException - правильно!");
        }

        System.out.println("\nПопытка изменить треки:");
        try {
            unmodifiableAlbum.setTrackDurations(new int[]{1, 2, 3});
            System.out.println("Изменение прошло успешно");
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException - правильно!");
        }

        System.out.println("\nПопытка изменить отдельный трек:");
        try {
            unmodifiableAlbum.setTrackElement(0, 999);
            System.out.println("Изменение прошло успешно");
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException - правильно!");
        }

        System.out.println("\nЧтение данных работает:");
        System.out.println("Title: " + unmodifiableAlbum.getTitle());
        System.out.println("SpecialValue (вступление): " + unmodifiableAlbum.getSpecialValue());
        System.out.println("Track 0: " + unmodifiableAlbum.getTrackElement(0));
    }

    public static void Factory() {
        MusicCollection v1 = MIO.createInstance();
        MusicCollection v2 = MIO.createInstance("Тест Album", new int[]{100, 200, 150}, 5);

        System.out.println("Объект по умолчанию: " + v1.getTitle());
        System.out.println("Создан с параметрами: " + v2.getTitle());

        MIO.setMusicFactory(new FactoryPlaylist());

        MusicCollection playlist = MIO.createInstance("My Playlist", new int[]{120, 130, 140}, 3);
        System.out.println("Создан через PlaylistFactory: " + playlist.getTitle() + " (" + playlist.getClass().getSimpleName() + ")");
    }




    private static void classesThread(){
        int[] array = new int[100];
        MusicCollection content = new Album(array, "Нити", 5);

        // Создаем и запускаем потоки
        ThreadWrite writer = new ThreadWrite(content);
        ThreadRead reader = new ThreadRead(content);

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nОбе нити завершили работу");
    }

    private static void classesRunnable(){
        int[] array = new int[100];
        MusicCollection content = new Playlist(array, "Runnable", 5);

        Semaphore writer = new Semaphore(1); // разрешено
        Semaphore reader = new Semaphore(0); // запрещено

        // Runnable
        ThreadWriteRun writeRun = new ThreadWriteRun(content, writer, reader);
        ThreadReadRun readRun = new ThreadReadRun(content, reader, writer);

        // Потоки
        Thread writeTh = new Thread(writeRun);
        Thread readTh = new Thread(readRun);

        writeTh.start();
        readTh.start();

        try {
            writeTh.join();
            readTh.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nОбе нити завершили работу");
    }

    private static void classesWrapper() {
        MusicCollection originalContent = new Album(new int[]{1, 2, 3}, "Test", 5);
        MusicCollection syncContent = new WrapperContent(originalContent);

        System.out.println(" ");
        Thread t1 = new Thread(() -> testMusicCollection(syncContent, "T1"));
        Thread t2 = new Thread(() -> testMusicCollection(syncContent, "T2"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Тест завершен");
    }

    private static void testMusicCollection(MusicCollection content, String threadName) {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(threadName + " getTitle: " + content.getTitle());
                content.setTitle("Title " + i);
                System.out.println(threadName + " getSpecialValue: " + content.getSpecialValue());
                content.setSpecialValue(i);
                int[] arr = content.getTrackDurations();
                content.setTrackDurations(new int[]{i, i + 1, i + 2});
                System.out.println(threadName + " getTrackElement("+i+"): " + content.getTrackElement(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void Lr3() {
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
            System.out.println("Чтение коллекций из символьного потока...");
            FileReader fr = new FileReader(filename);
            List<MusicCollection> readCollections = new ArrayList<>();

            MusicCollection mc;
            while ((mc = MIO.readMusicCollection(fr)) != null) {
                readCollections.add(mc);
            }
            fr.close();

            System.out.println("Прочитано " + readCollections.size() + " коллекций:");
            for (MusicCollection collection : readCollections) {
                System.out.println("  - " + collection);
                collections.add(collection); // Добавляем в основную коллекцию
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
            System.out.println("Сериализация " + collections.size() + " коллекций...");
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
                // Конец файла - нормальная ситуация
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
            System.out.println("Форматный вывод в файл...");
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