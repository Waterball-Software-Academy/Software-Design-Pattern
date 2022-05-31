package v3push;

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
public class StudentJobTitlePieChart implements StudentDataObserver {
    @Override
    public void update(Collection<Student> students) throws IOException {
        BarChart barChart = new BarChart();
        List<String> x = selectDistinct(students, Student::getLanguage);
        List<Integer> y = count(x, students, Student::getLanguage);
        barChart.export("students.bar.png", x, y);
    }
}
