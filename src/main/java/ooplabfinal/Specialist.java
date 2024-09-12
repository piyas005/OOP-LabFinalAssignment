package ooplabfinal;

public class Specialist extends Doctor {

    public Specialist(String name) {
        super(name, "Specialist");
        
    }

    @Override
    public void displayAvailability() {
        System.out.println("Specialist " + getName() + " is available by appointment only.");
    }
}
