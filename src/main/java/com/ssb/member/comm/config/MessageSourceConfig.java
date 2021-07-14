package com.ssb.member.comm.config;

import java.util.Locale;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageSourceConfig implements WebMvcConfigurer{
	
	@Value("${message.cacheSecond}")
	private int messageCacheSecond;
	
	private String localeKey = "lang";
	
	@Bean
	public LocaleResolver localeResolver() {
		var resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREA);
		resolver.setCookieName(localeKey);
		
		return resolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		var lci = new LocaleChangeInterceptor();
		lci.setParamName(localeKey);
		return lci;
	}
	
	
	@Bean
	public MessageSource messageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/message");
		messageSource.setDefaultLocale(Locale.KOREA);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(messageCacheSecond);
		
		return messageSource;
	}
	
	@Bean
	public MessageSource validationMessageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/validations/validation");
		messageSource.setDefaultLocale(Locale.KOREA);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(messageCacheSecond);
		
		return messageSource;
	}
	
	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		var msa = messageSource(); 
		return new MessageSourceAccessor(msa);
	}
	
	@Bean
	public Validator validator() {
		var bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(validationMessageSource());
		return bean;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
	}

}
