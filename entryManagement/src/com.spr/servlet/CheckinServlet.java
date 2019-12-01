import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by arpitjain
 * Date  30/11/19
 * Time  11:39 PM
 */
public class CheckinServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        RequestDispatcher rdObj;

        final String userName = request.getParameter("username");
        final String userEmail = request.getParameter("userEmail");
        final String mobileNumber = request.getParameter("mobileNumber");
        final String hostName = request.getParameter("hostName");
        final String hostMobileNumber = request.getParameter("hostMobileNumber");
        final String hostEmail = request.getParameter("hostemail");
        if (userName == null || userName.length() == 0 || userEmail == null || userEmail.length() == 0 || hostName == null || hostName.length() == 0 || hostEmail == null || hostEmail.length() == 0) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Not a valid information! Please provide user Name, user Email, host Name, host Email</p>");
            rdObj = request.getRequestDispatcher("/checkin.jsp");
            rdObj.include(request, response);
            out.close();
            return;
        }

        final UserDetails userDetails = new UserDetails();
        userDetails.setCheckinTime(System.currentTimeMillis());
        userDetails.setUserName(userName);
        userDetails.setMobileNumber(mobileNumber);
        userDetails.setUserEmail(userEmail);
        userDetails.setHostEmail(hostEmail);
        userDetails.setHostName(hostName);
        userDetails.setHostMobileNumber(hostMobileNumber);
        saveAndSendEmail(userDetails);

        out.write("<p id='errMsg' style='color: green; font-size: larger;'>Details Saved</p>");
        rdObj = request.getRequestDispatcher("/index.jsp");
        rdObj.include(request, response);
        out.close();
    }

    private void saveAndSendEmail(UserDetails userDetails) {
        final MongoCollection<Document> mongoCollection = MongoUtils.getCollection("userDetails");
        mongoCollection.insertOne(userDetails.toDocument());
        //send Email

        String to = userDetails.getHostEmail();
        String from = "no_reply@example.com";
        String host = "localhost";

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from, "NoReply-JD"));

            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("New User checked in");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("New User Checked in \n");
            stringBuilder.append("Username - ").append(userDetails.getHostName()).append("\n");
            stringBuilder.append("Email - ").append(userDetails.getHostName()).append("\n");
            stringBuilder.append("Checkin Time - ").append(userDetails.getCheckinTime()).append("\n");
            message.setText(stringBuilder.toString());
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}