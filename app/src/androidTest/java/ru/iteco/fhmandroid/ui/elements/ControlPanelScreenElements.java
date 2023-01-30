package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fhmandroid.ui.data.DataHelper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanelScreenElements {

    private final ViewInteraction controlPanel = onView(withText(containsString("Панель")));
    private final ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction createNewsButton = onView(withId(R.id.add_news_image_view));

    private final ViewInteraction listOfNews = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")), withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                            0)));

    private final ViewInteraction okDeleteNewsButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    private final ViewInteraction cancelDeleteNewsButton = onView(
            allOf(withId(android.R.id.button2), withText("ОТМЕНА"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            2)));

    public ViewInteraction getControlPanel() {
        return controlPanel;
    }
    public ViewInteraction getSortNewsButton() {
        return sortNewsButton;
    }
    public ViewInteraction getFilterNewsButton() {
        return filterNewsButton;
    }
    public ViewInteraction getCreateNewsButton() { return createNewsButton; }
    public ViewInteraction getListOfNews() {
        return listOfNews;
    }
    public ViewInteraction getOkDeleteNewsButton() {
        return okDeleteNewsButton;
    }
    public ViewInteraction getCancelDeleteNewsButton() {
        return cancelDeleteNewsButton;
    }
}
