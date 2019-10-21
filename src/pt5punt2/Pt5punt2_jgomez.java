package pt5punt2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Pt5punt2_jgomez {
	public static void main(String[] args) {
		creacioXML();

	}

	static void creacioXML() {
		Scanner sc = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element elementPare = doc.createElement("Cursos");
			doc.appendChild(elementPare);

			Element curs = doc.createElement("Curs");
			Attr id = doc.createAttribute("id");
			id.setValue("AMS2");
			curs.setAttributeNode(id);
			elementPare.appendChild(curs);

			Element tutor = doc.createElement("Tutor");
			tutor.appendChild(doc.createTextNode("David Espeja"));
			curs.appendChild(tutor);

			Element alumnes = doc.createElement("Alumnes");
			curs.appendChild(alumnes);

			// Alumne

			Element moduls = doc.createElement("Moduls");
			curs.appendChild(moduls);

			Element modul = doc.createElement("Modul");
			Attr modulId = doc.createAttribute("id");
			modulId.setValue("M06");
			modul.setAttributeNode(modulId);
			moduls.appendChild(modul);

			Element profes = doc.createElement("Profes");
			modul.appendChild(profes);

			Element profe = doc.createElement("Profe");
			profe.appendChild(doc.createTextNode("Rafa Aracil"));
			profes.appendChild(profe);

			Element ufs = doc.createElement("UFs");
			moduls.appendChild(ufs);

			Element uf = doc.createElement("UF");
			Attr ufNum = doc.createAttribute("n");
			ufNum.setValue("1");
			uf.setAttributeNode(ufNum);
			uf.appendChild(doc.createTextNode("Persitencia en fitxers"));
			ufs.appendChild(uf);

			boolean lolaux = true;
			while (lolaux) {
				sc = new Scanner(System.in);

				System.out.println("QUE VOLS FER? \r1. Insertar alumne \r2. Borrar alumne \r3. Sortir");

				int aux = sc.nextInt();

				switch (aux) {
				case 1:
					System.out.println("Introdueix el nom de l'alumne: ");
					String nouAlumne = sc.next();

					Element alumne = doc.createElement("Alumne");
					alumne.appendChild(doc.createTextNode(nouAlumne));
					alumnes.appendChild(alumne);

					break;

				case 2:
					System.out.println("Introdueix el nom de l'alumne que vols eliminar: ");
					String ripAlumne = sc.next();

					NodeList nodes = alumnes.getElementsByTagName("Alumne");

					for (int q = 0; q < nodes.getLength(); q++) {
						Node nNode = nodes.item(q);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							if (eElement.getTextContent().equals(ripAlumne)) {
								alumnes.removeChild(eElement);
								System.out.println("Alumne eliminat :)");
							}
						}
					}

					break;
				case 3:
					lolaux = false;
					break;
				}

			}

			sc.close();

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Cursos.xml"));
			
			try {
				transformer.transform(source, result);
			} 
				catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException | TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("----- ParserConfigurationException -----");
			e.printStackTrace();
		}
	}

}
