package com.employeeMangementServices.healthChecks;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class CustomHeathIndicator implements HealthIndicator {

	@Override
	public Health health() {
//		boolean error=true;
//		if(error) return Health.down().withDetail("Error Key", 123).build();
		
		return Health.up().withDetail("Error Key", 123).build();
	}

}
