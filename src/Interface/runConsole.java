package Interface;

import java.io.File;
import java.io.IOException;

public class runConsole {
	int nrest;
	int ntabu;
	int var;
	String test, train;
	String mode;

	/** Method that from the input console fields, gets the necessary values to execute
	 * the program
	 * 
	 * @param args vector of strings with the arguments obtained from the console 
	 * @throws IOException problem with the train/test file to read
	 */
		
	public runConsole(String args[]) throws IOException {
		//System.out.println("Too few arguments.");
		//System.out.println("Usage: jar -jar <<JAR-NAME>>.jar <<TRAIN-NAME>>.csv <<TEST-NAME>>.csv <<MDL/LL>> <<NUMBER-OF-RANDOM-RESTARTS>> <<VAR-TO-INFER>> ");
		//System.exit(-1);
		
		String dir;
		if(args.length < 3){
			System.out.println("Too few arguments.");
			System.out.println("Usage: jar -jar <<JAR-NAME>>.jar <<TRAIN-NAME>>.csv <<TEST-NAME>>.csv <<MDL/LL>> <<NUMBER-OF-RANDOM-RESTARTS>> <<VAR-TO-INFER>> ");
			System.exit(-1);
		}
		if(args.length == 3){
			nrest = 0;
		}else{
			if(args.length == 4){
				if(Integer.parseInt(args[3]) > -1){ 
					nrest = Integer.parseInt(args[3]);
				}else{
					System.out.println("Warning: An invalid number of restarts was entered. Program will run by default with zero restarts.");
				}
				ntabu = 0;
				var = -1;
			}else{
				if (args.length == 5){
					var = Integer.parseInt(args[4]);
				}else{
					System.out.println("Too many arguments.");
					System.out.println("Usage: jar -jar <<JAR-NAME>>.jar <<TRAIN-NAME>>.csv <<TEST-NAME>>.csv <<MDL/LL>> <<NUMBER-OF-RANDOM-RESTARTS>> <<VAR-TO-INFER>> ");
					System.exit(-1);
				}
			}
		}
		
		try {
			dir = new File(".").getCanonicalPath();
			train = new String(dir + File.separator + args[0]);
			test = new String(dir + File.separator + args[1]);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		mode = args[2];
		if (mode.equals("MDL")) {
			Executer starter;
			try {
				starter = new Executer();
				starter.run(train, test, 1, nrest, ntabu, var);
				results(starter);
			} catch (InvalidVar e) {
				System.out.println("Variable to infer doesn't exist");
				e.printStackTrace();
			}
		} else {
			if (mode.equals("LL")) {
				Executer starter;
				try {
					starter = new Executer();
					starter.run(train, test, 0, nrest, ntabu, var);
					results(starter);
				} catch (InvalidVar e) {
					System.out.println("Variable to infer doesn't exist");
				} catch (IOException e1){
					System.out.println("File(s) doesn't exist");
				}
			} else {
				System.out.println("ERROR: Score Mode Incorrect");
			}

		}

	}
	/** Method that will print the results to the graphic interface
	 * 
	 * @param starter class with all the results to print
	 * @throws IOException - problem with the train/test file to read
	 */
	private void results(Executer starter) throws IOException {
		
		File tr = new File(train);
		File te = new File(test);
		int i,j,p;
		long time;
		boolean comma;
		String[] tmp2;
		String tmp;
		if(nrest == 0){
			time =starter.elapsedTimeDBN;
		}else{
			time = starter.elapsedTimeDBN/nrest;
		}
		
		if (var == -1) {
			tmp = "AllVariables";
		}else{
			tmp2=starter.names[var].split("_");
			tmp=tmp2[0];
		}
		System.out.println("Parameters: "+tr.getName()+" "+te.getName()+" " + mode + " "+nrest +" "+ tmp +" " + "\nMemory used = "
					+ (starter.mem.totalMemory() - starter.mem.freeMemory()) / 1024 + " kB"
					+ "\nExecution Time : " + starter.elapsedTimeDBN
					+ " ms (Average of = " + (time) + " ms)"
					+ "\nInferred with DN: " + starter.elapsedTimeInfer + " ms\n");
		
		System.out.println("Transition Network: \n");
		System.out.print("=== Inter-slice connectivity \n");
		for (i = 0; i < starter.r.length / 2; i++) {
			p = i + starter.r.length / 2;		
			comma = false;
			System.out.print(starter.names[p] + " : ");
			for (j = 0; j < starter.r.length / 2; j++) {
				if (starter.matrix[j][i] == 1) {
					if (comma) {
						System.out.print(", " + starter.names[j]);
					} else {
						System.out.print(starter.names[j]);
						comma = true;
					}

				}
			}
			System.out.print("\n");
		}
		System.out.print("\n=== Intra-slice connectivity \n");

		for (j = 0; j < starter.r.length / 2; j++) {
			p = j + starter.r.length / 2;
			System.out.print(starter.names[p] + " : ");
			for (i = starter.r.length / 2; i < starter.r.length; i++) {
				comma = false;
				if (starter.matrix[i][j] == 1) {
					if (comma) {
						System.out.print(", " + starter.names[i]);
					} else {
						System.out.print(starter.names[i]);
						comma = true;
					}

				}
			}
			System.out.print("\n");
		}
		System.out.print("\n=== Scores \n");
		System.out.print("LL Score : " + starter.score_llmax);

		System.out.println("\nMDL Score : " + starter.score_mdlmax);
		if (var == -1) {
			System.out.print("\n=== Inferred Values of all variables");
			for (i = 0; i < starter.fut_values.length; i++) {
				System.out.print("\nInferred Value for " + i + " : ");
				comma=false;
				for (j = 0; j < starter.r.length / 2; j++) {
					if (comma){
						System.out.print(", "+starter.fut_values[i][j]);
					}else{
						System.out.print(" "+starter.fut_values[i][j]);
						comma=true;
					}
					
				}
			}

		} else {
			System.out.print("\n=== Inferred Values for variable " + starter.names[var]);
			for (i = 0; i < starter.fut_values.length; i++) {
				System.out.print("\nInferred Value for " + i + " : "+ starter.fut_values[i][0]);

			}
		}

	}

}