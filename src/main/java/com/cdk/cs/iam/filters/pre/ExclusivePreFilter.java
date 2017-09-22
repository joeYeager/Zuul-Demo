package com.cdk.cs.iam.filters.pre;

import com.cdk.cs.iam.service.RequestSwatterService;
import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Service
public class ExclusivePreFilter extends ZuulFilter {

    private RequestSwatterService requestSwatterService;

    @Autowired
    public ExclusivePreFilter(RequestSwatterService requestSwatterService) {
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
