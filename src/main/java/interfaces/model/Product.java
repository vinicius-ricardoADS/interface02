package interfaces.model;

import java.util.Objects;

public class Product {
    private String code;
    private String name;
    private double price;
    private Integer quanty;

    public Product(String code, String name, double price, Integer quanty) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quanty = quanty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuanty() {
        return quanty;
    }

    public void setQuanty(Integer quanty) {
        this.quanty = quanty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(quanty, product.quanty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quanty);
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quanty=" + quanty +
                '}';
    }
}
