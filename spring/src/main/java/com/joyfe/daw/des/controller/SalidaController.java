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

import com.joyfe.daw.des.response.Salida;
import com.joyfe.daw.des.service.SalidaService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/salidas")
public class SalidaController {
    
	@Autowired
	private SalidaService salidaService;

	@GetMapping("/{id}")
	public ResponseEntity<Salida> getSalidaById(@PathVariable Long id) {
	    Salida s = salidaService.getSalidaById(id);
	    if (s != null) {
	        return new ResponseEntity<>(s, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/")
	public ResponseEntity<List<Salida>> getAllSalidas() {
	    List<Salida> salidas = salidaService.getAllSalidas();
	    return new ResponseEntity<>(salidas, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Salida> addSalida(@RequestBody Salida salida) {
	    Salida s = salidaService.addSalida(salida);
	    return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Salida> updateSalida(@PathVariable Long id, @RequestBody Salida salida) {
	    Salida s = salidaService.updateSalida(id, salida);
	    if (s != null) {
	        return new ResponseEntity<>(s, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSalida(@PathVariable Long id) {
	    Salida result = salidaService.deleteSalida(id);
	    if (result != null) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
    
}