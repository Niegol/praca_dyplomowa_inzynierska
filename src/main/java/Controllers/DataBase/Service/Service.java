package Controllers.DataBase.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {

    public static Boolean isEmailCorrect(String email){
        Pattern pattern;
        Matcher matcher;
        String emailPattern = "^[a-zA-Z0-9]+[a-zA-Z0-9._-]{4,30}+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}";
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
