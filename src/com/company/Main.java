package com.company;

//import com.company.marshaling.DownloadFromBD;
//import com.company.marshaling.marsh_book;

//import com.company.marshaling.Lib_book;
//import com.company.marshaling.Lib_history;
//import com.company.marshaling.Lib_users;
//import com.company.marshaling.Unmarsh;

import com.company.marshaling.Lib_book;
import com.company.marshaling.Lib_history;
import com.company.marshaling.Lib_users;
import com.company.marshaling.Unmarsh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String ADURL = "jdbc:postgresql://localhost:5433/el_library";
    private static final String LOGIN = "postgres";;
    private static final String PASSWORD = "12345";

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Class.forName("org.postgresql.Driver");
        String url = ADURL;
        String login = LOGIN;
        String password = PASSWORD;
        Connection con = DriverManager.getConnection(url, login, password);
        Object lock = new Object();

//        DownloadFromBD loader = new DownloadFromBD();
//        Thread th1 = new Thread(new marsh_book("books.xml", loader.loadBooks(con)));
//        Thread th2 = new Thread(new marsh_book("users.xml", loader.loadUsers(con)));
//        Thread th3 = new Thread(new marsh_book("history.xml", loader.loadHistory(con)));
//
//        th1.start();
//        th2.start();
//        th3.start();
//        con.close();


        Lib_book books = new Lib_book();
        Lib_users users = new Lib_users();
        Lib_history history = new Lib_history();
        Thread th1 = new Thread(new Unmarsh("books.xml", books, con));
        Thread th2 = new Thread(new Unmarsh("users.xml", users, con));
        Thread th3 = new Thread(new Unmarsh("history.xml", history, con));

        th1.start();
        th2.start();
        th3.start();

        th1.join();
        th2.join();
        th3.join();

        con.close();
    }
}
