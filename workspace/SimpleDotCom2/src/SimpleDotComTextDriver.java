import java.util.ArrayList;


public class SimpleDotComTextDriver {
	public static void main(String[] args){
		int numOfGuesses = 0;
		GameHelper helper = new GameHelper();
		
		SimpleDotCom theDotCom = new SimpleDotCom();
		
		int randomNum = (int)(Math.random()*5);
		ArrayList<String> locations = {randomNum , randomNum+1 , randomNum+2 };
		
		System.out.println("The locations are "+randomNum+"¡¢"+(randomNum+1)
				           +" and "+(randomNum+2));
		theDotCom.setLocationCells(locations);
		boolean isAlive = true;
		
		while(isAlive == true){
			String guess = helper.getUserInput("enter a number");
			String result = theDotCom.checkYourself(guess);
			numOfGuesses++;
			if(result.equals("kill")){
				isAlive = false;
				System.out.println("you took "+numOfGuesses+" guesses");
			}
		}
	}
}













