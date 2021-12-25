package mydomain.com.homework_4;

public class Main {

    public static void main(String[] args) {
        Driver vasiliy = new Driver();

        vasiliy.firstName = "Vasiliy";
        vasiliy.lastName = "Pertov";
        vasiliy.age = 45;
        vasiliy.category = 'B';
        vasiliy.isAlcoholic = false;
        vasiliy.salary = 1300.30D;

        vasiliy.sayFullName();
        vasiliy.setHasInsurance(true);
        vasiliy.getHasInsurance();
        vasiliy.calculateSalary(22);
        vasiliy.getCategory();
        vasiliy.canDrive();
        vasiliy.isAllowToDrive();
    }
}
