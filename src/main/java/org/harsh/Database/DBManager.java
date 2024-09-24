package org.harsh.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.harsh.Model.Product;
import org.harsh.Model.User;

public class DBManager {

    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/e_ration";
    private static final String DB_USER = "root", DB_PASSWORD = "";

    private static final String USER_TABLE = "USERS";
    private static final String SELECT_ALL_FROM_USER = "SELECT * FROM " + USER_TABLE;
    private static final String PRODUCT_TABLE = "PRODUCTS";
    private static final String SELECT_ALL_FROM_PRODUCTS = "SELECT * FROM " + PRODUCT_TABLE;

    public DBManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String email) {
        String selectUserQuery = SELECT_ALL_FROM_USER + " WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectUserQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("user_id");
                String role = resultSet.getString("role");
                resultSet.close();
                return new User(userID, email, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean loginUser(String email, String password) {
        String selectQuery = SELECT_ALL_FROM_USER + " WHERE email = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerUser(User user) {
        String insertUserQuery = "INSERT INTO " + USER_TABLE
                + " (name, age, email , password , sex , state , city , country , dob , ration_no , card_type , role) VALUES (?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, user.getState());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getDob());
            preparedStatement.setString(10, user.getRationNo());
            preparedStatement.setString(11, user.getCardType());
            preparedStatement.setString(12, user.getRole());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        String deleteQuery = "DELETE FROM " + USER_TABLE + " WHERE userId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        String updateQuery = "UPDATE " + USER_TABLE
                + "SET name = ?,age=?,email=?,password=?,sex=?,state=?,city=?,country=?,dob=?,ration_no=?,card_type = ?,role=? WHERE user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, user.getState());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getDob());
            preparedStatement.setString(10, user.getRationNo());
            preparedStatement.setString(11, user.getCardType());
            preparedStatement.setString(12, user.getRole());
            preparedStatement.setInt(13, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USER);

            while (resultSet.next()) {
                int uId = resultSet.getInt("user_id");
                String uName = resultSet.getString("name");
                int uAge = resultSet.getInt("age");
                String uEmail = resultSet.getString("email");
                String password = resultSet.getString("password");
                String sex = resultSet.getString("sex");
                String state = resultSet.getString("state");
                String city = resultSet.getString("city");
                String country = resultSet.getString("country");
                String dob = resultSet.getString("dob");
                String rNo = resultSet.getString("ration_no");
                String cardType = resultSet.getString("card_type");
                String role = resultSet.getString("role");

                User user = new User(uId, uName, uAge, uEmail, password, sex, state, city, country, dob, rNo, cardType,
                        role);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PRODUCTS);

            while (resultSet.next()) {
                int pId = resultSet.getInt("product_id");
                String pName = resultSet.getString("product_name");
                int pPrice = resultSet.getInt("product_price");
                int pQuantity = resultSet.getInt("product_quantity");

                Product product = new Product(pId, pName, pPrice, pQuantity);
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }

    public int addProduct(Product product) {
        String insertTaskQuery = "INSERT INTO " + PRODUCT_TABLE
                + " (product_name, product_price, product_quantity) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertTaskQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getProductStock());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                preparedStatement.close();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean updateProduct(Product product) {
        String updateTaskQuery = "UPDATE " + PRODUCT_TABLE
                + " SET product_name = ?, product_price = ?, product_quantity = ? WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateTaskQuery)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getProductStock());
            preparedStatement.setInt(4, product.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(Product product) {
        String deleteTaskQuery = "DELETE FROM " + PRODUCT_TABLE + " WHERE product_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteTaskQuery);
            preparedStatement.setInt(1, product.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}