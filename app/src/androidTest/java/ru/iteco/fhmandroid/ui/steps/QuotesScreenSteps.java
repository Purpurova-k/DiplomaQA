package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.junit.Assert.assertEquals;

import ru.iteco.fhmandroid.ui.elements.QuotesScreenElements;

public class QuotesScreenSteps {

    QuotesScreenElements quotesScreenElements = new QuotesScreenElements();

    // Проверка отображения экрана Цитаты
    public void checkQuotesScreenIsDisplayed() {
        quotesScreenElements.getQuotesSectionTitle().check(matches(isDisplayed()));
    }

    // Нажатие на кнопку свернуть/развернуть рядом с цитатой
    public void expandQuoteDescription() {
        quotesScreenElements.getExpandQuoteDescriptionButton().perform(click());
    }

    // Проверка текста в названии и описании цитаты
    public void checkTitleAndDescriptionOfQuote(String title, String description) {
        String titleActual = quotesScreenElements.titleText(0);
        assertEquals(title, titleActual);
        quotesScreenElements.getQuoteTitle().check(matches(isDisplayed()));
        String descriptionActual = quotesScreenElements.descriptionText(0);
        assertEquals(description, descriptionActual);
        quotesScreenElements.getQuoteDescription().check(matches(isDisplayed()));
    }
}
