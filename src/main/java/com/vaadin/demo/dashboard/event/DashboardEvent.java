package com.vaadin.demo.dashboard.event;

import java.util.Collection;

import com.vaadin.demo.dashboard.domain.Transaction;
import com.vaadin.demo.dashboard.domain.Statistics;
import com.vaadin.demo.dashboard.domain.Detailed;
import com.vaadin.demo.dashboard.domain.Service;
import com.vaadin.demo.dashboard.domain.Location;
import com.vaadin.demo.dashboard.view.DashboardViewType;

/*
 * Event bus events used in Dashboard are listed here as inner classes.
 */
public abstract class DashboardEvent {

    public static final class UserLoginRequestedEvent {
        private final String userName, password;

        public UserLoginRequestedEvent(final String userName,
                final String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class BrowserResizeEvent {

    }

    public static class UserLoggedOutEvent {

    }

    public static class NotificationsCountUpdatedEvent {
    }

    public static final class ReportsCountUpdatedEvent {
        private final int count;

        public ReportsCountUpdatedEvent(final int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

    }

    public static final class TransactionReportEvent {
        private final Collection<Transaction> transactions;

        public TransactionReportEvent(final Collection<Transaction> transactions) {
            this.transactions = transactions;
        }

        public Collection<Transaction> getTransactions() {
            return transactions;
        }
    }
    
    public static final class StatisticsReportEvent {
        private final Collection<Statistics> statistics;

        public StatisticsReportEvent(final Collection<Statistics> statistics) {
            this.statistics = statistics;
        }

        public Collection<Statistics> getStatistics() {
            return statistics;
        }
    }

    public static final class DetailedReportEvent {
        private final Collection<Detailed> detailed;

        public DetailedReportEvent(final Collection<Detailed> detailed) {
            this.detailed = detailed;
        }

        public Collection<Detailed> getDetailed() {
            return detailed;
        }
    }
    
    public static final class ServiceReportEvent {
        private final Collection<Service> service;

        public ServiceReportEvent(final Collection<Service> service) {
            this.service = service;
        }

        public Collection<Service> getStatistics() {
            return service;
        }
    }
    
    public static final class LocationReportEvent {
        private final Collection<Location> location;

        public LocationReportEvent(final Collection<Location> location) {
            this.location = location;
        }

        public Collection<Location> getLocation() {
            return location;
        }
    }
    public static final class PostViewChangeEvent {
        private final DashboardViewType view;

        public PostViewChangeEvent(final DashboardViewType view) {
            this.view = view;
        }

        public DashboardViewType getView() {
            return view;
        }
    }

    public static class CloseOpenWindowsEvent {
    }

    public static class ProfileUpdatedEvent {
    }

}
