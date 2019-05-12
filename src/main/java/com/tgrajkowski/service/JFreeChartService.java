package com.tgrajkowski.service;

import com.tgrajkowski.model.job.JobDaoIml;
import com.tgrajkowski.model.job.JobDto;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class JFreeChartService {
    @Autowired
    private JobDaoIml jobDaoIml;

    public void createChart() {
        List<JobDto> jobDtos =  jobDaoIml.findPipelinedStatements();
        XYSeries series1 = new XYSeries("series1");
        XYSeries series2 = new XYSeries("series2");

//        for (JobDto jobDto: jobDtos) {
//            System.out.println(jobDto.toString());
//            series1.add(jobDto.getCount(), jobDto.getCount());
//            series2.add(jobDto.getDate().getTime(), jobDto.getCount()*2);
//
//        }

        for (int i =0; i<100; i++) {
            series1.add(i, i+2);
        }

        XYSeriesCollection dataSet1 = new XYSeriesCollection();
//        XYSeriesCollection dataSet2 = new XYSeriesCollection();
        dataSet1.addSeries(series1);
//        dataSet2.addSeries(series2);

        XYPlot plot = new XYPlot();
        plot.setDataset(0, dataSet1);
//        plot.setDataset(1, dataSet2);
        plot.mapDatasetToRangeAxis(0,0);
        JFreeChart chart = new JFreeChart("tiele", plot);

        try (OutputStream out= new FileOutputStream("chart.png")){
            ChartUtils.writeChartAsPNG(out, chart, 900,500);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createChart2() {
        XYSeries series1 = new XYSeries("series1");
        XYSeries series2 = new XYSeries("series2");
//        series1.add(1000, 1000);
//        series1.add(1150, 1150);
//        series1.add(1250, 1250);
//
//        series2.add(1000, 111250);
//        series2.add(1150, 211250);
//        series2.add(1250, 311250);
        TimeSeries timeSeries1 = new TimeSeries("Series1");
        TimeSeries timeSeries2 = new TimeSeries("Series1");

        List<JobDto> jobDtos =  jobDaoIml.findPipelinedStatements();

                for (JobDto jobDto: jobDtos) {
            System.out.println(jobDto.toString());
//            series1.add(jobDto.getDate().getTime(), jobDto.getCount());
//            series2.add(jobDto.getDate().getTime(), jobDto.getCount()*2);
            timeSeries1.add(new Day(jobDto.getDate()), jobDto.getCount());
            timeSeries2.add(new Day(jobDto.getDate()), jobDto.getCount()*2);
        }


        //create the datasets
//        XYSeriesCollection dataset1 = new XYSeriesCollection();
//        XYSeriesCollection dataset2 = new XYSeriesCollection();
//        dataset1.addSeries(series1);
//        dataset2.addSeries(series2);

        TimeSeriesCollection timeSeriesCollection1 = new TimeSeriesCollection();
        TimeSeriesCollection timeSeriesCollection2 = new TimeSeriesCollection();
        timeSeriesCollection1.addSeries(timeSeries1);
        timeSeriesCollection2.addSeries(timeSeries2);

        //construct the plot
        XYPlot plot = new XYPlot();
        plot.setDataset(0, timeSeriesCollection1);
        plot.setDataset(1, timeSeriesCollection2);


        //customize the plot with renderers and axis
        plot.setRenderer(0, new XYSplineRenderer());//use default fill paint for first series
        XYSplineRenderer splinerenderer = new XYSplineRenderer();
        splinerenderer.setSeriesFillPaint(0, Color.BLUE);
        plot.setRenderer(1, splinerenderer);
        plot.setRangeAxis(0, new NumberAxis("Series 1"));
        plot.setRangeAxis(1, new NumberAxis("Series 2"));
        plot.setDomainAxis(new NumberAxis("X Axis"));

        //Map the data to the appropriate axis
//        plot.mapDatasetToRangeAxis(0, 0);
//        plot.mapDatasetToRangeAxis(1, 1);

        //generate the chart
        Font font = new Font("Dialog", Font.PLAIN, 25);
        JFreeChart jFreeChart = ChartFactory.createTimeSeriesChart("title2", "Date", "number", timeSeriesCollection1);
        JFreeChart chart = new JFreeChart("MyPlot", font, plot, true);
        chart.setBackgroundPaint(Color.WHITE);
        JPanel chartPanel = new ChartPanel(chart);

        try (OutputStream out= new FileOutputStream("chart.png")){
            ChartUtils.writeChartAsPNG(out, jFreeChart, 900,500);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
