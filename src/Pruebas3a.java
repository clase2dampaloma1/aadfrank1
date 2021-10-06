
public class Pruebas3a {

	public static void main(String[] args) {
		// ruta a cambiar
		Fichero2 f2 = new Fichero2("//home//frank//Documentos//ficheros//p2.txt");
		// escribe contenido en el fichero
		for (int i = 1; i <= 15; i++) {
			f2.nuevaLinea("Linea escrita con Fichero2 numero: " + i);
		}
		
		
		for (int i = 1; i <= 15; i++) {
			f2.nuevaLinea("Linea escribida con Fichero2 numero: " + i);
		}
		// leer devuelve un array, que es iterable, se itera directamente sin asignar

		// leer todo
		for (String linea : f2.leer()) {
			System.out.println(linea);
		}

		// leer a partir
		for (String linea : f2.leer(5)) {
			System.out.println(linea);
		}

		// leer a entre 2 lineas
		for (String linea : f2.leer(7, 12)) {
			System.out.println(linea);
		}

		// borrar contenido
		// f2.borrarContenidoFichero();
		// leer a entre 2 lineas fichero vacio para saltar excepcion
		for (String linea : f2.leer(7, 12)) {
			System.out.println(linea);
		}
		System.out.println(f2);

		System.out.println("linea primera: " + f2.buscaPrimerElemento("11"));
		System.out.println("linea ultima: " + f2.buscaUltimoElemento("12"));
		System.out.println("n ocurrencias " + f2.numeroDeOcurrencias("escrita"));

		// leer todo
		for (String linea : f2.leer()) {
			System.out.println(linea);
		}
		f2.borrarLinea(2);
		f2.borrarLinea(13);
		// leer todo
		for (String linea : f2.leer()) {
			System.out.println(linea);
		}
		f2.borrarLineasQueContienen("escribida");
		for (String linea : f2.leer()) {
			System.out.println(linea);
		}
		System.out.println(f2.buscaPrimerElemento("escri"));
		
	}

}
