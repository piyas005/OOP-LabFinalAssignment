package ooplabfinal;

public class GeneralPractitioner extends Doctor {

    public GeneralPractitioner(String name) {
        super(name, "General Practitioner");
    }

    @Override
    public void displayAvailability() {
        System.out.println("General Practitioner " + getName() + " Will be able to meet You from 9 AM to 5 PM.");
    }
}

