package com.dgyj.yjpicture.config;

import com.dgyj.yjpicture.config.auth.XssFilter;
import com.dgyj.yjpicture.common.Interceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${resources.uri_path}")
    private String uriPath;

    @Value("${resources.location}")
    private String targetRootLocation;

    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
        cookieLocaleResolver.setCookieName("THYMELEAF_LANG");
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        System.out.println("localeChangeInterceptor");
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Bean
    public MessageSource messageSource(@Value("${spring.messages.basename}") String basename,
                                       @Value("${spring.messages.encoding}") String encoding){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);
        messageSource.setCacheSeconds(60);//???????????? ??????????????? ??????

        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(
            @Qualifier("messageSource") MessageSource messageSource
    ) {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public FilterRegistrationBean paramXssFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new XssFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

//    @Bean
//    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
//        return new HiddenHttpMethodFilter();
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(customInterceptor())
                .addPathPatterns("/*")  //?????? ????????? ????????? ??????
                .excludePathPatterns("/sample")  //??????????????? ????????????
                .excludePathPatterns("/editorUpload")
                .excludePathPatterns("/assets/*")
                .excludePathPatterns("/assets/*/*");
    }

    @Bean
    public Interceptor customInterceptor(){
        return new Interceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uriPath+"/**").addResourceLocations("file://"+targetRootLocation+"/").setCacheControl(CacheControl.noCache()).resourceChain(true).addResolver(new PathResourceResolver());
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/*");
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        SortHandlerMethodArgumentResolver sortArgumentResolver = new SortHandlerMethodArgumentResolver();
//        sortArgumentResolver.setSortParameter("sortBy");
//        sortArgumentResolver.setPropertyDelimiter("-");
//
//        PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver(sortArgumentResolver);
//        pageableArgumentResolver.setOneIndexedParameters(true);
//        pageableArgumentResolver.setMaxPageSize(500);
//        pageableArgumentResolver.setFallbackPageable(PageRequest.of(0,10));
//        argumentResolvers.add(pageableArgumentResolver);
//
//    }
}
