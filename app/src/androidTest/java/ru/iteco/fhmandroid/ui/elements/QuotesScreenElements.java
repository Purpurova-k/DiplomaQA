package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.R;

public class QuotesScreenElements {

    private final ViewInteraction quotesSectionTitle = onView(withId(R.id.our_mission_title_text_view));
    private final ViewInteraction expandQuoteDescriptionButton = onView(withIndex(withId(R.id.our_mission_item_open_card_image_button), 0));
    private final ViewInteraction quoteTitle = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));
    private final ViewInteraction quoteDescription = onView(withIndex(withId(R.id.our_mission_item_description_text_view), 0));

    public ViewInteraction getQuotesSectionTitle() {
        return quotesSectionTitle;
    }
    public ViewInteraction getExpandQuoteDescriptionButton() {
        return expandQuoteDescriptionButton;
    }
    public ViewInteraction getQuoteTitle() {
        return quoteTitle;
    }
    public ViewInteraction getQuoteDescription() {
        return quoteDescription;
    }
    public String titleText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.our_mission_item_title_text_view), position))).replaceAll("^\"|\"$", "");
    }
    public String descriptionText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.our_mission_item_description_text_view), position))).replaceAll("^\"|\"$", "");
    }
}
