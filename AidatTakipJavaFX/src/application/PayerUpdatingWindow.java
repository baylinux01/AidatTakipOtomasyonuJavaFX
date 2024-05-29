package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

public class PayerUpdatingWindow extends Application {

	Robot robot;
	TextField textField,textField1,textField2;
	TextArea textArea;
	Dao dao;
	@Override
	public void start(Stage arg0) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		arg0.setScene(scene);
		arg0.show();
		EventHandler arg0EventHandler=new EventHandler<>() 
				{
		
					@Override
					public void handle(Event arg) {
						// TODO Auto-generated method stub
						Group rootMain=new Group();
						Scene sceneMain=new Scene(rootMain,800,600);
						Stage stageMain=new Stage();
						stageMain.setScene(sceneMain);
						stageMain.setTitle("Ana Sayfa");
						//stageOpenAidatPayerAddingWindow.show();
						Main main=new Main();
						try {
							main.start(stageMain);
							arg0.hide();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				};
		arg0.setOnCloseRequest(arg0EventHandler);
		dao=new Dao();
		List<Aidat> aids=dao.getAllAidats();
		Set<Long> years=new TreeSet<Long>();
		for(Aidat aid:aids)
		{
			years.add(aid.getAidatYear());
		}
		Label label=new Label("Mükellef Adı Soyadı");
		label.setPrefSize(190, 20);
		label.setLayoutX(10);
		label.setLayoutY(10);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label);
		
		
		textField=new TextField();
		textField.setPrefSize(190, 20);
		textField.setLayoutX(10);
		textField.setLayoutY(30);
		root.getChildren().add(textField);
		
		Label label1=new Label("Mükellef Telefon Numarası");
		label1.setPrefSize(190, 20);
		label1.setLayoutX(10);
		label1.setLayoutY(70);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label1);

		
		textField1=new TextField();
		textField1.setPrefSize(190, 20);
		textField1.setLayoutX(10);
		textField1.setLayoutY(90);
		root.getChildren().add(textField1);
		
		Label label3=new Label("Mükellef Adresi");
		label3.setPrefSize(190, 20);
		label3.setLayoutX(10);
		label3.setLayoutY(130);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label3);
		
		textArea=new TextArea();
		textArea.setPrefSize(190, 60);
		textArea.setLayoutX(10);
		textArea.setLayoutY(150);
		textArea.setWrapText(true);
		root.getChildren().add(textArea);
		
		Label label2=new Label("Mükellef No");
		label2.setPrefSize(190, 20);
		label2.setLayoutX(10);
		label2.setLayoutY(210);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label2);
		
		List<AidatPayer> payers=dao.getAllAidatPayers();
		List<Long> payerNos=new ArrayList<Long>();
		for(AidatPayer payer:payers)
		{
			payerNos.add(payer.getPayerNo());
		}
		ChoiceBox cb2=new ChoiceBox();
		cb2.setPrefSize(190, 20);
		cb2.setLayoutX(10);
		cb2.setLayoutY(240);
		cb2.getItems().addAll(payerNos);
		root.getChildren().add(cb2);
		
		
		
		
		
		EventHandler payerUpdatingEventHandler=new EventHandler<>() 
		{

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				long payerNo=Long.valueOf(cb2.getValue().toString());
				String newName=textField.getText();
				String newPhone=textField1.getText();
				String newAddress=textArea.getText();
				int result=-1;
				try {
					result=dao.updateAidatPayer(payerNo, newName, newPhone, newAddress);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(result>0)
				{
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Bildirim");
					alert.setHeaderText("İşlem Başarılı");
					alert.setContentText("Mükellef Bilgileri Başarıyla Güncellenmiştir");
					ButtonType bt=alert.showAndWait().orElse(null);
					if(bt.equals(ButtonType.OK))
					{
						
					}
					return;
				}
				else
				{
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Uyarı");
					alert.setHeaderText("Dikkat");
					alert.setContentText("İşlem Başarısız. Bilgilerde hata olmadığından emin olun");
					ButtonType bt=alert.showAndWait().orElse(null);
					if(bt.equals(ButtonType.OK))
					{
						
					}
					return;
				}
			}
			
		};
		Button btnMukellefBilgileriniGuncelle= new Button("Mükellef Bilgilerini Güncelle");
		btnMukellefBilgileriniGuncelle.setPrefSize(240, 30);
		btnMukellefBilgileriniGuncelle.setLayoutX(10);
		btnMukellefBilgileriniGuncelle.setLayoutY(290);
		root.getChildren().add(btnMukellefBilgileriniGuncelle);
		btnMukellefBilgileriniGuncelle.setOnAction(payerUpdatingEventHandler);
		
		EventHandler goToMainPageEvent=new EventHandler<>() 
		{

			@Override
			public void handle(Event arg) {
				// TODO Auto-generated method stub
				Group rootMain=new Group();
				Scene sceneMain=new Scene(rootMain,800,600);
				Stage stageMain=new Stage();
				stageMain.setScene(sceneMain);
				stageMain.setTitle("Ana Sayfa");
				//stageOpenAidatPayerAddingWindow.show();
				Main main=new Main();
				try {
					main.start(stageMain);
					arg0.hide();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		Button btnAnaSayfayaDon= new Button("Ana Sayfaya Dön");
		btnAnaSayfayaDon.setPrefSize(240, 30);
		btnAnaSayfayaDon.setLayoutX(10);
		btnAnaSayfayaDon.setLayoutY(330);
		root.getChildren().add(btnAnaSayfayaDon);
		btnAnaSayfayaDon.setOnAction(goToMainPageEvent);
		
		
	}

}
