
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads input into the program, then outputs the time taken for each
 * tower size for the tower of Hanoi problem
 * @author stevl
 */
public class Main {

    /**
     * @param args the command line arguments for output file names
     * @throws java.io.IOException if the buffered writer fails
     */
    public static void main(String[] args) throws IOException {
        FileWriter          fw;
        BufferedWriter      bw;
        String              outputMetricsFile;
        String              outputStepsFile;
        Metrics             Metrics;
        RecursVSIterTester  RVI;
        Metrics = new Metrics();
        int                 numberTests = 0;
        try
        {
            numberTests = Integer.parseInt(args[2]);
        }
        catch (Exception e)
        {
            System.out.println("args: OutputMetricsfile.txt "
                    + "OutputStepsfile.txt Number_of_tests");
            System.exit(0);
        }
        
        //var RH = new RecursiveHanoi(10);
        //RH.writeSteps('Q', 'R', 9);
        //RH.writeSteps('A', 'B', 99);
        //System.out.println(RH.getSteps());
        
        
        RVI = new RecursVSIterTester(numberTests,Metrics);
        //begin testing the full range
        RVI.testRange();
        //Write the output
        try {
            outputMetricsFile = args[0];        //"outputMetricsFile.txt"
            outputStepsFile = args[1];          //"outputStepsFile.txt"
            fw = new FileWriter(outputMetricsFile);
            bw = new BufferedWriter(fw);
            //write output
            bw.write(RVI.getMetrics());
            bw.close();
            
            //write steps
            fw = new FileWriter(outputStepsFile);
            bw = new BufferedWriter(fw);
            //write output
            bw.write(RVI.getAllSteps());
            bw.close();
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                    null, ex);
            throw ex;
        }
    }
    
}
