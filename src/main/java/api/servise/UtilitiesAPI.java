package api.servise;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static utilities.Links.MAIN_PAGE_URL;

public class UtilitiesAPI {
    public static final RequestSpecification REQUEST = given().baseUri(MAIN_PAGE_URL).contentType(ContentType.JSON);

    public static final String USER_CREATE = "api/auth/register";
    public static final String USER_DELETE = "api/auth/user";
    public static final String USER_LOGIN = "api/auth/login";

    // сервисный метод печати информации в зависимости от статус-кода
    public static void printResponseInfo(Response response, int expectedStatusCode, String otherInfo) {
        // формируем тело ответа
        String responseBody = response
                .then()
                .extract()
                .body()
                .asString();
        // получаем статус-код
        int actualStatusCode = response.getStatusCode();
        // печатаем результат запроса
        String info = (actualStatusCode == expectedStatusCode)
                ? String.format("\uD83D\uDFE2 Статус-код: %d.%nУспешный запрос.%n%s", actualStatusCode, otherInfo)
                : String.format("\uD83D\uDFE1 ВНИМАНИЕ. Статус: %d.%nТело ответа: %s.%nЗапрос некорректный.%n", actualStatusCode, responseBody);
        System.out.println(info);
    }

    // сервисный метод печати сообщения об успешном assert-е
    public static void checkSuccessAssertTrue(boolean condition) {
        assertTrue(condition);
        if (condition) {
            System.out.println("✅ Проверка прошла успешно.\n");
        } else {
            throw new RuntimeException("⛔\uFE0F Проверка не прошла.\n");
        }
    }
}
