package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FFF extends Application {
	
    private Cell[][] c = new Cell[6][7];	//6*7的棋盘
    private char nextDisc = 'R';	
      
    // 重写start方法
    @Override 
    public void start(Stage primaryStage) {	
    	startOver();	    	   
    }
    
    class Cell extends Circle {

	    char token = ' ';		//获取单元格状态
	    int i, j;	
	    
	    //判断是否可以落子的方法
	    private boolean available() {
	        return (token == ' ' && (i == 5 || c[i + 1][j].token != ' '));	//最后一行或者下面有子方可落子
	    }
   
	    public Cell(int i, int j) {
	        super(10, 10, 20);	//圆的属性
	        this.setFill(Color.WHITE);	//填充颜色
	        this.setStroke(Color.BLACK);	//边框颜色
	        this.i = i;
	        this.j = j;
	        
	        this.setOnMousePressed(e -> {		//鼠标单击事件	        		       
	        	
		        if (available()) {
		          token = nextDisc;			//落子
		          
		          if (token == 'Y') {		//黄棋
		            this.setFill(Color.YELLOW);
		          } 
		          else if (token == 'R') {	//红棋
		            this.setFill(Color.RED);
		          }
		          
		          if (nextDisc == 'R') {	//交替落子
		            nextDisc = 'Y';
		          } 
		          else {
		            nextDisc = 'R';
		          }	
		          
		          
		          //平局情况
		          if (isDraw()) {	
		        	Label l0 = new Label("平局");
		        	Button bt = new Button("重新开始");
		        	
		      	    bt.setOnAction(new EventHandler<ActionEvent>(){
						@Override
						public void handle(ActionEvent e) {
							// TODO 自动生成的方法存根
							startOver();
							
						}
		      	    });
		      	    
		  	    	FlowPane p = new FlowPane();
		  	    	p.setAlignment(Pos.CENTER);
		  	    	p.setVgap(20);
		  	    	p.setHgap(20);
		  	    	p.getChildren().add(bt);
		  	    	p.getChildren().add(l0);
		  	    	
		  	    	Stage secondWindow = new Stage();
		  	    	Scene secondScene = new Scene(p , 200, 50);
		  	    	secondWindow.setScene(secondScene); 
		  	    	secondWindow.show(); 
		          }
		          
		          //某方获胜
		          if(isWin(c)){
		        	  
		        	  //黄方获胜
		  	    	if(nextDisc == 'R'){		  	    		
		  	    	    Label l1 = new Label("黄方获胜");
		  	    	    Button bt = new Button("重新开始");
		  	    	    
			      	    bt.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent e) {
								// TODO 自动生成的方法存根
								startOver();
								
							}
			      	    });
			      	    			      	    
			      	    FlowPane p = new FlowPane();
			  	    	p.setAlignment(Pos.CENTER);
			  	    	p.setVgap(20);
			  	    	p.setHgap(20);
		  	    		p.getChildren().add(l1);
		  	    		p.getChildren().add(bt);
		  	    		
		  	    	    Stage secondWindow = new Stage();    
		  	    		Scene secondScene = new Scene(p , 200, 50);
		  	    		secondWindow.setScene(secondScene); 
		  		    	secondWindow.show(); 
		  	    	}
		  	    	
		  	    	//红方获胜
		  	    	else if(nextDisc == 'Y'){
		  	    	    Label l2 = new Label("红方获胜");
		  	    	    Button bt = new Button("重新开始");
		  	    	    
			      	    bt.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent e) {
								// TODO 自动生成的方法存根
								startOver();
								
							}
			      	    });
			      	    
		  	    		FlowPane p = new FlowPane();
			  	    	p.setAlignment(Pos.CENTER);
			  	    	p.setVgap(20);
			  	    	p.setHgap(20);
		  	    		p.getChildren().add(l2);
		  	    		p.getChildren().add(bt);
		  	    		
		  	    	    Stage secondWindow = new Stage();    
		  	    		Scene secondScene = new Scene(p , 200, 50);		  	    		
		  	    		secondWindow.setScene(secondScene); 
		  		    	secondWindow.show(); 
		  	    	}
		  	      }		    
		        }  
	        });
	    }
	}

    //重新开始游戏
    private void startOver() {
    	GridPane gridPane = new GridPane();
	    gridPane.setAlignment(Pos.CENTER);
	    gridPane.setHgap(5);
	    gridPane.setVgap(5);
	    gridPane.setStyle("-fx-background-color: black");
	    	    
	    for (int i = 0; i < 6; i++) {
	        for (int j = 0; j < 7; j++) {
	          gridPane.add(c[i][j] = new Cell(i, j), j, i);
	        }
	    }
	    	   
	    BorderPane pane = new BorderPane();
	    pane.setCenter(gridPane);
	  	
	    Stage primaryStage = new Stage();
	    Scene scene = new Scene(pane, 400, 400);
	    primaryStage.setTitle("四子连棋");
	    primaryStage.setScene(scene); 
	    primaryStage.show(); 
	    
	    nextDisc = 'R';
	    for (int i = 0; i < c.length; i++) {
	        for (int j = 0; j < c[i].length; j++) {
	          c[i][j].token = ' ';	//初始化单元格
	        }
	    }
    }
    
        
     //判断是否获胜
    public static boolean isWin(Cell[][] ch) {
        return isConsecutiveFour1(ch);
      }
    
     //判断平局的方法：子都落满
    private boolean isDraw() {
	    for (int i = 0; i < c.length; i++) {
	      for (int j = 0; j < c[i].length; j++) {
	        if (c[i][j].token == ' ') {
	          return false;
	        }
	      }
	    }
	    return true;
    }
   
    //数组类型转换的方法
    public static boolean isConsecutiveFour1(Cell[][] c) {
	    char[][] values = new char[c.length][c[0].length];
	    for (int i = 0; i < c.length; i++) {
	      for (int j = 0; j < c[i].length; j++) {
	        values[i][j] = c[i][j].token;
	      }
	    }
	    return ConsecutiveFour(values);
    }
      
     //检查是否有四子连（按行 按列  按对角线）     
    public static boolean ConsecutiveFour(char[][] values) {
	    int numberOfRows = values.length;				//行数
	    int numberOfColumns = values[0].length;			//列数	
	    
	    // 检查行
	    for (int i = 0; i < numberOfRows; i++) {
	      if (isConsecutiveFour(values[i])) {    
	        return true;			
	      }
	    }
	
	    // 检查列
	    for (int j = 0; j < numberOfColumns; j++) {
	      char[] column = new char[numberOfRows];	 // 得到一列数组
	     
	      for (int i = 0; i < numberOfRows; i++) {
	        column[i] = values[i][j];
	      }
	
	      if (isConsecutiveFour(column)) {	      	
	        return true;			
	      }
	    }
	
	    // 检查主对角线下部
	    for (int i = 0; i < numberOfRows - 3; i++) {
	      int numberOfElementsInDiagonal = Math.min(numberOfRows - i, numberOfColumns);
	      char[] diagonal = new char[numberOfElementsInDiagonal];
	      
	      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
	        diagonal[k] = values[k + i][k];
	      }	
	      
	      if (isConsecutiveFour(diagonal)) {
	        return true;		
	      }
	    }
	
	    // 检查主对角线上部
	    for (int j = 1; j < numberOfColumns - 3; j++) {
	      int numberOfElementsInDiagonal = Math.min(numberOfColumns - j, numberOfRows);
	      char[] diagonal = new char[numberOfElementsInDiagonal];
	      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
	        diagonal[k] = values[k][k + j];
	      }
	
	      if (isConsecutiveFour(diagonal)) {	       
	        return true;
	      }
	    }
	
	    // 检查副对角线上部
	    for (int j = 3; j < numberOfColumns; j++) {
	      int numberOfElementsInDiagonal = Math.min(j + 1, numberOfRows);
	      char[] diagonal = new char[numberOfElementsInDiagonal];
	
	      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
	        diagonal[k] = values[k][j - k];
	      }
	
	      if (isConsecutiveFour(diagonal)) {
	        return true;
	      }
	    }
	
	    // 检查副对角线下部
	    for (int i = 1; i < numberOfRows - 3; i++) {
	      int numberOfElementsInDiagonal = Math.min(numberOfRows - i, numberOfColumns);
	      char[] diagonal = new char[numberOfElementsInDiagonal];
	
	      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
	        diagonal[k] = values[k + i][numberOfColumns - k - 1];
	      }
	
	      if (isConsecutiveFour(diagonal)) {
	        return true;
	      }
	    }	
	    return false;
    }
    
     //四子连规则     
    public static boolean isConsecutiveFour(char[] values) {
	    for (int i = 0; i < values.length - 3; i++) {
	      boolean isEqual = true;
	      for (int j = i; j < i + 3; j++) {
	        if (values[j] == ' ' || values[j] != values[j + 1]) {
	          isEqual = false;
	          break;
	        }
	      }	
	      if (isEqual) {
	        return true;
	      }
	    }
	
	    return false;
    }
    
    public static void main(String[] args) {
	    launch(args);
	}   
}

