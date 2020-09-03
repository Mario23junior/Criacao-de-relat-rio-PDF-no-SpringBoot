package com.geradoPDF.controller;

 
import java.io.ByteArrayInputStream;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geradoPDF.model.City;
import com.geradoPDF.service.ICityService;

@RestController
 public class Controller {
      
	@Autowired
	private ICityService cityService;
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE )
	public ResponseEntity<InputStreamResource> citiesReport(){
		
		var cities = (List<City>) cityService.findAll();
		
		ByteArrayInputStream bis = GeneratePdfReport.citiesReport(cities);
		
		var headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		
 		
	}
}
