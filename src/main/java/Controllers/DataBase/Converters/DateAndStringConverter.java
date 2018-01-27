package Controllers.DataBase.Converters;

import Classes.dialogs.DialogsUtils;
import javafx.beans.property.StringProperty;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class DateAndStringConverter {

    public static String dateToString(Date date){
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        String string = df.format(date);
        return string;
    }

    public static Date stringToDate(String string){
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = df.parse(string);
        } catch (ParseException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return date;
    }

    public static LocalDate stringToLocalDate(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate localDate = LocalDate.parse(string, formatter);
            return localDate;
        }catch (DateTimeParseException e){
            DialogsUtils.errorDialog(e.getMessage());
            return null;
        }
    }




}

