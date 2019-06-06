package com.leexboo.hotel.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.leexboo.hotel.annotation.AuthToken;
import com.leexboo.hotel.util.ConstantKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            //从请求体中取出token
            //String token = request.getParameter(httpHeaderName);
            //从请求头中取出token
            String token = request.getHeader(httpHeaderName);

            log.info("Get token from request is {} ", token);
            String phone = "";
            Jedis jedis = new Jedis("localhost", 6379);
            if (token != null && token.length() != 0) {
                phone = jedis.get(token);
                log.info("Get username from Redis is {}", phone);
            }
            if (phone != null && !phone.trim().equals("")) {
                Long tokeBirthTime = Long.valueOf(jedis.get(token + phone));
                log.info("token Birth time is: {}", tokeBirthTime);
                Long diff = System.currentTimeMillis() - tokeBirthTime;
                log.info("token is exist : {} ms", diff);
                //重新设置Redis中的token过期时间
                if (diff > ConstantKit.TOKEN_RESET_TIME) {
                    jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
                    log.info("Reset expire time success!");
                    Long newBirthTime = System.currentTimeMillis();
                    jedis.set(token + phone, newBirthTime.toString());
                    jedis.expire(token + phone,ConstantKit.TOKEN_EXPIRE_TIME);
                }

                //用完关闭
                jedis.close();
                request.setAttribute(REQUEST_CURRENT_KEY, phone);
                return true;
            } else {
                JSONObject jsonObject = new JSONObject();

                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    out = response.getWriter();
                    out.println(jsonObject);

                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }
        }
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }
}

