import java.util.concurrent.CountDownLatch;
/*
Задача:
Промоделировать соревнования гоночных машин, каждая из которых едет по 20 кругов.
Побеждает та, для которой первой выведется на консоль "Car #N. Lap 20". Каждая машина -
отдельный поток.
*/
public class LatchTest {
    public static void main (String[] args) throws InterruptedException {
        int n = 5;
        final CountDownLatch startGate = new CountDownLatch(1);
        for (int i = 0; i < n; i++) {
            Thread t = new Thread("Car #" + i) {
                public void run() {
                    try {
			//ждем стартовой команды
                        startGate.await();
                        for (int i = 0; i < 20; i++){
                            System.out.printf("%s Lap %d%n", getName(), i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace(); 
                    }
                }
            };
            t.start();
        }
	//одновременный старт 
        startGate.countDown();
    }
}
