package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

@Ignore
public class RouteFilterTest {

    private RouteFilter target;

    @Before
    public void setUp() throws Exception {
        target = new RouteFilter();
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
    public void whenShouldFilterIsCalledItShouldAlwaysReturnTrue() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_ALL_TRAFFIC, target.shouldFilter());
    }

    @Test
    public void testRun() throws Exception {
        RequestContext requestContext = mock(RequestContext.class);
        RequestContext.testSetCurrentContext(requestContext);

        target.run();

        assertEquals(false, true);
    }
}