package poo_interface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Calc.calcMDL;
import Calc.calcTeta;
import Files.NetInfo;
import Files.Test;
import Files.Train;
import Grafo.grafo;
import Inference.infer;

public class runConsole {

	public runConsole(String args[]) throws IOException {
		String test, train, dir;
		int nrest = Integer.parseInt(args[3]);
		int var;
		int ntabu = 0;
		if (args.length == 4) {
			var = -1;
		} else {
			var = Integer.parseInt(args[4]);
		}

		try {
			dir = new File(".").getCanonicalPath();
			train = new String(dir + File.separator + args[0]);
			test = new String(dir + File.separator + args[1]);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		if (args[2].equals("MDL")) {
			start(train, test, 1, nrest, ntabu, var);
		} else {
			if (args[2].equals("LL")) {
				start(train, test, 0, nrest, ntabu, var);
			} else {
				System.out.println("ERROR: Score Mode Incorrect");
			}

		}

	}

	void start(String train, String test, int mode, int restarts, int ntabu,
			int var) throws IOException {

		long startTime = System.currentTimeMillis();
		int i, j, p;
		boolean comma;
		grafo graph = new grafo();
		NetInfo teste = new Train();
		NetInfo file = new Test();
		((Train) teste).readTrain(train);
		((Test) file).readTest(test);
		int Data[][] = teste.matrix_data;

		int nr_rdm = restarts;
		String[] names = file.VariableNames;

		if (var >= names.length / 2) {
			System.out.println("ERROR : Variable to infer doesn't exist");
		}

		int[] r = teste.vectorR;
		// System.out.println("1");
		calcMDL scmdl = new calcMDL();
		calcTeta tt = new calcTeta();
		// System.out.println("2");
		int[][] mat1 = new int[r.length][(r.length) / 2];
		double score_llmax, score_mdlmax;
		List<double[]> tetas = new ArrayList<double[]>();
		// System.out.println("3");
		mat1 = graph.createGrafo(Data, r, mode, nr_rdm, ntabu);
		// System.out.println("4");
		score_llmax = scmdl.LL(Data, mat1, r);
		// System.out.println("5");
		score_mdlmax = scmdl.MDL(Data, mat1, r);
//		System.out.println("6");
		long stopTime = System.currentTimeMillis();
		long elapsedTimeDBN = stopTime - startTime;

		startTime = System.currentTimeMillis();
		tetas = tt.tetas(Data, mat1, r);

		int[][] fut_values = new int[file.matrix_test.length][r.length / 2];
		int var_to_guess = var;

		infer guess = new infer();
		if (var_to_guess == -1) {
			for (j = 0; j < r.length / 2; j++) {
				for (i = 0; i < file.matrix_test.length; i++) {
					fut_values[i][j] = guess.inf(file.matrix_test, mat1, r, j+ r.length / 2, tetas, i);
				}
			}
		} else {
			for (i = 0; i < file.matrix_test.length; i++) {
				fut_values[i][0] = guess.inf(file.matrix_test, mat1, r,
						var_to_guess + r.length / 2, tetas, i);
			}
		}
		stopTime = System.currentTimeMillis();
		long elapsedTimeInfer = stopTime - startTime;

		Runtime t = Runtime.getRuntime();
		if (nr_rdm != 0) {
			System.out.println("Memory used = "
					+ (t.totalMemory() - t.freeMemory()) / 1024 + " kB"
					+ "\nExecution Time : " + elapsedTimeDBN
					+ " ms (Average of = " + (elapsedTimeDBN / nr_rdm) + " ms)"
					+ "\nInferred with DN: " + elapsedTimeInfer + " ms");
		} else {
			System.out.println("Memory used = "
					+ (t.totalMemory() - t.freeMemory()) / 1024 + " kB"
					+ "\n Building DBN : " + elapsedTimeDBN
					+ " ms (Average of = " + (elapsedTimeDBN) + " ms)"
					+ "\n Inferred with DN: " + elapsedTimeInfer + " ms\n");
		}
		
		System.out.print("=== Inter-slice connectivity \n");
		for (i = 0; i < r.length / 2; i++) {
			p = i + r.length / 2;		
			comma = false;
			System.out.print(names[p] + " : ");
			for (j = 0; j < r.length / 2; j++) {
				if (mat1[j][i] == 1) {
					if (comma) {
						System.out.print(", " + names[j]);
					} else {
						System.out.print(names[j]);
						comma = true;
					}

				}
			}
			System.out.print("\n");
		}
		System.out.print("\n=== Intra-slice connectivity \n");

		for (j = 0; j < r.length / 2; j++) {
			p = j + r.length / 2;
			System.out.print(names[p] + " : ");
			for (i = r.length / 2; i < r.length; i++) {
				comma = false;
				if (mat1[i][j] == 1) {
					if (comma) {
						System.out.print(", " + names[i]);
					} else {
						System.out.print(names[i]);
						comma = true;
					}

				}
			}
			System.out.print("\n");
		}
		System.out.print("\n=== Scores \n");
		System.out.print("LL Score : " + score_llmax);

		System.out.println("\nMDL Score : " + score_mdlmax);
		if (var == -1) {
			System.out.print("\n=== Inferred Values of all variables");
			for (i = 0; i < file.matrix_test.length; i++) {
				System.out.print("\nInferred Value for " + i + " : ");
				for (j = 0; j < r.length / 2; j++) {
					System.out.print(fut_values[i][j] + ", ");
				}
			}

		} else {
			System.out.print("\n=== Inferred Values for variable" + names[var]);
			for (i = 0; i < file.matrix_test.length; i++) {
				System.out.println("Inferred Value for " + i + " : "+ fut_values[i][0]);

			}
		}

	}

}