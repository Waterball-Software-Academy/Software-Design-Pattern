package v2pull;

import common.BarChart;
import common.PieChart;
import common.Student;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static common.Utils.count;
import static common.Utils.selectDistinct;
import static java.util.stream.Collectors.toList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StudentJobTitlePieChart implements StudentDataObserver {
    private final StudentDataFile dataFile;

    public StudentJobTitlePieChart(StudentDataFile dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public void update() throws IOException {
        BarChart barChart = new BarChart();
        Collection<Student> students = dataFile.getStudents();
        List<String> x = selectDistinct(students, Student::getLanguage);
        List<Integer> y = count(x, students, Student::getLanguage);
        barChart.export("students.bar.png", x, y);
    }
}
