package com.otoniel.g6pd.g6pddeficiencyapp;


import static com.otoniel.g6pd.g6pddeficiencyapp.utils.ActivityUtil.getGlobalContext;

/**
 * Created by eltonjhony on 20/05/17.
 */

public class ApplicationMessages {

    private ApplicationMessages() {
    }

    public static String getFieldRequiredMessage(String field) {
        return String.format(getGlobalContext().getString(R.string.field_required_message), field);
    }

    public static String getUnexpectedErrorMessage() {
        return getGlobalContext().getString(R.string.unexpected_error_message);
    }
}
