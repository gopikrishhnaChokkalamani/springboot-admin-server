package com.gi.springboot;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class StudentConsumerController {

	/*
	 * @Autowired protected RestTemplate restTemplate;
	 * 
	 * @RequestMapping("/") public String welcomePage(Model model) {
	 * model.addAttribute("message", getResponseFromEurekaClient()); return
	 * "welcome"; }
	 * 
	 * public String getResponseFromEurekaClient() { String message =
	 * restTemplate.getForObject("http://STUDENT-CLIENT" + "/student",
	 * String.class); return message; }
	 */

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/consumer")
	public String getEmployee(Model model) throws RestClientException, IOException {

		List<ServiceInstance> instances = discoveryClient.getInstances("student-client");
		ServiceInstance serviceInstance = instances.get(0);
		String baseUrl = serviceInstance.getUri().toString() + "/student";

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println("##########################");
		System.out.println(response.getBody());
		System.out.println("##########################");

		return response.getBody();
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
