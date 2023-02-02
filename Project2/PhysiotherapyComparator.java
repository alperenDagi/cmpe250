import java.util.Comparator;

public class PhysiotherapyComparator implements Comparator<Event> {
    //change here
    @Override
    public int compare(Event o1, Event o2) {
        if (Double.compare(o1.getProcessTime(), o2.getProcessTime()) < 0) {
            return 1;
        } else if (Double.compare(o1.getProcessTime(), o2.getProcessTime()) >0) {
            return -1;
        } else {
            if(Double.compare(o1.getStartTime(),o2.getStartTime())==0){
                return Double.compare(o1.getPlayer().getID(), o2.getPlayer().getID());
            }else{
                return Double.compare(o1.getStartTime(), o2.getStartTime());
            }
        }
    }
}