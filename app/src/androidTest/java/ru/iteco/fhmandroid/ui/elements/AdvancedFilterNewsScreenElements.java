package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AdvancedFilterNewsScreenElements {

    private final ViewInteraction filterTitle = onView(withId(R.id.filter_news_title_text_view));
    private final ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction dateStart = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction dateEnd = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private final ViewInteraction checkBoxActive = onView(withId(R.id.filter_news_active_material_check_box));
    private final ViewInteraction checkBoxInactive = onView(withId(R.id.filter_news_inactive_material_check_box));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

    public ViewInteraction getFilterTitle() {
        return filterTitle;
    }
    public ViewInteraction getCategory() {
        return category;
    }
    public ViewInteraction getDateStart() {
        return dateStart;
    }
    public ViewInteraction getDateEnd() {
        return dateEnd;
    }
    public ViewInteraction getCheckBoxActive() { return checkBoxActive; }
    public ViewInteraction getCheckBoxInactive() { return checkBoxInactive; }
    public ViewInteraction getFilterButton() {
        return filterButton;
    }
    public ViewInteraction getCancelButton() {
        return cancelButton;
    }
}
