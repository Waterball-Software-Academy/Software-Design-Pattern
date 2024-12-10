package v2pull;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        StudentDataFile studentDataFile = new StudentDataFile("student.data");
        StudentLanguageBarChart barChart = new StudentLanguageBarChart(studentDataFile);
        StudentJobTitlePieChart pieChart = new StudentJobTitlePieChart(studentDataFile);
        StudentDataFileBackup dataFileBackup = new StudentDataFileBackup(studentDataFile);
        studentDataFile.register(barChart);
        studentDataFile.register(pieChart);
        studentDataFile.register(dataFileBackup);
        studentDataFile.startMonitoring();
    }
}
