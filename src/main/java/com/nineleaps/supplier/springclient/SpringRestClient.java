package com.nineleaps.supplier.springclient;

import java.util.Base64;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.nineleaps.supplier.exception.UserException;


@Service
public class SpringRestClient {

	final Logger logger = Logger.getLogger(SpringRestClient.class);

	public Object call(String url, Map<String, String> headers, Object payload, HttpMethod httpMethod)
			throws UserException {

		Object postResponse = null;
		try {
			logger.info("requestUrl "+url+" headers "+headers+" Request Object "+payload+" HttpMethod "+httpMethod);
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();

			if (headers != null)
				for (Map.Entry<String, String> headersEntry : headers.entrySet()) {
					httpHeaders.add(headersEntry.getKey(), headersEntry.getValue());
				}

			HttpEntity<?> httpEntity = new HttpEntity<>(payload, httpHeaders);

			postResponse = restTemplate.exchange(url, httpMethod, httpEntity, String.class);

			return postResponse;
		} catch (RuntimeException e) {
			logger.error("call method", e);
			throw new UserException("error occured userAuthentication" + e.getMessage(),e);
		}
	}

}