// Эти тесты необходимо выполнять на устройстве с Android 10 (API 29)

package ru.iteco.fhmandroid.ui.test;

import androidx.test.filters.LargeTest;

import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
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

    @Test
    @DisplayName("Кейс 2.1.1 \"Выход из аккаунта\"")
    public void shouldLogout() {
        mainScreenSteps.logout();
        authorizationScreenSteps.checkAuthorizationScreenIsDisplayed();
    }

    @Test
    @DisplayName("Кейс 2.2.1 \"Отображение раздела Новости на экране Главная через тап на кнопку свернуть/развернуть\"")
    public void shouldCollapseAndExpandNewsFeed() {
        mainScreenSteps.expandNewsFeed();
        mainScreenSteps.checkNameAllNewsIsNotVisible();
        mainScreenSteps.expandNewsFeed();
        mainScreenSteps.checkNameAllNewsIsVisible();
    }

    @Test
    @DisplayName("Кейс 2.2.2 \"Открытие описания новости через тап на новость из раздела Новости\"")
    public void shouldCollapseAndExpandNewsDescription() {
        mainScreenSteps.expandNewsDescription(0);
        mainScreenSteps.checkTextOfNewsDescriptionIsVisible(0);
    }

    @Test
    @DisplayName("Кейс 2.2.3 \"Отображение раздела Заявки на экране Главная через тап на кнопку свернуть/развернуть\"")
    public void shouldCollapseAndExpandClaimsFeed() {
        mainScreenSteps.expandClaimsFeed();
        mainScreenSteps.checkNameAllClaimsIsNotVisible();
        mainScreenSteps.expandClaimsFeed();
        mainScreenSteps.checkNameAllClaimsIsVisible();
    }

    @Test
    @DisplayName("Кейс 2.2.4 \"Открытие детального описания заявки через тап на заявку из раздела Заявки\"")
    public void shouldGoToClaimDescriptionWhenClickOnClaim() {
        mainScreenSteps.clickOnClaim(0);
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
    }

    @Test
    @DisplayName("Кейс 2.3.1 \"Возврат к экрану Главная через меню-гамбургер\"")
    public void shouldGoToSectionMainFromMainMenu() {
        mainScreenSteps.goToSectionNewsFromMainMenu();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        mainScreenSteps.goToSectionMainFromMainMenu();
        mainScreenSteps.checkMainScreenIsDisplayed();
    }
}
