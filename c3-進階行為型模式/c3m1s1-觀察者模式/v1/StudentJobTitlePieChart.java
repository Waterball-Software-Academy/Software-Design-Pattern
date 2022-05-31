package v1;

import common.PieChart;
import common.Student;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static common.Utils.count;
import static common.Utils.selectDistinct;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StudentJobTitlePieChart {
    private final StudentDataFile dataFile;

    public StudentJobTitlePieChart(StudentDataFile dataFile) {
        this.dataFile = dataFile;
    }

    public void renderPieChart() throws IOException {
        Collection<Student> students = dataFile.getStudents();
        List<String> series = selectDistinct(students, Student::getJobTitle);
        List<Integer> numbers = count(series, students, Student::getJobTitle);
        PieChart pieChart = new PieChart();
        pieChart.export("students.pie.png", series, numbers);
    }
}
