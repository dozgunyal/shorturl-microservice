package com.dozgunyal.service.shorturl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShorturlConfig {
	
	@Value("${shorturl.service.url.format}")
	private String serviceUrlFormat;
	
	@Value("${shorturl.service.redis.urlkey.format}")
	private String redisUrlKeyFormat;
	
	@Value("${shorturl.service.redis.idsequence.key}")
	private String redisIdSequenceKey;
	
	@Value("${shorturl.service.basic.auth.user}")
	private String basicAuthUser;
	
	@Value("${shorturl.service.basic.auth.password}")
	private String basicAuthPassword;
	
	public String getRedisIdSequenceKey() {
		return redisIdSequenceKey;
	}
	
	public String getRedisUrlKeyFormat() {
		return redisUrlKeyFormat;
	}
	
	public String getServiceUrlFormat() {
		return serviceUrlFormat;
	}
	
	public String getBasicAuthPassword() {
		return basicAuthPassword;
	}
	
	public String getBasicAuthUser() {
		return basicAuthUser;
	}
	
}
