import java.util.*;
public class House {

    private String owner;
    private int windows;

    public House(String owner, int windows) {
        this.owner = owner;
        this.windows = windows;
    }

    public String getOwner() {
        return this.owner;
    }

    public int getWindows() {
        return this.windows;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    @Override
    public String toString() {
        return "Owner: " + this.owner + "\nWindows: " + this.windows;
    }

    public static void main(String[] args) {
        House h1 = new House("Marissa", 64);
        House h2 = new House("Sarkar", 36);
        House h3 = new House("Amy", 14);
        House h4 = new House("Boom", 9);
        House h5 = new House("Aaaaa", 0);

        ArrayList<House> houses = new ArrayList<>();

        houses.add(h1);
        houses.add(h2);
        houses.add(h3);
        houses.add(h4);
        houses.add(h5);
        System.out.println(houses);

        for (int i = 0; i < houses.size(); ++i) {
            if (houses.get(i).getWindows() % 2 != 0) {
                houses.get(i).setWindows(houses.get(i).getWindows() + 1);
            }
        }


    }
    // public Deck () {
    //      this(52);
    // }
    //public Deck(int n) {
    //if (num is not within defined range (greater than 52 or less than 0)){
    //  System.out.println(...);
    // }
    //else {
    //  for(int i = 0; i < n; ++i){
    //      int rank = (int)( (Math.random()*52) + 1)
    //      int suit = ...
    //      while(num is in ranks array){
    //          ranks = ...
    //      }
    //      while (num is in suits array ){
    //          suits = ...
    //      }
    //      Cards card = new Card(ranks, suits);
    //       list.add(card);
    //   }
    // }



}
