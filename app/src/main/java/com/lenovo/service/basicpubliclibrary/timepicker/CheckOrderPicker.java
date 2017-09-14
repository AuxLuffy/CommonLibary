package com.lenovo.service.basicpubliclibrary.timepicker;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


/**
 * 日期选择器
 *
 * @version 2015 /12/14
 */
public class CheckOrderPicker extends WheelPicker {
    private RequireAppoint requireAppoint;
    private ArrayList<String> years = new ArrayList<String>();
    private ArrayList<String> months = new ArrayList<String>();
    private ArrayList<String> days = new ArrayList<String>();
//    private ArrayList<String> hours = new ArrayList<>();
    private OnDatePickListener onDatePickListener;
    private String yearLabel = "年", monthLabel = "月", dayLabel = "日";// hourLabel = "时";
    private int selectedYearIndex = 0, selectedMonthIndex = 0, selectedDayIndex = 0;//selectedHourIndex;

    /**
     * The enum Mode.
     */
    public enum Mode {
        /**
         * 年月日
         */
        YEAR_MONTH_DAY,
        /**
         * 年月
         */
        YEAR_MONTH,
        /**
         * 月日
         */
        MONTH_DAY
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     */
    public CheckOrderPicker(Activity activity, RequireAppoint requireAppoint) {
        this(activity, Mode.YEAR_MONTH_DAY);
        this.requireAppoint = requireAppoint;
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     * @param mode     the mode
     */
    public CheckOrderPicker(Activity activity, Mode mode) {
        super(activity);
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int currentMonth = (now.get(Calendar.MONTH) + 1);
        int currentDay = now.get(Calendar.DAY_OF_MONTH);
        for (int i = currentYear; i <= currentYear+1; i++) {
            years.add(String.valueOf(i));
        }
        for (int i = currentMonth; i <= currentMonth+12; i++) {
            if(i <= 12){
                months.add(DateUtils.fillZero(i));
            }else{
                months.add(DateUtils.fillZero(i%12));
            }
        }

        for (int i = 1; i <= 31; i++) {
            days.add(DateUtils.fillZero(i));
        }
//        for (int i = 1; i <= 24; i++) {
//            hours.add(DateUtils.fillZero(i));
//        }
    }

    /**
     * Sets label.
     *
     * @param yearLabel  the year label
     * @param monthLabel the month label
     * @param dayLabel   the day label
     */
    public void setLabel(String yearLabel, String monthLabel, String dayLabel, String hourLabel) {
        this.yearLabel = yearLabel;
        this.monthLabel = monthLabel;
        this.dayLabel = dayLabel;
//        this.hourLabel = hourLabel;
    }

    /**
     * Sets range.
     *
     * @param startYear the start year
     * @param endYear   the end year
     * @see Mode#YEAR_MONTH_DAY Mode#YEAR_MONTH_DAY
     * @see Mode#YEAR_MONTH Mode#YEAR_MONTH
     */
    public void setRange(int startYear, int endYear) {
        years.clear();
        for (int i = startYear; i <= endYear; i++) {
            years.add(String.valueOf(i));
        }
    }

    private int findItemIndex(ArrayList<String> items, int item) {
        //折半查找有序元素的索引
        int index = Collections.binarySearch(items, item, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String lhsStr = lhs.toString();
                String rhsStr = rhs.toString();
                lhsStr = lhsStr.startsWith("0") ? lhsStr.substring(1) : lhsStr;
                rhsStr = rhsStr.startsWith("0") ? rhsStr.substring(1) : rhsStr;
                return Integer.parseInt(lhsStr) - Integer.parseInt(rhsStr);
            }
        });
        if (index < 0) {
            index = 0;
        }
        return index;
    }


