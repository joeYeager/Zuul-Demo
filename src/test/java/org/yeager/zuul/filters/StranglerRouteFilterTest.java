package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

public class StranglerRouteFilterTest {

    private StranglerRouteFilter target;
    private RequestContext requestContext;

    @Before
    public void setUp() throws Exception {
        target = new StranglerRouteFilter();
        requestContext = mock(RequestContext.class);
    }

    @Test
    public void whenFilterTypeIsCalledItShouldReturnRouteType() throws Exception {
        assertEquals(ROUTE_TYPE, target.filterType());
    }

    @Test
    public void testFilterOrder() throws Exception {
        Assert.assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_HOST_ROUTING_OCCURS, target.filterOrder());
    }

    @Test
    public void whenRunIsCalledItShouldSetTheRouteHost() throws Exception {
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).setRouteHost(any());
    }
}