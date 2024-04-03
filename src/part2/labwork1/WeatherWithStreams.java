package part2.labwork1;

import part1.labwork3.first.Day;
import part1.labwork4.first.WeatherWithList;

import java.util.*;
import java.util.stream.Collectors;

public class WeatherWithStreams extends WeatherWithList {
    public WeatherWithStreams() {
    }

    public WeatherWithStreams(String season, String comment, List<Day> days) {
        super(season, comment);
        setDays(days);
    }

    public WeatherWithStreams(String season, String comment) {
        super(season, comment);
    }

    @Override
    public void setDays(List<Day> days) {
        super.setDays(days.stream()
                .distinct()
                .collect(Collectors.toList()));
    }

    @Override
    public void setDays(Day[] days) {
        setDays(Arrays.asList(days));
    }

    @Override
    public void sortByDecreasingTemperature() {
        setDays(getDaysList().stream()
                .sorted(Comparator.comparing(Day::getTemperature).reversed())
                .collect(Collectors.toList()));
    }

    @Override
    public void sortByComment() {
        setDays(getDaysList().stream()
                .sorted(Comparator.comparing(Day::getComment))
                .collect(Collectors.toList()));
    }
    @Override
    public Double calculateAverageTemperature() {
        if (daysCount() == 0)
            return null;
        return getDaysList().stream()
                .mapToDouble(Day::getTemperature)
                .average()
                .orElse(Double.NaN); //TODO
    }
//    @Override
//    public Day[] findMaxTemperatureDays() {
//        if (daysCount() == 0)
//            return null;
//        Day maxTemperatureDay = getDays()[0];
//        for (Day day : getDays()) {
//            if (maxTemperatureDay.getTemperature() < day.getTemperature()) {
//                maxTemperatureDay = day;
//            }
//        }
//        Day[] result = null;
//        for (Day day : getDays()) {
//            if (maxTemperatureDay.getTemperature() == day.getTemperature()) {
//                result = addToArray(result, day);
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public Day[] findLongestCommentDays() {
//        if (daysCount() == 0)
//            return null;
//        Day LongestCommentDay = getDays()[0];
//        for (Day day : getDays()) {
//            if (LongestCommentDay.calculateLengthComment() < day.calculateLengthComment()) {
//                LongestCommentDay = day;
//            }
//        }
//        Day[] result = null;
//        for (Day day : getDays()) {
//            if (LongestCommentDay.calculateLengthComment() == day.calculateLengthComment()) {
//                result = addToArray(result, day);
//            }
//        }
//        return result;
//    }


}
