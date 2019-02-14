package com.eilikce.toolkit.model;

import java.util.Arrays;

public enum ActionOptions {
    UNKNOW, PAI, CAI;

    public static ActionOptions check(String option) {
        return Arrays.stream(ActionOptions.values())
                .filter(options -> options.toString().equalsIgnoreCase(option))
                .findAny().get();
    }
}
