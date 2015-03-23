/*
 * Copyright © 2015 Nadav Cohen <nadavcoh@gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package nadavc;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import nadavc.github.GitHubHome;
import nadavc.github.OrgProfilePage;
import nadavc.github.RepoSummary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.text.MessageFormat.format;

public class GitHubScraper {

    private Multiset<String> languageCounter = HashMultiset.create();

    public static void main(String[] args) {
        GitHubScraper scraper = new GitHubScraper();
        scraper.scrape();
    }

    public void scrape() {
        WebDriver driver = new FirefoxDriver();
        try {
            OrgProfilePage msProfile = new GitHubHome(driver).goToOrgProfile("Microsoft");
            scrapeSinglePage(msProfile);
            while (msProfile.hasNextPage()) {
                scrapeSinglePage(msProfile.goToNextPage());
            }

            outputResults();
        } finally {
            driver.close();
        }
    }

    private void outputResults() {
        System.out.println("SUMMARY");
        for (String lang : languageCounter.elementSet()) {
            System.out.println(format("{0}: {1}", lang, languageCounter.count(lang)));
        }
    }


    private void scrapeSinglePage(OrgProfilePage page) {
        System.out.println(format("{0} Profile - Page {1}", page.getOrgName(), page.getPageNumber()));
        System.out.println(page.getOrgDescription());
        System.out.println("");

        for (RepoSummary repo : page.getRepos()) {
            System.out.println(format("Project {0}. Language: {1}", repo.getRepoName(), repo.getRepoLanguage()));
            languageCounter.add(repo.getRepoLanguage());
        }

    }

}
