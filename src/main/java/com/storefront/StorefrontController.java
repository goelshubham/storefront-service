package com.storefront;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
@RequestMapping(value="/storefront")
public class StorefrontController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${storefront.key}")
	private String storefrontProp;
	
	@RequestMapping(value="/products", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getProductList() {
		
		ResponseEntity<String> re = restTemplate.getForEntity("http://localhost:8002/productservice/products", String.class );
		System.out.println(re.getBody());
		
		return re;
	}
	
	@RequestMapping(value="/props", method=RequestMethod.GET, produces=MediaType.ALL_VALUE)
	public String getProperty() {
		return storefrontProp;
	}
	
}
