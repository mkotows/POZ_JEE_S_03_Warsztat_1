import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Zadanie5 {

	public static void main(String[] args) {
		
		List<String> spanList = arraysOfAllLines();
		
		List<String> wordList = createAllWordsList(spanList);
		
		//System.out.println(wordList.toString());
		
		Map<String, Integer> popularWords =  new TreeMap<>();
		
		for(String str: wordList) {
			if(popularWords.containsKey(str)) {
				int counter = popularWords.get(str);
				counter++;
				popularWords.put(str, counter);
				
			} else {
				popularWords.put(str, 1);
			}		
		}
		
		//System.out.println(Arrays.toString(popularWords.entrySet().toArray()));
		
		Map<String,Integer> topTen =
			    popularWords.entrySet().stream()
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .limit(10)
			       .collect(Collectors.toMap(
			          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		System.out.println(topTen.toString());
		
		
	}

	private static List<String> createAllWordsList(List<String> spanList) {
		Path path = Paths.get("./src/allSpan.txt");
		try {
			Files.write(path, spanList);
		} catch (IOException e) {
			System.err.println("Błąd przy tworzeniu pliku");
		}
		
		List <String> wordList = new ArrayList<>();
		
		try {
			for(String line: Files.readAllLines(path)) {
				for(String word: line.split("[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]")) {
					if(word.length()<3 || word.equals("się") || word.equals("nie") || word.equals("dla") || word.equals("Jak"))
						continue;
					
					wordList.add(word);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Path path1 = Paths.get("./src/allWords.txt");
		try {
			Files.write(path1, wordList);
		} catch (IOException e) {
			System.err.println("Błąd przy tworzeniu pliku");
		}
		
		return wordList;
	}

	private static List<String> arraysOfAllLines() {
		List <String> wordList = new ArrayList<>();
		
		Connection connect = Jsoup.connect("https://www.onet.pl/");
		try {
		    Document document = connect.get();
		    Elements links = document.select("span.title");
		    for (Element elem : links) {
		    	wordList.add(elem.text());
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return wordList;
	}

}
