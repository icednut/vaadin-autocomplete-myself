package app;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.zybnet.autocomplete.server.AutocompleteField;
import com.zybnet.autocomplete.server.AutocompleteQueryListener;
import com.zybnet.autocomplete.server.AutocompleteSuggestionPickedListener;

import javax.servlet.annotation.WebServlet;

@Theme("valo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.zybnet.autocomplete.AutocompleteWidgetSet")
    public static class DevServlet extends VaadinServlet {
    }

    private final AutocompleteField<Integer> search1 = new AutocompleteField<Integer>();

    @Override
    protected void init(VaadinRequest request) {
        setupAutocomplete(search1);
        search1.setWidth(300, Unit.PIXELS);

        setContent(new VerticalLayout() {{
            setMargin(true);
            setSizeFull();
            addComponents(search1);
        }});
    }

    private void setupAutocomplete(AutocompleteField<Integer> search) {
        search.setQueryListener(new AutocompleteQueryListener<Integer>() {
            @Override
            public void handleUserQuery(AutocompleteField<Integer> field, String query) {
                handleSearchQuery(field, query);
            }
        });

        search.setSuggestionPickedListener(new AutocompleteSuggestionPickedListener<Integer>() {
            @Override
            public void onSuggestionPicked(Integer page) {
                handleSuggestionSelection(page);
            }
        });
    }

    protected void handleSuggestionSelection(Integer suggestion) {
        Notification.show("Selected " + suggestion);
    }

    private void handleSearchQuery(AutocompleteField<Integer> field, String query) {
        for (int i = 0; i < 10; i++) {
            field.addSuggestion(i, i + ": " + query);
        }
    }

}
