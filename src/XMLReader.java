import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;

public class XMLReader extends DefaultHandler {
    private ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<Habitacion> rooms = new ArrayList<>();
    private ArrayList<Agencia> agencies = new ArrayList<>();
    private ArrayList<Reserva> bookings = new ArrayList<>();
    private ArrayList<Cliente> clients = new ArrayList<>();

    private Cliente clienteActual;
    private Agencia agenciaActual;
    private Hotel hotelActual;
    private Habitacion habitacionActual;
    private Reserva reservaActual;

    private String xmlPath;
    private DBConnection dbConnection;
    private StringBuilder content;

    public XMLReader(String xmlPath) {
        this.xmlPath = xmlPath;
        dbConnection = new DBConnection();
    }

    public void readLoadXML() {
        File file = new File(xmlPath);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
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
                hotelActual = new Hotel(Integer.parseInt(attributes.getValue("id_hotel")));
                break;
            case "client":
                clienteActual = new Cliente(Integer.parseInt(attributes.getValue("id_client")));
                break;
            case "agency":
                agenciaActual = new Agencia(Integer.parseInt(attributes.getValue("id_agency")));
                break;
            case "room":
                habitacionActual = new Habitacion(Integer.parseInt(attributes.getValue("id_type")));
                break;
            case "booking":
                reservaActual = new Reserva(Integer.parseInt(attributes.getValue("location_number")));
                break;
        }

        content = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "hotel":
                hotelActual.setName(content.toString());
                hotelActual.checkDuplicates(hotels);
                break;
            case "client":
                clienteActual.setName(content.toString());
                clienteActual.checkDuplicates(clients);
                break;
            case "agency":
                agenciaActual.setName(content.toString());
                agenciaActual.checkDuplicates(agencies);
                break;
            case "room":
                habitacionActual.setTipo(content.toString());
                habitacionActual.checkDuplicates(rooms);
                break;
            case "price":
                reservaActual.setPrecio(parseDecimal(content.toString()));
                break;
            case "check_in":
                reservaActual.setCheck_in(parseDate(content.toString()));
                break;
            case "room_nights":
                reservaActual.setNoches(Integer.parseInt(content.toString()));
                break;
            case "booking":
                reservaActual.setCliente(clienteActual);
                reservaActual.setAgencia(agenciaActual);
                reservaActual.setHabitacion(habitacionActual);
                reservaActual.setHotel(hotelActual);
                bookings.add(reservaActual);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }

    @Override
    public void endDocument(){
        System.out.println("Lectura del archivo XML finalizada");
        dbConnection.insertClients(clients);
        dbConnection.insertAgencies(agencies);
        dbConnection.insertHotels(hotels);
        dbConnection.insertRooms(rooms);
        dbConnection.insertBookings(bookings);
    }

    private Double parseDecimal(String decimal) {
        decimal = decimal.replace(",", ".");
        return Double.parseDouble(decimal);
    }

    private Date parseDate(String date) {
        String[] dateParts = date.split("/");
        return Date.valueOf(dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0]);
    }

    public void deleteDB() {
        dbConnection.deleteData();
    }

    public void findBooking(int id) {
        dbConnection.findBookingByID(id);
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public ArrayList<Habitacion> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Habitacion> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Agencia> getAgencies() {
        return agencies;
    }

    public void setAgencies(ArrayList<Agencia> agencies) {
        this.agencies = agencies;
    }

    public ArrayList<Reserva> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Reserva> bookings) {
        this.bookings = bookings;
    }

    public ArrayList<Cliente> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Cliente> clients) {
        this.clients = clients;
    }
}
