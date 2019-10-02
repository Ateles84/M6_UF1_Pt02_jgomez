import java.util.HashMap;
import java.util.*;

public class Agenda {

	private HashMap persones = new HashMap();

	public void mostrarAgenda() {
		System.out.println(" Agenda amb " + this.persones.size() + " telefons");
		for (Iterator i = this.persones.keySet().iterator(); i.hasNext();) {
			String k = (String) i.next();
			String v = String.valueOf(this.persones.get(k));
			System.out.println(k + " : " + v);
		}
	}

	public static void main(String[] args) {

		Agenda a = new Agenda();

		a.persones.put("mama", 78);
		a.persones.put("insti", 78546);
		a.persones.put("tonto", 4545);
		a.persones.put("herawer", 342);

		a.mostrarAgenda();
		
		a.persones.put("lolaso", 666);
		
		System.out.println("---------------------------");
		
		a.mostrarAgenda();
		

	}

}
