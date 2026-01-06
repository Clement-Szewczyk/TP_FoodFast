package foodfast;

import java.math.BigDecimal;
import java.util.Objects;

public class Dish{
    public enum DishSize { SMALL, MEDIUM, LARGE }

    private String name;
    private BigDecimal price;
    private DishSize size;

    public Dish(String name, BigDecimal price, DishSize size){
        this.name = name;
        this.price = price;
        this.size = size;
    }
    
    // HAScode, Equalt et toString methods 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Dish dish = (Dish) obj;

        if (!name.equals(dish.name)) return false;
        if (!price.equals(dish.price)) return false;
        return size == dish.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, size);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
    
    public BigDecimal getPrice() {
        return price;
    }
        

}