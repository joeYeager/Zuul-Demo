package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ErrorFilterTest {

    private ErrorFilter target;

    @Before
    public void setUp() {
        target = new ErrorFilter();
    }

    @Test
    public void testFilterOrder() throws Exception {
        Assert.assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_DEFAULT_ERROR_FILTER, target.filterOrder());
    }

}