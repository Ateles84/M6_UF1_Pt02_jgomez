import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class UF1_pt1_jgomez {

	static TreeMap<String, Float> tm = new TreeMap<String, Float>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		boolean power = true;

		while (power) { // base del menu
			System.out.println(
					"Benvingut al menu de videojocs de ValliJocs! \r1. Insertar un videojoc \r2. Modificar un videojoc \r3. Borrar un videojoc \r4. Mostrar tots els jocs \r5. Sortir del menu \r6. Afegir dades actuals al fitxer \r7. Importa al TreeMap el contingut del fitxer");

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
			case 6: 
				toFitxer();
				break;
			case 7:
				fitxerToMap();
				break;
			default:
				System.out.println("Introdueix un valor del 1 al 7!");
			}
		}

	}

	public static int validarOpcio() { // comprovar que s'introdueix un int al switch
		while (true) {
			if (sc.hasNextInt()) {
				return sc.nextInt();
			} else {
				System.out.println("Introdueix una opcio valida!");
				sc.next();
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
	
	public static void toFitxer() throws FileNotFoundException {
		if (tm.isEmpty()) {
			System.out.println("La llista de jocs es buida!");
			return;
		}
		
		File f = new File("deMapToFitxer.txt");
		
		PrintStream ps = new PrintStream(f);
		Iterator<String> lol = tm.keySet().iterator();
		
		for (int i = 0 ; i < tm.size(); i++) {
			String aux = lol.next().toString();
			ps.println(aux + " " + tm.get(aux));
		}
		
		ps.close();
		
		System.out.println("Fitxer desat");
	}
	
	public static void fitxerToMap() throws FileNotFoundException {
		File f = new File("/home/joan/eclipse-workspace/M6_UF1/deMapToFitxer.txt");
		Scanner s = new Scanner(f);
		tm = new TreeMap<String, Float>();
		
		while (s.hasNext()) {
			String[] aux = s.nextLine().split(" ");
			
			
			
			tm.put(aux[0], Float.parseFloat(aux[1]));
		}
		
		llistarJocs();
		
		s.close();
	}
}
