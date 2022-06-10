package interfaces.persistence;

import interfaces.model.Product;

import java.util.List;

public interface ProductDAO {
    void salvar(Product product);
    void delete(String code);
    void deleteAll();
    Product findOnde(String code);
    List<Product> findAll();
}
