import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;

    public Hotel(int id) {
        this.id = id;
    }

    public void checkDuplicates(ArrayList<Hotel> hoteles) {
        if (hoteles.isEmpty()) {
            hoteles.add(this);
            return;
        }

        for (Hotel hotel : hoteles) {
            if (hotel.getId() == this.id) {
                return;
            }
        }
        hoteles.add(this);
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
