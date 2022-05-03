package runner.entities;

public enum OSVersion {
    BIG_SUR("Big Sur"), MONTEREY("Monterey"), WINDOWS_11(String.valueOf(11)), WINDOWS_10("10"), WINDOWS_8("8"), WINDOWS_7("7");

    private final String OS_VERSION;

    OSVersion(String osv) {
        this.OS_VERSION = osv;
    }

    public String getValue(){
        return OS_VERSION;
    }
}
