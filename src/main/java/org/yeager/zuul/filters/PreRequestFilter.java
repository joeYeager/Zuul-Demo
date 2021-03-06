package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import org.yeager.zuul.contants.HeaderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "pre-filter", name = "enabled")
public class PreRequestFilter extends ZuulFilter {

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
        return FilterConstants.SHOULD_FILTER_ALL_TRAFFIC;
    }

    @Override
    public Object run() {
        Long epoch = System.currentTimeMillis();
        String requestId = UUID.randomUUID().toString();

        RequestContext currentContext = RequestContext.getCurrentContext();

        currentContext.set(HeaderConstants.TIMESTAMP_HEADER, epoch);
        currentContext.set(HeaderConstants.REQUEST_ID_HEADER, requestId);

        currentContext.addZuulRequestHeader(HeaderConstants.API_TOKEN_HEADER, "token-value");
        currentContext.addZuulRequestHeader(HeaderConstants.REQUEST_ID_HEADER, requestId);

        log.info(
                "Proxying request with request id: {} to URI: {}",
                requestId,
                currentContext.getRequest().getRequestURI()
        );

        return null;
    }
}
