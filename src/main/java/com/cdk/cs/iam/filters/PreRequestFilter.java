package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.service.RequestSwatterService;
import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@ConditionalOnProperty(prefix = "pre-filter", name = "enabled")
public class PreRequestFilter extends ZuulFilter {

    private RequestSwatterService requestSwatterService;

    @Autowired
    public PreRequestFilter(final RequestSwatterService requestSwatterService) {
        this.requestSwatterService = requestSwatterService;
    }

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
