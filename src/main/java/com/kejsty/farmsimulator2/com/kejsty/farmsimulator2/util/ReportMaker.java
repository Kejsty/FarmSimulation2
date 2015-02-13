package com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.util;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by snurkabill on 2/13/15.
 */
public class ReportMaker {

    private static final int CHART_SIZE_X = 1280;
    private static final int CHART_SIZE_Y = 720;

    public static void chart(final File target, final List<Results> data, final String chartName) {
        System.out.println("Preparing data for report");
        final XYSeriesCollection dataset = new XYSeriesCollection();
        final XYSeries xySheep = new XYSeries("NumOfSheep");
        final XYSeries xyWolfs = new XYSeries("NumOfWolfs");
        final XYSeries xyGrass = new XYSeries("NumOFGrass");
        final XYSeries xyTime = new XYSeries("TimePerIteration");
        long distributedSum = 0;
        for (Results summary : data) {
            distributedSum++;
            xySheep.add(distributedSum, summary.getNumberOfSheep());
        }
        dataset.addSeries(xySheep);

        distributedSum = 0;
        for (Results summary : data) {
            distributedSum++;
            xyWolfs.add(distributedSum, summary.getNumberOfWolfs());
        }
        dataset.addSeries(xyWolfs);

        distributedSum = 0;
        for (Results summary : data) {
            distributedSum++;
            xyGrass.add(distributedSum, summary.getNumberOfGrass());
        }
        dataset.addSeries(xyGrass);

        distributedSum = 0;
        for (Results summary : data) {
            distributedSum++;
            xyTime.add(distributedSum, summary.getTime());
        }
        dataset.addSeries(xyTime);

        System.out.println("Preparing axes");
        final NumberAxis xAxis = new NumberAxis("Time");
        final NumberAxis yAxis = new NumberAxis("Numbers");
        System.out.println("Preparing chart");
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, new XYLineAndShapeRenderer(true, true));
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setOutlinePaint(Color.DARK_GRAY);
        final JFreeChart chart = new JFreeChart(chartName, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        System.out.println("Drawing chart");
        try {
            if (target.exists()) {
                target.delete();
            }
            ChartUtilities.saveChartAsPNG(target, chart, ReportMaker.CHART_SIZE_X, ReportMaker.CHART_SIZE_Y, null);
            System.out.println("Chart written as " + target.getAbsolutePath());
        } catch (final IOException e) {
            System.out.println("Cannot write chart file." +  e);
        }
    }

}
