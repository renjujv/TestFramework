package runner.entities;

public enum BrowserVersion {
    LATEST("latest");

    private final String browserVersion;

    BrowserVersion(String browserVersion){ this.browserVersion = browserVersion;}

    public String getValue(){
        return browserVersion;
    }
}
