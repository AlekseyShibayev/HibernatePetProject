package com.mycompany.app.DataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.Beans.Contact;
import com.mycompany.app.Utilities;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "InsertContactsServlet")
public class InsertContactsServlet extends BaseServletDao {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Contact contact = mapper.readValue(request.getParameter("entry"), Contact.class);
            if (Utilities.isContactCorrect(contact)) {
                ResultSet rs = statement.executeQuery("SELECT * FROM contacts WHERE telephone = '" + contact.getTelephone() + "';");
                if (!rs.isBeforeFirst()) {
                    statement.executeUpdate("INSERT INTO contacts (telephone, name) "
                            + "VALUES ('" + contact.getTelephone() + "', '" + contact.getName() + "');");
                    Utilities.myResponsePrint(contact, response);
                }
                else {
                    Utilities.myResponsePrint("0", response);
                }
            } else {
                Utilities.myResponsePrint("1", response);
            }
        } catch (SQLException e) {
            Utilities.myResponsePrint("2", response);
            e.printStackTrace();
        }
    }

}