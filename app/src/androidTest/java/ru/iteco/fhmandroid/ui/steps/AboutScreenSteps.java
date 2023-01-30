package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import ru.iteco.fhmandroid.ui.elements.AboutScreenElements;

public class AboutScreenSteps {

    AboutScreenElements aboutScreenElements = new AboutScreenElements();

    // Проверка отображения экрана О приложении
    public void checkAboutScreenIsDisplayed() {
        aboutScreenElements.getVersion().check(matches(isDisplayed()));
        aboutScreenElements.getNumberVersion().check(matches(isDisplayed()));
        SystemClock.sleep(2000);
    }

    // Проверка кликабельности ссылки Политика конфиденциальности
    public void checkPrivacyPolicyLinkIsClickable() {
        aboutScreenElements.getPrivacyPolicyLink().check(matches(isClickable()));
        SystemClock.sleep(2000);
    }

    // Проверка кликабельности ссылки Пользовательское соглашение
    public void checkTermsOfUseLinkIsClickable() {
        aboutScreenElements.getTermsOfUseLink().check(matches(isClickable()));
        SystemClock.sleep(2000);
    }

    // Нажатие на кнопку Назад
    public void clickOnBackButton() {
        aboutScreenElements.getBackButton().perform(click());
        SystemClock.sleep(2000);
    }
}
