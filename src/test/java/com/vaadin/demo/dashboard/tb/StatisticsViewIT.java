package com.vaadin.demo.dashboard.tb;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.demo.dashboard.tb.pageobjects.TBLoginView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBMainView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBReportsView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBStatisticsView;
import com.vaadin.testbench.TestBenchTestCase;

public class StatisticsViewIT extends TestBenchTestCase {

    private TBLoginView loginView;
    private TBMainView mainView;

    @Before
    public void setUp() {
        loginView = TBUtils.openInitialView();
        mainView = loginView.login();
    }

    @Test
    public void testFilter() {
    	TBStatisticsView transactionsView = mainView.openStatisticsView();
        transactionsView.setFilter("Madrid");
        Assert.assertFalse(transactionsView.listingContainsCity("London"));
        transactionsView.setFilter("");
    }

    @Test
    public void testCreateReport() {
        TBStatisticsView transactionsView = mainView.openStatisticsView();
        List<String> titles = transactionsView.selectFirstTransactions(5);
        TBReportsView reportsView = transactionsView
                .createReportFromSelection();
        Assert.assertTrue(reportsView.isDisplayed());

        for (String title : titles) {
            Assert.assertTrue(reportsView.hasReportForTitle(title));
        }
    }

    @After
    public void tearDown() {
        loginView.getDriver().quit();
    }
}
