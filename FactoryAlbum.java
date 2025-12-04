public class FactoryAlbum implements MusicFactory {

    @Override
    public MusicCollection createInstance() {
        return new Album(new int[]{180, 200, 150}, "Album", 3);
    }

    @Override
    public MusicCollection createInstance(String title, int[] durations, int specialValue) {
        return new Album( durations, title, specialValue);
    }
}
