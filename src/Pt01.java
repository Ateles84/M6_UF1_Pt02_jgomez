import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class Pt01 {

	static TreeMap<String, Float> tm = new TreeMap<String, Float>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean power = true;

		while (power) { // base del menu
			System.out.println(
					"Benvingut al menu de videojocs de ValliJocs! \r1. Insertar un videojoc \r2. Modificar un videojoc \r3. Borrar un videojoc \r4. Mostrar tots els jocs \r5. Sortir del menu");

			switch (validarOpcio()) {
			case 1:
				afegirJoc();
				break;
			case 2:
				modificarJoc();
				break;
			case 3:
				esborrarJoc();
				break;
			case 4:
				llistarJocs();
				break;
			case 5:
				System.out.println("See ya soon!");
				power = false;
				break;
			default:
				System.out.println("Introdueix un valor del 1 al 5!");
			}
		}

	}

	public static int validarOpcio() { // comprovar que s'introdueix un int al switch
		while (true) {
			if (sc.hasNextInt()) {
				return sc.nextInt();
			} else {
				System.out.println("Introdueix una opcio valida!");
			}
		}
	}

	public static void afegirJoc() { // metode per afegit jocs
		System.out.println("Introdueix el nom del Joc: ");

		String jocActual = validarString();

		System.out.println("Introdueix el preu del Joc: ");

		float preuActual = validarFloat();

		tm.put(jocActual, preuActual);

		if (tm.containsKey(jocActual))
			System.out.println("Joc introduit satisfactoriament");
	}

	public static void modificarJoc() { // metode per modeificar jocs
		if (tm.isEmpty()) {
			System.out.println("No hay juegos en la bd");
			return;
		}

		System.out.println("Introdueix el nom del joc que vols modificar: ");

		String jocActual = validarString();

		if (!tm.containsKey(jocActual)) {
			System.out.println("El joc introduit no existeix a la bd");
			return;
		}

		System.out.println("Introdueix el nou preu del joc: ");

		float preuActual = validarFloat();

		tm.put(jocActual, preuActual);

		if (tm.containsKey(jocActual))
			System.out.println("Joc modificat satisfactoriament");
	}

	public static void esborrarJoc() { // metode per esborrar jocs
		if (tm.isEmpty()) {
			System.out.println("No hay juegos en la bd");
			return;
		}

		System.out.println("Introdueix el nom del joc que vols eliminar: ");

		String jocActual = validarString();

		if (!tm.containsKey(jocActual)) {
			System.out.println("El joc introduit no existeix a la bd");
			return;
		}

		System.out.println("El joc " + jocActual + " sera eliminat, estas segur de que vols fer-ho? [S/n]");

		String aux = sc.nextLine().toLowerCase();

		if (aux.isEmpty() || aux.equals("s"))
			tm.remove(jocActual);

		else if (aux.equals("n")) {
			System.out.println("Esborrament de joc abortat");
			return;
		}

		tm.remove(jocActual);

		if (!tm.containsKey(jocActual))
			System.out.println("Joc esborrat satisfactoriament");

	}

	public static void llistarJocs() { // metode per llistar jocs
		Iterator<String> lol = tm.keySet().iterator();

		while (lol.hasNext()) {
			String aux = lol.next().toString();
			System.out.println(aux + " " + tm.get(aux) + "€");
		}
	}

	public static String validarString() { // metode d'input de String
		sc = new Scanner(System.in);
		while (true) {
			String aux = sc.nextLine();
			if (!aux.isEmpty()) {
				return aux;
			} else {
				System.out.println("No es pot introduir un nom en blanc");
			}
		}
	}

	public static Float validarFloat() { // metode validacio de input float
		while (true) { // simple validacio de float
			if (sc.hasNextFloat()) {
				return sc.nextFloat();
			} else {
				System.out.println("Introdueix un preu valid!");
				sc.next();
			}
		}
	}

}
