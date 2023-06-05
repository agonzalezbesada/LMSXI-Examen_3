public class Empleado {

    private String firstName;

    private String lastName;

    Empleado() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void datos() {
        System.out.println("Empleado{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}');
    }
}