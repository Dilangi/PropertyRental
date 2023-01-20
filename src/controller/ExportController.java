package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import list.ObjectHelper;

public class ExportController {

    @FXML
    private Button btnAgreement;

    @FXML
    private Button btnAll;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnPoI;

    @FXML
    private Button btnProperties;

    @FXML
    void exportAgreementListener(ActionEvent event) throws ClassNotFoundException, IOException {
    	ObjectHelper.writeToCSVFile(ObjectHelper.AGREEMENT_EXPORT_FILE_NAME);
    }

    @FXML
    void exportAllListener(ActionEvent event) throws ClassNotFoundException, IOException {
    	ObjectHelper.writeToCSVFile(ObjectHelper.POI_EXPORT_FILE_NAME);
    	ObjectHelper.writeToCSVFile(ObjectHelper.AGREEMENT_EXPORT_FILE_NAME);
    	ObjectHelper.writeToCSVFile(ObjectHelper.CUSTOMER_EXPORT_FILE_NAME);
    	ObjectHelper.writeToCSVFile(ObjectHelper.PROPERTY_EXPORT_FILE_NAME);
    }

    @FXML
    void exportCustomerListener(ActionEvent event) throws ClassNotFoundException, IOException {
    	ObjectHelper.writeToCSVFile(ObjectHelper.CUSTOMER_EXPORT_FILE_NAME);
    }

    @FXML
    void exportPoIListener(ActionEvent event) throws ClassNotFoundException, IOException {
    	ObjectHelper.writeToCSVFile(ObjectHelper.POI_EXPORT_FILE_NAME);
    }

    @FXML
    void exportPropertiesListener(ActionEvent event) throws ClassNotFoundException, IOException {
    	ObjectHelper.writeToCSVFile(ObjectHelper.PROPERTY_EXPORT_FILE_NAME);
    }

}
