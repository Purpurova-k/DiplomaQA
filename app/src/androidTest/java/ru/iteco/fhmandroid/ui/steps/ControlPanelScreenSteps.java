package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.ControlPanelScreenElements;
import ru.iteco.fmhandroid.R;

public class ControlPanelScreenSteps {

    ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();

    // Проверка отображения экрана Панель управления новостей
    public void checkControlPanelScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.news_list_recycler_view, 3000));
        controlPanelScreenElements.getControlPanel().check(matches(isDisplayed()));
    }

    // Нажатие на кнопку Сортировка
    public void clickButtonSortNews() {
        onView(isRoot()).perform(waitUntilShown(R.id.sort_news_material_button, 3000));
        controlPanelScreenElements.getSortNewsButton().perform(click());
    }

    // Нажатие на кнопку Фильтр
    public void goToAdvancedFilterNewsScreen() {
        onView(isRoot()).perform(waitUntilShown(R.id.filter_news_material_button, 3000));
        controlPanelScreenElements.getFilterNewsButton().perform(click());
    }

    // Нажатие на кнопку Плюс (создать новость)
    public void clickButtonCreateNews() {
        onView(isRoot()).perform(waitUntilShown(R.id.add_news_image_view, 3000));
        controlPanelScreenElements.getCreateNewsButton().perform(click());
    }

    // Нажатие на новость в списке
    public void clickOnNewsItem(int position) {
        onView(isRoot()).perform(waitUntilShown(R.id.news_item_material_card_view, 3000));
        controlPanelScreenElements.getListOfNews().perform(actionOnItemAtPosition(position, click()));
    }

    // Проверка, что видно описание новости
    public void checkTextOfNewsDescriptionIsVisible(int position) {
        onView(withIndex(withId(R.id.news_item_description_text_view), position)).check(matches(isDisplayed()));
    }

    // Нажатие на кнопку удалить рядом с новостью
    public void clickButtonDeleteNews(String nameOfNews) {
        onView(isRoot()).perform(waitUntilShown(R.id.delete_news_item_image_view, 3000));
        onView(allOf(withId(R.id.delete_news_item_image_view), hasSibling(withText(nameOfNews)))).perform(click());

    }

    // Нажатие на ОК для подтверждения удаления новости
    public void clickButtonOkDeleteNews() {
        controlPanelScreenElements.getOkDeleteNewsButton().perform(click());
    }

    // Нажатие на Отмена при подтверждении удаления новости
    public void clickButtonCancelDeleteNews() {
        controlPanelScreenElements.getCancelDeleteNewsButton().perform(click());
    }

    // Нажатие на кнопку редактировать рядом с новостью
    public void clickButtonEditNews(int position) {
        onView(isRoot()).perform(waitUntilShown(R.id.edit_news_item_image_view, 3000));
        onView(withIndex(withId(R.id.edit_news_item_image_view), position)).perform(click());
    }

    // Обновить экран Панель управления
    public void swipeDownToRefresh() {
        onView(withId(R.id.news_control_panel_swipe_to_refresh)).perform(swipeDown());
    }

    // Проверка отображения экрана с надписью "Здесь пока ничего нет"
    public void checkEmptyScreenIsDisplayed() {
        onView(withText(startsWith("Здесь пока ничего нет"))).check(matches(isDisplayed()));
    }

    // Сравнение данных первой новости в списке до и после удаления новости
    public void compareDataOfFirstNewsBeforeAndAfterDeleting(String nameBefore, String nameAfter, String publicationDateBefore,
                                                             String publicationDateAfter, String creationDateBefore, String creationDateAfter,
                                                             String descriptionBefore, String descriptionAfter) {
        assertNotEquals(nameBefore, nameAfter);
        assertNotEquals(descriptionBefore, descriptionAfter);
        if(publicationDateBefore.equals(publicationDateAfter)) {
            assertEquals(publicationDateBefore, publicationDateAfter);
        } else {
            assertNotEquals(publicationDateBefore, publicationDateAfter);
        }
        if(creationDateBefore.equals(creationDateAfter)) {
            assertEquals(creationDateBefore, creationDateAfter);
        } else {
            assertNotEquals(creationDateBefore, creationDateAfter);
        }
    }

    // Сравнение данных первой новости в списке до и после отмены удаления новости
    public void compareDataOfFirstNewsBeforeAndAfterCancellingDeleting(String nameBefore, String nameAfter, String publicationDateBefore,
                                                             String publicationDateAfter, String creationDateBefore, String creationDateAfter,
                                                             String descriptionBefore, String descriptionAfter) {
        assertEquals(nameBefore, nameAfter);
        assertEquals(publicationDateBefore, publicationDateAfter);
        assertEquals(creationDateBefore, creationDateAfter);
        assertEquals(descriptionBefore, descriptionAfter);
    }

    // Проверка сортировки новостей от более старых к более новым и наоборот
    public void checkSortingNewsIsSuccessful(String firstNewsNameBeforeSorting, String firstNewsDateBeforeSorting,
                                             String lastNewsNameBeforeSorting, String lastNewsDateBeforeSorting,
                                             String firstNewsNameAfterSorting, String firstNewsDateAfterSorting,
                                             String lastNewsNameAfterSorting, String lastNewsDateAfterSorting) {
        assertEquals(firstNewsNameBeforeSorting, lastNewsNameAfterSorting);
        assertEquals(firstNewsDateBeforeSorting, lastNewsDateAfterSorting);
        assertEquals(lastNewsNameBeforeSorting, firstNewsNameAfterSorting);
        assertEquals(lastNewsDateBeforeSorting, firstNewsDateAfterSorting);
    }

    // Сравнение категории введенной в фильтре и категории найденной новости
    public void compareCategoryInFilterAndFilteredNews(String categoryFilter, String categoryNews) {
        assertEquals(categoryFilter, categoryNews);
    }

    // Сравнение названий новостей до и после отмененной фильтрации
    public void compareDataAfterCancellingFilter(String nameOneBefore, String nameOneAfter,
                                                 String nameTwoBefore, String nameTwoAfter,
                                                 String nameThreeBefore, String nameThreeAfter) {
        assertEquals(nameOneBefore, nameOneAfter);
        assertEquals(nameTwoBefore, nameTwoAfter);
        assertEquals(nameThreeBefore, nameThreeAfter);
    }

    // Проверка фильтрации по дате публикации
    public void checkDataOfFilteredNewsByDate(String publicationDateFirstNews, String date1, String publicationDateLastNews, String date2) {
        assertEquals(publicationDateFirstNews, date1);
        assertEquals(publicationDateLastNews, date2);
    }

    // Проверка фильтрации по дате публикации с одной введенной датой в фильтрации
    public void checkDataOfFilteredNewsByDateOneDate(String publicationDateNews, String date) {
        assertEquals(publicationDateNews, date);
    }

    // Проверка статуса Активна
    public void checkStatusIsActive(int position) {
        String status = DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));
        assertEquals("АКТИВНА", status.toUpperCase());
    }

    // Проверка статуса Не активна
    public void checkStatusIsInactive(int position) {
        String status = DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));
        assertEquals("НЕ АКТИВНА", status.toUpperCase());
    }

    // Сравнение данных новости после редактирования
    public void compareDataAfterEditingNews(String name, String editedName, String publicationDate, String editedPublicationDate,
                                            String time, String editedTime, String description, String editedDescription,
                                            String category, String editedCategory) {
        assertEquals(name, editedName);
        assertEquals(publicationDate, editedPublicationDate);
        assertEquals(time, editedTime);
        assertEquals(description, editedDescription);
        assertEquals(category, editedCategory);
    }

    // Сравнение данных новости до и после отмены редактирования
    public void compareDataAfterCancellingEditingNews(String nameBefore, String nameAfter, String publicationDateBefore, String publicationDateAfter,
                                            String timeBefore, String timeAfter, String descriptionBefore, String descriptionAfter,
                                            String categoryBefore, String categoryAfter) {
        assertEquals(nameBefore, nameAfter);
        assertEquals(publicationDateBefore, publicationDateAfter);
        assertEquals(timeBefore, timeAfter);
        assertEquals(descriptionBefore, descriptionAfter);
        assertEquals(categoryBefore, categoryAfter);
    }

    // Проверка, что статус новости изменился и она отсутствует в списке новостей
    public void checkNewsStatusEdited(String descriptionFirstNewsBefore, String descriptionFirstNewsAfter) {
        assertNotEquals(descriptionFirstNewsBefore, descriptionFirstNewsAfter);
    }

    // Сравнение данных у созданной и найденной претензий
    public void compareDataOfCreatedAndFoundNews(String nameCreated, String nameFound, String publicationDateCreated,
                                                  String publicationDateFound, String descriptionCreated, String descriptionFound) {
        assertEquals(nameCreated, nameFound);
        assertEquals(publicationDateCreated, publicationDateFound);
        assertEquals(descriptionCreated, descriptionFound);
    }

    // Сравнение данных первой новости до и после отмены создания новой новости
    public void compareDataAfterCancellingCreatingNews(String nameBefore, String nameAfter, String publicationDateBefore, String publicationDateAfter,
                                                       String descriptionBefore, String descriptionAfter) {
        assertEquals(nameBefore, nameAfter);
        assertEquals(publicationDateBefore, publicationDateAfter);
        assertEquals(descriptionBefore, descriptionAfter);
    }



    // Получение текстовых данных новости

    public String nameText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String publicationDateText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
    }

    public String creationDateText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), position)));
    }

    public String authorText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_author_name_text_view), position)));
    }

    public String descriptionText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }
}
