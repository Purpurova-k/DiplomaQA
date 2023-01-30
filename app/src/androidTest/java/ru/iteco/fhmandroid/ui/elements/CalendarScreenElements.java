package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fhmandroid.ui.data.DataHelper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

public class CalendarScreenElements {

    private final ViewInteraction okButton = onView(
            allOf(withText("ОК"), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    private final ViewInteraction cancelButton = onView(
            allOf(withText("ОТМЕНА"), childAtPosition(
                    withClassName(is("android.widget.LinearLayout")), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                            0),
                    2)));

    private final ViewInteraction chooseYearButton = onView(
            allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText("2023"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    0),
                            0)));

    private final ViewInteraction nextMonthButton = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Следующий месяц"),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.DayPickerView")),
                                    childAtPosition(withClassName(is("android.widget.LinearLayout")), withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                            0)),
                            2)));

    private final ViewInteraction previousMonthButton = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Прошлый месяц"),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.DayPickerView")),
                                    childAtPosition(withClassName(is("android.widget.LinearLayout")), withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                            0)),
                            1)));

    public ViewInteraction getOkButton() {
        return okButton;
    }
    public ViewInteraction getCancelButton() { return cancelButton; }
    public ViewInteraction getChooseYearButton() {
        return chooseYearButton;
    }
    public ViewInteraction getNextMonthButton() {
        return nextMonthButton;
    }
    public ViewInteraction getPreviousMonthButton() {
        return previousMonthButton;
    }
}
