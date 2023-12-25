package threads;

class A {
    Object lock1 = new Object();
    Object lock2 = new Object();

    public void m1(){
        synchronized (lock1){
            System.out.println("in m1 method thread"+Thread.currentThread());

            synchronized (lock2){
                System.out.println("in nested synchronized method of m1"+Thread.currentThread());
            }
        }
    }

    public void m2(){
        synchronized (lock2){
            System.out.println("in m2 method thread"+Thread.currentThread());

            synchronized (lock1){
                System.out.println("in nested synchronized method of m2"+Thread.currentThread());
            }
        }

    }
}



public class DeadLockEx {

    A obj1 = new A();


    public void run123() {

        obj1.m1();
        obj1.m2();

    }
    public static void main(String[] args) {
        DeadLockEx d = new DeadLockEx();
        Thread t1 =new Thread(()->d.run123());
        Thread t2 =new Thread(()->d.run123());
        t1.start();
        t2.start();

    }
}
