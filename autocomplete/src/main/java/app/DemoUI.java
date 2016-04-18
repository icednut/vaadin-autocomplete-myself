package app;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.zybnet.autocomplete.server.AutocompleteField;
import com.zybnet.autocomplete.server.AutocompleteQueryListener;
import com.zybnet.autocomplete.server.AutocompleteSuggestionPickedListener;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;

@Theme("valo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.zybnet.autocomplete.AutocompleteWidgetSet")
    public static class DevServlet extends VaadinServlet {
    }

    private AutocompleteField<Member> autoCompleteField;
    private ComboBox comboBox;

    @Override
    protected void init(VaadinRequest request) {
        final Member sam = new Member(20, "Sam");
        final Member tom = new Member(40, "Tom");
        final Member mike = new Member(34, "Mike");

        autoCompleteField = createAutoCompleteField(sam, tom, mike);
        comboBox = createComboBox(sam, tom, mike);

        VerticalLayout autoCompleteFieldLayout = new VerticalLayout() {{
            setMargin(true);
            setSpacing(true);
        }};
        autoCompleteFieldLayout.addComponents(new Label("AutoCompleteField"), autoCompleteField);
        VerticalLayout comboBoxLayout = new VerticalLayout() {{
            setMargin(true);
            setSpacing(true);
        }};
        comboBoxLayout.addComponents(new Label("com.vaadin.ui.ComboBox"), comboBox);

        VerticalLayout content = new VerticalLayout() {{
            setSpacing(true);
            setWidth(100, Unit.PERCENTAGE);
        }};
        content.addComponents(autoCompleteFieldLayout, comboBoxLayout);
        setContent(content);
    }

    private ComboBox createComboBox(Member sam, Member tom, Member mike) {
        ComboBox comboBox = new ComboBox();
        BeanItemContainer<Member> newDataSource = new BeanItemContainer(Member.class, Collections.<Member>emptyList());

        newDataSource.addBean(sam);
        newDataSource.addBean(tom);
        newDataSource.addBean(mike);

        comboBox.setItemCaption(sam, sam.getName());
        comboBox.setItemCaption(tom, tom.getName());
        comboBox.setItemCaption(mike, mike.getName());
        comboBox.setContainerDataSource(newDataSource);
        comboBox.setWidth(300, Unit.PIXELS);
        return comboBox;
    }

    private AutocompleteField<Member> createAutoCompleteField(final Member sam, final Member tom, final Member mike) {
        AutocompleteField<Member> autoCompleteField = new AutocompleteField<Member>();

        autoCompleteField.setItemCaptionTemplate("<div style=\"min-width:300px\"><h3>%s</h3><p>나이: %s, 이름: %s</p></div>");
        autoCompleteField.setQueryListener(new AutocompleteQueryListener<Member>() {
            @Override
            public void handleUserQuery(AutocompleteField<Member> field, String query) {
                field.addSuggestion(sam, query, "20", "Sam");
                field.addSuggestion(tom, query, "40", "Tom");
                field.addSuggestion(mike, query, "34", "Mike");
            }
        });

        autoCompleteField.setSuggestionPickedListener(new AutocompleteSuggestionPickedListener<Member>() {
            @Override
            public void onSuggestionPicked(Member selectedMember) {
                Notification.show(String.format("Selected Member's age: %d, name: %s", selectedMember.getAge(), selectedMember.getName()));
            }
        });
        autoCompleteField.setWidth(300, Unit.PIXELS);
        return autoCompleteField;
    }

    public static class Member {
        private int age;
        private String name;

        public Member(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
}
