package com.cdk.cs.iam.filters;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_FORWARD_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

class FilterConstants {
    static int SHOULD_FILTER_BEFORE_REQUEST_IS_FORWARDED = SEND_FORWARD_FILTER_ORDER - 1;
    static int SHOULD_FILTER_BEFORE_RESPONSE_IS_FORWARDED = SEND_RESPONSE_FILTER_ORDER - 1;
    static int SHOULD_FILTER_BEFORE_HOST_ROUTING_OCCURS = SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    static boolean SHOULD_FILTER_ALL_TRAFFIC = true;
}
