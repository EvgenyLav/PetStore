package utils;

import com.github.javafaker.Faker;
import models.Order;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomTestData {
    private static Random random = new Random();

    private static Faker faker = new Faker();

    public static Order getRandomOrder(){
        List<String> statusList = Arrays.asList("placed", "approved", "delivered");
        String randomStatus = statusList.get(random.nextInt(statusList.size()));

        return Order.builder()
                .id(faker.random().nextInt(1,9999))
                .petId(faker.random().nextInt(1,9999))
                .quantity(faker.random().nextInt(1,9999))
                .shipDate(LocalDateTime.now().toString() +"+0000")
                .status(randomStatus)
                .complete(faker.random().nextBoolean())
                .build();

    }

}
