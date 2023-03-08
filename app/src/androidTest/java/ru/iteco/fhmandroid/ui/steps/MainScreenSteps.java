package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.not;

import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fhmandroid.ui.elements.MainScreenElements;
import ru.iteco.fmhandroid.R;

public class MainScreenSteps {

    MainScreenElements mainScreenElements = new MainScreenElements();

    @Step("Проверка отображения экрана Главная")
    public void checkMainScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.authorization_image_button, 3000));
        mainScreenElements.getAllNewsButton().check(matches(isDisplayed()));
        mainScreenElements.getAllClaimsButton().check(matches(isDisplayed()));
    }

    @Step("Выход из аккаунта через нажатие на кнопку аватара в AppBar")
    public void logout() {
        onView(isRoot()).perform(waitUntilShown(R.id.authorization_image_button, 3000));
        mainScreenElements.getAvatarImageButton().perform(click());
        mainScreenElements.getExitButton().perform(click());
    }

    @Step("Нажатие на кнопку свернуть/развернуть в разделе новости")
    public void expandNewsFeed() {
        onView(isRoot()).perform(waitUntilShown(R.id.expand_material_button, 3000));
        mainScreenElements.getExpandNewsFeedButton().perform(click());
    }

    @Step("Нажатие на кнопку Все новости в разделе заявки")
    public void clickOnAllNewsButton() {
        mainScreenElements.getAllNewsButton().perform(click());
    }

    @Step("Проверка, что название \"Все новости\" видно")
    public void checkNameAllNewsIsVisible() {
        mainScreenElements.getAllNewsButton().check(matches(isDisplayed()));
    }

    @Step("Проверка, что название \"Все новости\" не видно")
    public void checkNameAllNewsIsNotVisible() {
        mainScreenElements.getAllNewsButton().check(matches(not(isDisplayed())));
    }

    @Step("Нажатие на кнопку свернуть/развернуть рядом с новостью")
    public void expandNewsDescription(int position) {
        onView(isRoot()).perform(waitUntilShown(R.id.view_news_item_image_view, 3000));
        mainScreenElements.getExpandNewsDescriptionButton().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Проверка, что видно описание новости")
    public void checkTextOfNewsDescriptionIsVisible(int position) {
        onView(withIndex(withId(R.id.news_item_description_text_view), position)).check(matches(isDisplayed()));
    }

    @Step("Нажатие на кнопку свернуть/развернуть в разделе заявки")
    public void expandClaimsFeed() {
        onView(isRoot()).perform(waitUntilShown(R.id.expand_material_button, 3000));
        mainScreenElements.getExpandClaimsFeedButton().perform(click());
    }

    @Step("Нажатие на кнопку Все заявки в разделе заявки")
    public void clickOnAllClaimsButton() {
        onView(isRoot()).perform(waitUntilShown(R.id.all_claims_text_view, 3000));
        mainScreenElements.getAllClaimsButton().perform(click());
    }

    @Step("Проверка, что название \"Все заявки\" видно")
    public void checkNameAllClaimsIsVisible() {
        mainScreenElements.getAllClaimsButton().check(matches(isDisplayed()));
    }

    @Step("Провекрка, что название \"Все заявки\" не видно")
    public void checkNameAllClaimsIsNotVisible() {
        mainScreenElements.getAllClaimsButton().check(matches(not(isDisplayed())));
    }

    @Step("Нажатие претензию в разделе заявки")
    public void clickOnClaim(int position) {
        onView(isRoot()).perform(waitUntilShown(R.id.title_material_text_view, 3000));
        mainScreenElements.getListOfClaims().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Нажатие на кнопку Плюс в разделе заявки")
    public void clickOnCreateClaimButton() {
        onView(isRoot()).perform(waitUntilShown(R.id.add_new_claim_material_button, 3000));
        mainScreenElements.getCreateClaimButton().perform(click());
    }

    @Step("Переход к экрану Новости через меню-гамбургер")
    public void goToSectionNewsFromMainMenu() {
        onView(isRoot()).perform(waitUntilShown(R.id.main_menu_image_button, 3000));
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionNews().perform(click());
    }

    @Step("Переход к экрану Заявки через меню-гамбургер")
    public void goToSectionClaimsFromMainMenu() {
        onView(isRoot()).perform(waitUntilShown(R.id.main_menu_image_button, 3000));
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionClaims().perform(click());
    }

    @Step("Переход к экрану Главная через меню-гамбургер")
    public void goToSectionMainFromMainMenu() {
        onView(isRoot()).perform(waitUntilShown(R.id.main_menu_image_button, 3000));
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionMain().perform(click());
    }

    @Step("Переход к экрану О приложении через меню-гамбургер")
    public void goToSectionAboutFromMainMenu() {
        onView(isRoot()).perform(waitUntilShown(R.id.main_menu_image_button, 3000));
        mainScreenElements.getMainMenuButton().perform(click());
        mainScreenElements.getSectionAbout().perform(click());
    }

    @Step("Нажатие на кнопку бабочки в AppBar")
    public void goToSectionQuotesFromAppBar() {
        onView(isRoot()).perform(waitUntilShown(R.id.our_mission_image_button, 3000));
        mainScreenElements.getQuotesButton().perform(click());
    }
}
