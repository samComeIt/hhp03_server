package hh.plus.server.balance.presentation.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class BalanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try{
            String tokenPayload = token.substring(6);

            // Extract balanceId from path variable
            String balanceIdStr = extractUserIdFromPath(request.getRequestURI());
            Long balanceId = Long.parseLong(balanceIdStr); // Assuming userId is a Long
            log.info("Balance ID from path variable: {}", balanceId);

            request.setAttribute("balanceId", balanceId);

            log.info("Request URL: {}", request.getRequestURL());
            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    public String extractUserIdFromPath(String requestURI) {
        String[] pathSegments = requestURI.split("/");
        for (int i = pathSegments.length - 1; i >= 0; i--) {
            if (!pathSegments[i].isEmpty()) {
                return pathSegments[i];
            }
        }
        throw new IllegalArgumentException("No balanceId found in path");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
          log.info("Response Status: {}", response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
    }
}

