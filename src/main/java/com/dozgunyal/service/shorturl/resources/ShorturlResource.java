package com.dozgunyal.service.shorturl.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.dozgunyal.service.shorturl.model.Url;
import com.dozgunyal.service.shorturl.services.ShorturlService;
 
@RestController
public class ShorturlResource {
	
	private ShorturlService shorturlService;
	public ShorturlResource(ShorturlService shorturlService) {
		this.shorturlService = shorturlService;
	}
	
	
	@PostMapping(path = "/v1/shorten")
	public ResponseEntity<Url> getInventory(@RequestBody Url longurl) throws Exception {
		return ResponseEntity.ok(new Url(shorturlService.getShorturl(longurl.getUrl())));
	}
	
	@RequestMapping(value="/r/{id}")
	public RedirectView redirectToUrl(@PathVariable("id") String id) {
		String url = shorturlService.getUrl(id);
		if(url == null) {
			throw new UrlFoundException();
		}
		return new RedirectView(url);
	}
	
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "url not found")
	public class UrlFoundException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -9094534482669826488L;
		
	}
}
