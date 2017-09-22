package com.cdk.cs.iam.service;

import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RequestSwatterService {

    public void swat(RequestContext requestContext, final HttpStatus status, final String message) {

    }
}
