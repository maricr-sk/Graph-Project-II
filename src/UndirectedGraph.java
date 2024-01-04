import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class UndirectedGraph {
    private int numNodes;
    private HashMap<String, Integer> map;
    private static int edgeCount = 0;
    private static int order = 0;

    private Integer[][] dijk;
    private boolean[][] am;
    private Integer[][] amMatrix;


    private String root;
    private PriorityQueue<String> unvisited;
    private PriorityQueue<String> visited;

    public UndirectedGraph(int numNodes){
        this.numNodes = numNodes;
        this.am = new boolean[numNodes][numNodes];
        adjacencyMatrix();
        map = new HashMap<String, Integer>();
    }

    public boolean contains(String a){
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String b = it.next();
            if(b.equals(a)){
                return true;
            }
        }
        return false;
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
    //add node to Map with its name as key and next avaliable index as val

    /*public void addEdge(String a, String b, Integer c) {
        // get node vals from the map, find matrix[][] and set it to c
        am.add(map.get(a), map.get(b), c);
        am.add(map.get(b), map.get(a), c);
        ++edgeCount;
    }*/

    public void addEdge(String a, String b){
        addA(map.get(a),map.get(b));
        addA(map.get(b),map.get(a));
        ++edgeCount;
    }

    public int getOrder(){return order;}
    public int getSize(){return edgeCount;}

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

    //public boolean hasEdge(String a, String b){ return check(map.get(a), map.get(b)); }
    public String getMatrix(){ return isMatrix(); }

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

    /*public void getDistance(String root){
        AdjacencyMatrix dijk = new AdjacencyMatrix(getOrder());
        unvisited = new PriorityQueue<>();
        visited = new PriorityQueue<>();
        Iterator<String> et = map.keySet().iterator();

        String h = "";
        while(et.hasNext()){
            String l = et.next();
            if(map.get(l) == 0){
                h = l;
                unvisited.add(l);
            }
        } // loading the first node
        Iterator<String> mt = map.keySet().iterator();
        while(mt.hasNext()){
            String l = mt.next();
            if(!l.equals(h)){
                unvisited.add(l);
            }
        } //loading rest of nodes

        System.out.println(unvisited);
        //loading the unvisited nodes

        Map<String, String> prevNodes = new HashMap<>();

        while(!unvisited.isEmpty()) { //doing dijkstras
            Integer[] nodes = am.eedges(getOrder(), map.get(unvisited.peek()));
            //existing edges in an array
            for (int i = 0; i < nodes.length; ++i) {
                if (nodes[i] == -1) {
                    nodes[i] = Integer.MAX_VALUE;
                } //setting the nonexistent values to infin
                if (i == map.get(unvisited.peek())) { // distance from start v to start v = 0
                    nodes[i] = 0;
                }
            }
            dijk.add(map.get(unvisited.peek()), getOrder(), nodes); //loading the nodes with an updated array


            Integer currentV = Integer.MAX_VALUE;
            int c = -1;
            for(int i = 0; i < nodes.length; ++i){
                if(nodes[i] < currentV){
                    currentV = nodes[i];
                    c = i;
                }
            } //getting the node with the shortest distance from the node thats the head of the pq

            String nodename = "";
            Iterator<String> ot = map.keySet().iterator();
            while(ot.hasNext()){
                String hold = ot.next();
                if(map.get(hold) == c){
                    nodename = hold;
                }
            } //iterating through map to see which matches the node

            for (String neighbor : unvisited) { //for each  unvisited neighbor
                if (hasEdge(nodename, neighbor)) { //going through neighbors
                    Integer distance = getEdge(unvisited.peek(), unvisited.peek()) + getEdge(nodename, neighbor); //gets distance
                    System.out.println(distance);
                    if(distance < nodes[map.get(neighbor)]){ //if the current node + distance < current distance
                        nodes[map.get(neighbor)] = distance; //set current distance to that value
                        prevNodes.put(neighbor, nodename); //set the previous node to that value
                    }
                }
            }
            visited.add(nodename);
            unvisited.remove(nodename);
        }

        String[] array = new String[getOrder()];
        int count = 0; //order of dijkstras nodes

        Iterator<String> pqit = visited.iterator();
        while(pqit.hasNext()) {
            array[count] = pqit.next();
            ++count;
        } // cannot access next index in pq, so needed to load into array

        Integer distance = 0;
        for(int i = 0; i < array.length -1; ++i){
            distance += getEdge(array[i], array[i+1]); //adding to the final distance the edges between the nodes in visited
            System.out.println(array[i] + " " + array[i+1]);
            System.out.println(am.get(map.get(array[i]), map.get(array[i+1])));
        }


        //NEED TO LOAD ARRAY BC ABOVE ISNT UPDATED, THEN ADD VALUES

        //return distance;

        //if calculated is less than known distance, update them
        //write last vertex, then add the node to the visited pq
        //next node is the next node w the smallest distance
        //have to check the neighbors, check
        //if there is another shorter method by going through
        //those nodes
        // have to add current distance with next one, see if its smaller
        //than what is written
        //using the prevous vistied vertex, you get the number that is held there
        // and add the new distance - -also keeps track of
        //fastest route
        //when it is done, add the last node that is in the unvisited
        //pq to the visited pq

    }*/

    public Set<String> toSet(String s){
        String [] strings = s.split(" ");
        Set<String> set = new HashSet<>();
        for(String b : strings){
            set.add(b);
        }
        return set;
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
        System.out.println();
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

    public void adjacencyMatrix(){
        for(int i = 0; i < numNodes; ++i){
            for(int j = 0; j < numNodes; ++j){
                am[i][j] = false;
            }
        }
    }

    public void addA(Integer one, Integer two){
        am[one][two] = true;
        am[two][one] = true;
    }

    public String isMatrix(){
        String answer = "[ ";

        for(int i = 0; i< numNodes; ++i){
            answer += " [";
            for(int v = 0; v < numNodes; ++v){
                answer += am[i][v];
                if(v != numNodes-1){ answer += ", "; }
            }
            answer += " ]\n";
        }
        answer += " ]" ;

        return answer;
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

    public Integer[] eedges(Integer num, Integer node){
        Integer [] answer = new Integer[num];
        for(int i = 0; i < num; ++i){
            answer[i] = amMatrix[node][i];
        }
        return answer;
    }

    public boolean get(int a, int b){
        return am[a][b];
    }

    public Integer getA(int a, int b){
        return dijk[a][b];
    }

    public void add(Integer t, Integer gO, Integer [] array){
        for(int i = 0; i < array.length; ++i){
            dijk[t][i] = array[i];
        }
    }
    //-1 when theres no edge, when there is one put the specific weight

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File(args[0]));
        Integer finalNum = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < finalNum; ++i) {
            int numNodes = Integer.parseInt(scan.nextLine());
            UndirectedGraph graph = new UndirectedGraph(numNodes);
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

