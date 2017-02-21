package com.company.marshaling;

import java.sql.*;
import java.text.SimpleDateFormat;
/**
 * Created by Tanusha on 21/02/2017.
 */
public class UploadToBD {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final String ADURL = "jdbc:postgresql://localhost:5433/el_library";
    private static final String LOGIN = "postgres";;
    private static final String PASSWORD = "Vera2304";

    public void uploadBooks (Object object) throws SQLException, ClassNotFoundException {
        Lib_book books = (Lib_book) object;
        Class.forName("org.postgresql.Driver");
        String url = ADURL;
        String login = LOGIN;
        String password = PASSWORD;
        Connection dbConnection = DriverManager.getConnection(url, login, password);
        try {
            String insertTableSQL = "INSERT INTO public.\"Books\"(\n" +
                    "\t\"id_books\", \"type_publication\", \"name\", \"year\", \"date\", \"author\", \"genre\")\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = dbConnection.prepareStatement(insertTableSQL);
            dbConnection.setAutoCommit(false);

            for (int i = 0; i < books.getBook().size(); i++) {
                prepareStatement.setInt(1, books.getBook().get(i).getId_book());
                prepareStatement.setString(2, books.getBook().get(i).getType_publication());
                prepareStatement.setString(3, books.getBook().get(i).getName());
                prepareStatement.setInt(4, books.getBook().get(i).getYear());
                if (books.getBook().get(i).getDate_pub() != null)
                    prepareStatement.setDate(5, new Date(books.getBook().get(i).getDate_pub().getTime()));
                else prepareStatement.setDate(5, null);
                prepareStatement.setString(6, books.getBook().get(i).getAuthor());
                prepareStatement.setString(7, books.getBook().get(i).getGenre());
                prepareStatement.addBatch();
            }
            prepareStatement.executeBatch();
            dbConnection.commit();

            System.out.println("Record is inserted into Books table!");
            prepareStatement.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            dbConnection.rollback();

        } finally {
                dbConnection.close();
        }
    }

    public void uploadUsers (Object object) throws SQLException, ClassNotFoundException {
        Lib_users users = (Lib_users) object;
        Class.forName("org.postgresql.Driver");
        String url = ADURL;
        String login = LOGIN;
        String password = PASSWORD;
        Connection dbConnection = DriverManager.getConnection(url, login, password);
        try {
            String insertTableSQL = "INSERT INTO public.\"Users\"(\n" +
                    "\t\"id_user\", \"firstname\", \"lastname\", \"email\", \"date_reg\", \"login\")\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement prepareStatement = dbConnection.prepareStatement(insertTableSQL);
            dbConnection.setAutoCommit(false);

            for (int i = 0; i < users.getUser().size(); i++) {
                prepareStatement.setInt(1, users.getUser().get(i).getId_user());
                prepareStatement.setString(2, users.getUser().get(i).getFirstname());
                prepareStatement.setString(3, users.getUser().get(i).getLastname());
                prepareStatement.setString(4, users.getUser().get(i).getEmail());
                prepareStatement.setDate(5, new Date(users.getUser().get(i).getDate_reg().getTime()));
                prepareStatement.setString(6, users.getUser().get(i).getLogin());
                prepareStatement.addBatch();
            }
            prepareStatement.executeBatch();
            dbConnection.commit();

            System.out.println("Record is inserted into Users table!");
            prepareStatement.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            dbConnection.rollback();

        } finally {
            dbConnection.close();
        }
    }
}
