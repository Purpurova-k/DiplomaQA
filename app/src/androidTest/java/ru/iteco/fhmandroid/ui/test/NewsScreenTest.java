package ru.iteco.fhmandroid.ui.test;

import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomNewsCategory;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomNewsCategoryExceptAnnouncement;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Swipe.swipeToBottomControlPanelScreen;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Swipe.swipeToBottomNewsScreen;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Swipe.swipeToTopControlPanelScreen;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Swipe.swipeToTopNewsScreen;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithCyrillicSymbols;
import static ru.iteco.fhmandroid.ui.data.DataHelper.createNewsPublicationAYearAgo;
import static ru.iteco.fhmandroid.ui.data.DataHelper.createNewsPublicationInOneWeek;
import static ru.iteco.fhmandroid.ui.data.DataHelper.createNewsPublicationToday;
import static ru.iteco.fhmandroid.ui.data.DataHelper.createNewsPublicationTomorrow;
import static ru.iteco.fhmandroid.ui.data.DataHelper.createNewsPublicationYesterday;
import static ru.iteco.fhmandroid.ui.data.DataHelper.popupChooseCategoryFromDropdown;
import static ru.iteco.fhmandroid.ui.data.DataHelper.popupWrongPeriod;
import static ru.iteco.fhmandroid.ui.data.DataHelper.popupWrongTimeForPublicationNews;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackFillEmptyFields;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackChooseCategoryFromDropdown;
import static ru.iteco.fhmandroid.ui.data.DataHelper.validLoginAndPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.validationInvalidTime;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.steps.AdvancedFilterNewsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.CreateNewsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.ControlPanelScreenSteps;
import ru.iteco.fhmandroid.ui.steps.EditNewsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.FilterNewsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fhmandroid.ui.steps.NewsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.WatchScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsScreenTest {

    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    NewsScreenSteps newsScreenSteps = new NewsScreenSteps();
    FilterNewsScreenSteps filterNewsScreenSteps = new FilterNewsScreenSteps();
    ControlPanelScreenSteps controlPanelScreenSteps = new ControlPanelScreenSteps();
    CreateNewsScreenSteps createNewsScreenSteps = new CreateNewsScreenSteps();
    AdvancedFilterNewsScreenSteps advancedFilterNewsScreenSteps = new AdvancedFilterNewsScreenSteps();
    EditNewsScreenSteps editNewsScreenSteps = new EditNewsScreenSteps();
    WatchScreenSteps watchScreenSteps = new WatchScreenSteps();

    @Before
    public void checkLogin() {
        SystemClock.sleep(8000);
        try {
            mainScreenSteps.checkMainScreenIsDisplayed();
        } catch (NoMatchingViewException e) {
            authorizationScreenSteps.validLoginAndPasswordAuthorization(validLoginAndPassword());
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    // Кейс 5.1.1 "Сортировка новостей на экране Новости"
    @Test
    public void shouldSortNewsOnScreenNews() {
        mainScreenSteps.goToSectionNewsFromMainMenu();
        newsScreenSteps.checkNewsScreenIsDisplayed();

        String firstNewsNameBeforeSorting = newsScreenSteps.nameOfFirstNewsText();
        String firstNewsDateBeforeSorting = newsScreenSteps.dateOfFirstNewsText();

        swipeToBottomNewsScreen();

        String lastNewsNameBeforeSorting = newsScreenSteps.nameOfLastNewsText();
        String lastNewsDateBeforeSorting = newsScreenSteps.dateOfLastNewsText();

        swipeToTopNewsScreen();
        newsScreenSteps.clickButtonSortNews();

        String firstNewsNameAfterSorting = newsScreenSteps.nameOfFirstNewsText();
        String firstNewsDateAfterSorting = newsScreenSteps.dateOfFirstNewsText();

        swipeToBottomNewsScreen();

        String lastNewsNameAfterSorting = newsScreenSteps.nameOfLastNewsText();
        String lastNewsDateAfterSorting = newsScreenSteps.dateOfLastNewsText();

        newsScreenSteps.checkSortingNewsIsSuccessful(firstNewsNameBeforeSorting, firstNewsDateBeforeSorting,
                lastNewsNameBeforeSorting, lastNewsDateBeforeSorting,
                firstNewsNameAfterSorting, firstNewsDateAfterSorting,
                lastNewsNameAfterSorting, lastNewsDateAfterSorting);
    }

    // Кейс 5.1.2 "Фильтрация новостей на экране Новости по случайно выбранной категории и по сегодняшней дате"
    @Test
    public void shouldFilterNewsCategoryAnnouncementOnNewsScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();

        newsScreenSteps.goToControlPanelScreen();
        String category = randomNewsCategory();
        createNewsPublicationToday(category);
        mainScreenSteps.goToSectionNewsFromMainMenu();

        newsScreenSteps.goToFilterNewsScreen();
        filterNewsScreenSteps.checkFilterNewsScreenIsDisplayed();

        filterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);
        filterNewsScreenSteps.fillTheStartDateToday();
        filterNewsScreenSteps.fillTheEndDateToday();

        String filterName = filterNewsScreenSteps.categoryText();
        String filterDate = filterNewsScreenSteps.dateEndText();

        filterNewsScreenSteps.clickOnFilterButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();

        String filteredNewsName = newsScreenSteps.nameOfFirstNewsText();
        String filteredNewsDate = newsScreenSteps.dateOfFirstNewsText();

        newsScreenSteps.compareDataOfFilteredNewsAndDataForFiltering(filterName, filterDate, filteredNewsName, filteredNewsDate);
    }

    // Кейс 5.1.3 "Просмотр описания новости на экране Новости"
    @Test
    public void shouldCollapseAndExpandNewsDescription() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();

        newsScreenSteps.expandNewsDescription(0);
        newsScreenSteps.checkTextOfNewsDescriptionIsVisible(0);
    }

    // Кейс 5.2.1 "Просмотр описания новости в разделе Панель управления новостей"
    @Test
    public void shouldCollapseAndExpandNewsDescriptionOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickOnNewsItem(0);
        controlPanelScreenSteps.checkTextOfNewsDescriptionIsVisible(0);
    }

    // Кейс 5.2.2 "Удаление новости в разделе Панель управления новостей"
    @Test
    public void shouldDeleteNewsOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();
        controlPanelScreenSteps.clickOnNewsItem(0);

        String nameBefore = controlPanelScreenSteps.nameText(0);
        String publicationDateBefore = controlPanelScreenSteps.publicationDateText(0);
        String creationDateBefore = controlPanelScreenSteps.creationDateText(0);
        String descriptionBefore = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.clickButtonDeleteNews(nameBefore);
        controlPanelScreenSteps.clickButtonOkDeleteNews();

        controlPanelScreenSteps.clickOnNewsItem(0);

        String nameAfter = controlPanelScreenSteps.nameText(0);
        String publicationDateAfter = controlPanelScreenSteps.publicationDateText(0);
        String creationDateAfter = controlPanelScreenSteps.creationDateText(0);
        String descriptionAfter = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.compareDataOfFirstNewsBeforeAndAfterDeleting(nameBefore, nameAfter, publicationDateBefore,
                publicationDateAfter, creationDateBefore, creationDateAfter, descriptionBefore, descriptionAfter);
    }

    // Кейс 5.2.3 "Удаление новости в разделе Панель управления новостей с последующей отменой"
    @Test
    public void shouldNotDeleteNewsAfterCancelingDeletingOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();
        controlPanelScreenSteps.clickOnNewsItem(0);

        String nameBefore = controlPanelScreenSteps.nameText(0);
        String publicationDateBefore = controlPanelScreenSteps.publicationDateText(0);
        String creationDateBefore = controlPanelScreenSteps.creationDateText(0);
        String descriptionBefore = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.clickButtonDeleteNews(nameBefore);
        controlPanelScreenSteps.clickButtonCancelDeleteNews();

        String nameAfter = controlPanelScreenSteps.nameText(0);
        String publicationDateAfter = controlPanelScreenSteps.publicationDateText(0);
        String creationDateAfter = controlPanelScreenSteps.creationDateText(0);
        String descriptionAfter = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.compareDataOfFirstNewsBeforeAndAfterCancellingDeleting(nameBefore, nameAfter, publicationDateBefore,
                publicationDateAfter, creationDateBefore, creationDateAfter, descriptionBefore, descriptionAfter);
    }

    // Кейс 5.2.4 "Сортировка новостей в разделе Панель управления новостей"
    @Test
    public void shouldSortNewsOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String firstNewsNameBeforeSorting = controlPanelScreenSteps.nameText(0);
        String firstNewsDateBeforeSorting = controlPanelScreenSteps.publicationDateText(0);

        swipeToBottomControlPanelScreen();

        String lastNewsNameBeforeSorting = controlPanelScreenSteps.nameText(3);
        String lastNewsDateBeforeSorting = controlPanelScreenSteps.publicationDateText(3);

        swipeToTopControlPanelScreen();
        controlPanelScreenSteps.swipeDownToRefresh();
        controlPanelScreenSteps.clickButtonSortNews();

        String firstNewsNameAfterSorting = controlPanelScreenSteps.nameText(0);
        String firstNewsDateAfterSorting = controlPanelScreenSteps.publicationDateText(0);

        swipeToBottomControlPanelScreen();

        String lastNewsNameAfterSorting = controlPanelScreenSteps.nameText(3);
        String lastNewsDateAfterSorting = controlPanelScreenSteps.publicationDateText(3);

        controlPanelScreenSteps.checkSortingNewsIsSuccessful(firstNewsNameBeforeSorting, firstNewsDateBeforeSorting,
                lastNewsNameBeforeSorting, lastNewsDateBeforeSorting,
                firstNewsNameAfterSorting, firstNewsDateAfterSorting,
                lastNewsNameAfterSorting, lastNewsDateAfterSorting);
    }

    // Кейс 5.3.1 "Фильтрация новостей в разделе Панель управления новостей по категории Объявление"
    @Test
    public void shouldFilterNewsCategoryAnnouncementOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = "Объявление";
        createNewsPublicationToday(category);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);

        String categoryFilter = advancedFilterNewsScreenSteps.categoryText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String categoryNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(categoryFilter, categoryNews);
    }

    // Кейс 5.3.2 "Фильтрация новостей в разделе Панель управления новостей по категории Профсоюз"
    @Test
    public void shouldFilterNewsCategoryUnionOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = "Профсоюз";
        createNewsPublicationToday(category);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);

        String categoryFilter = advancedFilterNewsScreenSteps.categoryText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String categoryNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(categoryFilter, categoryNews);
    }

    // Кейс 5.3.3 "Фильтрация новостей в разделе Панель управления новостей по категории Праздник"
    @Test
    public void shouldFilterNewsCategoryHolidayOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = "Праздник";
        createNewsPublicationToday(category);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);

        String categoryFilter = advancedFilterNewsScreenSteps.categoryText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String categoryNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(categoryFilter, categoryNews);
    }

    // Кейс 5.3.4 "Фильтрация новостей в разделе Панель управления новостей по категории Массаж"
    @Test
    public void shouldFilterNewsCategoryMassageOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = "Массаж";
        createNewsPublicationToday(category);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);

        String categoryFilter = advancedFilterNewsScreenSteps.categoryText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String categoryNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(categoryFilter, categoryNews);
    }

    // Кейс 5.3.5 "Фильтрация новостей в разделе Панель управления новостей по категории Благодарность"
    @Test
    public void shouldFilterNewsCategoryGratitudeOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = "Благодарность";
        createNewsPublicationToday(category);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);

        String categoryFilter = advancedFilterNewsScreenSteps.categoryText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String categoryNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(categoryFilter, categoryNews);
    }

    // Кейс 5.3.6 "Фильтрация новостей в разделе Панель управления новостей по категории Нужна помощь"
    @Test
    public void shouldFilterNewsCategoryNeedHelpOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = "Нужна помощь";
        createNewsPublicationToday(category);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);

        String categoryFilter = advancedFilterNewsScreenSteps.categoryText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String categoryNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(categoryFilter, categoryNews);
    }

    // Кейс 5.3.7 "Фильтрация новостей в разделе Панель управления новостей с последующей отменой"
    @Test
    public void shouldNotFilterNewsAfterCancelFilteringOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String nameOneBefore = controlPanelScreenSteps.nameText(0);
        String nameTwoBefore = controlPanelScreenSteps.nameText(1);
        String nameThreeBefore = controlPanelScreenSteps.nameText(2);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), randomNewsCategory());
        advancedFilterNewsScreenSteps.fillTheStartDateToday();
        advancedFilterNewsScreenSteps.fillTheEndDateToday();
        advancedFilterNewsScreenSteps.clickOnCancelButton();

        String nameOneAfter = controlPanelScreenSteps.nameText(0);
        String nameTwoAfter = controlPanelScreenSteps.nameText(1);
        String nameThreeAfter = controlPanelScreenSteps.nameText(2);

        controlPanelScreenSteps.compareDataAfterCancellingFilter(nameOneBefore, nameOneAfter,
                nameTwoBefore, nameTwoAfter, nameThreeBefore, nameThreeAfter);
    }

    // Кейс 5.3.8 "Фильтрация активных новостей в разделе Панель управления новостей"
    @Test
    public void shouldFilterActiveNewsOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = randomNewsCategory();
        createNewsPublicationToday(category);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);
        advancedFilterNewsScreenSteps.clickOnInactiveCheckBox();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String nameNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(category, nameNews);
        controlPanelScreenSteps.checkStatusIsActive(0);
    }

    // Кейс 5.3.9 "Фильтрация неактивных новостей в разделе Панель управления новостей"
    @Test
    public void shouldFilterInactiveNewsOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String category = randomNewsCategory();
        createNewsPublicationToday(category);

        controlPanelScreenSteps.clickButtonEditNews(0);
        editNewsScreenSteps.clickOnSwitcher();
        editNewsScreenSteps.clickOnSaveButton();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), category);
        advancedFilterNewsScreenSteps.clickOnActiveCheckBox();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String nameNews = controlPanelScreenSteps.nameText(0);

        controlPanelScreenSteps.compareCategoryInFilterAndFilteredNews(category, nameNews);
        controlPanelScreenSteps.checkStatusIsInactive(0);
    }

    // Кейс 5.3.10 "Фильтрация новостей в разделе Панель управления новостей с неактивными чек-боксами рядом с Активна и Не активна"
    @Test
    public void shouldShowEmptyScreenWhenAllStatusesUnchecked() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), randomNewsCategory());
        advancedFilterNewsScreenSteps.clickOnActiveCheckBox();
        advancedFilterNewsScreenSteps.clickOnInactiveCheckBox();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        controlPanelScreenSteps.checkEmptyScreenIsDisplayed();
    }

    // Кейс 5.3.11 "Выбор даты в фильтрации новостей в разделе Панель управления новостей с последующей отменой"
    @Test
    public void shouldNotFillTheFieldsDateAfterCancel() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();

        advancedFilterNewsScreenSteps.fillTheStartDateAndCancel();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.checkTheFieldStartDateIsEmpty();

        advancedFilterNewsScreenSteps.fillTheEndDateAndCancel();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.checkTheFieldEndDateIsEmpty();
    }

    // Кейс 5.4.1 "Фильтрация новостей в разделе Панель управления новостей с вводом значения в поле Категория не из выпадающего списка"
    @Test
    public void shouldNotFilterNewsWithCategoryNotFromDropdown() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheFieldCategoryNotFromDropdown();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        advancedFilterNewsScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupChooseCategoryFromDropdown);
    }

    // Кейс 5.4.2 "Фильтрация новостей в разделе Панель управления новостей с вводом пробела в поле Категория"
    @Test
    public void shouldFilterNewsWithCategoryWhiteSpace() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String nameOneBefore = controlPanelScreenSteps.nameText(0);
        String nameTwoBefore = controlPanelScreenSteps.nameText(1);
        String nameThreeBefore = controlPanelScreenSteps.nameText(2);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheFieldCategoryWhiteSpace();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String nameOneAfter = controlPanelScreenSteps.nameText(0);
        String nameTwoAfter = controlPanelScreenSteps.nameText(1);
        String nameThreeAfter = controlPanelScreenSteps.nameText(2);

        controlPanelScreenSteps.compareDataAfterCancellingFilter(nameOneBefore, nameOneAfter,
                nameTwoBefore, nameTwoAfter, nameThreeBefore, nameThreeAfter);
    }

    // Кейс 5.4.3 "Фильтрация новостей в разделе Панель управления новостей с выбором даты с сегодняшнего дня и на неделю вперед"
    @Test
    public void shouldFilterNewsWithStartDateTodayAndEndDateInOneWeek() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationToday(randomNewsCategory());
        createNewsPublicationInOneWeek(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheStartDateToday();
        advancedFilterNewsScreenSteps.fillTheEndDateInOneWeek();

        String date1 = advancedFilterNewsScreenSteps.endDateText();
        String date2 = advancedFilterNewsScreenSteps.startDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String publicationDateFirstNews = controlPanelScreenSteps.publicationDateText(0);
        swipeToBottomControlPanelScreen();
        String publicationDateLastNews = controlPanelScreenSteps.publicationDateText(3);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDate(publicationDateFirstNews, date1, publicationDateLastNews, date2);
    }

    // Кейс 5.4.4 "Фильтрация новостей в разделе Панель управления новостей с выбором даты с завтрашнего дня и до вчерашнего дня"
    @Test
    public void shouldNotFilterNewsWithStartDateTomorrowAndEndDateYesterday() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheStartDateTomorrow();
        advancedFilterNewsScreenSteps.fillTheEndDateYesterday();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        advancedFilterNewsScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupWrongPeriod);
    }

    // Кейс 5.4.5 "Фильтрация новостей в разделе Панель управления новостей с выбором даты прошлого года"
    @Test
    public void shouldFilterNewsWithStartDateAYearAgoAndEndDateToday() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationToday(randomNewsCategory());
        createNewsPublicationAYearAgo(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheEndDateToday();
        advancedFilterNewsScreenSteps.fillTheStartDateAYearAgo();

        String date1 = advancedFilterNewsScreenSteps.endDateText();
        String date2 = advancedFilterNewsScreenSteps.startDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();

        String publicationDateFirstNews = controlPanelScreenSteps.publicationDateText(0);
        swipeToBottomControlPanelScreen();
        String publicationDateLastNews = controlPanelScreenSteps.publicationDateText(3);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDate(publicationDateFirstNews, date1, publicationDateLastNews, date2);
    }

    // Кейс 5.4.6 "Фильтрация новостей в разделе Панель управления новостей с выбором второй даты вчерашний день"
    @Test
    public void shouldFilterNewsWithEndDateYesterday() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationYesterday(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheEndDateYesterday();

        String date = advancedFilterNewsScreenSteps.endDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String publicationDateFirstNews = controlPanelScreenSteps.publicationDateText(0);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDateOneDate(publicationDateFirstNews, date);
    }

    // Кейс 5.4.7 "Фильтрация новостей в разделе Панель управления новостей с выбором второй даты сегодняшний день"
    @Test
    public void shouldFilterNewsWithEndDateToday() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationToday(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheEndDateToday();

        String date = advancedFilterNewsScreenSteps.endDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String publicationDateFirstNews = controlPanelScreenSteps.publicationDateText(0);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDateOneDate(publicationDateFirstNews, date);
    }

    // Кейс 5.4.8 "Фильтрация новостей в разделе Панель управления новостей с выбором второй даты завтрашний день"
    @Test
    public void shouldFilterNewsWithEndDateTomorrow() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationTomorrow(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheEndDateTomorrow();

        String date = advancedFilterNewsScreenSteps.endDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String publicationDateFirstNews = controlPanelScreenSteps.publicationDateText(0);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDateOneDate(publicationDateFirstNews, date);
    }

    // Кейс 5.4.9 "Фильтрация новостей в разделе Панель управления новостей с выбором первой даты вчерашний день"
    @Test
    public void shouldFilterNewsWithStartDateYesterday() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationYesterday(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheStartDateYesterday();

        String date = advancedFilterNewsScreenSteps.startDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        swipeToBottomControlPanelScreen();
        String publicationDateLastNews = controlPanelScreenSteps.publicationDateText(3);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDateOneDate(publicationDateLastNews, date);
    }

    // Кейс 5.4.10 "Фильтрация новостей в разделе Панель управления новостей с выбором первой даты сегодняшний день"
    @Test
    public void shouldFilterNewsWithStartDateToday() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationToday(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheStartDateToday();

        String date = advancedFilterNewsScreenSteps.startDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        swipeToBottomControlPanelScreen();
        String publicationDateLastNews = controlPanelScreenSteps.publicationDateText(3);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDateOneDate(publicationDateLastNews, date);
    }

    // Кейс 5.4.11 "Фильтрация новостей в разделе Панель управления новостей с выбором первой даты завтрашний день"
    @Test
    public void shouldFilterNewsWithStartDateTomorrow() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        createNewsPublicationTomorrow(randomNewsCategory());
        createNewsPublicationTomorrow(randomNewsCategory());
        createNewsPublicationTomorrow(randomNewsCategory());
        createNewsPublicationTomorrow(randomNewsCategory());

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.fillTheStartDateTomorrow();

        String date = advancedFilterNewsScreenSteps.startDateText();

        advancedFilterNewsScreenSteps.clickOnFilterButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        swipeToBottomControlPanelScreen();
        String publicationDateLastNews = controlPanelScreenSteps.publicationDateText(3);

        controlPanelScreenSteps.checkDataOfFilteredNewsByDateOneDate(publicationDateLastNews, date);
    }

    // Кейс 5.5.1 "Редактирование новости в разделе Панель управление новостей с выбором категории из выпадающего списка"
    @Test
    public void shouldEditNewsWithCategoryFromDropdown() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String categoryToCreate = "Объявление";
        createNewsPublicationToday(categoryToCreate);

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), categoryToCreate);
        advancedFilterNewsScreenSteps.fillTheStartDateToday();
        advancedFilterNewsScreenSteps.fillTheEndDateToday();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        controlPanelScreenSteps.clickButtonEditNews(0);
        editNewsScreenSteps.checkEditNewsScreenIsDisplayed();

        String categoryNew = randomNewsCategoryExceptAnnouncement();

        editNewsScreenSteps.fillTheFieldCategory(categoryNew);
        editNewsScreenSteps.fillTheFieldHeader(categoryNew);
        editNewsScreenSteps.fillTheFieldDateTomorrow();
        editNewsScreenSteps.fillTheFieldTimeCurrentTimePlusOneHour();
        editNewsScreenSteps.fillTheFieldDescription(textWithCyrillicSymbols(15));

        String category = editNewsScreenSteps.categoryText();
        String name = editNewsScreenSteps.headerText();
        String publicationDate = editNewsScreenSteps.publicationDateText();
        String time = editNewsScreenSteps.timeText();
        String description = editNewsScreenSteps.descriptionText();

        editNewsScreenSteps.clickOnSaveButton();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.chooseCategory(ActivityTestRule.getActivity(), categoryNew);
        advancedFilterNewsScreenSteps.fillTheStartDateTomorrow();
        advancedFilterNewsScreenSteps.fillTheEndDateTomorrow();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        controlPanelScreenSteps.clickOnNewsItem(0);

        String editedName = controlPanelScreenSteps.nameText(0);
        String editedPublicationDate = controlPanelScreenSteps.publicationDateText(0);
        String editedDescription = controlPanelScreenSteps.descriptionText(0);
        controlPanelScreenSteps.clickButtonEditNews(0);
        String editedCategory = editNewsScreenSteps.categoryText();
        String editedTime = editNewsScreenSteps.timeText();

        controlPanelScreenSteps.compareDataAfterEditingNews(name, editedName, publicationDate, editedPublicationDate,
                time, editedTime, description, editedDescription, category, editedCategory);
    }

    // Кейс 5.5.2 "Редактирование новости в разделе Панель управление новостей с последующей отменой"
    @Test
    public void shouldNotEditNewsAfterCancelEditing() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickOnNewsItem(0);

        String nameBefore = controlPanelScreenSteps.nameText(0);
        String publicationDateBefore = controlPanelScreenSteps.publicationDateText(0);
        String descriptionBefore = controlPanelScreenSteps.descriptionText(0);
        controlPanelScreenSteps.clickButtonEditNews(0);
        editNewsScreenSteps.checkEditNewsScreenIsDisplayed();
        String categoryBefore = editNewsScreenSteps.categoryText();
        String timeBefore = editNewsScreenSteps.timeText();

        String categoryNew = randomNewsCategoryExceptAnnouncement();

        editNewsScreenSteps.fillTheFieldCategory(categoryNew);
        editNewsScreenSteps.fillTheFieldHeader(categoryNew);
        editNewsScreenSteps.fillTheFieldDateYesterday();
        editNewsScreenSteps.fillTheFieldTimeCurrentTimePlusOneHour();
        editNewsScreenSteps.fillTheFieldDescription(textWithCyrillicSymbols(15));

        editNewsScreenSteps.clickOnCancelButton();
        editNewsScreenSteps.clickOnOkExitButton();

        String nameAfter = controlPanelScreenSteps.nameText(0);
        String publicationDateAfter = controlPanelScreenSteps.publicationDateText(0);
        String descriptionAfter = controlPanelScreenSteps.descriptionText(0);
        controlPanelScreenSteps.clickButtonEditNews(0);
        String categoryAfter = editNewsScreenSteps.categoryText();
        String timeAfter = editNewsScreenSteps.timeText();

        controlPanelScreenSteps.compareDataAfterCancellingEditingNews(nameBefore, nameAfter, publicationDateBefore, publicationDateAfter,
                timeBefore, timeAfter, descriptionBefore, descriptionAfter, categoryBefore, categoryAfter);
    }

    // Кейс 5.5.3 "Редактирование неактивной новости в разделе Панель управление новостей с переключением новости на активную"
    @Test
    public void shouldEditActiveNewsToInactive() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.clickOnActiveCheckBox();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        controlPanelScreenSteps.clickOnNewsItem(0);
        controlPanelScreenSteps.checkStatusIsInactive(0);
        String descriptionFirstNewsBefore = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.clickButtonEditNews(0);
        editNewsScreenSteps.checkSwitcherInactive();
        editNewsScreenSteps.clickOnSwitcher();
        editNewsScreenSteps.checkSwitcherActive();
        editNewsScreenSteps.clickOnSaveButton();

        controlPanelScreenSteps.clickOnNewsItem(0);
        String descriptionFirstNewsAfter = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.checkNewsStatusEdited(descriptionFirstNewsBefore, descriptionFirstNewsAfter);
    }

    // Кейс 5.5.4 "Редактирование активной новости в разделе Панель управление новостей с переключением новости на неактивную"
    @Test
    public void shouldEditInactiveNewsToActive() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.checkAdvancedFilterNewsScreenIsDisplayed();
        advancedFilterNewsScreenSteps.clickOnInactiveCheckBox();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        controlPanelScreenSteps.clickOnNewsItem(0);
        controlPanelScreenSteps.checkStatusIsActive(0);
        String descriptionFirstNewsBefore = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.clickButtonEditNews(0);
        editNewsScreenSteps.checkSwitcherActive();
        editNewsScreenSteps.clickOnSwitcher();
        editNewsScreenSteps.checkSwitcherInactive();
        editNewsScreenSteps.clickOnSaveButton();

        controlPanelScreenSteps.clickOnNewsItem(0);
        String descriptionFirstNewsAfter = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.checkNewsStatusEdited(descriptionFirstNewsBefore, descriptionFirstNewsAfter);
    }

    // Кейс 5.5.5 "Редактирование активной новости в разделе Панель управление новостей с выбором категории не из выпадающего списка"
    @Test
    public void shouldNotEditNewsWithCategoryNotFromDropdown() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonEditNews(0);
        editNewsScreenSteps.checkEditNewsScreenIsDisplayed();

        editNewsScreenSteps.fillTheFieldCategory(textWithCyrillicSymbols(10));
        editNewsScreenSteps.clickOnSaveButton();

        editNewsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackChooseCategoryFromDropdown);
    }

    // Кейс 5.6.1 "Добавление новой новости в разделе Панель управления новостей  с валидными данными и с последующим сохранением"
    @Test
    public void shouldCreateNewsWithValidData() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.chooseCategoryFromDropdown(ActivityTestRule.getActivity(), randomNewsCategory());
        createNewsScreenSteps.fillTheFieldDateToday();
        createNewsScreenSteps.fillTheFieldTimeCurrentTimePlusOneHour();
        createNewsScreenSteps.fillTheFieldDescription(textWithCyrillicSymbols(15));

        String nameCreated = createNewsScreenSteps.headerText();
        String publicationDateCreated = createNewsScreenSteps.publicationDateText();
        String descriptionCreated = createNewsScreenSteps.descriptionText();

        createNewsScreenSteps.clickOnSaveButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.fillTheStartDateToday();
        advancedFilterNewsScreenSteps.fillTheEndDateToday();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        int position = DataHelper.Search.findTheCreatedNews(descriptionCreated);

        String nameFound = controlPanelScreenSteps.nameText(position);
        String publicationDateFound = controlPanelScreenSteps.publicationDateText(position);
        String descriptionFound = controlPanelScreenSteps.descriptionText(position);

        controlPanelScreenSteps.compareDataOfCreatedAndFoundNews(nameCreated, nameFound, publicationDateCreated,
                publicationDateFound, descriptionCreated, descriptionFound);
    }

    // Кейс 5.6.2 "Добавление новой новости в разделе Панель управления новостей с последующей отменой"
    @Test
    public void shouldNotCreateNewsAfterCancellingCreatingNews() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickOnNewsItem(0);
        String nameBefore = controlPanelScreenSteps.nameText(0);
        String publicationDateBefore = controlPanelScreenSteps.publicationDateText(0);
        String descriptionBefore = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.chooseCategoryFromDropdown(ActivityTestRule.getActivity(), randomNewsCategory());
        createNewsScreenSteps.fillTheFieldDate(publicationDateBefore);
        createNewsScreenSteps.fillTheFieldTimeCurrentTimePlusOneHour();
        createNewsScreenSteps.fillTheFieldDescription(textWithCyrillicSymbols(15));

        createNewsScreenSteps.clickOnCancelButton();
        createNewsScreenSteps.clickOnOkExitButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        String nameAfter = controlPanelScreenSteps.nameText(0);
        String publicationDateAfter = controlPanelScreenSteps.publicationDateText(0);
        String descriptionAfter = controlPanelScreenSteps.descriptionText(0);

        controlPanelScreenSteps.compareDataAfterCancellingCreatingNews(nameBefore, nameAfter, publicationDateBefore, publicationDateAfter,
                descriptionBefore, descriptionAfter);
    }

    // Кейс 5.7.1 "Добавление новой новости в разделе Панель управления новостей с пустыми полями"
    @Test
    public void shouldNotCreateNewsWithEmptyFields() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.clickOnSaveButton();
        createNewsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackFillEmptyFields);
    }

    // Кейс 5.7.2 "Добавление новой новости в разделе Панель управления новостей с вводом значения в поле Категория не из выпадающего списка"
    @Test
    public void shouldNotCreateNewsWithCategoryNotFromDropdown() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.fillTheFieldCategory(textWithCyrillicSymbols(10));
        createNewsScreenSteps.fillTheFieldHeader(textWithCyrillicSymbols(10));
        createNewsScreenSteps.fillTheFieldDateToday();
        createNewsScreenSteps.fillTheFieldTimeCurrentTime();
        createNewsScreenSteps.fillTheFieldDescription(textWithCyrillicSymbols(15));

        createNewsScreenSteps.clickOnSaveButton();
        createNewsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackChooseCategoryFromDropdown);
    }

    // Кейс 5.7.4 "Добавление новой новости в разделе Панель управления новостей с выбором завтрашней даты публикации"
    @Test
    public void shouldCreateNewsWithDateTomorrow() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.chooseCategoryFromDropdown(ActivityTestRule.getActivity(), randomNewsCategory());
        createNewsScreenSteps.fillTheFieldDateTomorrow();
        createNewsScreenSteps.fillTheFieldTimeCurrentTime();
        createNewsScreenSteps.fillTheFieldDescription(textWithCyrillicSymbols(15));

        String nameCreated = createNewsScreenSteps.headerText();
        String publicationDateCreated = createNewsScreenSteps.publicationDateText();
        String descriptionCreated = createNewsScreenSteps.descriptionText();

        createNewsScreenSteps.clickOnSaveButton();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.goToAdvancedFilterNewsScreen();
        advancedFilterNewsScreenSteps.fillTheStartDateTomorrow();
        advancedFilterNewsScreenSteps.fillTheEndDateTomorrow();
        advancedFilterNewsScreenSteps.clickOnFilterButton();

        int position = DataHelper.Search.findTheCreatedNews(descriptionCreated);

        String nameFound = controlPanelScreenSteps.nameText(position);
        String publicationDateFound = controlPanelScreenSteps.publicationDateText(position);
        String descriptionFound = controlPanelScreenSteps.descriptionText(position);

        controlPanelScreenSteps.compareDataOfCreatedAndFoundNews(nameCreated, nameFound, publicationDateCreated,
                publicationDateFound, descriptionCreated, descriptionFound);
    }

    // Кейс 5.7.5 "Добавление новой новости в разделе Панель управления новостей с выбором сегодняшней даты публикации и прошедшего времени"
    @Test
    public void shouldNotCreateNewsWithPastTime() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.chooseCategoryFromDropdown(ActivityTestRule.getActivity(), randomNewsCategory());
        createNewsScreenSteps.fillTheFieldDateToday();
        createNewsScreenSteps.fillTheFieldTimeCurrentTimeMinusOneHour();
        createNewsScreenSteps.fillTheFieldDescription(textWithCyrillicSymbols(15));

        createNewsScreenSteps.clickOnSaveButton();
        createNewsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), popupWrongTimeForPublicationNews);
    }

    // Кейс 5.7.6 "Добавление новой новости в разделе Панель управления новостей с выбором несуществующего времени"
    @Test
    public void shouldNotCreateNewsWithWithInvalidTime() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.fillTheFieldTimeInvalidTime();
        watchScreenSteps.checkValidationInvalidTimeIsDisplayed(ActivityTestRule.getActivity(), validationInvalidTime);
    }

    // Кейс 5.7.7 "Добавление новой новости в разделе Панель управления новостей с тапом на чек-бокс "Активна""
    @Test
    public void shouldNotCreateInactiveNews() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();

        controlPanelScreenSteps.clickButtonCreateNews();
        createNewsScreenSteps.checkCreateNewsScreenIsDisplayed();

        createNewsScreenSteps.clickOnSwitcher();
        createNewsScreenSteps.checkSwitcherActive();
    }
}
