package runner.entities;

public enum Browser {
    GOOGLE_CHROME("chrome"), MOZILLA_FIREFOX("firefox"), MS_EDGE("edge"), SAFARI("safari");

    private final String Browser;

    Browser(String BrowserName){ this.Browser = BrowserName;}

    public String getValue(){
        return Browser;
    }
}
