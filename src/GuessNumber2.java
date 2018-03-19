import java.util.Random;
import java.util.Scanner;

public class GuessNumber2 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = takeInt(scan);
		scan.nextLine();
		
		int countGuess = play(num, scan);
		

		scan.close();
	}

	private static int play(int num, Scanner scan) {
		int counter=1;
		Random rand = new Random();
	
		int min=1;
		int max=10;
		int gueesed=0;
		while(true) {
			
			try {
				gueesed=rand.nextInt(max-min-1)+min+1;
			} catch (Exception e) {
				System.err.println("Oszukujesz!! Koniec zabawy!!");
				break;
			}
			System.out.println("próba nr: " + counter);
			System.out.println("Wylosowana liczba to: " + gueesed);
			System.out.println("Wpisz jeśli mniej -> \"m\", jeśli więcej -> \"w\" lub \"trafiona\" ");
			String answer = scan.nextLine();
			boolean isGuessed=false;
			switch (answer) {
				case "trafiona":
					System.out.println("Liczba odgadnięta");
					isGuessed=true;
					break;
				case "m":
					max=gueesed;
					counter++;
					break;
				case "w":
					min=gueesed;
					counter++;
					break;
				default: 
					System.err.println("Błędnie wpisana komenda!!!!!");
			}
//			System.out.println("max = " + max);
//			System.out.println("min = " + min);
			if (counter>10) {
				System.out.println("Liczba nieodgadnięta");
				break;
			}
				
			if(isGuessed)
				break;
		}
		return counter;
	}

	private static int takeInt(Scanner scan) {
		
		System.out.println("Wymyśl liczbę z zakresu 1-1000");
		int wybrana;
		while(true) {
			while(!scan.hasNextInt()) {
				System.out.println("Podaj liczbę prawidłowo");
				scan.next();
			}
			wybrana = scan.nextInt();
			if(wybrana<1 || wybrana>1000) {
				System.out.println("Liczba spoza dozwolonego zakresu 1-1000");
				continue;
			}
			break;
		}
		
		return wybrana;
	}
}
