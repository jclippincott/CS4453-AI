import java.util.*;

public class SudokuSolver {
	
	public class GridSquare{
		
		int ID;
		int val;
		int[] domain;
		int row;
		int col;
		int square;

		public GridSquare(){
			ID = -1;
			val = 0;
			domain = new int[9];
			for(int i = 1; i < 10; i++){
				domain[i-1]=i;
			}
			row = 0;
			col = 0;
			square = 0;
		}
		
		public GridSquare(int newID, int newVal, int[] newDomain, int newRow, int newCol, int newSquare){
			ID = newID;
			val = newVal;
			domain = newDomain;
			row = newRow;
			col = newCol;
			square = newSquare;
		}
		
		public GridSquare(int newID, int newVal, int newRow, int newCol, int newSquare){
			ID = newID;
			int[] temp = new int[1];
			temp[0]=newVal;
			val = newVal;
			domain = temp;
			row = newRow;
			col = newCol;
			square = newSquare;
		}
		
		public void updateDomain(int[] newDomain){
			domain = newDomain;
		}
	}
	public ArrayList<Integer> findRowIndices(GridSquare search, GridSquare[] squares){
		ArrayList<Integer> found = new ArrayList<Integer>();
		for(int i = 0; i < squares.length; i++){
			if((search.row == squares[i].row) && (!(search.equals(squares[i]))))
				found.add(i);
		}
		return found;
	}
	
	public ArrayList<Integer> findColIndices(GridSquare search, GridSquare[] squares){
		ArrayList<Integer> found = new ArrayList<Integer>();
		for(int i = 0; i < squares.length; i++){
			if((search.col == squares[i].col) && (!(search.equals(squares[i]))))
				found.add(i);
		}
		return found;
	}		
	
	public ArrayList<Integer> findSquareIndices(GridSquare search, GridSquare[] squares){
		ArrayList<Integer> found = new ArrayList<Integer>();
		for(int i = 0; i < squares.length; i++){
			if((search.square == squares[i].square) && (!(search.equals(squares[i]))))
				found.add(i);
		}
		return found;
	}
     
    public GridSquare[] initSquares(String State){
		GridSquare[] vals = new GridSquare[81];
		int[] domain = new int[9];
		for(int i = 1; i < 10; i++){
			domain[i-1]=i;
		}
		
		for(int i = 0; i < 81; i++){
			int row = ((int)(i/9))+1;
			int col = (i%9)+1;
			int square = 1;
			if((row >= 1) && (row <= 3)){
				if((col >= 1) && (col <=3))
					square = 1;
				else if((col >=4) && (col <=6))
					square = 2;
				else if((col >=7) && (col <=9))
					square = 3;
			}
			else if((row >= 4) && (row <= 6)){
				if((col >= 1) && (col <=3))
					square = 4;
				else if((col >=4) && (col <=6))
					square = 5;
				else if((col >=7) && (col <=9))
					square = 6;
			}
			else if((row >= 7) && (row <= 9)){
				if((col >= 1) && (col <=3))
					square = 7;
				else if((col >=4) && (col <=6))
					square = 8;
				else if((col >=7) && (col <=9))
					square = 9;
			}
			
			if((State.substring(i,i+1)).equals("_"))
				vals[i]=new GridSquare(i+1, 0, domain, row, col, square);
			else{
				int val = Integer.parseInt(State.substring(i,i+1));
				vals[i]=new GridSquare(i+1, val, row, col, square);
			}
		}
		return vals;
	}
	
	public boolean findDiffVal(int x, GridSquare x2){
		for(int i = 0; i < (x2.domain).length; i++){
			int val2 = (x2.domain)[i];
			if(x != val2){
				return true;
			}
		}
		return false;
	}
	
	public boolean inconsistentVals(GridSquare[] pair){
		GridSquare x1 = pair[0];
		GridSquare x2 = pair[1];
		for(int i = 0; i < (x1.domain).length; i++){
			if(!(findDiffVal((x1.domain)[i],x2)))
				return true;
		}
		return false;
	}
	
