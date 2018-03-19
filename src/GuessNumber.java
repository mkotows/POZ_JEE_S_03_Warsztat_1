import java.util.Random;
import java.util.Scanner;

public class Zadanie1 {

	public static void main(String[] args) {
		
		Random rand = new Random();
		int guessedNumber = rand.nextInt(100)+1;
		System.out.println("----- " + guessedNumber);
		System.out.println("Zgadnij liczbę: ");
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			while(!scan.hasNextInt()) {
				System.out.println("Podaj liczbę!!");
				scan.next();
			}
			int num = scan.nextInt(); 
			
			if(num==guessedNumber) {
				System.out.println("Zgadłeś");
				break;
			} else if(num>guessedNumber){
				System.out.println("Za dużo!");
			} else {
				System.out.println("Za mało!");
			}
			
		}
		
	}

}
