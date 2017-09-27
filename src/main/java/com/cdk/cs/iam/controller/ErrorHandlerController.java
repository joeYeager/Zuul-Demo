package com.cdk.cs.iam.controller;

import com.cdk.cs.iam.contants.ErrorConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@Slf4j
public class ErrorHandlerController implements ErrorController {

    @Override
    public String getErrorPath() {
        return ErrorConstants.ERROR_ENDPOINT;
    }

    @RequestMapping(value = ErrorConstants.ERROR_ENDPOINT, produces = "application/json")
    public ResponseEntity error(final HttpServletRequest request) {
        final int status = getErrorStatus(request);
        final String errorMessage = getErrorMessage(status);

        HashMap<String, String> error = new HashMap<>();
        error.put("message", errorMessage);

        return ResponseEntity.status(status).body(error);
    }

    private int getErrorStatus(final HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            return statusCode;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String getErrorMessage(final int status) {
        if (status == HttpStatus.NOT_FOUND.value()) {
            return ErrorConstants.NOT_FOUND_MESSAGE;
        }
        return ErrorConstants.INTERNAL_SERVER_ERROR_MESSAGE;
    }
}
