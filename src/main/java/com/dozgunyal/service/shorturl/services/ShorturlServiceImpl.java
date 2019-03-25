package com.dozgunyal.service.shorturl.services;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.dozgunyal.service.shorturl.config.ShorturlConfig;

import io.seruco.encoding.base62.Base62;

@Service
public class ShorturlServiceImpl implements ShorturlService {
	
	private RedisTemplate<String, Object> redisTemplate;
	private ShorturlConfig config;
	
	public ShorturlServiceImpl(RedisTemplate<String, Object> redisTemplate, ShorturlConfig config) {
		this.redisTemplate = redisTemplate;
		this.config = config;
	}
	
	public String getShorturl(String url) {
		String id = getNextId();
		String key = getKey(id);
		redisTemplate.opsForValue().set(key, url);
		String shortUrl = String.format(this.config.getServiceUrlFormat(), encodeId(id));
		return shortUrl;
	}
	
	public String getUrl(String encoded) {
		String id = decodeId(encoded);
		String key = getKey(id);
		String url = (String)redisTemplate.opsForValue().get(key);
		return url;
	}
	
	private String getKey(String id) {
		return String.format(this.config.getRedisUrlKeyFormat(), id);
	}
	
	private String getNextId() {
		RedisAtomicLong uniqueNbr = new RedisAtomicLong(this.config.getRedisIdSequenceKey(), this.redisTemplate.getConnectionFactory());
		return String.format("%d", uniqueNbr.incrementAndGet());
	}
	
	private String encodeId(String id) {
		return new String(Base62.createInstance().encode(id.getBytes()));
	}
	
	private String decodeId(String encoded) {
		return new String(Base62.createInstance().decode(encoded.getBytes()));
	}
}
