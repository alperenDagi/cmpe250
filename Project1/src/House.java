public class House implements Comparable<House> {
    private int id;
    private int duration;
    private double rating;
    /**
     * constructor of house
     * @param id id of house
     * @param duration duration of house
     * @param rating rating of house
     */
    public House(int id, int duration, double rating) {
        this.id = id;
        this.duration = duration;
        this.rating = rating;
    }
    /**
     * gets rating
     * @return rating
     */
    public double getRating() {
        return rating;
    }
    public int getID(){return id;}
    /**
     * gets duration
     * @return duration
     */
    public int getDuration() {
        return duration;
    }
    /**
     * sets duration
     * @param duration duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * compares id's of house
     */
    @Override
    public int compareTo(House other) {
        return this.id- other.id ;
    }
}

