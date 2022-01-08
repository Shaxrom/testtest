package uz.episodeone.merchants.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    SMS_NOTIFICATION_OFF("1"),
    SERVICE_NOT_AVAILABLE("2"),
    MYPAY_EXCEPTION("3"),
    USER_NOT_FOUND("4"),
    USER_ALREADY_EXIST("5"),
    NOT_FOUND("6"),
    SMS_NOT_VALID("7"),
    DUPLICATE_DATA_FOUND("8"),
    SERVICE_ERROR("9"),
    PIN_CODE_MISMATCH("10"),
    PARAMTER_IS_MISSING("11"),
    UNSUPPORTED_MEDIA_TYPE("12"),
    JSON_MAILFORMED("13"),
    ARGUMENT_TYPE_MISMATCH("14"),
    INVALID_TOKEN("15"),
    BAD_REQUEST("16"),
    ALREADY_EXIST("17"),
    STORAGE_EXCEPTION("18"),
    HUMO_CARD_EXCEPTION("19"),
    HUMO_EXCEPTION("20"),
    RABBITMQ_EXCEPTION("21"),
    SMS_NOTIFICATION_ERROR("22"),
    VALIDATION_ERROR("43"),
    CARD_ACCOUNT_MISMATCH("23"),
    HUMO_NOT_SUPPORTED("24"),
    NOT_ENOUGH_FUNDS("25"),
    BANK_NOT_SUPPORTED("26"),
    CARD_NOT_SUPPORTED("27"),
    CARD_ALREADY_ADDED("28"),
    GENERAL_MESSAGE_CODE("29"),
    NOT_AUTHORIZED("30"),
    ONLY_HUMO_AND_UZCARD_CARD_SUPPORTED("31"),
    UZCARD_EXCEPTION("32"),
    EXCEPTION_WHEN_READ_FILE("33"),
    TRANSACTION_NOT_FOUND("34"),
    GROUP_NOT_FOUND("35"),
    CATEGORY_NOT_FOUND("36"),
    GRADIENT_NOT_FOUND("37"),
    LANGUAGE_NOT_FOUND("38"),
    RESTRICTION_NOT_FOUND("39"),
    INVALID_CODE("40"),
    TRANSACTION_FOR_THIS_USER_NOT_FOUND("41"),
    CARD_INFO_REQUEST_LIMIT("42"),
    PERSONAL_ACCOUNT_NOT_FOUND("44"),
    CARD_FOR_THIS_CUSTOMER_NOT_FOUND("45"),
    INVALID_PASSWORD("46"),
    ATTEMPTS_EXCEEDED("47"),
    USER_PASSWORD_MISMATCH("49"),
    NEW_PASSWORD_CANT_BE_OLD("51"),
    NEW_PIN_CANT_BE_OLD("50"),
    INVALID_TRANS_CANT_BE_ADDED_TO_GR("52"),
    ALREADY_EXIST_IN_GROUP("101"),
    SMS_CODE_EXPIRED("102"),
    MINIMUM_LENGTH_OF_PASSWORD("103"),
    CARD_NOT_LINKED_WITH_PHONE("104");


    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ErrorCode fromValue(String key) {
        if (key != null) {
            for (ErrorCode type : values()) {
                if (type.value.equalsIgnoreCase(key))
                    return type;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    @JsonValue
    public String toValue() {
        return value;
    }

}