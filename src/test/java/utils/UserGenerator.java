package utils;

import com.github.javafaker.Faker;
import java.util.Random;

public class UserGenerator {
    private final Faker faker = new Faker();
    private final String[] genders = {"male", "female"};

    public UserPojo generateUserData() {
        return UserPojo.builder()
                .email(faker.internet().emailAddress())
                .name(faker.name().fullName())
                .gender(genders[new Random().nextInt(genders.length)])
                .status("active")
                .build();
    }
}
