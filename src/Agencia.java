import java.util.ArrayList;

public class Agencia {
    private int id;
    private String name;

    public Agencia(int id) {
        this.id = id;
    }

    public void checkDuplicates(ArrayList<Agencia> agencias) {
        if (agencias.isEmpty()) {
            agencias.add(this);
            return;
        }

        for (Agencia agencia : agencias) {
            if (agencia.getId() == this.id) {
                return;
            }
        }
        agencias.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
