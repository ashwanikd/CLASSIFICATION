import Data.*;

/**
 *
 */
public class Main {
    public static void main(String args[]){
        Table database = new Table();
        String path = "/home/ashwani/IdeaProjects/CLASSIFICATION/src/heart.csv";
        database.readCSV(path);
        //database.plotXY();
        SupportVectorMachine.Classifier model = new SupportVectorMachine.Classifier(database);
        //model.printWeightVector();
        //model.testCSVFile("/home/ashwani/IdeaProjects/CLASSIFICATION/src/test.csv");
        //model.plotLine();
    }
}
