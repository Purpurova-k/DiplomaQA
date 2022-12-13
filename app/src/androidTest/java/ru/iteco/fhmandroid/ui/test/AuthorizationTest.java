package ru.iteco.fhmandroid.ui.test;

import static ru.iteco.fhmandroid.ui.data.DataHelper.snackEmpty;
import static ru.iteco.fhmandroid.ui.data.DataHelper.validLoginAndPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackWrong;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;

@RunWith(AndroidJUnit4.class)
public class AuthorizationTest {
    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();

    @Before
    public void checkLogout() {
        SystemClock.sleep(7000);
        try {
            authorizationScreenSteps.checkAuthorizationHeaderIsDisplayed();
            authorizationScreenSteps.checkLoginAndPasswordFieldsAreDisplayed();
        } catch (NoMatchingViewException e) {
            mainScreenSteps.logout();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    // Кейс 1.1.1 "Авторизация пользователя с валидными данными"
    @Test
    public void authWithValidData() {
        authorizationScreenSteps.validLoginAndPasswordAuthorization(validLoginAndPassword());
        mainScreenSteps.checkLogoImageIsDisplayed();
    }

    // Кейс 1.1.2 "Авторизация незарегистрированного пользователя"
    @Test
    public void authWithInvalidLogin() {
        authorizationScreenSteps.invalidLoginAuthorization();
        authorizationScreenSteps.checkSnackIsDisplayed(snackWrong);
    }

    // Кейс 1.1.3 "Авторизация пользователя с невалидным паролем"
    @Test
    public void authWithInvalidPassword() {
        authorizationScreenSteps.invalidPasswordAuthorization();
        authorizationScreenSteps.checkSnackIsDisplayed(snackWrong);
    }

    // Кейс 1.2.1 "Авторизация пользователя при введеном пробеле в поле логин"
    @Test
    public void authWithLoginWithWhitespace() {
        authorizationScreenSteps.loginWithWhitespaceAuthorization();
        mainScreenSteps.checkLogoImageIsDisplayed();
    }

    // Кейс 1.2.2 "Авторизация пользователя при введеном пробеле в поле пароль"
    @Test
    public void authWithPasswordWithWhitespace() {
        authorizationScreenSteps.passwordWithWhitespaceAuthorization();
        authorizationScreenSteps.checkSnackIsDisplayed(snackWrong);
    }

    // Кейс 1.2.3 "Авторизация пользователя с пустым полем логин"
    @Test
    public void authWithEmptyLoginField() {
        authorizationScreenSteps.emptyLoginFieldAuthorization();
        authorizationScreenSteps.checkSnackIsDisplayed(snackEmpty);
    }

    // Кейс 1.2.4 "Авторизация пользователя с пустым полем пароль"
    @Test
    public void authWithEmptyPasswordField() {
        authorizationScreenSteps.emptyPasswordFieldAuthorization();
        authorizationScreenSteps.checkSnackIsDisplayed(snackEmpty);
    }
}
