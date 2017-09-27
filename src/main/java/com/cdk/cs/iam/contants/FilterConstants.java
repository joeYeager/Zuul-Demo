package com.cdk.cs.iam.contants;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_FORWARD_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

public class FilterConstants {
    public static final int SHOULD_FILTER_BEFORE_REQUEST_IS_FORWARDED = SEND_FORWARD_FILTER_ORDER - 1;
    public static final int SHOULD_FILTER_BEFORE_RESPONSE_IS_FORWARDED = SEND_RESPONSE_FILTER_ORDER - 1;
    public static final int SHOULD_FILTER_BEFORE_HOST_ROUTING_OCCURS = SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    public static final int SHOULD_FILTER_BEFORE_DEFAULT_ERROR_FILTER = SEND_ERROR_FILTER_ORDER - 1;
    public static final int SHOULD_BE_THE_FIRST_PRE_FILTER_TO_RUN = SEND_ERROR_FILTER_ORDER - 2;
    public static final boolean SHOULD_FILTER_ALL_TRAFFIC = true;
}
