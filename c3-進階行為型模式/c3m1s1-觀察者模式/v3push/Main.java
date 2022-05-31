package v3push;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        StudentDataFile studentDataFile = new StudentDataFile("student.data");
        StudentLanguageBarChart barChart = new StudentLanguageBarChart();
        StudentJobTitlePieChart pieChart = new StudentJobTitlePieChart();
        StudentDataFileBackup dataFileBackup = new StudentDataFileBackup();
        studentDataFile.register(barChart);
        studentDataFile.register(pieChart);
        studentDataFile.register(dataFileBackup);
        studentDataFile.startMonitoring();
    }
}
