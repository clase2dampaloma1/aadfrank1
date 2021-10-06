import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Fichero2 {

	private boolean seAnnade = true;
	private String ruta;
	private String nombre;

	private File fichero;
	private FileReader fr = null;
	private FileWriter fw = null;
	private BufferedWriter bw = null;
	private BufferedReader br = null;
	private ExcepcionContenidoVacio eNoHayContenido;

	/**
	 * @param ruta
	 */
	public Fichero2(String ruta) {
		super();
		this.ruta = ruta;
		this.fichero = new File(ruta);
		this.nombre = ruta.split("//")[ruta.split("//").length - 1];
		eNoHayContenido = new ExcepcionContenidoVacio(this.nombre);
		// borrar contenido anterior
		borrarContenidoFichero();
	}

	/**
	 * Borra el fichero si existe
	 */

	public void borrarFichero() {
		if (fichero.exists()) {
			fichero.delete();
		}
	}

	/**
	 * Crea el fichero si no existe
	 */

	public void crearFichero() {
		if (!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch (IOException e) {
				System.out.println("Error al crear nuevo fichero: " + this.nombre + "  " + e.getMessage());
			}
		}
	}

	/**
	 * Si existe el fichero
	 * 
	 * @return
	 */

	public boolean existe() {
		return fichero.exists();
	}

	/**
	 * Borra contenido fichero texto plano
	 */

	public void borrarContenidoFichero() {
		BufferedWriter bwBorrar = null;
		try {
			bwBorrar = new BufferedWriter(new FileWriter(ruta, false));
			bwBorrar.write("");
		} catch (IOException e) {
			System.out.println("Error interno al borrar el fichero: " + e.getMessage());
		} finally {
			try {
				if (bwBorrar != null) {
					bwBorrar.close();
				}
			} catch (IOException e2) {
				System.out.println("Error interno al borrar el fichero: " + e2.getMessage());
			}
		}
	}

	/**
	 * Lo necesario para escribir
	 */

	private void activarEscritura() {
		try {
			fw = new FileWriter(this.ruta, seAnnade);
			bw = new BufferedWriter(fw);
		} catch (IOException ioe) {
			System.out.println("Error al activar escritura " + ioe.getMessage());
		}

	}

	/**
	 * Lo necesario para leer
	 */

	private void activarLectura() {
		try {
			fr = new FileReader(this.ruta);
			br = new BufferedReader(fr);
		} catch (IOException ioe) {
			System.out.println("Error al activar lectura " + ioe.getMessage());
		}

	}

	/**
	 * Cierra la escritura, necesario para guardar cambios
	 */

	private void cerrarEscritura() {
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar escritura " + e.getMessage());
		}
	}

	/**
	 * Cierra la lectura
	 */

	private void cerrarLectura() {
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar lectura " + e.getMessage());
		}
	}

	/**
	 * añade nueva linea
	 * 
	 * @param linea
	 */
	public void nuevaLinea(String linea) {
		this.activarEscritura();
		try {
			bw.write(linea);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
		this.cerrarEscritura();
	}

	/**
	 * Lee todo el contenido del fichero
	 * 
	 * @return
	 */

	public String[] leer() {
		String linea = "";
		String contenido[];
		ArrayList<String> arrListContenido = new ArrayList<String>();
		activarLectura();
		try {
			while ((linea = br.readLine()) != null) {
				arrListContenido.add(linea);
			}
		} catch (IOException ioe) {
			System.out.println("Error al leer linea: " + ioe.getMessage());
		}
		cerrarLectura();
		// conversion de ArrayList a array, mejor practica devolver array
		contenido = new String[arrListContenido.size()];
		try {
			if (contenido.length == 0) {
				throw eNoHayContenido;
			}
		} catch (ExcepcionContenidoVacio ecv1) {
			System.out.println(ecv1.getMessage());
		}
		for (int j = 0; j < contenido.length; j++) {
			contenido[j] = arrListContenido.get(j);
		}
		return contenido;
	}

	/**
	 * Lee a partir de una linea hasta el final
	 * 
	 * @param inicio
	 * @return
	 */

	public String[] leer(int inicio) {
		String linea = "";
		int i = 1;
		String contenido[];
		ArrayList<String> arrListContenido = new ArrayList<String>();
		activarLectura();
		try {
			while ((linea = br.readLine()) != null) {
				if (i >= inicio) {
					arrListContenido.add(linea);
				}
				i++;
			}
		} catch (IOException ioe) {
			System.out.println("Error al leer linea: " + ioe.getMessage());
		}
		cerrarLectura();
		// conversion de ArrayList a array, mejor practica devolver array
		contenido = new String[arrListContenido.size()];
		try {
			if (contenido.length == 0) {
				throw eNoHayContenido;
			}
		} catch (ExcepcionContenidoVacio ecv1) {
			System.out.println(ecv1.getMessage());
		}
		for (int j = 0; j < contenido.length; j++) {
			contenido[j] = arrListContenido.get(j);
		}
		return contenido;
	}

	/**
	 * Lee entre dos lineas
	 * 
	 * @param inicio
	 * @param fin
	 * @return
	 */

	public String[] leer(int inicio, int fin) {
		String linea = "";
		int i = 1;
		String contenido[];
		ArrayList<String> arrListContenido = new ArrayList<String>();
		activarLectura();
		try {
			while ((linea = br.readLine()) != null) {
				if (i >= inicio && i <= fin) {
					arrListContenido.add(linea);
				}
				i++;
			}
		} catch (IOException ioe) {
			System.out.println("Error al leer linea: " + ioe.getMessage());
		}
		// conversion de ArrayList a array, mejor practica devolver array
		contenido = new String[arrListContenido.size()];
		cerrarLectura();
		for (int j = 0; j < contenido.length; j++) {
			contenido[j] = arrListContenido.get(j);
		}
		try {
			if (contenido.length == 0) {
				throw eNoHayContenido;
			}
		} catch (ExcepcionContenidoVacio ecv1) {
			System.out.println(ecv1.getMessage());
		}
		return contenido;
	}

	/**
	 * Busca primera ocurrencia
	 * 
	 * @param patron
	 * @return
	 * @throws ExcepcionContenidoVacio
	 */

	public int buscaPrimerElemento(String patron) {
		int i = 1, numeroDeLinea = 1;
		boolean encontrado = false;
		String[] todasLasLineasDelFichero;
		todasLasLineasDelFichero = this.leer();
		while (!(encontrado = (todasLasLineasDelFichero[i - 1].contains(patron))) && i< todasLasLineasDelFichero.length) {
			i++;
		}
		if (encontrado) {
			numeroDeLinea = i;
		} else {
			numeroDeLinea = -1;
			try {
				throw eNoHayContenido;
			} catch (ExcepcionContenidoVacio e) {
				System.out.println(e.getMessage());
			}

		}
		return numeroDeLinea;
	}

	/**
	 * Busca ultima ocurrencia
	 * 
	 * @param patron
	 * @return
	 */

	public int buscaUltimoElemento(String patron) {
		int i = 0, numeroDeLinea = 0;
		boolean encontrado = false;
		for (String linea : this.leer()) {
			i++;
			if (linea.contains(patron)) {
				numeroDeLinea = i;
				encontrado = true;
			}
		}
		if (!encontrado) {
			numeroDeLinea = -1;
		}
		return numeroDeLinea;
	}

	/**
	 * Devuelve numero de ocurrencias
	 * 
	 * @param patron
	 * @return
	 */

	public int numeroDeOcurrencias(String patron) {
		int numeroDeOcurrencias = 0;
		for (String linea : this.leer()) {
			if (linea.contains(patron)) {
				numeroDeOcurrencias++;
			}
		}
		return numeroDeOcurrencias;
	}

	/**
	 * Borra el numero de linea indicado sobre el archivo inplace
	 * 
	 * @param numeroDeLineaBorrar
	 */

	public void borrarLinea(int numeroDeLineaBorrar) {
		String parteDeArriba[], parteDeAbajo[];
		// asignar lo que no es la linea a borrar
		parteDeArriba = this.leer(1, numeroDeLineaBorrar - 1);
		parteDeAbajo = this.leer(numeroDeLineaBorrar + 1);
		// borrar todo
		this.borrarContenidoFichero();
		// escribir todo de nuevo menos la linea
		for (String linea : parteDeArriba) {
			this.nuevaLinea(linea);
		}
		for (String linea : parteDeAbajo) {
			this.nuevaLinea(linea);
		}
	}

	public void borrarLineasQueContienen(String partonBorrar) {
		int numeroDeOcurrencias = this.numeroDeOcurrencias(partonBorrar);
		for (int i = 0; i < numeroDeOcurrencias; i++) {
			this.borrarLinea(this.buscaUltimoElemento(partonBorrar));
		}
	}

	/**
	 * Devuelve el nombre del fichero sin ruta
	 * 
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Resumen del fichero
	 */
	@Override
	public String toString() {
		return "Fichero: " + this.nombre + "\nRuta: " + this.ruta;
	}

}
