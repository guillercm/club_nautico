package com.joyfe.daw.des.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.NonNull;

public class GeneradorArchivos {

	private static String carpeta = "C:/Users/a901258/OneDrive - Atos/Escritorio/spring/src/main/java/";
	
	private static String carpetasProyec = "com/joyfe/daw/des/";
	
	private static String carpetasProyecto = carpeta + carpetasProyec;
	
	private static String txt_data = carpetasProyecto + "util/data.txt";
	
	private static String carpetaResponse = "response";
	
	private static String carpetaDao = "rest/dao";
	
	private static String carpetaService = "service";
	
	private static String carpetaController = "controller";
	
	private static String packageResponse = "package " + carpetasProyec.replace("/", ".") + carpetaResponse + ";\n\n";
	
	private static String packageDao = "package " + carpetasProyec.replace("/", ".") + carpetaDao + ";\n\n";
	
	private static String packageService = "package " + carpetasProyec.replace("/", ".") + carpetaService + ";\n\n";
	
	private static String packageController = "package " + carpetasProyec.replace("/", ".") + carpetaController + ";\n\n";
	
	
	
	private static boolean forceCreateDAOServiceController = false;
	
	private static List<Clase> clases = new ArrayList();
	
	
	public static void crearClases() {
		String[] contenido = leerArchivo(txt_data).split("\n");
		Clase clase = null;
		for (String linea : contenido) {
			if (linea.trim().replace("\t", "").replace("\n", "").isEmpty()) {
				continue;
			}
			if (linea.startsWith("\t")) {
				linea = linea.replace("\t", "").trim();
				String[] data = linea.split(" ");
				Columna col = new Columna();
				col.setTipo(data[0]);
				col.setNombre(data[1]);
				col.addPropiedad("@NonNull");
				int index = data[0].length() + data[1].length() + 2;
				if (index < linea.length()) {
				    linea = linea.substring(index);
				    col.setPropiedades(linea.split("\\|"));
				}
				clase.getColumnas().add(col);
			} else {
				clase = new Clase();
				clases.add(clase);
				String[] dataClases = linea.replace("\")", "").split("\\(\"");
				clase.setNombre(dataClases[0]);
				clase.setNombreBBDD(dataClases[1]);
			}
		}
		for (Clase c : clases) {
			String dataClass = c.toString();
			String name_file = c.getNameFile();
			String name_file_class = carpetasProyecto + carpetaResponse + "/" + name_file;
			crearArchivo(name_file_class, packageResponse + dataClass);
			String ext = ".java";
			
			String nombre_dao = c.getNombre() + "DAO";
			String nombre_interfaz = "I" + nombre_dao;
			String nombre_service = c.getNombre() + "Service";
			String nombre_controller = c.getNombre() + "Controller";
			
			String archivo_dao = carpetasProyecto + carpetaDao + "/" + nombre_dao + ext;
			String archivo_interfaz = carpetasProyecto + carpetaDao + "/" + nombre_interfaz + ext;
			String archivo_service = carpetasProyecto + carpetaService + "/" + nombre_service + ext;
			String archivo_controller = carpetasProyecto + carpetaController + "/" + nombre_controller + ext;
			
			String import_response = packageResponse.replace(";", "").replace("package ", "import ").replace("\n", "") + "." + c.getNombre().replace("/", ".") + ";\r\n";
			if (forceCreateDAOServiceController || !existeArchivo(archivo_interfaz)) {
				String dataInterfaz = "";
				dataInterfaz += packageDao.replace("/", ".") + "\r\n"
						+ "\r\n"
						+ import_response
						+ "\r\n"
						+ "public interface " + nombre_interfaz + " {\r\n"
						+ "\r\n"
						+ "	public " + c.getNombre() + " get" + c.getNombre() + "ById(Long id);\r\n"
						+ "	\r\n"
						+ "}";
				crearArchivo(archivo_interfaz, dataInterfaz);
			}
			if (forceCreateDAOServiceController || !existeArchivo(archivo_dao)) {
				String data_dao = "";
				data_dao += packageDao.replace("/", ".") + "\r\n"
						+ "\r\n"
						+ "import org.springframework.stereotype.Repository;\r\n"
						+ import_response
						+ "import jakarta.persistence.EntityManager;\r\n"
						+ "import jakarta.persistence.PersistenceContext;\r\n"
						+ "import jakarta.transaction.Transactional;\r\n"
						+ "\r\n"
						+ "@Repository\r\n"
						+ "@Transactional\r\n"
						+ "public class " + nombre_dao + " implements " + nombre_interfaz + " {\r\n"
						+ "\r\n"
						+ "	@PersistenceContext\r\n"
						+ "	public EntityManager em;\r\n"
						+ "	\r\n"
						+ "	@Override\r\n"
						+ "	public " + c.getNombre() + " get" + c.getNombre() + "ById(Long id) {\r\n"
						+ "		return em.find(" + c.getNombre() + ".class, id);\r\n"
						+ "	}"
						+ "	\r\n}";
				crearArchivo(archivo_dao, data_dao);
			}
			if (forceCreateDAOServiceController || !existeArchivo(archivo_service)) {
				String data_service = "";
				String nombre_dao_var = nombre_dao.substring(0,1).toLowerCase() + nombre_dao.substring(1);
				data_service += packageService.replace("/", ".")
						+ "\r\n"
						+ "import org.springframework.beans.factory.annotation.Autowired;\r\n"
						+ "import org.springframework.stereotype.Service;\r\n"
						+ import_response
						+ "import " + (carpetasProyec + carpetaDao).replace("/", ".") + "." + nombre_dao + ";\r\n"
						+ "import jakarta.transaction.Transactional;\r\n"
						+ "\r\n"
						+ "@Service\r\n"
						+ "@Transactional\r\n"
						+ "public class " + nombre_service + " {\r\n"
						+ "\r\n"
						+ "    @Autowired\r\n"
						+ "    private " + nombre_dao + " " + nombre_dao_var + ";\r\n"
						+ "\r\n"
						+ "    public " + c.getNombre() + " get" + c.getNombre() + "ById(Long id) {\r\n"
						+ "        return " + nombre_dao_var + ".get" + c.getNombre() + "ById(id);\r\n"
						+ "    }\n\n}";
				crearArchivo(archivo_service, data_service);
			}
			if (forceCreateDAOServiceController || !existeArchivo(archivo_controller)) {
				String data_controller = "";
				String name_var = c.getNombre().toLowerCase().charAt(0) + "";
				String nombre_service_var = (nombre_service.charAt(0) + "").toLowerCase() + nombre_service.substring(1);
				data_controller += packageController.replace("/", ".") + "\r\n"
						+ "\r\n"
						+ "import org.springframework.beans.factory.annotation.Autowired;\r\n"
						+ "import org.springframework.http.HttpStatus;\r\n"
						+ "import org.springframework.http.ResponseEntity;\r\n"
						+ "import org.springframework.web.bind.annotation.GetMapping;\r\n"
						+ "import org.springframework.web.bind.annotation.PathVariable;\r\n"
						+ "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
						+ "import org.springframework.web.bind.annotation.RestController;\r\n"
						+ "\r\n"
						+ import_response
						+ "import " + carpetasProyec.replace("/", ".") + carpetaService + "." + nombre_service + ";\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "@RestController\r\n"
						+ "@RequestMapping(\"/" + c.getNombreBBDD() + "\")\r\n"
						+ "public class " + nombre_controller + " {\r\n"
						+ "    \r\n"
						+ "	@Autowired\r\n"
						+ "    private " + nombre_service + " " + nombre_service_var + ";\r\n"
						+ "    \r\n"
						+ "    @GetMapping(\"/{id}\")\r\n"
						+ "    public ResponseEntity<" + c.getNombre() + "> get" + c.getNombre() + "ById(@PathVariable Long id) {\r\n"
						+ "        " + c.getNombre() + " " + name_var + " = " + nombre_service_var + ".get" + c.getNombre() + "ById(id);\r\n"
						+ "        if (" + name_var + " != null) {\r\n"
						+ "            return new ResponseEntity<>(" + name_var + ", HttpStatus.OK);\r\n"
						+ "        } else {\r\n"
						+ "            return new ResponseEntity<>(HttpStatus.NOT_FOUND);\r\n"
						+ "        }\r\n"
						+ "    }\r\n"
						+ "    \r\n"
						+ "    \r\n"
						+ "}";
				crearArchivo(archivo_controller, data_controller);
			}
		}
		
	}
	
	public static void crearArchivo(String ruta, String contenido) {
		try {
		    FileWriter myWriter = new FileWriter(ruta);
		    myWriter.write(contenido);
		    myWriter.close();
	    } 
	    catch (Exception e) {
	    	
	    }
	}
	
	public static boolean existeArchivo(String ruta) {
	    return new File(ruta).exists();
	}
	
	public static String leerArchivo(String ruta) {
		try {
		    StringBuilder sb = new StringBuilder();
		    BufferedReader br = new BufferedReader(new FileReader(ruta));
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        sb.append(linea);
		        sb.append(System.lineSeparator());
		    }
		    br.close();
		    return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
}
