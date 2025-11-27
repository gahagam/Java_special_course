public class ThreadWrite extends Thread {

    private MusicCollection content;

    public ThreadWrite(MusicCollection content) {
        this.content = content;
    }

    @Override
    public void run() {
        for (int i = 0; i < content.length(); i++) {
            int value = 1 + (int) (Math.random() * 1000);
            content.setTrackElement(i, value);
            System.out.println(" Write: " + value + " to position " + i);
        }
    }
}