package v3push;

import common.BarChart;
import common.Student;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static common.Utils.count;
import static common.Utils.selectDistinct;
import static java.util.stream.Collectors.toList;
import static org.knowm.xchart.BitmapEncoder.saveBitmap;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StudentLanguageBarChart implements StudentDataObserver {
    @Override
    public void update(Collection<Student> students) throws IOException {
        BarChart barChart = new BarChart();
        List<String> x = selectDistinct(students, Student::getLanguage);
        List<Integer> y = count(x, students, Student::getLanguage);
        barChart.export("students.bar.png", x, y);
    }
}
