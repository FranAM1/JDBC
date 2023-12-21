import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static XMLReader xmlReader = new XMLReader("bookings.xml");
    public static void main(String[] args) {
        boolean exit = false;
        String opcion = "";

        do{
            do {
                menu();
                opcion = sc.nextLine();
            }while(!checkNumber(opcion));

            switch (opcion) {
                case "1":
                    xmlReader.readLoadXML();
                    break;
                case "2":
                    xmlReader.deleteDB();
                    break;
                case "3":
                    consultarReservaPorID();
                    break;
                case "4":
                    consultarReservasPorAgencia();
                    break;
                case "5":
                    insertarNuevaReserva();
                    break;
                case "6":
                    borrarReservaPorID();
                    break;
                case "7":
                    actualizarReserva();
                    break;
                case "8":
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        }while(!exit);


    }

    public static void menu() {
        System.out.println("1. Insertar los datos a la base de datos");
        System.out.println("2. Suprimir todos los datos de las reservas");
        System.out.println("3. Consultar datos de una reserva");
        System.out.println("4. Consulta de reservas por agencia");
        System.out.println("5. Insertar una reserva");
        System.out.println("6. Eliminar una reserva");
        System.out.println("7. Modificar datos de una reserva");
        System.out.println("8. Salir");
        System.out.println("Elige una opcion: ");
    }

    public static void consultarReservaPorID() {
        System.out.println("Introduce el id de la reserva: ");
        String id = sc.nextLine();
        if (checkNumber(id)) {
            xmlReader.findBooking(Integer.parseInt(id));
        }
    }

    public static void insertarNuevaReserva() {
        // La creo con id 0 pero da igual porque se autoincrementa en la base de datos, por lo que no usaré ese valor.
        Reserva reserva = new Reserva(0);
        String precio = "";
        do {
            System.out.println("Introduce el precio: ");
            precio = sc.nextLine();
        } while (!checkPrice(precio));
        reserva.setPrecio(Double.parseDouble(precio));

        String check_in = "";
        do {
            System.out.println("Introduce la fecha de entrada (yyyy-mm-dd): ");
            check_in = sc.nextLine();
        } while (!checkDate(check_in));
        reserva.setCheck_in(Date.valueOf(check_in));

        String noches = "";
        do {
            System.out.println("Introduce el numero de noches: ");
            noches = sc.nextLine();
        } while (!checkNumber(noches));
        reserva.setNoches(Integer.parseInt(noches));

        String idCliente = "";
        do {
            System.out.println("Introduce el id del cliente: ");
            idCliente = sc.nextLine();
        } while (!checkNumber(idCliente));
        Cliente cliente = new Cliente(Integer.parseInt(idCliente));

        String idAgencia = "";
        do {
            System.out.println("Introduce el id de la agencia: ");
            idAgencia = sc.nextLine();
        } while (!checkNumber(idAgencia));
        Agencia agencia = new Agencia(Integer.parseInt(idAgencia));

        String idHabitacion = "";
        do {
            System.out.println("Introduce el id de la habitacion: ");
            idHabitacion = sc.nextLine();
        } while (!checkNumber(idHabitacion));
        Habitacion habitacion = new Habitacion(Integer.parseInt(idHabitacion));

        String idHotel = "";
        do {
            System.out.println("Introduce el id del hotel: ");
            idHotel = sc.nextLine();
        } while (!checkNumber(idHotel));
        Hotel hotel = new Hotel(Integer.parseInt(idHotel));

        reserva.setCliente(cliente);
        reserva.setAgencia(agencia);
        reserva.setHabitacion(habitacion);
        reserva.setHotel(hotel);

        xmlReader.insertBooking(reserva);
    }

    public static void borrarReservaPorID() {
        System.out.println("Introduce el id de la reserva que quieres borrar: ");
        String id = sc.nextLine();
        if (checkNumber(id)) {
            boolean exists = xmlReader.findBooking(Integer.parseInt(id));
            if(exists){
                xmlReader.deleteBooking(Integer.parseInt(id));
            }else{
                System.out.println("No existe ninguna reserva con ese id");
            }
        }
    }

    public static void consultarReservasPorAgencia() {
        System.out.println("Introduce el identificador de la agencia: ");
        String id = sc.nextLine();
        if (checkNumber(id)) {
            xmlReader.findBookingsByAgency(Integer.parseInt(id));
        }
    }

    public static void actualizarReserva(){
        System.out.println("Introduce el id de la reserva que quieres modificar: ");
        String id = sc.nextLine();
        if (checkNumber(id)) {
            boolean exists = xmlReader.findBooking(Integer.parseInt(id));
            if(exists){
                Reserva reserva = new Reserva(Integer.parseInt(id));

                String precio = "";
                do {
                    System.out.println("Introduce el nuevo precio: ");
                    precio = sc.nextLine();
                } while (!checkPrice(precio));
                reserva.setPrecio(Double.parseDouble(precio));

                String check_in = "";
                do {
                    System.out.println("Introduce la nueva fecha de entrada (yyyy-mm-dd): ");
                    check_in = sc.nextLine();
                } while (!checkDate(check_in));
                reserva.setCheck_in(Date.valueOf(check_in));

                String noches = "";
                do {
                    System.out.println("Introduce el nuevo numero de noches: ");
                    noches = sc.nextLine();
                } while (!checkNumber(noches));
                reserva.setNoches(Integer.parseInt(noches));

                String idCliente = "";
                do {
                    System.out.println("Introduce el nuevo id del cliente: ");
                    idCliente = sc.nextLine();
                } while (!checkNumber(idCliente));
                Cliente cliente = new Cliente(Integer.parseInt(idCliente));

                String idAgencia = "";
                do {
                    System.out.println("Introduce el nuevo id de la agencia: ");
                    idAgencia = sc.nextLine();
                } while (!checkNumber(idAgencia));
                Agencia agencia = new Agencia(Integer.parseInt(idAgencia));

                String idHabitacion = "";
                do {
                    System.out.println("Introduce el nuevo id de la habitacion: ");
                    idHabitacion = sc.nextLine();
                } while (!checkNumber(idHabitacion));
                Habitacion habitacion = new Habitacion(Integer.parseInt(idHabitacion));

                String idHotel = "";
                do {
                    System.out.println("Introduce el nuevo id del hotel: ");
                    idHotel = sc.nextLine();
                } while (!checkNumber(idHotel));
                Hotel hotel = new Hotel(Integer.parseInt(idHotel));

                reserva.setCliente(cliente);
                reserva.setAgencia(agencia);
                reserva.setHabitacion(habitacion);
                reserva.setHotel(hotel);

                xmlReader.updateBooking(reserva);
            }else{
                System.out.println("No existe ninguna reserva con ese id");
            }
        }
    }

    public static boolean checkPrice(String price) {
        boolean check = false;

        if(price.matches("[0-9]+(?:\\.[0-9]+)?")){
            check = true;
        }

        return check;
    }

    public static boolean checkDate(String date) {
        try {
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha incorrecto");
            return false;
        }
    }

    public static boolean checkNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("El valor introducido no es un numero");
            return false;
        }
    }
}