package controller;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import util.MySQLHelper;

public class PointOfSaleSystemController {
	
	@FXML
    private JFXButton toolbar_print;

    @FXML
    private JFXButton toolbar_product;

    @FXML
    private JFXButton toolbar_exit;
    
    @FXML
    private AnchorPane content_pane;

    @FXML
    void closeProgram(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void loadProductFrame(ActionEvent event) {
    	setContentPane(fadeAnimation("/view/ProductFrame.fxml"));
    }

	private void setContentPane(Node node) {
		// TODO Auto-generated method stub
		content_pane.getChildren().setAll(node);
	}

	private AnchorPane fadeAnimation(String path) {
		// TODO Auto-generated method stub
		AnchorPane pane = null;
		try {
			pane = FXMLLoader.load(getClass().getResource(path));
			FadeTransition ft = new FadeTransition(Duration.millis(1500));
			ft.setNode(pane);
			ft.setFromValue(0.1);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pane;
	}

	@FXML
    void printProduct(ActionEvent event) {
		String print_product = "src/report/report_product.jasper";
		Map map = new HashedMap();
		
		try {
			JasperPrint jp = JasperFillManager.fillReport(print_product, map, MySQLHelper.openDB());
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
