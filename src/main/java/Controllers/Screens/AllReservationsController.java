package Controllers.Screens;

import Classes.ShowPanes.ScreenShowBorderPane;
import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.Converters.*;
import Controllers.DataBase.FXModels.CustomerFX;
import Controllers.DataBase.FXModels.ReservationFX;
import Controllers.DataBase.FXModels.RoomFX;
import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.Service.*;
import Controllers.DataBase.models.Reservation;
import Controllers.DataBase.models.Room;
import Controllers.DataBase.models.RoomReservation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableListValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AllReservationsController {
    private static final String pathToAllCustomersScreen = "/FXMLFiles/AllCustomers.fxml";
    private static final String titleOfAllCustomersScreen = "All Customer";
    private static final Boolean resizableAllCustomersScreen = false;

    @FXML
    private TableView<ReservationFX> reservationsTable;

    @FXML
    private TableColumn<ReservationFX, Number> idColumn;

    @FXML
    private TableColumn<ReservationFX, CustomerFX> idCustomerColumn;

    @FXML
    private TableColumn<ReservationFX, String> ammountPeopleColumn;

    @FXML
    private TableColumn<ReservationFX, String> arrivalDateColumn;

    @FXML
    private TableColumn<ReservationFX, String> departureDateColumn;

    @FXML
    private TableColumn<ReservationFX, String> startingMealColumn;

    @FXML
    private TableColumn<ReservationFX, String> endingMealColumn;

    @FXML
    private TableColumn<ReservationFX, String> statusColumn;

    @FXML
    private TableColumn<ReservationFX, String> commentColumn;

    @FXML
    private ComboBox<CustomerFX> customersComboBox;
    ObservableList<CustomerFX> originalItems;
    private String filter = "";

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private TextField ammountTextField;

    @FXML
    private DatePicker arrivalDatePicker;

    @FXML
    private DatePicker departureDatePicker;

    @FXML
    private TextField arrivalTextField;

    @FXML
    private TextField departureTextField;

    @FXML
    private TextField commentTextField;

    @FXML
    private Button addReservationButton;


    @FXML
    private GridPane roomsGridPane;

    @FXML
    private MenuItem contectMenuDelete;


    private ReservationService reservationService;
    private CustomerService customerService;
    private UserService userService;
    private RoomService roomService;
    private RoomReservationService roomReservationService;


    @FXML
    void initialize() {
        this.reservationService = new ReservationService();
        this.reservationService.init();
        this.reservationsTable.setItems(this.reservationService.getReservationFXObservableList());
        this.arrivalDatePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });

        this.departureDatePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });



        this.initBindings();
        this.setGridPane();

        this.customerService = new CustomerService();
        this.userService = new UserService();
        this.customerService.init();

        this.customersComboBox.setItems(this.customerService.getCustomerList());
        this.originalItems = FXCollections.observableArrayList(this.customersComboBox.getItems());
        this.customersComboBox.setTooltip(new Tooltip());
        this.customersComboBox.setOnKeyPressed(this::handleOnKeyPressed);
        this.customersComboBox.setOnHidden(this::handleOnHiding);

        ObservableList<String> list = FXCollections.observableArrayList();

        list.add("Reserved");
        list.add("Paid");
        list.add("Piece");

        this.statusComboBox.setItems(list);


        this.idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        this.idCustomerColumn.setCellValueFactory(cellData -> cellData.getValue().customerFXProperty());
        this.ammountPeopleColumn.setCellValueFactory(cellData -> cellData.getValue().ammountOfPeopleProperty());
        this.arrivalDateColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalDateProperty());
        this.departureDateColumn.setCellValueFactory(cellData -> cellData.getValue().departureDateProperty());
        this.startingMealColumn.setCellValueFactory(cellData -> cellData.getValue().startingMealProperty());
        this.endingMealColumn.setCellValueFactory(cellData -> cellData.getValue().endingMealProperty());
        this.statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        this.commentColumn.setCellValueFactory(cellData -> cellData.getValue().commentProperty());

        this.ammountPeopleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.arrivalDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.departureDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.startingMealColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.endingMealColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        this.reservationsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.reservationService.setReservationEdit(newValue);
        });
    }



    @FXML
    public void initBindings(){
        this.reservationService.reservationProperty().get().ammountOfPeopleProperty().bind(this.ammountTextField.textProperty());
        this.reservationService.reservationProperty().get().arrivalDateProperty().bind(this.arrivalDatePicker.getEditor().textProperty());
        this.reservationService.reservationProperty().get().departureDateProperty().bind(this.departureDatePicker.getEditor().textProperty());
        this.reservationService.reservationProperty().get().startingMealProperty().bind(this.arrivalTextField.textProperty());
        this.reservationService.reservationProperty().get().endingMealProperty().bind(this.departureTextField.textProperty());

        this.reservationService.reservationProperty().get().commentProperty().bind(this.commentTextField.textProperty());

        this.addReservationButton.disableProperty().bind(this.customersComboBox.getSelectionModel().selectedItemProperty().isNull()
                        .or(this.ammountTextField.textProperty().isEmpty())
                        .or(this.arrivalDatePicker.getEditor().textProperty().isEmpty())
                        .or(this.departureDatePicker.getEditor().textProperty().isEmpty())
                        .or(this.statusComboBox.getSelectionModel().selectedItemProperty().isNull())
                    );
    }

    public void setGridPane(){
        this.roomService = new RoomService();
        roomService.init("A",0);
        this.roomsGridPane.getChildren().clear();
        while(this.roomsGridPane.getRowConstraints().size() > 0){
            this.roomsGridPane.getRowConstraints().remove(0);
        }
        while (this.roomsGridPane.getColumnConstraints().size() > 0){
            this.roomsGridPane.getColumnConstraints().remove(0);
        }
        this.roomsGridPane.setGridLinesVisible(true);
        this.roomsGridPane.setVgap(10);

        ColumnConstraints col = new ColumnConstraints();
        this.roomsGridPane.getColumnConstraints().add(col);

        for (int i = 0; i < roomService.getRoomFXObservableList().size(); ++i){
            CheckBox checkBox = new CheckBox();
            checkBox.setId(String.valueOf(roomService.getRoomFXObservableList().get(i).getId()));
            RoomFX roomFX = roomService.getRoomFXObservableList().get(i);
            checkBox.setText(roomService.getRoomFXObservableList().get(i).getRoomNumber());
            checkBox.setOnAction(event -> {
                if(checkBox.isSelected()){
                    reservationService.addSelectedRoom(roomFX);
                }else
                    reservationService.removeSelectedRoom(roomFX);
            });
            this.roomsGridPane.addColumn(0,checkBox);
        }
    }

    @FXML
    public void onEditCustomerCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXNumberCellEditEvent) {
       // this.reservationService.getReservationEdit().getCustomerFX().setId(reservationFXNumberCellEditEvent.getNewValue());
    }

    @FXML
    public void onAmmountEditCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXStringCellEditEvent) {
        this.reservationService.getReservationEdit().setAmmountOfPeople(reservationFXStringCellEditEvent.getNewValue());
        try {
            this.reservationService.saveEditInDB();
        }catch (NumberFormatException e){
            DialogsUtils.errorDialog("Wrong ammount of people format!");
        }
        this.reservationService.init();
    }

    @FXML
    public void onArrivalDateEditCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXStringCellEditEvent) {
        this.reservationService.getReservationEdit().setArrivalDate(reservationFXStringCellEditEvent.getNewValue());
        try {
            this.reservationService.saveEditInDB();
        }catch (NullPointerException e){
            DialogsUtils.errorDialog("Wrong date format!");
        }
        this.reservationService.init();
    }

    @FXML
    public void onDepartureDateEditCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXStringCellEditEvent) {
        this.reservationService.getReservationEdit().setDepartureDate(reservationFXStringCellEditEvent.getNewValue());
        try {
            this.reservationService.saveEditInDB();
        }catch (NullPointerException e){
            DialogsUtils.errorDialog("Wrong date format!");
        }
        this.reservationService.init();
    }

    @FXML
    public void onStartingMealEditCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXStringCellEditEvent) {
        this.reservationService.getReservationEdit().setStartingMeal(reservationFXStringCellEditEvent.getNewValue());
        this.reservationService.saveEditInDB();
        this.reservationService.init();
    }

    @FXML
    public void onEndingMealEditCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXStringCellEditEvent) {
        this.reservationService.getReservationEdit().setEndingMeal(reservationFXStringCellEditEvent.getNewValue());
        this.reservationService.saveEditInDB();
        this.reservationService.init();
    }

    @FXML
    public void onStatusEditCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXStringCellEditEvent) {
        this.reservationService.getReservationEdit().setStatus(reservationFXStringCellEditEvent.getNewValue());
        this.reservationService.saveEditInDB();
        this.reservationService.init();
    }

    @FXML
    public void onCommentEditCommit(TableColumn.CellEditEvent<ReservationFX, String> reservationFXStringCellEditEvent) {
        this.reservationService.getReservationEdit().setComment(reservationFXStringCellEditEvent.getNewValue());
        this.reservationService.saveEditInDB();
        this.reservationService.init();
    }

    public void addReservationOnAction() {
        LocalDate lowAdd = DateAndStringConverter.stringToLocalDate(this.reservationService.getReservation().getArrivalDate());
        LocalDate highAdd = DateAndStringConverter.stringToLocalDate(this.reservationService.getReservation().getDepartureDate());
        

        this.roomReservationService = new RoomReservationService();

        this.roomReservationService.init(lowAdd, highAdd);

        this.reservationService.getReservation()
                .setUserFX(UserConverter.convertToUserFX(this.userService.getLoggedUser()));


        if(this.reservationService.getReservationFXSelectedRooms().isEmpty()){
            DialogsUtils.errorDialog("You have to select room/rooms to add reservation!");

        } else {
            if(lowAdd.isAfter(highAdd) | highAdd.isEqual(lowAdd)) {
                DialogsUtils.errorDialog("Wrong range of arrival and departure dates!");
            } else{
                    int falses = 0;
                    for (RoomFX roomFX : this.reservationService.getReservationFXSelectedRooms()) {
                        for (RoomReservationFX roomResFX : this.roomReservationService.getRoomReservationFXObservableList()) {
                            LocalDate arrivalDate = DateAndStringConverter.stringToLocalDate(roomResFX.getReservationFX().getArrivalDate().toString());
                            LocalDate departureDate = DateAndStringConverter.stringToLocalDate(roomResFX.getReservationFX().getDepartureDate().toString());


//                    DialogsUtils.communicat(lowAdd + " ... " + highAdd
//                            +"\n" +arrivalDate + " ... " + departureDate );

                            if (roomFX.getId() == roomResFX.getRoomFX().getId()) {
                                if ((lowAdd.isBefore(arrivalDate) & (highAdd.isBefore(arrivalDate) | highAdd.isEqual(arrivalDate))) |
                                        (highAdd.isAfter(departureDate) & (lowAdd.isAfter(departureDate) | lowAdd.isEqual(departureDate)))) {

                                } else {
                                    falses = falses + 1;
                                }
                            } else {
                                if (lowAdd.isBefore(highAdd)) {

                                } else {
                                    falses = falses + 1;
                                }

                            }
                        }

                    }
                    if (falses > 0) {
                        DialogsUtils.errorDialog("On this time there is room booked!");

                    } else {
                        this.reservationService.saveInDB();
                        this.reservationService.init();
                        this.reservationService.getReservationFXSelectedRooms().forEach(e -> {
                            RoomReservation roomReservation = new RoomReservation();
                            roomReservation.setRoom(RoomConverter.convertToRoom(e));
                            roomReservation.setReservation(ReservationConverter.convertToReservation(reservationService.getLastReservation()));
                            RoomReservationService roomReservationService = new RoomReservationService();
                            roomReservationService.saveInDB(roomReservation);
                        });
                    }


                    this.customersComboBox.getSelectionModel().clearSelection();
                    this.ammountTextField.clear();
                    this.arrivalDatePicker.getEditor().clear();
                    this.departureDatePicker.getEditor().clear();
                    this.arrivalTextField.clear();
                    this.departureTextField.clear();
                    this.commentTextField.clear();


                    this.actionClose();
            }
        }

    }


    public void setCustomer() {
        this.reservationService.getReservation().setCustomerFX(this.customersComboBox.getSelectionModel().getSelectedItem());
    }

    public void setStatus() {
        this.reservationService.getReservation().setStatus(this.statusComboBox.getSelectionModel().getSelectedItem());
    }

    public void actionClose(){
        Stage stage = (Stage) addReservationButton.getScene().getWindow();
        stage.close();
    }


    public void onMouseClicked() {
        this.customerService.init();
    }

    public void addCustomerAction() {
        ScreenShowBorderPane borderPane = new ScreenShowBorderPane(pathToAllCustomersScreen, titleOfAllCustomersScreen, resizableAllCustomersScreen);

    }

    public void deleteReservation() {
        if(( this.reservationService.getReservationEdit().getStatus().equals("Reserved") &
                LocalDate.now().plusDays(-1).isBefore(DateAndStringConverter.stringToLocalDate
                        (this.reservationService.getReservationEdit().getArrivalDate())) ) |

                ( this.reservationService.getReservationEdit().getStatus().equals("Piece") &
                        (LocalDate.now().isEqual(DateAndStringConverter.stringToLocalDate
                        (this.reservationService.getReservationEdit().getArrivalDate())) |
                        LocalDate.now().isAfter(DateAndStringConverter.stringToLocalDate
                                (this.reservationService.getReservationEdit().getArrivalDate())))
                )) {

            this.reservationService.deleteInDB();

        }
        else
            DialogsUtils.errorDialog("This reservation has paid or piece status!\nDeleting is cancelled!");
    }


    public void handleOnKeyPressed(KeyEvent e) {

        ObservableList<CustomerFX> filteredList = FXCollections.observableArrayList();


        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            this.customersComboBox.getItems().setAll(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (filter.length() == 0) {
            filteredList = originalItems;
            this.customersComboBox.getTooltip().hide();
        } else {
            Stream<CustomerFX> itens = this.customersComboBox.getItems().stream();
            String txtUsr = filter.toString().toLowerCase();
            itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
            this.customersComboBox.getTooltip().setText(txtUsr);
            Window stage = this.customersComboBox.getScene().getWindow();
            double posX = stage.getX() + this.customersComboBox.getBoundsInParent().getMinX();
            double posY = stage.getY() + this.customersComboBox.getBoundsInParent().getMinY();
            this.customersComboBox.getTooltip().show(stage, posX, posY);
            this.customersComboBox.show();
        }
        this.customersComboBox.getItems().setAll(filteredList);
    }

    public void handleOnHiding(Event e) {
        ObservableList<CustomerFX> originalItems = FXCollections.observableArrayList(this.customersComboBox.getItems());
        filter = "";
        this.customersComboBox.getTooltip().hide();
        CustomerFX s = this.customersComboBox.getSelectionModel().getSelectedItem();
        this.customersComboBox.getItems().setAll(originalItems);
        this.customersComboBox.getSelectionModel().select(s);
    }



}
