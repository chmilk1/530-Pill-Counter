<<<<<<< HEAD:PillCounter/src/main/java/Storage.java
=======
package project;

import java.util.Calendar;
<<<<<<< HEAD:PillCounter/src/main/java/Storage.java
>>>>>>> 5d6a2e57c346f0ffd0ff36d9bbf007e90a80d4f7:PillCounter/src/project/Storage.java
=======
>>>>>>> 5d6a2e57c346f0ffd0ff36d9bbf007e90a80d4f7:PillCounter/src/project/Storage.java
import java.util.Date;

public class Storae {
    static final int NUM_COUNTERS = 4;
    static final int NUM_DAYS = 31;

    PCounter[] counters;
    int[][] history;
    int day;

    /**
     * Constructor for Storage
     * inits the counter and history lists
     */
    public Storage(){
        this.counters = new PCounter[NUM_COUNTERS];
        this.history = new int[NUM_DAYS][NUM_COUNTERS];
    }

    /**
     * uptics the counter at position n
     * @param n the counter to be upticked, starting at 0
     */
    public void uptickCounter(int n){
        counters[n].countPill();
    }

    /**
     * get weather or not a light is on
     * @param n the counter position starting at 0
     * @param light the lights position starting at 0
     * @return a boolean of weather or not a light is on
     */
    public boolean getCounterLights(int n, int light){
        return counters[n].getLight(n);
    }

    /**
     * saves the current days info and
     * resets all the counters afterwards
     */
    public void nextDay(){
        commitToday();
        advanceDay();
        resetCounters();
    }

    /**
     * saves the current days info and
     * resets all the counters afterwards
     */
    private void commitToday(){
        for (int i = 0; i < NUM_COUNTERS; i++){
            history[day][i] = counters[i].getPillsCounted();
        }
    }

    /**
     * adds 1 to the day or loops back around to 0
     * if days exceeds NUM_DAYS
     */
    private void advanceDay(){
        day = (day > NUM_DAYS-1) ? 0 : (day + 1);
    }

    /**
     * calls reset() for each PillCounter in counters
     */
    private void resetCounters(){
        for (PCounter c: counters){
            c.reset();
        }
    }

    /**
     * generates a formated
     * @return the formated string of each days inputs
     */
    public String getFormatedHistory(Calendar today){
        StringBuilder sb = new StringBuilder();

        sb.append("Starting from " + today.toString() + "\n");

        for (int i = 0; i < NUM_DAYS; i++){

            sb.append("days back: " +(i * -1) + " ");

            for (int j = 0; j < NUM_COUNTERS; j++){
                sb.append( String.format("%2.2d: %2.2d ", j+1, history[(i+day) % NUM_DAYS][j]));
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
