package com.braver.t2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ImageCutApart extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub 
		Scene scene = new Scene(getGroup());
		primaryStage.setTitle("�ָ�ͼƬ");
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
//		primaryStage.setOpacity(0.5);
		primaryStage.show();
	}
	public Group getGroup(){
		Group group = new Group();
//		Button button = new Button("ѧУ");
//		group.getChildren().add(button);
//		button.setOpacity(1.0);
		return group;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
