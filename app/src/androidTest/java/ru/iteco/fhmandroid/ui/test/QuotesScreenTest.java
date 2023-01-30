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

import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fhmandroid.ui.steps.QuotesScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class QuotesScreenTest {

    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    QuotesScreenSteps quotesScreenSteps = new QuotesScreenSteps();

    @Before
    public void checkLogout() {
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

    // Кейс 3.1.1 "Открытие описания цитаты через тап на цитату"
    @Test
    public void shouldCollapseAndExpandQuoteDescription() {
        mainScreenSteps.goToSectionQuotesFromAppBar();
        quotesScreenSteps.checkQuotesScreenIsDisplayed();
        quotesScreenSteps.expandQuoteDescription();
        quotesScreenSteps.checkTitleAndDescriptionOfQuote();
    }
}
