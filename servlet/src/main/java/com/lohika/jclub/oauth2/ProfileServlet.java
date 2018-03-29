package com.lohika.jclub.oauth2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object userCredentials = req.getSession().getAttribute("credential");
        if (userCredentials != null && userCredentials instanceof Credential) {
            Credential credential = (Credential) userCredentials;
            System.out.println(credential.getAccessToken());
            Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("").build();
            Userinfoplus userinfoplus = oauth2.userinfo().get().execute();
            req.setAttribute("name", userinfoplus.getName());
            req.setAttribute("email", userinfoplus.getEmail());
            req.setAttribute("avatar", userinfoplus.getPicture());
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }

}
