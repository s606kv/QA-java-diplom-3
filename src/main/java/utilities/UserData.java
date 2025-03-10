package utilities;

import net.datafaker.Faker;

public class UserData {
    // сгенерированы данные пользователя
    private static Faker faker = new Faker();

    public static final String TEST_USER_EMAIL = faker.internet().emailAddress();
    public static final String TEST_USER_PASSWORD = faker.lorem().characters(6, 6, false, false, true);
    public static final String TEST_USER_NAME = faker.name().username();
}
