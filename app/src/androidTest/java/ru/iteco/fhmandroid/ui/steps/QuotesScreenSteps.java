package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.junit.Assert.assertEquals;

import android.os.SystemClock;

import ru.iteco.fhmandroid.ui.elements.QuotesScreenElements;

public class QuotesScreenSteps {

    QuotesScreenElements quotesScreenElements = new QuotesScreenElements();

    // Проверка отображения экрана Цитаты
    public void checkQuotesScreenIsDisplayed() {
        quotesScreenElements.getQuotesSectionTitle().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку свернуть/развернуть рядом с цитатой
    public void expandQuoteDescription() {
        quotesScreenElements.getExpandQuoteDescriptionButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Проверка текста в названии и описании цитаты
    public void checkTitleAndDescriptionOfQuote() {
        String title = quotesScreenElements.titleText(0);
        assertEquals("«Хоспис для меня - это то, каким должен быть мир.", title);
        quotesScreenElements.getQuoteTitle().check(matches(isDisplayed()));
        String description = quotesScreenElements.descriptionText(0);
        assertEquals("Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер", description);
        quotesScreenElements.getQuoteDescription().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
}
