package com.company.marshaling;

/**
 * Created by Tanusha on 21/02/2017.
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;

public class Unmarsh implements Runnable {
    private String path;
    private Object object;

    public Unmarsh(String path, Object object) {
        this.path = path;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = jaxbUnmarshaller.unmarshal(file);
            UploadToBD uploader = new UploadToBD();
            switch (object.getClass().getSimpleName()) {
                case "Lib_book":
                    //System.out.println(1);
                    uploader.uploadBooks(object);
                    return;
                case "Lib_users":
                    //System.out.println(2);
                    uploader.uploadUsers(object);
                    return;
            };

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
