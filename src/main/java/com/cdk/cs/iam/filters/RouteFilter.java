package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "route-filter", name = "enabled")
public class RouteFilter extends ZuulFilter {


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
        return RequestContext.getCurrentContext().get(HeaderConstants.ROUTING_HEADER) != null;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        try {
            URL url = new URL("http://localhost:8000/new-service");
            currentContext.setRouteHost(url);
        } catch (MalformedURLException e) {}
        return null;
    }
}
