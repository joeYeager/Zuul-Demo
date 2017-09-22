package com.cdk.cs.iam.filters;

import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Ignore
public class PostRequestFilterTest {

    private PostRequestFilter target;

    @Before
    public void setUp() throws Exception {
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
    public void testRun() throws Exception {
        RequestContext requestContext = mock(RequestContext.class);
        RequestContext.testSetCurrentContext(requestContext);

        target.run();

        assertEquals(false, true);
    }
}
