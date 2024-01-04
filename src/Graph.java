import java.io.File;
import java.util.*;

public class Graph {
    private int numNodes;
    private HashMap<String, Integer> map;
    private static int order = 0;
    private static int edgeCount = 0;

    private boolean[][] am;
    private Integer[][] amMatrix;


    private String root;

    public Graph(int numNodes){
        this.numNodes = numNodes;
        this.am = new boolean[numNodes][numNodes];
        adjacencyMatrix();
        map = new HashMap<String, Integer>();
    }

    public void adjacencyMatrix(){
        for(int i = 0; i < numNodes; ++i){
            for(int j = 0; j < numNodes; ++j){
                am[i][j] = false;
            }
        }
    }

    public Set<String> getNodes(){
        Set<String> set = new HashSet<String>(map.keySet());
        return set;
    }

    public void addNode(String a){
        map.put(a, order);
        if(order == 0) root = a;
        ++order;
    }

    public void addEdge(String a, String b){
        addA(map.get(a),map.get(b));
        addA(map.get(b),map.get(a));
        ++edgeCount;
    }

    public String getEdges(String a){
        Integer one = map.get(a); //getting the nodes corresponding order
        int [] search = search(one); //an array of the corresponding pairs

        Set<String> set = map.keySet(); //names of all the nodes
        String answer = "";

        Iterator<String> it = set.iterator(); //an iterator for the names of the nodes

        for(int f = 0; f < map.size(); ++f) {
            String s = it.next();
            for(int i = 0; i < search.length; i++){
                if (map.get(s) == search[i]) {
                    answer += s + " ";
                }
            }
        }
        return answer;
    }

    public int getNumEdges(String a){
        String [] hold = getEdges(a).split(" ");
        return hold.length;
    }

    public String toString(){
        String c = "";
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String b = it.next();
            c += b + ": " +
                    getEdges(b) + "\n   ";
        }
        return c;
    }

    public String BFS(String a) {
        String answer = "";
        Set<String> visited = new HashSet<>();
        Queue<String> working = new LinkedList<String>();
        String wN = a;
        working.add(wN);

        while (!working.isEmpty()){
            String [] hold = getEdges(wN).split(" ");
            for(int f = 0; f < getNumEdges(wN); ++f){
                if (!visited.contains(hold[f])) {
                    working.add(hold[f]);
                }
            }
            if(!answer.contains(wN)) {
                visited.add(wN);
                answer += wN;
            }
            wN = working.poll();
        }
        return answer;
    }

    public String DFS(String a) {
        Set<String> visited = new HashSet<String>();
        Stack<String> working = new Stack<String>();
        String answer = "";

        working.push(a);

        while (!working.isEmpty()) {
            String b = working.peek();
            if (!visited.contains(b)) {
                answer += b;
                visited.add(b);
            }

            String [] hold = getEdges(b).split(" ");
            List<String> arList = Arrays.asList( hold );
            Collections.sort(arList);
            Set<String> eedges = new HashSet<String>(arList);

            if (visited.containsAll(eedges)) {
                while (!working.isEmpty()) {
                    String [] hold2 = getEdges(working.peek()).split(" ");
                    Set<String> newL = new HashSet<String>( Arrays.asList(hold2));
                    if (!visited.contains(working.peek()) || !visited.containsAll(newL)) {
                        break;
                    }
                    working.pop();
                }
            }
            else {
                String[] array = arList.toArray( new String[arList.size()] );
                for (int i = array.length - 1; i >= 0; i--) {
                    if (!visited.contains(array[i])) {
                        working.push(array[i]);
                    }
                }
            }
        }
        return answer;
    }

    public void addA(Integer one, Integer two){
        am[one][two] = true;
        am[two][one] = true;
    }

    public int [] search(int node){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int j = 0; j < numNodes; ++j){
            if(am[node][j]){
                arrayList.add(j);
            }
        }
        int [] total = new int[arrayList.size()];
        int value = 0;
        for(Integer inte : arrayList){
            total[value] = inte;
            ++value;
        }
        return total;
    }


    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File(args[0]));
        Integer finalNum = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < finalNum; ++i) {
            int numNodes = Integer.parseInt(scan.nextLine());
            Graph graph = new Graph(numNodes);
            int numEdges = Integer.parseInt(scan.nextLine());

            for (int f = 0; f < numEdges; ++f) {
                String[] next = scan.nextLine().split(" ");
                Set<String> nodes = graph.getNodes();
                if (!nodes.contains(next[0])) { graph.addNode(next[0]); }
                if (!nodes.contains(next[1])) { graph.addNode(next[1]); }
                graph.addEdge(next[0], next[1]);
            }

            System.out.println(graph.BFS("C"));
            System.out.println(graph.DFS("C"));
        }
    }
}