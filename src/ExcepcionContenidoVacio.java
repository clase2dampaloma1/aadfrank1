/**
 * 
 */

/**
 * @author frank
 *
 */
public class ExcepcionContenidoVacio extends Exception {
	
	public ExcepcionContenidoVacio(String nombreFichero) {
		super("El contenido devuelto esta vacio, o las lineas indicadas no existen. Fichero: " + nombreFichero);
	}
}
