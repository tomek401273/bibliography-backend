package com.tgrajkowski.service;

import com.tgrajkowski.model.job.JobDaoIml;
import com.tgrajkowski.model.job.JobDto;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class JFreeChartServiceTime {
    @Autowired
    private JobDaoIml jobDaoIml;

    public void  create() {
        final XYDataset dataset = createDataset1();
        Font font =new Font("Tahoma", Font.BOLD, 14);
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Bibliography Usage",
                "Time",
                "Count Checked Diploma Work",
                dataset
        );

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis axis2 = new NumberAxis("Count Login Users");
        axis2.setLabelFont(font);
        axis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);

        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());

        final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setSeriesPaint(0, Color.black);
        renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        plot.setRenderer(1, renderer2);
        plot.setBackgroundPaint(new Color(0, 0, 0, 0));

        final DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

        chart.setBackgroundPaint(new Color(0, 0, 0, 0));
        try (OutputStream out= new FileOutputStream("chart.png")){
            ChartUtils.writeChartAsPNG(out, chart, 900,500);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private XYDataset createDataset1() {

        final TimeSeries s1 = new TimeSeries("Random Data 1");
        List<JobDto> jobDtos = jobDaoIml.findPipelinedStatements();
        for (JobDto jobDto: jobDtos) {
            s1.add(new Day(jobDto.getDate()), jobDto.getCount());
        }

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;

    }

    private XYDataset createDataset2() {
        final TimeSeries s2 = new TimeSeries("Count Login Users");
        List<JobDto> jobDtos = jobDaoIml.findPipelinedStatements();
        for (JobDto jobDto: jobDtos) {
            s2.add(new Day(jobDto.getDate()), (int)(Math.random()*100)+1);
        }

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s2);

        return dataset;

    }
}
