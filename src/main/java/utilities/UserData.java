package utilities;

import net.datafaker.Faker;

public class UserData {
    private static Faker faker = new Faker();

    public static final String TEST_EMAIL = "ludgi@yandex.ru";
    public static final String TEST_PASSWORD = faker.lorem().characters(6, 6, false, false, true);
    public static final String TEST_NAME = faker.name().username();
}
