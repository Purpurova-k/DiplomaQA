package ru.iteco.fhmandroid.ui.test;

import androidx.test.filters.LargeTest;

import org.junit.Test;

import ru.iteco.fhmandroid.ui.custom.BaseAndroidTest;
import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.ClaimsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fhmandroid.ui.steps.NewsScreenSteps;

@LargeTest
public class MainScreenTest extends BaseAndroidTest {

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    ClaimsScreenSteps claimsScreenSteps = new ClaimsScreenSteps();
    NewsScreenSteps newsScreenSteps = new NewsScreenSteps();


    // Кейс 2.1.1 "Выход из аккаунта"
    @Test
    public void shouldLogout() {
        mainScreenSteps.logout();
        authorizationScreenSteps.checkAuthorizationScreenIsDisplayed();
    }

    // Кейс 2.2.1 "Отображение раздела Новости на экране Главная через тап на кнопку свернуть/развернуть"
    @Test
    public void shouldCollapseAndExpandNewsFeed() {
        mainScreenSteps.expandNewsFeed();
        mainScreenSteps.checkNameAllNewsIsNotVisible();
        mainScreenSteps.expandNewsFeed();
        mainScreenSteps.checkNameAllNewsIsVisible();
    }

    // Кейс 2.2.2 "Открытие описания новости через тап на новость из раздела Новости"
    @Test
    public void shouldCollapseAndExpandNewsDescription() {
        mainScreenSteps.expandNewsDescription(0);
        mainScreenSteps.checkTextOfNewsDescriptionIsVisible(0);
    }

    // Кейс 2.2.3 "Отображение раздела Заявки на экране Главная через тап на кнопку свернуть/развернуть"
    @Test
    public void shouldCollapseAndExpandClaimsFeed() {
        mainScreenSteps.expandClaimsFeed();
        mainScreenSteps.checkNameAllClaimsIsNotVisible();
        mainScreenSteps.expandClaimsFeed();
        mainScreenSteps.checkNameAllClaimsIsVisible();
    }

    // Кейс 2.2.4 "Открытие детального описания заявки через тап на заявку из раздела Заявки"
    @Test
    public void shouldGoToClaimDescriptionWhenClickOnClaim() {
        mainScreenSteps.clickOnClaim(0);
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
    }

    // Кейс 2.3.1 "Возврат к экрану Главная через меню-гамбургер"
    @Test
    public void shouldGoToSectionMainFromMainMenu() {
        mainScreenSteps.goToSectionNewsFromMainMenu();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        mainScreenSteps.goToSectionMainFromMainMenu();
        mainScreenSteps.checkMainScreenIsDisplayed();
    }
}
