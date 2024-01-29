package com.samax.simpleCommerce.common.util;

public class InputValidation {

    public static final String RX_NAME = "^[A-Za-z0-9]+(?:\\.[A-Za-z0-9])?[A-Za-z0-9]*$";
    public static final String RX_NAME_MATCH_ERROR = "name can only be alpha-numeric with zero-one dot contained";
    public static final String RX_PASS = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$";
    public static final String RX_PASS_MATCH_ERROR = "password must be at least 8 characters long containing - capital letter, number, one of @$!%*?&._- symbols";

}
