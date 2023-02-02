import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;

public class project4main{
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        FileReader file = new FileReader(args[0]);
        PrintStream out = new PrintStream(args[1]);
        BufferedReader reader = new BufferedReader(file);
        Vertex sink = new Vertex("Sink");
        // create green train
        int numOfGreenTrain = Integer.parseInt(reader.readLine().split(" ")[0]);
        ArrayList<Vertex> greenTrainArray= new ArrayList<>();
        if(numOfGreenTrain <=0){
            reader.readLine();
        }else{
            String[] greenTrains = reader.readLine().split(" ");
            for(int i=0; i< numOfGreenTrain;i++){
                Vertex greenTrain = new Vertex("GreenTrain");
                greenTrain.addEdge(sink,Integer.parseInt(greenTrains[i]));
                greenTrainArray.add(greenTrain);
            }
        }
        //create red train
        int numOfRedTrain = Integer.parseInt(reader.readLine().split(" ")[0]);
        ArrayList<Vertex> redTrainArray = new ArrayList<>();
        if(numOfRedTrain <=0){
            reader.readLine();
        }else{
            String[] redTrains = reader.readLine().split(" ");
            for(int i=0; i< numOfRedTrain;i++){
                Vertex redTrain = new Vertex("RedTrain");
                redTrain.addEdge(sink,Integer.parseInt(redTrains[i]));
                redTrainArray.add(redTrain);
            }
        }
        //create green reindeer
        int numOfGreenReindeer = Integer.parseInt(reader.readLine().split(" ")[0]);
        ArrayList<Vertex> greenReindeerArray = new ArrayList<>();
        if(numOfGreenReindeer <=0){
            reader.readLine();
        }else{
            String[] greenReindeers = reader.readLine().split(" ");
            for(int i=0; i< numOfGreenReindeer;i++){
                Vertex greenReindeer = new Vertex("GreenReindeer");
                greenReindeer.addEdge(sink,Integer.parseInt(greenReindeers[i]));
                greenReindeerArray.add(greenReindeer);
            }
        }
        // create red reindeer
        int numOfRedReindeer = Integer.parseInt(reader.readLine().split(" ")[0]);
        ArrayList<Vertex> redReindeerArray = new ArrayList<>();
        if(numOfRedReindeer <=0){
            reader.readLine();
        }else{
            String[] redReindeers = reader.readLine().split(" ");
            for(int i=0; i< numOfRedReindeer;i++){
                Vertex redReindeer = new Vertex("RedReindeer");
                redReindeer.addEdge(sink,Integer.parseInt(redReindeers[i]));
                redReindeerArray.add(redReindeer);
            }
        }
        // create bags
        int numOfBag = Integer.parseInt(reader.readLine().split(" ")[0]);
        Vertex source = new Vertex("Source");
        ArrayList<Vertex> aBags = new ArrayList<>();
        ArrayList<Vertex> otherBags = new ArrayList<>();
        int totalBag =0;
        if(numOfBag <=0){
            reader.readLine();
        }else{
            String[] bags = reader.readLine().split(" ");
            for(int i=0; i< numOfBag*2;i +=2) {
                totalBag+=Integer.parseInt(bags[i+1]);
                switch (bags[i]) {
                    case "a" -> {
                        Vertex aBag = new Vertex("A Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenTrain; j++) {
                            aBag.addEdge(greenTrainArray.get(j),1);
                        }
                        for (int j = 0; j < numOfRedTrain; j++) {
                            aBag.addEdge(redTrainArray.get(j),1);
                        }
                        for (int j = 0; j < numOfGreenReindeer; j++) {
                            aBag.addEdge(greenReindeerArray.get(j),1);
                        }
                        for (int j = 0; j < numOfRedReindeer; j++) {
                            aBag.addEdge(redReindeerArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "b" -> {
                        Vertex bag = new Vertex("B Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i + 1]));
                        for (int j = 0; j < numOfGreenTrain; j++) {
                            bag.addEdge(greenTrainArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        for (int j = 0; j < numOfGreenReindeer; j++) {
                            bag.addEdge(greenReindeerArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        otherBags.add(bag);
                    }
                    case "c" -> {
                        Vertex bag = new Vertex("C Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfRedTrain; j++) {
                            bag.addEdge(redTrainArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        for (int j = 0; j < numOfRedReindeer; j++) {
                            bag.addEdge(redReindeerArray.get(j),Integer.parseInt(bags[i+1]));
                        }
                        otherBags.add(bag);
                    }
                    case "d" -> {
                        Vertex bag = new Vertex("D Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenTrain; j++) {
                            bag.addEdge(greenTrainArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        for (int j = 0; j < numOfRedTrain; j++) {
                            bag.addEdge(redTrainArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        otherBags.add(bag);
                    }
                    case "e" -> {
                        Vertex bag = new Vertex("E Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenReindeer; j++) {
                            bag.addEdge(greenReindeerArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        for (int j = 0; j < numOfRedReindeer; j++) {
                            bag.addEdge(redReindeerArray.get(j),Integer.parseInt(bags[i+1]));
                        }
                        otherBags.add(bag);
                    }
                    case "ab" -> {
                        Vertex aBag = new Vertex("AB Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenTrain; j++) {
                            aBag.addEdge(greenTrainArray.get(j),1);
                        }
                        for (int j = 0; j < numOfGreenReindeer; j++) {
                            aBag.addEdge(greenReindeerArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "ac" -> {
                        Vertex aBag = new Vertex("AC Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfRedTrain; j++) {
                            aBag.addEdge(redTrainArray.get(j),1);
                        }
                        for (int j = 0; j < numOfRedReindeer; j++) {
                            aBag.addEdge(redReindeerArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "ad" -> {
                        Vertex aBag = new Vertex("AD Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenTrain; j++) {
                            aBag.addEdge(greenTrainArray.get(j),1);
                        }
                        for (int j = 0; j < numOfRedTrain; j++) {
                            aBag.addEdge(redTrainArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "ae" -> {
                        Vertex aBag = new Vertex("AE Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenReindeer; j++) {
                            aBag.addEdge(greenReindeerArray.get(j),1);
                        }
                        for (int j = 0; j < numOfRedReindeer; j++) {
                            aBag.addEdge(redReindeerArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "bd" -> {
                        Vertex bag = new Vertex("BD Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i + 1]));
                        for (int j = 0; j < numOfGreenTrain; j++) {
                            bag.addEdge(greenTrainArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        otherBags.add(bag);
                    }
                    case "be" -> {
                        Vertex bag = new Vertex("BE Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i + 1]));
                        for (int j = 0; j < numOfGreenReindeer; j++) {
                            bag.addEdge(greenReindeerArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        otherBags.add(bag);
                    }
                    case "cd" -> {
                        Vertex bag = new Vertex("CD Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfRedTrain; j++) {
                            bag.addEdge(redTrainArray.get(j),Integer.parseInt(bags[i + 1]));
                        }
                        otherBags.add(bag);
                    }
                    case "ce" -> {
                        Vertex bag = new Vertex("CE Bag");
                        source.addEdge(bag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfRedReindeer; j++) {
                            bag.addEdge(redReindeerArray.get(j),Integer.parseInt(bags[i+1]));
                        }
                        otherBags.add(bag);
                    }
                    case "abd" -> {
                        Vertex aBag = new Vertex("ABD Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenTrain; j++) {
                            aBag.addEdge(greenTrainArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "abe" -> {
                        Vertex aBag = new Vertex("ABE Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfGreenReindeer; j++) {
                            aBag.addEdge(greenReindeerArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "acd" -> {
                        Vertex aBag = new Vertex("ACD Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfRedTrain; j++) {
                            aBag.addEdge(redTrainArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                    case "ace" -> {
                        Vertex aBag = new Vertex("ACE Bag");
                        source.addEdge(aBag,Integer.parseInt(bags[i+1]));
                        for (int j = 0; j < numOfRedReindeer; j++) {
                            aBag.addEdge(redReindeerArray.get(j),1);
                        }
                        aBags.add(aBag);
                    }
                }
            }
        }
        Graph graph = new Graph();
        graph.getGraph().add(source);
        graph.getGraph().addAll(aBags);
        graph.getGraph().addAll(otherBags);
        graph.getGraph().addAll(greenTrainArray);
        graph.getGraph().addAll(redTrainArray);
        graph.getGraph().addAll(greenReindeerArray);
        graph.getGraph().addAll(redReindeerArray);
        graph.getGraph().add(sink);
        int output =totalBag-graph.Dinic(source,sink);
        out.println(output);
    }
}