    @Override
    protected View initContentView() {
        LinearLayout layoutContain = new LinearLayout(activity);
        layoutContain.setOrientation(LinearLayout.VERTICAL);


        if (false) {//requireAppoint.isRechckDateNess
            TextView tvFujian = new TextView(activity);
            tvFujian.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tvFujian.setTextSize(16);
            tvFujian.setPadding(DisplayUtil.dip2px(16), 16, 16, 25);
            tvFujian.setText("预约复检时间");
            tvFujian.setGravity(Gravity.CENTER);
            layoutContain.addView(tvFujian);
//            layoutContain.addView(getFujianView());
        }
        if (true) {//requireAppoint.isNextStepDateNesss
            TextView tvNextItem = new TextView(activity);
            tvNextItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tvNextItem.setTextSize(16);
            tvNextItem.setPadding(DisplayUtil.dip2px(16), 16, 16, 25);
            tvNextItem.setText("请预约" + "next" + "时间");//requireAppoint.nextStepName
            tvNextItem.setGravity(Gravity.CENTER);
            layoutContain.addView(tvNextItem);
//            layoutContain.addView(getNextAppointView());

        }



        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        WheelView yearView = new WheelView(activity);
        yearView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        yearView.setTextSize(textSize);
        yearView.setTextColor(textColorNormal, textColorFocus);
        yearView.setLineVisible(lineVisible);
        yearView.setLineColor(lineColor);
        yearView.setOffset(offset);
        layout.addView(yearView);
        TextView yearTextView = new TextView(activity);
        yearTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        yearTextView.setTextSize(textSize);
        yearTextView.setTextColor(textColorFocus);
        if (!TextUtils.isEmpty(yearLabel)) {
            yearTextView.setText(yearLabel);
        }
        layout.addView(yearTextView);
        WheelView monthView = new WheelView(activity);
        monthView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        monthView.setTextSize(textSize);
        monthView.setTextColor(textColorNormal, textColorFocus);
        monthView.setLineVisible(lineVisible);
        monthView.setLineColor(lineColor);
        monthView.setOffset(offset);
        layout.addView(monthView);
        TextView monthTextView = new TextView(activity);
        monthTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        monthTextView.setTextSize(textSize);
        monthTextView.setTextColor(textColorFocus);
        if (!TextUtils.isEmpty(monthLabel)) {
            monthTextView.setText(monthLabel);
        }
        layout.addView(monthTextView);
        final WheelView dayView = new WheelView(activity);
        dayView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        dayView.setTextSize(textSize);
        dayView.setTextColor(textColorNormal, textColorFocus);
        dayView.setLineVisible(lineVisible);
        dayView.setLineColor(lineColor);
        dayView.setOffset(offset);
        layout.addView(dayView);
        TextView dayTextView = new TextView(activity);
        dayTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        dayTextView.setTextSize(textSize);
        dayTextView.setTextColor(textColorFocus);
        if (!TextUtils.isEmpty(dayLabel)) {
            dayTextView.setText(dayLabel);
        }
        layout.addView(dayTextView);

        //设置小时
//        final WheelView hourView = new WheelView(activity);
//        hourView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        hourView.setTextSize(textSize);
//        hourView.setTextColor(textColorNormal, textColorFocus);
//        hourView.setLineVisible(lineVisible);
//        hourView.setLineColor(lineColor);
//        hourView.setOffset(offset);
//        layout.addView(hourView);
//        TextView hourTextView = new TextView(activity);
//        hourTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        hourTextView.setTextSize(textSize);
//        hourTextView.setTextColor(textColorFocus);
//        if (!TextUtils.isEmpty(hourLabel)) {
//            hourTextView.setText(hourLabel);
//        }
//        layout.addView(hourTextView);


        yearView.setItems(years);
        yearView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedYearIndex = selectedIndex;
                //需要根据年份及月份动态计算天数
                days.clear();
                int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(item), stringToYearMonthDay(months.get(selectedMonthIndex)));
                for (int i = 1; i <= maxDays; i++) {
                    days.add(DateUtils.fillZero(i));
                }
                if (selectedDayIndex >= maxDays) {
                    //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
                    selectedDayIndex = days.size() - 1;
                }
                dayView.setItems(days, selectedDayIndex);
            }
        });

        if (!TextUtils.isEmpty(monthLabel)) {
            monthTextView.setText(monthLabel);
        }
        if (selectedMonthIndex == 0) {
            monthView.setItems(months);
        } else {
            monthView.setItems(months, selectedMonthIndex);
        }
        monthView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedMonthIndex = selectedIndex;
                //年月日或年月模式下，需要根据年份及月份动态计算天数
                days.clear();
                int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(years.get(selectedYearIndex)), stringToYearMonthDay(item));
                Calendar now = Calendar.getInstance();
                int currentDay = now.get(Calendar.DAY_OF_MONTH);
                for (int i = currentDay; i <= currentDay+maxDays; i++) {
                    if(i <= maxDays){
                        days.add(DateUtils.fillZero(i));
                    }else{
                        days.add(DateUtils.fillZero(i%maxDays));
                    }
                }
