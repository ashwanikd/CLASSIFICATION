import Data.*;

/**
 * @author ashwani kumar dwivedi
 * @version 1.0
 * @since 1.0
 */
public class Main {
    public static void main(String args[]){
        Table database = new Table();
        String path = "/home/ashwani/IdeaProjects/CLASSIFICATION/src/LS.csv";
        database.readCSV(path);
        database.plotXY();
        SupportVectorMachine.Classifier model = new SupportVectorMachine.Classifier(database);
        model.printWeightVector();
        model.testCSVFile("/home/ashwani/IdeaProjects/CLASSIFICATION/src/test.csv");
        model.plotLine();
    }
}
