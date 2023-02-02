import java.util.Comparator;
public class MassageComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1,Event o2) {
        if (Double.compare(o1.getPlayer().getSkillLevel(), o2.getPlayer().getSkillLevel())<0) {
            return 1;
        } else if (Double.compare(o1.getPlayer().getSkillLevel(), o2.getPlayer().getSkillLevel())>0) {
            return -1;
        } else {
            if(Double.compare(o1.getStartTime(),o2.getStartTime())<0){
                return -1;
            }else if(Double.compare(o1.getStartTime(),o2.getStartTime())>0){
                return 1;
            }
            else{
                return Double.compare(o1.getPlayer().getID(), o2.getPlayer().getID());
            }

        }
    }
}
