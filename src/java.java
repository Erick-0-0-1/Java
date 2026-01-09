import java.util.Scanner;



public class java{
    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);

//STARTING MY JAVA PROGRAMMING

 /*
         System.out.print("Enter your name: ");
        String name =   scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();


        System.out.print("What is your GPA: ");
        double gpa = scanner.nextDouble();

        System.out.print("Are you a student (true/false): ");
        boolean isStudent = scanner.nextBoolean();

        System.out.println(" ");

        System.out.println("Hello " + name);
        System.out.println("You are " + age + " years old");
        System.out.println("Your GPA is: " + gpa );

        if (isStudent){
            System.out.println("You are enrolled as a student");
        }
        else {
            System.out.println("You are not enrolled as a student");
        }


        double width = 0;
        double height = 0;
        double area = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please insert the width: ");
        width = scanner.nextDouble();

        System.out.print("Please insert the height: ");
        height = scanner.nextDouble();

        area = width * height;
        System.out.print("The area of the Triangle is: " + area + "cm");
 */



        // GAME JAVA

String noun1;
String verb1;
String noun2;
String verb2;
String adjective1;


        System.out.print("Please enter a noun (Animal or Person): ");
        noun1 = scanner.nextLine();
        System.out.print("Please enter a verb (Action): ");
        verb1 = scanner.nextLine();
        System.out.print("Please enter a noun (Animal or Person): ");
        noun2 = scanner.nextLine();
        System.out.print("Please enter a verb (Action): ");
        verb2 = scanner.nextLine();
        System.out.print("Please enter a adjective (Description): ");
        adjective1 = scanner.nextLine();

        System.out.print("I saw " +  noun1 + ".");
        System.out.print();




        scanner.close();
    }
}
