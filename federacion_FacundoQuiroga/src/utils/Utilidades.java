package utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author luis
 */
public class Utilidades {

	/**
	 * Función que pide al usuario que introduzca 's' o 'S' para Sí o 'n' o 'N' para
	 * No y devuelve true o false en cada caso. Si el usuario no introduce ni 's' ni
	 * 'S' ni 'n' ni 'N' entonces avisa al usuario y le vuelve a pedir a que lo
	 * introduzca de nuevo.
	 *
	 * @return true si el usuario introduce 's' o 'S'; false si el usuario introduce
	 *         'n' o 'N'
	 */
	public static boolean leerBoolean() {
		boolean ret;
		Scanner in;
		char resp;
		do {
			System.out.println("Pulse s para Sí o n para No");
			in = new Scanner(System.in, "ISO-8859-1");
			resp = in.nextLine().charAt(0);
			if (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
				System.out.println("Valor introducido incorrecto.");
			}
		} while (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N');
		if (resp == 's' || resp != 'S') {
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}

	/**
	 * Función que pide al usuario que introduzca un valor decimal por la entrada
	 * estándar. Si el formato introducido no es correcto, avisa al usuario y le
	 * vuelve a pedir que lo introduzca de nuevo.
	 *
	 * @return el valor double introducido por el usuario
	 */
	public static double leerDouble() {
		double ret = 0.0;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor decimal (xx.xx)");
			in = new Scanner(System.in, "ISO-8859-1");
			try {
				ret = in.nextDouble();
				correcto = true;
			} catch (InputMismatchException ime) {
				System.out.println("Formato introducido incorrecto.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que pide al usuario que introduce un valor para una fecha a partir de
	 * 3 enteros para el día, mes y año respectivamente. Si los valores introducidos
	 * no producen una fecha correcta, avisa al usuario y le pide que los introduzca
	 * de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una fecha de la clase java.time.LocalDate o null si hay error
	 */
	public static java.time.LocalDate leerFecha() {
		LocalDate ret = null;
		int dia, mes, anio;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor para el día (1...31)");
			in = new Scanner(System.in, "ISO-8859-1");
			dia = in.nextInt();
			System.out.println("Introduzca un valor para el mes (1...12)");
			in = new Scanner(System.in, "ISO-8859-1");
			mes = in.nextInt();
			System.out.println("Introduzca un valor para el año");
			in = new Scanner(System.in, "ISO-8859-1");
			anio = in.nextInt();

			try {
				ret = LocalDate.of(anio, mes, dia);
				correcto = true;
			} catch (Exception e) {
				System.out.println("Fecha introducida incorrecta.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que pide al usuario que introduce un valor para una fecha a partir de
	 * 3 enteros para el día, mes y año respectivamente Y una hora a partir de ptrps
	 * 3 valores para la hora, minutos y segundos. Si los valores introducidos no
	 * producen una fecha u hora correctas, avisa al usuario y le pide que los
	 * introduzca de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una fecha-hora de la clase java.time.LocalDateTime o null si hay
	 *         error
	 */
	public static java.time.LocalDateTime leerFechaHora() {
		LocalDateTime ret = null;
		int dia, mes, anio;
		int hora, min, seg;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor para el día (1...31)");
			in = new Scanner(System.in, "ISO-8859-1");
			dia = in.nextInt();
			System.out.println("Introduzca un valor para el mes (1...12)");
			in = new Scanner(System.in, "ISO-8859-1");
			mes = in.nextInt();
			System.out.println("Introduzca un valor para el año");
			in = new Scanner(System.in, "ISO-8859-1");
			anio = in.nextInt();
			System.out.println("Introduzca un valor para la hora del día (0...23)");
			in = new Scanner(System.in, "ISO-8859-1");
			hora = in.nextInt();
			System.out.println("Introduzca un valor para los minutos (0...59)");
			in = new Scanner(System.in, "ISO-8859-1");
			min = in.nextInt();
			System.out.println("Introduzca un valor para los segundos (0...59)");
			in = new Scanner(System.in, "ISO-8859-1");
			seg = in.nextInt();

			try {
				ret = LocalDateTime.of(anio, mes, dia, hora, min, seg);
				correcto = true;
			} catch (Exception e) {
				System.out.println("Fecha-hora introducida incorrecta.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que quita los espacios en blanco del comienzo y del final de una
	 * cadena de caracteres que se pasa como parámetro y, además, sustituye todas
	 * las vocales que tengan tilde por la correspondiente sin tilde, devolviendo la
	 * cadena resultante
	 * 
	 * @param s cadena original
	 * @return cadena original sin espacios en blanco al comienzo y final de la
	 *         cadena y sin vocales con tildes
	 */
	public static String quitarEspaciosTildes(String s) {
		String ret = s.trim();
		return ret.replace('á', 'a').replace('é', 'e').replace('í', 'i').replace('ó', 'o').replace('ú', 'u')
				.replace('Á', 'A').replace('É', 'E').replace('Í', 'I').replace('Ó', 'O').replace('Ú', 'U');
	}

	public static String removeDiacriticalMarks(String string) {
		// Form.NFC acepta ñ y distingue las tildes en español
		return Normalizer.normalize(string, Form.NFC).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	/**
	 * Lee un flotante y verifica que lo sea, pidiendo uno hasta que no de ninguna
	 * exepcion
	 * 
	 * @param flotante que el usuario desea introducir
	 * @return un Flot
	 * @throws InputMismatchException if the next token does not match the
	 *                                <i>Float</i> regular expression, or is out of
	 *                                range
	 * @throws NoSuchElementException if input is exhausted
	 * @throws IllegalStateException  if this scanner is closed
	 * @author Facu examen 5
	 */
	public static float leerFloat() {
		float ret = 0.0F;
		boolean valido = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor flotante (xx.xx)");
			in = new Scanner(System.in);
			try {
				ret = in.nextFloat();
				valido = true;
			} catch (InputMismatchException ime) {
				System.out.println("Formato introducido incorrecto.");
				valido = false;
			} catch (NoSuchElementException nse) {
				System.out.println("Formato introducido incorrecto.");
				valido = false;
			} catch (IllegalStateException ise) {
				System.out.println("Formato introducido incorrecto");
				valido = false;
			} catch (Exception e) {
				System.out.println("Formato introducido incorrecto");
				valido = false;
			}
		} while (!valido);
		return ret;

	}
}
