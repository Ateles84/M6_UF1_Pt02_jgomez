import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Pt02 {
	public static void main(String[] args) throws IOException {
		ex7();
	}

	public static void ex1() throws FileNotFoundException {
		File f = new File("/home/joan/eclipse-workspace/M6_UF1/src/Pt02.java"); // aixo es un fitxer de prova, canviar a
																				// una ruta local per a provar el metode
		Scanner sc = new Scanner(f); // Scanner per a llegir el fitxer

		while (sc.hasNext()) { // Bucle per a seguir llegint fins que no hi hagi res mes
			System.out.println(sc.next());
		}

		sc.close();
	}

	public static void ex2() throws FileNotFoundException {
		File f = new File("/home/joan/eclipse-workspace/M6_UF1/src/Pt02.java"); // aixo es un fitxer de prova, canviar a
																				// una ruta local per a provar el metode
		Scanner sc = new Scanner(f); // Scanner per a llegir el fitxer

		while (sc.hasNext()) { // Bucle per a seguir llegint fins que no hi hagi res mes
			System.out.println(sc.nextLine());
		}

		sc.close();
	}

	public static void ex3() throws IOException {
		File f = new File("MyFiles"); // Nom del directori que crearem

		if (f.exists()) { // Comprovacio de si el directori existeix o no, si no, el crea
			System.out.println("Aquest directori ja existeix");
		} else {
			f.mkdir();
		}

		File f1 = new File("/home/joan/eclipse-workspace/M6_UF1/MyFiles/Fichero1.txt"); // Declaracio primer fitxer
		if (!f1.exists())
			f1.createNewFile(); // Si el fitxer no existeix, el crea

		File f2 = new File("/home/joan/eclipse-workspace/M6_UF1/MyFiles/Fichero2.txt"); // Declaracio segon fitxer
		if (!f2.exists())
			f2.createNewFile(); // Si el fitxer no existeix, el crea

		Files.list(new File("/home/joan/eclipse-workspace/M6_UF1/MyFiles/").toPath()).forEach(ruta -> {
			System.out.println(ruta);
		}); // Llistar fitxers del directori amb funcio Lambda

		f2.renameTo(new File("/home/joan/eclipse-workspace/M6_UF1/MyFiles/ficheroRenombrado")); // Renombrar del fitxer

		Files.list(new File("/home/joan/eclipse-workspace/M6_UF1/MyFiles/").toPath()).forEach(System.out::println); // Llistar

	}

	public static void ex4() throws FileNotFoundException { // cat
		System.out.println("Introdueix la ruta d'un fitxer per a llegir :)");
		Scanner sc = new Scanner(System.in); // Escaner per agafar Input de l'usuari

		String aux = sc.nextLine();
		File f = new File(aux);

		sc = new Scanner(f);

		while (sc.hasNext()) {
			System.out.println(sc.nextLine());
		}

		sc.close();

	}

	public static void ex5() throws IOException { // Frases matrix
		PrintWriter pw = new PrintWriter(
				new FileWriter("/home/joan/eclipse-workspace/M6_UF1/MyFiles/frasesMatrix.txt", true)); // Declaracio de
																										// printWriter
		// Per a no sobreescriure el text existent, s'ha de declarar el File com
		// FileWriter i afergir-hi el bolea true, aixi agafa l'ultim index escrit i
		// comença a escriure alla
		pw.println("Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.");

		pw.close();

	}

	public static void ex6() throws FileNotFoundException { // escribeLineas
		File f = new File("/home/joan/eclipse-workspace/M6_UF1/MyFiles/escribeLineas.txt"); // Declaracio de la ruta del
																							// fitxer i lector local
		PrintStream ps = new PrintStream(f); // PrintStream per a escriure a l'arxiu

		for (int i = 1; i <= 10; i++) {
			ps.println("Linea " + i); // println per escriure una linea cada vegada que passem, si fos print normal
										// podriem posar \r
		}

		ps.close();
	}

	public static void ex7() throws IOException {
		System.out.println("Intrdueix la ruta de l'arxiu que vols copiar!");
		Scanner sc = new Scanner(System.in);

		String s1 = sc.nextLine();
		File f1 = new File(s1);

		System.out.println("Intrdueix la ruta on el vols copiar!");

		String s2 = sc.nextLine();
		File f2 = new File(s2);

		Files.copy(f1.toPath(), f2.toPath(), StandardCopyOption.REPLACE_EXISTING);	//Comanda de la classe Files per a copiar arxius

		sc.close();
	}
}
