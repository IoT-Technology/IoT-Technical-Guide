package com.sanshengshui.token.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: 穆书伟
 * @Date: 19-4-10 上午9:44
 * @Version 1.0
 */
public class ThingsboardSecurityConfiguration extends WebSecurityConfigurerAdapter  {

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String JWT_TOKEN_QUERY_PARAM = "token";
}
