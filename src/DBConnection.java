import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    String url = "jdbc:mysql://localhost:3306/reserves";
    String usuario = "root";
    String contraseña = "root";


    Connection conn = null;
    PreparedStatement pstmt = null;

    public void connect() {
        try {
            conn = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findBookingByID(int id){
        connect();
        String query = "SELECT * FROM reserva WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("-----RESERVA-----");
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Precio: " + rs.getDouble("precio"));
                System.out.println("Check in: " + rs.getDate("check_in"));
                System.out.println("Noches: " + rs.getInt("noches"));
                System.out.println("-CLIENTE-");
                findClientByID(rs.getInt("id_cliente"));
                System.out.println("-AGENCIA-");
                findAgencyByID(rs.getInt("id_agencia"));
                System.out.println("-HOTEL-");
                findHotelByID(rs.getInt("id_hotel"));
                System.out.println("-HABITACION-");
                findRoomByID(rs.getInt("id_habitacion"));
            }else{
                System.out.println("No se ha encontrado ninguna reserva con ese id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void findClientByID(int id){
        connect();
        String query = "SELECT * FROM cliente WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
            }else{
                System.out.println("No se ha encontrado ningun cliente con ese id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void findAgencyByID(int id){
        connect();
        String query = "SELECT * FROM agencia WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
            }else{
                System.out.println("No se ha encontrado ninguna agencia con ese id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void findHotelByID(int id){
        connect();
        String query = "SELECT * FROM hotel WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
            }else{
                System.out.println("No se ha encontrado ningun hotel con ese id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void findRoomByID(int id){
        connect();
        String query = "SELECT * FROM habitacion WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Tipo: " + rs.getString("tipo"));
            }else{
                System.out.println("No se ha encontrado ninguna habitacion con ese id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void deleteData(){
        connect();
        String[] queries = {
                "DELETE FROM reserva",
                "DELETE FROM cliente",
                "DELETE FROM agencia",
                "DELETE FROM hotel",
                "DELETE FROM habitacion"
        };
        try {
            conn.setAutoCommit(false);
            for (String query : queries) {
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.executeUpdate();
                }
            }
            conn.commit(); // Confirmar la transacción
            System.out.println("Datos eliminados correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void insertClients(ArrayList<Cliente> clientes){
        connect();
        String query = "INSERT INTO cliente(id, nombre) VALUES(?,?)";
        try {
            pstmt = conn.prepareStatement(query);
            for (Cliente cliente : clientes) {
                pstmt.setInt(1, cliente.getId());
                pstmt.setString(2, cliente.getName());
                pstmt.executeUpdate();
            }
            System.out.println("Clientes insertados correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public void insertAgencies(ArrayList<Agencia> agencias){
        connect();
        String query = "INSERT INTO agencia(id, nombre) VALUES(?,?)";
        try {
            pstmt = conn.prepareStatement(query);
            for (Agencia agencia : agencias) {
                pstmt.setInt(1, agencia.getId());
                pstmt.setString(2, agencia.getName());
                pstmt.executeUpdate();
            }
            System.out.println("Agencias insertadas correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public void insertHotels(ArrayList<Hotel> hoteles){
        connect();
        String query = "INSERT INTO hotel(id, nombre) VALUES(?,?)";
        try {
            pstmt = conn.prepareStatement(query);
            for (Hotel hotel : hoteles) {
                pstmt.setInt(1, hotel.getId());
                pstmt.setString(2, hotel.getName());
                pstmt.executeUpdate();
            }
            System.out.println("Hoteles insertados correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public void insertRooms(ArrayList<Habitacion> habitaciones){
        connect();
        String query = "INSERT INTO habitacion(id, tipo) VALUES(?,?)";
        try {
            pstmt = conn.prepareStatement(query);
            for (Habitacion habitacion : habitaciones) {
                pstmt.setInt(1, habitacion.getId());
                pstmt.setString(2, habitacion.getTipo());
                pstmt.executeUpdate();
            }
            System.out.println("Habitaciones insertadas correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public void insertBookings(ArrayList<Reserva> reservas){
        connect();
        String query = "INSERT INTO reserva(id, precio, check_in, noches, id_cliente, id_agencia, id_hotel, id_habitacion) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(query);
            for (Reserva reserva : reservas) {
                pstmt.setInt(1, reserva.getId());
                pstmt.setDouble(2, reserva.getPrecio());
                pstmt.setDate(3, reserva.getCheck_in());
                pstmt.setInt(4, reserva.getNoches());
                pstmt.setInt(5, reserva.getCliente().getId());
                pstmt.setInt(6, reserva.getAgencia().getId());
                pstmt.setInt(7, reserva.getHotel().getId());
                pstmt.setInt(8, reserva.getHabitacion().getId());
                pstmt.executeUpdate();
            }
            System.out.println("Reservas insertadas correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeConnection();
        }

    }

    private void closeConnection(){
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
