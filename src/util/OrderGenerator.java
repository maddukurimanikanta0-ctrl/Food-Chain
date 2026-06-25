package util;

import java.util.Random;

public class OrderGenerator {

    public static String generateOrderId() {

        Random random =
                new Random();

        return "ORD" +
                (10000 +
                 random.nextInt(90000));
    }
}