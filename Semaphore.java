import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {
    private int permits;
    private Queue<Thread> queue;

    public Semaphore(int permits) {
        this.permits = permits;
        this.queue = new LinkedList<>();
    }

    public synchronized void acquire() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        queue.add(currentThread);

        while (queue.peek() != currentThread || permits <= 0) { // возвр эл, не удаляет
            wait(); // приостановление
        }

        queue.poll(); // удаляет
        permits--;
    }

    public synchronized void release() {
        permits++;
        notifyAll(); // пробуждение
    }

    public synchronized int availablePermits() {
        return permits;
    }
}





/*
public class Semaphore {

    private int permits;
    private int nextTicket = 0;
    private int curTicket = 0;


    public Semaphore(int permits){
        if(permits < 0){
            throw new MusicValidationException("Введено некорректное значение.");
        }
        this.permits = permits;
    }

    public synchronized void getPermission() throws InterruptedException {
        int ticket = nextTicket;
        nextTicket +=1;

        while (permits<=0 || ticket!=curTicket){
            wait();
        }

        permits -=1;
        curTicket +=1;
        notifyAll();
    }

    public synchronized void release(){
        permits+=1;
        notifyAll();
    }

    public int getLengthQueue(){
        return Math.max(0, nextTicket-curTicket);
    }
}
*/