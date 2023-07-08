package com.company;


import java.util.HashSet;
import java.util.Scanner;

class Player{
    String pname;
    char assgn;
    int result;
    Player(String pname,char assgn){
        this.pname=pname;
        this.assgn=assgn;
        this.result=0;
    }
      char show(){
        return assgn;
     }

}

class Board {
    static char board[][] = new char[3][3];
    static int c = 0;
    private Player p1, p2;

    Board(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    static void boardInitialize() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    void boardDisplay() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
//method for checking whether the blocks are empty and can be occupied
    boolean checkStatus(int x, int y) {
        if (board[x][y] == '-')
            return true;

        return false;
    }

    void setBoard(int x, int y, Player p) {
        board[x][y] = p.show();
    }

//method for checking the winner
    int decide(Player p) {
        //single row
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == p.show())
                    count++;
            }
            if (count == 3) {
                return 1;
            }
            count = 0;

        }
        count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == p.show())
                    count++;
            }
            if (count == 3)
                return 1;
            count = 0;

        }
        count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == p.show() && i == j)
                    count++;
            }

        }
        if (count == 3)
            return 1;
        count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == p.show() && i + j == 2)
                    count++;
            }

        }
        if (count == 3)
            return 1;

        if (c == 8)
            return 2;
        return 3;

    }
}



public class Main {
    //server class
    static HashSet<String> hs = new HashSet<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("To Play Locally Press + or with server press -");
        hs.add("anonymous");
        char option = sc.next().charAt(0);
        if (option == '+') {
            System.out.println("Enter the your name:");
            String playerName = sc.next();
            boolean status = checkPlayer(playerName);
            if (!status) {
                addPlayer(playerName);
            }
            playGame(playerName);
        }

    }

    static boolean checkPlayer(String name) {
        if (hs.contains(name))
            return true;
        return false;
    }

    static void addPlayer(String playerName) {
        hs.add(playerName);
    }

    static void playGame(String playerName) {
        System.out.println("Welcome to the TIC-TAC-TOE");
        System.out.println("Enter the player name to play with...");
        String oppoPlayer = sc.next();
        while (!checkPlayer(oppoPlayer)) {
            oppoPlayer = sc.next();
            System.out.println("Sorry ,type in correct name..");
        }
        System.out.println("Please wait for the board to load......");
        Player player2 = new Player(oppoPlayer, '0');
        Player player1 = new Player(playerName, 'x');
        Board board = new Board(player1, player2);
        Board.boardInitialize();
        System.out.println("Player 1 chooses X and player 2 0");
        System.out.println("Start Game  ");
        int row, col,res;
        while (true) {
            board.boardDisplay();
            System.out.println("Move " + Board.c);
            System.out.println("Enter the coordinate you want to enter");
            row = sc.nextInt();
            col = sc.nextInt();
            while(row<0 || row>=3  || col<0 || col>=3){
                System.out.println("Invalid Move");
                row = sc.nextInt();
                col = sc.nextInt();

            }
            while (board.checkStatus(row, col) == false) {
                System.out.println("Enter proper blocks");
                row = sc.nextInt();
                col = sc.nextInt();
            }
                if (Board.c % 2 == 0) {
                    System.out.println("Player1");
                    board.setBoard(row, col, player1);
                    res = board.decide(player1);
                    if (res == 1) {
                        System.out.println(player1.pname+ " wins");
                        break;
                    } else if (res == 2) {
                        System.out.println("no more moves...Match tied");
                        break;
                    }

                } else {

                    System.out.println("Player2");
                    board.setBoard(row, col, player2);
                    res = board.decide(player2);
                    if (res == 1) {
                        System.out.println(player2.pname+ " wins");
                        break;
                    } else if (res == 2) {
                        System.out.println("no more moves...Match tied");
                        break;
                    }
                }


            Board.c++;

            }
        board.boardDisplay();

        }


    }


