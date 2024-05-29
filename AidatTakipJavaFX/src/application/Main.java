package application;
	
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;



import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	ChoiceBox cb;
	TableView tableView,tableView1,tableView2;
	TextField textField1,textField2,textField3,textField4,textField5;
	TextArea textArea;
	long mukellefinOdenmemisToplami;
	long toplamOdenmemisAidat;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,1200,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Dao dao=new Dao();
			dao.createDatabase();
			dao.createAidatPayerTable();
			dao.createAidatTable();
			EventHandler openAidatPayerAddingWindowEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Group rootOpenAidatPayerAddingWindow=new Group();
					Scene sceneOpenAidatPayerAddingWindow=new Scene(rootOpenAidatPayerAddingWindow,400,400);
					Stage stageOpenAidatPayerAddingWindow=new Stage();
					stageOpenAidatPayerAddingWindow.setScene(sceneOpenAidatPayerAddingWindow);
					stageOpenAidatPayerAddingWindow.setTitle("Aidat Mükellefi Ekleme Sayfası");
					//stageOpenAidatPayerAddingWindow.show();
					AidatPayerAddingWindow openAidatPayerAddingWindow=new AidatPayerAddingWindow();
					try {
						openAidatPayerAddingWindow.start(stageOpenAidatPayerAddingWindow);
						primaryStage.hide();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			Button btnMukellefEkle= new Button("Mükellef Ekle");
			btnMukellefEkle.setPrefSize(240, 30);
			btnMukellefEkle.setLayoutX(10);
			btnMukellefEkle.setLayoutY(10);
			root.getChildren().add(btnMukellefEkle);
			btnMukellefEkle.setOnAction(openAidatPayerAddingWindowEventHandler);
			
			EventHandler openAidatAddingWindowEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Group rootOpenAidatAddingWindow=new Group();
					Scene sceneOpenAidatAddingWindow=new Scene(rootOpenAidatAddingWindow,400,400);
					Stage stageOpenAidatAddingWindow=new Stage();
					stageOpenAidatAddingWindow.setScene(sceneOpenAidatAddingWindow);
					stageOpenAidatAddingWindow.setTitle("Mükelleflere Aidat Ekleme Sayfası");
					//stageOpenAidatPayerAddingWindow.show();
					AidatAddingWindow openAidatAddingWindow=new AidatAddingWindow();
					try {
						openAidatAddingWindow.start(stageOpenAidatAddingWindow);
						primaryStage.hide();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			Button btnAidatEklemeSayfasiniAc= new Button("Aidat Ekle");
			btnAidatEklemeSayfasiniAc.setPrefSize(240, 30);
			btnAidatEklemeSayfasiniAc.setLayoutX(10);
			btnAidatEklemeSayfasiniAc.setLayoutY(50);
			root.getChildren().add(btnAidatEklemeSayfasiniAc);
			btnAidatEklemeSayfasiniAc.setOnAction(openAidatAddingWindowEventHandler);
			
			EventHandler openAidatAmountUpdatingWindowEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Group rootOpenAidatAmountUpdatingWindow=new Group();
					Scene sceneOpenAidatAmountUpdatingWindow=new Scene(rootOpenAidatAmountUpdatingWindow,400,400);
					Stage stageOpenAidatAmountUpdatingWindow=new Stage();
					stageOpenAidatAmountUpdatingWindow.setScene(sceneOpenAidatAmountUpdatingWindow);
					stageOpenAidatAmountUpdatingWindow.setTitle("Aidat Tutarını Güncelleme Sayfası");
					//stageOpenAidatPayerAddingWindow.show();
					AidatAmountUpdatingWindow openAidatAmountUpdatingWindow=new AidatAmountUpdatingWindow();
					try {
						openAidatAmountUpdatingWindow.start(stageOpenAidatAmountUpdatingWindow);
						primaryStage.hide();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			Button btnAidatMiktariniGuncellemeSayfasiniAc= new Button("Aidat Miktarını Güncelle");
			btnAidatMiktariniGuncellemeSayfasiniAc.setPrefSize(240, 30);
			btnAidatMiktariniGuncellemeSayfasiniAc.setLayoutX(10);
			btnAidatMiktariniGuncellemeSayfasiniAc.setLayoutY(90);
			root.getChildren().add(btnAidatMiktariniGuncellemeSayfasiniAc);
			btnAidatMiktariniGuncellemeSayfasiniAc.setOnAction(openAidatAmountUpdatingWindowEventHandler);
			
			EventHandler openAidatStatusUpdatingWindowEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Group rootOpenAidatStatusUpdatingWindow=new Group();
					Scene sceneOpenAidatStatusUpdatingWindow=new Scene(rootOpenAidatStatusUpdatingWindow,400,400);
					Stage stageOpenAidatStatusUpdatingWindow=new Stage();
					stageOpenAidatStatusUpdatingWindow.setScene(sceneOpenAidatStatusUpdatingWindow);
					stageOpenAidatStatusUpdatingWindow.setTitle("Aidat Ödenme Durumunu Güncelleme Sayfası");
					//stageOpenAidatPayerAddingWindow.show();
					AidatStatusUpdatingWindow openAidatStatusUpdatingWindow=new AidatStatusUpdatingWindow();
					try {
						openAidatStatusUpdatingWindow.start(stageOpenAidatStatusUpdatingWindow);
						primaryStage.hide();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			Button btnAidatOdenmeDurumunuGuncellemeSayfasiniAc= new Button("Aidat Ödenme Durumunu Güncelle");
			btnAidatOdenmeDurumunuGuncellemeSayfasiniAc.setPrefSize(240, 30);
			btnAidatOdenmeDurumunuGuncellemeSayfasiniAc.setLayoutX(10);
			btnAidatOdenmeDurumunuGuncellemeSayfasiniAc.setLayoutY(130);
			root.getChildren().add(btnAidatOdenmeDurumunuGuncellemeSayfasiniAc);
			btnAidatOdenmeDurumunuGuncellemeSayfasiniAc.setOnAction(openAidatStatusUpdatingWindowEventHandler);
			
			EventHandler openPayerUpdatingWindowEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Group rootOpenPayerUpdatingWindow=new Group();
					Scene sceneOpenPayerUpdatingWindow=new Scene(rootOpenPayerUpdatingWindow,400,400);
					Stage stageOpenPayerUpdatingWindow=new Stage();
					stageOpenPayerUpdatingWindow.setScene(sceneOpenPayerUpdatingWindow);
					stageOpenPayerUpdatingWindow.setTitle("Mükellef Bilgilerini Güncelleme Sayfası");
					//stageOpenAidatPayerAddingWindow.show();
					PayerUpdatingWindow openPayerUpdatingWindow=new PayerUpdatingWindow();
					try {
						openPayerUpdatingWindow.start(stageOpenPayerUpdatingWindow);
						primaryStage.hide();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			Button btnMukellefGuncellemeSayfasiniAc= new Button("Mükellef Bilgilerini Güncelle");
			btnMukellefGuncellemeSayfasiniAc.setPrefSize(240, 30);
			btnMukellefGuncellemeSayfasiniAc.setLayoutX(10);
			btnMukellefGuncellemeSayfasiniAc.setLayoutY(170);
			root.getChildren().add(btnMukellefGuncellemeSayfasiniAc);
			btnMukellefGuncellemeSayfasiniAc.setOnAction(openPayerUpdatingWindowEventHandler);
			
			
			EventHandler openAidatDeletingWindowEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Group rootOpenAidatDeletingWindow=new Group();
					Scene sceneOpenAidatDeletingWindow=new Scene(rootOpenAidatDeletingWindow,400,400);
					Stage stageOpenAidatDeletingWindow=new Stage();
					stageOpenAidatDeletingWindow.setScene(sceneOpenAidatDeletingWindow);
					stageOpenAidatDeletingWindow.setTitle("Aidat Silme Sayfası");
					//stageOpenAidatPayerAddingWindow.show();
					AidatDeletingWindow openAidatDeletingWindow=new AidatDeletingWindow();
					try {
						openAidatDeletingWindow.start(stageOpenAidatDeletingWindow);
						primaryStage.hide();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			Button btnAidatSilmeSayfasiniAc= new Button("Aidat Sil");
			btnAidatSilmeSayfasiniAc.setPrefSize(240, 30);
			btnAidatSilmeSayfasiniAc.setLayoutX(10);
			btnAidatSilmeSayfasiniAc.setLayoutY(210);
			root.getChildren().add(btnAidatSilmeSayfasiniAc);
			btnAidatSilmeSayfasiniAc.setOnAction(openAidatDeletingWindowEventHandler);
			
			EventHandler openPayerDeletingWindowEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Group rootOpenPayerDeletingWindow=new Group();
					Scene sceneOpenPayerDeletingWindow=new Scene(rootOpenPayerDeletingWindow,400,400);
					Stage stageOpenPayerDeletingWindow=new Stage();
					stageOpenPayerDeletingWindow.setScene(sceneOpenPayerDeletingWindow);
					stageOpenPayerDeletingWindow.setTitle("Mükellef Silme Sayfası");
					//stageOpenAidatPayerAddingWindow.show();
					PayerDeletingWindow openPayerDeletingWindow=new PayerDeletingWindow();
					try {
						openPayerDeletingWindow.start(stageOpenPayerDeletingWindow);
						primaryStage.hide();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			};
			Button btnMukellefSilmeSayfasiniAc= new Button("Mükellef Sil");
			btnMukellefSilmeSayfasiniAc.setPrefSize(240, 30);
			btnMukellefSilmeSayfasiniAc.setLayoutX(10);
			btnMukellefSilmeSayfasiniAc.setLayoutY(250);
			root.getChildren().add(btnMukellefSilmeSayfasiniAc);
			btnMukellefSilmeSayfasiniAc.setOnAction(openPayerDeletingWindowEventHandler);
			
			EventHandler clearDBEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					int result=-1;
					Alert alert=new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Uyarı");
					alert.setHeaderText("Dikkat");
					alert.setContentText("Bu işlemi yaparsanız veri tabanı sıfırlanacaktır. "
							+ "Onaylıyor musunuz?");
					ButtonType bt=alert.showAndWait().orElse(null);
					if(bt.equals(ButtonType.OK))
					{
						try {
							result=dao.clearDB();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mukellefinOdenmemisToplami=0;
						toplamOdenmemisAidat=0;
						textField1.setText(mukellefinOdenmemisToplami+"");
						textField2.setText(toplamOdenmemisAidat+"");
						tableView1.getItems().clear();
						tableView.getItems().clear();
						//cb.getItems().clear();
						textField3.setText("");
						textField4.setText("");
						textArea.setText("");
						if(result>0)
						{
							Alert alert1=new Alert(AlertType.INFORMATION);
							alert1.setTitle("Bildirim");
							alert1.setHeaderText("İşlem Başarılı");
							alert1.setContentText("Veri tabanı başarıyla sıfırlanmıştır");
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
							alert1.setHeaderText("İşlem Başarısız");
							alert1.setContentText("Bir hata oluştu");
							ButtonType bt1=alert1.showAndWait().orElse(null);
							if(bt1.equals(ButtonType.OK))
							{
								
							}
							return;
						}
					}
					return;
					
					
				}
				
			};
			Button btnveriTabaniniTemizle= new Button("Veri tabanını sıfırla");
			btnveriTabaniniTemizle.setPrefSize(240, 30);
			btnveriTabaniniTemizle.setLayoutX(10);
			btnveriTabaniniTemizle.setLayoutY(290);
			root.getChildren().add(btnveriTabaniniTemizle);
			btnveriTabaniniTemizle.setOnAction(clearDBEventHandler);
			
			Label label3=new Label("Mükellef Adı Soyadı");
			label3.setPrefSize(190, 20);
			label3.setLayoutX(10);
			label3.setLayoutY(330);
			//label.setAlignment(Pos.BASELINE_RIGHT);
			root.getChildren().add(label3);
			
			textField3=new TextField();
			textField3.setPrefSize(190, 20);
			textField3.setLayoutX(10);
			textField3.setLayoutY(350);
			textField3.setEditable(false);
			root.getChildren().add(textField3);
			
			Label label4=new Label("Mükellef Telefon");
			label4.setPrefSize(190, 20);
			label4.setLayoutX(10);
			label4.setLayoutY(390);
			//label.setAlignment(Pos.BASELINE_RIGHT);
			root.getChildren().add(label4);
			
			textField4=new TextField();
			textField4.setPrefSize(190, 20);
			textField4.setLayoutX(10);
			textField4.setLayoutY(410);
			textField4.setEditable(false);
			root.getChildren().add(textField4);
			
			Label label5=new Label("Mükellef Adresi");
			label5.setPrefSize(190, 20);
			label5.setLayoutX(10);
			label5.setLayoutY(450);
			//label.setAlignment(Pos.BASELINE_RIGHT);
			root.getChildren().add(label5);
			
			textArea=new TextArea();
			textArea.setPrefSize(250, 120);
			textArea.setLayoutX(10);
			textArea.setLayoutY(470);
			textArea.setWrapText(true);
			textArea.setEditable(false);
			root.getChildren().add(textArea);
			
			List<AidatPayer> payers=dao.getAllAidatPayers();
			Set<Long> payerNos=new TreeSet();
			for(AidatPayer payer:payers)
			{
				payerNos.add(payer.getPayerNo());
			}	
			List<Aidat>		 aids  =dao.getAllAidats();
			
			Label label=new Label("Mükellef No Seçiniz");
			label.setPrefSize(190, 20);
			label.setLayoutX(300);
			label.setLayoutY(10);
			//label.setAlignment(Pos.BASELINE_RIGHT);
			root.getChildren().add(label);
			
			
			
			
			
			EventHandler fillTableViewEventHandler=new EventHandler<>() 
			{

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					tableView.getItems().clear();
					long payerNo=Long.valueOf(cb.getValue().toString());
					List<Aidat> aidats=null;
					mukellefinOdenmemisToplami=0;
					List<AidatToShow> aidatsToShow=new ArrayList();
					AidatPayer payer=null;
					try {
						payer=dao.getAidatPayerByPayerNo(payerNo);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						aidats=dao.getAidatsByPayerNo(payerNo);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(Aidat aidat:aidats)
					{
						AidatToShow aidatToShow=new AidatToShow();
						aidatToShow.setAidatId(aidat.getAidatId());
						aidatToShow.setAidatMonth(aidat.getAidatMonth());
						aidatToShow.setAidatYear(aidat.getAidatYear());
						aidatToShow.setAidatAmount(aidat.getAidatAmount());
						aidatToShow.setPayerNo(aidat.getPayerNo());
						if(aidat.getAidatStatus()>0) aidatToShow.setAidatStatus("Ödendi");
						else 
						{
							aidatToShow.setAidatStatus("Ödenmedi");
							mukellefinOdenmemisToplami+=aidat.getAidatAmount();
						}
						aidatsToShow.add(aidatToShow);
					}
					textField1.setText(mukellefinOdenmemisToplami+"");
					tableView.getItems().addAll(aidatsToShow);
					textField3.setText(payer.getPayerName());
					textField4.setText(payer.getPayerPhone());
					textArea.setText(payer.getPayerAddress());
				}
				
			};
			
			
			cb=new ChoiceBox();
			cb.setPrefSize(190, 20);
			cb.setLayoutX(300);
			cb.setLayoutY(30);
			cb.getItems().addAll(payerNos);
			root.getChildren().add(cb);
			cb.setOnAction(fillTableViewEventHandler);
			
			tableView=new TableView<AidatToShow>();
			tableView.setPrefSize(420, 520);
			tableView.setLayoutX(300);
			tableView.setLayoutY(70);
			root.getChildren().add(tableView);
			
			TableColumn<AidatToShow, Long> col1=new TableColumn<AidatToShow, Long>("Aidat Ayı");
			TableColumn<AidatToShow, Long> col2=new TableColumn<AidatToShow, Long>("Aidat Yılı");
			TableColumn<AidatToShow, Long> col3=new TableColumn<AidatToShow, Long>("Aidat Tutarı");
			TableColumn<AidatToShow, String> col4=new TableColumn<AidatToShow, String>("Ödenme Durumu");
			TableColumn<AidatToShow, Long> col5=new TableColumn<AidatToShow, Long>("Mükellef No");
			
			col1.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("aidatMonth"));
			col2.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("aidatYear"));
			col3.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("aidatAmount"));
			col4.setCellValueFactory(new PropertyValueFactory<AidatToShow, String>("aidatStatus"));
			col5.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("payerNo"));
			
			
//			col1.setMinWidth(50);
//			col1.setMaxWidth(50);
//			col2.setMinWidth(50);
//			col2.setMaxWidth(50);
//			col3.setMaxWidth(50);
			col4.setMinWidth(100);
//			col4.setMaxWidth(50);
//			col5.setMaxWidth(50);
			
			col1.setStyle("-fx-font:normal bold 10px 'serif' ");
			col2.setStyle("-fx-font:normal bold 10px 'serif' ");
			col3.setStyle("-fx-font:normal bold 10px 'serif' ");
			col4.setStyle("-fx-font:normal bold 10px 'serif' ");
			col5.setStyle("-fx-font:normal bold 10px 'serif' ");
			
			tableView.getColumns().add(col1);
			tableView.getColumns().add(col2);
			tableView.getColumns().add(col3);
			tableView.getColumns().add(col4);
			tableView.getColumns().add(col5);
			//tableview.autosize();
			
			Label label1=new Label("Mükellefin Ödenmemiş Toplamı:");
			label1.setPrefSize(210, 20);
			label1.setLayoutX(510);
			label1.setLayoutY(10);
			//label.setAlignment(Pos.BASELINE_RIGHT);
			root.getChildren().add(label1);
			
			textField1=new TextField();
			textField1.setPrefSize(210, 20);
			textField1.setLayoutX(510);
			textField1.setLayoutY(30);
			textField1.setEditable(false);
			textField1.setText(mukellefinOdenmemisToplami+"");
			root.getChildren().add(textField1);
			
			List<Aidat> aidats=dao.getAllAidats();
			List<AidatToShow> aidatsToShow=new ArrayList();
			long toplamOdenmemisAidat=0;
			for(Aidat aidat:aidats)
			{
				AidatToShow aidatToShow=new AidatToShow();
				aidatToShow.setAidatId(aidat.getAidatId());
				aidatToShow.setAidatMonth(aidat.getAidatMonth());
				aidatToShow.setAidatYear(aidat.getAidatYear());
				aidatToShow.setAidatAmount(aidat.getAidatAmount());
				aidatToShow.setPayerNo(aidat.getPayerNo());
				if(aidat.getAidatStatus()>0) aidatToShow.setAidatStatus("Ödendi");
				else 
				{
					aidatToShow.setAidatStatus("Ödenmedi");
					toplamOdenmemisAidat+=aidat.getAidatAmount();
				}
				aidatsToShow.add(aidatToShow);
			}
			Comparator<AidatToShow> myComparator=Comparator.comparing(AidatToShow::getAidatYear)
					.thenComparing(AidatToShow::getAidatMonth);
			List<AidatToShow> sortedAidatsToShow=aidatsToShow.stream()
					.sorted(myComparator).collect(Collectors.toList());
			
			
			
			
			tableView1=new TableView<AidatToShow>();
			tableView1.setPrefSize(420, 520);
			tableView1.setLayoutX(740);
			tableView1.setLayoutY(70);
			root.getChildren().add(tableView1);
			
			TableColumn<AidatToShow, Long> col11=new TableColumn<AidatToShow, Long>("Aidat Ayı");
			TableColumn<AidatToShow, Long> col22=new TableColumn<AidatToShow, Long>("Aidat Yılı");
			TableColumn<AidatToShow, Long> col33=new TableColumn<AidatToShow, Long>("Aidat Tutarı");
			TableColumn<AidatToShow, String> col44=new TableColumn<AidatToShow, String>("Ödenme Durumu");
			TableColumn<AidatToShow, Long> col55=new TableColumn<AidatToShow, Long>("Mükellef No");
			
			col11.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("aidatMonth"));
			col22.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("aidatYear"));
			col33.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("aidatAmount"));
			col44.setCellValueFactory(new PropertyValueFactory<AidatToShow, String>("aidatStatus"));
			col55.setCellValueFactory(new PropertyValueFactory<AidatToShow, Long>("payerNo"));
			
			
