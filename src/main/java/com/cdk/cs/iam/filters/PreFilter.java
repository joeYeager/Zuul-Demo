package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.service.RequestSwatterService;
import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Service
@Slf4j
public class PreFilter extends ZuulFilter {

    private RequestSwatterService requestSwatterService;

    @Autowired
    public PreFilter(final RequestSwatterService requestSwatterService) {
        this.requestSwatterService = requestSwatterService;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
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
