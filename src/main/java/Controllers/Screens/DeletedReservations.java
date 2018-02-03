package Controllers.Screens;

import Controllers.DataBase.FXModels.HistoryFX;
import Controllers.DataBase.FXModels.UserFX;
import Controllers.DataBase.Service.HistoryService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeletedReservations {


    @FXML
    private TableView<HistoryFX> historyTable;

    @FXML
    private TableColumn<HistoryFX, Number> idColumn;

    @FXML
    private TableColumn<HistoryFX, String> customerColumn;

    @FXML
    private TableColumn<HistoryFX, String> arrivalColumn;

    @FXML
    private TableColumn<HistoryFX, String> departureColumn;

    @FXML
    private TableColumn<HistoryFX, String> amountColumn;

    @FXML
    private TableColumn<HistoryFX, String> changingColumn;

    @FXML
    private TableColumn<HistoryFX, UserFX> userColumn;

    @FXML
    private HistoryService historyService;




    @FXML
    private void initialize(){
        this.historyService = new HistoryService();
        this.historyService.init();

        this.historyTable.setItems(this.historyService.getHistoryFXES());

        this.idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        this.customerColumn.setCellValueFactory(cellData -> cellData.getValue().customerSurnameProperty());
        this.arrivalColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalDateProperty());
        this.departureColumn.setCellValueFactory(cellData -> cellData.getValue().departureDateProperty());
        this.amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountPeopleProperty());
        this.changingColumn.setCellValueFactory(cellData -> cellData.getValue().changingDateProperty());
        this.userColumn.setCellValueFactory(cellData -> cellData.getValue().userFXProperty());


    }




}
