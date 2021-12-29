package domain.com.homework_2.enums;

public enum City {
    AGRA ("Agra"),
    LUCKNOW ("Lucknow"),
    MERRUT ("Merrut");

    private String value;

    City(final String value) {
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
