package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.filters.RouteFilter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteFilterTest {

    private RouteFilter target;

    @Before
    public void setUp() throws Exception {
        target = new RouteFilter();
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