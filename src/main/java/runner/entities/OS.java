package runner.entities;

public enum OS {
    WINDOWS("Windows"), LINUX("Linux"), OS_X("OS X");

    private final String OS;

    OS(String OSName){ this.OS = OSName;}

    public String getValue(){
        return OS;
    }
}
