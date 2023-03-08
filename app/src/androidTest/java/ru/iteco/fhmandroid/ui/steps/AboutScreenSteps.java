package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fhmandroid.ui.elements.AboutScreenElements;
import ru.iteco.fmhandroid.R;

public class AboutScreenSteps {

    AboutScreenElements aboutScreenElements = new AboutScreenElements();

    @Step("Проверка отображения экрана О приложении")
    public void checkAboutScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.about_company_info_label_text_view, 3000));
        aboutScreenElements.getVersion().check(matches(isDisplayed()));
        aboutScreenElements.getNumberVersion().check(matches(isDisplayed()));
    }

    @Step("Проверка кликабельности ссылки Политика конфиденциальности")
    public void checkPrivacyPolicyLinkIsClickable() {
        aboutScreenElements.getPrivacyPolicyLink().check(matches(isClickable()));
    }

    @Step("Проверка кликабельности ссылки Пользовательское соглашение")
    public void checkTermsOfUseLinkIsClickable() {
        aboutScreenElements.getTermsOfUseLink().check(matches(isClickable()));
    }

    @Step("Нажатие на кнопку Назад")
    public void clickOnBackButton() {
        aboutScreenElements.getBackButton().perform(click());
    }
}
