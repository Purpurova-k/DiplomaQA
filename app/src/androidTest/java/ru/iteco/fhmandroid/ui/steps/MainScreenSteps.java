package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.not;

import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import ru.iteco.fhmandroid.ui.elements.MainScreenElements;
import ru.iteco.fmhandroid.R;

public class MainScreenSteps {

    MainScreenElements mainScreenElements = new MainScreenElements();

    // Проверка отображения логотипа приложения
    public void checkLogoImageIsDisplayed() {
        mainScreenElements.getLogoImage().check(matches(isDisplayed()));
    }

    // Проверка отображения экрана Главная
    public void checkMainScreenIsDisplayed() {
        mainScreenElements.getAllNewsButton().check(matches(isDisplayed()));
        mainScreenElements.getAllClaimsButton().check(matches(isDisplayed()));
    }

    // Выход из аккаунта через нажатие на кнопку аватара в AppBar
    public void logout() {
        mainScreenElements.getAvatarImageButton().perform(click());
        mainScreenElements.getExitButton().perform(click());
    }

    // Нажатие на кнопку свернуть/развернуть в разделе новости
    public void expandNewsFeed() {
        mainScreenElements.getExpandNewsFeedButton().perform(click());
    }

    // Нажатие на кнопку Все новости в разделе заявки
    public void clickOnAllNewsButton() {
        mainScreenElements.getAllNewsButton().perform(click());
    }

    // Проверка, что название "Все новости" видно
    public void checkNameAllNewsIsVisible() {
        mainScreenElements.getAllNewsButton().check(matches(isDisplayed()));
    }

    // Проверка, что название "Все новости" не видно
    public void checkNameAllNewsIsNotVisible() {
        mainScreenElements.getAllNewsButton().check(matches(not(isDisplayed())));
    }

    // Нажатие на кнопку свернуть/развернуть рядом с новостью
    public void expandNewsDescription(int position) {
        mainScreenElements.getExpandNewsDescriptionButton().perform(actionOnItemAtPosition(position, click()));
    }

    // Проверка, что видно описание новости
    public void checkTextOfNewsDescriptionIsVisible(int position) {
        onView(withIndex(withId(R.id.news_item_description_text_view), position)).check(matches(isDisplayed()));
    }

    // Нажатие на кнопку свернуть/развернуть в разделе заявки
    public void expandClaimsFeed() {
        mainScreenElements.getExpandClaimsFeedButton().perform(click());
    }

    // Нажатие на кнопку Все заявки в разделе заявки
    public void clickOnAllClaimsButton() {
        mainScreenElements.getAllClaimsButton().perform(click());
    }

    // Проверка, что название "Все заявки" видно
    public void checkNameAllClaimsIsVisible() {
        mainScreenElements.getAllClaimsButton().check(matches(isDisplayed()));
    }

    // Провекрка, что название "Все заявки" не видно
    public void checkNameAllClaimsIsNotVisible() {
        mainScreenElements.getAllClaimsButton().check(matches(not(isDisplayed())));
    }

    // Нажатие претензию в разделе заявки
    public void clickOnClaim(int position) {
        mainScreenElements.getListOfClaims().perform(actionOnItemAtPosition(position, click()));
    }

    // Нажатие на кнопку Плюс в разделе заявки
    public void clickOnCreateClaimButton() {
        mainScreenElements.getCreateClaimButton().perform(click());
    }

    // Переход к экрану Новости через меню-гамбургер
    public void goToSectionNewsFromMainMenu() {
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionNews().perform(click());
    }

    // Переход к экрану Заявки через меню-гамбургер
    public void goToSectionClaimsFromMainMenu() {
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionClaims().perform(click());
    }

    // Переход к экрану Главная через меню-гамбургер
    public void goToSectionMainFromMainMenu() {
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionMain().perform(click());
    }

    // Переход к экрану О приложении через меню-гамбургер
    public void goToSectionAboutFromMainMenu() {
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionAbout().perform(click());
    }

    // Нажатие на кнопку бабочки в AppBar
    public void goToSectionQuotesFromAppBar() {
        mainScreenElements.getQuotesButton().perform(click());
    }

}
