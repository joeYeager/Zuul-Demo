package com.cdk.cs.iam.filters;

import com.cdk.cs.iam.service.RequestSwatterService;
import com.netflix.zuul.context.RequestContext;
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

public class PreRequestFilterTest {

    @InjectMocks
    private PreRequestFilter target;

    @Mock
    private RequestSwatterService requestSwatterService;

    private RequestContext requestContext;
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        requestContext = mock(RequestContext.class);
        request = mock(HttpServletRequest.class);

        when(requestContext.getRequest()).thenReturn(request);
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
    public void whenShouldFilterIsCalledItShouldAlwaysReturnTrue() throws Exception {
        assertEquals(FilterConstants.SHOULD_FILTER_ALL_TRAFFIC, target.shouldFilter());
    }

    @Test
    public void whenRunIsCalledItShouldAddAnApiTokenHeader() throws Exception {
        RequestContext.testSetCurrentContext(requestContext);
        target.run();
        verify(requestContext).addZuulRequestHeader("Api-Token", "token-value");
    }

    @Test
    public void whenRunIsCalledItShouldSwatRequestsContainingTheStringUser() throws Exception {
        when(request.getRequestURI()).thenReturn("user");
        RequestContext.testSetCurrentContext(requestContext);

        target.run();
        verify(requestSwatterService).swat(requestContext, HttpStatus.I_AM_A_TEAPOT, "Get Swatted");
    }

    @Test
    public void whenRunIsCalledItShouldNotSwatRequestsNotContainingTheStringUser() throws Exception {
        when(request.getRequestURI()).thenReturn("aFineUri");
        RequestContext.testSetCurrentContext(requestContext);

        target.run();
        verify(requestSwatterService).swat(requestContext, HttpStatus.I_AM_A_TEAPOT, "Get Swatted");
    }
}