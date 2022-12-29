package zad2;


enum TaskState {
  CREATED,
  RUNNING,
  ABORTED,
  READY
}

public class StringTask implements Runnable {
  private final String str;
  private final int repetitions;
  private TaskState state;
  private String result;

  public StringTask(String str, int repetitions) {
    this.str = str;
    this.repetitions = repetitions;
    this.state = TaskState.CREATED;
  }

  @Override
  public void run() {
    state = TaskState.RUNNING;
    for (int i = 0; i < repetitions; i++) {
        result += str;
    }
    if (state != TaskState.ABORTED) {
      state = TaskState.READY;
    }
  }

  public String getResult() {return result;}
  public TaskState getState() {return state;}

  public void start() {
    new Thread(this).start();
  }

  public void abort() {
    state = TaskState.ABORTED;
    Thread.currentThread().interrupt();
  }

  public boolean isDone() {
    return state == TaskState.READY || state == TaskState.ABORTED;
  }
}
