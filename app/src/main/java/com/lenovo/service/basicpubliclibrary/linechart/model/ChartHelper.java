package com.lenovo.service.basicpubliclibrary.linechart.model;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * Created by cx on 2016/12/19.
 */

public class ChartHelper {

    private LineChartData data;
    private int numberOfLines = 3;
    private int numberOfPoints = 7;
    float[][] randomNumbersTab = new float[numberOfLines][numberOfPoints];
    private boolean hasAxes = true;
    private boolean hasAxesNames = false;//横纵坐标名称
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private int[] COLORS = new int[]{Color.parseColor("#e9524a"), Color.parseColor("#22FFcc"), Color.parseColor("#6abf4a")};
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();
    private LineChartView chart;

    public ChartHelper(LineChartView chart, UploadRecord.SevendaysBean sevendaysData){
        this.chart = chart;
        initChart(sevendaysData);
    }

    public void initChart(UploadRecord.SevendaysBean sevendaysData) {
        try{

            randomNumbersTab[0][0] = sevendaysData.getSale().getOne();
            randomNumbersTab[0][1] = sevendaysData.getSale().getTwo();
            randomNumbersTab[0][2] = sevendaysData.getSale().getThree();
            randomNumbersTab[0][3] = sevendaysData.getSale().getFour();
            randomNumbersTab[0][4] = sevendaysData.getSale().getFive();
            randomNumbersTab[0][5] = sevendaysData.getSale().getSix();
            randomNumbersTab[0][6] = sevendaysData.getSale().getSeven();

            randomNumbersTab[1][0] = sevendaysData.getUser().getOne();
            randomNumbersTab[1][1] = sevendaysData.getUser().getTwo();
            randomNumbersTab[1][2] = sevendaysData.getUser().getThree();
            randomNumbersTab[1][3] = sevendaysData.getUser().getFour();
            randomNumbersTab[1][4] = sevendaysData.getUser().getFive();
            randomNumbersTab[1][5] = sevendaysData.getUser().getSix();
            randomNumbersTab[1][6] = sevendaysData.getUser().getSeven();

            randomNumbersTab[2][0] = sevendaysData.getPush().getOne();
            randomNumbersTab[2][1] = sevendaysData.getPush().getTwo();
            randomNumbersTab[2][2] = sevendaysData.getPush().getThree();
            randomNumbersTab[2][3] = sevendaysData.getPush().getFour();
            randomNumbersTab[2][4] = sevendaysData.getPush().getFive();
            randomNumbersTab[2][5] = sevendaysData.getPush().getSix();
            randomNumbersTab[2][6] = sevendaysData.getPush().getSeven();

            int[] pointValues = new int[numberOfLines*numberOfPoints];

            for (int i = 0; i < numberOfLines; i++) {

                for (int j = 0; j < numberOfPoints; j++) {
                    try{
                        pointValues[i * numberOfPoints + j] = (int)randomNumbersTab[i][j];
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
            Arrays.sort(pointValues);
            generateData();
            // Disable viewport recalculations, see toggleCubic() method for more info.
            chart.setViewportCalculationEnabled(false);
            int maxValue = pointValues[pointValues.length - 1];
            if(maxValue < 6){
                maxValue = 6;
                resetViewport(maxValue,0);
            }else if(maxValue > 999){
                maxValue = 999;
                resetViewport(maxValue,pointValues[0]);
            }else{
                resetViewport(maxValue,pointValues[0]);
            }
//        resetViewport(40,0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(){
        for (int i = 0; i < numberOfPoints; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel((i+1)+""));
        }
    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; j++) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setStrokeWidth(2);
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("");
                axisY.setName("");
            }
//            axisY.setTextSize(6);
            getAxisXLables();
            axisX.setValues(mAxisXValues);//填充X轴的坐标名称
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setInteractive(false);//缩放
        chart.setLineChartData(data);

    }
    private void resetViewport(int maxNum,int minNum) {
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = minNum;
        v.top = maxNum;
//        v.left = 1;
//        v.right = numberOfPoints;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
        chart.setVisibility(View.VISIBLE);
    }

}
