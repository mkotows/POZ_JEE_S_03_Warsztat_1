import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Zadanie4 {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		System.out.println("Wpisz komendę: ");
		String komenda = scan.nextLine();
		
		int wylosowana = losuj(komenda);
		

	}

	private static int losuj(String komenda) {
		char[] arr = komenda.toCharArray();
		int wylosowana=0;
		int ileScian=0;
		int ileKosci=1;
		int modyfikator=0;
		int indeksD=0;
		int indeksModyfikatora;
		
		for(int i=0;i<arr.length;i++) {
			
			if(arr[0]!='D') {
				ileKosci = Integer.parseInt(String.valueOf(arr[0]));
			}
			if(arr[i]=='D') {
				indeksD=i;
			}
			
			
			if(arr[i]=='+' || arr[i]=='-') {
				if(arr[i]=='+') {
					String [] podzielony = komenda.split("\\" + String.valueOf(arr[i]));
					modyfikator = Integer.parseInt(podzielony[1]);
				}
				if(arr[i]=='-') {
					String [] podzielony = komenda.split(String.valueOf(arr[i]));
					modyfikator = -Integer.parseInt(podzielony[1]);
				}
				
				indeksModyfikatora=i;
				char [] arr1 = Arrays.copyOfRange(arr, indeksD+1, indeksModyfikatora);
				ileScian = Integer.parseInt(String.valueOf(arr1));
				return losowanie(ileScian, ileKosci, modyfikator);
				
			} else if(i==arr.length-1) {
				char [] arr1 = Arrays.copyOfRange(arr, indeksD+1, arr.length);
				ileScian = Integer.parseInt(String.valueOf(arr1));
				return losowanie(ileScian, ileKosci, modyfikator);
			}
			
							
			
	
		}
//		System.out.println(ileKosci);
//		System.out.println(modyfikator);
		
		
		return wylosowana;
	}

	private static int losowanie(int ileScian, int ileKosci, int modyfikator) {
		int counter=1;
		int sum =0;
		Random rand = new Random();
		while(counter<=ileKosci) {
			int wylosowana = rand.nextInt(ileScian)+1;
			System.out.println(counter +": Wylosowana kostka: " + (wylosowana+modyfikator));	
			sum+=wylosowana;
			counter++;
		}
		return sum;
	}

}
