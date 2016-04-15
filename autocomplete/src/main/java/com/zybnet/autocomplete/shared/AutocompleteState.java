package com.zybnet.autocomplete.shared;

import com.vaadin.shared.AbstractFieldState;
import com.vaadin.shared.annotations.DelegateToWidget;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class AutocompleteState extends AbstractFieldState {

    public List<AutocompleteFieldSuggestion> suggestions = Collections.emptyList();
    public int delayMillis = 300;
    @DelegateToWidget
    public int minimumQueryCharacters = 3;
    @DelegateToWidget
    public boolean trimQuery = true;
}
