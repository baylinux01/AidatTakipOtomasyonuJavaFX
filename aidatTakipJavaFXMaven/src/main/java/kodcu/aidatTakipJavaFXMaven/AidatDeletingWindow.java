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

public class AidatDeletingWindow extends Application {

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
		List<Aidat> aids=dao.getAllAidats();
		Set<Long> years=new TreeSet<Long>();
		for(Aidat aid:aids)
		{
			years.add(aid.getAidatYear());
		}
		Label label=new Label("Aidat Ayı");
		label.setPrefSize(190, 20);
		label.setLayoutX(10);
		label.setLayoutY(10);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label);
		
		
		ChoiceBox<Integer> cb=new ChoiceBox<Integer>();
		cb.setPrefSize(190, 20);
		cb.setLayoutX(10);
		cb.setLayoutY(30);
		cb.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
		root.getChildren().add(cb);
		
		Label label1=new Label("Aidat Yılı");
		label1.setPrefSize(190, 20);
		label1.setLayoutX(10);
		label1.setLayoutY(70);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label1);

		
		ChoiceBox<Long> cb1=new ChoiceBox<Long>();
		cb1.setPrefSize(190, 20);
		cb1.setLayoutX(10);
		cb1.setLayoutY(90);
		cb1.getItems().addAll(years);
		root.getChildren().add(cb1);
		
		
		
		EventHandler<ActionEvent> aidatDeletingEventHandler=new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				long aidatMonth=1;
				long aidatYear=2000;
				long aidatPayerNo;
				long newAidatStatus=0;
				int result=-1;
				if(cb.getValue().toString().matches("^[1-9]||(1[0-2]$)")
					&&cb1.getValue().toString().matches("^[1-9][0-9]{3}$")
					&&Long.valueOf(cb1.getValue().toString())>2023
					)
				{
					aidatMonth=Long.valueOf(cb.getValue().toString());
					aidatYear=Long.valueOf(cb1.getValue().toString());
					
					try {
						result=dao.deleteAidatFromAidatTable(aidatMonth, aidatYear);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result>0)
					{
						Alert alert=new Alert(AlertType.INFORMATION);
						alert.setTitle("Bildirim");
						alert.setHeaderText("İşlem Başarılı");
						alert.setContentText("Aidat başarı ile silinmiştir");
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
								+ "mevcut bir aidat ile uyuştuğundan, "
								+ "bilgilerin boş olmadığından, "
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
					alert.setContentText("İşlem Başarısız. Aidat yıl ve ayının birlikte "
							+ "mevcut bir aidat ile uyuştuğundan, "
							+ "bilgilerin boş olmadığından, "
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
		Button btnAidatSil = new Button("Aidatı Sil");
		btnAidatSil.setPrefSize(240, 30);
		btnAidatSil.setLayoutX(10);
		btnAidatSil.setLayoutY(130);
		root.getChildren().add(btnAidatSil);
		btnAidatSil.setOnAction(aidatDeletingEventHandler);
		
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
		btnAnaSayfayaDon.setLayoutY(170);
		root.getChildren().add(btnAnaSayfayaDon);
		btnAnaSayfayaDon.setOnAction(goToMainPageEvent);
		
		
	}

}
