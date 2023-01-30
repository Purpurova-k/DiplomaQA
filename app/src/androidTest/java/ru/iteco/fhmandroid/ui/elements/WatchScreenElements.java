package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fhmandroid.ui.data.DataHelper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

public class WatchScreenElements {

    private final ViewInteraction okButton = onView(
            allOf(withId(android.R.id.button1), withText("ОК"),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")),
                            childAtPosition(withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    private final ViewInteraction openKeyboardButton = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")),
                            childAtPosition(withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    4),
                            0)));

    private final ViewInteraction hourField = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.RelativeLayout")),
                                    childAtPosition(withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.TextInputTimePickerView")),
                                            1)),
                            0)));

    private final ViewInteraction minuteField = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.RelativeLayout")),
                                    childAtPosition(withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.TextInputTimePickerView")),
                                            1)),
                            3)));

    public ViewInteraction getOkButton() {
        return okButton;
    }
    public ViewInteraction getOpenKeyboardButton() {
        return openKeyboardButton;
    }
    public ViewInteraction getHourField() {
        return hourField;
    }
    public ViewInteraction getMinuteField() {
        return minuteField;
    }
}
