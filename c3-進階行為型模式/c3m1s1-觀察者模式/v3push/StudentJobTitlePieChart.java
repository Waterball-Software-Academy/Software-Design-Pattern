package v3push;

import common.BarChart;
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
public class StudentJobTitlePieChart implements StudentDataObserver {
    @Override
    public void update(Collection<Student> students) throws IOException {
        PieChart pieChart = new PieChart();
        List<String> x = selectDistinct(students, Student::getLanguage);
        List<Integer> y = count(x, students, Student::getLanguage);
        pieChart.export("students.bar.png", x, y);
    }
}
