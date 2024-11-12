package org.example.backendexample.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backendexample.dox.User;
import org.example.backendexample.excepiton.Code;
import org.example.backendexample.excepiton.myException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //这个拦截器放在login之后，login已经往request塞入了token,role，所以这里可以直接拿出来用
        if(User.ADMIN.equals(request.getAttribute("role"))) {
            return true;
        }
       else  throw myException.builder().code(Code.FORBIDDEN).build();
    }
}
