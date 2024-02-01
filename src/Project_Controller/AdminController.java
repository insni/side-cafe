package Project_Controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import Project_Constructor.Menu;
import Project_DAO.MenuDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class AdminController implements Initializable {

	@FXML
	private TableColumn<Menu, String> ordernameTable;
	@FXML
	private TableColumn<Menu, String> ordernumTable;
	@FXML
	private TableColumn<Menu, String> orderpriceTable;
	@FXML
	private TableView<Menu> orderlist;

	@FXML
	private TableView<Menu> menulist;

	@FXML
	private Button addBtn;
	@FXML
	private Button cancleBtn;
	@FXML
	private Button payBtn;
	@FXML
	private Button backBtn;
	@FXML
	private Label total;
	@FXML
	private Label time;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnRefresh;

	// coffe_tab
	@FXML
	private Tab tab_coffe;

	// coffe_tab's childern
	@FXML
	private TableColumn<Menu, String> idntfNmbrTable;
	@FXML
	private TableColumn<Menu, String> menunameTable;
	@FXML
	private TableColumn<Menu, String> priceTable;
	@FXML
	private TableColumn<Menu, String> countTable;
	@FXML
	private ImageView menuimage;
	@FXML
	private Button btnAddCoffe;
	@FXML
	private Button btnUpdateCoffe;
	@FXML
	private Button btnDeleteCoffe;
	@FXML
	private Button btnAddFood;
	@FXML
	private Button btnUpdateFood;
	@FXML
	private Button btnDeleteFood;

	// tab_food
	@FXML
	private Tab tab_food;

	// tab_food's children
	@FXML
	ImageView menuimage2;
	@FXML
	private TableColumn<Menu, String> idntfNmbrTable2;
	@FXML
	private TableColumn<Menu, String> menunameTable2;
	@FXML
	private TableColumn<Menu, String> priceTable2;
	@FXML
	private TableColumn<Menu, String> countTable2;
	@FXML
	private TableView<Menu> menulist2;

	// tab_drink
	@FXML
	private Tab tab_drink;
	// tab_drink's children
	@FXML
	private TableView<Menu> menulist3;
	@FXML
	private TableColumn<Menu, String> idntfNmbrTable3;
	@FXML
	private TableColumn<Menu, String> menunameTable3;
	@FXML
	private TableColumn<Menu, String> priceTable3;
	@FXML
	private TableColumn<Menu, String> countTable3;
	@FXML
	ImageView menuimage3;
	@FXML
	private Button btnAddDrink;
	@FXML
	private Button btnUpdateDrink;
	@FXML
	private Button btnDeleteDrink;
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	private Stage stage;
	String orderMenu;
	String orderPrice;
	static String selectedMenu;

	int totalPrice;
	String orderNum;
	int selectNum;
	Menu menu;
	ObservableList<Menu> orderList = FXCollections.observableArrayList();
	MenuDao mDao = new MenuDao();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/* tab_coffe */
		tab_coffe.setOnSelectionChanged(event -> {

			idntfNmbrTable.setCellValueFactory(cellData->cellData.getValue().getIdntfNmbrProperty());
			menunameTable.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			priceTable.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			countTable.setCellValueFactory(cellData->cellData.getValue().getCountProperty());
			menulist.setItems(mDao.selectAllCoffe());

			menulist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {
				@Override
				public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
					if (newValue != null) {
						selectedMenu = menulist.getSelectionModel().getSelectedItem().getGoods();
						
//						URL imageURL = getClass().getResource("../images/" + newValue.getGoods() + ".jpg");
						Path imageURL= Paths.get("D://프로젝트_자료/2020_12_16/src/images/" + newValue.getGoods() + ".jpg");
//						URL imageURL = getClass().getResource("D://프로젝트_자료/2020_12_16/src/images/" + newValue.getGoods() + ".jpg");
						boolean haveImage = havingImage(imageURL);
						System.out.println("imageURL");
						System.out.println(imageURL.toString());
						if (haveImage) {
							menuimage.setImage(new Image("file:"+imageURL.toString()));
							
						}

					}

				}

			});

		});

		btnAddCoffe.setOnAction(event -> setMenu(event));
		btnUpdateCoffe.setOnAction(event -> updateMenu(event));
		btnDeleteCoffe.setOnAction(event -> {
			String menuName = menulist.getSelectionModel().getSelectedItem().getGoods();
			mDao.deleteCoffe(menuName);
//			menuimage.setImage(new Image(getClass().getResource("../images/null.png").toString()));
			Path imageURL=Paths.get("D://프로젝트_자료/2020_12_16/src/images/null.png");
			menuimage.setImage(new Image("file:"+imageURL.toString()));
		});

		/* end of tab_coffe */

		/* tab_food */
		tab_food.setOnSelectionChanged(event -> {

			idntfNmbrTable2.setCellValueFactory(cellData->cellData.getValue().getIdntfNmbrProperty());
			menunameTable2.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			priceTable2.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			countTable2.setCellValueFactory(cellData->cellData.getValue().getCountProperty());
			menulist2.setItems(mDao.selectAllFood());

			menulist2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {
				@Override
				public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
					if (newValue != null) {
						selectedMenu = menulist2.getSelectionModel().getSelectedItem().getGoods();
//						URL imageURL = getClass().getResource("../images/" + newValue.getGoods() + ".jpg");
						Path imageURL=Paths.get("D://프로젝트_자료/2020_12_16/src/images/" + newValue.getGoods() + ".jpg");
						
						boolean haveImage = havingImage(imageURL);
						System.out.println("imageURL");
						System.out.println(imageURL.toString());
						if (haveImage) {
							menuimage2.setImage(new Image("file:"+imageURL.toString()));
							
						}

					}

				}
			});
		});

		btnAddFood.setOnAction(event -> setMenu(event));
		btnUpdateFood.setOnAction(event -> updateMenu(event));
		btnDeleteFood.setOnAction(event -> {
			String menuName = menulist2.getSelectionModel().getSelectedItem().getGoods();
			mDao.deleteFood(menuName);
//			menuimage2.setImage(new Image(getClass().getResource("../images/null.png").toString()));
			Path imageURL=Paths.get("D://프로젝트_자료/2020_12_16/src/images/null.png");
			menuimage2.setImage(new Image("file:"+imageURL.toString()));
		});

		/* end of tab_food */

		/* tab_drink */
		tab_drink.setOnSelectionChanged(event -> {

			idntfNmbrTable3.setCellValueFactory(cellData->cellData.getValue().getIdntfNmbrProperty());
			menunameTable3.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			priceTable3.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			countTable3.setCellValueFactory(cellData->cellData.getValue().getCountProperty());
			menulist3.setItems(mDao.selectAllDrink());

			menulist3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {
				@Override
				public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
					if (newValue != null) {
						selectedMenu = menulist3.getSelectionModel().getSelectedItem().getGoods();
//						URL imageURL = getClass().getResource("../images/" + newValue.getGoods() + ".jpg");
						Path imageURL=Paths.get("D://프로젝트_자료/2020_12_16/src/images/" + newValue.getGoods() + ".jpg");
						boolean haveImage = havingImage(imageURL);
						if (haveImage) {
							menuimage3.setImage(new Image("file:"+imageURL.toString()));
						}

					}

				}
			});
		});


		btnAddDrink.setOnAction(event -> setMenu(event));
		btnUpdateDrink.setOnAction(event -> updateMenu(event));
		btnDeleteDrink.setOnAction(event -> {
			String menuName = menulist3.getSelectionModel().getSelectedItem().getGoods();
			mDao.deleteDrink(menuName);
//			menuimage3.setImage(new Image(getClass().getResource("../images/null.png").toString()));
			Path imageURL=Paths.get("D://프로젝트_자료/2020_12_16/src/images/null.png");
			menuimage3.setImage(new Image("file:"+imageURL.toString()));
		});

		/* end of tab_drink */

		/* auto menuRefresh */
		menuRefresh(new ActionEvent());
	}

	// 새로고침이벤트
	public void menuRefresh(ActionEvent event) {
		menulist.setItems(mDao.selectAllCoffe());
		menulist2.setItems(mDao.selectAllFood());
		menulist3.setItems(mDao.selectAllDrink());
	}

	// 추가하기 이벤트
	public void setMenu(ActionEvent event) {
		Stage stage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../Project_fxml/menuAdd.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("메뉴 추가");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 메뉴 수정 이벤트
	public void updateMenu(ActionEvent event) {
		this.stage = new Stage();
		Parent root;
		try {
			this.stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("../Project_fxml/menuUpdate.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("메뉴수정");
			stage.setResizable(false);
			stage.show();
			stage.setTitle("메뉴 수정");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 이미지 있는지 확인
	public boolean havingImage(URL imageURL) {
		if (imageURL == null)
			return false;
		return true;

	}	// 이미지 있는지 확인
	public boolean havingImage(Path imageURL) {
		if (imageURL == null)
			return false;
		return true;

	}
}
