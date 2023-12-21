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
                    consultarReserva();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
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

    public static void consultarReserva() {
        System.out.println("Introduce el id de la reserva: ");
        String id = sc.nextLine();
        if (checkNumber(id)) {
            xmlReader.findBooking(Integer.parseInt(id));
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