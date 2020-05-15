/*
 * name: raghad zohair - ID: 1705628 - CPCS204 - secion: BBR
 * email: ryahya0010@stu.kau.edu.sa
 * Program 2: KAU Grade Book (Linked Lists) 
 * Due: Thursday, October 6th, 2018 by 11:59 PM
 */
package fcitrecurse_2019_1st;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Raghad
 */
public class FCITrecurse_2019_1st {

    public static void main(String[] args) throws Exception{
        File input = new File("FCITrecurse_2019_1st.in");
        // chek if file input is exists
        if(!input.exists()){
            System.out.println("The file\"FCITrecurse_2019_1st\" not exists!");
            System.exit(0);
        }
        // create file output and create scanner and PrintWriter objects
        File output = new File("FCITrecurse_2019_1st.out");
        Scanner scan = new Scanner(input);
        PrintWriter write = new PrintWriter(output);
        int numOfCommand = scan.nextInt();
        String command;
        while(scan.hasNext()){
            command = scan.next();
            if(command.equalsIgnoreCase("Divide_by_9")){
                String digits = scan.next();
                int count =0;
                int i =Divide_by_9(digits);
                count++;
                while(i%9==0){ 
                    if(i==9){
                        write.println(digits + " is a multiple of "+i+"and has 9-degree " + count + ".");
                        break;
                    }
                    i = Divide_by_9(i + "");
                    count++;
                }
                if(i%9 != 0){
                   write.println(digits+" is not a multiple of 9."); 
                }                    
            }
            
            else if(command.equalsIgnoreCase("Palindromes")){
                String word = scan.next();
                int i =word.length()/2;
                write.printf("The palindromes in \'%s\' are ",word);
                Palindromes(word,i,0,write);
                write.println();     
            }
            else if(command.equalsIgnoreCase("Board_Game")){ 
                String[][] board = {{"S","R","R","R","R","R","R","R","R","R"}
                        ,{"R","R","L","R","R","R","R","R","R","R"}
                        ,{"R","R","R","R","R","R","R","R","R","R"}
                        ,{"R","R","R","R","R","L","R","R","R","R"}
                        ,{"R","R","R","R","R","R","R","R","R","R"}
                        ,{"R","R","R","R","L","R","R","R","R","R"}
                        ,{"R","R","R","R","R","R","R","R","R","R"}
                        ,{"R","R","R","R","R","R","R","R","R","R"}
                        ,{"R","R","R","R","R","R","L","R","R","R"}
                        ,{"R","R","R","R","R","R","R","R","R","E"}};
                write.println("**********My Cardboard Game!**********\r\n\tThe layout of my Cardboard looks like this:");
                for (int i = 0; i < board.length; i++) {                    
                    for (int j = 0; j < board[i].length; j++) {
                        write.print(board[i][j]+"-");                      
                    }
                                       
                    //write.print("\b"); //to clear(-) in end row ***that succeeds just in java screen not in file output
                    write.println();   
                }
                write.println("We begin our game NOW!");
                Board_Game(board, 0,0,0,write);
                write.println("See You Next Time!\r\n**************************************");
            }
        }
        scan.close();
        write.close();        
    } 
    ////////////////////////////////////////////////////////////////////////////
    public static int Divide_by_9(String digits){  
        if(digits.length()==0){           
            return 0;
        }
        // (digits.charAt(0)-'0') to convert  char to int
        return (digits.charAt(0)-'0') +Divide_by_9(digits.substring(1)); 
    }
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public static void Board_Game(String[][] board, int row,int column,int steps,PrintWriter write){
        if(board[row][column].equals("E")){
            write.printf("Finish: (%d,%d)Succesful Game. Number of steps:%d\r\n",row,column,steps);
            return ;
        }
        else if(board[row][column].equals("S")){
            write.printf("Current: (%d,%d)\t\t",row,column);
            column=column+1; steps=steps+1;
            write.printf("Next: (%d,%d)\r\n",row,column);
            Board_Game(board,row,column,steps,write);
        }
        else if(board[row][column].equals("R")){
            write.printf("Current: (%d,%d)\t\t",row,column);
            if(column==board.length-1){
                row=row+1;
                column=-1;               
            }
            column=column+1;
            steps=steps+1;
            write.printf("Next: (%d,%d)\r\n",row,column);
            Board_Game(board,row,column,steps,write);
        }
        else{
            int luck = (int)(Math.random()*2);
            if(luck == 0){
                row=row-1;column=column-2;
                write.printf("Big Tremendous Trap: Go To:   (%d,%d)\r\n",row,column);
            }
            else{
                row=row+1;column=column+3;
                write.printf("Surprise Opportunity: Go To:  (%d,%d)\r\n",row,column);
            }
            Board_Game(board,row,column,steps,write);      
        }   
    }
    ////////////////////////////////////////////////////////////////////////////
    public static String Palindromes(String word,int firstIndex,int lastIndex,PrintWriter write) {   
        if(firstIndex==0){
            for (int k = 0; k < word.length(); k++) {
                write.print(word.charAt(k)+" ");
            }
            return"";
        }
        lastIndex = firstIndex + 1;
        firstIndex--;
        if (word.charAt(firstIndex) == word.charAt(lastIndex)) {
            write.print(word.substring(firstIndex, lastIndex+1) + " ");            
        }
        return Palindromes(word, firstIndex, lastIndex,write);        
    }   
}
