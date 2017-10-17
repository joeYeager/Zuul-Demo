package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

public class RouteFilterTest {

    private RouteFilter target;
    private RequestContext requestContext;

    @Before
    public void setUp() throws Exception {
        target = new RouteFilter();
        requestContext = mock(RequestContext.class);
    }

    @Test
    public void whenFilterTypeIsCalledItShouldReturnRouteType() throws Exception {
        assertEquals(ROUTE_TYPE, target.filterType());
    }

    @Test
    public void testFilterOrder() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_HOST_ROUTING_OCCURS, target.filterOrder());
    }

    @Test
    public void whenShouldFilterIsCalledItReturnFalseIfRoutingPropertyIsNotSet() throws Exception {
        when(requestContext.get(HeaderConstants.ROUTING_HEADER)).thenReturn(null);
        RequestContext.testSetCurrentContext(requestContext);
        assertEquals(false, target.shouldFilter());
    }

    @Test
    public void whenShouldFilterIsCalledItReturnTrueIfRoutingPropertyIsSet() throws Exception {
        when(requestContext.get(HeaderConstants.ROUTING_HEADER)).thenReturn(true);
        RequestContext.testSetCurrentContext(requestContext);
        assertEquals(true, target.shouldFilter());
    }

    @Test
    public void whenRunIsCalledItShouldSetTheRouteHost() throws Exception {
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).setRouteHost(any());
    }
}