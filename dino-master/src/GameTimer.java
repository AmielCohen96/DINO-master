public class GameTimer extends Thread {

    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public GameTimer() {
        startTime = 0;
        stopTime = 0;
        isRunning = false;

    }

    @Override
    public void run() {

    }

    public void startTimer() {
        startTime = System.currentTimeMillis()/1000;
        System.out.println(startTime);
        isRunning = true;
    }

    public void stopTimer() {
        stopTime = System.currentTimeMillis();
        isRunning = false;
    }

    public long getTimePlayed() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return stopTime - startTime;
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
