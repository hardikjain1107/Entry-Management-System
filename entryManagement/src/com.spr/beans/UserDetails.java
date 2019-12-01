import org.bson.Document;

/**
 * Created by arpitjain
 * Date  01/12/19
 * Time  3:00 PM
 */
public class UserDetails extends Document {

    public static final String ID = "id";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_NAME = "userName";
    public static final String MOBILE_NUMBER = "mobileNumber";
    public static final String HOST_NAME = "hostName";
    public static final String HOST_MOBILE_NUMBER = "hostMobileNumber";
    public static final String HOST_EMAIL = "hostEmail";
    public static final String CHECK_IN_TIME = "checkinTime";
    public static final String CHECK_OUT_TIME = "checkoutTime";

    private String id;
    private String userName;
    private String userEmail;
    private String mobileNumber;
    private String hostName;
    private String hostMobileNumber;
    private String hostEmail;
    private Long checkinTime;
    private Long checkoutTime;

    public UserDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostMobileNumber() {
        return hostMobileNumber;
    }

    public void setHostMobileNumber(String hostMobileNumber) {
        this.hostMobileNumber = hostMobileNumber;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public void setCheckinTime(Long checkinTime) {
        this.checkinTime = checkinTime;
    }

    public void setCheckoutTime(Long checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public Long getCheckinTime() {
        return checkinTime;
    }

    public Long getCheckoutTime() {
        return checkoutTime;
    }

    public Document toDocument() {
        Document document = new Document();
        document.put(USER_EMAIL, userEmail);
        document.put(USER_NAME, userName);
        document.put(MOBILE_NUMBER, mobileNumber);
        document.put(HOST_NAME, hostName);
        document.put(HOST_EMAIL, hostEmail);
        document.put(HOST_MOBILE_NUMBER, hostMobileNumber);
        document.put(CHECK_IN_TIME, checkinTime);
        document.put(CHECK_OUT_TIME, checkoutTime);
        return document;
    }

    public static UserDetails fromDocument(Document document) {
        UserDetails userDetails = new UserDetails();

        userDetails.setCheckinTime(document.getLong(CHECK_IN_TIME));
        userDetails.setCheckoutTime(document.getLong(CHECK_OUT_TIME));
        userDetails.setUserEmail(document.getString(USER_EMAIL));
        userDetails.setUserName(document.getString(USER_NAME));
        userDetails.setMobileNumber(document.getString(MOBILE_NUMBER));
        userDetails.setHostEmail(document.getString(HOST_EMAIL));
        userDetails.setHostName(document.getString(HOST_NAME));
        userDetails.setHostMobileNumber(document.getString(HOST_MOBILE_NUMBER));
        userDetails.setId(document.getString(ID));
        return userDetails;
    }
}
