public class Physiotherapist implements Comparable<Physiotherapist> {
    private int ID;
    private double serviceTime;
    public Physiotherapist(int ID,double serviceTime) {
        this.ID = ID;
        this.serviceTime = serviceTime;
    }
    public double getServiceTime() {
        return serviceTime;
    }
    @Override
    public int compareTo(Physiotherapist o) {
        return this.ID-o.ID;
    }
}
