package vaccinationdrive;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
public class Driver {
    Scanner getInput = new Scanner(System.in);
    static String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    static void goToNextVaccinationDate(String nextVaccinationDate) {
        todayDate = nextVaccinationDate;
    }
    public static void main(String[] args) {
        Driver driverObj = new Driver();
        Scanner input = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("Enter your choice: \n 1. New Account Creation \n 2. Vaccination Details \n 3. Users History \n 4. Exit");
            choice = input.nextInt();
            switch(choice) {
                case 1:
                    new Beneficiary();
                    break;
                case 2:
                    new Vaccination();
                    break;
                case 3:
                    driverObj.checkHistory();
                    break;
                case 4:
                    System.out.println("\n\n Program Ending . . . \n\n");
                    return;
                default:
                    System.out.println("please enter a proper choice number");
            }
        }
    }

    void checkHistory() {
        int totalUsers = Vaccination.registeredUsers.size();
        if(totalUsers == 0) {
            System.out.println("No users yet\n");
            return;
        }
        while(true) {
            System.out.println("Enter Choice: \n 1. All Registered Users \n 2. All Vaccinated Users \n 3. Non - Vaccinated Users \n 4. Go Back");
            int historyChoice = getInput.nextInt();
            switch(historyChoice) {
                case 1:
                    getAllRegisteredUsers(totalUsers);
                    break;
                case 2:
                    getAllVaccinatedUsers(totalUsers);
                    break;
                case 3:
                    getAllNonVaccinatedUsers(totalUsers);
                    break;
                case 4:
                    System.out.println("Returning to main menu . . .");
                    return;
                default:
                    System.out.println("Enter a proper choice!");;
            }
        }
    }

    void getAllRegisteredUsers(int totalUsers) {
        for(Beneficiary obj : Vaccination.registeredUsers) {
            System.out.println("Name: " + obj.getName() + " \nRegister Number: " + obj.getRegNumber() + "\n");
        }
        System.out.println("Total Number of registered User(s): " + totalUsers + "\n");
    }

    void getAllVaccinatedUsers(int totalUsers) {
        int vaccinatedUsers = 0, completelyVaccinatedUsers = 0, onlyOnceVaccinatedUsers = 0;
        for(Beneficiary obj : Vaccination.registeredUsers) {
            if(obj.completelyVaccinated) {
                System.out.println("Name: " + obj.getName() + " \nRegister Number: " + obj.getRegNumber() + " \nFirst Vaccination Date: " + obj.firstVaccinationDate + " \nSecond Vaccination Date: " + obj.secondVaccinationDate + " \nYou are completely Vaccinated\n");
                vaccinatedUsers++;
                completelyVaccinatedUsers++;
            } else if (obj.nextVaccinationDate != null){
                System.out.println("Name: " + obj.getName() + " \nRegister Number: " + obj.getRegNumber() + " \nFirst Vaccination Date: " + obj.firstVaccinationDate + " \nYour are vaccinated only once, your Next Vaccination Date is: " + obj.nextVaccinationDate + "\n");
                vaccinatedUsers++;
                onlyOnceVaccinatedUsers++;
            }
        }
        if(vaccinatedUsers == 0) {
            System.out.println("No Vaccinated Users Yet\n");
        } else {
            System.out.println("Out of " + totalUsers + " user(s) " + onlyOnceVaccinatedUsers + " user(s) have been vaccinated only for the first time\n");
            System.out.println("Out of " + totalUsers + " user(s) " + completelyVaccinatedUsers + " user(s) have been vaccinated completely\n");
        }
    }

    void getAllNonVaccinatedUsers(int totalUsers) {
        int nonVaccinatedUsers = 0;
        for(Beneficiary obj : Vaccination.registeredUsers) {
            if(obj.nextVaccinationDate == null) {
                System.out.println("Name: " + obj.getName() + " \nRegister Number: " + obj.getRegNumber() + "\nyou haven't vaccinated yet");
                nonVaccinatedUsers++;
            }
        }
        if(nonVaccinatedUsers == 0) {
            System.out.println("all the users are vaccinated");
        } else {
            System.out.println("Out of " + totalUsers + " user(s) " + nonVaccinatedUsers + " user(s) haven't been vaccinated at all\n");
        }
    }
}