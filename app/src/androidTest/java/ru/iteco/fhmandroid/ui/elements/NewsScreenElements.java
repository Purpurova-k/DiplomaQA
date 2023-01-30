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

public class NewsScreenElements {

    private final ViewInteraction newsSectionTitle = onView(withText("Новости"));
    private final ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    private final ViewInteraction listOfNews = onView(withId(R.id.news_list_recycler_view));

    private final ViewInteraction expandNewsDescriptionButton = onView(
            allOf(withId(R.id.news_list_recycler_view), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.all_news_cards_block_constraint_layout),
                            0)));


    public ViewInteraction getNewsSectionTitle() {
        return newsSectionTitle;
    }
    public ViewInteraction getSortNewsButton() {
        return sortNewsButton;
    }
    public ViewInteraction getFilterNewsButton() {
        return filterNewsButton;
    }
    public ViewInteraction getEditNewsButton() {
        return editNewsButton;
    }
    public ViewInteraction getListOfNews() {
        return listOfNews;
    }
    public ViewInteraction getExpandNewsDescriptionButton() {
        return expandNewsDescriptionButton;
    }
}
