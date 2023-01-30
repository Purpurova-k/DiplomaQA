package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterClaimsScreenElements {

    private final ViewInteraction filterTitle = onView(withId(R.id.claim_filter_dialog_title));
    private final ViewInteraction checkBoxOpened = onView(withId(R.id.item_filter_open));
    private final ViewInteraction checkBoxInProgress = onView(withId(R.id.item_filter_in_progress));
    private final ViewInteraction checkBoxExecuted = onView(withId(R.id.item_filter_executed));
    private final ViewInteraction checkBoxCancelled = onView(withId(R.id.item_filter_cancelled));
    private final ViewInteraction okButton = onView(withId(R.id.claim_list_filter_ok_material_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.claim_filter_cancel_material_button));

    public ViewInteraction getFilterTitle() {
        return filterTitle;
    }
    public ViewInteraction getCheckBoxOpened() {
        return checkBoxOpened;
    }
    public ViewInteraction getCheckBoxInProgress() {
        return checkBoxInProgress;
    }
    public ViewInteraction getCheckBoxExecuted() {
        return checkBoxExecuted;
    }
    public ViewInteraction getCheckBoxCancelled() {
        return checkBoxCancelled;
    }
    public ViewInteraction getOkButton() {
        return okButton;
    }
    public ViewInteraction getCancelButton() {
        return cancelButton;
    }
}
