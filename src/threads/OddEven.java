package threads;

public class OddEven {
    int number=0;
    int max=10;
    Object lock = new Object();
    public void even() throws InterruptedException {

        while(number<max){
            synchronized (lock){
                System.out.println(Thread.currentThread()+" is printing="+number);
                number++;
                lock.notify();
                lock.wait();
            }
        }
    }

    public void odd() throws InterruptedException {

        while(number<max){
            synchronized (lock){
                System.out.println(Thread.currentThread()+" is printing="+number);
                number++;
                lock.notify();
                lock.wait();
            }
        }
    }

    public static void main(String[] args) {

        OddEven obj = new OddEven();
        Thread t1 = new Thread(()->{
            try {
                obj.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                obj.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("Even Thread");
        t1.start();
        t2.setName("Odd Thread");
        t2.start();
    }

}
