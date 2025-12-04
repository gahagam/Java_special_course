public class FactoryPlaylist implements MusicFactory {

    @Override
    public MusicCollection createInstance() {
        return new Playlist(new int[]{180, 210, 160}, "Playlist", 4);
    }

    @Override
    public MusicCollection createInstance(String title, int[] array, int specialValue) {
        return new Playlist(array, title, specialValue);
    }
}
