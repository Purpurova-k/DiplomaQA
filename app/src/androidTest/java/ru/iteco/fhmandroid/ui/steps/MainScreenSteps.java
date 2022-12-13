package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import ru.iteco.fhmandroid.ui.elements.MainScreenElements;

public class MainScreenSteps {
    MainScreenElements mainScreenElements = new MainScreenElements();

    // Проверка отображения логотипа приложения
    public void checkLogoImageIsDisplayed() {
        mainScreenElements.getLogoImage().check(matches(isDisplayed()));
    }

    // Выход из аккаунта
    public void logout() {
        mainScreenElements.getAvatarImageButton().perform(click());
        mainScreenElements.getExitButton().perform(click());
        SystemClock.sleep(3000);
    }
}
