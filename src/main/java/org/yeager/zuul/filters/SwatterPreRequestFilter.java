package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import org.yeager.zuul.service.RequestSwatterService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


@Service
@Slf4j
@ConditionalOnProperty(prefix = "pre-filter", name = "enabled")
public class SwatterPreRequestFilter extends ZuulFilter {

    private RequestSwatterService requestSwatterService;

    @Autowired
    public SwatterPreRequestFilter(final RequestSwatterService requestSwatterService) {
        this.requestSwatterService = requestSwatterService;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SHOULD_BE_THE_FIRST_PRE_FILTER_TO_RUN;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        String requestURI = currentContext.getRequest().getRequestURI();
        return requestURI.toLowerCase().contains("swatted");
    }

    @Override
    public Object run() {
        requestSwatterService.swat(RequestContext.getCurrentContext(), HttpStatus.I_AM_A_TEAPOT, "Get Swatted");
        return null;
    }
}
