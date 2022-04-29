package vaccinationdrive;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;

class Vaccination {
    Scanner input2 = new Scanner(System.in);
    static ArrayList<Beneficiary> registeredUsers = new ArrayList<Beneficiary>();
    private int accountIndex = -1;

    Vaccination() {
        System.out.println("enter the registration number: ");
        long rNumber = (input2.nextLong());
        if(isAlreadyRegistered(rNumber)) {
            this.mainMenuDriver(Vaccination.registeredUsers.get(this.accountIndex));
        } else {
            System.out.println("kindly register or enter the correct register number");
        }
    }

    void mainMenuDriver(Beneficiary obj) {
        while(true) {
            System.out.println("\n 1. Vaccine today? \n 2. Vaccination History \n 3. Next Vaccination Date \n 4. Get Details \n 5. Go to next Vaccination Date \n 6. Go Back ");
            int vaccinationInput = input2.nextInt();
            switch (vaccinationInput) {
                case 1:
                    vaccineToday(obj);
                    break;
                case 2:
                    vaccinationHistory(obj);
                    break;
                case 3:
                    getNextVaccinationDate(obj);
                    break;
                case 4:
                    printReceipt(obj);
                    break;
                case 5:
                    jumpDate(obj);
                    break;
                case 6:
                    System.out.println("Returning to main menu . . .");
                    return;
                default:
                    System.out.println("Enter a proper choice number!");
            }
        }
    }

    void jumpDate(Beneficiary obj) {
        if(obj.nextVaccinationDate == null) {
            System.out.println("kindly take your first vaccine first !");
        } else {
            if(Driver.todayDate.equals(obj.nextVaccinationDate)) {
                System.out.println("already in the required date");
            } else {
                Driver.goToNextVaccinationDate(obj.nextVaccinationDate);
                System.out.println("Today's date: " + Driver.todayDate);
            }
        }
    }

    void printReceipt(Beneficiary obj) {
        System.out.println("\n\n");
        System.out.println("Name: " + obj.getName());
        System.out.println("Register NUmber: " + obj.getRegNumber());
        System.out.println("Age: " + obj.getAge());
        System.out.println("gender: " + obj.getGender());
        System.out.println("Mobile Number: " + obj.getMobileNumber());
        System.out.println("City: " + obj.getCity());
        System.out.print("Vaccination Details: ");
        if(obj.completelyVaccinated) {
            System.out.println("you are fully vaccinated on dates: " + obj.firstVaccinationDate + " and " + obj.secondVaccinationDate);
        } else {
            if(obj.nextVaccinationDate == null) {
                System.out.println("You haven't vaccinated yet");
            } else {
                System.out.println("Your first vaccination date: " + obj.firstVaccinationDate + ", and your next vaccination date is: " + obj.nextVaccinationDate);
            }
        }
        System.out.println("\n\n");
    }

    void getNextVaccinationDate(Beneficiary obj) {
        System.out.print("Vaccination Details: ");
        if(obj.completelyVaccinated) {
            System.out.println("you've vaccinated fully");
        } else {
            if(obj.nextVaccinationDate != null) {
                System.out.println("your next vaccination date is: " + obj.nextVaccinationDate);
            } else {
                System.out.println("You haven't vaccinated yet");
            }
        }
    }

    void vaccinationHistory(Beneficiary obj) {
        System.out.print("Vaccination Details: ");
        if(obj.nextVaccinationDate == null) {
            System.out.println("you haven't vaccinated yet");
        } else {
            if(obj.completelyVaccinated) {
                System.out.println("you are fully vaccinated on dates: " + obj.firstVaccinationDate + " and " + obj.secondVaccinationDate);
            } else {
                System.out.println("you have completed your 1st dose on: " + obj.firstVaccinationDate);
            }
        }
    }

    void vaccineToday(Beneficiary obj) {
        System.out.print("Vaccination Details: ");
        if(obj.completelyVaccinated) {
            System.out.println("you've vaccinated fully");
        }
        else {
            if(obj.nextVaccinationDate == null) {
                obj.firstVaccinationDate = Driver.todayDate;
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(sdf.parse(obj.firstVaccinationDate));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                cal.add(Calendar.DAY_OF_MONTH, 84);
                obj.nextVaccinationDate = sdf.format(cal.getTime());
                System.out.println("Today is your first vaccination, your next vaccinated date is: " + obj.nextVaccinationDate);
            } else {
                if(Driver.todayDate.equals(obj.nextVaccinationDate)) {
                    System.out.println("today is your second vaccination, congratulations you have been vaccinated fully!");
                    obj.secondVaccinationDate = Driver.todayDate;
                    obj.completelyVaccinated = true;
                } else {
                    System.out.println("kindly come back at your specified date");
                }
            }
        }
    }

    boolean isAlreadyRegistered(long rNumber) {
        for(Beneficiary i : registeredUsers) {
            (this.accountIndex)++;
            if(i.getRegNumber() == rNumber) {
                return true;
            }
        }
        this.accountIndex = -1;
        return false;
    }

}