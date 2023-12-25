package threads;

public class T1T2T3 {
    int number = 1;
    int max = 10;
    Object lock = new Object();

    public void first() throws InterruptedException {

        while (number < max) {
            synchronized (lock) {
                while (number % 3 != 1) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread() + " is printing=" + number);
                number++;
                lock.notifyAll();

            }
        }
    }

    public void second() throws InterruptedException {

        while (number < max) {
            synchronized (lock) {

                while (number % 3 != 2) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread() + " is printing=" + number);
                number++;
                lock.notifyAll();
            }
        }
    }

    public void third() throws InterruptedException {

        while (number < max) {
            synchronized (lock) {
                while (number % 3 != 0) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread() + " is printing=" + number);
                number++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {

        T1T2T3 obj = new T1T2T3();
        Thread t1 = new Thread(() -> {
            try {
                obj.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"First thread t1" );
        Thread t2 = new Thread(() -> {
            try {
                obj.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Second Thread t2");

        Thread t3 = new Thread(() -> {
            try {
                obj.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Third Thread t3");
        t1.start();
        t2.start();
        t3.start();
    }

}

