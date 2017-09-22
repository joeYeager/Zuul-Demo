package com.cdk.cs.iam.filters.pre;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExclusivePreFilterTest {

    private ExclusivePreFilter target;

    @Before
    public void setUp() throws Exception {
        target = new ExclusivePreFilter();
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