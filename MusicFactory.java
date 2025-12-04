public interface MusicFactory {

    MusicCollection createInstance(); // по умолчанию
    MusicCollection createInstance(String title, int[] durations, int specialValue);
}
