package org.saxing.service1rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    // 跟auth-center里定义的ACCESS_TOKEN_USURPED("040017", "access token usurped", "访问令牌被挪用")一致
    private static final String ACCESS_TOKEN_USURPED = "040017";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        if (handler instanceof HandlerMethod && !request.getRequestURI().contains("ignore/")) {
//            SessionUserHelper sessionUserHelper = xxxxAutoConfiguration.getApplicationContext()
//                    .getBean(SessionUserHelper.class);
//
//            // 出于安全考虑，检测accessToken是否被挪用，目前只检查web请求
//            if (checkTokenIsUsurped(sessionUserHelper, request)) {
//                writeJsonResponse(response, HttpStatus.CONFLICT, ResultVO.build(ACCESS_TOKEN_USURPED, "登录已失效，请重新登录"));
//                return false;
//            }
//
//            // 检查短链接进来的权限
//            HandlerMethod method = (HandlerMethod) handler;
//            TokenAccessPermission tokenAccessPermission = method.getMethodAnnotation(TokenAccessPermission.class);
//            TokenAccessPermissionCheck tokenAccessPermissionCheck = xxxxAutoConfiguration.getApplicationContext()
//                    .getBean(TokenAccessPermissionCheck.class);
//            if (!tokenAccessPermissionCheck.checkPermission(tokenAccessPermission)) {
//                LOGGER.info("该资源需要正常登陆权限, url:{}, session:{}", request.getRequestURI(),
//                        sessionUserHelper.getSessionInfo());
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                return false;
//            }
//
//            // 检查员工是否被停用
//            EmpStatusCheck empStatusCheck = xxxxAutoConfiguration.getApplicationContext()
//                    .getBean(EmpStatusCheck.class);
//            JSONObject empStatusCheckResult = empStatusCheck.checkEmpStatus();
//            if (!empStatusCheckResult.getBooleanValue("isPass")) {
//
//                writeJsonResponse(response, HttpStatus.CONFLICT,
//                        ResultVO.build(EntBizExceptionCode.EMPLOYEE_DISABLED.getCode(),
//                                String.format("您已被%s管理员移除", empStatusCheckResult.getString("entName"))));
//                return false;
//            }
//
//            IgnorePermissionCheck ignorePermissionCheck = method.getMethodAnnotation(IgnorePermissionCheck.class);
//            if (ignorePermissionCheck != null) {
//                return true;
//            }
//            Permission needPermission = method.getMethodAnnotation(Permission.class);
//            if (needPermission == null) {
//                Object controllerObj = method.getBean();
//                needPermission = controllerObj.getClass().getAnnotation(Permission.class);
//            }
//            // 检查所配置的权限
//            if (needPermission != null) {
//                List<PermissionEnum> permissionEnums = Arrays.asList(needPermission.value());
//                boolean isPass = getPermissionCheck(needPermission.operator()).checkPermission(permissionEnums);
//                if (!isPass) {
//                    LOGGER.info("资源权限校验不通过, 需要权限: {}, url:{}, session:{}", permissionEnums.toString(),
//                            request.getRequestURI(), sessionUserHelper.getSessionInfo());
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    return false;
//                }
//            }
//        }
        return true;
    }

    /**
     * 检查访问token是否被盗用，根据请求ip和userAgent的信息来比较
     * @param sessionUserHelper
     * @return
     */
//    private boolean checkTokenIsUsurped(SessionUserHelper sessionUserHelper, HttpServletRequest request) {
//        if (sessionUserHelper.getRequestSource() == RegisterSourceEnum.WEB){
//            String tokenRequestIp = sessionUserHelper.getTokenRequestIp();
//            if(StringUtils.isNotEmpty(tokenRequestIp)){
//                if (!StringUtils.equals(sessionUserHelper.getRequestIp(), tokenRequestIp)
//                        || !StringUtils.equals(request.getHeader("user-agent"), sessionUserHelper.getTokenUserAgent())) {
//                    LOGGER.info("token被挪用, requestIp: {}, user-agent: {}, session:{}", sessionUserHelper.getRequestIp(),
//                            request.getHeader("user-agent"), sessionUserHelper.getSessionInfo());
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private IPermissionCheck getPermissionCheck(PermissionOperatorEnum operatorEnum) {
//        if (operatorEnum == PermissionOperatorEnum.OR) {
//            return xxxxAutoConfiguration.getApplicationContext().getBean(PermissionOrCheck.class);
//        }
//        return xxxxAutoConfiguration.getApplicationContext().getBean(PermissionAndCheck.class);
//    }
//
//    private void writeJsonResponse(HttpServletResponse response, HttpStatus httpStatus, ResultVO resultVO) {
//        response.setStatus(httpStatus.value());
//        response.setContentType("application/json; charset=utf-8");
//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//            out.append(JSON.toJSONString(resultVO));
//        }
//        catch (IOException e) {
//            LOGGER.warn("返回response异常, ", e);
//        }
//        finally {
//            if (out != null) {
//                out.close();
//            }
//        }
//    }

}
