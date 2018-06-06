package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.InfoRiver;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.SimResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doInfoFiume(ActionEvent event) {
    	this.txtEndDate.clear();
    	this.txtStartDate.clear();
    	this.txtNumMeasurements.clear();
    	this.txtFMed.clear();
    	
    	
    	River river= boxRiver.getValue();
    	if(river == null) {
    		txtResult.appendText("Selezionare un fiume");
    		return;
    	}
    	InfoRiver result= model.getInfoRiver(river);
    	
    	
    	txtStartDate.appendText(result.getPrimaData().toString());
    	txtEndDate.appendText(result.getUltimaData().toString());
    	this.txtNumMeasurements.appendText(String.format("%d", result.getnMisure()));
    	txtFMed.appendText(String.format("%f", result.getF_med()));

    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	this.txtResult.clear();
    	int k=0;
    	try {
    		k=Integer.parseInt(txtK.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserire valore numerico!\n");
    	}
    	
    	if(k<0) {
    		txtResult.appendText("Inserire valore numerico positivo!\n");
    	}
    	
    	SimResult result= model.doSimula(k);
    	
    	txtResult.appendText("-Informazioni sul bacino costruito-\n");
    	txtResult.appendText(String.format("Numero di giorni per cui non è stata garantita erogazione minima: %d\n", result.getGg_non_coperti()));
    	txtResult.appendText(String.format("Capienza media quotidiana del bacino: %f metri cubi al giorno\n", result.getC_med()));

    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Untitled'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Untitled'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Untitled'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Untitled'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Untitled'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Untitled'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Untitled'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Untitled'.";
        
       

    }
    
    public void setModel(Model model) {
    	this.model=model; 
    	boxRiver.getItems().addAll(model.getAllRivers());
    }
}
