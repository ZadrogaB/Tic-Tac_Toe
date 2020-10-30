package com.company;
import java.util.Scanner;

class Board{
    private int row;
    private int column;

    public void printBoard(char[][] board){
        int rozmiar = board.length;

        System.out.print("\t");
        for (int i=0; i<rozmiar; i++){ //drukuje nagłówek kolumn
            System.out.print(i+"\t");
        }
        System.out.println();
        for (int row=0; row<rozmiar;row++){
            System.out.print(row+":\t");
            for (int column=0; column<rozmiar; column++){
                System.out.print(board[row][column]+"\t");
            }
            System.out.println();
        }
    }
    public boolean isEmpty (int row, int column, char[][] board){
        if (board[row][column]==0){
            return true;
        }
        else{
            return false;
        }
    }
    public char activePlayerChange (char activePlayer){
        if (activePlayer == 'X') {
            return 'O'; //                              <------zmienić po testach na 'O'
        } else {
            return 'X';
        }
    }
    public boolean isAGoodRowColumn (char[][] board, int playerNumber){
        int rozmiar= board.length-1;
        if (playerNumber>rozmiar || playerNumber<0){
            return false;
        } else {
            return true;
        }
    }

    //sprawdzanie czy ktoś wygrał
    public boolean rowWin(char[][] board, char activePlayer){
        int rozmiar= board.length;
        for (int row=0; row<rozmiar;row++){
            boolean win = true;
            for (int column=0; column<rozmiar; column++) {
                if (board[row][column]!=activePlayer){
                    win=false;
                    break;
                }
            }
            if (win){
                return true;
            }
        }
        return false;
    }
    public boolean columnWin(char[][] board, char activePlayer){
        int rozmiar= board.length;
        for (int column=0; column<rozmiar;column++){
            boolean win = true;
            for (int row=0; row<rozmiar; row++) {
                if (board[row][column]!=activePlayer){
                    win=false;
                    break;
                }
            }
            if (win){
                return true;
            }
        }
        return false;
    }
    public boolean diagonalWinOne (char[][] board, char activePlayer){
        int rozmiar= board.length -1;
        boolean win=true;
        for (int i=0; i<=rozmiar;i++) {
            if (board[i][rozmiar-i] != activePlayer) {
                win = false;
                break;
            }
        }
        if (win){
            return true;
        }
        return false;
    }
    public boolean diagonalWinTwo (char[][] board, char activePlayer){
        int rozmiar= board.length-1;
        boolean win=true;
        for (int i=0; i<=rozmiar;i++) {
            if (board[i][i] != activePlayer) {
                win = false;
                break;
            }
        }
        if (win){
            return true;
        }
        return false;
    }
    public boolean winChecker(char[][] board, char activePlayer) {
        if (this.rowWin(board,activePlayer)==true || this.columnWin(board,activePlayer)||this.diagonalWinOne(board,activePlayer)||this.diagonalWinTwo(board,activePlayer)){
            return true;
        } else {
            return false;
        }
    }

    // gettery
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }

    // konstruktory podzielone na dwa
    public void Row (int Row){
        this.row=Row;
    }
    public void Column (int Column){
        this.column=Column;
    }

}

public class Main {

    public static void main(String[] args) {
        System.out.println("Witaj w grze, podaj rozmiar planszy: "); // do samodzielnego wybrania rozmiaru planszy
        Scanner scan = new Scanner(System.in);
        int rozmiar = scan.nextInt();

        //int rozmiar = 3;
        char [][] board = new char [rozmiar][rozmiar];
        char activePlayer = 'X';
        Board board1=new Board();
        boolean win=false;



        System.out.println("Witaj w grze!");
        board1.printBoard(board);

        for (int numberOfMoves = 0; numberOfMoves<rozmiar*rozmiar;) {
            boolean numberTest = false;
            do{
                System.out.print(activePlayer + " podaj rząd: ");
                int row = scan.nextInt();
                numberTest = board1.isAGoodRowColumn(board, row);
                if (numberTest==false){
                    System.out.println("podano złą wartość");
                }
                board1.Row(row);
            } while (numberTest==false);
            numberTest = false;

            do {
                System.out.print(activePlayer + " podaj kolumnę: ");
                int column = scan.nextInt();
                numberTest = board1.isAGoodRowColumn(board, column);
                if (numberTest==false){
                    System.out.println("podano złą wartość");
                }
                board1.Column(column);
            }while (numberTest==false);
            numberTest = false;
            int row = board1.getRow();
            int column = board1.getColumn();
            if (board1.isEmpty(row, column,board )) {
                board[row][column] = activePlayer;
                numberOfMoves++;
                board1.printBoard(board);
                 win=board1.winChecker(board, activePlayer);
                 if (win){
                     System.out.print(activePlayer+" wygrał!");
                     break;
                 }
            }
            else {
                System.out.println("To miejsce jest zajęte, spróbuj ponownie!");
                activePlayer=board1.activePlayerChange(activePlayer);
            }
            activePlayer=board1.activePlayerChange(activePlayer);

        }
    }
}
