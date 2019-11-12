package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PizzaMapper {

    Connection con = null;

    public ArrayList<Pizza> getPizzas() throws SQLException {
        Statement stmt;
        ArrayList<Pizza> pizzaer = new ArrayList();
        Pizza pizza;

        con = DatabaseConnector.getConnection();
        stmt = con.createStatement();

        try {

            con = DatabaseConnector.getConnection();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizzaer");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                pizza = new Pizza(id, name, description, price);
                pizzaer.add(pizza);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pizzaer;
    }

    public void indsætPizza(Pizza pizza) {
        String SQL = "INSERT INTO pizzabestillinger (name, description, price) VALUES (?, ?, ?)";
        con = DatabaseConnector.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pizza.getNavn());
            ps.setString(2, pizza.getFyld());
            ps.setInt(3, pizza.getPris());
            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            pizza.setNummer(id);
        } catch (SQLException ex) {
            Logger.getLogger(PizzaMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException {
        ArrayList<Pizza> menucard = new PizzaMapper().getPizzas();
        for (Pizza pizza : menucard) {
            System.out.println(pizza);
        }
    }
}
