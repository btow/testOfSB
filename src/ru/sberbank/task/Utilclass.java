package ru.sberbank.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by btow on 05.03.2017.
 * A class name must begin with a capital letter
 */
public class Utilclass {

    /**    public final Set<byte[]> listElements = new HashSet<>();
     * the modifier was changed from "public" to "private",
     * the modifier "final" is removed
     */
    private Set<byte[]> listElements;

    /**the modifier was changed from "private" to "public",
     * the modifiers "static final" has ben added
     */
    public static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");

    //Constructor of class
    public Utilclass() {
        this.listElements = new HashSet<>();
    }

    public Set<byte[]> getListElements() {

        return this.listElements;

    }

    public void setListElements(Set<byte[]> newListElements) {

        this.listElements = newListElements;

    }

    public void addElement(byte[] element) {

        //a conditional statement is removed, since the class "HashSet" contains only unique elements
        //if (!setElements.contains(element)) {

            this.listElements.add(element);

        //}
    }


    public <T> boolean compareNumberWithZero(T obj) {

//operator "instanceof" use is not recommended
//      if (obj instanceof Integer && ((Integer)obj) == 0) {
        if (isInteger(obj)) {

//          return true;
            return ((Integer)obj) == 0;

//      } else if (obj instanceof Double && ((Double)obj).equals(0.00)) {
        } else if (isDouble(obj)) {

//          return true;
            return ((Double)obj).equals(0.00);

//      } else if (obj instanceof Float && new Float("0.0").equals(obj)) {
        } else if (isFloat(obj)) {

//          return true;
            return new Float("0.0").equals(obj);

//      } else if (obj instanceof BigDecimal && BigDecimal.ZERO.equals(obj)) {
        } else if (isBigDecimal(obj)) {

//          return true;
            return BigDecimal.ZERO.equals(obj);

        }

        return false;

    }

    //Methods to check data type without using the operator "instanceof".
    private <T> boolean isBigDecimal(T obj) {

        try {

            BigDecimal intObj = (BigDecimal) obj;

        } catch (NumberFormatException nfe) {

            return false;

        }

        return true;
    }

    private <T> boolean isFloat(T obj) {

        try {

            Float intObj = (Float) obj;

        } catch (NumberFormatException nfe) {

            return false;

        }

        return true;
    }

    private <T> boolean isDouble(T obj) {

        try {

            Double intObj = (Double) obj;

        } catch (NumberFormatException nfe) {

            return false;

        }

        return true;
    }

    private <T> boolean isInteger(T obj) {

        try {

            Integer intObj = (Integer) obj;

        } catch (NumberFormatException nfe) {

            return false;

        }

        return true;
    }


    /**
     * Метод логирует данные (предполагается использование из внешних модулей).
     * Формат: [текущая дата в виде dd-MM-yy]: stroka; dataElements как строка (через сепаратор)
     * @param stroka строка из инпута
     * @param dataElements коллеция с данными для логирования
     * @param separator символ, который используется для разделения элементов из коллеции dataElements
     *                  для создания строкового представления.
     */

    //Added the modifier "public" and "static
//  void LogData(String stroka, Collection dataElements, char separator) {
    public static void LogData(String stroka, Collection dataElements, char separator) {

        stroka.trim();

        String result = "[" + format.format(new Date()) + "]: " + stroka + "; ";

        //The addition of the loop braces
        for (Object obj : dataElements) {

            //Convert object to its string representation and appends to the string separator
//          result += obj + " ";
            result += obj.toString() + separator;

        }

        System.out.println(result);

    }



    /**

     * [startDate или текущая дата, если startDate = null или меньше текущей даты] + 5 рабочих дней + 1 календарный день + 1 год.

     * classifier - справочник содержащий даты выходных и праздничных дней

     */

    public static Date getDatePlus5WorkDaysPlus1DayPlus1Year(Date startDate, List<InsuranceHoliday> classifier) {

        Calendar calendar = Calendar.getInstance();

        if (startDate != null & startDate.compareTo(calendar.getTime()) < 0) {

            calendar.setTime(startDate);

        }

        int workingDays = 0;

        while (workingDays < 5) {

            calendar.add(Calendar.DAY_OF_YEAR, 1);

            boolean weekend = false;

            for (InsuranceHoliday holiday : classifier) {

                //The class "Date" is deprecated
//                if (holiday.getTheDate().getYear() == calendar.getTime().getYear()
//                        && holiday.getTheDate().getMonth() == calendar.getTime().getMonth()
//                        && holiday.getTheDate().getDay() == calendar.getTime().getDay()) {
                if (holiday.equals(calendar)) {

                    weekend = true;
                    break;

                }

            }

            if (!weekend)

                workingDays++;

        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);

        calendar.add(Calendar.YEAR, 1);

        return calendar.getTime();

    }

    //Yhe Class to store the dates of holidays and of operations with them
    public static class InsuranceHoliday {

        private Calendar holiday;

        public InsuranceHoliday(Date date) {

            holiday.setTime(date);

        }

        public Calendar getTheDate() {

            return holiday;

        }
    }
}