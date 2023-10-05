package com.joyfe.daw.des.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyfe.daw.des.response.Socio;
import com.joyfe.daw.des.service.SocioService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/socios")
@CrossOrigin(origins = "http://localhost:4200")
public class SocioController {
    
	@Autowired
	private SocioService socioService;

	@GetMapping("/{id}")
	public ResponseEntity<Socio> getSocioById(@PathVariable Long id) {
	    Socio s = socioService.getSocioById(id);
	    if (s != null) {
	        return new ResponseEntity<>(s, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/")
	public ResponseEntity<List<Socio>> getAllSocios() {
	    List<Socio> socios = socioService.getAllSocios();
	    return new ResponseEntity<>(socios, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Socio> addSocio(@RequestBody Socio socio) {
	    Socio s = socioService.addSocio(socio);
	    return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	
	@PostMapping("/all")
	public ResponseEntity<List<Socio>> addSocio(@RequestBody List<Socio> socios) {
		int contador = 0;
		for (Socio s : socios) {
			Socio s_bbdd = socioService.addSocio(s);
			if (s_bbdd == null) {
				return new ResponseEntity<>(socios, HttpStatus.BAD_REQUEST);
			}
			socios.set(contador, s_bbdd);
			contador++;
		}
	    return new ResponseEntity<>(socios, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Socio> updateSocio(@PathVariable Long id, @RequestBody Socio socio) {
	    Socio s = socioService.updateSocio(id, socio);
	    if (s != null) {
	        return new ResponseEntity<>(s, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSocio(@PathVariable Long id) {
	    Socio result = socioService.deleteSocio(id);
	    if (result != null) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
    
}