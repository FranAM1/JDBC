import java.util.ArrayList;

public class Cliente {
    private int id;
    private String name;

    public Cliente(int id) {
        this.id = id;
    }

    public void checkDuplicates(ArrayList<Cliente> clientes) {
        if (clientes.isEmpty()) {
            clientes.add(this);
            return;
        }

        for (Cliente cliente : clientes) {
            if (cliente.getId() == this.id) {
                return;
            }
        }
        clientes.add(this);
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
