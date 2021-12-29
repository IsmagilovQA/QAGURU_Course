package domain.com.homework_2.enums;

public enum Hobby {
    SPORTS ("Sports"),
    READING ("Reading"),
    MUSIC ("Music");

    private String value;

    Hobby(final String value) {
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
