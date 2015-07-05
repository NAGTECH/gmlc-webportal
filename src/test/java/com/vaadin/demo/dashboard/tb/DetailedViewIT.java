package com.vaadin.demo.dashboard.tb;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.demo.dashboard.tb.pageobjects.TBLoginView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBMainView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBReportsView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBDetailedView;
import com.vaadin.testbench.TestBenchTestCase;

public class DetailedViewIT extends TestBenchTestCase {

    private TBLoginView loginView;
    private TBMainView mainView;

    @Before
    public void setUp() {
        loginView = TBUtils.openInitialView();
        mainView = loginView.login();
    }

    @Test
    public void testFilter() {
        TBDetailedView detailedView = mainView.openDetailedView();
        detailedView.setFilter("Madrid");
        Assert.assertFalse(detailedView.listingContainsCity("London"));
        detailedView.setFilter("");
    }

    @Test
    public void testCreateReport() {
        TBDetailedView detailedView = mainView.openDetailedView();
        List<String> titles = detailedView.selectFirstDetailed(5);
        TBReportsView reportsView = detailedView
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
