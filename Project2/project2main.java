import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class project2main {
    public static double time;

    public static void main(String[] args) throws FileNotFoundException {
        PriorityQueue<Event> trainingQueue = new PriorityQueue<>(new TrainingComparator());
        PriorityQueue<Event> massageQueue = new PriorityQueue<>(new MassageComparator());
        PriorityQueue<Event> physiotherapyQueue = new PriorityQueue<>(new PhysiotherapyComparator());
        PriorityQueue<Event> events = new PriorityQueue<>();
        ArrayList<Player> players = new ArrayList<>();
        PriorityQueue<Physiotherapist> physiotherapists = new PriorityQueue<>();
        Scanner scanner = new Scanner(new File(args[0])).useLocale(Locale.US);
        int playerCount = scanner.nextInt();
        PrintStream out = new PrintStream(args[1]);
        ArrayList<String> eventType = new ArrayList<>();
        eventType.add("QUITTHERAPY");
        eventType.add("QUITTRAINING");
        eventType.add("QUITMASSAGE");
        eventType.add("STARTTRAINING");
        eventType.add("STARTMASSAGE");
        for (int i = 0; i < playerCount; i++) {
            int playerID = scanner.nextInt();
            int playerSkill = scanner.nextInt();
            players.add(new Player(playerID, playerSkill));
        }
        int arrivalCount = scanner.nextInt();
        for (int i = 0; i < arrivalCount; i++) {
            String type = scanner.next();
            int ID = scanner.nextInt();
            double currentTime = scanner.nextDouble();
            double serviceTime = scanner.nextDouble();
            if (Objects.equals(type, "t")) {
                Event tempEvent = new Event(players.get(ID), currentTime, serviceTime,3);
                events.add(tempEvent);
            } else if (Objects.equals(type, "m")) {
                Event tempEvent = new Event(players.get(ID), currentTime, serviceTime,4);
                events.add(tempEvent);
            }
        }
        int physiotherapistsCount = scanner.nextInt();
        for (int i = 0; i < physiotherapistsCount; i++) {
            physiotherapists.add(new Physiotherapist(i, scanner.nextDouble()));
        }
        int availableCoach = scanner.nextInt();
        int availableMasseur = scanner.nextInt();
//        Simulation simulation = new Simulation(events,trainingQueue,physiotherapyQueue,massageQueue,players,
//                physiotherapists,availableCoach,availableMasseur);
        int idOfMostWaited=0;
        double waitTimeOfTheMostWaited=0;
        int idOfLeastWaited=-1;
        double waitTimeOfLeastWaited=Double.POSITIVE_INFINITY;
        int totalMassageCount =0;
        int totalTrainingCount = 0;
        double totalTrainingWait =0;
        double totalMassageWait=0;
        double totalTherapyWait =0;
        double totalTrainingTime =0;
        double totalMassageTime=0;
        double totalTherapyTime=0;
        int maxTrainingQueue = 0;
        int maxTherapyQueue = 0;
        int maxMassageQueue = 0;
        int totalNumberOfInvalidAttempts = 0;
        int totalNumberofCancelledAttempts = 0;
        while (!events.isEmpty()) {
            Event event = events.poll();
            time = event.getStartTime();
            switch (event.getTypeIndex()) {
                case 0 -> { //quit therapy
                    event.getPlayer().setInProcess(false);
                    if (!physiotherapyQueue.isEmpty()) {
                        Event waitedTherapy = physiotherapyQueue.poll();
                        waitedTherapy.getPlayer().increaseTotalTherapyWaitTime(time - waitedTherapy.getStartTime());
                        waitedTherapy.getPlayer().increaseTotalTherapyTime(event.getPhysiotherapist().getServiceTime());
                        Event quitTherapy = new Event(waitedTherapy.getPlayer(), time + event.getPhysiotherapist().getServiceTime(), 0, 0, event.getPhysiotherapist());
                        events.add(quitTherapy);
                    } else {
                        Physiotherapist tempTherapist = event.getPhysiotherapist();
                        physiotherapists.add(tempTherapist);
                    }
                }
                case 1 -> { //quit training
                    if (physiotherapists.peek() != null) {
                        Physiotherapist tempTherapist = physiotherapists.poll();
                        event.getPlayer().increaseTotalTherapyTime(tempTherapist.getServiceTime());
                        Event quitTherapy = new Event(event.getPlayer(), time+ tempTherapist.getServiceTime(), tempTherapist.getServiceTime(), 0, tempTherapist);
                        events.add(quitTherapy);
                    } else {
                        physiotherapyQueue.add(event);
                    }
                    if (trainingQueue.isEmpty()) {
                        availableCoach++;
                    } else {
                        Event waitedTraining = trainingQueue.poll();
                        waitedTraining.getPlayer().increaseTrainingWaitTime(time - waitedTraining.getStartTime());
                        waitedTraining.getPlayer().increaseTotalTrainingTime(waitedTraining.getProcessTime());
                        Event quitTraining = new Event(waitedTraining.getPlayer(), time + waitedTraining.getProcessTime(), waitedTraining.getProcessTime()
                                 , 1);
                        events.add(quitTraining);
                    }
                    if (maxTherapyQueue < physiotherapyQueue.size()) {
                        maxTherapyQueue = physiotherapyQueue.size();
                    }
                }
                case 2 -> { //quit massage
                    event.getPlayer().setInProcess(false);
                    if (massageQueue.isEmpty()) {
                        availableMasseur++;
                    } else {
                        Event waitedMassage = massageQueue.poll();
                        waitedMassage.getPlayer().increaseTotalMassageWaitTime(time - waitedMassage.getStartTime());
                        waitedMassage.getPlayer().increaseTotalMassageTime(waitedMassage.getProcessTime());
                        Event quitMassage = new Event(waitedMassage.getPlayer(), time + waitedMassage.getProcessTime(), 0, 2);
                        events.add(quitMassage);
                    }
                }
                case 3 -> { //start training
                    if (event.getPlayer().isInProcess()) {
                        totalNumberofCancelledAttempts++;
                    }
                    else{
                        event.getPlayer().setInProcess(true);
                        if (availableCoach > 0) {
                            availableCoach--;
                            event.getPlayer().increaseTotalTrainingTime(event.getProcessTime());
                            Event quitTraining = new Event(event.getPlayer(), time+ event.getProcessTime(), event.getProcessTime(), 1);
                            events.add(quitTraining);
                        }
                        else {
                            trainingQueue.add(event);
                        }
                        if (maxTrainingQueue < trainingQueue.size()) {
                            maxTrainingQueue = trainingQueue.size();
                        }
                    }
                }
                case 4 -> { //start massage
                    if (event.getPlayer().getMassageCount() == 3) {
                        totalNumberOfInvalidAttempts++;
                    }
                    else if (event.getPlayer().isInProcess()) {
                        totalNumberofCancelledAttempts++;
                    }
                    else {
                        event.getPlayer().setInProcess(true);
                        if (availableMasseur > 0) {
                            availableMasseur--;
                            event.getPlayer().increaseTotalMassageTime(event.getProcessTime());
                            event.getPlayer().increaseTotalMassageCount();
                            Event quitMassage = new Event(event.getPlayer(), time + event.getProcessTime(), 0, 2);
                            events.add(quitMassage);
                        } else {
                            event.getPlayer().increaseTotalMassageCount();
                            massageQueue.add(event);
                        }
                    }
                    if (maxMassageQueue < massageQueue.size()) {
                        maxMassageQueue = massageQueue.size();
                    }
                }
            }
            }
        ArrayList<Player> maxCountPlayers = new ArrayList<>();
        for(Player player : players) {
            if (player.getMassageCount() == 3) {
                maxCountPlayers.add(player);
            }
            if(waitTimeOfTheMostWaited<player.getTotalTherapyWaitTime()){
                idOfMostWaited = player.getID();
                waitTimeOfTheMostWaited = player.getTotalTherapyWaitTime();
            }
                totalTrainingTime += player.getTotalTrainingTime();
                totalTrainingWait += player.getTotalTrainingWaitTime();
                totalTherapyTime += player.getTotalTherapyTime();
                totalTherapyWait += player.getTotalTherapyWaitTime();
                totalMassageTime += player.getTotalMassageTime();
                totalMassageWait += player.getTotalMassageWaitTime();
                totalMassageCount += player.getMassageCount();
                totalTrainingCount += player.getTotalTrainingCount();
        }
        if(!maxCountPlayers.isEmpty()){
            for (Player player:maxCountPlayers){
                if(player.getTotalMassageWaitTime()<waitTimeOfLeastWaited){
                    idOfLeastWaited=player.getID();
                    waitTimeOfLeastWaited =player.getTotalMassageWaitTime();
                }
            }
        }else
            waitTimeOfLeastWaited = -1;
        out.println(maxTrainingQueue);
        out.println(maxTherapyQueue);
        out.println(maxMassageQueue);
        out.printf(Locale.US, "%.3f%n",totalTrainingWait/totalTrainingCount);
        out.printf(Locale.US, "%.3f%n",totalTherapyWait/totalTrainingCount);
        out.printf(Locale.US, "%.3f%n",totalMassageWait/(totalMassageCount));
        out.printf(Locale.US, "%.3f%n",totalTrainingTime/(totalTrainingCount));
        out.printf(Locale.US, "%.3f%n",totalTherapyTime/totalTrainingCount);
        out.printf(Locale.US, "%.3f%n",totalMassageTime/totalMassageCount);
        out.printf(Locale.US, "%.3f%n",(totalTrainingWait+totalTrainingTime+totalTherapyWait+totalTherapyTime)/totalTrainingCount);
        out.print(idOfMostWaited+" ");
        out.printf(Locale.US,"%.3f%n",waitTimeOfTheMostWaited);
        out.print(idOfLeastWaited+" ");
        out.printf(Locale.US,"%.3f%n",waitTimeOfLeastWaited);
        out.println(totalNumberOfInvalidAttempts);
        out.println(totalNumberofCancelledAttempts);
        out.printf(Locale.US, "%.3f%n",time);
    }
}
