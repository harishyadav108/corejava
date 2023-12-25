package threads;

public class InterruptExample {

    public static void main(String[] args) {
        Thread myThread = new Thread(new MyRunnable());
        myThread.start();

        // Let the thread run for some time
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt the thread
        myThread.interrupt();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Working on task " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Handle interruption by checking the interrupted status
            System.out.println("Thread interrupted. Cleaning up or handling interruption...");
            // Optionally, perform cleanup or handle the interruption
            return;
        }

        System.out.println("Task complete. Exiting gracefully...");
    }
}
