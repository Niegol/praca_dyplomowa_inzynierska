package Classes;

import java.io.*;

import Classes.dialogs.DialogsUtils;


/*
klasa sluzaca do wykonywania operacji na plikach. W tym wypadku do konkretnego pliku o sciezce "file.txt"
zawierajacego informacje o numerze id osoby logujacej sie do bazy danych
*/
public class FileOperations {

    public static final String FILE_TXT = "file.txt";

    public FileOperations(){}

    // konstruktor tworzacy plik
    public FileOperations(String path){
        File f = new File(path);
        try {
            f.createNewFile();
        } catch (IOException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    // metoda tworzaca plik
    public static void create(){
        File f = new File(FILE_TXT);
        try {
            f.createNewFile();
        } catch (IOException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    // metoda usuwajaca plik
    public static void delete(){
        File f = new File(FILE_TXT);
        try {
            f.delete();
        } catch (Exception e){
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    // metoda piszaca do pliku w formie stringa. W naszym przypadku zmienna int (numer id usera) bedzie musiala byc
    // rzutowana do stringa przed wywolaniem metody
    public static void addText(String data) throws FileNotFoundException {
        PrintWriter add = new PrintWriter(FILE_TXT);
        add.println(data);
        add.close();
    }

    // metoda czytajaca z pliku
    public static int read() throws IOException {
        FileReader fileReader = new FileReader(FILE_TXT);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = bufferedReader.readLine();
        System.out.println(textLine);
        bufferedReader.close();
        return Integer.parseInt(textLine);
    }

}
