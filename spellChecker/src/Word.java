import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.PriorityQueue;

public class Word {

	ArrayList<String> all_words = new ArrayList<String>();
	
	public Word() throws FileNotFoundException {
		File word_file = new File("words.txt");
		Scanner sc = new Scanner(word_file);
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			all_words.add(line);
		}
	}
	
	
	public void editDistanceStart(String word) {
		PriorityQueue<Priority> topWords = new PriorityQueue<Priority>();
		if(all_words.contains(word.toLowerCase())) {
			System.out.println("You spelt the word correctly, Good Job!\n");
		}
		else {
			for(int i = 0; i < all_words.size(); i++) {
				//find lengths and create memoization table
				int m = word.length();
				int n = all_words.get(i).length();
				int[][] dp = new int [n+1][m+1];
				
				//fill memoization table with -1
				for(int j = 0; j < n+1; j++) {
					Arrays.fill(dp[j], -1);
				}
				// create a new class that holds the returned length with word and add to PriorityQueue
				int answer = editDistance(word.toLowerCase(), all_words.get(i), m, n, dp);
				Priority pr = new Priority(all_words.get(i), answer);
				topWords.add(pr);
			}
			//poll off top 10 items from PriorityQueue
			for(int i = 0; i < 10; i++) {
				Priority answer = topWords.poll();
				System.out.println("Did you mean "+answer.getWord()+"("+answer.getEdit()+") ?");
			}
			System.out.print("\n");
		}
	}
	
	
	public static int editDistance(String str1, String str2, int m, int n, int[][] dp) {
		
		//base case
		if(m == 0) {
			return n;
		}
		else if(n == 0) {
			return m;
		}
		//Check memoization table if we already have the answer
		if(dp[n][m] != -1) {
			return dp[n][m];
		}
		
		if(str1.charAt(m-1) == str2.charAt(n-1)) {
			int x = editDistance(str1, str2, m-1, n-1, dp);
			dp[n][m] = x;
			return dp[n][m];
		}
		
		//Try our three possible options for a misalignment
		int insert = 1 + editDistance(str1, str2, m, n-1, dp);
		int remove = 1 + editDistance(str1, str2, m-1, n, dp);
		int replace = 1 + editDistance(str1, str2, m-1, n-1, dp);
		
		//Select the minimum branch
		int solution = Math.min(Math.min(insert, remove), replace);
		dp[n][m] = solution;
		return dp[n][m];
		
		}
	
}
