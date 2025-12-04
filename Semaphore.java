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




