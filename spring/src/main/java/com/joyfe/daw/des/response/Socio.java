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
import lombok.Setter;
  
@Entity(name = "socios")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Socio {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String nombre;

	@NonNull
	private String apellido;

	@NonNull
	private String dni;

	@NonNull
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Barco> barcos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<Barco> getBarcos() {
		return barcos;
	}

	public void setBarcos(List<Barco> barcos) {
		this.barcos = barcos;
	}

	
}