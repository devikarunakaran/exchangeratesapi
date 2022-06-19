package com.finance.exchrates.controller;

import com.finance.exchrates.model.ExchangeRateRequest;
import com.finance.exchrates.model.ExchangeRates;
import com.finance.exchrates.service.ExchRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@EnableSwagger2
public class ExchangeRatesController {

	@Autowired
	private ExchRatesService ratesService;

	@GetMapping(value = "/")
	@ResponseStatus(HttpStatus.OK)
	public String getHealth() {
		return "result success";
	}

	@GetMapping(value = "/ExchRates")
	public @ResponseBody String getHello() {
		return "Hello there, welcome to Currency Exchange Rate Services";
	}

	/*
		Load Exchange rates from a File
	 */
	@PostMapping(value = "/ExchRates/loadRatesFromFile")
	public ResponseEntity<String> loadRatesFromFile(@RequestPart("file") MultipartFile file) {

		if (null == file.getOriginalFilename()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(file.getOriginalFilename());
			Files.write(path, bytes);
			ratesService.loadRatesFromFile(path.toString());
			System.out.println(path.getFileName());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("Good Job", HttpStatus.OK);

	}

	/*
		Get all Exchange rates loaded in the table
	 */
	@GetMapping(value = "/ExchRates/getRates")
	public ResponseEntity<List<ExchangeRates>> getRates() {
		return new ResponseEntity<List<ExchangeRates>>(ratesService.listAll(), HttpStatus.OK);

	}

	/*
    	Get Exchange rates loaded for a specific currency pair
 	*/
	@GetMapping(value = "/ExchRates/getLatestRates/from/{from}/to/{to}")
	public ResponseEntity<ExchangeRates> getRatesFromTo(@PathVariable("from") String from,
			@PathVariable("to") String to) {
		return new ResponseEntity<ExchangeRates>(ratesService.getRatesFromTo(from, to), HttpStatus.OK);

	}

	/*
    	Get Exchange rates loaded for a specific currency pair on specified date
 	*/
	@PostMapping(value = "/ExchRates/getSpecificRatesForCurrencyPair", produces = { "application/json" }, consumes = {
			"application/json" })
	public ResponseEntity<ExchangeRates> getSpecificRatesForCurrencyPair(@RequestBody ExchangeRateRequest exch) {
		try {
			ExchangeRates ex = ratesService.getSpecificRatesFromToDate(exch);
			if (Objects.nonNull(ex))
				return new ResponseEntity<ExchangeRates>(ex, HttpStatus.OK);
			else
				return new ResponseEntity<ExchangeRates>(HttpStatus.NOT_FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ExchangeRates>(HttpStatus.NOT_FOUND);
		}

	}

	@Bean
	public Docket currencyApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.finance.exchrates.controller")).build();
	}

}
