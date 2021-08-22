package ru.appline.framework.managers;


import ru.appline.framework.pages.ContributionPage;
import ru.appline.framework.pages.HomePage;


public class PageManager {
    private PageManager() {
    }

    private static PageManager pageManager;

    private HomePage homePage;

    private ContributionPage contributionPage;


    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public ContributionPage getContributionPage() {
        if (contributionPage == null) {
            contributionPage = new ContributionPage();
        }
        return contributionPage;
    }


}