	public GridSquare[] removeInconsistent(GridSquare[] pair){
		GridSquare x1 = pair[0];
		GridSquare x2 = pair[1];
		ArrayList<Integer> remove = new ArrayList<Integer>();
		for(int i = 0; i < (x1.domain).length; i++){
			if(!(findDiffVal((x1.domain)[i],x2)))
				//System.out.printf("Couldn't find a proper value. Removing %d from %d's domain\n", (x1.domain)[i], x1.ID);
				remove.add(i);
		}
		
		int[] newDomain = new int[((x1.domain).length)-remove.size()];
		int index = 0;
		for(int i = 0; i < (x1.domain).length; i++){
			if(!(remove.contains(i))){
				newDomain[index]=(x1.domain)[i];
				index++;
			}
		}
		x1.updateDomain(newDomain);
		pair[0]=x1;
		pair[1]=x2;
		return pair;
	}
	
	public int findMatch(GridSquare x1, GridSquare[] vals){
		for(int i = 0; i < vals.length; i++){
			if((x1.ID)==(vals[i].ID))
				return i;
		}
		return 0;
	}
	
	public ArrayList<GridSquare> findNeighbors (GridSquare x1, GridSquare[] csp){
		ArrayList<GridSquare> neighbors = new ArrayList<GridSquare>();
		
		
		return neighbors;
	}
	
	
    public String solve(String State){
       
            String ans = "";
			
			//String s1 = "8__________36______7___9_2___5___7_______457_____1___3___1____68__85___1__9____4__";
			String s2 = "__9748___7_________2_1_9_____7___24__64_1_59__98___3_____8_3_2_________6___2759__";
			
			GridSquare[] vals = initSquares(s2);
			for(int i = 0; i < vals.length; i++){
				String ID = Integer.toString((vals[i]).ID);
				String val = Integer.toString((vals[i]).val);
				String row = Integer.toString((vals[i]).row);
				String col = Integer.toString((vals[i]).col);
				String square = Integer.toString((vals[i]).square);
				System.out.print(ID+": "+val+", "+row+", "+col+", "+square+", {");
				for(int j = 0; j < (vals[i].domain).length; j++){
					System.out.print((vals[i].domain)[j]);	
					System.out.print(",");
				}
				System.out.println("}");
			}
			ArrayDeque<GridSquare[]> squareQueue = new ArrayDeque<GridSquare[]>();
			for(int i = 0; i < 81; i++){
				GridSquare[] pair = new GridSquare[2];
				ArrayList<Integer> row = findColIndices(vals[i],vals);
				ArrayList<Integer> col = findColIndices(vals[i],vals);
				ArrayList<Integer> square = findSquareIndices(vals[i],vals);
				for(int j = 0; j < 8; j++){
					pair[0]=vals[i];
					pair[1]=vals[row.get(j)];
					squareQueue.addLast(pair);
				}
				for(int j = 0; j < 8; j++){
					pair[0]=vals[i];
					pair[1]=vals[col.get(j)];
					squareQueue.addLast(pair);
				}
				for(int j = 0; j < 8; j++){
					pair[0]=vals[i];
					pair[1]=vals[square.get(j)];
					squareQueue.addLast(pair);
				}
			}
								
			while(squareQueue.peek() != null){
				GridSquare[] curr = squareQueue.removeFirst();
				GridSquare[] temp = removeInconsistent(curr);
				if(inconsistentVals(curr)){
					System.out.println("removed");
					GridSquare x1 = temp[0];
					vals[findMatch(x1,vals)]=x1;
					ArrayList<GridSquare> neighbors = findNeighbors(x1,vals);
					GridSquare[] pair = new GridSquare[2];
					for(int i = 0; i < neighbors.size(); i++){
						pair[0]=x1;
						pair[0]=neighbors.get(i);
						squareQueue.addLast(pair);
					}
				}	
			}
			
			for(int i = 0; i < vals.length; i++){
				String ID = Integer.toString((vals[i]).ID);
				String val = Integer.toString((vals[i]).val);
				String row = Integer.toString((vals[i]).row);
				String col = Integer.toString((vals[i]).col);
				String square = Integer.toString((vals[i]).square);
				System.out.print(ID+": "+val+", "+row+", "+col+", "+square+", {");
				for(int j = 0; j < (vals[i].domain).length; j++){
					System.out.print((vals[i].domain)[j]);	
					System.out.print(",");
				}
				System.out.println("}");
			}
			

            return ans;
    }
}
