package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static ru.iteco.fhmandroid.ui.data.DataHelper.emptyLogin;
import static ru.iteco.fhmandroid.ui.data.DataHelper.emptyPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.invalidLogin;
import static ru.iteco.fhmandroid.ui.data.DataHelper.invalidPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.loginWithWhitespace;
import static ru.iteco.fhmandroid.ui.data.DataHelper.passwordWithWhitespace;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.AuthorizationScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthorizationScreenSteps {

    AuthorizationScreenElements authorizationScreenElements = new AuthorizationScreenElements();

    // Проверка отображения заголовка экрана Авторизации
    public void checkAuthorizationHeaderIsDisplayed() {
        authorizationScreenElements.getAuthorizationHeader().check(matches(isDisplayed()));
    }

    // Проверка отображения полей Логин и Пароль
    public void checkLoginAndPasswordFieldsAreDisplayed() {
        authorizationScreenElements.getLoginField().check(matches(isDisplayed()));
        authorizationScreenElements.getPasswordField().check(matches(isDisplayed()));
    }

    // Авторизация пользователя с валидным логином и паролем
    public void validLoginAndPasswordAuthorization(DataHelper.AuthInfo info) {
        authorizationScreenElements.getLoginField().perform(typeText(info.getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(info.getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationScreenElements.getLoginButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Авторизация пользователя с невалидным логином
    public void invalidLoginAuthorization() {
        authorizationScreenElements.getLoginField().perform(typeText(invalidLogin().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(invalidLogin().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationScreenElements.getLoginButton().perform(click());
    }

    // Авторизация пользователя с невалидным паролем
    public void invalidPasswordAuthorization() {
        authorizationScreenElements.getLoginField().perform(typeText(invalidPassword().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(invalidPassword().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationScreenElements.getLoginButton().perform(click());
    }

    // Авторизация пользователя с пробелом в поле логин
    public void loginWithWhitespaceAuthorization() {
        authorizationScreenElements.getLoginField().perform(typeText(loginWithWhitespace().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(loginWithWhitespace().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationScreenElements.getLoginButton().perform(click());
    }

    // Авторизация пользователя с пробелом в поле пароль
    public void passwordWithWhitespaceAuthorization() {
        authorizationScreenElements.getLoginField().perform(typeText(passwordWithWhitespace().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(passwordWithWhitespace().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationScreenElements.getLoginButton().perform(click());
    }

    // Авторизация пользователя с пустым полем логин
    public void emptyLoginFieldAuthorization() {
        authorizationScreenElements.getLoginField().perform(typeText(emptyLogin().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(emptyLogin().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationScreenElements.getLoginButton().perform(click());
    }

    // Авторизация пользователя с пустым полем пароль
    public void emptyPasswordFieldAuthorization() {
        authorizationScreenElements.getLoginField().perform(typeText(emptyPassword().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(emptyPassword().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationScreenElements.getLoginButton().perform(click());
    }

    // Проверка появления снэка с ошибкой

    public void checkSnackIsDisplayed(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

//    public void checkSnackIsDisplayed(String text) {
//        onView(withText(text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//        SystemClock.sleep(3000);
//    }

//        public void checkSnackWrongLoginOrPasswordIsDisplayed() {
//            authorizationScreenElements.getSnackWrongLoginOrPassword().check(matches(withText("Неверный логин или пароль")));
//            SystemClock.sleep(3000);
//    }

//    public void checkSnackWrongLoginOrPasswordIsDisplayed() {
//         onView(withId(R.string.wrong_login_or_password)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//            SystemClock.sleep(3000);
//    }
}
