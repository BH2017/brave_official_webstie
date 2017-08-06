/**
 * 
 */
package com.braver.t1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
//import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Text;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
//import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author zzf
 *
 */
public class GameJigsawPuzzle extends Application {
	/**���ڰ��ṹ���򷽷�д���ֲ��׶����߼�����
	 * 1.�뷨���Ӻڰ����𽥻ָ�������ɫ������ʹ����������ɫ������̣�
	 * 2.��ImageView��������¼�ʱ�����ڷ���ͼƬ�����ã�����������͸����
	 * 	����Ϊ����ͼƬ�ϣ���ɾ��Ҷ���
	 * 3.���Լ��ظ���ͬ�Ĵ���------
	 */
	/**from:201707111920
	 * @author zzf
	 *end:
	 */
		private final int title_height = 30;
		private final double window_size_width = 800;
		private final double window_size_height = 600;
		private final double window_size_valid_height = window_size_height-title_height;
		private final int font_size_default = 20;
		private final int font_size_middle = 25;
		private final double move_images_top = font_size_default+3;
		private final double move_images_right = window_size_valid_height-move_images_top;
		private final double show_thumbnail_size = window_size_width-move_images_right;
		private final double show_thumbnail_top = window_size_valid_height-show_thumbnail_size;
		private Scene sceneWelcome = null;
		private Group group_welcome_page = null;
		private Scene sceneMenu = null;
		private Group group_menu_page = null;
		private Stage stage_unique=null;
		private ImageView imageView_startGame = null;//�˵���������
		private ImageView imageView_continueGame=null;
		private ImageView imageView_setGame = null;
		private ImageView imageView_rankingList = null;
		private ImageView imageView_gameHelp = null;
		private ImageView imageView_gameAbout = null;
		private ImageView imageView_gameExit = null;
		private Image image_startGame[]=null;
		private Image image_continueGame[]=null;
		private Image image_setGame[]= null;
		private Image image_rankList[] = null;
		private Image image_gameHelp[]= null;
		private Image image_gameAbout[] =null;
		private Image image_cursor[] = null;
		private Scene scene_startGame = null;
		private Group group_startGame = null;
		private Text text_startGameReturn = null;
		private Rectangle rectangle_moveImage = null;
		private Rectangle rectangle_showThumbnail = null;
		
