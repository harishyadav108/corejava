package threads;

import java.util.ArrayList;
import java.util.List;

public class PubSubEx {

    List sharedList = new ArrayList();

    public void producer() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            synchronized (sharedList) {
                if (sharedList.isEmpty()) {
                    System.out.println("adding element in the list");
                    sharedList.add(i);
                    sharedList.notify();
                    sharedList.wait();
                    System.out.println("current thread is "+Thread.currentThread());
                    Thread.sleep(1000);
                }/*else {
                    sharedList.notify();
                }*/
            }
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {
            synchronized (sharedList) {
                if (!sharedList.isEmpty()) {
                    System.out.println("pull message from list =" + sharedList.remove(0));
                    sharedList.notify();
                    sharedList.wait();
                }/* else {

                }*/

            }
        }
    }

    public static void main(String[] args) {
        PubSubEx obj = new PubSubEx();
        Thread producer = new Thread(() -> {
            try {
                obj.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                obj.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
