package org.sample.web.service.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

public class GetClaimServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String callBackUrl = req.getParameter("callbackUrl");

        String cityClaim = "Colombo";
        String mobileNumber = "094344442244";

        req.setAttribute("cityClaim", cityClaim);
        req.setAttribute("mobileNumber", mobileNumber);

        String responseUrl = URLDecoder.decode(callBackUrl, "UTF-8") +
                "&cityClaim=" + cityClaim +
                "&mobileNumber=" + mobileNumber;

        System.out.println(responseUrl);

        resp.sendRedirect(responseUrl);
    }

}
