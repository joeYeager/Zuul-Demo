package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class ThrowErrorFilterTest {

    private ThrowErrorFilter target;

    private RequestContext requestContext;
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        target = new ThrowErrorFilter();
        requestContext = mock(RequestContext.class);
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void filterType() throws Exception {
        assertEquals(PRE_TYPE, target.filterType());
    }

    @Test
    public void filterOrder() throws Exception {
        Assert.assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_REQUEST_IS_FORWARDED, target.filterOrder());
    }

    @Test
    public void whenShouldFilterIsCalledItShouldFilterRequestsWithSwattedInTheUri() throws Exception {
        when(request.getRequestURI()).thenReturn("exception");
        when(requestContext.getRequest()).thenReturn(request);
        RequestContext.testSetCurrentContext(requestContext);

        assertEquals(true, target.shouldFilter());
    }

    @Test
    public void whenShouldFilterIsCalledItShouldNotFilterRequestsWithoutSwattedInTheUri() throws Exception {
        when(request.getRequestURI()).thenReturn("aFineUri");
        when(requestContext.getRequest()).thenReturn(request);
        RequestContext.testSetCurrentContext(requestContext);
        assertEquals(false, target.shouldFilter());
    }

    @Test(expected = RuntimeException.class)
    public void run() throws Exception {
        target.run();
    }

}