		private void initRectangleShowThumbnail(){
			rectangle_showThumbnail = new Rectangle(move_images_right,
					show_thumbnail_top,show_thumbnail_size,show_thumbnail_size);
			group_startGame.getChildren().add(rectangle_showThumbnail);
			rectangle_showThumbnail.setStroke(Color.BLACK);
			rectangle_showThumbnail.setFill(Color.TRANSPARENT);}
		private void initRectangleMoveImage(){
			rectangle_moveImage = new Rectangle(0,move_images_top,move_images_right,window_size_valid_height);
			group_startGame.getChildren().add(rectangle_moveImage);
			rectangle_moveImage.setStroke(Color.CORNFLOWERBLUE);
			rectangle_moveImage.setFill(Color.TRANSPARENT);}
		private void initTextStartGameReturn(){
			text_startGameReturn = new Text("����");
			group_startGame.getChildren().add(text_startGameReturn);
			text_startGameReturn.setFont(new Font(font_size_default));
			text_startGameReturn.setStroke(Color.BLACK);
			text_startGameReturn.setTranslateX(0);
			text_startGameReturn.setTranslateY(font_size_default);
			text_startGameReturn.setOnMouseEntered(me->{
				text_startGameReturn.setStroke(Color.BLUE);
				text_startGameReturn.setFont(new Font(font_size_middle));});
			text_startGameReturn.setOnMouseExited(me->{
				text_startGameReturn.setStroke(Color.BLACK);
				text_startGameReturn.setFont(new Font(font_size_default));});
			text_startGameReturn.setOnMousePressed(me->{
				text_startGameReturn.setStroke(Color.CRIMSON);});
			text_startGameReturn.setOnMouseReleased(me->{
				text_startGameReturn.setStroke(Color.BLACK);
				stage_unique.setScene(sceneMenu);
			});}
		private void initGroupStartGame(){
			group_startGame = new Group();
			initTextStartGameReturn();
			initRectangleMoveImage();
			initRectangleShowThumbnail();}
		private void initSceneStartGame(){
			initGroupStartGame();
			scene_startGame = new Scene(group_startGame);}
		private void initImageViewGameExit(){
			imageView_gameExit = new ImageView(image_startGame[0]);
			group_menu_page.getChildren().add(imageView_gameExit);
			imageView_gameExit.setVisible(false);}
		private void initImageViewGameAbout(){
			imageView_gameAbout = new ImageView(image_gameAbout[0]);
			group_menu_page.getChildren().add(imageView_gameAbout);
			imageView_gameAbout.setVisible(false);
			imageView_gameAbout.setOnMouseEntered(me->{
				imageView_gameAbout.setImage(image_gameAbout[2]);});
			imageView_gameAbout.setOnMouseExited(me->{
				imageView_gameAbout.setImage(image_gameAbout[0]);});
			imageView_gameAbout.setOnMousePressed(me->{
				imageView_gameAbout.setImage(image_gameAbout[1]);});
			imageView_gameAbout.setOnMouseReleased(me->{
				imageView_gameAbout.setImage(image_gameAbout[2]);});}
		private void initImageViewGameHelp(){
			imageView_gameHelp = new ImageView(image_gameHelp[0]);
			group_menu_page.getChildren().add(imageView_gameHelp);
			imageView_gameHelp.setVisible(false);
			imageView_gameHelp.setOnMouseEntered(me->{
				imageView_gameHelp.setImage(image_gameHelp[2]);});
			imageView_gameHelp.setOnMouseExited(me->{
				imageView_gameHelp.setImage(image_gameHelp[0]);});
			imageView_gameHelp.setOnMousePressed(me->{
				imageView_gameHelp.setImage(image_gameHelp[1]);});
			imageView_gameHelp.setOnMouseReleased(me->{
				imageView_gameHelp.setImage(image_gameHelp[2]);});}
		private void initImageViewRankingList(){
			imageView_rankingList = new ImageView(image_rankList[0]);
			group_menu_page.getChildren().add(imageView_rankingList);
			imageView_rankingList.setVisible(false);
			imageView_rankingList.setOnMouseEntered(me->{
				imageView_rankingList.setImage(image_rankList[2]);});
			imageView_rankingList.setOnMouseExited(me->{
				imageView_rankingList.setImage(image_rankList[0]);});
			imageView_rankingList.setOnMousePressed(me->{
				imageView_rankingList.setImage(image_rankList[1]);});
			imageView_rankingList.setOnMouseReleased(me->{
				imageView_rankingList.setImage(image_rankList[2]);});
		}
		private void initImageViewSetGame(){
			imageView_setGame = new ImageView(image_setGame[0]);
			group_menu_page.getChildren().add(imageView_setGame);
			imageView_setGame.setVisible(false);
			imageView_setGame.setOnMouseEntered(me->{
				imageView_setGame.setImage(image_setGame[2]);});
			imageView_setGame.setOnMouseExited(me->{
				imageView_setGame.setImage(image_setGame[0]);});
			imageView_setGame.setOnMousePressed(me->{
				imageView_setGame.setImage(image_setGame[1]);});
			imageView_setGame.setOnMouseReleased(me->{
				imageView_setGame.setImage(image_setGame[2]);});
		}
		private void initImageViewContinueGame(){
			imageView_continueGame = new ImageView(image_continueGame[0]);
			group_menu_page.getChildren().add(imageView_continueGame);
			imageView_continueGame.setVisible(false);
			imageView_continueGame.setOnMouseEntered(me->{
				imageView_continueGame.setImage(image_continueGame[2]);});
			imageView_continueGame.setOnMouseExited(me->{
				imageView_continueGame.setImage(image_continueGame[0]);});
			imageView_continueGame.setOnMousePressed(me->{
				imageView_continueGame.setImage(image_continueGame[1]);});
			imageView_continueGame.setOnMouseReleased(me->{
				imageView_continueGame.setImage(image_continueGame[2]);});}
		private void menuPageStartSecondFlyInto(){//���ڲ˵�ͼ��ͻȻð���������ͣ�
			imageView_startGame.setTranslateX(0-100);//���������Ҫ���Զ���·��
			imageView_startGame.setTranslateY(0-100);//PathTransmition�ķ�ʽê����widget�е�
			imageView_continueGame.setTranslateX(window_size_width/2);
			imageView_continueGame.setTranslateY(-100);
			imageView_setGame.setTranslateX(900);
			imageView_setGame.setTranslateY(-100);
			imageView_rankingList.setTranslateX(900);
			imageView_rankingList.setTranslateY(window_size_valid_height/2);
			imageView_gameHelp.setTranslateX(900);
			imageView_gameHelp.setTranslateY(window_size_valid_height+100);
			imageView_gameAbout.setTranslateX(-100);
			imageView_gameAbout.setTranslateY(window_size_valid_height+100);
			imageView_startGame.setVisible(true);
			imageView_continueGame.setVisible(true);
			imageView_setGame.setVisible(true);
			imageView_rankingList.setVisible(true);
			imageView_gameHelp.setVisible(true);
			imageView_gameAbout.setVisible(true);
			double height =image_startGame[0].getHeight();//������õĻ�׼�ڵ�һ��ͼƬ�߶�
			double spaceHeight = (window_size_valid_height - height*7)/8;//�߸��˵���,�˸��հ�
			Path path_startGame = new Path(new MoveTo(-100, -100),//·��һ
					new CubicCurveTo(50,100,1450,500,window_size_width/2,
							spaceHeight+image_startGame[0].getHeight()/2));
			Path path_continueGame = new Path(new MoveTo(window_size_width/2,-100),
					new CubicCurveTo(0,0,100,800,
							window_size_width/2,
							spaceHeight*2+image_startGame[0].getHeight()*3/2));
			Path path_setGame = new Path(new MoveTo(900,-100),
					new CubicCurveTo(0, 0, 0, 3, window_size_width/2,
							spaceHeight*3+image_startGame[0].getHeight()*5/2));
			Path path_rankList = new Path(new MoveTo(900,window_size_valid_height/2),
					new CubicCurveTo(0, 1, 2, 3, window_size_width/2,
							spaceHeight*4+image_startGame[0].getHeight()*7/2));
			Path path_gameHelp = new Path(new MoveTo(900, window_size_valid_height+100),
					new CubicCurveTo(0, 1, 2, 3, window_size_width/2,
							spaceHeight*5+image_startGame[0].getHeight()*9/2));
			Path path_gameAbout = new Path(new MoveTo(-100,window_size_valid_height+100),
					new CubicCurveTo(0,1,0,0,window_size_width/2,
							spaceHeight*6+image_startGame[0].getHeight()*11/2));
			PathTransition pathTransition_startGame = new PathTransition(Duration.seconds(2),
					path_startGame,imageView_startGame);
			PathTransition pathTransition_continueGame = new PathTransition(Duration.seconds(2),
					path_continueGame,imageView_continueGame);
			PathTransition pathTransition_setGame = new PathTransition(Duration.seconds(2),
					path_setGame,imageView_setGame);
			PathTransition pathTransition_rankList = new PathTransition(Duration.seconds(2),
					path_rankList,imageView_rankingList);
			PathTransition pathTransition_gameHelp = new PathTransition(Duration.seconds(2),
					path_gameHelp,imageView_gameHelp);
			PathTransition pathTransition_gameAbout = new PathTransition(Duration.seconds(2),
					path_gameAbout,imageView_gameAbout);
			pathTransition_startGame.play();
			pathTransition_continueGame.play();
			pathTransition_setGame.play();
			pathTransition_rankList.play();
			pathTransition_gameHelp.play();
			pathTransition_gameAbout.play();}
		private void loadResourcesImages(){//��ťͼƬ���ţ�1��ͨ�����2�����£�3����껬������
			image_startGame = new Image[3];//������Դ�ڿ���������ʼ��ʱ�����̼߳���.
			image_startGame[0] = new Image("res_this/start/startNormal.png");
			image_startGame[1] = new Image("res_this/start/startPress.png");
			image_startGame[2] = new Image("res_this/start/startHover.png");
			image_continueGame = new Image[3];
			image_continueGame[0] = new Image("res_this/continue/continueNormal.png");
			image_continueGame[1] = new Image("res_this/continue/continuePress.png");
			image_continueGame[2] = new Image("res_this/continue/continueHover.png");
			image_setGame = new Image[3];
			image_setGame[0] = new Image("res_this/set/setNormal.png");
			image_setGame[1]  =new Image("res_this/set/setPress.png");
			image_setGame[2] = new Image("res_this/set/setHover.png");
			image_rankList = new Image[3];
			image_rankList[0] = new Image("res_this/rankList/rankListNormal.png");
			image_rankList[1] = new Image("res_this/rankList/rankListPress.png");
			image_rankList[2] = new Image("res_this/rankList/rankListHover.png");
			image_gameHelp = new Image[3];
			image_gameHelp[0] = new Image("res_this/help/helpNormal.png");
			image_gameHelp[1] = new Image("res_this/help/helpPress.png");
			image_gameHelp[2] = new Image("res_this/help/helpHover.png");
			image_gameAbout = new Image[3];
			image_gameAbout[0] = new Image("res_this/about/aboutNormal.png");
			image_gameAbout[1] = new Image("res_this/about/aboutPress.png");
			image_gameAbout[2] = new Image("res_this/about/aboutHover.png");
			image_cursor = new Image[2];
			image_cursor[0] = new Image("res_this/cursor/cursor_normal.png");
			image_cursor[1] = new Image("res_this/cursor/cursor_press.png");}
		private void initImageViewStartGame(){
			imageView_startGame = new ImageView();
			group_menu_page.getChildren().add(imageView_startGame);
			imageView_startGame.setImage(image_startGame[0]);
			imageView_startGame.setOnMouseEntered(mouseEvent->{
				imageView_startGame.setImage(image_startGame[2]);});
			imageView_startGame.setOnMouseExited(me->{
				imageView_startGame.setImage(image_startGame[0]);});
			imageView_startGame.setOnMousePressed(me->{
				imageView_startGame.setImage(image_startGame[1]);
			});
			imageView_startGame.setOnMouseReleased(me->{
				imageView_startGame.setImage(image_startGame[2]);
				if(scene_startGame == null)
					initSceneStartGame();
				stage_unique.setScene(scene_startGame);
			});
			imageView_startGame.setVisible(false);}		
		private void menuPageStart(){//ת��˵�ҳ̫ͻأ��Ӧ���ɺ�ת����ͬ���ģ�ǰ����β��ʱ��Ӧ��ת��
			Timeline timeline = new Timeline();//��Ϊ������ת���������⿪ʼ��
			timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
					(ActionEvent ae)->{menuPageStartSecondFlyInto();},
					new KeyValue(sceneMenu.fillProperty(), Color.LIGHTGREEN)));
			timeline.play();//����������menu-item��ʼλ��::7�����Ƹ�ͼ��
		}
		private void changeSceneToMenuFirst(Stage stage){
			initSceneMenu();
			stage.setScene(sceneMenu);
			menuPageStart();}
		private void initGroupMenuPage(){//��Ӳ˵�widgets--
			group_menu_page = new Group();
			initImageViewStartGame();
			initImageViewContinueGame();
			initImageViewGameAbout();
			initImageViewGameExit();
			initImageViewGameHelp();
			initImageViewRankingList();
			initImageViewSetGame();}
		private void initSceneMenu(){
			initGroupMenuPage();
			sceneMenu = new Scene(group_menu_page);
			sceneMenu.setFill(Color.BLACK);
			ImageCursor cursor_normal = new ImageCursor(image_cursor[0]);
			ImageCursor cursor_press = new ImageCursor(image_cursor[1]);
			sceneMenu.setCursor(cursor_normal);
			sceneMenu.setOnMousePressed(me->{
				sceneMenu.setCursor(cursor_press);});
			sceneMenu.setOnMouseReleased(me->{
				sceneMenu.setCursor(cursor_normal);});}
		private void welcomePageEnd(){//����ӭҳ��β����
//			int step_changeToDark = 50;//���������²��ܵõ��������ķָ�--������������Ҷ������
//			int time_changeToDark = (int)(window_size_valid_height/step_changeToDark);
			Timeline timeline= new Timeline();
			timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
					(ActionEvent ae)->{changeSceneToMenuFirst(stage_unique);},
					new KeyValue(sceneWelcome.fillProperty(),
					Color.BLACK)));
			timeline.play();}
		private void welcomePageStart(){//�ṩ��ӭҳ��������--ʾ����һ��С����
			Timeline timeline = new Timeline();
			Text text[] = new Text[3];
			String str[]={"��","ӭ","��"};
			int i=0;
			int font_size = 60;
			for(i=0;i<3;i++){
				text[i] = new Text(str[i]);
				group_welcome_page.getChildren().add(text[i]);
				text[i].setFont(new Font(font_size));
			}
			text[0].setTranslateX(0-font_size);
			text[0].setTranslateY(window_size_valid_height/2);
			text[1].setTranslateX(window_size_width/2);
			text[1].setTranslateY(0-font_size);
			text[2].setTranslateX(window_size_width);
			text[2].setTranslateY(window_size_valid_height/2);
			KeyValue keyValue[] = new KeyValue[3];
			keyValue[0] = new KeyValue(text[0].translateXProperty(),
					window_size_width/2-font_size);
			keyValue[1] = new KeyValue(text[1].translateYProperty(),
					window_size_valid_height/2);
			keyValue[2] = new KeyValue(text[2].translateXProperty(),
					window_size_width/2+font_size);
			timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
					(ActionEvent ae)->{welcomePageEnd();},
					keyValue[0],keyValue[1],keyValue[2]));
			timeline.play();
			new Thread(){//���ÿ�������ʱ����غ���ʹ�õ���Դ
				public void run() {loadResourcesImages();};
			}.start();}
		private void initGroupWelcomePage(){//��ʼ����ӭҳ
			group_welcome_page = new Group();
			welcomePageStart();}
		private void initSceneWelcome(){//��ʼ����ӭ����
			initGroupWelcomePage();
			sceneWelcome = new Scene(group_welcome_page);
			sceneWelcome.setFill(Color.LIGHTGRAY);
			sceneWelcome.setCursor(Cursor.NONE);}
		private void initStage(Stage stage){//��ʼ����̨����
			stage_unique = stage;//
			initSceneWelcome();
			stage.setScene(sceneWelcome);
			stage.setWidth(window_size_width);
			stage.setHeight(window_size_height);
			stage.setResizable(false);
			stage.setTitle("ƴͼ-������Ϸ");			
			stage.show();}
		@Override
		public void start(Stage stage) throws Exception {//������̨-����
			// TODO Auto-generated method stub
			initStage(stage);}
		public static void main(String[] args) {//����javafx�ĳ�����main������ֻ��һ�䣺
			// TODO Auto-generated method stub//Ŀǰ����֪�������ģ����а�main�����������
			System.out.println("this is a jigsaw  ");
			launch(args);//����������������Ӧ�÷������������󣬷���ǰ������˳��
		}
	}
