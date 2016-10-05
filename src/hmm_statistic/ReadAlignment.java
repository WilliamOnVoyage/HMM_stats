/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm_statistic;


import java.io.FileReader;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Moliang
 */
public class ReadAlignment {

    private String fileName = "";
    private List<EventTrace> alignment = null;

    public ReadAlignment(String filename) {
        this.fileName = filename;
        this.alignment = new ArrayList<>();
    }

    public List<EventTrace> Read_alignment() throws ActivityOverFlowException {
        FileReader fileReader;
        boolean success;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            String delimiter = ",";
            String line;

            while ((line = br.readLine()) != null) {

                String[] temp_line = line.split(delimiter, -1);
                String caseID = temp_line[0];
                EventTrace et = new EventTrace(caseID);
                for (int index = 1; index < temp_line.length; index++) {
                    Event e = new Event(caseID, temp_line[index], index, index + 1, "Read");
                    et.add(e, index - 1);
                }
                alignment.add(et);
            }
            success = true;
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found!!!");
            e.printStackTrace();
            success = false;
        } catch (IOException e) {
            System.out.println("Error in csv reader!!!");
            e.printStackTrace();
            success = false;
        }
        if (success) {
            return this.alignment;
        }
        return null;
    }

    public List<EventTrace> GetAlignment() {
        return this.alignment;
    }

}
