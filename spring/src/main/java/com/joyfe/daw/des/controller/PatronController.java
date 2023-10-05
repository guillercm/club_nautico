package com.joyfe.daw.des.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyfe.daw.des.response.Barco;
import com.joyfe.daw.des.response.Patron;
import com.joyfe.daw.des.response.Salida;
import com.joyfe.daw.des.response.Socio;
import com.joyfe.daw.des.service.PatronService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/patrones")
public class PatronController {
    
	@Autowired
    private PatronService patronService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Patron p = patronService.getPatronById(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
	@PostMapping("/")
	public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
		Patron p = patronService.addPatron(patron);
	    return new ResponseEntity<>(p, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Patron>> getAllPatrones() {
	    List<Patron> patrones = patronService.getAllPatrones();
	    return new ResponseEntity<>(patrones, HttpStatus.OK);
	}
	
	@PostMapping("/all")
	public ResponseEntity<List<Patron>> addSocio(@RequestBody List<Patron> patrones) {
		int contador = 0;
		for (Patron p : patrones) {
			Patron s_bbdd = patronService.addPatron(p);
			if (s_bbdd == null) {
				return new ResponseEntity<>(patrones, HttpStatus.BAD_REQUEST);
			}
			patrones.set(contador, s_bbdd);
			contador++;
		}
	    return new ResponseEntity<>(patrones, HttpStatus.CREATED);
	}
    
}