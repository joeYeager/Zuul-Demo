package com.cdk.cs.iam.contants;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

public class ErrorConstants {
    public static final String ZUUL_ERROR_ENDPOINT = "/error";
    public static final String NOT_FOUND_MESSAGE = "Not Found";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";

}
