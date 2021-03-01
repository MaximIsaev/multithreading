package main.task_1;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReaderThread extends Thread {
    private Map<Integer, String> map;
    private String name;

    public ReaderThread(Map<Integer, String> map, String threadName) {
        this.map = map;
        this.name = threadName;
    }

    public void run() {
        while (true) {
            Set<Integer> keySetView = map.keySet();
            Iterator<Integer> iterator = keySetView.iterator();

            long time = System.currentTimeMillis();
            String output = time + ": " + name + ": ";

            while (iterator.hasNext()) {
                Integer key = iterator.next();
                String value = map.get(key);
                output += key + "=>" + value + "; ";
            }

            System.out.println(output);

            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
