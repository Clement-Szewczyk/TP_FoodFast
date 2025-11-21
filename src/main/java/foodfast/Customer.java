package foodfast;

import java.util.Objects;

public class Customer {

    private String id;
    private String name;
    private String address;

    public Customer(String id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }


    // Equals, Hashcode and toString methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return id.equals(customer.id) &&
                name.equals(customer.name) &&
                address.equals(customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}