package com.company.marshaling;

import com.company.tables.Books;
import com.company.tables.History;
import com.company.tables.Users;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tanusha on 20/02/2017.
 */
public class DownloadFromBD {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Lib_book loadBooks (Connection con) throws SQLException, ClassNotFoundException {
        Lib_book books = new Lib_book();
        books.setBooks("List of books");
        try {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"public\".\"Books\"");
                while (rs.next()) {
                    Integer id = Integer.parseInt(rs.getString("id_books"));
                    String author = rs.getString("author").trim();
                    Date date = rs.getDate("date");
                    String genre = rs.getString("genre").trim();
                    String name = rs.getString("name").trim();
                    Integer year = rs.getInt("year");
                    String type_publication = rs.getString("type_publication").trim();

                    books.getBook().add(createBook(id, author, date, genre,
                            name, year, type_publication));
                }
                rs.close();
                stmt.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Books createBook(int id, String author, Date date, String genre,
                            String name, int year, String type) throws ParseException {
        Books book = new Books();
        book.setId_book(id);
        book.setAuthor(author);
        book.setDate_pub(date);
        book.setGenre(genre);
        book.setName(name);
        book.setType_publication(type);
        book.setYear(year);
        return book;
    }

    public Lib_users loadUsers (Connection con) throws SQLException, ClassNotFoundException {
        Lib_users users = new Lib_users();
        users.setUsers("List of users");
        try {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"public\".\"Users\"");
                while (rs.next()) {
                    Integer id = Integer.parseInt(rs.getString("id_user"));
                    String firstname = rs.getString("firstname").trim();
                    Date date = rs.getDate("date_reg");
                    String lastname = rs.getString("lastname").trim();
                    String email = rs.getString("email").trim();
                    String login1 = rs.getString("login").trim();

                    users.getUser().add(createUser(id, firstname, date, lastname,
                            email, login1));
                }
                rs.close();
                stmt.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Users createUser(int id, String firstname, Date date, String lastname,
                            String email, String login) throws ParseException {
        Users user = new Users();
        user.setId_user(id);
        user.setFirstname(firstname);
        user.setDate_reg(date);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setLogin(login);
        return user;
    }

    public Lib_history loadHistory (Connection con) throws SQLException, ClassNotFoundException {
        Lib_history history = new Lib_history();
        history.setHistories("List of history");
        try {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"public\".\"History\"");
                while (rs.next()) {
                    Integer id = Integer.parseInt(rs.getString("id"));
                    Integer id_user = Integer.parseInt(rs.getString("id_user"));
                    Integer id_book = Integer.parseInt(rs.getString("id_book"));
                    Date date = rs.getDate("date");

                    history.getHistory().add(createHistory(id, id_user, id_book, date));
                }
                rs.close();
                stmt.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public History createHistory(int id, int id_user, int id_book, Date date) throws ParseException {
        History history = new History();
        history.setId(id);
        history.setId_user(id_user);
        history.setId_book(id_book);
        history.setDate_red(date);

        return history;
    }
}
