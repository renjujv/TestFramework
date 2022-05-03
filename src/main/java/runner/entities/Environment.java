package runner.entities;

public enum Environment {
    SAUCELABS("sl"), BROWSERSTACK("bs"), LAMBDATEST("lt"), LOCAL("local");

    private final String EnvironmentName;

    Environment(String EnvironmentName){ this.EnvironmentName = EnvironmentName;}

    public String getValue(){
        return EnvironmentName;
    }
}
