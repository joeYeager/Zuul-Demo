package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Random;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "route-filter", name = "enabled")
public class StranglerPreRequestFilter extends ZuulFilter {

    private Random random = new Random();

    @Value("${routing-percentage}")
    private float routingPercentage;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SHOULD_FILTER_BEFORE_REQUEST_IS_FORWARDED;
    }

    @Override
    public boolean shouldFilter() {
        return random.nextFloat() < routingPercentage;
    }

    @Override
    public Object run() {
        RequestContext.getCurrentContext().set(HeaderConstants.ROUTING_HEADER, true);
        return null;
    }
}
