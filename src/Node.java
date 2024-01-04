public class Node {

    String data;
    //int freq;

    @Override public String toString(){
        return data + "";
    }

    public Node(String name){
        this.data = name;
    }

}
