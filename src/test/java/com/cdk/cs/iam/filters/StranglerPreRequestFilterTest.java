package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import com.cdk.cs.iam.contants.HeaderConstants;
import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class StranglerPreRequestFilterTest {

    private StranglerPreRequestFilter target;

    @Before
    public void setUp() throws Exception {
        target = new StranglerPreRequestFilter();
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
    public void run() throws Exception {
        RequestContext requestContext = mock(RequestContext.class);
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).set(HeaderConstants.ROUTING_HEADER, true);
    }

}