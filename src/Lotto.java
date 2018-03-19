import java.time.chrono.IsoChronology;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import sun.nio.cs.ext.ISCII91;

public class Lotto {

	public static void main(String[] args) {
		
		int [] wybrane = wczytajLiczby();
		System.out.println("Twoje liczby: " + Arrays.toString(wybrane));
		int [] wylosowaneLiczby = LosujLiczby();
		System.out.println("Wylosowane liczby " + Arrays.toString(wylosowaneLiczby));
		
//		int [] wylosowaneLiczby = {1,2,3,4,5,6};
		
		int trafionych = porownajTablice(wybrane, wylosowaneLiczby);
		
		
		int counter=0;
		while(trafionych!=6) {
			wylosowaneLiczby = LosujLiczby();
			trafionych = porownajTablice(wybrane, wylosowaneLiczby);
			if (trafionych==6)
				System.out.println("trafiłeś '6' za próba nr: " + counter);
			if(counter%2000000==0)
				System.out.println("tik tak");
			
			counter++;
		}
		
		
		
		if(trafionych>=3) {
			System.out.println("Trafiłeś: " + trafionych);
		} else {
			System.out.println("Nie trafiłeś nawet \"trójki\" ");
		}

	}

	private static int[] LosujLiczby() {
		int [] wylosowane = new int[6];
		Random rand = new Random();
		
		for (int i=0; i<wylosowane.length; i++) {
			int losNum;
			while(true) {
				losNum=rand.nextInt(49)+1;
				boolean isCorrect=false;
				
				for(int j=0; j<wylosowane.length; j++) {
					if(wylosowane[j]==losNum) {
						//System.out.println("Taka liczba już została wybrana");
						isCorrect=true;
						continue;
					}
				} 
				if(isCorrect) {
					continue;
				} else {
					wylosowane[i]=losNum;
					break;
				}
			}
		}
		Arrays.sort(wylosowane);
		return wylosowane;
	}

	private static int[] wczytajLiczby() {
		Scanner scan = new Scanner(System.in);

		int [] yourArr = new int [6];
		
		for (int i=0; i<yourArr.length; i++) {
			System.out.println("Podaj liczbę z zakresu 1-49");
			boolean isCorrect = true;
			
			while(isCorrect) {
				isCorrect=false;
				
				while(!scan.hasNextInt()) {
					System.out.println("Podaj liczbę z zakresu 1-49");
					scan.next();
				} 
				int wybrana=scan.nextInt();
				
				if(wybrana<1 || wybrana>49) {
					System.out.println("Liczba spoza dozwolonego przedziału!");
					isCorrect=true;
					continue;
				}
				for(int j=0; j<yourArr.length; j++) {
					if(yourArr[j]==wybrana) {
						System.out.println("Taka liczba już została wybrana");
						isCorrect=true;
						continue;
					}
				}
				if(isCorrect) {
					continue;
				} else {
					yourArr[i]=wybrana;
					//System.out.println("Liczba dodana");
					isCorrect=false;
				}	
			}
		}
		Arrays.sort(yourArr);
		return yourArr;
	}


	private static int porownajTablice(int[] wybrane, int[] wylosowaneLiczby) {
		int counter=0;
		
		for(int i=0; i<wybrane.length; i++) {
			for(int j=0; j<wylosowaneLiczby.length; j++) {
				if(wybrane[i]==wylosowaneLiczby[j])
					counter++;
			}
		}	
		return counter;
	}
}
