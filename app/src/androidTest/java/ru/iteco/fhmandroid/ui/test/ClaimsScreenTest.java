package ru.iteco.fhmandroid.ui.test;

import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomExecutor;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Swipe.swipeToBottomClaimScreen;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Swipe.swipeToTopClaimScreen;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithCyrillicSymbols;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithLatinSymbols;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithSpecialSymbolsAndNumbers;
import static ru.iteco.fhmandroid.ui.data.DataHelper.popupChooseExecutorFromDropdown;
import static ru.iteco.fhmandroid.ui.data.DataHelper.popupFillEmptyFields;
import static ru.iteco.fhmandroid.ui.data.DataHelper.popupTimeForClaimShouldBeAtLeast1Hour;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackCannotEditClaim;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackCannotEditStatusOfClaim;
import static ru.iteco.fhmandroid.ui.data.DataHelper.snackEmptyField;
import static ru.iteco.fhmandroid.ui.data.DataHelper.validationInvalidTime;

import androidx.test.filters.LargeTest;

import org.junit.Test;

import ru.iteco.fhmandroid.ui.custom.BaseAndroidTest;
import ru.iteco.fhmandroid.ui.steps.ClaimsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.CommentClaimScreenSteps;
import ru.iteco.fhmandroid.ui.steps.CreateClaimScreenSteps;
import ru.iteco.fhmandroid.ui.steps.EditClaimScreenSteps;
import ru.iteco.fhmandroid.ui.steps.FilterClaimsScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fhmandroid.ui.steps.WatchScreenSteps;

@LargeTest
public class ClaimsScreenTest extends BaseAndroidTest {

    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    ClaimsScreenSteps claimsScreenSteps = new ClaimsScreenSteps();
    FilterClaimsScreenSteps filterClaimsScreenSteps = new FilterClaimsScreenSteps();
    CommentClaimScreenSteps commentClaimScreenSteps = new CommentClaimScreenSteps();
    EditClaimScreenSteps editClaimScreenSteps = new EditClaimScreenSteps();
    CreateClaimScreenSteps createClaimScreenSteps = new CreateClaimScreenSteps();
    WatchScreenSteps watchScreenSteps = new WatchScreenSteps();

    // Кейс 4.1.1 "Добавление комментария, состоящего из кириллических символов, к заявке в статусе "В работе" с последующим сохранением"
    @Test
    public void shouldAddCommentWithCyrillicSymbolsToClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();
        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonAddCommentToClaim();
        commentClaimScreenSteps.checkCommentClaimScreenIsDisplayed();

