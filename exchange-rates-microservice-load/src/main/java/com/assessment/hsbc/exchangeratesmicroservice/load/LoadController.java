package com.assessment.hsbc.exchangeratesmicroservice.load;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/load")
public class LoadController {

	@Autowired
	LoadService service;

	@GetMapping("/year")
	public CompletableFuture<ResponseEntity<?>> loadData(@RequestParam(required = false) String base,
			@RequestParam(required = false) List<String> symbols) {
		return service.loadData(base,symbols);
	}
	
	@GetMapping("/{date}")
	public CompletableFuture<ResponseEntity<?>> loadData(@PathVariable String date,
			@RequestParam(required = false) String base,
			@RequestParam(required = false) List<String> symbols) {
		return service.loadData(date,base,symbols);
	}
	
}
