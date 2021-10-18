package br.com.fnbrandao.artigo_remotecall;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Service
public class RemoteCallService {

	private static final Logger logger = LoggerFactory.getLogger(RemoteCallService.class);

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private HikariDataSource dataSource;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationContext applicationContext;

	private RemoteCallService remoteCallService;

	@PostConstruct
	public void setup() {
		this.remoteCallService = applicationContext.getBean(RemoteCallService.class);
	}

	private void logMaxId() {
		logger.info("Max Log Id {} ",
				entityManager.createQuery("SELECT MAX(id) FROM RemoteCallLogEntity", Long.class).getSingleResult());
	}

	@Transactional
	public void testTransactionalOutside(int sleep, long start) {
		logMaxId();
		logger.info("In use connections {}", dataSource.getHikariPoolMXBean().getActiveConnections());
		logger.info("Response {}",
				restTemplate.getForObject(URI.create("http://localhost:8081/slowService/" + sleep), String.class));

		RemoteCallLogEntity log = new RemoteCallLogEntity();
		log.setSleep(sleep);
		log.setTime(System.currentTimeMillis() - start);
		entityManager.persist(log);
	}

	public void testTransactionalCorrect(int sleep, long start) {
		logMaxId();
		logger.info("In use connections {}", dataSource.getHikariPoolMXBean().getActiveConnections());
		logger.info("Response {}",
				restTemplate.getForObject(URI.create("http://localhost:8081/slowService/" + sleep), String.class));

		remoteCallService.persistLog(sleep, System.currentTimeMillis() - start);
	}

	@Transactional
	public void persistLog(int sleep, long time) {
		RemoteCallLogEntity log = new RemoteCallLogEntity();
		log.setSleep(sleep);
		log.setTime(time);
		entityManager.persist(log);
	}
}
