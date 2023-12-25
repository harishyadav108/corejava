package threads;

public class DeadLockUsingJava8 {



    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(()->{
            synchronized (lock1){
                System.out.println("in first thread acquired lock1"+Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("in first thread acquired lock2 by"+Thread.currentThread());

                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (lock2){
                System.out.println("in second thread acquired lock1"+Thread.currentThread());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("in second thread acquired lock1 by"+Thread.currentThread());

                }
            }
        });

        t1.start();
        t2.start();
    }
}
