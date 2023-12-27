package util;

public class Random {
    public static int generateRandomNumber(long min, long max) {

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return randomInt;
    }
}