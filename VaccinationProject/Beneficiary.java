package vaccinationdrive;

import java.util.Scanner;

class Beneficiary {
    Scanner input1 = new Scanner(System.in);
    private static long registerNumberGenerator = 1001;
    private String name;
    private int age;
    private char gender;
    private long mobileNumber;
    private String city;
    final private long registerNumber;
    boolean completelyVaccinated = false;
    String firstVaccinationDate, secondVaccinationDate;
    String nextVaccinationDate;

    Beneficiary() {
        this.setName();
        this.setAge();
        this.setGender();
        this.setMobileNumber();
        this.setCity();
        this.registerNumber = Beneficiary.registerNumberGenerator;
        (Beneficiary.registerNumberGenerator)++;
        System.out.println("your registration number is: " + this.getRegNumber() + "\n Successfully Registered !\n");
        Vaccination.registeredUsers.add(this);
    }

    String getName() {
        return this.name;
    }

    int getAge() {
        return this.age;
    }

    char getGender() {
        return this.gender;
    }

    long getMobileNumber() {
        return this.mobileNumber;
    }

    String getCity() {
        return this.city;
    }

    long getRegNumber() {
        return this.registerNumber;
    }

    void setName() {
        System.out.println("Enter Name: ");
        this.name = input1.nextLine();
    }

    void setAge() {
        System.out.println("Enter Age: ");
        this.age = input1.nextInt();
    }

    void setGender() {
        System.out.println("Enter Gender: ");
        this.gender = input1.next().charAt(0);
    }

    void setMobileNumber() {
        System.out.println("Enter Mobile Number: ");
        this.mobileNumber = (input1.nextLong());
    }

    void setCity() {
        System.out.println("Enter City: ");
        this.city = input1.next();
    }

}