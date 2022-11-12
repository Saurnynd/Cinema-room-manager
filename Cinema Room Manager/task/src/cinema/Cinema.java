package cinema;
import java.util.Scanner;
public class Cinema {

    public static void printSeats(char[][] cinema){
        int rows = cinema.length;
        int colums = cinema[0].length;
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= colums;i++){
            System.out.print(i + " ");
        }
        for(int i = 0; i < rows; i++){
            System.out.println();
            System.out.print(i + 1);
            for(int j = 0; j < colums; j++){
                System.out.print(" " + cinema[i][j]);
            }
        }
        System.out.println();
    }

    public static void buyTicket(char[][] cinema){
        int rows = cinema.length;
        int colums = cinema[0].length;
        Scanner scanner = new Scanner(System.in);
        boolean isNotChoosen = true;
        do {
            System.out.println("Enter a row number:");
            int ticketRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int ticketColum = scanner.nextInt();
            if (ticketColum <=0 || ticketColum > colums || ticketRow <= 0 || ticketRow > rows){
                System.out.println("Wrong input!");
            } else if (cinema[ticketRow - 1][ticketColum - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                int ticketPrice = 0;
                isNotChoosen = false;
                if (rows * colums <= 60) {
                    ticketPrice = 10;
                } else if (rows / 2 >= ticketRow) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }

                System.out.println("Ticket price: $" + ticketPrice);

                cinema[ticketRow - 1][ticketColum - 1] = 'B';
            }
        }while (isNotChoosen);
    }

    public static  void printStatistics(char[][] cinema){
        int rows = cinema.length;
        int colums = cinema[0].length;
        int counter = 0;
        int currentIncome = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < colums; j++){
               if(cinema[i][j] == 'B'){
                   counter++;
                   currentIncome += rows * colums <= 60? 10: rows / 2 >= i + 1? 10: 8;
               }
            }
        }
        int totalIncome;
        if (rows * colums <= 60) {
            totalIncome = rows * colums * 10;
        } else {
            totalIncome = rows / 2 * colums * 10 + (rows - rows / 2) * colums * 8;
        }
        float percentage = ((float) counter / (rows * colums)) * 100;
        System.out.println("Number of purchased tickets: " + counter);
        System.out.format("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
    public static void startMenu(char[][] cinema){
        boolean isContinue = true;
        Scanner scanner = new Scanner(System.in);
        while(isContinue){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    printSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    printStatistics(cinema);
                    break;
                case 0:
                    isContinue = false;
                    break;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }

    public static char[][] createCinema(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int colums = scanner.nextInt();

        char[][] cinema = new char[rows][colums];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < colums; j++){
                cinema[i][j] = 'S';
            }
        }
        return cinema;
    }

    public static void main(String[] args) {

        char[][] cinema = createCinema();

        startMenu(cinema);
    }
}