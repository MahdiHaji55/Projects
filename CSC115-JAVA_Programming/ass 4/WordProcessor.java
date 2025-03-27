import java.util.Scanner;

public class WordProcessor {
	A4Stack<String> undo;
	A4Stack<String> redo;
	
	public WordProcessor() {
		undo = new A4Stack<String>();
		redo = new A4Stack<String>();
	}
	
	/*
	 * Purpose: undo most recent action, but record this
	 *          action in redo just in case the user wants
	 *          to redo it later 
	 * Parameters: None
	 * Returns: boolean - true if undo was successful, false otherwise
	 */
	public boolean undo() {
		boolean result = false;
		if(!undo.isEmpty()) {
			redo.push(undo.pop());
			result=true;
		}
		return result;
	}
	
	/*
	 * Purpose: redo most recent action, but record this
	 *          action in undo just in case the user wants
	 *          to undo it later 
	 * Parameters: None
	 * Returns: boolean - true if redo was successful, false otherwise
	 */
	public boolean redo() {
		boolean result = false;
		if(!redo.isEmpty()) {
			undo.push(redo.pop());
			result=true;
		}
		return result;
	}
	
	public void handleInput(String[] words) {
		redo.popAll();
		for (int i = 0; i < words.length; i++) {
			undo.push(words[i]);
		}
	}
	
	public void displayDocument() {
		System.out.println("Document contents:");
		System.out.println(undo.getWords());
		System.out.println("\n");
	}
	
	public static void simulateWordProcessor() {
		WordProcessor w = new WordProcessor();
		Scanner inputReader = new Scanner(System.in);
		String userInput = "";
		boolean done = false;
		
		while (!done) {
			System.out.println("Please do one of the following, and then hit enter:");
			System.out.println(" - Enter text below");
			System.out.println(" - Enter 1 to undo");
			System.out.println(" - Enter 2 to redo");
			System.out.println(" - Enter quit to quit");
			System.out.print("Enter command: ");
			userInput = inputReader.nextLine();
			System.out.println("\n\n");
			if (userInput.equals("quit")) {
				done = true;
			} else if (userInput.equals("1")) {
				System.out.print("applying undo... ");
				if (w.undo()) {
					System.out.println("undo successful\n");
				} else {
					System.out.println("Nothing to undo\n");
				}
			} else if (userInput.equals("2")) {
				System.out.print("applying redo... ");
				if (w.redo()) {
					System.out.println("redo successful\n");
				} else {
					System.out.println("Nothing to redo\n");
				}
			} else {
				w.handleInput(userInput.split(" "));
			}
			w.displayDocument();
		}
		
		inputReader.close();

	}
	
	public static void main(String[] args) {
		simulateWordProcessor();
	}
}