package org.yeager.zuul.controller;

import org.yeager.zuul.contants.ErrorConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ErrorHandlerControllerTest {

    private ErrorHandlerController target;
    private HashMap<String, String>  expected;

    @Before
    public void setUp() throws Exception {
        target = new ErrorHandlerController();
        expected = new HashMap<>();
    }

    @Test
    public void getErrorPath() throws Exception {
        Assert.assertEquals(ErrorConstants.ERROR_ENDPOINT, target.getErrorPath());
    }

    @Test
    public void whenThereIsNoStatusCodeSetReturnInternalServerError() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute(anyString())).thenReturn(null);
        ResponseEntity actual = target.error(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());
    }

    @Test
    public void whenThereIsAStatusCodeSetReturnThatStatusCode() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute(anyString())).thenReturn(HttpStatus.I_AM_A_TEAPOT.value());
        ResponseEntity actual = target.error(request);
        assertEquals(HttpStatus.I_AM_A_TEAPOT, actual.getStatusCode());
    }

    @Test
    public void whenNotFoundIsReturnedTheMessageShouldBeNotFound() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute(anyString())).thenReturn(HttpStatus.NOT_FOUND.value());
        ResponseEntity actual = target.error(request);
        expected.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        assertEquals(expected, actual.getBody());
    }

    @Test
    public void whenNotFoundStatusIsNotReturnedTheMessageShouldBeIntenalServerError() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute(anyString())).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ResponseEntity actual = target.error(request);
        expected.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        assertEquals(expected, actual.getBody());
    }
}