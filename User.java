package regnos.pancakes;

public class User {

    private String name;
    private int numberOfPancakesEaten;

    public User(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public int getNumberOfPancakesEaten(){
        return numberOfPancakesEaten;
    }

    public void addNumberOfPancakesEaten(int numberOfPancakesEaten){
        int currentNumberOfPancakesEaten = this.numberOfPancakesEaten;
        if(currentNumberOfPancakesEaten<5&&numberOfPancakesEaten>=0
                &&currentNumberOfPancakesEaten+numberOfPancakesEaten<=5) this.numberOfPancakesEaten+=numberOfPancakesEaten;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", numberOfPancakesEaten=" + numberOfPancakesEaten +
                '}';
    }
}
