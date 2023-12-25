package threads;

public class DeadEx {

    Object lock1  = new Object();
    Object lock2  = new Object();

    public void m1() throws InterruptedException {

        synchronized (lock1){
            System.out.println("acured lock1 by "+Thread.currentThread());

            Thread.sleep(1000);

            synchronized (lock2){
                System.out.println("trying to acuire lock2 by "+Thread.currentThread());
            }
        }

    }

    public void m2() throws InterruptedException {

        synchronized (lock2){
            System.out.println("acured lock2 by "+Thread.currentThread());

            Thread.sleep(1000);

            synchronized (lock1){
                System.out.println("trying to acuire lock1 by "+Thread.currentThread());
            }
        }

    }

    public static void main(String[] args) {
        DeadEx obj = new DeadEx();
        Thread t1 = new Thread(()->{
            try {
                obj.m1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                obj.m2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
