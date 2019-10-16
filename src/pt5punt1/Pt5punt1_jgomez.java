package pt5punt1;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Pt5punt1_jgomez {

	public static void main(String[] args) {
		crearDat();
		crearXML();
		llegirXML();
	}

	public static void crearDat() {

		FileOutputStream fos;
		ObjectOutputStream oos;
		Persona p;

		try {

			fos = new FileOutputStream(new File("myPeople.dat")); // Declaracio de la utilitat de les eines
			oos = new ObjectOutputStream(fos);

			p = new Persona("Maria", "Lopez", 36); // Declarem a les persones i les nem afegint al fitxer .dat
			oos.writeObject(p);

			p = new Persona("Gustavo", "Gomez", 1);
			oos.writeObject(p);

			p = new Persona("Irene", "Salas", 36);
			oos.writeObject(p);

			p = new Persona("Roberto", "Morgade", 63);
			oos.writeObject(p);

			p = new Persona("Graciela", "Iglesias", 60);
			oos.writeObject(p);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("------ FileNotFoundException ------ crearDat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("------ IOException ------ crearDat");
		}

	}

	public static void crearXML() {

		ObjectInputStream ois;	//declaracio del ObjectInputStream

		try {
			ois = new ObjectInputStream(new FileInputStream(new File("myPeople.dat")));

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();										//Declaracio de les eines per escriure la estructura XML
			Element elementPare = doc.createElement("Gent");
			doc.appendChild(elementPare);

			Persona p;

			try {
				while (true) {														//Bucle que surt al EOFException, afegim els elements de cada persona del .dat

					p = (Persona) ois.readObject();

					Element persona = doc.createElement("Persona");
					elementPare.appendChild(persona);

					Element nom = doc.createElement("Nom");
					nom.appendChild(doc.createTextNode(p.getNom()));
					persona.appendChild(nom);

					Element cognom = doc.createElement("Cognom");
					cognom.appendChild(doc.createTextNode(p.getCognom()));
					persona.appendChild(cognom);

					Element edat = doc.createElement("Edat");
					edat.appendChild(doc.createTextNode(String.valueOf(p.getEdat())));
					persona.appendChild(edat);
				}

			} catch (EOFException e) {
				// TODO Auto-generated catch block
				System.out.println("Bloc creat");
				ois.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();					//Declaracio i utilitzacio de les eines per generar el fitxer XML
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Gent.xml"));

			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				System.out.println("------ TransformerException ------ crearXML");
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("------ ParserConfigurationException ------ crearXML");
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("------ TransformerConfigurationException ------ crearXML");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("------ FileNotFoundException ------ crearXML");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("------ IOException ------ crearXML");
			e.printStackTrace();

		}

	}

	public static void llegirXML() {
		File f = new File("Gent.xml");

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();					//Declaracio de les eines per llegir fitxer XML
			Document doc = dBuilder.parse(f);

			doc.getDocumentElement().normalize();		//eliminem possibles nodes vuits o duplicats

			NodeList nList = doc.getElementsByTagName("Persona");		//Fem una llista totes els elements Persona

			System.out.println("Nombre de persones: " + nList.getLength());			

			for (int q = 0; q < nList.getLength(); q++) {				//Bucle for per a imprimir totes les persones que tenim a la llista
				Node nNode = nList.item(q);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					System.out.println("[Nom: " + eElement.getElementsByTagName("Nom").item(0).getTextContent()
							+ " | Cognom: " + eElement.getElementsByTagName("Cognom").item(0).getTextContent()
							+ " | Edat: " + eElement.getElementsByTagName("Edat").item(0).getTextContent()+"]");
				}

			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
