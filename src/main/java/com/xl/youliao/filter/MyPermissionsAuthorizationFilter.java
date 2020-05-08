package com.xl.youliao.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author WeiC
 * @date 2020/5/7 17:00
 * 暂时无效
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter  {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter out = httpServletResponse.getWriter();
        JSONObject json = new JSONObject();
        json.put("code","403");
        json.put("msg","无权访问");
        out.println(json);
        out.flush();
        out.close();
        return false;
    }

}
