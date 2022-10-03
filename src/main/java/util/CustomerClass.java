package util;

import com.github.javafaker.Faker;

public class CustomerClass {
    //add faker for methods
    static Faker faker = new Faker();

    public static String[] Customer() {
        String[] details = new String[4];

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName+lastName+"@gmail.com";
        String phone = faker.phoneNumber().phoneNumber();

        details[0] = firstName;
        details[1] = lastName;
        details[2] = email;
        details[3] = phone;

        return details;
    }
}
