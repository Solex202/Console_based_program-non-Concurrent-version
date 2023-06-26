package regnos.pancakes;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;

import static java.math.BigInteger.ZERO;


public class PancakeApp {
    private static final int BAKERS_MAX_PER_THIRTY_SECONDS = 12;
    private static final int PROGRAM_TIMEOUT = 30;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static LocalDateTime startTime;
    private static int numberOfPancakesCreated;
    private static final User[] users = {
            new User("John"),
            new User("Jane"),
            new User("James")};

    public static void main(String[] args) {
        bakePancakes();
        displayNumberOfPancakesCreated();
        eatPancakes();
        displayAppStats();
    }
    private static void displayAppStats() {
        System.out.println(users[0]+" "+users[1]+" "+ users[2]);
        System.out.println("Number Of Pancakes eaten:: "+ getTotalNumberOfPancakesEaten());
        System.out.println("Number Of Pancakes wasted:: "+ (numberOfPancakesCreated-getTotalNumberOfPancakesEaten()));
        System.out.println(numberOfPancakesCreated>=getTotalNumberOfPancakesEaten()?"Baker Met the users demands":"Baker didn't meet users demands");
    }
    private static void eatPancakes() {
        boolean condition;
        condition=true;
        LocalDateTime endTime=null;
        startTime = LocalDateTime.now();
        System.out.println("Eating start time:: "+startTime);
        int numberOPancakesEaten;
        while(condition){
            var user = users[secureRandom.nextInt(ZERO.intValue(),BigInteger.valueOf(3).intValue())];
            numberOPancakesEaten=secureRandom.nextInt(ZERO.intValue(),BigInteger.valueOf(13).intValue());
            if (getTotalNumberOfPancakesEaten()+numberOPancakesEaten<numberOfPancakesCreated)user.addNumberOfPancakesEaten(numberOPancakesEaten);
            endTime = LocalDateTime.now();
            if (endTime.isEqual(startTime.plusSeconds(PROGRAM_TIMEOUT))
                    ||endTime.isAfter(startTime.plusSeconds(PROGRAM_TIMEOUT))){
                condition=false;
            }
        }

        System.out.println("Eating end time:: "+endTime);
    }

    private static void bakePancakes() {
        startTime= LocalDateTime.now();
        System.out.println("Baking Start Time:: "+startTime);
        boolean condition = true;
        LocalDateTime endTime=null;
        while (condition){
            int gen =secureRandom.nextInt(ZERO.intValue(), BigInteger.valueOf(13).intValue());

            if (numberOfPancakesCreated<BAKERS_MAX_PER_THIRTY_SECONDS &&
                    numberOfPancakesCreated+gen<BAKERS_MAX_PER_THIRTY_SECONDS) {
                numberOfPancakesCreated += gen;
            }
            endTime = LocalDateTime.now();

            if (endTime.isEqual(startTime.plusSeconds(PROGRAM_TIMEOUT))||
                    endTime.isAfter(startTime.plusSeconds(PROGRAM_TIMEOUT))) {
                System.out.println("Baking End Time:: " + endTime);
                condition=false;
            }
        }
    }

    private static void displayNumberOfPancakesCreated(){
        if (numberOfPancakesCreated>= ZERO.intValue()) {
            System.out.println("Pancakes Created:: " + numberOfPancakesCreated);
        }
    }



    private static int getTotalNumberOfPancakesEaten(){
        return Arrays.stream(users).mapToInt(User::getNumberOfPancakesEaten).sum();
    }
}
