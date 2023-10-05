package com.joyfe.daw.des;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.joyfe.daw.des.util.GeneradorArchivos;
import com.joyfe.daw.des.util.JksProperties;

@SpringBootApplication
@EnableConfigurationProperties(JksProperties.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//GeneradorArchivos.crearClases();
	}

}
