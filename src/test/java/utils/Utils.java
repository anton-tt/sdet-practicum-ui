package utils;

import java.util.*;

public class Utils {
    private Utils() {
    }

    private static final Random random = new Random();

    public static int getRandomQuantity(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static List<Integer> getRandomUniqueNumbers(int min, int max, int count) {
        Set<Integer> result = new LinkedHashSet<>();
        while (result.size() < count) {
            int num = random.nextInt(max - min + 1) + min;
            result.add(num);
        }
        return new ArrayList<>(result);
    }

}
