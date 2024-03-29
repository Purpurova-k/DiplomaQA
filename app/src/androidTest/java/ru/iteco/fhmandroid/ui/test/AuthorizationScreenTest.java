// Эти тесты необходимо выполнять на устройстве с Android 10 (API 29)

package ru.iteco.fhmandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackEmptyLoginOrPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackWrongLoginOrPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.validLoginAndPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationScreenTest {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();


    @Before
    public void checkLogout() {
        onView(isRoot()).perform(waitUntilShown(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            authorizationScreenSteps.checkAuthorizationScreenIsDisplayed();
            authorizationScreenSteps.checkLoginAndPasswordFieldsAreDisplayed();
        } catch (NoMatchingViewException e) {
            mainScreenSteps.logout();
        }
    }

    @Test
    @DisplayName("Кейс 1.1.1 \"Авторизация пользователя с валидными данными\"")
    public void shouldLoginWithValidData() {
        authorizationScreenSteps.validLoginAndPasswordAuthorization(validLoginAndPassword());
        mainScreenSteps.checkMainScreenIsDisplayed();
    }

    @Test
    @DisplayName("Кейс 1.1.2 \"Авторизация незарегистрированного пользователя\"")
    public void shouldNotLoginWithInvalidLogin() {
        authorizationScreenSteps.invalidLoginAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackWrongLoginOrPassword);
    }

    @Test
    @DisplayName("Кейс 1.1.3 \"Авторизация пользователя с невалидным паролем\"")
    public void shouldNotLoginWithInvalidPassword() {
        authorizationScreenSteps.invalidPasswordAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackWrongLoginOrPassword);
    }

    @Test
    @DisplayName("Кейс 1.2.1 \"Авторизация пользователя при введеном пробеле в поле логин\"")
    public void shouldLoginWithLoginWithWhitespace() {
        authorizationScreenSteps.loginWithWhitespaceAuthorization();
        mainScreenSteps.checkMainScreenIsDisplayed();
    }

    @Test
    @DisplayName("Кейс 1.2.2 \"Авторизация пользователя при введеном пробеле в поле пароль\"")
    public void shouldNotLoginWithPasswordWithWhitespace() {
        authorizationScreenSteps.passwordWithWhitespaceAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackWrongLoginOrPassword);
    }

    @Test
    @DisplayName("Кейс 1.2.3 \"Авторизация пользователя с пустым полем логин\"")
    public void shouldNotLoginWithEmptyLoginField() {
        authorizationScreenSteps.emptyLoginFieldAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackEmptyLoginOrPassword);
    }

    @Test
    @DisplayName("Кейс 1.2.4 \"Авторизация пользователя с пустым полем пароль\"")
    public void shouldNotLoginWithEmptyPasswordField() {
        authorizationScreenSteps.emptyPasswordFieldAuthorization();
        authorizationScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackEmptyLoginOrPassword);
    }
}
