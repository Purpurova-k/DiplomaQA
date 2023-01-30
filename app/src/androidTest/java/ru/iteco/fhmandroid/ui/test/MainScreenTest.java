package ru.iteco.fhmandroid.ui.test;

import static ru.iteco.fhmandroid.ui.data.DataHelper.validLoginAndPassword;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.ClaimsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fhmandroid.ui.steps.NewsScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainScreenTest {

    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    ClaimsScreenSteps claimsScreenSteps = new ClaimsScreenSteps();
    NewsScreenSteps newsScreenSteps = new NewsScreenSteps();

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
        mainScreenSteps.swipeUpInSectionClaims();
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
