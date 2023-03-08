package ru.iteco.fhmandroid.ui.test;

import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomNewsCategory;
import static ru.iteco.fhmandroid.ui.data.DataHelper.createNewsPublicationInOneYear;

import org.junit.Test;

import ru.iteco.fhmandroid.ui.custom.BaseAndroidTest;
import ru.iteco.fhmandroid.ui.steps.ControlPanelScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fhmandroid.ui.steps.NewsScreenSteps;

// Эти тесты необходимо выполнять на устройстве с Android 13 (API 33)

public class NewsScreenAPI33Test extends BaseAndroidTest {

    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    NewsScreenSteps newsScreenSteps = new NewsScreenSteps();
    ControlPanelScreenSteps controlPanelScreenSteps = new ControlPanelScreenSteps();

    // Кейс 5.2.2 "Удаление новости в разделе Панель управления новостей"
    @Test
    public void shouldDeleteNewsOnControlPanelScreen() {
        mainScreenSteps.clickOnAllNewsButton();
        newsScreenSteps.checkNewsScreenIsDisplayed();
        newsScreenSteps.goToControlPanelScreen();
        controlPanelScreenSteps.checkControlPanelScreenIsDisplayed();
        String category = randomNewsCategory();
        createNewsPublicationInOneYear(category);
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
}
