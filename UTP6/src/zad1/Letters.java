package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {

  private List<Thread> threads;

  public Letters(String letters) {
    threads = new ArrayList<>();
    for (int i = 0; i < letters.length(); i++) {
      char letter = letters.charAt(i);
      Thread thread = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
          System.out.print(letter);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            break;
          }
        }
      });
      thread.setName("Thread " + letter);
      threads.add(thread);
    }
  }

  public List<Thread> getThreads() {
    return threads;
  }

}
