package ru.iteco.fhmandroid.ui.test;

import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomExecutor;

import androidx.test.filters.LargeTest;

import org.junit.Test;

import ru.iteco.fhmandroid.ui.custom.BaseAndroidTest;
import ru.iteco.fhmandroid.ui.steps.ClaimsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.CreateClaimScreenSteps;
import ru.iteco.fhmandroid.ui.steps.FilterClaimsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;

// Эти тесты необходимо выполнять на устройстве с Android 13 (API 33)

@LargeTest
public class ClaimsScreenAPI33Test extends BaseAndroidTest {

    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    ClaimsScreenSteps claimsScreenSteps = new ClaimsScreenSteps();
    FilterClaimsScreenSteps filterClaimsScreenSteps = new FilterClaimsScreenSteps();
    CreateClaimScreenSteps createClaimScreenSteps = new CreateClaimScreenSteps();


    // Кейс 4.7.1 "Создание новой заявки с валидными данными"
    @Test
    public void shouldCreateClaimWithValidData() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.7.2 "Создание новой заявки с валидными данными с последующей отменой"
    @Test
    public void shouldNotCreateClaimAfterCancelCreating() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());

        String themeCreated = createClaimScreenSteps.themeText();

        createClaimScreenSteps.clickOnCancelButton();
        createClaimScreenSteps.clickOnOkExitButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.checkTheAbsenceOfUncreatedClaim(themeCreated);
    }

    // Кейс 4.8.1 "Создание новой заявки с темой, состоящей из 49 кириллических символов"
    @Test
    public void shouldCreateClaimWithTheme49CyrillicSymbols() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldThemeCyrillicSymbols(49);

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.2 "Создание новой заявки с темой, состоящей из 50 кириллических символов"
    @Test
    public void shouldCreateClaimWithTheme50CyrillicSymbols() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldThemeCyrillicSymbols(50);

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.4 "Создание новой заявки с темой, состоящей из 50 латинских символов"
    @Test
    public void shouldCreateClaimWithTheme50LatinSymbols() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldThemeLatinSymbols(50);

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.5 "Создание новой заявки с темой, состоящей из цифр, знаков препинания и специальных символов"
    @Test
    public void shouldCreateClaimWithThemeSpecialSymbolsAndNumbers() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldThemeSpecialSymbolsAndNumbers();

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.12 "Создание новой заявки с выбором завтрашней даты"
    @Test
    public void shouldCreateClaimWithDateTomorrow() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldDateTomorrow();

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.13 "Создание новой заявки с выбором даты через месяц"
    @Test
    public void shouldCreateClaimWithDateInMonth() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldDateInOneMonth();

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.14 "Создание новой заявки с выбором даты через год"
    @Test
    public void shouldCreateClaimWithDateInYear() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldDateInOneYear();

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.16 "Создание новой заявки с описанием состоящим из латинских символов"
    @Test
    public void shouldCreateClaimWithDescriptionLatinSymbols() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldDescriptionLatinSymbols(25);

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.17 "Создание новой заявки с описанием состоящим из цифр, знаков препинания и специальных символов"
    @Test
    public void shouldCreateClaimWithDescriptionSpecialSymbolsAndNumbers() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldDescriptionSpecialSymbolsAndNumbers(25);

        String themeCreated = createClaimScreenSteps.themeText();
        String executorCreated = createClaimScreenSteps.executorText();
        String dateCreated = createClaimScreenSteps.dateText();
        String timeCreated = createClaimScreenSteps.timeText();
        String descriptionCreated = createClaimScreenSteps.descriptionText();

        createClaimScreenSteps.clickOnSaveButton();
        mainScreenSteps.checkMainScreenIsDisplayed();

        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.filterClaimsInProgress();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }
}
