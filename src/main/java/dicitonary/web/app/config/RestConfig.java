package dicitonary.web.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean
	public RestOperations createRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ClientHttpRequestFactory createClientHttpRequestFactory(
			@Value("${connect.timeout}") final Integer connectTimeout,
			@Value("${read.timeout}") final Integer readTimeout) {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);
		httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(connectTimeout);
		return httpComponentsClientHttpRequestFactory;
	}

}
