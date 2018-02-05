package Controllers.DataBase.Service;

import Classes.dialogs.DialogsUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {

    public static Boolean isEmailCorrect(String email){
        Pattern pattern;
        Matcher matcher;
        String emailPattern = "^[a-zA-Z0-9]+[a-zA-Z0-9._-]{4,30}+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}";
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        if (matcher.matches()){}
        else{
            DialogsUtils.errorDialog("Wrong email format!");
        }
        return matcher.matches();
    }

    public static Boolean isNameCorrect(String name){
        Pattern pattern;
        Matcher matcher;
        String namePattern = "[A-Z][a-z$]{2,15}";
        pattern = Pattern.compile(namePattern);
        matcher = pattern.matcher(name);
        if (matcher.matches()){}
        else{
            DialogsUtils.errorDialog("Wrong name format!");
        }
        return matcher.matches();
    }
    public static Boolean isSurnameCorrect(String surname){
        Pattern pattern;
        Matcher matcher;
        String surnamePattern = "[A-Z]{1}[a-z]+((-| )[A-Z]{1}[a-z]+)*";
        pattern = Pattern.compile(surnamePattern);
        matcher = pattern.matcher(surname);
        if (matcher.matches()){}
        else{
            DialogsUtils.errorDialog("Wrong surname format!");
        }
        return matcher.matches();
    }

    public static Boolean isPhoneCorrect(String phone){
        Pattern pattern;
        Matcher matcher;
        String phonePattern = "(5|6|7){1}[0-9]{8}";
        pattern = Pattern.compile(phonePattern);
        matcher = pattern.matcher(phone);
        if (matcher.matches()){}
        else{
            DialogsUtils.errorDialog("Wrong phone format!");
        }
        return matcher.matches();

    }


}
