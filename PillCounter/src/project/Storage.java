package project;

import java.util.Date;

public class Storage {
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
        this.history = new int[NUM_COUNTERS][NUM_DAYS];
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
    public String getFormatedHistory(Date today){
        return "";
    }
}
