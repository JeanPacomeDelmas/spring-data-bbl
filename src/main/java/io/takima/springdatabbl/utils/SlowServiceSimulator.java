package io.takima.springdatabbl.utils;

public class SlowServiceSimulator {

    // Don't do this at home
    public static void simulateSlowThreeSecondsRequest() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
