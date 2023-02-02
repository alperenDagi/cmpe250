public class Student implements Comparable<Student> {
    private int id ;
    private String name;
    private int duration;
    private double rating;

    /**
     * represent student
     * @param id id of student
     * @param name name of student
     * @param duration duration of student
     * @param rating rating of student
     */
    public Student(int id, String name, int duration, double rating) {
        this.id = id;
        this.name = name;
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
     * gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * compares students
     * @param o other student
     * @return difference between id's
     */
    @Override
    public int compareTo(Student o) {
        return this.id-o.id;
    }
}
