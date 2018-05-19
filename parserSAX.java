package projet2;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class parserSAX extends DefaultHandler{
	Information inf;
    private boolean btxt=false;
    private boolean bidusr=false;
    private boolean biddoc=false;
    
    public parserSAX(File xmlfile) {
	    try{
	    	SAXParserFactory factory = SAXParserFactory.newInstance();
	    	SAXParser builder = factory.newSAXParser();
	    	DefaultHandler handler = new DefaultHandler(){
	    		
	    		public void startElement(String namespaceURI, String localName, String qName, Attributes atts){
	    	    	if(qName.equalsIgnoreCase("information")){
	    	    		
	    	    	}
	    	    	if(qName.equalsIgnoreCase("idUsr")){
	    	            bidusr=true;
	    	        }
	    	    	if(qName.equalsIgnoreCase("txt")){
	    	            btxt = true;
	    	        }
	    	        if(qName.equalsIgnoreCase("idDoc")){
	    	            biddoc=true;
	    	        }
	    	    }
	    	    
	    	    public void endElement(String uri, String localName, String qName) throws SAXException{
	    	    	
	    	    }
	    	    
	    	    public void characters(char ch[], int start, int length) throws SAXException{
	    	    	String d = new String(ch, start, length);
	    	    	if (bidusr) {
	    	    		int idU = Integer.parseInt(d);
	    	    		inf.setIdUsr(idU);
	    	    	}
	    	    	if (btxt) {
	    	    		inf.setTxt(d);
	    	    	}
	    	    	if (biddoc) {
	    	    		int idD = Integer.parseInt(d);
	    	    		inf.setIdDoc(idD);
	    	    	}
	    	    }
	    	};
	    	builder.parse(xmlfile, handler);
	    }catch (Exception e) {
	    		e.printStackTrace();
	    }  
    }   
}