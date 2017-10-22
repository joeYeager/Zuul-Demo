package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

@Service
@Slf4j
@ConditionalOnProperty(prefix = "route-filter", name = "enabled")
public class StranglerRouteFilter extends ZuulFilter {

    private Random random = new Random();

    @Value("${routing-percentage}")
    private float routingPercentage;

    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SHOULD_FILTER_BEFORE_HOST_ROUTING_OCCURS;
    }

    @Override
    public boolean shouldFilter() {
        return random.nextFloat() < routingPercentage;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        String requestId = (String) currentContext.get(HeaderConstants.REQUEST_ID_HEADER);
        try {
            URL url = new URL("http://localhost:8000/new-service");
            currentContext.setRouteHost(url);
        } catch (MalformedURLException e) {}

        log.info("Redirecting request with id: {} to new-service", requestId);
        return null;
    }
}
