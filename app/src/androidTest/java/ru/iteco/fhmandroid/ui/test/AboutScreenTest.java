package ru.iteco.fhmandroid.ui.test;

import org.junit.Test;

import ru.iteco.fhmandroid.ui.custom.BaseAndroidTest;
import ru.iteco.fhmandroid.ui.steps.AboutScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;

public class AboutScreenTest extends BaseAndroidTest {

    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    AboutScreenSteps aboutScreenSteps = new AboutScreenSteps();

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
