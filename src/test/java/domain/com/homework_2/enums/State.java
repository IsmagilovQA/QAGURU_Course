package domain.com.homework_2.enums;

public enum State {
    NCR ("NCR"),
    UTTAR_PRADESH ("Uttar Pradesh"),
    HARYANA ("Haryana"),
    RAJASTHAN ("Rajasthan");

    private String value;

    State(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
