// Эти тесты необходимо выполнять на устройстве с Android 10 (API 29)

package ru.iteco.fhmandroid.ui.test;

import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fhmandroid.ui.custom.BaseAndroidTest;
import ru.iteco.fhmandroid.ui.steps.AboutScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;


public class AboutScreenTest extends BaseAndroidTest {

    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    AboutScreenSteps aboutScreenSteps = new AboutScreenSteps();

    @Test
    @DisplayName("Кейс 6.1.1 \"Переход по ссылке на Политику конфиденциальности из раздела О приложении\"")
    public void shouldFollowTheLinkToPrivacyPolicy() {
        mainScreenSteps.goToSectionAboutFromMainMenu();
        aboutScreenSteps.checkAboutScreenIsDisplayed();
        aboutScreenSteps.checkPrivacyPolicyLinkIsClickable();
    }

    @Test
    @DisplayName("Кейс 6.1.2 \"Переход по ссылке на Пользовательское соглашение из раздела О приложении\"")
    public void shouldFollowTheLinkToTermsOfUse() {
        mainScreenSteps.goToSectionAboutFromMainMenu();
        aboutScreenSteps.checkAboutScreenIsDisplayed();
        aboutScreenSteps.checkTermsOfUseLinkIsClickable();
    }

    @Test
    @DisplayName("Кейс 6.1.3 \"Переход к разделу Главная из раздела О приложении через стрелку Назад\"")
    public void shouldGoToSectionMainFromSectionAbout() {
        mainScreenSteps.goToSectionAboutFromMainMenu();
        aboutScreenSteps.checkAboutScreenIsDisplayed();

        aboutScreenSteps.clickOnBackButton();
        mainScreenSteps.checkMainScreenIsDisplayed();
    }
}
