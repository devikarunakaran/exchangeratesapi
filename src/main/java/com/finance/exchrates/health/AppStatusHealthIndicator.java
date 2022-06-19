package com.finance.exchrates.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class AppStatusHealthIndicator implements ApplicationListener<ApplicationReadyEvent>, HealthIndicator {

	public Health health() {
		// TODO Auto-generated method stub
		return null;
	}

	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		
	}


}