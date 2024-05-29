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

public class AidatStatusUpdatingWindow extends Application {

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
		Label label=new Label("Aidat Ayı");
		label.setPrefSize(190, 20);
		label.setLayoutX(10);
		label.setLayoutY(10);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label);
		
		
		ChoiceBox cb=new ChoiceBox();
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

		
		ChoiceBox cb1=new ChoiceBox();
		cb1.setPrefSize(190, 20);
		cb1.setLayoutX(10);
		cb1.setLayoutY(90);
		cb1.getItems().addAll(years);
		root.getChildren().add(cb1);
		
		Label label2=new Label("Aidat Mükellef No");
		label2.setPrefSize(190, 20);
		label2.setLayoutX(10);
		label2.setLayoutY(130);
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
		cb2.setLayoutY(150);
		cb2.getItems().addAll(payerNos);
		root.getChildren().add(cb2);
		
		Label label3=new Label("Aidat Ödenme Durumu");
		label3.setPrefSize(190, 20);
		label3.setLayoutX(10);
		label3.setLayoutY(190);
		//label.setAlignment(Pos.BASELINE_RIGHT);
		root.getChildren().add(label3);
		
		ListView listView=new ListView();
		listView.setPrefSize(190, 60);
		listView.setLayoutX(10);
		listView.setLayoutY(220);
		listView.getItems().addAll("Ödenmedi","Ödendi");
		listView.getSelectionModel().select(0);
		root.getChildren().add(listView);
		
		EventHandler aidatAmountUpdatingEventHandler=new EventHandler<>() 
		{

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				long aidatMonth=1;
				long aidatYear=2000;
				long aidatPayerNo;
				long newAidatStatus=0;
				int result=-1;
				if(cb.getValue().toString().matches("^[1-9]||(1[0-2]$)")
					&&cb1.getValue().toString().matches("^[1-9][0-9]{3}$")
					&&Long.valueOf(cb1.getValue().toString())>2023
					&&cb2.getValue().toString().matches("^[1-9][0-9]{0,}$")
					&&(listView.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("ödendi")
					||listView.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("ödenmedi")))
				{
					aidatMonth=Long.valueOf(cb.getValue().toString());
					aidatYear=Long.valueOf(cb1.getValue().toString());
					aidatPayerNo=Long.valueOf(cb2.getValue().toString());
					if(listView.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("ödendi"))
					{
						newAidatStatus=1;
					}
					try {
						result=dao.updateAidatStatus(aidatMonth, aidatYear, aidatPayerNo, newAidatStatus);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result>0)
					{
						Alert alert=new Alert(AlertType.INFORMATION);
						alert.setTitle("Bildirim");
						alert.setHeaderText("İşlem Başarılı");
						alert.setContentText("Aidat ödenme durumu bilgisi başarı ile güncellenmiştir");
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
		Button btnAidatTutariniGuncelle= new Button("Aidat Ödenme Durumunu Güncelle");
		btnAidatTutariniGuncelle.setPrefSize(240, 30);
		btnAidatTutariniGuncelle.setLayoutX(10);
		btnAidatTutariniGuncelle.setLayoutY(290);
		root.getChildren().add(btnAidatTutariniGuncelle);
		btnAidatTutariniGuncelle.setOnAction(aidatAmountUpdatingEventHandler);
		
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
