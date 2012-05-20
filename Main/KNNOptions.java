/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 *
 * @author ninj0x
 */
public class KNNOptions {
    public enum Mode{
        CALC, QUERY;
    }
    
    @Option(name="-k",usage="How many nearest neighbors to calculate.")
    private int k;

    @Option(name="-q",usage="Query user ID")
    private int query = -1;

    @Option(name="-n", usage="Neighborhood file, used for query only")
    private String  neighborhood_file;
    
    @Option(name="-t", usage="Threshold, used for query only")
    private double  threshold;

    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();

    public Mode getMode(){
        return (query == -1)?Mode.CALC:Mode.QUERY;
    }
    
    public String getDatabasePath() {
        return arguments.get(0);
    }

    public int getK() {
        return k;
    }

    public String getNeighborhoodFilePath() {
        return neighborhood_file;
    }

    public int getQuery() {
        return query;
    }

    public double getThreshold() {
        return threshold;
    }
    
    
}
