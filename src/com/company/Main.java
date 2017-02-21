package com.company;

//import com.company.marshaling.DownloadFromBD;
//import com.company.marshaling.marsh_book;

import com.company.marshaling.Lib_book;
import com.company.marshaling.Lib_users;
import com.company.marshaling.Unmarsh;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        DownloadFromBD loader = new DownloadFromBD();
//        Thread th1 = new Thread(new marsh_book("books.xml", loader.loadBooks()));
//        Thread th2 = new Thread(new marsh_book("users.xml", loader.loadUsers()));
//
//        th1.start();
//        th2.start();


        Lib_book books = new Lib_book();
        Lib_users users = new Lib_users();
        Thread th1 = new Thread(new Unmarsh("books.xml", books));
        Thread th2 = new Thread(new Unmarsh("users.xml", users));

        th1.start();
        th2.start();

    }
}
