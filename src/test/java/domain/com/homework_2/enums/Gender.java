package domain.com.homework_2.enums;

public enum Gender {
    MALE ("Male"),
    FEMALE ("Female"),
    OTHER ("Other");

    private String value;

    Gender(final String value) {
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
