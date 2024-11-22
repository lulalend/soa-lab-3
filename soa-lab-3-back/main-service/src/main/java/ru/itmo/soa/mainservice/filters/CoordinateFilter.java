package ru.itmo.soa.mainservice.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.itmo.soa.mainservice.filters.io.CachedBodyHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebFilter("/api/v1/bands/*")
public class CoordinateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(httpRequest);

        if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {
            String requestBody = cachedBodyHttpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            Map<String, Object> coordinates = (Map<String, Object>) parseJsonToMap(requestBody).get("coordinates");

            if (coordinates != null) {
                Object x = coordinates.get("x");
                if (x instanceof Double) {
                    Double xValue = (Double) x;
                    if (xValue % 1 != 0) {
                        sendErrorResponse(response, "X must be an integer.");
                        return;
                    }
                }
            }
        }

        chain.doFilter(cachedBodyHttpServletRequest, response);
    }

    private Map<String, Object> parseJsonToMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    private void sendErrorResponse(ServletResponse response, String message) throws IOException {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", message);

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        response.getWriter().flush();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
