import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
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
 * Date  01/12/19
 * Time  10:22 PM
 */
public class CheckoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        RequestDispatcher rdObj;
        final String userName = request.getParameter("username");
        final String userEmail = request.getParameter("userEmail");

        if (userName == null || userName.length() == 0 || userEmail == null || userEmail.length() == 0) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Not a valid information! Please provide user Name, user Email to checkout</p>");
            rdObj = request.getRequestDispatcher("/checkout.jsp");
            rdObj.include(request, response);
            out.close();
            return;
        }

        if (!userPresent(userEmail)) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>User Not present please checkin</p>");
            rdObj = request.getRequestDispatcher("/checkin.jsp");
            rdObj.include(request, response);
            out.close();
            return;

        }
        checkout(userEmail, userName);
        out.write("<p id='errMsg' style='color: green; font-size: larger;'>Checked out</p>");
        rdObj = request.getRequestDispatcher("/index.jsp");
        rdObj.include(request, response);
        out.close();
    }

    private boolean userPresent(String userEmail) {
        final MongoCollection<Document> mongoCollection = MongoUtils.getCollection("userDetails");
        BasicDBObject userQuery = new BasicDBObject();
        userQuery.put(UserDetails.USER_EMAIL, userEmail);
        final FindIterable<Document> documents = mongoCollection.find(userQuery);
        return documents.iterator().hasNext();
    }

    private void checkout(String userEmail, String userName) {
        final MongoCollection<Document> mongoCollection = MongoUtils.getCollection("userDetails");
        BasicDBObject userQuery = new BasicDBObject();
        userQuery.put(UserDetails.USER_EMAIL, userEmail);
        final FindIterable<Document> documents = mongoCollection.find(userQuery);
        if (documents.iterator().hasNext()) {
            final Document next = documents.iterator().next();
            final UserDetails userDetails = UserDetails.fromDocument(next);
            userDetails.setCheckoutTime(System.currentTimeMillis());
            mongoCollection.insertOne(userDetails.toDocument());
            sendEmail(userDetails);
        }
    }

    private void sendEmail(UserDetails userDetails) {
        String to = userDetails.getUserEmail();
        String from = "no_reply@example.com";
        String host = "localhost";
        Long checkoutTime = userDetails.getCheckoutTime();
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
            message.setSubject("checkout out successfully");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("You checkout out successfully").append("\n");
            stringBuilder.append("Host Name - ").append(userDetails.getHostName()).append("\n");
            stringBuilder.append("Host Email - ").append(userDetails.getHostEmail()).append("\n");
            stringBuilder.append("Checkin time - ").append(userDetails.getCheckinTime()).append("\n");
            stringBuilder.append("Checkout time - ").append(userDetails.getCheckoutTime()).append("\n");
            message.setText(stringBuilder.toString());
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
