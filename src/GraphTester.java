import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GraphTester {

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        Scanner scan = new Scanner(file);
        Integer finalNum = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < finalNum; ++i) {
            int numNodes = Integer.parseInt(scan.nextLine());
            UndirectedGraph graph = new UndirectedGraph(numNodes);
            int numEdges = Integer.parseInt(scan.nextLine());

            for (int f = 0; f < numEdges; ++f) {
                String[] next = scan.nextLine().split(" ");
                Set<String> nodes = graph.getNodes();

                if (!nodes.contains(next[0])) {
                    graph.addNode(next[0]);
                }
                if (!nodes.contains(next[1])) {
                    graph.addNode(next[1]);
                }
                graph.addEdge(next[0], next[1]);
            }

        }
    }

        /*
        graph.addNode("Boston"); //A
        graph.addNode("Houston"); //B
        graph.addNode("Las Vegas"); //C
        graph.addNode("Miami"); //D
        graph.addNode("New York City"); //E
        graph.addNode("Salt Lake City"); //F
        graph.addNode("Chicago"); //G
        graph.addNode("Los Angeles"); //H



        graph.addEdge("Chicago", "Houston", 8);
        graph.addEdge("Chicago", "Las Vegas", 2);
        graph.addEdge("Chicago", "Miami", 5);

        graph.addEdge("Houston", "Miami", 2);
        graph.addEdge("Houston", "Salt Lake City", 13);

        graph.addEdge("Las Vegas", "New York City", 5);
        graph.addEdge("Las Vegas", "Miami", 2);

        graph.addEdge("Miami", "New York City", 1);
        graph.addEdge("Miami", "Boston", 3);
        graph.addEdge("Miami", "Salt Lake City", 6);

        graph.addEdge("New York City", "Boston", 1);

        graph.addEdge("Salt Lake City", "Boston", 2);
        graph.addEdge("Salt Lake City", "Los Angeles", 3);

        graph.addEdge("Boston", "Los Angeles", 5);

        graph.getDistance("Boston"); // should print 11

        graph.addEdge("Chicago", "Houston", 17);
        graph.addEdge("Chicago", "New York City", 17);
        graph.addEdge("Chicago", "Las Vegas", 3);
        graph.addEdge("Las Vegas", "New York City", 9);
        graph.addEdge("Las Vegas", "Miami", 4);
        graph.addEdge("Las Vegas", "Houston", 8);



        System.out.println("# of nodes is: " + graph.getOrder());	// should print  5
        System.out.println("# of edges is: "  + graph.getSize());      // should print   6

        System.out.println(graph.getEdges("New York City"));        // should print  Las Vegas    Chicago

        System.out.println(graph.hasEdge("New York City",  "Miami"));     // should print false
        System.out.println(graph.hasEdge("Houston",  "Las Vegas"));     // should print true

        System.out.println( graph.getMatrix()  );      // should print out the  adjacency matrix

        System.out.println(   graph   );           // should  print out each node  and its edges */
}