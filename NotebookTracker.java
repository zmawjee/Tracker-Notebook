import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class NotebookTracker{
    public static Scanner kbd = new Scanner(System.in);

    public static boolean timesCross(int start_p1, int end_p1,int start_p2,int end_p2)
        {
            boolean overlap = false;//instanciate variable

            if ( (start_p1 < end_p2) && (start_p2 < end_p1) ) //check whether two times overlap
            {
                overlap = true;
            }

            return overlap;
        
    } 
    public static boolean timesCrossLate(int start_p1, int end_p1,int start_p2,int end_p2){
        if (end_p1 < start_p1){ //checks if end period is smaller than start and adjusts values accordingly
            start_p1 -= 12;
            end_p1 += 12;
        }
        if (end_p2< start_p2){
            start_p2 -= 12;
            end_p2 += 12;
        }
        return timesCross(start_p1, end_p1, start_p2, end_p2);
    }

    public static int getCrossingStaff(int start_period,int end_period)
    {
        int num_staff_notebook = 0;

        System.out.println("Enter the number of staff in the lab:");
        int number_of_staff = kbd.nextInt();
        
        for (int i = 0; i<number_of_staff; i++){
            System.out.println("Enter the staff member's name:");
            String name = kbd.next();
            System.out.println("Enter the entry time:");
            int entry_time = kbd.nextInt();
            System.out.println("Enter the exit time:");
            int exit_time = kbd.nextInt();
            
            if(timesCross(start_period, end_period, entry_time, exit_time)){
                System.out.println(name + " might have the notebook.");
                num_staff_notebook +=1;
            }
            else{
                System.out.println(name+ " will not have the notebook.");
            }
        }
        return num_staff_notebook;
    }

public static void main(String[] args){ 
    //Initiate Variables
    int num_suspected_staff = 0;
    int time_lost; int time_noticed;

    //Find notebook information using Scanner
    System.out.println("What is the earliest the notebook could have been lost?");
    time_lost = kbd.nextInt();
    System.out.println("When did you notice the notebook was missing?");
    time_noticed = kbd.nextInt();

    // for loop that loops the number of times there are files
    for (int i = 0; i<args.length;i++) {
        try {
            //open file
            File file = new File(args[i]);
    
            Scanner rdr = new Scanner(file);
            //read file and save staff info
            while(rdr.hasNextLine()){
                String staff_info = rdr.nextLine();
                String[] staff_info_split = staff_info.split(" ");
            
                String name = staff_info_split[0]; 
                int start_time = Integer.parseInt(staff_info_split[1]);
                int end_time = Integer.parseInt(staff_info_split[2]);
                
                //check if staff might have notebook using timesCross method
                if (timesCross(time_lost, time_noticed, start_time, end_time)){
                    System.out.println(name+" might have the notebook.");
                    num_suspected_staff +=1;
                }

                
     }} catch (FileNotFoundException e) {
         System.err.println("Error file not found: " + e);
 }
}
if (num_suspected_staff == 0){
    int num_staff = getCrossingStaff(time_lost, time_noticed);
    System.out.println("Number of staff who might have the notebook: " + num_staff);

}
else {
     System.out.println("Number of staff who might have the notebook: " + num_suspected_staff);
}

 }
    }
