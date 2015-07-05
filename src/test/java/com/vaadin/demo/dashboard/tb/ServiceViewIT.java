package com.vaadin.demo.dashboard.tb;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.demo.dashboard.tb.pageobjects.TBLoginView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBMainView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBReportsView;
import com.vaadin.demo.dashboard.tb.pageobjects.TBServiceView;
import com.vaadin.testbench.TestBenchTestCase;

public class ServiceViewIT extends TestBenchTestCase {

    private TBLoginView loginView;
    private TBMainView mainView;

    @Before
    public void setUp() {
        loginView = TBUtils.openInitialView();
        mainView = loginView.login();
    }

    @Test
    public void testFilter() {
        TBServiceView serviceView = mainView.openServiceView();
        serviceView.setFilter("Madrid");
        Assert.assertFalse(serviceView.listingContainsCity("London"));
        serviceView.setFilter("");
    }

    @Test
    public void testCreateReport() {
        TBServiceView serviceView = mainView.openServiceView();
        List<String> titles = serviceView.selectFirstService(5);
        TBReportsView reportsView = serviceView
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
