package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.HystrixService;

@RestController
public class HystrixController {

	@Autowired
	private HystrixService service;

	@RequestMapping(value = "/test")
	private String checkHystrixCircuitBreaker(
			@RequestParam(value = "fallback", defaultValue = "false") boolean fallback,
			@RequestParam(value = "timeout", defaultValue = "0") int timeout) throws Exception {
		return service.mainMethodReturn(fallback, timeout);
	}
}
