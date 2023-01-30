package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import ru.iteco.fhmandroid.ui.elements.WatchScreenElements;
import ru.iteco.fmhandroid.ui.AppActivity;

public class WatchScreenSteps {

    WatchScreenElements watchScreenElements = new WatchScreenElements();

    // Нажатие на кнопку ОК
    public void clickOnOkButton() {
        watchScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку клавиатуры для изенения вида часов
    public void clickOnOpenKeyboardButton() {
        watchScreenElements.getOpenKeyboardButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Установка часа
    public void settingHour(String hour) {
        watchScreenElements.getHourField().perform(replaceText(hour));
        SystemClock.sleep(3000);
    }

    // Установка минут
    public void settingMinute(String minute) {
        watchScreenElements.getMinuteField().perform(replaceText(minute));
        SystemClock.sleep(3000);
    }

    // Проверка отображения валидации поля - только это добавить в шаги Алюр, остальные не надо
    public void checkValidationInvalidTimeIsDisplayed(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

}
