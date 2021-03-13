package gui;

public enum PanelName {
    MAIN("MAIN"),
    ENCODE("ENCODE"),
    DECODE("DECODE"),
    PREVIEW("PREVIEW");

    private String name;

    PanelName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
