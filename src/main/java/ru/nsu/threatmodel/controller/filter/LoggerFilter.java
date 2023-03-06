package ru.nsu.threatmodel.controller.filter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter
@Slf4j
@Component
public class LoggerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var requestCacheWrapper = new ContentCachingRequestWrapper(request);
        var responseCacheWrapper = new ContentCachingResponseWrapper(response);

        var startTime = System.currentTimeMillis();
        filterChain.doFilter(requestCacheWrapper, responseCacheWrapper);
        var endTime = System.currentTimeMillis();

        log.info("Получен запрос [" + requestCacheWrapper.getMethod() + "] [" +
                requestCacheWrapper.getServletPath() + "]: " +
                new String(requestCacheWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));

        log.info("Ответ на запрос [" + requestCacheWrapper.getMethod() + "] [" +
                requestCacheWrapper.getServletPath() + "]: " +
                new String(responseCacheWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));

        log.info("Время обработки запроса [" + requestCacheWrapper.getMethod() + "] [" +
                requestCacheWrapper.getServletPath() + "]: " +
                (endTime - startTime) + "мс");

        responseCacheWrapper.copyBodyToResponse();

    }
}
