package garbage;

import java.util.List;

public class RegistrationConsoleWriter {
    public static void print(List<Registration> registrations) {
        System.out.println("Print from database: ");
        for(Registration registration : registrations){
            System.out.println(registration);
        }
    }
}
