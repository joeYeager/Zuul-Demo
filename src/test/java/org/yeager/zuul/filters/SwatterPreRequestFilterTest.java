package org.yeager.zuul.filters;

import org.yeager.zuul.contants.FilterConstants;
import org.yeager.zuul.service.RequestSwatterService;
import com.netflix.zuul.context.RequestContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class SwatterPreRequestFilterTest {

    @InjectMocks
    private SwatterPreRequestFilter target;

    @Mock
    private RequestSwatterService requestSwatterService;

    private RequestContext requestContext;
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        requestContext = mock(RequestContext.class);
        request = mock(HttpServletRequest.class);

    }

    @Test
    public void whenFilterTypeIsCalledItShouldReturnPreType() throws Exception {
        assertEquals(PRE_TYPE, target.filterType());
    }

    @Test
    public void testFilterOrder() throws Exception {
        Assert.assertEquals(FilterConstants.SHOULD_BE_THE_FIRST_PRE_FILTER_TO_RUN, target.filterOrder());
    }

    @Test
    public void whenShouldFilterIsCalledItShouldFilterRequestsWithSwattedInTheUri() throws Exception {
        when(request.getRequestURI()).thenReturn("swatted");
        when(requestContext.getRequest()).thenReturn(request);
        RequestContext.testSetCurrentContext(requestContext);

        assertEquals(true, target.shouldFilter());
    }

    @Test
    public void whenShouldFilterIsCalledItShouldNotFilterRequestsWithoutSwattedInTheUri() throws Exception {
        when(request.getRequestURI()).thenReturn("aFineUri");
        when(requestContext.getRequest()).thenReturn(request);
        RequestContext.testSetCurrentContext(requestContext);
        assertEquals(false, target.shouldFilter());
    }

    @Test
    public void whenRunIsCalledItShouldCallTheSwatterService() throws Exception {
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestSwatterService).swat(requestContext, HttpStatus.I_AM_A_TEAPOT, "Get Swatted");
    }

}