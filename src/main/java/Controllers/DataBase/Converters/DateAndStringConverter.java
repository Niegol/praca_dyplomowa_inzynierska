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

/**
 * Klasa odpowiedzialna za konwersję zmiennych Date na String i odwrotnie
 */
public class DateAndStringConverter {

    /**
     * Konwersja zmiennej Date na zmienną tekstową String
     * @param date data
     * @return data w formie String
     */
    public static String dateToString(Date date){
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        String string = df.format(date);
        return string;
    }

    /**
     * Konwersja ze zmiennej String na Date. W przypadku błędnego formatu zostanie wyświetlona informacja w oknie dielogowym
     * @param string data w formie tekstowej
     * @return data typu Date
     */
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

    /**
     * Konwersja łańcucha znaków na zmienną LocalDate. W przypadku błędnego formatu zostanie wyświetlona informacja w oknie dielogowym
     * @param string łańcuch, który ma zostać zamieniony na zmienną LocalDate
     * @return lokalna data typu Localdate
     */
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

