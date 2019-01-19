import java.util.*;

public class minmaxag {
      
        
    public minmaxag(){}
	
	private static int findlevel(int b)
    {   
        int level = 0;
        for(int i = 6; i < 42; i+=7)
            if(b > i)
                level += 1;
        return level;           
    }
	
	private ArrayList<String> genSuccessors(String S, String play){
		ArrayList<String> successors = new ArrayList<String>();
		for(int i = 35; i < 42; i++){
			int index = i;
			while((index > 0) && !((S.substring(index,index+1).equals("_"))))
				index-=7;
			if(index > 0)
				successors.add(S.substring(0,index)+play+S.substring(index+1));
		}
		return successors;
	}
	
	public static int straightup(String S, String check, int pos){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        int val = pos;
        int count = 0;
        for(int i = 0; i< 4; i++){
            if (val  >= 0){
                positions.add(val);
                val -= 7;
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
   
	public static int straightdown(String S, String check, int pos){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        int val = pos;
        int count = 0;
        for(int i = 0; i< 4; i++){
            if (val  < 42){
                positions.add(val);
                val += 7;
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
	
	public static int left(String S, String check, int pos){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        int val = pos;
        int count = 0;
		int level = findlevel(pos);
        for(int i = 0; i< 4; i++){
            if ((findlevel(val) == level) && (val >= 0)){
                positions.add(val);
                val ++;
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
	
	public static int right(String S, String check, int pos){
		ArrayList<Integer> positions = new ArrayList<Integer>();
        int val = pos;
        int count = 0;
		int level = findlevel(pos);
        for(int i = 0; i< 4; i++){
            if ((findlevel(val) == level) && (val < 42)){
                positions.add(val);
                val ++;
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
    
   	public static int diagleftup(String S, String check, int pos){
		ArrayList<Integer> positions = new ArrayList<Integer>();
        int levela = 0;
		int levelb = 1;
		int val = pos;
        int count = 0;
        for(int i = 0; i< 4; i++){
            if ((val >= 0) && (Math.abs(levela - levelb) == 1)){
                levela = findlevel(val);
				positions.add(val);
                val -= 8;
				levelb = findlevel(val);
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
    
	public static int diagrightdown(String S, String check, int pos){
		ArrayList<Integer> positions = new ArrayList<Integer>();
        int levela = 0;
		int levelb = 1;
		int val = pos;
        int count = 0;
        for(int i = 0; i< 4; i++){
            if ((val < 42) && (Math.abs(levela - levelb) == 1)){
                levela = findlevel(val);
				positions.add(val);
                val += 8;
				levelb = findlevel(val);
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
	
	public static int diagleftdown(String S, String check, int pos){
		ArrayList<Integer> positions = new ArrayList<Integer>();
        int levela = 0;
		int levelb = 1;
		int val = pos;
        int count = 0;
        for(int i = 0; i< 4; i++){
            if ((val < 42) && (Math.abs(levela - levelb) == 1)){
                levela = findlevel(val);
				positions.add(val);
                val += 6;
				levelb = findlevel(val);
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
	
    public static int diagrightup(String S, String check, int pos){
		ArrayList<Integer> positions = new ArrayList<Integer>();
        int levela = 0;
		int levelb = 1;
		int val = pos;
        int count = 0;
        for(int i = 0; i< 4; i++){
            if ((val >= 0) && (Math.abs(levela - levelb) == 1)){
                levela = findlevel(val);
				positions.add(val);
                val -= 6;
				levelb = findlevel(val);
            }
        }
		for(int i = 0; i < positions.size(); i++){
			if((S.substring(positions.get(i),positions.get(i)+1)).equals(check))
				count++;
		}

		return count;
	}
	
	private int count3InRow(String S, String check){
		int count = 0;
		for(int i = 0; i < 42; i++){
			int up = straightup(S,check,i);
			int down = straightdown(S,check,i);
			int l = left(S,check,i);
			int r = right(S,check,i);
			int downleft = diagleftdown(S,check,i);
			int rightup = diagrightup(S,check,i);
			int upleft = diagleftup(S,check,i);
			int downright = diagrightdown(S,check,i);
			
			if((up == 3) || (up+down >= 3) || (down == 3)
				|| (l == 3) || (r == 3) || (l + r >= 3)
				|| (downleft == 3) || (rightup == 3) || (downleft+rightup >= 3)
				|| (downright == 3) || (upleft == 3) || (downright+upleft >= 3))
				count++;
		}

		return count;
	}
	
	private int heuristicVal(String S){
		int X = count3InRow(S,"X");
		int O = count3InRow(S,"O");
		
		return O-X;
	}
	
	private boolean win	(String S, String check){
		for(int i = 0; i < 42; i++){
			if((straightup(S,check,i) == 4) || (straightdown(S,check,i) == 4)
				|| (left(S,check,i) == 4) || (right(S,check,i) == 4) 
				|| (diagleftdown(S,check,i) == 4) || (diagleftup(S,check,i) == 4)
				|| (diagrightdown(S,check,i) == 4) || (diagrightup(S,check,i) == 4))
				return true;
		}		
		return false;
	}
	
	private int getMove(String S, String succ){
		for(int i = 0; i < 42; i++){
			if(!(S.substring(i,i+1).equals(succ.substring(i,i+1))))
				return i;
		}
		return 0;
	}

    public int move(String S){
      int max = -100000;
		ArrayList<String> successors = genSuccessors(S,"O");
		int move = 0;
		for(int i = 0; i < successors.size(); i++){
			if(max < minVal(successors.get(i),-100000,100000,6))
				move = getMove(S,successors.get(i));
			max = Math.max(max,minVal(successors.get(i),-100000,1000000,6));
		}			
		return move;
    }
	 
	private int maxVal(String S, int alpha, int beta, int depth){
		ArrayList<String> successors = genSuccessors(S,"O");
		if(win(S,"X"))
			return -100;
		else if (win(S,"O"))
			return 100;
		else if (depth == 0)
			return heuristicVal(S);
		
		int v = -1000000000;
		for (int i = 0; i < successors.size(); i++){
			v = Math.max(v,minVal(successors.get(i),alpha,beta,depth-1));
			alpha = Math.max(v,alpha);
			if(beta <= alpha)
				return v;
		}
		return v;
	}
	 
	private int minVal(String S, int alpha, int beta, int depth){
		ArrayList<String> successors = genSuccessors(S,"X");
		if(win(S,"X"))
			return -100;
		else if (win(S,"O"))
			return 100;
		else if (depth == 0)
			return heuristicVal(S);
		int v = 1000000000;
		for (int i = 0; i < successors.size(); i++){
			v = Math.min(v,maxVal(successors.get(i),alpha,beta,depth-1));
			beta = Math.min(v,beta);
			if(beta <= alpha)
				return v;

		}
		return v;
	}
	
	//heuristic is the maximum that we have in a row minus how many the opponent has in a row
	//Do alpha-beta pruning
	 
}
