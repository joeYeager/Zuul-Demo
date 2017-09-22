package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Service
public class ErrorFilter extends SendErrorFilter {

    @Override
    public int filterOrder() {
        return FilterConstants.SHOULD_FILTER_BEFORE_DEFAULT_ERROR_FILTER;
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final Throwable exception = ctx.getThrowable();
        final HttpServletRequest request = ctx.getRequest();
        log.error(
                "Request to {} resulted in an exception with message: {}, Stacktrace: {}\n",
                request.getRequestURI(),
                exception.getMessage(),
                exception.getStackTrace()
        );
        return null;
    }
}
