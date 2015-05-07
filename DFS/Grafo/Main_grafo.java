package Grafo;

public class Main_grafo {

	public static void main(String[] args) {

		grafo graph = new grafo();

		int i,j;
		int Data[][] = {{ 0, 0, 0, 0}, 
						{ 0, 1, 1 ,0}, 
						{ 1, 2, 0 ,1}, 
						{ 1, 1, 1 ,0}, 
						{ 0, 1, 0 ,2}, 
						{ 1, 2, 0 ,0}, 
						{ 1, 0, 1, 0}};
		
//		Data [0][0]= Data [0][1]= Data [0][2]= Data[0][3]= Data [1][0]= Data [1][2]= Data [4][0]= Data [4][2]= Data [5][2]= Data [6][1]=  0;
//		Data [1][1]= Data [1][2]= Data [2][0]= Data [3][0]= Data [3][1]= Data [3][2]= Data [4][1]= Data [5][0]= Data [6][0]= Data [6][2]= 1;
//		Data [2][1]= Data [5][1]= Data[4][3]= 2 ;

		int[] r = new int [4];
		r[0] = r[2] = 2;
		r[1]= r[3] = 3;
		
		int [][] mat1,mat2= new int[Data.length][r.length];
		
		mat1=graph.createGrafo(Data, r, 0);
		mat2=graph.createGrafo(Data, r, 1);

		System.out.println("Mat1:");
		
		for (i=0;i<Data.length;i++){
			for (j=0;j<r.length;j++){
			System.out.print(mat1[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Mat2:");
		for (i=0;i<Data.length;i++){
			for (j=0;j<r.length;j++){
			System.out.print(mat2[i][j] + " ");
			}
			System.out.println();
		}
		

	}

}
