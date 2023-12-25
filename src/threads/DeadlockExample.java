package threads;

public class DeadlockExample {

    static class SharedResource {
        private final Object lock1 = new Object();
        private final Object lock2 = new Object();

        public void method1() {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " acquired lock1");
                /*try {
                    Thread.sleep(100); // Simulating some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock2");
                }
            }
        }

        public void method2() {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock2");
                /*try {
                    Thread.sleep(100); // Simulating some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock1");
                }
            }
        }
    }

    public static void main(String[] args) {
        final SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> sharedResource.method1(), "Thread-1");
        Thread thread2 = new Thread(() -> sharedResource.method2(), "Thread-2");

        thread1.start();
        thread2.start();
    }
}
