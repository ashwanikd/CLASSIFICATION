package Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Table {
    public LinkedList<String> columns;
    public LinkedList<Record> records;
    public int NumberOfColumns;
    public int NumberOfRecords;

    public Table(int numberofcolumns){
        NumberOfColumns = numberofcolumns;
        columns = new LinkedList<>();
        records = new LinkedList<>();
    }

    public Table(){
        records = new LinkedList<>();
    }

    public void readCSV(String filepath){
        FileInputStream fin;
        try {
            fin = new FileInputStream(filepath);
        }catch (FileNotFoundException e){
            System.out.println("file not found");
            return;
        }
        int i;
        state = 0;
        buffer = "";
        NumberOfColumns = 0;
        records = new LinkedList<>();
        columns = new LinkedList<>();
        System.out.println("Loading data from file...");
        try{
            while((i = fin.read())!=-1){
                inputChar((char)i);
            }
        }catch (IOException e){
            System.out.println("Error in reading file");
            return;
        }
        columnLength = new int[NumberOfColumns];
        for(i=0;i<NumberOfColumns;i++){
            columnLength[i] = columns.get(i).length();
        }
        records.remove(records.size()-1);
        NumberOfRecords = records.size();
        System.out.println("Successfully loded the data...");
    }
    //****************************************AUTOMATON RELATED*******************************************************//
    String buffer;
    int recordbuffer;
    int recordindexbuffer;
    int state;
    void inputChar(char c){
        if(state == 0){
            if(c == '\n'){
                state = 1;
                NumberOfColumns++;
                recordbuffer = 0;
                recordindexbuffer = 0;
                columns.add(buffer);
                records.add(new Record(NumberOfColumns));
                buffer = "";
                return;
            }else if(c == ','){
                NumberOfColumns++;
                columns.add(buffer);
                buffer = "";
                return;
            }else {
                buffer = buffer + c;
                return;
            }
        }else if(state == 1){
            if(c == '\n'){
                records.get(recordbuffer).data[recordindexbuffer++] = new Cell<Double>(Double.parseDouble(buffer));
                recordbuffer++;
                records.add(new Record(NumberOfColumns));
                recordindexbuffer = 0;
                buffer = "";
                return;
            }else if(c == ','){
                records.get(recordbuffer).data[recordindexbuffer++] = new Cell<Double>(Double.parseDouble(buffer));
                buffer = "";
                return;
            }else {
                buffer = buffer + c;
                return;
            }
        }
    }
    //****************************************************************************************************************//
    int[] columnLength;
    public void printData(){
        for(int i=0;i<NumberOfColumns;i++){
            System.out.print(columns.get(i)+" |");
        }
        System.out.println();
        for(int i=0;i<NumberOfColumns;i++){
            for(int j=0;j<columns.get(i).length()+2;j++){
                System.out.print("-");
            }
        }
        System.out.println();
        String s;
        int k;
        for(int i=0;i<records.size();i++){
            for(int j=0;j<NumberOfColumns;j++){
                 s = Double.toString((Double) records.get(i).data[j].data);
                 System.out.print(s+" |");
            }
            System.out.println();
        }
    }
    //****************************************************************************************************************//
    public void plotXY(){
        if(NumberOfColumns>3){
            System.out.println("Graph Not possible for this number of attributes");
            return;
        }
        new ScatterPlot(this);
    }
}
