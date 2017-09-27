package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

public class PostRequestFilterTest {

    private PostRequestFilter target;

    private RequestContext requestContext;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        requestContext = mock(RequestContext.class);
        response = mock(HttpServletResponse.class);
        when(requestContext.getResponse()).thenReturn(response);
        target = new PostRequestFilter();
    }

    @Test
    public void whenFilterTypeIsCalledItShouldReturnPostType() throws Exception {
        assertEquals(POST_TYPE, target.filterType());
    }

    @Test
    public void testFilterOrder() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_RESPONSE_IS_FORWARDED, target.filterOrder());
    }

    @Test
    public void whenShouldFilterIsCalledItShouldAlwaysReturnTrue() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_ALL_TRAFFIC, target.shouldFilter());
    }

    @Test
    public void whenRunIsCalledItShouldAddTheTotalResponseTimeHeader() throws Exception {
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(response).setHeader(eq(HeaderConstants.RESPONSE_TIME), anyString());
    }
}
