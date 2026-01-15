import java.util.Scanner;


public class IF_Statement {
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);


        int age = 26;


        System.out.print("please enter your age: ");
        age = scanner.nextInt();

        if (age >= 18){
            System.out.println("You are adult");
        }
        else {
            System.out.println("you are minor");
        }


scanner.close();

    }
}
