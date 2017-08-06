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
	/**类内按结构程序方法写，分层易懂，逻辑清晰
	 * 1.想法：从黑暗中逐渐恢复到正常色，可以使用正常到黑色的逆过程，
	 * 2.以ImageView设置鼠标事件时，由于放置图片做不好，导致字上有透明，
	 * 	被认为不在图片上，造成剧烈抖动
	 * 3.可以简化重复相同的代码------
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
		private ImageView imageView_startGame = null;//菜单项有七项
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
			text_startGameReturn = new Text("返回");
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
		private void menuPageStartSecondFlyInto(){//由于菜单图标突然冒出来不缓和，
			imageView_startGame.setTranslateX(0-100);//这个动画需要用自定义路径
			imageView_startGame.setTranslateY(0-100);//PathTransmition的方式锚点在widget中点
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
			double height =image_startGame[0].getHeight();//这里采用的基准于第一张图片高度
			double spaceHeight = (window_size_valid_height - height*7)/8;//七个菜单项,八个空白
			Path path_startGame = new Path(new MoveTo(-100, -100),//路径一
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
		private void loadResourcesImages(){//按钮图片三张，1普通情况，2，按下，3，鼠标滑动上面
			image_startGame = new Image[3];//加载资源在开场动画开始的时候用线程加载.
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
		private void menuPageStart(){//转入菜单页太突兀，应该由黑转亮，同样的，前面收尾的时候应该转暗
			Timeline timeline = new Timeline();//因为场景是转换不是在这开始的
			timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
					(ActionEvent ae)->{menuPageStartSecondFlyInto();},
					new KeyValue(sceneMenu.fillProperty(), Color.LIGHTGREEN)));
			timeline.play();//后面计算添加menu-item开始位置::7项：：设计个图；
		}
		private void changeSceneToMenuFirst(Stage stage){
			initSceneMenu();
			stage.setScene(sceneMenu);
			menuPageStart();}
		private void initGroupMenuPage(){//添加菜单widgets--
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
		private void welcomePageEnd(){//处理欢迎页收尾工作
//			int step_changeToDark = 50;//大多数情况下不能得到整倍数的分格--本来是想做百叶窗那种
//			int time_changeToDark = (int)(window_size_valid_height/step_changeToDark);
			Timeline timeline= new Timeline();
			timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
					(ActionEvent ae)->{changeSceneToMenuFirst(stage_unique);},
					new KeyValue(sceneWelcome.fillProperty(),
					Color.BLACK)));
			timeline.play();}
		private void welcomePageStart(){//提供欢迎页启动内容--示例：一个小动画
			Timeline timeline = new Timeline();
			Text text[] = new Text[3];
			String str[]={"欢","迎","你"};
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
			new Thread(){//利用开场动画时间加载后续使用的资源
				public void run() {loadResourcesImages();};
			}.start();}
		private void initGroupWelcomePage(){//初始化欢迎页
			group_welcome_page = new Group();
			welcomePageStart();}
		private void initSceneWelcome(){//初始化欢迎场景
			initGroupWelcomePage();
			sceneWelcome = new Scene(group_welcome_page);
			sceneWelcome.setFill(Color.LIGHTGRAY);
			sceneWelcome.setCursor(Cursor.NONE);}
		private void initStage(Stage stage){//初始化舞台内容
			stage_unique = stage;//
			initSceneWelcome();
			stage.setScene(sceneWelcome);
			stage.setWidth(window_size_width);
			stage.setHeight(window_size_height);
			stage.setResizable(false);
			stage.setTitle("拼图-益智游戏");			
			stage.show();}
		@Override
		public void start(Stage stage) throws Exception {//启动舞台-配置
			// TODO Auto-generated method stub
			initStage(stage);}
		public static void main(String[] args) {//由于javafx的程序在main方法里只有一句：
			// TODO Auto-generated method stub//目前的认知是这样的，所有把main方法放在最后
			System.out.println("this is a jigsaw  ");
			launch(args);//但是在其他程序中应该放在属性声明后，方法前，方便顺手
		}
	}
