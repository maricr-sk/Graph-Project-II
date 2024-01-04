import java.util.ArrayList;

public class AdjacencyMatrix {
    private Integer[][] amMatrix;
    private Integer maxNum;
    private Integer[][] dijk;
    private boolean[][] amStrings;

    public AdjacencyMatrix(Integer maxNum){
        amMatrix = new Integer[maxNum][maxNum];
        amStrings = new boolean[maxNum][maxNum];
        this.maxNum = maxNum;
        for(int i = 0; i < maxNum; ++i){
            for(int j = 0; j < maxNum; ++j){
                amMatrix[i][j] = -1;
                amStrings[i][j] = false;
            }
        }

    }

    public void add(Integer one, Integer two, Integer three){
        amMatrix[one][two] = three;
    }

    public void add(Integer one, Integer two){
         amStrings[one][two] = true;
        amStrings[two][one] = true;
    }

    public String isMatrix(){
        String answer = "[ ";

        for(int i = 0; i< maxNum; ++i){
            answer += " [";
            for(int v = 0; v < maxNum; ++v){
                answer += amMatrix[i][v];
                if(v != maxNum-1){ answer += ", "; }
            }
            answer += " ]\n";
        }
        answer += " ]" ;

        return answer;
    }

    public int [] search(int one){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int j = 0; j < maxNum; ++j){
            if(amStrings[one][j]){
                arrayList.add(j);
                System.out.println(j);
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

    public boolean check(int i, int j){
        return amMatrix[i][j] != -1;
    }

    public Integer[] eedges(Integer num, Integer node){
        Integer [] answer = new Integer[num];
        for(int i = 0; i < num; ++i){
            answer[i] = amMatrix[node][i];
        }
        return answer;
    }

    public Integer get(int a, int b){
        return amMatrix[a][b];
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
}
