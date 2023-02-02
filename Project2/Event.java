import java.util.ArrayList;

public class Event implements Comparable<Event>{
    private double startTime;
    private double processTime;
    private double endTime;
    private Player player;
    private Physiotherapist physiotherapist;
    private int typeIndex;

    @Override
    public int compareTo(Event o) {
        if(Double.compare(this.startTime, o.startTime)==0){
           if(this.getPlayer().getID()- o.getPlayer().getID() ==0){
               return this.getTypeIndex()-o.getTypeIndex();
           }else{
               return this.getPlayer().getID()-o.getPlayer().getID();
           }
        }else{
            return (Double.compare(this.startTime, o.startTime));
        }
    }

    public Event(Player player,double startTime,double processTime,int i) {

        this.startTime = startTime;
        this.processTime = processTime;
        this.player =player;
        typeIndex = i;
    }
    public Event(Player player,double startTime,double processTime,int i,Physiotherapist physiotherapist) {

        this.startTime = startTime;
        this.processTime = processTime;
        this.player =player;
        this.physiotherapist = physiotherapist;
        typeIndex = i;

    }

    public Player getPlayer() {
        return player;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public double getProcessTime() {
        return processTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
}
