public class ThreadRead extends Thread {

    private MusicCollection content;

    public ThreadRead(MusicCollection content) {
        this.content = content;
    }

    @Override
    public void run() {
        for (int i = 0; i < content.length(); i++) {
            int value = content.getTrackElement(i);
            System.out.println(" Read: " + value + " from position " + i);
        }
    }
}