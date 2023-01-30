package ru.iteco.fhmandroid.ui.test;

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

import ru.iteco.fhmandroid.ui.steps.AboutScreenSteps;
import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class AboutScreenTest {

    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    AboutScreenSteps aboutScreenSteps = new AboutScreenSteps();

    @Before
    public void checkLogin() {
        SystemClock.sleep(8000);
        try {
            mainScreenSteps.checkMainScreenIsDisplayed();
        } catch (NoMatchingViewException e) {
            authorizationScreenSteps.validLoginAndPasswordAuthorization(validLoginAndPassword());
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    // Кейс 6.1.1 "Переход по ссылке на Политику конфиденциальности из раздела О приложении"
    @Test
    public void shouldFollowTheLinkToPrivacyPolicy() {
        mainScreenSteps.goToSectionAboutFromMainMenu();
        aboutScreenSteps.checkAboutScreenIsDisplayed();
        aboutScreenSteps.checkPrivacyPolicyLinkIsClickable();
    }

    // Кейс 6.1.2 "Переход по ссылке на Пользовательское соглашение из раздела О приложении"
    @Test
    public void shouldFollowTheLinkToTermsOfUse() {
        mainScreenSteps.goToSectionAboutFromMainMenu();
        aboutScreenSteps.checkAboutScreenIsDisplayed();
        aboutScreenSteps.checkTermsOfUseLinkIsClickable();
    }

    // Кейс 6.1.3 "Переход к разделу Главная из раздела О приложении через стрелку Назад"
    @Test
    public void shouldGoToSectionMainFromSectionAbout() {
        mainScreenSteps.goToSectionAboutFromMainMenu();
        aboutScreenSteps.checkAboutScreenIsDisplayed();

        aboutScreenSteps.clickOnBackButton();
        mainScreenSteps.checkMainScreenIsDisplayed();
    }
}
