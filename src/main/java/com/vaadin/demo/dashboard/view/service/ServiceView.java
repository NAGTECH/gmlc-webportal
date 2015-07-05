package com.vaadin.demo.dashboard.view.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.google.common.eventbus.Subscribe;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.demo.dashboard.domain.Service;
import com.vaadin.demo.dashboard.event.DashboardEvent.BrowserResizeEvent;
import com.vaadin.demo.dashboard.event.DashboardEventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings({ "serial" })
public final class ServiceView extends VerticalLayout implements View {
	private final Table table;
    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    private static final String[] DEFAULT_COLLAPSIBLE = { "id", "sequenceId" };
    private BeanItemContainer<Service> beansContainer = null;
    
    public ServiceView() {
        setSizeFull();
        addStyleName("service");
        DashboardEventBus.register(this);

        addComponent(buildToolbar());

        table = buildTable();
        addComponent(table);
        setExpandRatio(table, 1);
    }

    @Override
    public void detach() {
        super.detach();
        
        DashboardEventBus.unregister(this);
    }

    private Component buildToolbar() {
        VerticalLayout header = new VerticalLayout();
        header.addStyleName("viewheader");
        header.setSpacing(true);
        Responsive.makeResponsive(header);

        Label title = new Label("Service Report");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        
        header.addComponent(buildFilter());

        return header;
    }

    private Component buildFilter() {
    	HorizontalLayout toolbar = new HorizontalLayout();
    	toolbar.addStyleName("toolbar");
        toolbar.setSpacing(true);
    	
    	final DateField txtStartDate = new DateField("Start Date");
    	txtStartDate.setDateFormat("yyyy-MM-dd");
        toolbar.addComponent(txtStartDate);
        
        final DateField  txtEndDate = new DateField("End Date");
        txtEndDate.setDateFormat("yyyy-MM-dd");
        toolbar.addComponent(txtEndDate);
        
        final TextField txtService = new TextField("Service Name");
        toolbar.addComponent(txtService);
        
        final Button btnFilter = new Button("Filter");
        btnFilter.addStyleName("filterbutton");
        
        toolbar.addComponent(btnFilter);
        toolbar.setComponentAlignment(btnFilter, Alignment.BOTTOM_LEFT);
        
        
        btnFilter.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				btnFilter.setComponentError(null);
				
				if(txtStartDate.getValue() != null && txtEndDate.getValue() != null) {
					if(txtStartDate.getValue().getTime() > txtEndDate.getValue().getTime()) {
						btnFilter.setComponentError(new UserError("End date can't be earlier than start date"));
						return;
					}
				} else if(txtService.getValue().trim().length() <= 0) {
					btnFilter.setComponentError(new UserError("Please provide Service Name  or Start and End Dates"));
					return;
				}
				
				beansContainer.removeAllItems();
				Collection<Service> service = DashboardUI.getDataProvider()
						.getService(txtService.getValue().trim(), txtStartDate.getValue(), txtEndDate.getValue());
				if(service != null) {
					beansContainer.addAll(service);
				}
			}
		});
        
        return toolbar;
    }

    private Table buildTable() {
        final Table table = new Table() {
            @Override
            protected String formatPropertyValue(final Object rowId,
                    final Object colId, final Property<?> property) {
                String result = super.formatPropertyValue(rowId, colId,
                        property);
                if (colId.equals("dateTime")) {
                    result = DATEFORMAT.format(((Date) property.getValue()));
                }
                
                return result;
            }
        };
        
        table.setSizeFull();
        table.addStyleName(ValoTheme.TABLE_BORDERLESS);
        table.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
        table.addStyleName(ValoTheme.TABLE_COMPACT);
        table.setSelectable(true);

        table.setColumnCollapsingAllowed(true);
        table.setColumnReorderingAllowed(false);
        
        beansContainer = new BeanItemContainer<Service>(Service.class);
        table.setContainerDataSource(beansContainer);
        
        table.setSortContainerPropertyId("id");
        table.setSortAscending(false);

        table.setVisibleColumns("id", "dateTime", "msisdn", "cellId");
        table.setColumnHeaders("S.no", "Date/time", "MSISDN", "ATI Response (Cell-ID)");

        table.setFooterVisible(false);
        table.setImmediate(true);

        return table;
    }

    private boolean defaultColumnsVisible() {
        boolean result = true;
        for (String propertyId : DEFAULT_COLLAPSIBLE) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    @Subscribe
    public void browserResized(final BrowserResizeEvent event) {
        // Some columns are collapsed when browser window width gets small
        // enough to make the table fit better.
        if (defaultColumnsVisible()) {
            for (String propertyId : DEFAULT_COLLAPSIBLE) {
                table.setColumnCollapsed(propertyId, Page.getCurrent()
                        .getBrowserWindowWidth() < 800);
            }
        }
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    }
}
