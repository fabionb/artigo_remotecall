package br.com.fnbrandao.artigo_remotecall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Autowired
	private RemoteCallService remoteCallService;

	@GetMapping("/testTransactionalOutside/{sleep}")
	public void testTransactionalOutside(@PathVariable("sleep") int sleep) {
		remoteCallService.testTransactionalOutside(sleep, System.currentTimeMillis());
	}

	@GetMapping("/testTransactionalCorrect/{sleep}")
	public void testTransactionalCorrect(@PathVariable("sleep") int sleep) {
		remoteCallService.testTransactionalCorrect(sleep, System.currentTimeMillis());
	}

	@GetMapping("/slowService/{sleep}")
	public void slowService(@PathVariable("sleep") int sleep) throws InterruptedException {
		Thread.sleep(sleep);
	}

}