//			col11.setMinWidth(50);
//			col11.setMaxWidth(50);
//			col22.setMinWidth(50);
//			col22.setMaxWidth(50);
//			col33.setMaxWidth(50);
			col44.setMinWidth(100);
//			col44.setMaxWidth(50);
//			col55.setMaxWidth(50);
			
			col11.setStyle("-fx-font:normal bold 10px 'serif' ");
			col22.setStyle("-fx-font:normal bold 10px 'serif' ");
			col33.setStyle("-fx-font:normal bold 10px 'serif' ");
			col44.setStyle("-fx-font:normal bold 10px 'serif' ");
			col55.setStyle("-fx-font:normal bold 10px 'serif' ");
			
			tableView1.getColumns().add(col11);
			tableView1.getColumns().add(col22);
			tableView1.getColumns().add(col33);
			tableView1.getColumns().add(col44);
			tableView1.getColumns().add(col55);
			//tableview1.autosize();
			tableView1.getItems().addAll(sortedAidatsToShow);
			
			Label label2=new Label("Ödenmemiş Toplam:");
			label2.setPrefSize(210, 20);
			label2.setLayoutX(740);
			label2.setLayoutY(10);
			//label.setAlignment(Pos.BASELINE_RIGHT);
			root.getChildren().add(label2);
			
			textField2=new TextField();
			textField2.setPrefSize(210, 20);
			textField2.setLayoutX(740);
			textField2.setLayoutY(30);
			textField2.setEditable(false);
			textField2.setText(toplamOdenmemisAidat+"");
			root.getChildren().add(textField2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		launch(args);
		
	}
}
