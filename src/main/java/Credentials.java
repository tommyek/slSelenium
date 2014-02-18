
public class Credentials {

    private static final String SMS_TICKET_PHONENO_KEY = "TRACKS_USERNAME_KEY";
    private static final String SMS_TICKET_PASSWORD_KEY = "TRACKS_PASSWORD_KEY";
    private static final String SMS_TICKET_PORTAL_URL_KEY = "TRACKS_URL";

    public final String telno;
    public final String password;
    public final String ticket_url;

    public Credentials(){
    telno = System.getProperty(SMS_TICKET_PHONENO_KEY, "0700012869");
    password = System.getProperty(SMS_TICKET_PASSWORD_KEY, "password");
    ticket_url = System.getProperty(SMS_TICKET_PORTAL_URL_KEY, "https://biljett.klarna.se");
    }

}
