package api;

import api.servise.UserJson;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.servise.UtilitiesAPI.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;

public class UserAPI {
    @Step ("POST. Получение ответа на запрос создания пользователя. Ручка api/auth/register.")
    public Response userCreating (UserJson userJson) {
        System.out.println("-> Создаётся пользователь.");

        Response response = REQUEST
                .body(userJson)
                .when()
                .post(USER_CREATE);

        // печатаем информацию о запросе
        printResponseInfo(response, SC_OK, "");

        return response;
    }

    @Step ("Извлечение accessToken после создания пользователя.")
    public String getAccessToken (Response response) {
        System.out.println("-> Получение accessToken.");

        String untrimmedAccessToken = response
                .then()
                .extract()
                .body()
                .path("accessToken")
                .toString();
        String cleanAccessToken = untrimmedAccessToken.substring(7);

        // вывод сообщения в зависимости от исхода запроса
        if(!untrimmedAccessToken.isEmpty()) {
            System.out.println(String.format("\uD83D\uDFE2 accessToken:%n%s%n", cleanAccessToken));
        } else {
            System.out.println("\uD83D\uDFE1 ВНИМАНИЕ. accessToken не получен.\n");
        }

        // проверка наличия accessToken
        assertNotNull(untrimmedAccessToken);

        return cleanAccessToken;
    }

    @Step ("Создание пользователя и извлечение accessToken.")
    public String userCreateAngGetAccessToken (String email, String password, String name) {
        UserJson userJson = new UserJson (email, password, name);
        Response userCreatingResponse = userCreating (userJson);
        String accessToken = getAccessToken(userCreatingResponse);
        return accessToken;
    }

    @Step ("POST. Получение ответа на запрос логина пользователя. Ручка api/auth/login.")
    public Response loginUser (UserJson userJson) {
        System.out.println("-> Выполняется вход пользователя в систему.");

        Response response = REQUEST
                .body(userJson)
                .when()
                .post(USER_LOGIN);

        // печатаем информацию о запросе
        printResponseInfo(response, SC_OK, "");

        return response;
    }

    @Step ("DELETE. Удаления пользователя с проверкой статус-кода и тела ответа. Ручка api/auth/user.")
    public void deleteUser (String accessToken) {
        System.out.println("-> Удаляется пользователь.");

        Response response = REQUEST
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_DELETE);

        // печатаем информацию о запросе
        printResponseInfo(response, SC_ACCEPTED, "");

        // проверка статуса и тела ответа
        response.then()
                .assertThat()
                .statusCode(SC_ACCEPTED)
                .body(
                        "success", equalTo(true),
                        "message", equalTo("User successfully removed")
                );
    }
}
