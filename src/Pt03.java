import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Pt03 {

	static Scanner sc;
	static DataInputStream dis;
	static DataOutputStream os;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		boolean power = true;

		while (power) {
			

			System.out.println(
					"1. Introdueir nou becari \r2. Llistar becaris existents \r3. Fer backup al fitxer \r4. Sortir");

			int i = sc.nextInt();
			
			switch (i) {
			case 1:
				questionari();
				break;
			case 2:
				llistarBecaris();
				break;
			case 3:
				ferBK();
				break;
			case 4:
				System.out.println("C ya! :)");
				power = false;
				break;
			default:
				System.out.println("Introdueix una de les opcions!");
				break;
			}

		}
		
		sc.close();
		dis.close();
		os.close();
	}

	public static void questionari() throws IOException {
		os = new DataOutputStream(new FileOutputStream("/home/joan/eclipse-workspace/M6_UF1/becadades.dat", true));
		String aux;
		int auxi;
		char auxc;
		sc = new Scanner(System.in);

		System.out.println("Introdueix el nom teu nom i cognom de becari:");
		aux = sc.nextLine();
		os.writeUTF(aux);

		System.out.println("Introdueix el teu sexe [H|D]:");
		auxc = sc.next().charAt(0);
		os.writeChar(auxc);

		System.out.println("Introdueix la teva edat:");
		auxi = sc.nextInt();
		os.writeInt(auxi);

		System.out.println("Introdueix el numero de suspensos al curs anterior:");
		auxi = sc.nextInt();
		os.writeInt(auxi);

		System.out.println("Vius en residencia familiar? [S|n]");
		auxc = sc.next().toLowerCase().charAt(0);
		if (auxc == 'n')
			os.writeBoolean(false);
		else
			os.writeBoolean(true);

		System.out.println("Introdueix els ingressos anuals familiars:");
		auxi = sc.nextInt();
		os.writeInt(auxi);

	}

	public static void llistarBecaris() throws IOException {
		dis = new DataInputStream(new FileInputStream("/home/joan/eclipse-workspace/M6_UF1/becadades.dat"));

		while (true) {
			try {
				System.out.println("Nom: "+dis.readUTF() + " - Sexe: " + dis.readChar() + " - Edat: " + dis.readInt() + " - Suspeses: "
						+ dis.readInt() + " - Viu amb familia: " + (dis.readBoolean() ? "si" : "no") + " - Salari familiar anual: " + dis.readInt());
			} catch (EOFException e) {
				System.out.println("-------------------------------");
				return;
			}
		}

	}

	public static void ferBK() throws IOException {
		Files.copy(new File("/home/joan/eclipse-workspace/M6_UF1/becadades.dat").toPath(),
				new File("/home/joan/eclipse-workspace/M6_UF1/becadadesBK.dat").toPath(),StandardCopyOption.REPLACE_EXISTING);
	}
}
