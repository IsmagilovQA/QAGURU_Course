package mydomain.com.homework_4;

public class Driver {

    public String firstName;
    public String lastName;
    public int age;
    public char category;
    public boolean hasInsurance;
    public boolean isAllowToDrive;
    public boolean isAlcoholic;
    public double salary;

    public void calculateSalary(int experience) {
        if (experience >= 10) {
            salary = salary * 1.3;
            System.out.println("Your salary has grown by 30%. From now you will get: " + salary);
        } else {
            System.out.println("You don't have enough experience to get salary upgrade");
        }
    }

    public void sayFullName() {
        System.out.println("Hi! I'm a driver. My full name is: " + firstName + " " + lastName);
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public void getHasInsurance() {
        System.out.println("Does driver have insurance? -> " + hasInsurance);
    }

    public void getCategory() {
        System.out.println("Your driving category is: " + category);
    }

    public boolean canDrive() {
        if (age > 75 || age < 16) {
            System.out.println("You can't drive a car!");
            return isAllowToDrive = false;
        }
        System.out.println("Your age allowed to drive a car!");
        return isAllowToDrive = true;
    }

    public boolean isAllowToDrive() {
        System.out.println("isAllowToDrive -> " + isAllowToDrive);
        return isAllowToDrive;
    }
}
