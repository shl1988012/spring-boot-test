package com.spring.test.Exception;

import org.apache.commons.lang3.StringUtils;

public enum EnrollmentError {
    /**
     * Error: unknown error
     */
    ERROR0000("20000", "Unknown error occurred."),
    /**
     * Error: userUUID or userEmail should be provided
     */
    ERROR0004("20004", "userUUID or userEmail should be provided."),

    ERROR0010("20010", "Internal Server Error"),

    CLIENT_ERROR("20011", "Request is invalid."),
    /**
     * Error: Param is invaild
     */
    ERROR0015("20015", "Param is invaild"),
    /**
     * Error: Param covert to request dto failed
     */
    ERROR0016("20016", "Param covert to request dto failed");

    //handle navigation error
//    ERROR0082("20082", "Record with specified UUID was not found."),
//    ERROR0083("20083", "Invalid record."),

    private String errorCode;
    private String errorVerbose;

    EnrollmentError(String errorCode, String errorVerbose) {
        this.errorCode = errorCode;
        this.errorVerbose = errorVerbose;
    }

    public static EnrollmentError getEnrollmentErrorByCode(String errorCode) {
        for (EnrollmentError enrollmentError : EnrollmentError.values()) {
            if (enrollmentError.getErrorCode().equalsIgnoreCase(errorCode)) {
                return enrollmentError;
            }
        }
        return null;
    }

    public static EnrollmentError getEnrollmentErrorByMsg(String msg) {
        for (EnrollmentError enrollmentError : EnrollmentError.values()) {
            if (StringUtils.equals(msg, enrollmentError.getErrorVerbose())) {
                return enrollmentError;
            }
        }
        return null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorVerbose() {
        return errorVerbose;
    }

    public void setErrorVerbose(String errorVerbose) {
        this.errorVerbose = errorVerbose;
    }
}
