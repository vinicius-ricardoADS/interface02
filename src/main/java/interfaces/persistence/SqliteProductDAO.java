package interfaces.persistence;

import interfaces.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteProductDAO implements ProductDAO{

    @Override
    public void salvar(Product product) {
        boolean exist = false;
        String sql = "SELECT * FROM product WHERE code = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getCode());
            ResultSet rs = stmt.executeQuery(); // executa o comando SQL e armazena no ResultSet
            while (rs.next()) {
                exist = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (exist) {
            String sqlUpdate = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE code = ?";
            try (Connection connUpdate = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
                 PreparedStatement stmtUpdate = connUpdate.prepareStatement(sqlUpdate)) {
                stmtUpdate.setString(1, product.getName());
                stmtUpdate.setDouble(2, product.getPrice());
                stmtUpdate.setInt(3, product.getQuanty());
                stmtUpdate.setString(4, product.getCode());
                stmtUpdate.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            PreparedStatement stmt = null;
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
                // cria um preparedStatement com o comando SQL
                String sqlInsert = "INSERT INTO product (code, name, price, quantity) values (?,?,?,?)";
                stmt = conn.prepareStatement(sqlInsert);
                // preenche os valores para (?,?, ..., ?)
                stmt.setString(1, product.getCode());
                stmt.setString(2, product.getName());
                stmt.setDouble(3, product.getPrice());
                stmt.setInt(4, product.getQuanty());
                // executa o comando SQL
                stmt.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            } finally { // garante o fechamento do PreparedStatement e da connection
                if(stmt != null) {
                    try {stmt.close();}
                    catch (SQLException e) { e.printStackTrace();}
                }
                if(conn != null) {
                    try {conn.close();}
                    catch (SQLException e) { e.printStackTrace();}
                }
            }
        }

    }

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM product WHERE code = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        String sql = "DELETE FROM product";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findOnde(String code) {
        Product p = null;
        String sql = "SELECT * FROM product WHERE code = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery(); // executa o comando SQL e armazena no ResultSet
            if (rs.next()) {
                p = new Product(rs.getString("code"), rs.getString("name"),
                        rs.getDouble("price"), rs.getInt("quantity"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(); // executa o comando SQL e armazena no ResultSet
            while (rs.next()) {
                List<Product> data = new ArrayList<>();
                data.add(new Product(rs.getString("code"), rs.getString("name"),
                        rs.getDouble("price"), rs.getInt("quantity")));
                products.addAll(data);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
