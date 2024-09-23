
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgramDemo {

	
	public static void main(String[] args) throws FileNotFoundException {
		//Create new Word class. Auto loads in file.
		Word check = new Word();
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter a word:");
			String input = sc.nextLine();
			check.editDistanceStart(input);
		}
	}

}
