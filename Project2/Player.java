public class Player {
    private final int ID;
    private final int skillLevel;
    private int massageCount;
    private double totalTrainingTime;
    private double totalTrainingWaitTime;
    private int totalTrainingCount;
    private boolean inProcess;
    private double totalTherapyTime;
    private double totalTherapyWaitTime;
    private double totalMassageTime;
    private double totalMassageWaitTime;


    public Player(int ID, int skillLevel) {
        this.ID = ID;
        this.skillLevel = skillLevel;
        massageCount =0;
    }

    public int getID() {
        return ID;
    }

    public boolean isInProcess() {
        return inProcess;
    }
    public int getMassageCount() {
        return massageCount;
    }

    public int getSkillLevel() {
        return skillLevel;
    }


    public void increaseTotalTrainingTime(double time){
        totalTrainingTime +=time;
        totalTrainingCount ++;
    }
    public void increaseTotalMassageTime(double time){
        totalMassageTime +=time;
        //massageCount++;
    }
    public void increaseTotalMassageCount(){
        massageCount++;
    }
    public void setMassageCount(int massageCount) {
        this.massageCount = massageCount;
    }

    public double getTotalMassageTime() {
        return totalMassageTime;
    }

    public double getTotalTherapyTime() {
        return totalTherapyTime;
    }

    public double getTotalTrainingTime() {
        return totalTrainingTime;
    }

    public double getTotalTherapyWaitTime() {
        return totalTherapyWaitTime;
    }

    public double getTotalTrainingWaitTime() {
        return totalTrainingWaitTime;
    }

    public int getTotalTrainingCount() {
        return totalTrainingCount;
    }

    public void increaseTotalTherapyTime(double time){totalTherapyTime +=time;}

    public void increaseTotalTherapyWaitTime(double time){totalTherapyWaitTime +=time;}

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

    public void increaseTrainingWaitTime(double time){totalTrainingWaitTime += time;}

    public double getTotalMassageWaitTime() {return totalMassageWaitTime; }

    public void increaseTotalMassageWaitTime(double time){totalMassageWaitTime+=time;
    }
}
