package fr.formation.masterpiece.config.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
public class CorsFilter implements Filter {

    @Value("${server.url}")
    private String allowedOrigin;

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
	HttpServletResponse response = (HttpServletResponse) servletResponse;
	response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
	filterChain.doFilter(servletRequest, servletResponse);
    }
}