import java.util.ArrayList;

public class Habitacion {
    private int id;
    private String tipo;

    public Habitacion(int id) {
        this.id = id;
    }

    public void checkDuplicates(ArrayList<Habitacion> habitaciones) {
        if (habitaciones.isEmpty()) {
            habitaciones.add(this);
            return;
        }

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == this.id) {
                return;
            }
        }
        habitaciones.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
