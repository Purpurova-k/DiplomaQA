package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fhmandroid.ui.data.DataHelper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreateClaimScreenElements {

    private final ViewInteraction createTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    private final ViewInteraction claimsTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));

    private final ViewInteraction theme = onView(withId(R.id.title_edit_text));
    private final ViewInteraction executor = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    private final ViewInteraction date = onView(withId(R.id.date_in_plan_text_input_edit_text));
    private final ViewInteraction time = onView(withId(R.id.time_in_plan_text_input_edit_text));
    private final ViewInteraction description = onView(withId(R.id.description_edit_text));

    private final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

    private final ViewInteraction okExitButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    public ViewInteraction getCreateTitle() {
        return createTitle;
    }
    public ViewInteraction getClaimsTitle() {
        return claimsTitle;
    }
    public ViewInteraction getTheme() {
        return theme;
    }
    public ViewInteraction getExecutor() {
        return executor;
    }
    public ViewInteraction getDate() {
        return date;
    }
    public ViewInteraction getTime() {
        return time;
    }
    public ViewInteraction getDescription() {
        return description;
    }
    public ViewInteraction getSaveButton() {
        return saveButton;
    }
    public ViewInteraction getCancelButton() {
        return cancelButton;
    }
    public ViewInteraction getOkExitButton() {
        return okExitButton;
    }
}
