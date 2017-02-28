
import aihw2.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class homework{
    
    public static int n = 0;
    public static String opponent;
    public static String gyouplay;
    public static String gopponent;
    public static ArrayList<Node> array = new ArrayList<>();
    public static void main(String[] args) {
        String fileName = "C://input.txt";
        String line = null;        
        try{
            String mode = null;
            int depth = 0;
            String[][] cell = null;
            String youplay = null;
            String line1 = null;
            int counter = 0;
            String[][] board = null;
            int counter1 = 0;
            String strArray1[] = null;
            int sumO = 0;
            int sumX = 0;
            int i =0;
            int d =0;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            while((line = br.readLine())!= null){
            if(i == 0){
                n = Integer.parseInt(line);
                cell = new String[n][n];
                board = new String[n][n];
            }
            
            if(i == 1){
                mode = line;
            }
            if(i == 2){
                youplay = line;   
                
            }
            if(i == 3 ){
                depth = Integer.parseInt(line);
            }
            if(i >= 4 && i < (4 + n)){
                 line1 = line;
                 String[] strArray  = line1.split(" ");
                
                 for(int j = 0; j < strArray.length; j++){
                    
                     cell[counter][j] = strArray[j];
                    
                 }
                 counter++;
            }
            if(i >= (4 + n) && i < (4 + (2 * n))){
               strArray1 = line.split("");
               for(int q = 0; q < strArray1.length; q++){
                   board[counter1][q] = strArray1[q]; 
               }
               counter1++;
            }       
            i++;
        }
            gyouplay = youplay;
            
            if(youplay.equals("X")){
                opponent = "O";
                gopponent = "O";
            }
            else if(youplay.equals("O")){
                opponent = "X";
                gopponent = "X";
            }
     
      
        if(mode.equals("MINIMAX")){
            
            //System.out.println("Hi");

            Node start = new Node(board,d,0,0,0,"none");
            Node g = new Node();
            Node v = new Node();
            g = minimax(start,n,depth,youplay,cell,d) ;
            //System.out.println("Final value");
        
            
            //System.out.println("Final score:");
          
            //System.out.println(g.score);
            //System.out.println("Final type is:");
            System.out.println(g.type);
           // System.out.println("Final answer is:");
            int l = (g.x+1);
            int m = (g.y);
            char ch1 = (char)(m+65);
            System.out.print(ch1+""+l);
            FileWriter filewrite;
            try {
            filewrite = new FileWriter("D://output.txt");
            filewrite.write(ch1+""+l+" "+g.type+"\n");
            for(int e =0; e < n;e++){
                for(int f = 0; f < n; f++){
                    filewrite.write(g.state[e][f]);
                }
                filewrite.write("\n");
            }
            filewrite.close();
             }
             catch(IOException ex){     
             }
           
            
        }
        else if(mode.equals("ALPHABETA")){
            Node start = new Node(board,d,0,0,0,"none");
            Node g = new Node();
            g = alphabeta(start,n,depth,youplay,cell,d);
           // System.out.println("Final value in alpha-beta");
            
            //System.out.println("Final score in alpha-beta");
           
            //System.out.println("Final ty System.out.println(g.type);
            //System.out.println("Final answer is:");
            int l = (g.x+1);
            int m = (g.y);
            char ch1 = (char)(m+65);
            System.out.println(g.type);
            System.out.print(ch1+""+l);
             FileWriter filewrite;
            try {
            filewrite = new FileWriter("D://output.txt");
            filewrite.write(ch1+""+l+" "+g.type+"\n");
            for(int e =0; e < n;e++){
                for(int f = 0; f < n; f++){
                    filewrite.write(g.state[e][f]);
                }
                filewrite.write("\n");
            }
            filewrite.close();
             }
             catch(IOException ex){     
             }
        }
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
       
    }
    public static Node minimax(Node start,int n,int depth,String youplay,String[][] cell, int d){
        int j =0; 
        int temp =0;
        int position =0;

        Node f = max(start,n,depth,youplay,cell,d,j);
        return f;
    }
  
 
    public static Node max(Node start, int n, int depth, String youplay, String[][] cell, int d, int j){
        
        
               int c = 1;
        if(start.depth == depth || !boardFull(start.state,n)){
          
           return start;  
           
        }
        Node v =new Node();
        v.score = Integer.MIN_VALUE;
        //System.out.println("Score is "+ v.score);
        ArrayList<Node> children1 = new ArrayList<>();
        children1 = action(start,n,depth,youplay,cell,d);
        //System.out.println("In max");
        
        for(int i = 0; i < children1.size(); i++){
          
            int currScore = min(children1.get(i),n,depth,youplay,cell,d+1,i).score;
            
            if(currScore > v.score) {
                v.score = currScore;
                v.state = children1.get(i).state;
                v.type = children1.get(i).type;
                v.x = children1.get(i).x;
                v.y = children1.get(i).y;
            }
            
      
           
          
        }
        return v;
          
    }
    public static Node min(Node start, int n, int depth, String youplay, String[][] cell, int d, int j){
       
            if(start.depth == depth || !boardFull(start.state,n)){
            return start;
            } 
            
        

        Node v = new Node();
        v.score = Integer.MAX_VALUE;
       // System.out.println("Score is "+ v.score);
        ArrayList<Node> children1 = new ArrayList<>();
        youplay = opponent;
        children1 = action(start,n,depth,youplay,cell,d);
        //System.out.println("In min");
       /// printmatrix(start.state);
        for(int i = 0; i < children1.size(); i++){
        youplay = gyouplay;
        
        int currScore = max(children1.get(i),n,depth,youplay,cell,d+1,i).score;
            
        if(currScore < v.score) {
            v.score = currScore;
            v.state = children1.get(i).state;
            v.type = children1.get(i).type;
            v.x = children1.get(i).x;
            v.y = children1.get(i).y;
        }
       // System.out.println("Score in min is:"+v.score);
        
        }
        
        return v;
    }
     public static Node alphabeta(Node start,int n,int depth,String youplay,String[][] cell, int d){
        int j =0; 
        int temp =0;
        int position =0;
        
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        Node f = max1(start,n,depth,youplay,cell,d,j, alpha, beta);
        return f;
    }
    
    public static Node max1(Node start, int n, int depth, String youplay, String[][] cell, int d, int j, int alpha, int beta){
        
        
        int c = 1;
        if(start.depth == depth || !boardFull(start.state,n)){
          
           return start;  
        }
        Node v =new Node();
        v.score = Integer.MIN_VALUE;
       // System.out.println("Score is "+ v.score);
        ArrayList<Node> children1 = new ArrayList<>();
        children1 = action(start,n,depth,youplay,cell,d);
       // System.out.println("In max");
        
        for(int i = 0; i < children1.size(); i++){
            
            int currScore = min1(children1.get(i),n,depth,youplay,cell,d+1,i,alpha,beta).score;
            
            if(currScore > v.score) {
                v.score = currScore;
                v.state = children1.get(i).state;
                v.type = children1.get(i).type;
                v.x = children1.get(i).x;
                v.y = children1.get(i).y;
            }
            if(v.score >= beta)
                return v;
            alpha = Math.max(alpha,v.score);
           // System.out.println("v.score is " +v.score);
            
        }
        return v;
          
    } 
    public static Node min1(Node start, int n, int depth, String youplay, String[][] cell, int d, int j, int alpha, int beta){
       
        if(start.depth == depth || !boardFull(start.state,n)){
            
            return start;
        }
        Node v = new Node();
        v.score = Integer.MAX_VALUE;
        //System.out.println("Score is "+ v.score);
        ArrayList<Node> children1 = new ArrayList<>();
        youplay = opponent;
        children1 = action(start,n,depth,youplay,cell,d);
        //System.out.println("In min");
       /// printmatrix(start.state);
        for(int i = 0; i < children1.size(); i++){
        youplay = gyouplay;
        
        int currScore = max1(children1.get(i),n,depth,youplay,cell,d+1,i, alpha,beta).score;
            
        if(currScore < v.score) {
            v.score = currScore;
            v.state = children1.get(i).state;
            v.type = children1.get(i).type;
            v.x = children1.get(i).x;
            v.y = children1.get(i).y;
        }
        if(v.score <= alpha){
            return v;
        }
        beta = Math.min(beta,v.score);
       
        
        }
        
        return v;
    }
    
    public static ArrayList<Node> action(Node start, int n, int depth, String youplay, String[][] cell,int d){
        ArrayList<Node> children = new ArrayList<Node>();
        children.addAll(action1(start,n,depth,youplay,cell,d));
        children.addAll(action2(start,n,depth,youplay,cell,d));
        return children;
    }
     
    
    public static ArrayList<Node> action1(Node start, int n, int depth, String youplay, String[][] cell,int d){
 
        ArrayList<Node> stake = new ArrayList<Node>();
        
        int stateScore = 0;
        String[][] new_board  = new String[n][n];
        String type;
       // System.out.println(youplay);
        for(int x = 0; x < n; x++){
           for(int y = 0; y < n; y++){
               if(start.state[x][y].equals(".")){
                   new_board = new String[start.state.length][start.state.length];
                   for(int h=0; h < n; h++){
                       for(int m =0 ; m < n; m++){
                           new_board[h][m] = start.state[h][m];
                       }
                   }
                 
                   if(youplay.equals("X")){
                    
                       type ="Stake";
                       new_board[x][y] = "X";
                       stateScore = score(new_board,x,y,n,cell,youplay);
                       stake.add(new Node(new_board, start.depth + 1,stateScore,x,y,type)); 
                  
             
                       }
                   
                   else if(youplay.equals("O")){
                    
                       type = "Stake";    
                       new_board[x][y] = "O";
                       stateScore = score(new_board,x,y,n,cell,youplay);
                       stake.add(new Node(new_board, start.depth + 1,stateScore,x,y,type));
                    
    
                   
                       }
                       
                       
                   }
                   }
               }
          
return stake;
    }
    public static ArrayList<Node> action2(Node start, int n, int depth, String youplay, String[][] cell,int d){
        ArrayList<Node> raid = new ArrayList<Node>();
         int stateScore = 0;
        String[][] new_board  = new String[n][n];
        String type;
       // System.out.println(youplay);
        for(int x = 0; x < n; x++){
           for(int y = 0; y < n; y++){
               if(start.state[x][y].equals(".")){
                   new_board = new String[start.state.length][start.state.length];
                   for(int h=0; h < n; h++){
                       for(int m =0 ; m < n; m++){
                           new_board[h][m] = start.state[h][m];
                       }
                   }
                   
                   if(youplay.equals("X")){
                                 if((new_board[Math.max(0,x-1)][y]).equals("X")||(start.state[x][Math.max(0,y-1)]).equals("X")||(start.state[Math.min(n-1,x+1)][y]).equals("X")||(start.state[x][Math.min(n-1,y+1)]).equals("X")){
                       new_board[x][y] = "X";
                       if(new_board[Math.max(0,x-1)][y].equals("O")){
                           new_board[x-1][y] = "X";
                       }
                       if(new_board[x][Math.max(0, y-1)].equals("O")){
                           new_board[x][y-1] = "X";
                       }
                       if(new_board[Math.min(n-1,x+1)][y].equals("O")){
                           new_board[x+1][y] = "X"; 
                       }
                       if(new_board[x][Math.min(n-1,y+1)].equals("O")){
                           new_board[x][y+1] = "X";
                       }
                       type = "Raid";
                       stateScore = score(new_board,x,y,n,cell,youplay);
                       
                        //printmatrix(new_board); 
                        raid.add(new Node(new_board, start.depth + 1,stateScore,x,y,type));
                   }
                   }
                   else if(youplay.equals("O")){
                           if((new_board[Math.max(0,x-1)][y]).equals("O")||(new_board[x][Math.max(0,y-1)]).equals("O")||(new_board[Math.min(n-1,x+1)][y]).equals("O")||(new_board[x][Math.min(n-1,y+1)]).equals("O")){             
                       new_board[x][y] = "O";
                       if(new_board[Math.max(0,x-1)][y].equals("X")){
                           new_board[x-1][y] = "O";
                       }
                       if(new_board[x][Math.max(0, y-1)].equals("X")){
                           new_board[x][y-1] = "O";
                       }
                       if(new_board[Math.min(n-1,x+1)][y].equals("X")){
                           new_board[x+1][y] = "O";
                       }
                       if(new_board[x][Math.min(n-1,y+1)].equals("X")){
                           new_board[x][y+1] = "O";
                       }
                       type = "Raid";
                 
                       stateScore = score(new_board,x,y,n,cell,youplay);
                       raid.add(new Node(new_board, start.depth + 1,stateScore,x,y,type));
                   }
                   }
                   
                  
    }
           }
        }
           return raid;
        }
           
                                                    
    
    public static int score(String[][] new_board, int i, int j, int n, String[][] cell,String youplay){
    int result = 0;
    for(int k1 = 0; k1 < n; k1++){
        for(int k2 = 0; k2 < n ; k2++){
            if(new_board[k1][k2].equals(gyouplay)){
              result+=Integer.parseInt(cell[k1][k2]);
            }
              else if(new_board[k1][k2].equals(gopponent)){  
            result-=Integer.parseInt(cell[k1][k2]);}             
        }
        }
    
    return result;
    }
    public static boolean boardFull(String[][] start,int n){
        for(int y = 0; y < n ; y++)
            for(int z = 0; z < n; z++)
               if(start[y][z].equals(".")){
                   return true;
               }  
        
        return false;
    
}
}
class Node {
int score;
String[][] state = new String[2][2];
int depth;
int x;
int y;
String type;
public Node(){
    
}
public Node(String[][] state, int depth, int score,int x, int y, String type){
    this.depth = depth;
    this.state = state;
    this.score = score;
    this.x = x;
    this.y = y;
    this.type = type;
}
}