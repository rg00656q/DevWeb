/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ParserDOM{
    Document doc;
    Information inf;

    public ParserDOM(Information inf){
        this.inf=inf;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder;
        try{
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
        }catch (Exception e) {
			e.printStackTrace();
		}
    }    

    public void intoXML(){
		Element information = doc.createElement("information");
		doc.appendChild(information);
		
		Element idUsr = doc.createElement("idUsr");
		idUsr.setTextContent(String.valueOf(inf.getIdUsr()));
		
		Element txt = doc.createElement("txt");
		txt.setTextContent(inf.getTxt());
		
		Element idDoc = doc.createElement("idDoc");
		idDoc.setTextContent(String.valueOf(inf.getIdDoc()));
	}
    
}