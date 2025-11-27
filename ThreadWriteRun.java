public class ThreadWriteRun implements Runnable{

    private MusicCollection content;
    private Semaphore writer;
    private Semaphore reader;

    public ThreadWriteRun(MusicCollection content, Semaphore writer, Semaphore reader){
        super();
        this.content = content;
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            for (int i = 0; i < content.length(); i++) {
                // разрешение на запись
                writer.acquire();

                value = 1 + (int)(Math.random() * 1000);
                content.setTrackElement(i, value);
                System.out.println("Write: " + value + " to position " + i);

                // чтение
                reader.release();
            }
            System.out.println("\nЗапись окончена.");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}