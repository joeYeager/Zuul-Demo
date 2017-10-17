package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class PreRequestFilterTest {

    private PreRequestFilter target;

    @Before
    public void setUp() throws Exception {
        target = new PreRequestFilter();
    }

    @Test
    public void whenFilterTypeIsCalledItShouldReturnPreType() throws Exception {
        assertEquals(PRE_TYPE, target.filterType());
    }

    @Test
    public void testFilterOrder() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_REQUEST_IS_FORWARDED, target.filterOrder());
    }

    @Test
    public void whenShouldFilterIsCalledItShouldAlwaysReturnTrue() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_ALL_TRAFFIC, target.shouldFilter());
    }

    @Test
    public void whenRunIsCalledItShouldAddAnApiTokenHeader() throws Exception {
        RequestContext requestContext = mock(RequestContext.class);
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).addZuulRequestHeader(HeaderConstants.API_TOKEN_HEADER, "token-value");
    }

    @Test
    public void whenRunIsCalledItShouldAddRequestIdHeader() throws Exception {
        RequestContext requestContext = mock(RequestContext.class);
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).addZuulRequestHeader(eq(HeaderConstants.REQUEST_ID_HEADER), anyString());
    }

    @Test
    public void whenRunIsCalledItShouldAddTimestampHeader() throws Exception {
        RequestContext requestContext = mock(RequestContext.class);
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).set(eq(HeaderConstants.TIMESTAMP_HEADER), anyString());
    }
}