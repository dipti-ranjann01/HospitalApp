import Services.DoctorService;
import Services.PatientService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        DoctorService doctorService=new DoctorService();
        PatientService patientService=new PatientService();
        while(true)
        {
            String input=sc.nextLine();
            input=input.trim();
            String[] inArr=input.split(" ");
            switch(inArr[0])
            {
                case "registerDoc" : {
                    doctorService.registerDoc(inArr[1],inArr[2]);
                    break;
                }
                case "markDocAvail" : {
                    doctorService.markDocAvail(inArr[1],inArr[2]);
                    break;
                }
                case "showAvailByspeciality": {
                    doctorService.showAvailByspeciality(inArr[1]);
                    break;
                }
                case "registerPatient" :{
                    patientService.registerPatient(inArr[1]);
                    break;
                }
                case "bookAppointment" :{
                    if(inArr.length==5) {
                        doctorService.bookAppointment(inArr[1], inArr[2], inArr[3], inArr[4], patientService);
                    }
                    else{
                        String waitTime="";
                        doctorService.bookAppointment(inArr[1], inArr[2], inArr[3], waitTime, patientService);
                    }
                    break;
                }
                case "cancelBookingId" :{
                    doctorService.cancelBookingId(Integer.parseInt(inArr[1]));
                    break;
                }
                case "showAppointmentsBooked" : {
                    patientService.showAppointmentsBooked(inArr[1],doctorService);
                    break;
                }
                default:{
                    return;
                }
            }
        }
    }
}
