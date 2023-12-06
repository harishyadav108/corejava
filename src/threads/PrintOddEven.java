package threads;

public class PrintOddEven {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Even evenTask = new Even(printer);
        Odd oddTask = new Odd(printer);
        Thread t1 =new Thread(evenTask,"t1");
        Thread t2 =new Thread(oddTask,"t2");
        t1.start();
        t2.start();
    }
}


class Even implements Runnable{
    Printer printer;
    int i=0;

    public Even(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {

        while( i < 10){
            try {
                printer.evenPrinting(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i=i+2;
        }

    }
}

class Odd implements Runnable{
    Printer printer;
    int i=1;

    public Odd(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {

        while( i < 10){
            try {
                printer.oddPrinting(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i=i+2;
        }

    }
}

class Printer {
    boolean isEven=false;
    public synchronized void evenPrinting(int i) throws InterruptedException {

        if(isEven){
            wait();
        }
        System.out.println(Thread.currentThread()+" is printing : "+i);
        notify();
        isEven=true;
    }
    public synchronized void oddPrinting(int i) throws InterruptedException {

        if(!isEven){
            wait();
        }
        System.out.println(Thread.currentThread()+" is printing : "+i);
        notify();
        isEven=false;
    }
}