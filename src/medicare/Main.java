package medicare;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import medicare.model.Medicine;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			Button btnLogin = (Button) root.lookup("#btnLogin");
			TextField txtUID = (TextField) root.lookup("#txtUID");
			Label lbTitle = (Label) root.lookup("#lbTitle");
			PasswordField txtPwd = (PasswordField) root.lookup("#txtPwd");
			btnLogin.setOnAction(processLogin -> {
				if (txtUID.getText().trim().equals("") || txtPwd.getText().equals("")) {
					lbTitle.setText("非法的输入");
				} else {
					try {
						if (txtUID.getText().equals("1000")&& txtPwd.getText().equals("admin")) {
							showDashboard();
							primaryStage.hide();
						} else {
							lbTitle.setText("ID 或密码不正确");
						}
					} catch (Exception ex) {
						lbTitle.setText("数据存储文件被占用");
						ex.printStackTrace();
					}
				}
			});
			
			
			
			Storage storage = Storage.getStarted();
			storage.loadFromFile("runtime/storage.json");
			// storage.var_dump();
			//storage.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void showDashboard(){
		Stage dashboardWindow = new Stage();
		Parent dashboardRoot;
		try {
			dashboardRoot = (Parent) FXMLLoader.load(getClass().getResource("Main.fxml"));
		
		Scene scene = new Scene(dashboardRoot);
		dashboardWindow.setTitle("医疗报销管理系统");
		dashboardWindow.setScene(scene);
		dashboardWindow.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
