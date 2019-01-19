import java.util.*;

class BayesianNetwork{








	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the query: ");
		String query = sc.nextLine();
		System.out.print("Please enter the query outcome: ");
		String qOutcome = sc.nextLine();
		
		ArrayList<String> evidence = new ArrayList<String>();
		ArrayList<String> eOutcomes = new ArrayList<String>();
		
		System.out.print("Please enter the evidence, or \"done\" if you are finished: ");
		String strEvidence = sc.nextLine();
		while(!(strEvidence.equals("done"))){
			evidence.add(strEvidence);
			System.out.print("Please enter the evidence outcome: ");
			eOutcomes.add(sc.nextLine());
			System.out.print("Please enter the evidence, or \"done\" if you are finished: ");
			strEvidence = sc.nextLine();
		}

	}	
}