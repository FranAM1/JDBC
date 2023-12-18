public class Habitacion {
    private int id;
    private String tipo;

    public Habitacion(int id, String name) {
        this.id = id;
        this.tipo = name;
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
