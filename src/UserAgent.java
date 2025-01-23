public class UserAgent {
    final String userAgent;
    final String typeOS;
    final String browser;


    public UserAgent(String userAgent) {
        this.userAgent = userAgent;

        String[] parts = userAgent.split(";");
        String typePart = parts[0].replaceAll(" ", "").replaceAll("\\(", "");

        if (typePart.contains("Windows") || typePart.contains("Linux") || typePart.contains("Macintosh")) {
            typeOS = typePart;
        } else typeOS = "not valid OS";

        if (userAgent.contains("Gecko") && userAgent.contains("Firefox")) {
            browser = "Firefox";
        } else if (userAgent.contains("KHTML") && userAgent.contains("Chrome") && !userAgent.contains("OPR") && userAgent.contains("Safari") && !userAgent.contains("Edg")) {
            browser = "Chrome";
        } else if (userAgent.contains("KHTML") && userAgent.contains("Chrome") && userAgent.contains("OPR")) {
            browser = "Opera";
        } else if (userAgent.contains("Edg")) {
            browser = "Edge";
        }
        else browser =  "Another browser";
    }

    public String getTypeOS() {
        return typeOS;
    }

    public String getBrowser() {
        return browser;
    }
}
