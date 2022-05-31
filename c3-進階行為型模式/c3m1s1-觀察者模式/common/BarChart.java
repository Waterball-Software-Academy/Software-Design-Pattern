package common;

import common.ReadStudents;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.List;

import static org.knowm.xchart.BitmapEncoder.saveBitmap;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class BarChart {
    public void export(String fileName, List<String> x, List<Integer> y) throws IOException {
        CategoryChart chart = categoryChart();
        chart.addSeries("histogram", x, y);
        saveBitmap(chart, fileName, BitmapEncoder.BitmapFormat.PNG);
    }

    private CategoryChart categoryChart() {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Bar Chart").xAxisTitle("Language").yAxisTitle("Count").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setAvailableSpaceFill(.96);
        chart.getStyler().setOverlapped(true);
        return chart;
    }

}
