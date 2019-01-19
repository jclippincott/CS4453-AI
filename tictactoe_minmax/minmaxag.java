import java.util.*;

public class minmaxag {
	     
    public minmaxag(){}
     
	private int getMove(String S, String succ){
		for(int i = 0; i < 9; i++){
			if(!(S.substring(i,i+1).equals(succ.substring(i,i+1))))
				return i;
		}
		return 0;
	}
		
	private int winTest (String S){
		//Test win conditions for X
		if((S.substring(0,1)).equals("X")){
			if(((S.substring(1,2)).equals("X")) && ((S.substring(2,3)).equals("X")))
				return -10;
			else if(((S.substring(3,4)).equals("X")) && ((S.substring(6,7)).equals("X")))
				return -10;
			else if(((S.substring(4,5)).equals("X")) && ((S.substring(8,9)).equals("X")))
				return -10;
		}
		else if((S.substring(4,5)).equals("X")){
			if(((S.substring(3,4)).equals("X")) && ((S.substring(5,6)).equals("X")))
				return -10;
			else if(((S.substring(1,2)).equals("X")) && ((S.substring(7,8)).equals("X")))
				return -10;
			else if(((S.substring(2,3)).equals("X")) && ((S.substring(6,7)).equals("X")))
				return -10;
		}
		else if((S.substring(8)).equals("X")){
			if(((S.substring(6,7)).equals("X")) && ((S.substring(7,8)).equals("X")))
				return -10;
			else if(((S.substring(2,3)).equals("X")) && ((S.substring(5,6)).equals("X")))
				return -10;
		}
		
		//Test win conditions for O
		if((S.substring(0,1)).equals("O")){
			if(((S.substring(1,2)).equals("O")) && ((S.substring(2,3)).equals("O")))
				return 10;
			else if(((S.substring(3,4)).equals("O")) && ((S.substring(6,7)).equals("O")))
				return 10;
			else if(((S.substring(4,5)).equals("O")) && ((S.substring(8,9)).equals("O")))
				return 10;
		}
		else if((S.substring(4,5)).equals("O")){
			if(((S.substring(3,4)).equals("O")) && ((S.substring(5,6)).equals("O")))
				return 10;
			else if(((S.substring(1,2)).equals("O")) && ((S.substring(7,8)).equals("O")))
				return 10;
			else if(((S.substring(2,3)).equals("O")) && ((S.substring(6,7)).equals("O")))
				return 10;
		}
		else if((S.substring(8)).equals("O")){
			if(((S.substring(6,7)).equals("O")) && ((S.substring(7,8)).equals("O")))
				return 10;
			else if(((S.substring(2,3)).equals("O")) && ((S.substring(5,6)).equals("O")))
				return 10;
		}
		
		//If none of these trigger, that means we're not in a terminal state or we're in a tie and in a terminal state
		return 0;
	}
	 
	private ArrayList<String> genSuccessors(String S,String play){
		ArrayList<String> successors = new ArrayList<String>();
		for(int i = 0; i < 9; i++){
			if((S.substring(i,i+1)).equals("_"))
				successors.add(S.substring(0,i)+play+S.substring(i+1));
		}
		return successors; 
	}
	
    public int move(String S){
        int max = -100000;
		ArrayList<String> successors = genSuccessors(S,"O");
		int move = 0;
		for(int i = 0; i < successors.size(); i++){
			if((minVal(successors.get(i),0)) > max){
				max = minVal(successors.get(i),0);
				move = getMove(S,successors.get(i));			
			}
		}			
		return move;
    }
	 
	private int maxVal(String S, int depth){
		ArrayList<String> successors = genSuccessors(S,"O");
		if((successors.size() == 0) || (winTest(S) != 0)){
			return winTest(S)+depth;
		}
		int v = -1000000000;
		for (int i = 0; i < successors.size(); i++){
			v = Math.max(v,minVal(successors.get(i),depth+1));
		}
		return v;
	}
	 
	private int minVal(String S, int depth){
		ArrayList<String> successors = genSuccessors(S,"X");
		if((successors.size() == 0) || (winTest(S) != 0))
			return winTest(S)-depth;
		int v = 1000000000;
		for (int i = 0; i < successors.size(); i++){
			v = Math.min(v,maxVal(successors.get(i),depth+1));
		}
		return v;
	}
}