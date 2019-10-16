package random;
import java.util.*;

public class Agenda2 {
	
	private TreeMap llista = new TreeMap();

	public static void main(String[] args) {
		Agenda2 a2=new Agenda2(); 
		
		a2.llista.put("Metge", "(+52)-4000-5000"); 
		a2.llista.put("Casa", "(888)-4500-3400"); 
		a2.llista.put("Germa", "(575)-2042-3233"); 
		a2.llista.put("Oncle", "(421)-1010-0020"); 

		mostrarAgenda(a2.llista); 
		
		System.out.println("--------------------");
		
		a2.llista.put("this is a test", 123456789);
		
		mostrarAgenda(a2.llista);

//		SortedMap AO=a2.llista.subMap("A","O"); 
//		SortedMap PZ=a2.llista.tailMap("P"); 
//
//		System.out.println("---- Agenda A-O ----"); 
//		mostrarAgenda(AO); 
//
//		System.out.println("---- Agenda P-Z ----"); 
//		mostrarAgenda(PZ); 
		

	}
	
	
	public static void mostrarAgenda(Map m) {
		System.out.println(" Agenda amb " + m.size() + " telefons");
		for (Iterator i = m.keySet().iterator(); i.hasNext();) {
			String k = (String) i.next();
			String v = String.valueOf(m.get(k));
			System.out.println(k + " : " + v);
		}
	}

}
