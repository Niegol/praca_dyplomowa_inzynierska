package Controllers.Screens;
import Classes.ShowPanes.ScreenShowAnchorPane;
import Classes.ShowPanes.ScreenShowBorderPane;
import Classes.ShowPanes.ScreenShowPane;
import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.Converters.DateAndStringConverter;
import Controllers.DataBase.FXModels.CustomerFX;
import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.Service.CustomerService;
import Controllers.DataBase.Service.RoomReservationService;
import Controllers.DataBase.Service.RoomService;
import Controllers.DataBase.Service.UserService;
import Controllers.DataBase.dbutilies.DbManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainScreenController{
    private static final String pathToLoginScreen = "/FXMLFiles/LoginScreen.fxml";
    private static final String pathToUserSettings = "/FXMLFiles/UserSettings.fxml";
    private static final String pathToAllCustomersScreen = "/FXMLFiles/AllCustomers.fxml";
    private static final String pathToAllReservationsScreen = "/FXMLFiles/AllReservations.fxml";
    private static final String pathToDeletedReservations = "/FXMLFiles/DeletedReservations.fxml";

    private static final String titleOfLoginScreen = "Login Screen";
    private static final String titleOfUserSettings = "User Settings";
    private static final String titleOfAllCustomersScreen = "All Customer";
    private static final String getTitleOfAllReservationsScreen = "All reservations";
    private static final String titleOfDeletedReservations = "History Of Reservations";


    private static final Boolean resizableEditUserScreen = false;
    private static final Boolean resizableUserSettings = false;
    private static final Boolean resizableAllCustomersScreen = false;
    private static final Boolean resizableAllReservationsScreen = false;
    private static final Boolean resizableDeletedReservations = false;


    @FXML
    private Label labelUser;
    @FXML
    private Tab pavA;

    @FXML
    private Tab pavB;

    @FXML
    private Tab pavC;

    @FXML
    private Tab pavD;

    @FXML
    private Tab pafoor0;

    @FXML
    private Tab pafoor1;

    @FXML
    private Tab pafoor2;

    @FXML
    private Tab pbfoor0;

    @FXML
    private Tab pbfoor1;

    @FXML
    private Tab pbfoor2;

    @FXML
    private Tab pcfoor0;

    @FXML
    private Tab pcfoor1;

    @FXML
    private Tab pdfoor0;

    @FXML
    private Tab pdfoor1;

    @FXML
    private GridPane pavAfloor0;

    @FXML
    private TableView pavAfloor1;

    @FXML
    private TableView pavAfloor2;


    @FXML
    private Button showButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button reservationsButton;


    @FXML
    private Button buttonSavePA1;

    @FXML
    private Button buttonSavePA2;

    @FXML
    private Button buttonSavePB0;

    @FXML
    private Button buttonSavePB1;

    @FXML
    private Button buttonSavePB2;

    @FXML
    private Button buttonSavePC0;

    @FXML
    private Button buttonSavePC1;

    @FXML
    private Button buttonSavePD0;

    @FXML
    private Button buttonSavePD1;

    @FXML
    private Button buttonSettings;

    @FXML
    private Button buttonEditCustomer;

    @FXML
    private Button showCustomer;

    @FXML
    private Button showHistoryButton;

    @FXML
    private ComboBox<CustomerFX> customersBox;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField nip;

    @FXML
    private TextField companyName;

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private ScrollPane scrollPane;

    private List<String> colors;
    private int colorIterator = 0;


    private CustomerFX customerFX;
    private CustomerService customerService;

    private RoomService roomServiceA0 = new RoomService();
    private RoomService roomServiceA1 = new RoomService();
    private RoomService roomServiceA2 = new RoomService();
    private RoomService roomServiceB0 = new RoomService();
    private RoomService roomServiceB1 = new RoomService();
    private RoomService roomServiceB2 = new RoomService();
    private RoomService roomServiceC0 = new RoomService();
    private RoomService roomServiceC1 = new RoomService();
    private RoomService roomServiceD0 = new RoomService();
    private RoomService roomServiceD1 = new RoomService();

    private RoomReservationService roomReservationService;



    @FXML
    public void showAction(){
        if(this.checkInDatePicker.getValue() != null & this.checkOutDatePicker.getValue() != null) {
            if (this.checkInDatePicker.getValue().isBefore(this.checkOutDatePicker.getValue())
                    | this.checkInDatePicker.getValue().isEqual(this.checkOutDatePicker.getValue())) {

                LocalDate low = this.checkInDatePicker.getValue();
                LocalDate high = this.checkOutDatePicker.getValue();
                this.roomReservationService.init(low,high);
                this.getAllRoomsForTableViev(low,high);
                DbManager.closeConnectionSource();
            } else
                DialogsUtils.communicat("Wrong range!");
        }else
            DialogsUtils.communicat("Data fields can't be empty!");
    }


    @FXML
    public void closeAction(){
        DialogsUtils dialogsUtils = new DialogsUtils();
        dialogsUtils.closeApplication();
    }

    @FXML
    public void logoutAction(){
        UserService userService = new UserService();
        userService.logOut();
        ScreenShowPane pane = new ScreenShowPane(pathToLoginScreen,titleOfLoginScreen, resizableUserSettings);
        close();
    }

    @FXML
    public void close(){
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void actionSave(){

    }

    @FXML
    public void actionShowAll(){
        ScreenShowBorderPane borderPane = new ScreenShowBorderPane(pathToAllCustomersScreen, titleOfAllCustomersScreen, resizableAllCustomersScreen);
    }

    @FXML
    public void actionSettings(){
        ScreenShowAnchorPane anchorPane = new ScreenShowAnchorPane(pathToUserSettings, titleOfUserSettings,resizableEditUserScreen);
    }

    @FXML
    public void actionSetTextFields(){
        this.customerService.setCustomer(customersBox.getSelectionModel().getSelectedItem()); //ustawia klienta jako element z kombo boxa
        if (this.customerService.customerProperty().isNotNull().getValue()) {
            name.setText(customerService.getCustomer().getName());
            name.setEditable(false);
            surname.setText(customerService.getCustomer().getSurname());
            surname.setEditable(false);
            phone.setText(customerService.getCustomer().getPhone());
            phone.setEditable(false);
            nip.setText(customerService.getCustomer().getNip());
            nip.setEditable(false);
            companyName.setText(customerService.getCustomer().getCompany());
            companyName.setEditable(false);
            email.setText(customerService.getCustomer().getEmail());
            email.setEditable(false);
        }
    }

    public void initialize() {
        UserService userService = new UserService();
        labelUser.setText(userService.getUserNick());

        if(!userService.isAdmin())
            this.showHistoryButton.setDisable(true);

        this.colors = new ArrayList<>();
        this.setColors();
        this.roomReservationService = new RoomReservationService();
        this.getAllRoomsForTableViev(LocalDate.now(),LocalDate.now().plusDays(10));
        this.customerService = new CustomerService();
        this.customerService.init();
        this.customersBox.setItems(this.customerService.getCustomerList());
    }
    public void setColors(){
        colors.add("lightgreen");
        colors.add("aqua");
        colors.add("chartreuse");
        colors.add("brown");
        colors.add("cornflowerblue");
        colors.add("darkgreen");
        colors.add("gold");

    }

    public void getAllRoomsForTableViev(LocalDate low, LocalDate high){
        Map<String, String> colorsMap = new HashMap<>();
        this.pavAfloor0.getChildren().clear();
        while(this.pavAfloor0.getRowConstraints().size() > 0){
            this.pavAfloor0.getRowConstraints().remove(0);
        }
        while (this.pavAfloor0.getColumnConstraints().size() > 0){
            this.pavAfloor0.getColumnConstraints().remove(0);
        }
        this.pavAfloor0.setGridLinesVisible(true);

        RowConstraints row = new RowConstraints();
        this.pavAfloor0.getRowConstraints().add(row);


        this.roomReservationService.init(low,high);

        this.roomServiceA0.init("A",0);

        for(int i= -1; i<this.roomServiceA0.getRoomFXObservableList().size(); ++i){     //filling first row of Grid Pane
            TextField textField = new TextField();
            textField.setStyle("-fx-border-color: black;");

            if (i == -1) {
                textField.setText("Date:");
                textField.setPrefWidth(220);
//                textField.setPrefHeight(50);
                textField.setAlignment(Pos.CENTER_LEFT);
            }
            else
                textField.setText(roomServiceA0.getRoomFXObservableList().get(i).getRoomNumber());
            textField.setAlignment(Pos.CENTER);
            textField.setEditable(false);
            textField.setFont(Font.font(13));
            this.pavAfloor0.addRow(0,textField);
            this.pavAfloor0.setGridLinesVisible(true);

        }

        for(int i = 0; i < this.roomReservationService.getStringDatesObservableList().size(); ++i){ //filling first column
            TextField textField = new TextField();
            textField.setStyle("-fx-border-color: black;");

            textField.setPrefWidth(220);
            textField.setPrefHeight(60);
            textField.setText(roomReservationService.getStringDatesObservableList().get(i));
            textField.setEditable(false);
            this.pavAfloor0.addColumn(0, textField);
            this.pavAfloor0.setGridLinesVisible(true);
        }


        for(int i = 1; i <= this.roomReservationService.getStringDatesObservableList().size(); ++i){
            for(int j = 1; j<= this.roomServiceA0.getRoomFXObservableList().size(); ++j)
            {
                GridPane dev = new GridPane();
                TextField tex1 = new TextField();
                TextField tex2 = new TextField();
                tex1.setAlignment(Pos.CENTER);
                tex2.setAlignment(Pos.CENTER);
                tex1.setPrefHeight(30);
                tex2.setPrefHeight(30);

                String color = this.colors.get(0);
                RoomReservationFX set = new RoomReservationFX();
                LocalDate iIter = DateAndStringConverter.stringToLocalDate(this.roomReservationService.getStringDatesObservableList().get(i-1));
                int jIter = Integer.parseInt(this.roomServiceA0.getRoomFXObservableList().get(j-1).getRoomNumber());

                for(int k=0;k<this.roomReservationService.getRoomReservationFXObservableList().size();++k) {
                    RoomReservationFX kIter = this.roomReservationService.getRoomReservationFXObservableList().get(k);
                    LocalDate kArrivDate = DateAndStringConverter.stringToLocalDate(kIter.getReservationFX().getArrivalDate());
                    LocalDate kDepDate = DateAndStringConverter.stringToLocalDate(kIter.getReservationFX().getDepartureDate());
                    int kRoom = Integer.parseInt(kIter.getRoomFX().getRoomNumber());
                    if (((iIter.isBefore(kDepDate) & iIter.isAfter(kArrivDate)) | iIter.isEqual(kArrivDate) | iIter.isEqual(kDepDate)) &
                                    kRoom == jIter ) {

                        String idCustomer = String.valueOf(kIter.getReservationFX().getCustomerFX().getId());

                        if(colorsMap.containsKey(idCustomer)){
                            color = colorsMap.get(idCustomer);
                        }else{
                            colorsMap.put(idCustomer, this.getNextColor());
                            color = colorsMap.get(idCustomer);
                        }

                        set = kIter;
                        if(iIter.isEqual(kArrivDate)) {
                            RoomReservationFX finalSet = set;
                            tex2.setText(idCustomer);
                            addMouseClickedEvent(tex2, finalSet);

                        }
                        if(iIter.isEqual(kDepDate)) {
                            tex1.setText(idCustomer);
                            RoomReservationFX finalSet = set;
                            addMouseClickedEvent(tex1, finalSet);

                        }
                        if(iIter.isBefore(kDepDate) & iIter.isAfter(kArrivDate)){
                            tex1.setText(idCustomer);
                            RoomReservationFX finalSet = set;
                            addMouseClickedEvent(tex1, finalSet);
                            tex2.setText(idCustomer);
                            addMouseClickedEvent(tex2, finalSet);
                        }

                    }else{
                        tex1.setStyle("-fx-border-color: black");
                        tex2.setStyle("-fx-border-color: black");
                    }
                }

                setTextFields(tex1, color);
                setTextFields(tex2, color);
                tex1.setEditable(false);
                tex2.setEditable(false);
                dev.addColumn(0,tex1);
                dev.addColumn(0,tex2);
                this.pavAfloor0.addRow(i,dev);
           }

        }
        this.pavAfloor0.setGridLinesVisible(true);

    }

    private String getNextColor(){
        if (this.colorIterator < this.colors.size()){
            return this.colors.get(this.colorIterator++);
        }else{
            this.colorIterator = 0;
            return  this.colors.get(this.colorIterator);
        }
    }

    public void addMouseClickedEvent(TextField tex1, RoomReservationFX finalSet) {
        tex1.setOnMouseClicked(event -> DialogsUtils.communicat(String.valueOf(
                "Rezerwation Id: " + finalSet.getReservationFX().getId() +
                        "\nCustomer: "+ finalSet.getReservationFX().getCustomerFX().getName() + " " +
                        finalSet.getReservationFX().getCustomerFX().getSurname() +
                        "\nArrival time: " + finalSet.getReservationFX().getStartingMeal()+ "\nDeparture time: " +
                        finalSet.getReservationFX().getEndingMeal() + "\nAmmount of people: " +
                        finalSet.getReservationFX().getAmmountOfPeople() +
                        "\nComment: " + finalSet.getReservationFX().getComment()
        )));
    }


    public void setTextFields(TextField tex, String color) {
        if(!tex.getText().isEmpty())
            tex.setStyle("-fx-background-color: "+color+"; -fx-border-color: black");

        else
            tex.setStyle("-fx-border-color: black");

    }

    public void showReservationsAction(){
        ScreenShowBorderPane borderPane = new ScreenShowBorderPane(pathToAllReservationsScreen, getTitleOfAllReservationsScreen, resizableAllReservationsScreen);

    }

    public void onMouseClicked() {
        this.customerService.init();
    }


    public void showHistoryAction() {
        ScreenShowBorderPane borderPan = new ScreenShowBorderPane(pathToDeletedReservations, titleOfDeletedReservations, resizableDeletedReservations);
    }
}
