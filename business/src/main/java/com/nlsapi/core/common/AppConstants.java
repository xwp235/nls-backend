package com.nlsapi.core.common;

public interface AppConstants {

    String LOG_ID = "LOG_ID";
    String TRACE_ID = "TRACE_ID";
    String ERROR_TYPE = "ERROR_TYPE";
    int REQUEST_SUCCESS=1;
    int REQUEST_ERROR=0;

    interface EXCEPTION_TYPE {
        String INFO ="INFO";
        String WARN = "WARN";
        String ERROR = "ERROR";
    }

}
