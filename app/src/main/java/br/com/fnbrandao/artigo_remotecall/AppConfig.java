package br.com.fnbrandao.artigo_remotecall;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder //
				.setConnectTimeout(Duration.ofSeconds(10)) //
				.setReadTimeout(Duration.ofSeconds(10)) //
				.build();
	}
}
