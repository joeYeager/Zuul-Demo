package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import org.yeager.zuul.contants.HeaderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


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
        final long responseReceived = System.currentTimeMillis();

        RequestContext currentContext = RequestContext.getCurrentContext();
        String requestId = (String) currentContext.get(HeaderConstants.REQUEST_ID_HEADER);

        Long timestamp = (Long) currentContext.get(HeaderConstants.TIMESTAMP_HEADER);
        long responseTime = responseReceived - timestamp;

        currentContext.addZuulResponseHeader(HeaderConstants.RESPONSE_TIME, String.valueOf(responseTime));

        log.info(
                "Proxying of request with id: {} took {}ms.",
                requestId,
                String.valueOf(responseTime)
        );

        return null;
    }
}
