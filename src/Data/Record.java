package Data;

public class Record {
    public Cell[] data;
    public int N;
    Record(int numberofcolumns){
        N = numberofcolumns;
        data = new Cell[N];
    }
    void set(int index,String value){
        data[index] = new Cell(value);
    }
    void set(int index,int value){
        data[index] = new Cell(value);
    }
    void set(int index,double value){
        data[index] = new Cell(value);
    }
    void set(int index,float value){
        data[index] = new Cell(value);
    }
    Cell get(int column){
        return data[column];
    }
}
