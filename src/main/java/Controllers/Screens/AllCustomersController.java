package Controllers.Screens;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.FXModels.CustomerFX;
import Controllers.DataBase.Service.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;


public class AllCustomersController implements Initializable{
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
    private TextField company;

    @FXML
    public Button buttonAdd;

    @FXML
    private TableView<CustomerFX> tableCustomers;

    @FXML
    private TableColumn<CustomerFX, Number> columnID;

    @FXML
    private TableColumn<CustomerFX, String> columnName;

    @FXML
    private TableColumn<CustomerFX, String> columnSurname;

    @FXML
    private TableColumn<CustomerFX, String> columnPhone;

    @FXML
    private TableColumn<CustomerFX, String> columnEmail;

    @FXML
    private TableColumn<CustomerFX, String> columnNip;

    @FXML
    private TableColumn<CustomerFX, String> columnCompany;

    @FXML
    private MenuItem deleteMenuItem;


    private CustomerService customerService;



    @FXML
    public void initBindings(){
        this.customerService.customerProperty().get().nameProperty().bind(this.name.textProperty());
        this.customerService.customerProperty().get().surnameProperty().bind(this.surname.textProperty());
        this.customerService.customerProperty().get().phoneProperty().bind(this.phone.textProperty());
        this.customerService.customerProperty().get().emailProperty().bind(this.email.textProperty());
        this.customerService.customerProperty().get().nipProperty().bind(this.nip.textProperty());
        this.customerService.customerProperty().get().companyProperty().bind(this.company.textProperty());

        this.buttonAdd.disableProperty().bind(this.name.textProperty().isEmpty().
                or(this.surname.textProperty().isEmpty()).
                or(this.phone.textProperty().isEmpty()).
                or(this.email.textProperty().isEmpty())
        );
    }

    @FXML
    public void actionAdd(){

            customerService.saveInDB();
            customerService.init();
            this.name.clear();
            this.surname.clear();
            this.phone.clear();
            this.email.clear();
            this.nip.clear();
            this.company.clear();

    }


    public void onEditCommitId(TableColumn.CellEditEvent<CustomerFX, Number> customerFXNumberCellEditEvent) {
    }

    @FXML
    public void onEditCommitName(TableColumn.CellEditEvent<CustomerFX, String> customerFXStringCellEditEvent) {
        this.customerService.getCustomerEdit().setName(customerFXStringCellEditEvent.getNewValue());
        this.customerService.saveEditInDB();
        this.customerService.init();

    }

    @FXML
    public void onEditCommitSurname(TableColumn.CellEditEvent<CustomerFX, String> customerFXStringCellEditEvent) {
        this.customerService.getCustomerEdit().setSurname(customerFXStringCellEditEvent.getNewValue());
        this.customerService.saveEditInDB();
        this.customerService.init();

    }

    @FXML
    public void onEditCommitPhone(TableColumn.CellEditEvent<CustomerFX, Number> customerFXNumberCellEditEvent) {
        this.customerService.getCustomerEdit().setPhone(String.valueOf(customerFXNumberCellEditEvent.getNewValue()));
        this.customerService.saveEditInDB();
        this.customerService.init();

    }

    @FXML
    public void onEditCommitEmail(TableColumn.CellEditEvent<CustomerFX, String> customerFXStringCellEditEvent) {
        this.customerService.getCustomerEdit().setEmail(customerFXStringCellEditEvent.getNewValue());
        this.customerService.saveEditInDB();
        this.customerService.init();
    }

    @FXML
    public void onEditCommitNip(TableColumn.CellEditEvent<CustomerFX, Number> customerFXNumberCellEditEvent) {
        try {
            this.customerService.getCustomerEdit().setNip(String.valueOf(customerFXNumberCellEditEvent.getNewValue()));
            this.customerService.saveEditInDB();
        }catch (NumberFormatException e) {
            DialogsUtils.errorDialog("Wrong NIP Format");
        }
    }

    @FXML
    public void onEditCommitCompany(TableColumn.CellEditEvent<CustomerFX, String> customerFXStringCellEditEvent) {
        this.customerService.getCustomerEdit().setCompany(customerFXStringCellEditEvent.getNewValue());
        this.customerService.saveEditInDB();
    }


    public void deleteOnAction() {
        this.customerService.deleteInDB();
    }

    public void setName(String n){
        this.name.setText(n);
    }

    public void setSurname(String s){
        this.surname.setText(s);
    }

    public void setPhone(String p){
        this.phone.setText(p);
    }

    public void setEmail(String e){
        this.email.setText(e);
    }

    public void setNip(String ni){
        this.nip.setText(ni);
    }

    public void setCompany(String com){
        this.company.setText(com);
    }


    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.customerService = new CustomerService();
        customerService.init();
        initBindings();

        this.tableCustomers.setItems(this.customerService.getCustomerList());

        this.columnID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        this.columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.columnSurname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        this.columnPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        this.columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        this.columnNip.setCellValueFactory(cellData -> cellData.getValue().nipProperty());
        this.columnCompany.setCellValueFactory(cellData -> cellData.getValue().companyProperty());

        this.columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnNip.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnCompany.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.customerService.setCustomerEdit(newValue);
        });


    }
}
