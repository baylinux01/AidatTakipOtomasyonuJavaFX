package kodcu.aidatTakipJavaFXMaven;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
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

public class PayerDeletingWindow extends Application {

	Robot robot;
	TextField textField,textField1,textField2;
	TextArea textArea;
	List<AidatPayer> payers;
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
	
		
		Label label2=new Label("Aidat Mükellef No");
		label2.setPrefSize(190, 20);
		label2.setLayoutX(10);
		label2.setLayoutY(10);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label2);
		
		payers=dao.getAllAidatPayers();
		List<Long> payerNos=new ArrayList<Long>();
		for(AidatPayer payer:payers)
		{
			payerNos.add(payer.getPayerNo());
		}
		ChoiceBox<Long> cb=new ChoiceBox<Long>();
		cb.setPrefSize(190, 20);
		cb.setLayoutX(10);
		cb.setLayoutY(50);
		cb.getItems().addAll(payerNos);
		root.getChildren().add(cb);
		
		
		
		EventHandler<ActionEvent> payerDeletingEventHandler=new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				long payer1No=Long.valueOf(cb.getValue().toString());
				int result=-1;
				AidatPayer payer1=null;
				List<Aidat> aids=null;
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Uyarı");
				alert.setHeaderText("Dikkat");
				alert.setContentText("Mükellefi Silmek Üzeresiniz. "
						+ "Mükellef silinirse kayıtlı aidat bilgileri de "
						+ "silinecektir. Onaylıyor musunuz?");
				ButtonType bt=alert.showAndWait().orElse(null);
				if(bt.equals(ButtonType.OK))
				{
					
						try {
							result=dao.deleteAidatsOfAnAidatPayerFromAidatTable(payer1No);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						try {
							result=dao.deleteFromAidatPayerTable(payer1No);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(result>0)
						{
							try {
								payers=dao.getAllAidatPayers();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							payerNos.clear();
							for(AidatPayer payer:payers)
							{
								payerNos.add(payer.getPayerNo());
							}
							ChoiceBox<Long> cb=new ChoiceBox<Long>();
							cb.setPrefSize(190, 20);
							cb.setLayoutX(10);
							cb.setLayoutY(50);
							cb.getItems().addAll(payerNos);
							root.getChildren().add(cb);
							
							Alert alert1=new Alert(AlertType.INFORMATION);
							alert1.setTitle("Bildirim");
							alert1.setHeaderText("İşlem Başarılı");
							alert1.setContentText("Mükellef Başarıyla Silinmiştir");
							ButtonType bt1=alert1.showAndWait().orElse(null);
							if(bt1.equals(ButtonType.OK))
							{
								
							}
							return;
						
					}
					else 
					{
						Alert alert1=new Alert(AlertType.INFORMATION);
						alert1.setTitle("Uyarı");
						alert1.setHeaderText("Dikkat");
						alert1.setContentText("İşlem Başarısız. Bilgilerde hata olmadığından emin olun");
						ButtonType bt1=alert1.showAndWait().orElse(null);
						if(bt1.equals(ButtonType.OK))
						{
							
						}
						return;
					}
			
				}else
				return;
				
				
				
				
			}
			
		};
		Button btnMukellefiSil= new Button("Mükellefi Sil");
		btnMukellefiSil.setPrefSize(240, 30);
		btnMukellefiSil.setLayoutX(10);
		btnMukellefiSil.setLayoutY(290);
		root.getChildren().add(btnMukellefiSil);
		btnMukellefiSil.setOnAction(payerDeletingEventHandler);
		
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
		btnAnaSayfayaDon.setPrefSize(240, 30);
		btnAnaSayfayaDon.setLayoutX(10);
		btnAnaSayfayaDon.setLayoutY(330);
		root.getChildren().add(btnAnaSayfayaDon);
		btnAnaSayfayaDon.setOnAction(goToMainPageEvent);
		
		
	}

}
