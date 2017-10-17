package com.cdk.cs.iam.service;

import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RequestSwatterServiceTest {


    private RequestSwatterService target;
    private RequestContext requestContext;
    private HttpServletResponse response;
    private final String message = "I AM A TEAPOT";

    @Before
    public void setUp() throws Exception {
        requestContext = mock(RequestContext.class);
        response = mock(HttpServletResponse.class);
        when(requestContext.getResponse()).thenReturn(response);
        target = new RequestSwatterService();
    }

    @Test
    public void whenSwatIsCalledItShouldSetSendZuulResponseToFalse() throws Exception {
        target.swat(requestContext, HttpStatus.I_AM_A_TEAPOT, message);
        verify(requestContext).setSendZuulResponse(false);
    }

    @Test
    public void whenSwatIsCalledItShouldSetTheResponseStatusCode() throws Exception {
        target.swat(requestContext, HttpStatus.I_AM_A_TEAPOT, message);
        verify(requestContext).setResponseStatusCode(HttpStatus.I_AM_A_TEAPOT.value());
    }

    @Test
    public void whenSwatIsCalledItShouldSetTheResponseBody() throws Exception {
        target.swat(requestContext, HttpStatus.I_AM_A_TEAPOT, message);
        verify(requestContext).setResponseBody(anyString());
    }

    @Test
    public void whenSwatIsCalledItShouldSetTheContentTypeAsJson() throws Exception {
        target.swat(requestContext, HttpStatus.I_AM_A_TEAPOT, message);
        verify(response).setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }
}