package Enum;

public enum Gender {
    MALE,
    FEMALE;
    public static Gender fromString(String genderStr) {
        switch (genderStr.toLowerCase()) {
            case "male":
                return MALE;
            case "female":
                return FEMALE;
            default:
                return null;
        }
    }
}
