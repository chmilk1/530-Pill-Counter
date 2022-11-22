public class PCounter {
    private int pillsCounted; //the num of pills counted today

    /**
     * Constructor for PillCounter
     * just sets the pills counted to zero
     */
    public PCounter(){
        this.pillsCounted = 0;
    }

    /**
     * resets the pills counted to 0
     */
    public void reset(){
        pillsCounted = 0;
    }

    /**
     * gets weather or not a specific light should be lit
     * @param lightNum the number of the light starting at 0
     * @return a boolean representing weather or not the light is enabled
     */
    public boolean getLight(int lightNum){
        return pillsCounted >= lightNum;

    }

    /**
     * returns the current number of pills counted
     * @return the int pillsCounted
     */
    public int getPillsCounted() {
        return pillsCounted;
    }

    /**
     * increments the num of pills counted
     */
    public void countPill() {
        this.pillsCounted += 1;
    }
}
