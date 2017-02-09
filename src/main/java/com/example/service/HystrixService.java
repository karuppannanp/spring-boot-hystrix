package com.example.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HystrixService {
	
	@HystrixCommand(groupKey = "hystrix-example", fallbackMethod = "fallbackReturn", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") })
	public String mainMethodReturn(boolean fallback, int timeout) throws Exception {
		if (fallback) {
			throw new Exception("Falling back based on input");
		}
		if (timeout > 0) {
			Thread.sleep(timeout);
		}
		return "Main method executed";
	}
	
	public String fallbackReturn(boolean fallback, int timeout) {
		return "Fallback method executed";
	}
}
