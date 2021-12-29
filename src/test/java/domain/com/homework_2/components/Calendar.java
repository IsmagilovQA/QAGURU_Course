package domain.com.homework_2.components;


import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class Calendar {

    public void setDate(String month, String year, String day) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        String dayLocator = format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day);
        $(dayLocator).click();
    }
}
