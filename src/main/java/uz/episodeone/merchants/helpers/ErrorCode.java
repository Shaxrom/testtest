package uz.episodeone.merchants.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    NOT_FOUND("1"),
    ALREADY_EXIST("2"),
    BAD_REQUEST("3"),
    NOT_AUTHORIZED("4"),
    SERVICE_NOT_AVAILABLE("5"),
    SERVICE_ERROR("6");


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