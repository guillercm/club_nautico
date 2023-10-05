package com.joyfe.daw.des.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyfe.daw.des.response.Barco;
import com.joyfe.daw.des.service.BarcoService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/barcos")
public class BarcoController {
    
	@Autowired
	private BarcoService barcoService;

	@GetMapping("/{id}")
	public ResponseEntity<Barco> getBarcoById(@PathVariable Long id) {
	    Barco b = barcoService.getBarcoById(id);
	    if (b != null) {
	        return new ResponseEntity<>(b, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/")
	public ResponseEntity<List<Barco>> getAllBarcos() {
	    List<Barco> barcos = barcoService.getAllBarcos();
	    return new ResponseEntity<>(barcos, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Barco> addBarco(@RequestBody Barco barco) {
	    Barco b = barcoService.addBarco(barco);
	    return new ResponseEntity<>(b, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Barco> updateBarco(@PathVariable Long id, @RequestBody Barco barco) {
	    Barco b = barcoService.updateBarco(id, barco);
	    if (b != null) {
	        return new ResponseEntity<>(b, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteBarco(@PathVariable Long id) {
	    Barco result = barcoService.deleteBarco(id);
	    if (result != null) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

    
    
}