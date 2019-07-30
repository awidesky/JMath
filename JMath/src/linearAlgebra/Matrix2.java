package linearAlgebra;

import exceptions.MatrixMultiplicationException;

public class Matrix2 {

	private int data[][];
	private int demicalPoint;
	
	private int row, column;
	private boolean isSquare;
	
	public Matrix2(int input[][]) {
		// TODO Auto-generated constructor stub

		row = input.length;
		
		demicalPoint = 0;
		int max = 0;
		for(int i = 0 ; i < input.length ; i++) { // find longest column lenght
			
			if (max < input[i].length) max = input[i].length; 
			
		}
		
		column = max;
		
		isSquare = (row == column) ? true : false;
		
		data = new int[row][column]; // init data with 0
		
		
		// put element of input to data
		for(int i = 0 ; i < input.length ; i++) {
			
			for(int j = 0 ; j < input[i].length ; j++) {
				
				data[i][j] = input[i][j];
				
			}
			
		}
		
	} // constructor end
	
	
	public Matrix2(double input[][], int dem) {
		// TODO Auto-generated constructor stub
	
		demicalPoint = dem;
		
		row = input.length;
		
		int max = 0;
		for(int i = 0 ; i < input.length ; i++) { // find longest column lenght
			
			if (max < input[i].length) max = input[i].length; 
			
		}
		
		column = max;
		
		isSquare = (row == column) ? true : false;
		
		data = new int[row][column]; // init data with 0
		
		
		// put element of input to data
		for(int i = 0 ; i < input.length ; i++) {
			
			for(int j = 0 ; j < input[i].length ; j++) {
				
				data[i][j] = (int)input[i][j] * (int)Math.pow(10, demicalPoint);
				
			}
			
		}
		
	} // constructor end
	
	public Matrix2(int row, int column) {
		// TODO Auto-generated constructor stub
		
		this.row = row;
		this.column = column;
		this.isSquare = (row == column) ? true : false;
		
		this.data = new int[row][column];
		
	} // constructor end
	

	public void set(int i, int j, int k) {
		// TODO Auto-generated method stub
		data[i-1][j-1] = k;
	} // set end


	public int get(int i, int j) {
		// TODO Auto-generated method stub
		return data[i-1][j-1];
	} // get end
	
	public int getrow() { return row; }
	
	public int getcolumn() { return column; }
	
	public boolean isSquare() { return isSquare; }


	public Matrix2 plus(Matrix2 b) {
		
		if (this.row != b.row || this.column != b.column) return null;
		
		Matrix2 result = new Matrix2(this.row, this.column);
		
		for(int i = 1 ; i < row + 1; i++) {
			
			for(int j = 1 ; j < column + 1; j++) {
				
				result.set(i, j, data[i-1][j-1] + b.get(i, j));
				
			}
			
		}
		
		return result;
	} // plus end
	
	public Matrix2 times(Matrix2 b) throws MatrixMultiplicationException {
		
		if (this.row != b.row || this.column != b.column) throw new MatrixMultiplicationException("unable to Multiplication- " + column + " != " + b.row);
		
		Matrix2 result = new Matrix2(row, b.column);
		
		for(int r = 1 ; r <= b.row ; r++) {
			
			for(int c = 1 ; c <= column ; c++) {
				
				for(int k = 1 ; k <= column ; k++) {
					
					result.set(r, c, this.get(r, k) * b.get(k, c));
					
				}
				
			}
			
		}
		
		return result; 
	}
	
	
	
	
} // class end
