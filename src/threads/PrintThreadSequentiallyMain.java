package threads;

public class PrintThreadSequentiallyMain {

    public static void main(String[] args) {
        PrintThreadSequential printThreadSequential1 = new PrintThreadSequential(1);
        PrintThreadSequential printThreadSequential2 = new PrintThreadSequential(2);
        PrintThreadSequential printThreadSequential3 = new PrintThreadSequential(0);
        Thread thread1 = new Thread(printThreadSequential1, "t1");
        Thread thread2 = new Thread(printThreadSequential2, "t2");
        Thread thread3 = new Thread(printThreadSequential3, "t3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class PrintThreadSequential implements Runnable{

    static int number=1;
    int max=10;
    int remainder;
    static Object lock = new Object();

    public PrintThreadSequential(int remainder) {
        this.remainder = remainder;
    }

    @Override
    public void run() {
        while(number<max){
            synchronized (lock) {
                while(number % 3 != remainder) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread()+" is printing : "+number);
                number++;
                lock.notifyAll();
            }
        }
    }
}

/*
 class PrintSequenceRunnable implements Runnable{

    public int PRINT_NUMBERS_UPTO=10;
    static int  number=1;
    int remainder;
    static Object lock=new Object();

    PrintSequenceRunnable(int remainder)
    {
        this.remainder=remainder;
    }

    @Override
    public void run() {
        while (number < PRINT_NUMBERS_UPTO-1) {
            synchronized (lock) {
                while (number % 3 != remainder) { // wait for numbers other than remainder
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + number);
                number++;
                lock.notifyAll();
            }
        }
    }
}









public class PrintThreadSequentiallyMain {

    public static void main(String[] args) {

        PrintSequenceRunnable runnable1=new PrintSequenceRunnable(1);
        PrintSequenceRunnable runnable2=new PrintSequenceRunnable(2);
        PrintSequenceRunnable runnable3=new PrintSequenceRunnable(0);

        Thread t1=new Thread(runnable1,"T1");
        Thread t2=new Thread(runnable2,"T2");
        Thread t3=new Thread(runnable3,"T3");

        t1.start();
        t2.start();
        t3.start();
    }
}
*/
