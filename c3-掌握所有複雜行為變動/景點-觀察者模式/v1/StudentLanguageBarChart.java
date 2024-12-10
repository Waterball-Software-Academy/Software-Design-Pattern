package v1;

import common.BarChart;
import common.Student;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static common.Utils.count;
import static common.Utils.selectDistinct;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StudentLanguageBarChart {
    private final StudentDataFile dataFile;

    public StudentLanguageBarChart(StudentDataFile dataFile) {
        this.dataFile = dataFile;
    }

    public void renderBarChart() throws IOException {
        BarChart barChart = new BarChart();
        Collection<Student> students = dataFile.getStudents();
        List<String> x = selectDistinct(students, Student::getLanguage);
        List<Integer> y = count(x, students, Student::getLanguage);
        barChart.export("students.bar.png", x, y);
    }
}
