package com.joyfe.daw.des.response;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.annotation.Nullable;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Setter;
  
@Entity(name = "patrones")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Patron {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String nombrePatron;

	@NonNull
	private String apellidosPatron;

	@NonNull
	private String dniPatron;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePatron() {
		return nombrePatron;
	}

	public void setNombrePatron(String nombrePatron) {
		this.nombrePatron = nombrePatron;
	}

	public String getApellidosPatron() {
		return apellidosPatron;
	}

	public void setApellidosPatron(String apellidosPatron) {
		this.apellidosPatron = apellidosPatron;
	}

	public String getDniPatron() {
		return dniPatron;
	}

	public void setDniPatron(String dniPatron) {
		this.dniPatron = dniPatron;
	}


	
}