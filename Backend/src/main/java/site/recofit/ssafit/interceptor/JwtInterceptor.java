package site.recofit.ssafit.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import site.recofit.ssafit.utility.jwt.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    private static final String HEADER_AUTH = "access-token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS"))
            return true;

        String token = request.getHeader(HEADER_AUTH);

        if (token != null) {
            jwtUtil.valid(token);
            return true;
        }

        throw new Exception("유효하지 않은 접근");
    }
}