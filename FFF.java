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
	
    private Cell[][] c = new Cell[6][7];	//6*7������
    private char nextDisc = 'R';	
      
    // ��дstart����
    @Override 
    public void start(Stage primaryStage) {	
    	startOver();	    	   
    }
    
    class Cell extends Circle {

	    char token = ' ';		//��ȡ��Ԫ��״̬
	    int i, j;	
	    
	    //�ж��Ƿ�������ӵķ���
	    private boolean available() {
	        return (token == ' ' && (i == 5 || c[i + 1][j].token != ' '));	//���һ�л����������ӷ�������
	    }
   
	    public Cell(int i, int j) {
	        super(10, 10, 20);	//Բ������
	        this.setFill(Color.WHITE);	//�����ɫ
	        this.setStroke(Color.BLACK);	//�߿���ɫ
	        this.i = i;
	        this.j = j;
	        
	        this.setOnMousePressed(e -> {		//��굥���¼�	        		       
	        	
		        if (available()) {
		          token = nextDisc;			//����
		          
		          if (token == 'Y') {		//����
		            this.setFill(Color.YELLOW);
		          } 
		          else if (token == 'R') {	//����
		            this.setFill(Color.RED);
		          }
		          
		          if (nextDisc == 'R') {	//��������
		            nextDisc = 'Y';
		          } 
		          else {
		            nextDisc = 'R';
		          }	
		          
		          
		          //ƽ�����
		          if (isDraw()) {	
		        	Label l0 = new Label("ƽ��");
		        	Button bt = new Button("���¿�ʼ");
		        	
		      	    bt.setOnAction(new EventHandler<ActionEvent>(){
						@Override
						public void handle(ActionEvent e) {
							// TODO �Զ����ɵķ������
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
		          
		          //ĳ����ʤ
		          if(isWin(c)){
		        	  
		        	  //�Ʒ���ʤ
		  	    	if(nextDisc == 'R'){		  	    		
		  	    	    Label l1 = new Label("�Ʒ���ʤ");
		  	    	    Button bt = new Button("���¿�ʼ");
		  	    	    
			      	    bt.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent e) {
								// TODO �Զ����ɵķ������
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
		  	    	
		  	    	//�췽��ʤ
		  	    	else if(nextDisc == 'Y'){
		  	    	    Label l2 = new Label("�췽��ʤ");
		  	    	    Button bt = new Button("���¿�ʼ");
		  	    	    
			      	    bt.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent e) {
								// TODO �Զ����ɵķ������
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

    //���¿�ʼ��Ϸ
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
	    primaryStage.setTitle("��������");
	    primaryStage.setScene(scene); 
	    primaryStage.show(); 
	    
	    nextDisc = 'R';
	    for (int i = 0; i < c.length; i++) {
	        for (int j = 0; j < c[i].length; j++) {
	          c[i][j].token = ' ';	//��ʼ����Ԫ��
	        }
	    }
    }
    
        
     //�ж��Ƿ��ʤ
    public static boolean isWin(Cell[][] ch) {
        return isConsecutiveFour1(ch);
      }
    
     //�ж�ƽ�ֵķ������Ӷ�����
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
   
    //��������ת���ķ���
    public static boolean isConsecutiveFour1(Cell[][] c) {
	    char[][] values = new char[c.length][c[0].length];
	    for (int i = 0; i < c.length; i++) {
	      for (int j = 0; j < c[i].length; j++) {
	        values[i][j] = c[i][j].token;
	      }
	    }
	    return ConsecutiveFour(values);
    }
      
     //����Ƿ��������������� ����  ���Խ��ߣ�     
    public static boolean ConsecutiveFour(char[][] values) {
	    int numberOfRows = values.length;				//����
	    int numberOfColumns = values[0].length;			//����	
	    
	    // �����
	    for (int i = 0; i < numberOfRows; i++) {
	      if (isConsecutiveFour(values[i])) {    
	        return true;			
	      }
	    }
	
	    // �����
	    for (int j = 0; j < numberOfColumns; j++) {
	      char[] column = new char[numberOfRows];	 // �õ�һ������
	     
	      for (int i = 0; i < numberOfRows; i++) {
	        column[i] = values[i][j];
	      }
	
	      if (isConsecutiveFour(column)) {	      	
	        return true;			
	      }
	    }
	
	    // ������Խ����²�
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
	
	    // ������Խ����ϲ�
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
	
	    // ��鸱�Խ����ϲ�
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
	
	    // ��鸱�Խ����²�
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
    
     //����������     
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

