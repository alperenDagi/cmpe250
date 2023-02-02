import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class project3main {
    public static void main(String[] args) throws IOException {

        Locale.setDefault(Locale.US);
        FileReader file = new FileReader(args[0]);
        PrintStream out = new PrintStream(args[1]);
        BufferedReader reader = new BufferedReader(file);
        int timeLimit = Integer.parseInt(reader.readLine());
        int cityCount = Integer.parseInt(reader.readLine());
        String[] mecnunLeyla = reader.readLine().split(" ");
        String mecnunLocation = mecnunLeyla[0];
        String leylaLocation = mecnunLeyla[1];
        Graph marriageGraph = new Graph();
        HashMap<String, String> djikstraInput = new HashMap<>();
        HashMap<String, String> kruskalInput = new HashMap<>();
        Graph honeymoonGraph = new Graph();
        for (int j = 0; j < cityCount; j++) {
            String line = reader.readLine();
            String[] data = line.split(" ", 2);
            if (data[0].charAt(0) == 99) {
                if(data[0].equals(leylaLocation)){
                    honeymoonGraph.addNode(new Node(data[0]));
                }
                marriageGraph.addNode(new Node(data[0]));
                if (!data[0].equals(leylaLocation) && data.length != 1) {
                    djikstraInput.put(data[0], data[1]);
                } else if (data[0].equals(leylaLocation)) {
                    kruskalInput.put(data[0], data[1]);
                }
            } else if (data[0].charAt(0) == 100) {
                honeymoonGraph.addNode(new Node(data[0]));
//                System.out.println(data[0]);
                if (data.length != 1) {
                    kruskalInput.put(data[0], data[1]);
                }
            }
        }
        for (String key : djikstraInput.keySet()) {
            String links = djikstraInput.get(key);
            String[] data = links.split(" ");
            if(data.length ==1){
                continue;
            }
            for (int i = 0; i < data.length; i += 2) {
                marriageGraph.getNodes().get(key).addDestination(marriageGraph.getNodes().get(data[i]), Integer.parseInt(data[i + 1]));
            }
        }
        djikstraInput.clear();
        for (String key : kruskalInput.keySet()) {
            String links = kruskalInput.get(key);
            String data[] = links.split(" ");
            if(data.length ==1){
                continue;
            }
            for (int i = 0; i < data.length; i += 2) {
                if(data[i].charAt(0) ==100){
                    honeymoonGraph.getNodes().get(key).addDestination(honeymoonGraph.getNodes().get(data[i]), Integer.parseInt(data[i + 1]));
                }
            }
        }
        Dijkstra dijkstra = new Dijkstra(marriageGraph.getNodes().size(),out);
        dijkstra.dijkstra(marriageGraph, marriageGraph.getNodes().get(mecnunLocation), leylaLocation);
        honeymoonGraph.toUndirected();
        MST mst = new MST(honeymoonGraph.getNodes().size());
        int cost =mst.mst(honeymoonGraph,honeymoonGraph.getNodes().get(leylaLocation));
        if(marriageGraph.getNodes().get(leylaLocation).getDistance() > timeLimit){
            out.print(-1);
        }else{
            out.print(cost);
        }
    }
}

