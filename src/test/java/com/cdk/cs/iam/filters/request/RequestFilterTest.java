package com.cdk.cs.iam.filters.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestFilterTest {

    private RequestFilter target;

    @Before
    public void setUp() throws Exception {
        target = new RequestFilter();
    }

    @Test
    public void testFilterType() throws Exception {
        assertEquals(false, true);
    }

    @Test
    public void testFilterOrder() throws Exception {
        assertEquals(false, true);
    }

    @Test
    public void testShouldFilter() throws Exception {
        assertEquals(false, true);
    }

    @Test
    public void testRun() throws Exception {
        assertEquals(false, true);
    }
}