import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Pt03 {
	/*---------------------------------------------------------------------------------
	 * Declaracio de les diverses eines que faré servir a lo llarg del programa, per
	 * fer proves, canviar les rutes de f1 i f2
	 ---------------------------------------------------------------------------------*/
	static Scanner sc;
	static DataInputStream dis;
	static DataOutputStream os;
	static File f1 = new File("/home/joan/eclipse-workspace/M6_UF1/becadades.dat");
	static File f2 = new File("/home/joan/eclipse-workspace/M6_UF1/becadadesBK.dat");

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		boolean power = true;

		/*---------------------------------------------------------------------------------
		 * Tipic menu amb un Switch, cada opcio crida a un Method de la propia classe
		 ---------------------------------------------------------------------------------*/

		while (power) {

			System.out.println(
					"1. Introdueir nou becari \r2. Llistar becaris existents \r3. Fer backup al fitxer \r4. Mostrar contingut del fitxer backup \r5. Sortir");

			int i = sc.nextInt();

			switch (i) {
			case 1:
				questionari();
				break;
			case 2:
				llistarBecaris(f1);
				break;
			case 3:
				ferBK();
				break;
			case 4:
				llistarBecaris(f2);
				break;
			case 5:
				System.out.println("C ya! :)");
				power = false;
				break;
			default:
				System.out.println("Introdueix una de les opcions!");
				break;
			}

		}

		// Tancament del Scanner, DataOutputStream i DataInputStream

		sc.close();
		dis.close();
		os.close();
	}

	/*---------------------------------------------------------------------------------
	 * METODES D'ESCRIPTURA / LECTURA
	 ---------------------------------------------------------------------------------*/

	public static void questionari() throws IOException {

		/*---------------------------------------------------------------------------------
		 * Declaracio del scanner i DataOutputStream per a escriure el fitxer binari sense sobreescriure el text anterior (metodes de validacio explicats mes abaix<)
		 ---------------------------------------------------------------------------------*/

		os = new DataOutputStream(new FileOutputStream(f1, true));
		sc = new Scanner(System.in);

		System.out.println("Introdueix el nom teu nom i cognom de becari:");
		os.writeUTF(validarString());

		System.out.println("Introdueix el teu sexe [H|D]:");
		os.writeChar(validarSexe());

		System.out.println("Introdueix la teva edat:");
		os.writeInt(validaInt());

		System.out.println("Introdueix el numero de suspensos al curs anterior:");
		os.writeInt(validaInt());

		System.out.println("Vius en residencia familiar? [S|n]");
		os.writeChar(validarResidencia());

		System.out.println("Introdueix els ingressos anuals familiars:");
		os.writeInt(validaInt());

	}

	public static void llistarBecaris(File f) throws IOException {

		/*---------------------------------------------------------------------------------
		 * Metode que a partir d'un while infinit i jugant amb la excepcio EndOfFile, imprimeix tot el contingut del fitxer amb el determinat format.
		 * L'argument del Method es per a escollir si llistar l'arxiu principal o el backup (es pot veure facilment al switch)
		 ---------------------------------------------------------------------------------*/

		dis = new DataInputStream(new FileInputStream(f));

		while (true) {
			try {
				System.out.println("Nom: " + dis.readUTF() + " - Sexe: " + dis.readChar() + " - Edat: " + dis.readInt()
						+ " - Suspeses: " + dis.readInt() + " - Viu amb familia: " + dis.readChar()
						+ " - Salari familiar anual: " + dis.readInt());
			} catch (EOFException e) {
				System.out.println("-------------------------------");
				return;
			}
		}

	}

	public static void ferBK() {

		/*---------------------------------------------------------------------------------
		 * Metode que realitza un backup amb el mateix sistema de while amb excepcio d'abans. 
		 * El metode escriu a l'hora que llegeix, pel que no emmagatzema variables addicionals
		 ---------------------------------------------------------------------------------*/

		try {
			dis = new DataInputStream(new FileInputStream(f1));
			os = new DataOutputStream(new FileOutputStream(f2));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			try {
				os.writeUTF(dis.readUTF());
				os.writeChar(dis.readChar());
				os.writeInt(dis.readInt());
				os.writeInt(dis.readInt());
				os.writeChar(dis.readChar());
				os.writeInt(dis.readInt());
			} catch (IOException e) {
				return;
			}
		}

	}

	/*---------------------------------------------------------------------------------
	 * VALIDACIO DE DADES
	 * Tots els metodes de validacio de dades funcionen igual, un metode que retorna el tipus de dada dessitjada, i en un while true, asseguta que es  el tipus i/o valor dessitjat
	 ---------------------------------------------------------------------------------*/

	public static String validarString() {
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

	public static char validarSexe() {
		sc = new Scanner(System.in);
		while (true) {
			char aux = sc.next().toLowerCase().charAt(0);
			if (aux == 'h')
				return 'H';
			if (aux == 'd')
				return 'D';
			System.out.println("Introdueix una opcio valida!");
		}
	}

	public static char validarResidencia() {
		sc = new Scanner(System.in);
		while (true) {
			char aux = sc.next().toLowerCase().charAt(0);
			if (aux == 's')
				return 'S';
			if (aux == 'n')
				return 'N';
			System.out.println("Introdueix una opcio valida!");
		}
	}

	public static int validaInt() {
		while (true) { // simple validacio de int
			if (sc.hasNextInt()) {
				return sc.nextInt();
			} else {
				System.out.println("Introdueix un valor valid!");
				sc.next();
			}
		}
	}
}
