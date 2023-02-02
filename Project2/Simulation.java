//import java.util.ArrayList;
//import java.util.PriorityQueue;
//import java.util.Queue;
//
//public class Simulation {
//    PriorityQueue<Event> events;
//    Queue<Player> trainingQueue;
//    PriorityQueue<Player> therapyQueue;
//    PriorityQueue<Player> massageQueue;
//    ArrayList<Player> players;
//    ArrayList<Physiotherapist> therapists;
//    int coachCount;
//    int masseurCount;
//    public Simulation(PriorityQueue<Event> events, Queue<Player> trainingQueue, PriorityQueue<Player> therapyQueue,
//                      PriorityQueue<Player> massageQueue, ArrayList<Player> players,ArrayList<Physiotherapist> therapists,
//                      int coachCount, int masseurCount) {
//        this.events = events;
//        this.players = players;
//        this.trainingQueue = trainingQueue;
//        this.therapyQueue = therapyQueue;
//        this.massageQueue = massageQueue;
//        this.therapists = therapists;
//        this.coachCount = coachCount;
//        this.masseurCount = masseurCount;
//
//    }
//
//    public void start(){
//        System.out.println("noluo lan");
//        while(!events.isEmpty()){
//            Event tempEvent = events.poll();
//            time = tempEvent.getStartTime();
//            System.out.println(time);
//            if(tempEvent instanceof TrainingArrival){
//                if (tempEvent.getPlayer().isInProcess()) {
//                    tempEvent.getPlayer().increaseCancelledAttempt();
//                    continue;
//                }
//                if(coachCount>0){
//                    if(trainingQueue.isEmpty()){
//                            coachCount--;
//                            tempEvent.getPlayer().setInProcess(true);
//                            tempEvent.getPlayer().increaseTotalTrainingTime(tempEvent.getProcessTime());
//                            events.add(new QuitTraining(tempEvent.getPlayer(), tempEvent.getEndTime(), 0));
//                    }else{
//                        tempEvent.getPlayer().setInProcess(true);
//                        tempEvent.getPlayer().setProcessTime(tempEvent.getProcessTime());
//                        tempEvent.getPlayer().setStartTime(tempEvent.getStartTime());
//                        trainingQueue.add(tempEvent.getPlayer());
//                    }
//                }
//                else{
//                    tempEvent.getPlayer().setInProcess(true);
//                    tempEvent.getPlayer().setProcessTime(tempEvent.getProcessTime());
//                    tempEvent.getPlayer().setStartTime(tempEvent.getStartTime());
//                    trainingQueue.add(tempEvent.getPlayer());
//                }
//
//            }else if(tempEvent instanceof QuitTraining){
//                tempEvent.getPlayer().setInProcess(false);
//                for(Physiotherapist physiotherapist:therapists){
//                    if(physiotherapist.isAvailable()){
//                        if(therapyQueue.isEmpty()){
//                            physiotherapist.setAvailable(false);
//                            tempEvent.getPlayer().increaseTotalTherapyTime(tempEvent.getPlayer().getProcessTime());
//                            events.add(new QuitTherapy(tempEvent.getPlayer(), time+physiotherapist.getServiceTime(),0));
//                            break;
//                        }else{
//                            tempEvent.getPlayer().setInProcess(true);
//                            tempEvent.getPlayer().setStartTime(tempEvent.getStartTime());
//                            therapyQueue.add(tempEvent.getPlayer());
//                            break;
//                        }
//                    }
//                    else{
//                        tempEvent.getPlayer().setInProcess(true);
//                        tempEvent.getPlayer().setStartTime(tempEvent.getStartTime());
//                        therapyQueue.add(tempEvent.getPlayer());
//                        break;
//                    }
//                }
//                if(trainingQueue.isEmpty()){
//                    coachCount++;
//                }else{
//                    Player tempPlayer =trainingQueue.poll();
//                    tempPlayer.increaseTrainingWaitTime(time- tempPlayer.getStartTime());
//                    tempPlayer.increaseTotalTrainingTime(tempPlayer.getProcessTime());
//                    events.add(new QuitTraining(tempPlayer,time+ tempPlayer.getProcessTime(), 0));
//                }
//
//            }else if(tempEvent instanceof QuitTherapy){
//                tempEvent.getPlayer().setInProcess(false);
//                if(therapyQueue.isEmpty()){
//                    for(Physiotherapist physiotherapist:therapists){
//                        if(physiotherapist.getPlayer().equals(tempEvent.getPlayer())){
//                            physiotherapist.setAvailable(true);
//                            physiotherapist.setPlayer(null);
//                            break;
//                        }
//                    }
//                }else{
//                    for(Physiotherapist physiotherapist:therapists){
//                        if(physiotherapist.getPlayer().equals(tempEvent.getPlayer())){
//                            Player tempPlayer = therapyQueue.poll();
//                            tempPlayer.increaseTotalTherapyWaitTime(time-tempPlayer.getStartTime());
//                            tempPlayer.increaseTotalTherapyTime(physiotherapist.getServiceTime());
//                            physiotherapist.setPlayer(tempPlayer);
//                            events.add(new QuitTherapy(tempPlayer,time+ physiotherapist.getServiceTime(),0));
//                            break;
//                        }
//                    }
//                }
//
//            }else if(tempEvent instanceof MassageArrival){
//                if(tempEvent.getPlayer().getMassageCount()>0){
//                    if(masseurCount>0){
//                        if(massageQueue.isEmpty()){
//                            masseurCount--;
//                            tempEvent.getPlayer().setInProcess(true);
//                            tempEvent.getPlayer().increaseTotalMassageTime(tempEvent.getProcessTime());
//                            events.add(new QuitMassage(tempEvent.getPlayer(), tempEvent.getEndTime(), 0));
//                        }else{
//                            tempEvent.getPlayer().setInProcess(true);
//                            tempEvent.getPlayer().setProcessTime(tempEvent.getProcessTime());
//                            tempEvent.getPlayer().setStartTime(tempEvent.getStartTime());
//                            massageQueue.add(tempEvent.getPlayer());
//                        }
//                    }else{
//                        tempEvent.getPlayer().setInProcess(true);
//                        tempEvent.getPlayer().setProcessTime(tempEvent.getProcessTime());
//                        tempEvent.getPlayer().setStartTime(tempEvent.getStartTime());
//                        massageQueue.add(tempEvent.getPlayer());
//                    }
//                }else{
//                    tempEvent.getPlayer().increaseInvalidAttempt();
//                }
//
//            }else if(tempEvent instanceof QuitMassage){
//                tempEvent.getPlayer().setInProcess(false);
//                if(massageQueue.isEmpty()) {
//                    masseurCount++;
//                }else{
//                    Player tempPlayer = massageQueue.poll();
//                    tempPlayer.increaseTotalMassageWaitTime(time-tempPlayer.getStartTime());
//                    tempPlayer.increaseTotalMassageTime(tempPlayer.getProcessTime());
//                    events.add(new QuitMassage(tempPlayer,tempPlayer.getProcessTime()+time,0));
//                }
//            }else System.out.println("noluo la");
//        }
//    }
//}
