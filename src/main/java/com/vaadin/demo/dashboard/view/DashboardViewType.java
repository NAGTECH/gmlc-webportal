package com.vaadin.demo.dashboard.view;

import com.vaadin.demo.dashboard.view.dashboard.DashboardView;
import com.vaadin.demo.dashboard.view.statistics.StatisticsView;
import com.vaadin.demo.dashboard.view.transactions.TransactionsView;
import com.vaadin.demo.dashboard.view.detailed.DetailedView;
import com.vaadin.demo.dashboard.view.service.ServiceView;
import com.vaadin.demo.dashboard.view.location.LocationView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum DashboardViewType {
    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true),
            STATISTICS_REPORT("Platform Statistics", StatisticsView.class, FontAwesome.BAR_CHART_O, false),
            REQUEST_REPORT("Detailed Requests", DetailedView.class, FontAwesome.EXCHANGE, false),
            SERVICE_REPORT("Service Report", ServiceView.class, FontAwesome.CUBES, false),
            LOCATION_REQUEST_REPORT("Location Requests", LocationView.class, FontAwesome.GLOBE, false),
            TRANSACTION_REPORT("Transactions", TransactionsView.class, FontAwesome.TABLE, false);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private DashboardViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
