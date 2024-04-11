package org.example.domain;

import org.example.entity.Account;
import org.example.util.ApplicationProperties;

import java.util.Properties;
import java.util.UUID;

public class AccountRepository {
    private static final Properties appProps = ApplicationProperties.getAppProps();
    public static int getRandomDelay() {
        int minDelay = Integer.parseInt(appProps.getProperty("min_delay"));
        int maxDelay = Integer.parseInt(appProps.getProperty("max_delay"));
        return minDelay + (int) (Math.random() * (maxDelay - minDelay + 1));
    }

    public static int getRandomAmount() {
        int minAmount = Integer.parseInt(appProps.getProperty("min_amount"));
        int maxAmount = Integer.parseInt(appProps.getProperty("max_amount"));
        return minAmount + (int) (Math.random() * (maxAmount - minAmount + 1));
    }

//    public static Integer getRandomDelay() {
//        return Integer.parseInt(appProps.getProperty("min_delay")) + (int) (Math.random()
//                * (Integer.parseInt(appProps.getProperty("max_delay"))
//                - Integer.parseInt(appProps.getProperty("min_delay"))) + 1);
//    }
//
//    public static Integer getRandomAmount() {
//        return Integer.parseInt(appProps.getProperty("min_amount")) + (int) (Math.random()
//                * (Integer.parseInt(appProps.getProperty("max_amount"))
//                - Integer.parseInt(appProps.getProperty("min_amount"))) + 1);
//    }

    public static int[] getRandomPairByLimit(int limit) {
        int[] result = new int[2];
        int first = (int) (Math.random() * limit);
        int second = (int) (Math.random() * limit);
        if (first == second) {
            result = getRandomPairByLimit(limit);
        } else {
            result[0] = first;
            result[1] = second;
        }
        return result;
    }

    public static String randomId() {
        return UUID.randomUUID().toString();
    }

    public static Integer getStartAmount() {
        return Integer.parseInt(appProps.getProperty("start_amount"));
    }
}
