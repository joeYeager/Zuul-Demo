package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.contants.FilterConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorFilterTest {

    private ErrorFilter target;

    @Before
    public void setUp() {
        target = new ErrorFilter();
    }

    @Test
    public void testFilterOrder() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_BEFORE_DEFAULT_ERROR_FILTER, target.filterOrder());
    }

}