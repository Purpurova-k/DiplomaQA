package ru.iteco.fhmandroid.ui.test;

import static ru.iteco.fhmandroid.ui.data.DataHelper.snackEmptyLoginOrPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackWrongLoginOrPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.validLoginAndPassword;

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
public class AuthorizationScreenTest {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();

    @Before
    public void checkLogout() {
        SystemClock.sleep(7000);
        try {
            authorizationScreenSteps.checkAuthorizationScreenIsDisplayed();
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
    public void shouldLoginWithValidData() {
        authorizationScreenSteps.validLoginAndPasswordAuthorization(validLoginAndPassword());
        mainScreenSteps.checkLogoImageIsDisplayed();
    }

    // Кейс 1.1.2 "Авторизация незарегистрированного пользователя"
    @Test
    public void shouldNotLoginWithInvalidLogin() {
        authorizationScreenSteps.invalidLoginAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackWrongLoginOrPassword);
    }

    // Кейс 1.1.3 "Авторизация пользователя с невалидным паролем"
    @Test
    public void shouldNotLoginWithInvalidPassword() {
        authorizationScreenSteps.invalidPasswordAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackWrongLoginOrPassword);
    }

    // Кейс 1.2.1 "Авторизация пользователя при введеном пробеле в поле логин"
    @Test
    public void shouldLoginWithLoginWithWhitespace() {
        authorizationScreenSteps.loginWithWhitespaceAuthorization();
        mainScreenSteps.checkLogoImageIsDisplayed();
    }

    // Кейс 1.2.2 "Авторизация пользователя при введеном пробеле в поле пароль"
    @Test
    public void shouldNotLoginWithPasswordWithWhitespace() {
        authorizationScreenSteps.passwordWithWhitespaceAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackWrongLoginOrPassword);
    }

    // Кейс 1.2.3 "Авторизация пользователя с пустым полем логин"
    @Test
    public void shouldNotLoginWithEmptyLoginField() {
        authorizationScreenSteps.emptyLoginFieldAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackEmptyLoginOrPassword);
    }

    // Кейс 1.2.4 "Авторизация пользователя с пустым полем пароль"
    @Test
    public void shouldNotLoginWithEmptyPasswordField() {
        authorizationScreenSteps.emptyPasswordFieldAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackEmptyLoginOrPassword);
    }
}
