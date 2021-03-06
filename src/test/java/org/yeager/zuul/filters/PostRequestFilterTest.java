package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import org.yeager.zuul.contants.HeaderConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
        Assert.assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_RESPONSE_IS_FORWARDED, target.filterOrder());
    }

    @Test
    public void whenShouldFilterIsCalledItShouldAlwaysReturnTrue() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_ALL_TRAFFIC, target.shouldFilter());
    }

    @Test
    public void whenRunIsCalledAddTimestampHeaderIsNullDoNotSetResponseTimeHeader() throws Exception {
        when(requestContext.get(HeaderConstants.TIMESTAMP_HEADER)).thenReturn(System.currentTimeMillis());
        when(requestContext.get(HeaderConstants.REQUEST_ID_HEADER)).thenReturn("");
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(response, times(0)).setHeader(eq(HeaderConstants.RESPONSE_TIME), anyString());
    }

    @Test
    public void whenRunIsCalledItShouldAddTheTotalResponseTimeHeader() throws Exception {
        when(requestContext.get(HeaderConstants.TIMESTAMP_HEADER)).thenReturn(System.currentTimeMillis());
        when(requestContext.get(HeaderConstants.REQUEST_ID_HEADER)).thenReturn("");
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).addZuulResponseHeader(eq(HeaderConstants.RESPONSE_TIME), anyString());
    }
}
