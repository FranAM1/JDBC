import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLReader extends DefaultHandler {
    private ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<Habitacion> rooms = new ArrayList<>();
    private ArrayList<Agencia> agencies = new ArrayList<>();
    private ArrayList<Reserva> bookings = new ArrayList<>();
    private ArrayList<Cliente> clients = new ArrayList<>();
    private String xmlPath;
    private String actualElement;

    public XMLReader(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public void readLoadXML() {
        File file = new File(xmlPath);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(file, this);
        } catch (ParserConfigurationException e) {
            System.out.println("Error de configuracion");
        } catch (SAXException e) {
            System.out.println("Error de parseo");
        }catch (IOException e) {
            System.out.println("Error de lectura/escritura");
        }
    }

    @Override
    public void startDocument(){
        System.out.println("Inicio de lectura del archivo XML");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        switch (qName) {
            case "hotel":
                hotels.add(new Hotel(Integer.parseInt(attributes.getValue("id_hotel"))));
                break;
            case "client":
                clients.add(new Cliente(Integer.parseInt(attributes.getValue("id_client"))));
                break;
            case "agency":
                agencies.add(new Agencia(Integer.parseInt(attributes.getValue("id_agency"))));
                break;
            case "room":
                rooms.add(new Habitacion(Integer.parseInt(attributes.getValue("id_type"))));
                break;
        }

        actualElement = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

    }

    @Override
    public void characters(char[] ch, int start, int length) {

    }

    @Override
    public void endDocument(){
        System.out.println("Lectura del archivo XML finalizada");
    }

    /*
    ejemplo
    <booking location_number="1">
        <client id_client="1">Mateo Alberti Valades</client>
        <agency id_agency="1">Expedia</agency>
        <price>252,93</price>
        <room id_type="1">Double</room>
        <hotel id_hotel="5">Sineu rooms</hotel>
        <check_in>08/06/2018</check_in>
        <room_nights>2</room_nights>
    </booking>
     */
}
