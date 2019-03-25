package com.dozgunyal.service.shorturl.services;

public interface ShorturlService {
	
	public String getShorturl(String url);
	
	public String getUrl(String encoded);
	
}