//                for (int i = 1; i <= maxDays; i++) {
//                    days.add(DateUtils.fillZero(i));
//                }
                if (selectedDayIndex >= maxDays) {
                    //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
                    selectedDayIndex = days.size() - 1;
                }
                dayView.setItems(days, selectedDayIndex);
            }
        });

        dayView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedDayIndex = selectedIndex;
            }
        });

//        hourView.setItems(hours);
//        hourView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override
//            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
//                selectedHourIndex = selectedIndex;
//            }
//        });

        layoutContain.addView(layout);
        return layoutContain;
    }

    private int stringToYearMonthDay(String text) {
        if (text.startsWith("0")) {
            //截取掉前缀0以便转换为整数
            text = text.substring(1);
        }
        return Integer.parseInt(text);
    }

    @Override
    protected void setContentViewAfter(View contentView) {
        super.setContentViewAfter(contentView);
        super.setOnConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm() {
                if (onDatePickListener != null) {
                    String year = years.get(selectedYearIndex);
                    String month = months.get(selectedMonthIndex);
                    String day = days.get(selectedDayIndex);
//                    String hour = hours.get(selectedHourIndex);
                    String hour = "0";
                    ((OnYMDhPickListener) onDatePickListener).onDatePicked(year, month, day, hour);
                }
            }
        });
    }

    public void setOnDateSelListner(OnYMDhPickListener listner) {
        this.onDatePickListener = listner;
    }


    /**
     * The interface On date pick listener.
     */
    protected interface OnDatePickListener {

    }

    /**
     * The interface On year month day pick listener.
     */
    public interface OnYMDhPickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param year  the year
         * @param month the month
         * @param day   the day
         */
        void onDatePicked(String year, String month, String day, String hour);

    }

    /**
     * The interface On year month pick listener.
     */
    public interface OnYearMonthPickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param year  the year
         * @param month the month
         */
        void onDatePicked(String year, String month);

    }

    /**
     * The interface On month day pick listener.
     */
    public interface OnMonthDayPickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param month the month
         * @param day   the day
         */
        void onDatePicked(String month, String day);

    }

}
//private ArrayList<String> years = new ArrayList<String>();
//private ArrayList<String> months = new ArrayList<String>();
//private ArrayList<String> days = new ArrayList<String>();
//private RequireAppoint requireAppoint;
//private OnDatePickListener onDatePickListener;
//private String yearLabel = "年", monthLabel = "月", dayLabel = "日";
//private int fujianYearIndex = 0, fujianMonthIndex = 0, fujianDayIndex = 0;
//private int nextYearIndex = 0, nextMonthIndex = 0, nextDayIndex = 0;
//private Mode mode = Mode.YEAR_MONTH_DAY;
//
///**
// * The enum Mode.
// */
//public enum Mode {
//    /**
//     * 年月日
//     */
//    YEAR_MONTH_DAY,
//    /**
//     * 年月
//     */
//    YEAR_MONTH,
//    /**
//     * 月日
//     */
//    MONTH_DAY
//}
//
//    /**
//     * Instantiates a new Date picker.
//     */
//    public CheckOrderPicker(Activity activity, RequireAppoint requireAppoint) {
//        this(activity, Mode.YEAR_MONTH_DAY, requireAppoint);
//    }
//
//    /**
//     * Instantiates a new Date picker.
//     */
//    public CheckOrderPicker(Activity activity, Mode mode, RequireAppoint requireAppoint) {
//        super(activity);
//        this.requireAppoint = requireAppoint;
//        this.mode = mode;
//        Calendar now = Calendar.getInstance();
//        int currentYear = now.get(Calendar.YEAR);
//        int currentMonth = (now.get(Calendar.MONTH) + 1);
//        int currentDay = now.get(Calendar.DAY_OF_MONTH);
//
////        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
////        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
////        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
////        System.out.println("分: " + now.get(Calendar.MINUTE));
////        System.out.println("秒: " + now.get(Calendar.SECOND));
////        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
////        System.out.println(now.getTime());
////        TestUtil.showToast(currentYear+","+currentMonth+","+currentDay);
//        TestUtil.showLog(currentYear+","+currentMonth+","+currentDay);
//        for (int i = currentYear; i <= currentYear+1; i++) {
//            years.add(String.valueOf(i));
//        }
//        for (int i = currentMonth; i <= currentMonth+12; i++) {
//            if(i <= 12){
//                months.add(DateUtils.fillZero(i));
//            }else{
//                months.add(DateUtils.fillZero(i%12));
//            }
////            months.add(DateUtils.fillZero(i));
//        }
//        for (int i = currentDay; i <= currentDay+31; i++) {
//            if(i <= 31){
//                days.add(DateUtils.fillZero(i));
//            }else{
//                days.add(DateUtils.fillZero(i%31));
//            }
//        }
//    }
//    /**
//     * 获取当月的 天数
//     * */
//    public static int getCurrentMonthDay() {
//
//        Calendar a = Calendar.getInstance();
//        a.set(Calendar.DATE, 1);
//        a.roll(Calendar.DATE, -1);
//        int maxDate = a.get(Calendar.DATE);
//        return maxDate;
//    }
//    /**
//     * Sets label.
//     *
//     * @param yearLabel  the year label
//     * @param monthLabel the month label
//     * @param dayLabel   the day label
//     */
//    public void setLabel(String yearLabel, String monthLabel, String dayLabel) {
//        this.yearLabel = yearLabel;
//        this.monthLabel = monthLabel;
//        this.dayLabel = dayLabel;
//    }
//
//    /**
//     * Sets range.
//     *
//     * @param startYear the start year
//     * @param endYear   the end year
//     * @see Mode#YEAR_MONTH_DAY Mode#YEAR_MONTH_DAY
//     * @see Mode#YEAR_MONTH Mode#YEAR_MONTH
//     */
//    public void setRange(int startYear, int endYear) {
//        years.clear();
//        for (int i = startYear; i <= endYear; i++) {
//            years.add(String.valueOf(i));
//        }
//    }
//
//    private int findItemIndex(ArrayList<String> items, int item) {
//        //折半查找有序元素的索引
//        int index = Collections.binarySearch(items, item, new Comparator<Object>() {
//            @Override
//            public int compare(Object lhs, Object rhs) {
//                String lhsStr = lhs.toString();
//                String rhsStr = rhs.toString();
//                lhsStr = lhsStr.startsWith("0") ? lhsStr.substring(1) : lhsStr;
//                rhsStr = rhsStr.startsWith("0") ? rhsStr.substring(1) : rhsStr;
//                return Integer.parseInt(lhsStr) - Integer.parseInt(rhsStr);
//            }
//        });
//        if (index < 0) {
//            index = 0;
//        }
//        return index;
//    }
//
//    /**
//     * Sets selected item.
//     *
//     * @param year  the year
//     * @param month the month
//     * @param day   the day
//     */
//    public void setSelectedItem(int year, int month, int day) {
//        fujianYearIndex = findItemIndex(years, year);
//        fujianMonthIndex = findItemIndex(months, month);
//        fujianDayIndex = findItemIndex(days, day);
//    }
//
//    /**
//     * Sets selected item.
//     *
//     * @param yearOrMonth the year or month
//     * @param monthOrDay  the month or day
//     */
//    public void setSelectedItem(int yearOrMonth, int monthOrDay) {
//        if (mode.equals(Mode.MONTH_DAY)) {
//            fujianMonthIndex = findItemIndex(months, yearOrMonth);
//            fujianDayIndex = findItemIndex(days, monthOrDay);
//        } else {
//            fujianYearIndex = findItemIndex(years, yearOrMonth);
//            fujianMonthIndex = findItemIndex(months, monthOrDay);
//        }
//    }
//
//    /**
//     * Sets on date pick listener.
//     *
//     * @param listener the listener
//     */
//    public void setOnDatePickListener(OnDatePickListener listener) {
//        this.onDatePickListener = listener;
//    }
//
//    @Override
//    protected View initContentView() {
//
//
//        LinearLayout layoutContain = new LinearLayout(activity);
//        layoutContain.setOrientation(LinearLayout.VERTICAL);
//
//
//        if (requireAppoint.isRechckDateNess) {
//            TextView tvFujian = new TextView(activity);
//            tvFujian.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            tvFujian.setTextSize(16);
//            tvFujian.setPadding(DisplayUtil.dip2px(16), 16, 16, 25);
//            tvFujian.setText("预约复检时间");
//            tvFujian.setGravity(Gravity.CENTER);
//            layoutContain.addView(tvFujian);
//            layoutContain.addView(getFujianView());
//        }
//        if (requireAppoint.isNextStepDateNesss) {
//            TextView tvNextItem = new TextView(activity);
//            tvNextItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            tvNextItem.setTextSize(16);
//            tvNextItem.setPadding(DisplayUtil.dip2px(16), 16, 16, 25);
//            tvNextItem.setText("请预约" + requireAppoint.nextStepName + "时间");
//            tvNextItem.setGravity(Gravity.CENTER);
//            layoutContain.addView(tvNextItem);
//            layoutContain.addView(getNextAppointView());
//
//        }
//        return layoutContain;
//    }
//
//
//    private View getFujianView() {
//        LinearLayout layout = new LinearLayout(activity);
//        layout.setOrientation(LinearLayout.HORIZONTAL);
//        layout.setGravity(Gravity.CENTER);
//        WheelView yearView = new WheelView(activity);
//        yearView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        yearView.setTextSize(textSize);
//        yearView.setTextColor(textColorNormal, textColorFocus);
//        yearView.setLineVisible(lineVisible);
//        yearView.setLineColor(lineColor);
//        yearView.setOffset(offset);
//        layout.addView(yearView);
//        TextView yearTextView = new TextView(activity);
//        yearTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        yearTextView.setTextSize(textSize);
//        yearTextView.setTextColor(textColorFocus);
//        if (!TextUtils.isEmpty(yearLabel)) {
//            yearTextView.setText(yearLabel);
//        }
//        layout.addView(yearTextView);
//        WheelView monthView = new WheelView(activity);
//        monthView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        monthView.setTextSize(textSize);
//        monthView.setTextColor(textColorNormal, textColorFocus);
//        monthView.setLineVisible(lineVisible);
//        monthView.setLineColor(lineColor);
//        monthView.setOffset(offset);
//        layout.addView(monthView);
//        TextView monthTextView = new TextView(activity);
//        monthTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        monthTextView.setTextSize(textSize);
//        monthTextView.setTextColor(textColorFocus);
//        if (!TextUtils.isEmpty(monthLabel)) {
//            monthTextView.setText(monthLabel);
//        }
//        layout.addView(monthTextView);
//        final WheelView dayView = new WheelView(activity);
//        dayView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        dayView.setTextSize(textSize);
//        dayView.setTextColor(textColorNormal, textColorFocus);
//        dayView.setLineVisible(lineVisible);
//        dayView.setLineColor(lineColor);
//        dayView.setOffset(offset);
//        layout.addView(dayView);
//        TextView dayTextView = new TextView(activity);
//        dayTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        dayTextView.setTextSize(textSize);
//        dayTextView.setTextColor(textColorFocus);
//        if (!TextUtils.isEmpty(dayLabel)) {
//            dayTextView.setText(dayLabel);
//        }
//        layout.addView(dayTextView);
//        if (mode.equals(Mode.YEAR_MONTH)) {
//            dayView.setVisibility(View.GONE);
//            dayTextView.setVisibility(View.GONE);
//        } else if (mode.equals(Mode.MONTH_DAY)) {
//            yearView.setVisibility(View.GONE);
//            yearTextView.setVisibility(View.GONE);
//        }
//        if (!mode.equals(Mode.MONTH_DAY)) {
//            if (!TextUtils.isEmpty(yearLabel)) {
//                yearTextView.setText(yearLabel);
//            }
//            if (fujianYearIndex == 0) {
//                yearView.setItems(years);
//            } else {
//                yearView.setItems(years, fujianYearIndex);
//            }
//            yearView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//                @Override
//                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
//                    fujianYearIndex = selectedIndex;
//                    //需要根据年份及月份动态计算天数
//                    days.clear();
//                    int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(item), stringToYearMonthDay(months.get(fujianMonthIndex)));
//                    for (int i = 1; i <= maxDays; i++) {
//                        days.add(DateUtils.fillZero(i));
//                    }
//                    if (fujianDayIndex >= maxDays) {
//                        //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
//                        fujianDayIndex = days.size() - 1;
//                    }
//                    dayView.setItems(days, fujianDayIndex);
//                    dealTime();
//                }
//            });
//        }
//        if (!TextUtils.isEmpty(monthLabel)) {
//            monthTextView.setText(monthLabel);
//        }
//        if (fujianMonthIndex == 0) {
//            monthView.setItems(months);
//        } else {
//            monthView.setItems(months, fujianMonthIndex);
//        }
//        monthView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override
//            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
//                fujianMonthIndex = selectedIndex;
//                if (!mode.equals(Mode.YEAR_MONTH)) {
//                    //年月日或年月模式下，需要根据年份及月份动态计算天数
//                    days.clear();
//                    int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(years.get(fujianYearIndex)), stringToYearMonthDay(item));
//                    Calendar now = Calendar.getInstance();
//                    int currentDay = now.get(Calendar.DAY_OF_MONTH);
//                    for (int i = currentDay; i <= currentDay+maxDays; i++) {
//                        if(i <= maxDays){
//                            days.add(DateUtils.fillZero(i));
//                        }else{
//                            days.add(DateUtils.fillZero(i%maxDays));
//                        }
//                    }
//
////                    for (int i = 1; i <= maxDays; i++) {
////                        days.add(DateUtils.fillZero(i));
////                    }
//                    if (fujianDayIndex >= maxDays) {
//                        //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
//                        fujianDayIndex = days.size() - 1;
//                    }
//                    dayView.setItems(days, fujianDayIndex);
//                    dealTime();
//                }
//            }
//        });
//        if (!mode.equals(Mode.YEAR_MONTH)) {
//            if (!TextUtils.isEmpty(dayLabel)) {
//                dayTextView.setText(dayLabel);
//            }
//            if (fujianDayIndex == 0) {
//                dayView.setItems(days);
//            } else {
//                dayView.setItems(days, fujianDayIndex);
//            }
//            dayView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//                @Override
//                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
//                    fujianDayIndex = selectedIndex;
//                    dealTime();
//                }
//            });
//        }
//        return layout;
//    }
//
//
//    private View getNextAppointView() {
//        LinearLayout layout = new LinearLayout(activity);
//        layout.setOrientation(LinearLayout.HORIZONTAL);
//        layout.setGravity(Gravity.CENTER);
//        WheelView yearView = new WheelView(activity);
//        yearView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        yearView.setTextSize(textSize);
//        yearView.setTextColor(textColorNormal, textColorFocus);
//        yearView.setLineVisible(lineVisible);
//        yearView.setLineColor(lineColor);
//        yearView.setOffset(offset);
//        layout.addView(yearView);
//        TextView yearTextView = new TextView(activity);
//        yearTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        yearTextView.setTextSize(textSize);
//        yearTextView.setTextColor(textColorFocus);
//        if (!TextUtils.isEmpty(yearLabel)) {
//            yearTextView.setText(yearLabel);
//        }
//        layout.addView(yearTextView);
//        WheelView monthView = new WheelView(activity);
//        monthView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        monthView.setTextSize(textSize);
//        monthView.setTextColor(textColorNormal, textColorFocus);
//        monthView.setLineVisible(lineVisible);
//        monthView.setLineColor(lineColor);
//        monthView.setOffset(offset);
//        layout.addView(monthView);
//        TextView monthTextView = new TextView(activity);
//        monthTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        monthTextView.setTextSize(textSize);
//        monthTextView.setTextColor(textColorFocus);
//        if (!TextUtils.isEmpty(monthLabel)) {
//            monthTextView.setText(monthLabel);
//        }
//        layout.addView(monthTextView);
//        final WheelView dayView = new WheelView(activity);
//        dayView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        dayView.setTextSize(textSize);
//        dayView.setTextColor(textColorNormal, textColorFocus);
//        dayView.setLineVisible(lineVisible);
//        dayView.setLineColor(lineColor);
//        dayView.setOffset(offset);
//        layout.addView(dayView);
//        TextView dayTextView = new TextView(activity);
//        dayTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//        dayTextView.setTextSize(textSize);
//        dayTextView.setTextColor(textColorFocus);
//        if (!TextUtils.isEmpty(dayLabel)) {
//            dayTextView.setText(dayLabel);
//        }
//        layout.addView(dayTextView);
//        if (mode.equals(Mode.YEAR_MONTH)) {
//            dayView.setVisibility(View.GONE);
//            dayTextView.setVisibility(View.GONE);
//        } else if (mode.equals(Mode.MONTH_DAY)) {
//            yearView.setVisibility(View.GONE);
//            yearTextView.setVisibility(View.GONE);
//        }
//        if (!mode.equals(Mode.MONTH_DAY)) {
//            if (!TextUtils.isEmpty(yearLabel)) {
//                yearTextView.setText(yearLabel);
//            }
//            if (nextYearIndex == 0) {
//                yearView.setItems(years);
//            } else {
//                yearView.setItems(years, nextYearIndex);
//            }
//            yearView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//                @Override
//                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
//                    nextYearIndex = selectedIndex;
//                    //需要根据年份及月份动态计算天数
//                    days.clear();
//                    int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(item), stringToYearMonthDay(months.get(nextMonthIndex)));
//                    for (int i = 1; i <= maxDays; i++) {
//                        days.add(DateUtils.fillZero(i));
//                    }
//                    if (nextDayIndex >= maxDays) {
//                        //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
//                        nextDayIndex = days.size() - 1;
//                    }
//                    dayView.setItems(days, nextDayIndex);
//                    dealTime();
//                }
//            });
//        }
//        if (!TextUtils.isEmpty(monthLabel)) {
//            monthTextView.setText(monthLabel);
//        }
//        if (nextMonthIndex == 0) {
//            monthView.setItems(months);
//        } else {
//            monthView.setItems(months, nextMonthIndex);
//        }
//        monthView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override
//            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
//                nextMonthIndex = selectedIndex;
//                if (!mode.equals(Mode.YEAR_MONTH)) {
//                    //年月日或年月模式下，需要根据年份及月份动态计算天数
//                    days.clear();
//                    int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(years.get(nextYearIndex)), stringToYearMonthDay(item));
//                    Calendar now = Calendar.getInstance();
//                    int currentDay = now.get(Calendar.DAY_OF_MONTH);
//                    for (int i = currentDay; i <= currentDay+maxDays; i++) {
//                        if(i <= maxDays){
//                            days.add(DateUtils.fillZero(i));
//                        }else{
//                            days.add(DateUtils.fillZero(i%maxDays));
//                        }
//                    }
////                    for (int i = 1; i <= maxDays; i++) {
////                        days.add(DateUtils.fillZero(i));
////                    }
//                    if (nextDayIndex >= maxDays) {
//                        //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
//                        nextDayIndex = days.size() - 1;
//                    }
//                    dayView.setItems(days, nextDayIndex);
//                    dealTime();
//                }
//            }
//        });
//        if (!mode.equals(Mode.YEAR_MONTH)) {
//            if (!TextUtils.isEmpty(dayLabel)) {
//                dayTextView.setText(dayLabel);
//            }
//            if (nextDayIndex == 0) {
//                dayView.setItems(days);
//            } else {
//                dayView.setItems(days, nextDayIndex);
//            }
//            dayView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//                @Override
//                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
//                    nextDayIndex = selectedIndex;
//                    dealTime();
//                }
//            });
//        }
//        return layout;
//    }
//
//
//    private int stringToYearMonthDay(String text) {
//        if (text.startsWith("0")) {
//            //截取掉前缀0以便转换为整数
//            text = text.substring(1);
//        }
//        return Integer.parseInt(text);
//    }
//
//    @Override
//    protected void setContentViewAfter(View contentView) {
//        super.setContentViewAfter(contentView);
//        super.setOnConfirmListener(new ConfirmPopup.OnConfirmListener() {
//            @Override
//            public void onConfirm() {
//                if (onDatePickListener != null) {
//                    String fjyear = "";
//                    String fjmonth = "";
//                    String fjday = "";
//                    //预约下次的时间
//                    String nextyear = "";
//                    String nextmonth = "";
//                    String nextday = "";
//                    if (requireAppoint.isRechckDateNess) {
//                        fjyear = years.get(fujianYearIndex);
//                        fjmonth = months.get(fujianMonthIndex);
//                        fjday = days.get(fujianDayIndex);
//                    }
//
//                    if (requireAppoint.isNextStepDateNesss) {
//                        nextyear = years.get(fujianYearIndex);
//                        nextmonth = months.get(fujianMonthIndex);
//                        nextday = days.get(fujianDayIndex);
//                    }
//
//
//                    switch (mode) {
//                        default:
//                            ((OnYearMonthDayPickListener) onDatePickListener).onDatePicked(fjyear, fjmonth, fjday, nextyear, nextmonth, nextday);
//                            break;
//                    }
//                }
//            }
//        });
//    }
//    public void dealTime(){
//        if (onDatePickListener != null) {
//            String fjyear = "";
//            String fjmonth = "";
//            String fjday = "";
//            //预约下次的时间
//            String nextyear = "";
//            String nextmonth = "";
//            String nextday = "";
//            if (requireAppoint.isRechckDateNess) {
//                fjyear = years.get(fujianYearIndex);
//                fjmonth = months.get(fujianMonthIndex);
//                fjday = days.get(fujianDayIndex);
//            }
//
//            if (requireAppoint.isNextStepDateNesss) {
//                nextyear = years.get(fujianYearIndex);
//                nextmonth = months.get(fujianMonthIndex);
//                nextday = days.get(fujianDayIndex);
//            }
//
//
//            switch (mode) {
//                default:
//                    ((OnYearMonthDayPickListener) onDatePickListener).onDatePicked(fjyear, fjmonth, fjday, nextyear, nextmonth, nextday);
//                    break;
//            }
//        }
//    }
//
///**
// * The interface On date pick listener.
// */
//protected interface OnDatePickListener {
//
//}
//
///**
// * The interface On year month day pick listener.
// */
//public interface OnYearMonthDayPickListener extends OnDatePickListener {
//    void onDatePicked(String fjyear, String fjmonth, String fjday, String nextyear, String nextmonth, String nextday);
//
//}