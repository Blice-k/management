package com.library.management.service.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Error implements ErrorController {
    @RequestMapping("/error")
    public Map<String, Object> handleError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        errorAttributes.put("message", "Resource not found");
        return errorAttributes;
    }
}
