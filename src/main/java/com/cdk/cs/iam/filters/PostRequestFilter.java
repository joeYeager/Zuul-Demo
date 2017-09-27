package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "post-filter", name = "enabled")
public class PostRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SHOULD_FILTER_BEFORE_RESPONSE_IS_FORWARDED;
    }

    @Override
    public boolean shouldFilter() {
        return FilterConstants.SHOULD_FILTER_ALL_TRAFFIC;
    }

    @Override
    public Object run() {
        final long responseRecieved = System.currentTimeMillis();
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();
        String requestInitiated = response.getHeader(HeaderConstants.TIMESTAMP_HEADER);

        if (requestInitiated == null) {
            return null;
        }

        long responseTime = responseRecieved - Long.valueOf(requestInitiated);
        response.setHeader(HeaderConstants.RESPONSE_TIME, String.valueOf(responseTime));
        return null;
    }
}
