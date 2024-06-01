package kodcu.aidatTakipJavaFXMaven;



import java.sql.SQLException;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

public class AidatPayerAddingWindow extends Application{

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
		EventHandler arg0EventHandler=new EventHandler() 
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
		Label label=new Label("Eklenecek Mükellef Adı Soyadı");
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
		
		
		
		Label label1=new Label("Eklenecek Mükellef No");
		label1.setPrefSize(190, 20);
		label1.setLayoutX(10);
		label1.setLayoutY(70);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label1);
		
//		EventHandler aidatPayerNoCorrectionEvent=new EventHandler<>() 
//		{
//
//			@Override
//			public void handle(Event arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("deneme");
//				if(!Pattern.matches("^[1-9]||([1-9][0-9]{0,})$", textField1.getText()))
//				{
//					textField1.requestFocus();
//					textField1.positionCaret(textField1.getText().length()); 
//					robot=new Robot();
//					robot.keyPress(KeyCode.BACK_SPACE);
//					robot.keyRelease(KeyCode.BACK_SPACE);
//					robot.keyPress(KeyCode.F1);
//					
//				}
//				
//				
//				
//			}
//			
//		};
		
		
		textField1=new TextField();
		textField1.setPrefSize(190, 20);
		textField1.setLayoutX(10);
		textField1.setLayoutY(90);
		root.getChildren().add(textField1);
		//textField1.addEventHandler(KeyEvent.KEY_RELEASED, aidatPayerNoCorrectionEvent);
		
		
		Label label2=new Label("Eklenecek Mükellef Telefon");
		label2.setPrefSize(190, 20);
		label2.setLayoutX(10);
		label2.setLayoutY(130);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label2);
		
		textField2=new TextField();
		textField2.setPrefSize(190, 20);
		textField2.setLayoutX(10);
		textField2.setLayoutY(150);
		root.getChildren().add(textField2);
		
		Label label3=new Label("Eklenecek Mükellef Adres");
		label3.setPrefSize(190, 20);
		label3.setLayoutX(10);
		label3.setLayoutY(190);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label3);
		
		textArea=new TextArea();
		textArea.setPrefSize(190, 60);
		textArea.setLayoutX(10);
		textArea.setLayoutY(210);
		textArea.setWrapText(true);
		root.getChildren().add(textArea);
		
		EventHandler<ActionEvent> aidatPayerAddingEventHandler=new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int result=-1;
				String payerName=textField.getText();
				long payerNo=0;
				if(Pattern.matches("^[1-9][0-9]{0,}$", textField1.getText()))
				{
					payerNo=(long)Integer.valueOf(textField1.getText());
				}
				else
				{
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Uyarı");
					alert.setHeaderText("Dikkat");
					alert.setContentText("İşlem Başarısız. Mükellef numarasının benzersiz "
							+ "ve pozitif tam sayı olduğundan emin olun");
					ButtonType bt=alert.showAndWait().orElse(null);
					if(bt.equals(ButtonType.OK))
					{
						
					}
					return;
				}
				String payerPhone=textField2.getText();
				String payerAddress=textArea.getText();
				AidatPayer aidatPayer=new AidatPayer();
				aidatPayer.setPayerName(payerName);
				aidatPayer.setPayerNo(payerNo);
				aidatPayer.setPayerPhone(payerPhone);
				aidatPayer.setPayerAddress(payerAddress);
				try {
					result=dao.addAidatPayerToAidatPayerTable(aidatPayer);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(result>0)
				{
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Bildirim");
					alert.setHeaderText("İşlem Başarılı");
					alert.setContentText("Mükellef Başarıyla Kaydedilmiştir");
					ButtonType bt=alert.showAndWait().orElse(null);
					if(bt.equals(ButtonType.OK))
					{
						
					}
					textField.setText("");
					textField1.setText("");
					textField2.setText("");
					textArea.setText("");
					return;
				}
				else
				{
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Uyarı");
					alert.setHeaderText("Dikkat");
					alert.setContentText("İşlem Başarısız. Mükellef numarasının benzersiz "
							+ "ve pozitif tam sayı olduğundan emin olun");
					ButtonType bt=alert.showAndWait().orElse(null);
					if(bt.equals(ButtonType.OK))
					{
						
					}
					return;
				}
				
				
			}
			
		};
		Button btnMukellefiKaydet= new Button("Mükellefi Kaydet");
		btnMukellefiKaydet.setPrefSize(140, 30);
		btnMukellefiKaydet.setLayoutX(60);
		btnMukellefiKaydet.setLayoutY(280);
		root.getChildren().add(btnMukellefiKaydet);
		btnMukellefiKaydet.setOnAction(aidatPayerAddingEventHandler);
		
		EventHandler<ActionEvent> goToMainPageEvent=new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent arg) {
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
		btnAnaSayfayaDon.setPrefSize(140, 30);
		btnAnaSayfayaDon.setLayoutX(60);
		btnAnaSayfayaDon.setLayoutY(320);
		root.getChildren().add(btnAnaSayfayaDon);
		btnAnaSayfayaDon.setOnAction(goToMainPageEvent);
	}

}
