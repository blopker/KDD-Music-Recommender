package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
        private List<Id> idList;


        public Parser(String fileName) {

        }

        private void read(String fileName) {
                System.out.println("Reading database...");
            StringBuilder text = new StringBuilder();
            String NL = System.getProperty("line.separator");
            try {
                Scanner scanner = new Scanner(new FileInputStream(fileName));
              while (scanner.hasNextLine()){
                text.append(scanner.nextLine() + NL);
              }
              scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to open database");
                        e.printStackTrace();
                }
            System.out.println("Database loaded.");
        }

        private void format(String dataLine) {
                if(dataLine.contains("|")){
                        formatId(dataLine);
                } else {
                        formatInfo(dataLine);
                }
        }

        private void formatInfo(String dataLine) {
                String[] splitData = dataLine.split("\t");
                Id id = idList.get(idList.size()-1);
                id.addInfo(strArrayToIntArray(splitData));
        }

        private void formatId(String dataLine) {
                String[] splitData = dataLine.split("|");
                idList.add(new Id(strArrayToIntArray(splitData)));
        }

        private int[] strArrayToIntArray(String[] line) {
                int[] infoLine = new int[line.length];
                for (int i = 0; i < line.length; i++) {
                        int info = (line[i].toLowerCase() == "none")?-1:Integer.                                                                                                                                                             valueOf(line[i]).intValue();
                        infoLine[i] = info;
                }
                return infoLine;
        }

        public class Id{
                List<int[]> info;
                int id;
                public Id(int[] idArray){
                        id = idArray[0];
                }

                public void addInfo(int[] infoArray) {
                        if(info == null){
                                info = new ArrayList<int[]>();
                        }
                        info.add(infoArray);
                }

                public List<int[]> getInfo() {
                        return (info == null)?new ArrayList<int[]>():info;
                }
        }
}

