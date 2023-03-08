// Эти тесты необходимо выполнять на устройстве с Android 10 (API 29)

package ru.iteco.fhmandroid.ui.test;

import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fhmandroid.ui.custom.BaseAndroidTest;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fhmandroid.ui.steps.QuotesScreenSteps;


public class QuotesScreenTest extends BaseAndroidTest {

    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    QuotesScreenSteps quotesScreenSteps = new QuotesScreenSteps();

    @Test
    @DisplayName("Кейс 3.1.1 \"Открытие описания цитаты через тап на цитату\"")
    public void shouldCollapseAndExpandQuoteDescription() {
        mainScreenSteps.goToSectionQuotesFromAppBar();
        quotesScreenSteps.checkQuotesScreenIsDisplayed();
        quotesScreenSteps.expandQuoteDescription();

        String title = "«Хоспис для меня - это то, каким должен быть мир.";
        String description = "Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, " +
                "никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, " +
                "а если холодно тебя согреют.” Юля Капис, волонтер";

        quotesScreenSteps.checkTitleAndDescriptionOfQuote(title, description);
    }
}
