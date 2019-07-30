package com.hiynn.spring.security.config;

import com.hiynn.spring.security.config.properties.SecurityProperties;
import com.hiynn.spring.security.untils.ResultBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 自定义登录认证流程
 * @Project hiynn-lee-examples
 * @Package com.hiynn.spring.security.config
 * @Author ZhouXiaoLe
 * @Date 2019-07-27 22:18
 */
@RestController
public class BrowserSecurityController {
    @Autowired
    SecurityProperties securityProperties;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * @param request
     * @param response
     * @return java.lang.String
     * @throws
     * @Description 当需要认证时跳转到这
     * @Method requireAuthention
     * @Author ZhouXiaoLe
     * @Date 2019-07-27  10:21:39
     **/
    @RequestMapping("/auth/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultBuilder requireAuthention(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String url = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(url, ".html")) {
                String redirectUrl = securityProperties.getBrowser().getLoginPage();
                redirectStrategy.sendRedirect(request, response, redirectUrl);
            }
            /**
             * 如果是html请求跳转到form表单登录
             */
        }
        return ResultBuilder.fail();
    }
}
