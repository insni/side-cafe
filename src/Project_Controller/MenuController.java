package Project_Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import Project_Constructor.Menu;
import Project_DAO.MenuDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.halowd.saveImg.SaveImg;

public class MenuController implements Initializable {

	// 메뉴추가
	@FXML
	private TextField menuField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField photoField;
	@FXML
	private TextField countField;

	// 메뉴수정
	@FXML
	private TextField newMenuNameField;
	@FXML
	private TextField newMenuPriceField;
	@FXML
	private TextField newMenuCountField;
	@FXML
	private TextField idntfNmbrField;
	@FXML
	private TextField newPhotoField;

	@FXML
	private Button addMenuBtn;
	@FXML
	private Button updateMenuBtn;
	@FXML
	private Button deleteMe;
	@FXML
	private Tab tab_coffe;
	@FXML
	private ComboBox<String> comboAdd; // 메뉴추가의 콤보박스
	@FXML
	private ComboBox<String> comboUpdate; // 메뉴수정의 콤보박스
	private MenuDao mDao;
	private Alert alert;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		mDao = new MenuDao();
		alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("");
		alert.setHeaderText("");

	}

	// 메뉴추가
	public void insertMenu(ActionEvent event) {
		if (comboAdd.getValue().equals("")) {
			alert.setHeaderText("카테고리를 선택해주세요 !");
			alert.showAndWait();
			return;
		} else if (idntfNmbrField.getText().trim().equals("")) {
			alert.setHeaderText("상품번호를 입력해주세요 !");
			alert.showAndWait();
			return;
		} else if (menuField.getText().trim().equals("")) {
			alert.setHeaderText("메뉴이름을 입력해주세요 !");
			alert.showAndWait();
			return;
		} else if (priceField.getText().trim().equals("")) {
			alert.setHeaderText("상품가격을 입력해주세요 !");
			alert.showAndWait();
			return;

		} else if (countField.getText().trim().equals("")) {
			alert.setHeaderText("상품수량을 입력해주세요 !");
			alert.showAndWait();
			return;
		} else {
			try {
				Integer.valueOf(idntfNmbrField.getText().trim());
				if (Integer.valueOf(idntfNmbrField.getText().trim()) > 400) {
					throw new Exception();
				}
				switch (comboAdd.getValue()) {
				case "커피":
					if (Integer.valueOf(idntfNmbrField.getText().trim()) > 100
							&& Integer.valueOf(idntfNmbrField.getText().trim()) < 200) {
					} else {
						throw new Exception();
					}
					break;
				case "푸드":
					if (Integer.valueOf(idntfNmbrField.getText().trim()) > 200
							&& Integer.valueOf(idntfNmbrField.getText().trim()) < 300) {
					} else {
						throw new Exception();
					}
					break;
				case "음료":
					if (Integer.valueOf(idntfNmbrField.getText().trim()) > 300
							&& Integer.valueOf(idntfNmbrField.getText().trim()) < 400) {
					} else {
						throw new Exception();
					}
					break;
				default:
					break;
				}

			} catch (NumberFormatException e) {
				alert.setHeaderText("상품번호를 숫자로만 입력해주세요.");
				alert.show();
				e.printStackTrace();
				return;
			} catch (Exception e) {
				alert.setHeaderText("상품번호 안내 \n100~200(커피) \n200~300(푸드) \n300~400(음료)");
				alert.show();
				e.printStackTrace();
				return;
			}
			try {
				for (int i = 0; i < menuField.getText().trim().length(); i++) {
					for (int j = 0; j < 10; j++) {
						if ((j + "").equals(menuField.getText().trim().charAt(i) + ""))
							throw new Exception();
					}
				}
			} catch (Exception e) {
				alert.setHeaderText("메뉴이름을 문자로만 입력해주세요.");
				alert.show();
				e.printStackTrace();
				return;
			}
			try {
				Integer.valueOf(priceField.getText().trim());
			} catch (NumberFormatException e) {
				alert.setHeaderText("메뉴가격을 숫자로만 입력해주세요.");
				alert.show();
				e.printStackTrace();
				return;
			}
			try {
				Integer.valueOf(countField.getText().trim());
			} catch (NumberFormatException e) {
				alert.setHeaderText("메뉴수량을 숫자로만 입력해주세요.");
				alert.show();
				e.printStackTrace();
				return;
			}

			// 메뉴 삽입 전 유효성 검사
			String selectedItem = comboAdd.getSelectionModel().getSelectedItem();
			String idntfNmb = idntfNmbrField.getText().trim();
			String goods = menuField.getText().trim();
			String price = priceField.getText().trim();
			String count = countField.getText().trim();
			if (mDao.verify(menuField.getText().trim()) == 1) {
				alert.setTitle("메뉴이름 중복");
				alert.setHeaderText("사용 중인 메뉴입니다.");
				alert.show();
			} else {
				int listSize = 0;
				// 메뉴 종류에 맞게 삽입
				switch (selectedItem) {
				case "커피":
					MenuDao coffeInserter = new MenuDao();
					listSize = coffeInserter.selectAllCoffe().size();
					for (int i = 0; i < coffeInserter.selectAllCoffe().size(); i++) {
						if (idntfNmbrField.getText().equals(coffeInserter.selectAllCoffe().get(i).getIdntfNmbr())) {
							alert.setHeaderText("등록할 수 없는 상품번호");
							alert.showAndWait();
							break;
						}
					}

					coffeInserter.insert(new Menu(idntfNmb, goods, price, count));
					System.out.println("추가한 메뉴 정보");
					System.out.println(new Menu(idntfNmb, goods, price, count).toString());
					break;
				case "푸드":
					MenuDao foodInserter = new MenuDao();
					listSize = foodInserter.selectAllFood().size();
					idntfNmb = (Integer.valueOf(foodInserter.selectAllFood().get(listSize - 1).getIdntfNmbr()) + 1)
							+ "";
					if (listSize != 0 && idntfNmb != null && idntfNmb != "")
						foodInserter.insert(new Menu(idntfNmb, goods, price, count));
					System.out.println("추가한 메뉴 정보");
					System.out.println(new Menu(idntfNmb, goods, price, count).toString());
					break;
				case "음료":
					MenuDao drinkInserter = new MenuDao();
					listSize = drinkInserter.selectAllDrink().size();
					idntfNmb = (Integer.valueOf(drinkInserter.selectAllDrink().get(listSize - 1).getIdntfNmbr()) + 1)
							+ "";
					if (listSize != 0)
						drinkInserter.insert(new Menu(idntfNmb, goods, price, count));
					System.out.println("추가한 메뉴 정보");
					System.out.println(new Menu(idntfNmb, goods, price, count).toString());
					break;
				default:
					alert.setHeaderText("확인해주세요.");
					this.alert.show();
					break;
				}
				alert.setAlertType(AlertType.CONFIRMATION);
				alert.setHeaderText("메뉴 추가 완료");
				alert.show();
				((Stage) addMenuBtn.getScene().getWindow()).close();
			}
		}
	}

	public void updateMenu(ActionEvent event) {
		// 유효성 검사

		if (comboUpdate.getValue().equals("")) {
			alert.setHeaderText("카테고리를 선택해주세요 !");
			alert.showAndWait();
			return;
		} else if (newMenuNameField.getText().trim().equals("")) {
			alert.setHeaderText("메뉴이름을 입력해주세요 !");
			alert.showAndWait();
			return;
		} else if (newMenuPriceField.getText().trim().equals("")) {
			alert.setHeaderText("상품가격을 입력해주세요 !");
			alert.showAndWait();
			return;

		} else if (newMenuCountField.getText().trim().equals("")) {
			alert.setHeaderText("상품수량을 입력해주세요 !");
			alert.showAndWait();
			return;
		} else {
			try {
				for (int i = 0; i < newMenuNameField.getText().trim().length(); i++) {
					for (int j = 0; j < 10; j++) {
						if ((j + "").equals(newMenuNameField.getText().trim().charAt(i) + ""))
							throw new Exception();
					}
				}
			} catch (Exception e) {
				alert.setHeaderText("메뉴이름을 문자로만 입력해주세요.");
				alert.show();
				e.printStackTrace();
				return;
			}
			try {
				Integer.valueOf(newMenuPriceField.getText().trim());
			} catch (NumberFormatException e) {
				alert.setHeaderText("메뉴가격을 숫자로만 입력해주세요.");
				alert.show();
				e.printStackTrace();
				return;
			}
			try {
				Integer.valueOf(newMenuCountField.getText().trim());
			} catch (NumberFormatException e) {
				alert.setHeaderText("메뉴수량을 숫자로만 입력해주세요.");
				alert.show();
				e.printStackTrace();
				return;
			}

			// 메뉴 수정전 유효성 검사
			String selectedItem = comboUpdate.getSelectionModel().getSelectedItem();
			String goods = newMenuNameField.getText().trim();
			int price = Integer.valueOf(newMenuPriceField.getText().trim());
			int count = Integer.valueOf(newMenuCountField.getText().trim());

			switch (selectedItem) {
			case "커피":
				MenuDao coffeUpdater = new MenuDao();
				if (coffeUpdater.update(AdminController.selectedMenu, goods, price, count) != 1)
					alert.show();
				else
					coffeUpdater.update(AdminController.selectedMenu, goods, price, count);
				break;
			case "푸드":
				MenuDao foodUpdater = new MenuDao();
				if (foodUpdater.update(AdminController.selectedMenu, goods, price, count) != 1)
					alert.show();
				else
					foodUpdater.update(AdminController.selectedMenu, goods, price, count);
				break;
			case "음료":
				MenuDao drinkUpdater = new MenuDao();
				if (drinkUpdater.update(AdminController.selectedMenu, goods, price, count) == 1)
					alert.show();
				else
					drinkUpdater.update(AdminController.selectedMenu, goods, price, count);
				break;
			}
			


			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("메뉴 수정");
			alert.setHeaderText("메뉴 수정 완료");
			alert.show();
			Stage root = (Stage) updateMenuBtn.getScene().getWindow();
			root.close();
		}
	}

	public void fileChoose() throws Exception {
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("이미지 선택");

			fc.setInitialDirectory(new File("D:/"));


			ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
			fc.getExtensionFilters().add(imgType);
			File selectedFile = fc.showOpenDialog(null);
			
			if (selectedFile != null) {
				photoField.setText(selectedFile.toURI().toString());
				Path imageURL = Paths.get("D://프로젝트_자료/2020_12_16/src/images/" + menuField.getText() + ".jpg");
				System.out.println("imageURL : "+imageURL.toString());
				if(!imageURL.toString().equals("")){
					new File(imageURL.toString()).delete();
					alert.setHeaderText("등록된 기존 이미지파일 삭제");
					new File(imageURL.toString()).delete();
					alert.show();
				}

				System.out.println("menuField.getText() : "+menuField.getText());
				SaveImg saveImg = new SaveImg();
				String file = selectedFile.toURI().toString();
				String path = "/프로젝트_자료/2020_12_16/src/images/";
				
				int result = saveImg.saveImgFromUrl(file, path,menuField.getText().trim());
				if (result == 1) {
					System.out.println("저장된경로 : " + saveImg.getPath());
					System.out.println("저장된파일이름 : " + saveImg.getSavedFileName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeFile() throws Exception {
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("이미지 선택");

			fc.setInitialDirectory(new File("D:/"));


			ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
			fc.getExtensionFilters().add(imgType);
			File selectedFile = fc.showOpenDialog(null);

			if (selectedFile != null) {
				newPhotoField.setText(selectedFile.toURI().toString());
				
				Path imageURL = Paths.get("D://프로젝트_자료/2020_12_16/src/images/" + AdminController.selectedMenu + ".jpg");
				Path newImageURL = Paths
						.get("D://프로젝트_자료/2020_12_16/src/images/" + newMenuNameField.getText().trim() + ".jpg");
				if(imageURL.toString().equals(newImageURL.toString())) {
					new File(imageURL.toString()).delete();
					alert.setHeaderText("동일한이름으로 존재하는 파일을 삭제하겠습니다.");
					alert.show();
				}
				SaveImg saveImg = new SaveImg();
				String file = selectedFile.toURI().toString();
				String path = "/프로젝트_자료/2020_12_16/src/images/";
				System.out.println("String file = selectedFile.toURI().toString()");
				System.out.println(file);
				
				int result = saveImg.saveImgFromUrl(file, path,newMenuNameField.getText());
				System.out.println("saveFileName : "+saveImg.getSavedFileName());
				if (result == 1) {
					System.out.println("저장된경로 : " + saveImg.getPath());
					System.out.println("저장된파일이름 : " + saveImg.getSavedFileName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}