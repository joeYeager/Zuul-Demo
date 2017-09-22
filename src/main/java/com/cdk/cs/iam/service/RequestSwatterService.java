package com.cdk.cs.iam.service;

import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class RequestSwatterService {

    public void swat(final RequestContext requestContext, final HttpStatus status, final String message) {
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(status.value());
        final String responseBody = String.format("{\"message\":\"%s\"}", message);
        requestContext.setResponseBody(responseBody);
        requestContext.getResponse().setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }
}
