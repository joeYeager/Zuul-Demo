package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Service
@ConditionalOnProperty(prefix = "error--throw-filter", name = "enabled")
public class ThrowErrorFilter extends ZuulFilter {
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
        RequestContext currentContext = RequestContext.getCurrentContext();
        String requestURI = currentContext.getRequest().getRequestURI();
        return requestURI.toLowerCase().contains("exception");
    }

    @Override
    public Object run() {
        throw new RuntimeException();
    }
}