        String comment = textWithCyrillicSymbols(10);
        commentClaimScreenSteps.fillTheFieldCommentAndSave(comment);
        swipeToBottomClaimScreen();
        claimsScreenSteps.checkAddedCommentIsVisible(comment);
    }

    // Кейс 4.1.2 "Добавление комментария к заявке в статусе "В работе" с последующей отменой"
    @Test
    public void shouldNotAddCommentWhenCancel() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();
        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonAddCommentToClaim();
        commentClaimScreenSteps.checkCommentClaimScreenIsDisplayed();

        String comment = textWithCyrillicSymbols(10);
        commentClaimScreenSteps.fillTheFieldCommentAndCancel(comment);
        claimsScreenSteps.checkNotAddedCommentNotVisible(comment);
    }

    // Кейс 4.1.3 "Добавление комментария к заявке в статусе "В работе" с пустым полем Комментарий"
    @Test
    public void shouldWarnWhenCommentFieldIsEmpty() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();
        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonAddCommentToClaim();
        commentClaimScreenSteps.checkCommentClaimScreenIsDisplayed();

        commentClaimScreenSteps.clickSaveButtonWithoutComment();
        commentClaimScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackEmptyField);
    }

    // Кейс 4.1.4 "Добавление комментария, состоящего из латинских символов, к заявке в статусе "В работе" с последующим сохранением"
    @Test
    public void shouldAddCommentWithLatinSymbolsToClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();
        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonAddCommentToClaim();
        commentClaimScreenSteps.checkCommentClaimScreenIsDisplayed();

        String comment = textWithLatinSymbols(10);
        commentClaimScreenSteps.fillTheFieldCommentAndSave(comment);
        swipeToBottomClaimScreen();
        claimsScreenSteps.checkAddedCommentIsVisible(comment);
    }

    // Кейс 4.1.5 "Добавление комментария, состоящего из цифр, знаков препинания и специальных символов, к заявке в статусе "В работе" с последующим сохранением"
    @Test
    public void shouldAddCommentWithSpecialSymbolsAndNumbersToClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();
        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonAddCommentToClaim();
        commentClaimScreenSteps.checkCommentClaimScreenIsDisplayed();

        String comment = textWithSpecialSymbolsAndNumbers(10);
        commentClaimScreenSteps.fillTheFieldCommentAndSave(comment);
        swipeToBottomClaimScreen();
        claimsScreenSteps.checkAddedCommentIsVisible(comment);
    }

    // Кейс 4.1.6 "Редактирование комментария к заявке в статусе "В работе" с последующим сохранением"
    @Test
    public void shouldEditCommentInClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();
        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonAddCommentToClaim();
        commentClaimScreenSteps.checkCommentClaimScreenIsDisplayed();

        String comment1 = textWithCyrillicSymbols(10);
        String comment2 = textWithCyrillicSymbols(10);
        commentClaimScreenSteps.fillTheFieldCommentAndSave(comment1);
        swipeToBottomClaimScreen();
        claimsScreenSteps.checkAddedCommentIsVisible(comment1);

        claimsScreenSteps.clickButtonEditComment(0);
        commentClaimScreenSteps.fillTheFieldCommentAndSave(comment2);
        claimsScreenSteps.checkCommentWasEdited(comment2);
    }

    // Кейс 4.1.7 "Переведение заявки в статусе "В работе" в статус "Открыта" с заполнением поля Комментарий"
    @Test
    public void shouldChangeStatusFromInProgressToOpened() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.clickTrowOff();

        String comment = textWithCyrillicSymbols(10);
        claimsScreenSteps.fillTheFieldCommentAndSave(comment);
        claimsScreenSteps.checkStatusOpened();
        swipeToBottomClaimScreen();
        claimsScreenSteps.checkAddedCommentIsVisible(comment);
    }

    // Кейс 4.1.8 "Переведение заявки в статусе "В работе" в статус "Выполнена" с заполнением поля Комментарий"
    @Test
    public void shouldChangeStatusFromInProgressToExecuted() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.clickExecute();

        String comment = textWithCyrillicSymbols(10);
        claimsScreenSteps.fillTheFieldCommentAndSave(comment);
        claimsScreenSteps.checkStatusExecuted();
        swipeToBottomClaimScreen();
        claimsScreenSteps.checkAddedCommentIsVisible(comment);
    }

    // Кейс 4.1.9 "Переведение заявки в статусе "В работе" в статус "Выполнена" без заполнения поля Комментарий"
    @Test
    public void shouldNotChangeStatusFromInProgressToExecutedWithoutComment() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();

        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.clickExecute();

        claimsScreenSteps.clickOkButtonWithoutComment();
        claimsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackEmptyField);
    }

    // Кейс 4.1.10 "Переведение заявки в статусе "В работе" в статус "Выполнена" с заполнением поля Комментарий с последующей отменой"
    @Test
    public void shouldNotChangeStatusFromInProgressToExecutedAfterCancelingComment() {
        mainScreenSteps.goToSectionClaimsFromMainMenu();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.clickExecute();

        String comment = textWithCyrillicSymbols(10);
        claimsScreenSteps.fillTheFieldCommentAndCancel(comment);
        claimsScreenSteps.checkTheAbsenceOfComment(comment);
        swipeToTopClaimScreen();
        claimsScreenSteps.checkStatusInProgress();
    }

    // Кейс 4.1.11 "Редактирование заявки в статусе "В работе""
    @Test
    public void shouldNotEditClaimInProgress() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditClaim();
        claimsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackCannotEditClaim);
    }

    // Кейс 4.1.12 "Переход к экрану детального описания заявки в статусе "В работе" и возврат к списку заявок через стрелку Назад"
    @Test
    public void shouldReturnToClaimsScreenFromScreenOfDetailedDescriptionClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsInProgress();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusInProgress();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonReturn();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
    }

    // Кейс 4.2.1 "Переведение заявки в статусе "Открыта" в статус "В работе""
    @Test
    public void shouldChangeStatusFromOpenedToInProgress() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsOpened();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusOpened();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.clickTakeToWork();

        swipeToTopClaimScreen();
        claimsScreenSteps.checkStatusInProgress();
    }

    // Кейс 4.2.2 "Переведение заявки в статусе "Открыта" в статус "Отменена""
    @Test
    public void shouldChangeStatusFromOpenedToCancelled() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsOpened();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusOpened();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.clickCancel();

        swipeToTopClaimScreen();
        claimsScreenSteps.checkStatusCanceled();
    }

    // Кейс 4.3.1 "Изменение статуса заявки в статусе "Выполнена""
    @Test
    public void shouldNotChangeStatusOfExecutedClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsExecuted();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusExecuted();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackCannotEditStatusOfClaim);
    }

    // Кейс 4.3.2 "Редактирование заявки в статусе "Выполнена""
    @Test
    public void shouldNotEditExecutedClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsExecuted();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusExecuted();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditClaim();
        claimsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackCannotEditClaim);
    }

    // Кейс 4.4.1 "Изменение статуса заявки в статусе "Отменена""
    @Test
    public void shouldNotChangeStatusOfCancelledClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsCancelled();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusCanceled();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditStatusOfClaim();
        claimsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackCannotEditStatusOfClaim);
    }

    // Кейс 4.4.2 "Редактирование заявки в статусе "Отменена""
    @Test
    public void shouldNotEditCancelledClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsCancelled();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusCanceled();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditClaim();
        claimsScreenSteps.checkSnackIsVisible(ActivityTestRule.getActivity(), snackCannotEditClaim);
    }

    // Кейс 4.5.1 "Редактирование заявки в статусе "Открыта""
    @Test
    public void shouldEditOpenedClaim() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsOpened();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusOpened();

        String themeClaimBefore = claimsScreenSteps.themeText();
        String executorClaimBefore = claimsScreenSteps.executorText();
        String dateClaimBefore = claimsScreenSteps.planDateText();
        String timeClaimBefore = claimsScreenSteps.planTimeText();
        String descriptionClaimBefore = claimsScreenSteps.descriptionText();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditClaim();
        editClaimScreenSteps.checkEditClaimScreenIsDisplayed();
        editClaimScreenSteps.fillTheFieldsWithNewValidData();
        editClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        editClaimScreenSteps.clickOnSaveButton();

        String themeClaimAfter = claimsScreenSteps.themeText();
        String executorClaimAfter = claimsScreenSteps.executorText();
        String dateClaimAfter = claimsScreenSteps.planDateText();
        String timeClaimAfter = claimsScreenSteps.planTimeText();
        String descriptionClaimAfter = claimsScreenSteps.descriptionText();

        claimsScreenSteps.checkStatusInProgress();
        editClaimScreenSteps.compareDataBeforeEditingAndAfter(themeClaimBefore, themeClaimAfter, executorClaimBefore, executorClaimAfter,
                dateClaimBefore, dateClaimAfter, timeClaimBefore, timeClaimAfter, descriptionClaimBefore, descriptionClaimAfter);
    }

    // Кейс 4.5.2 "Редактирование заявки в статусе "Открыта" с последующей отменой"
    @Test
    public void shouldNotEditOpenedClaimAfterCancelEditing() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.filterClaimsOpened();

        claimsScreenSteps.clickOnClaim();
        claimsScreenSteps.checkScreenOfDetailedDescriptionClaimIsDisplayed();
        claimsScreenSteps.checkStatusOpened();

        String themeClaimBefore = claimsScreenSteps.themeText();
        String executorClaimBefore = claimsScreenSteps.executorText();
        String dateClaimBefore = claimsScreenSteps.planDateText();
        String timeClaimBefore = claimsScreenSteps.planTimeText();
        String descriptionClaimBefore = claimsScreenSteps.descriptionText();

        swipeToBottomClaimScreen();
        claimsScreenSteps.clickButtonEditClaim();
        editClaimScreenSteps.checkEditClaimScreenIsDisplayed();
        editClaimScreenSteps.fillTheFieldsWithNewValidData();
        editClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        editClaimScreenSteps.clickOnCancelButton();
        editClaimScreenSteps.clickOnOkExitButton();

        String themeClaimAfter = claimsScreenSteps.themeText();
        String executorClaimAfter = claimsScreenSteps.executorText();
        String dateClaimAfter = claimsScreenSteps.planDateText();
        String timeClaimAfter = claimsScreenSteps.planTimeText();
        String descriptionClaimAfter = claimsScreenSteps.descriptionText();

        claimsScreenSteps.checkStatusOpened();
        editClaimScreenSteps.compareDataBeforeAndAfterWhenCancelledEditing(themeClaimBefore, themeClaimAfter, executorClaimBefore, executorClaimAfter,
                dateClaimBefore, dateClaimAfter, timeClaimBefore, timeClaimAfter, descriptionClaimBefore, descriptionClaimAfter);
    }

    // Кейс 4.6.4 "Фильтрация заявок без выбора статусов"
    @Test
    public void shouldShowEmptyScreenWhenAllStatusesUnchecked() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.uncheckAllStatuses();

        claimsScreenSteps.checkEmptyScreenIsDisplayed();
    }

    // Кейс 4.6.5 "Фильтрация заявок по статусу с последующей отменой"
    @Test
    public void shouldNotFilterAfterCancelFiltering() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        String themeOneBefore = claimsScreenSteps.nameClaimText(0);
        String themeTwoBefore = claimsScreenSteps.nameClaimText(1);
        String themeThreeBefore = claimsScreenSteps.nameClaimText(2);

        claimsScreenSteps.goToFilterClaimsScreen();
        filterClaimsScreenSteps.checkFilterClaimsScreenIsDisplayed();
        filterClaimsScreenSteps.clickOnCancelButton();

        String themeOneAfter = claimsScreenSteps.nameClaimText(0);
        String themeTwoAfter = claimsScreenSteps.nameClaimText(1);
        String themeThreeAfter = claimsScreenSteps.nameClaimText(2);

        claimsScreenSteps.compareDataBeforeAndAfterWhenCancelledFilter(themeOneBefore, themeOneAfter,
                themeTwoBefore, themeTwoAfter, themeThreeBefore, themeThreeAfter);

    }

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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.3 "Создание новой заявки с темой, состоящей из 51 кириллического символа"
    @Test
    public void shouldNotCreateClaimWithTheme51CyrillicSymbols() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldThemeCyrillicSymbols(51);
        createClaimScreenSteps.compareNumberOfEnteredSymbolsAndSymbolsInTheField();
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.6 "Создание новой заявки с темой, состоящей из пробела"
    @Test
    public void shouldNotCreateClaimWithThemeWhitespace() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldThemeWhiteSpace();

        createClaimScreenSteps.clickOnSaveButton();
        createClaimScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupFillEmptyFields);
    }

    // Кейс 4.8.8 "Создание новой заявки с вводом значения в поле Исполнитель не из выпадающего списка"
    @Test
    public void shouldNotCreateClaimWithExecutorNotFromDropdown() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.fillTheFieldExecutorNotFromDropdown();

        createClaimScreenSteps.clickOnSaveButton();
        createClaimScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupChooseExecutorFromDropdown);
    }

    // Кейс 4.8.9 "Создание новой заявки с выбором текущей даты и текущего времени"
    @Test
    public void shouldNotCreateClaimWithCurrentTime() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldTimeCurrentTime();

        createClaimScreenSteps.clickOnSaveButton();
        createClaimScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupTimeForClaimShouldBeAtLeast1Hour);
    }

    // Кейс 4.8.10 "Создание новой заявки с выбором текущей даты и прошедшего времени"
    @Test
    public void shouldNotCreateClaimWithPastTime() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldTimePastTime();

        createClaimScreenSteps.clickOnSaveButton();
        createClaimScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupTimeForClaimShouldBeAtLeast1Hour);
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.15 "Создание новой заявки с вводом несуществующего времени"
    @Test
    public void shouldNotCreateClaimWithInvalidTime() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldTimeInvalidTime();
        watchScreenSteps.checkValidationInvalidTimeIsDisplayed(ActivityTestRule.getActivity(), validationInvalidTime);
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
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
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();
        claimsScreenSteps.findCreatedClaim(themeCreated);

        String themeFound = claimsScreenSteps.themeText();
        String executorFound = claimsScreenSteps.executorText();
        String dateFound = claimsScreenSteps.planDateText();
        String timeFound = claimsScreenSteps.planTimeText();
        String descriptionFound = claimsScreenSteps.descriptionText();

        claimsScreenSteps.compareDataOfCreatedAndFoundClaims(themeCreated, themeFound, executorCreated, executorFound,
                dateCreated, dateFound, timeCreated, timeFound, descriptionCreated, descriptionFound);
    }

    // Кейс 4.8.18 "Создание новой заявки с описанием состоящим из пробела"
    @Test
    public void shouldNotCreateClaimWithDescriptionWhitespace() {
        mainScreenSteps.clickOnCreateClaimButton();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.fillTheFieldsWithValidData();
        createClaimScreenSteps.chooseExecutorFromDropdown(ActivityTestRule.getActivity(), randomExecutor());
        createClaimScreenSteps.fillTheFieldDescriptionWhiteSpace();

        createClaimScreenSteps.clickOnSaveButton();
        createClaimScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupFillEmptyFields);
    }

    // Кейс 4.8.19 "Создание новой заявки с пустыми полями"
    @Test
    public void shouldNotCreateClaimWithEmptyFields() {
        mainScreenSteps.clickOnAllClaimsButton();
        claimsScreenSteps.checkClaimsScreenIsDisplayed();

        claimsScreenSteps.clickButtonCreateClaim();
        createClaimScreenSteps.checkCreateClaimScreenIsDisplayed();

        createClaimScreenSteps.clickOnSaveButton();
        createClaimScreenSteps.checkPopupIsVisible(ActivityTestRule.getActivity(), popupFillEmptyFields);
    }
}
