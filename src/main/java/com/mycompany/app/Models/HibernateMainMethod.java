package com.mycompany.app.Models;

import com.mycompany.app.Dao.ContactDao;
import com.mycompany.app.Dao.ContactDaoImpl;

public class HibernateMainMethod {
    public static void main(String[] args) {
        ContactDao contactDao = new ContactDaoImpl();
        System.out.println(contactDao.read(1));
    }
}