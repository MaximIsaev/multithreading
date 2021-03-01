package main.task_1;

import java.util.Map;
import java.util.Random;

public class WriterThread extends Thread {
    private Map<Integer, String> map;
    private Random random;
    private String name;

    public WriterThread(Map<Integer, String> map,
                        String threadName, long randomSeed) {
        this.map = map;
        this.random = new Random(randomSeed);
        this.name = threadName;
    }

    public void run() {
        while (true) {
            Integer key = random.nextInt(10);
            String value = name;

            if(map.putIfAbsent(key, value) == null) {
                long time = System.currentTimeMillis();
                String output = String.format("%d: %s has put [%d => %s]",
                        time, name, key, value);
                System.out.println(output);
            }


            Integer keyToRemove = random.nextInt(10);

            if (map.remove(keyToRemove, value)) {
                long time = System.currentTimeMillis();
                String output = String.format("%d: %s has removed [%d => %s]",
                        time, name, keyToRemove, value);
                System.out.println(output);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}