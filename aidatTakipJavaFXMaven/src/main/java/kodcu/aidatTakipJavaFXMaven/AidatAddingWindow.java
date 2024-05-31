package kodcu.aidatTakipJavaFXMaven;

import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

public class AidatAddingWindow extends Application {

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
		Label label=new Label("Aidat Ayı");
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
		
		
		
		Label label1=new Label("Aidat Yılı");
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
		
		
		Label label2=new Label("Aidat Miktarı");
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
		
		EventHandler aidatAddingEventHandler=new EventHandler() 
		{

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				long aidatMonth=1;
				long aidatYear=2000;
				long aidatAmount=2000;
				long aidatStatus=0;
				long payerNo;
				List<AidatPayer> aidatPayers=null;
				int result=-1;
				if(textField.getText().matches("^[1-9]||(1[0-2]$)")
						&&textField1.getText().matches("^[1-9][0-9]{3}$")
						&&Long.valueOf(textField1.getText())>2023
						&&textField2.getText().matches("^[1-9][0-9]{0,}$"))
				{
					aidatMonth=Long.valueOf(textField.getText());
					aidatYear=Long.valueOf(textField1.getText());
					aidatAmount=Long.valueOf(textField2.getText());
					try {
						aidatPayers=dao.getAllAidatPayers();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(aidatPayers!=null&&aidatPayers.size()>0)
					{
						for(AidatPayer aidatPayer:aidatPayers)
						{
							List<Aidat> aidats=null;
							try {
								 aidats=dao.getAidatsByPayerNo(aidatPayer.getPayerNo());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(aidats!=null&&aidats.size()>0)
							{
								long count=0;
								for(Aidat aidat:aidats)
								{
									if(aidat.getAidatMonth()!=aidatMonth
											||aidat.getAidatYear()!=aidatYear)
									{   
										count++;
									}
									if(count==aidats.size())
									{
										Aidat aidatToAdd=new Aidat();
										aidatToAdd.setAidatMonth(aidatMonth);
										aidatToAdd.setAidatYear(aidatYear);
										aidatToAdd.setAidatAmount(aidatAmount);
										aidatToAdd.setAidatStatus(0);
										aidatToAdd.setPayerNo(aidatPayer.getPayerNo());
										try {
											result=dao.addAidatToAidatTable(aidatToAdd);
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}
							else
							{
								Aidat aidatToAdd=new Aidat();
								aidatToAdd.setAidatMonth(aidatMonth);
								aidatToAdd.setAidatYear(aidatYear);
								aidatToAdd.setAidatAmount(aidatAmount);
								aidatToAdd.setAidatStatus(0);
								aidatToAdd.setPayerNo(aidatPayer.getPayerNo());
								try {
									result=dao.addAidatToAidatTable(aidatToAdd);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						if(result>0)
						{
							Alert alert=new Alert(AlertType.INFORMATION);
							alert.setTitle("Bildirim");
							alert.setHeaderText("İşlem Başarılı");
							alert.setContentText("Aidat ay ve yıla göre "
									+ "aydattan önceden sorumlu olmayan"
									+ " bütün kullanıcılara "
									+ "eklenmiştir");
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
							alert.setContentText("İşlem Başarısız. Aidat yıl ve ayının birlikte "
									+ "benzersiz "
									+ "olduğundan, bilgilerin boş olmadığından, "
									+ "ve tüm bilgilerin sıfır ile başlamayan "
									+ "sayılardan oluştuğundan, "
									+ "ve geçerli olduğundan emin olun");
							ButtonType bt=alert.showAndWait().orElse(null);
							if(bt.equals(ButtonType.OK))
							{
								
							}
							return;
						}
						
					}
					else
					{
						Alert alert=new Alert(AlertType.INFORMATION);
						alert.setTitle("Uyarı");
						alert.setHeaderText("Dikkat");
						alert.setContentText("Aidat ödeyebilecek kayıtlı "
								+ "bir mükellef olmadığı için aidat eklenemedi");
						ButtonType bt=alert.showAndWait().orElse(null);
						if(bt.equals(ButtonType.OK))
						{
							
						}
						return;
					}
				}
				else
				{
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Uyarı");
					alert.setHeaderText("Dikkat");
					alert.setContentText("İşlem Başarısız. Aidat yıl ve ayının birlikte "
							+ "benzersiz "
							+ "olduğundan, bilgilerin boş olmadığından, "
							+ "ve tüm bilgilerin sıfır ile başlamayan "
							+ "sayılardan oluştuğundan, "
							+ "ve geçerli olduğundan emin olun");
					ButtonType bt=alert.showAndWait().orElse(null);
					if(bt.equals(ButtonType.OK))
					{
						
					}
					return;
				}
				
				
				
			}
			
		};
		Button btnAidatiKaydet= new Button("Aidatı Kaydet");
		btnAidatiKaydet.setPrefSize(140, 30);
		btnAidatiKaydet.setLayoutX(60);
		btnAidatiKaydet.setLayoutY(190);
		root.getChildren().add(btnAidatiKaydet);
		btnAidatiKaydet.setOnAction(aidatAddingEventHandler);
		
		EventHandler goToMainPageEvent=new EventHandler() 
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
		btnAnaSayfayaDon.setPrefSize(140, 30);
		btnAnaSayfayaDon.setLayoutX(60);
		btnAnaSayfayaDon.setLayoutY(230);
		root.getChildren().add(btnAnaSayfayaDon);
		btnAnaSayfayaDon.setOnAction(goToMainPageEvent);
		
		
	}

}
