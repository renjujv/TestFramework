package runner.entities;

public enum BSCapabilities {
    BROWSER("browser"), BROWSER_VERSION("browser_version"), OS("os"), OS_VERSION("os_version"), BUILD("build"), NAME("name");
    final String capabilityName;

    BSCapabilities(String capability) {
        this.capabilityName = capability;
    }
}