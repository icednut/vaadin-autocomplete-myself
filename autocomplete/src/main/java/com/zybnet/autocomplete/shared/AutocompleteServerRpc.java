package com.zybnet.autocomplete.shared;

import com.vaadin.shared.annotations.Delayed;
import com.vaadin.shared.communication.ServerRpc;

public interface AutocompleteServerRpc extends ServerRpc {

    void onQuery(String query);

    void onSuggestionPicked(AutocompleteFieldSuggestion suggestion);

    @Delayed
    void onTextValueChanged(String text);
}
