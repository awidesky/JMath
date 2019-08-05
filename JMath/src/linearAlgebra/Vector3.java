package linearAlgebra;

public class Vector3 {

	private double x, y, z;
	
	public Vector3(double x, double y, double z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	public void add(Vector3 v) {
		
		x += v.x;
		y += v.y;
		z += v.z;
		
	}
	
	public Vector3 plus(Vector3 v) {
		
		return new Vector3(x + v.x, y + v.y, z + v.z);
		
	}

	public void subtract(Vector3 v) {
		
		x -= v.x;
		y -= v.y;
		z -= v.z;
		
	}
	
	public Vector3 minus(Vector3 v) {
		
		return new Vector3(x - v.x, y - v.y, z - v.z);
		
	}

	
	public void addScaledVertor(Vector3 v, double scale) {
		
		x += v.x * scale;
		y += v.y * scale;
		z += v.z * scale;
		
	}
	
	public void multiple(double d) {
		
		x *= d;
		y *= d;
		z *= d;
		
	}
	
	public double magnitude() {
		
		return Math.sqrt(x*x + y*y + z*z);
		
	}
	
	public double squaredmagnitude() {
		
		return (x*x + y*y + z*z);
		
	}
	
	public Vector3 orthonormalbasis() {
		
		Vector3 c = null;
		
		return c;
		
	}
	
	public double ScalarProduct(Vector3 v) {
		
		return (x * v.x) + (y * v.y) + (z * v.z);
		
	}
	
	public Vector3 VectorProduct(Vector3 v) {
		
		return new Vector3((y * v.z) - (z * v.y),
   						   (z * v.x) - (x * v.z),
						   (z * v.y) - (y * v.x));
		
	}
	
	public void invert() {
		
		x = -x;
		y = -y;
		z = -z;
		
	}
	
	public double getx() {	return x;  }
	public double gety() {	return y;  }
	public double getz() {	return z;  }
}
