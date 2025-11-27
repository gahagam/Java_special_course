public class ThreadReadRun implements Runnable{

    private MusicCollection content;
    private Semaphore reader;
    private Semaphore writer;

    public ThreadReadRun(MusicCollection content, Semaphore reader, Semaphore writer){
        this.content = content;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        try{
            int value = 0;
            for(int i = 0; i < content.length(); i++){
                // разрешение на чтение
                reader.acquire();

                value = content.getTrackElement(i);
                System.out.println("Read: "+ value + " from position " + i);

                // запись
                writer.release();
            }
            System.out.println("\nЧтение окончено.");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}