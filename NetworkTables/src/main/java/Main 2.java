
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Main{

    public static void main(String[] args){

        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        inst.startServer("networktables.ini", "0.0.0.0", 1735);
        
        NetworkTable table = inst.getTable("SmartDashboard");
        NetworkTableEntry connectionEntry = table.getEntry("connected");

        connectionEntry.forceSetBoolean(true);
        
        System.out.println("Initialized");
 
        while(true){

        }     
  }
}

