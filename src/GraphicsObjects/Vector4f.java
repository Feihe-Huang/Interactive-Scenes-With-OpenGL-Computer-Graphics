package GraphicsObjects;

public class Vector4f {

	public float x = 0;
	public float y = 0;
	public float z = 0;
	public float a = 0;

	public Vector4f() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}

	public Vector4f(float x, float y, float z, float a) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}

	// this method is used to implement the operation
	// Vector plus a Vector by adding the coordinates of the two vectors
	public Vector4f PlusVector(Vector4f Additonal) {
		return new Vector4f(this.x + Additonal.x, this.y + Additonal.y, this.z + Additonal.z, this.a + Additonal.a);
	}

	// this method is used to implement the operation Vector minus a Vector
	// by subtracting the coordinates of each of the two vectors
	public Vector4f MinusVector(Vector4f Minus) {
		// calculate the coordinates of the new vector
		float newX = x - Minus.x;
		float newY = y - Minus.y;
		float newZ = z - Minus.z;
		float newA = a - Minus.a;
		return new Vector4f(newX, newY, newZ, newA);
	}

	// this method is used to implement the operation Vector plus a point
	// by adding the coordinates of the vector to the coordinates of the point
	public Point4f PlusPoint(Point4f Additonal) {

		// calculate the coordinates of the new point
		float newX = x + Additonal.x;
		float newY = y + Additonal.y;
		float newZ = z + Additonal.z;
		float newA = a + Additonal.a;
		return new Point4f(newX, newY, newZ, newA);
	}
	// Do not implement Vector minus a Point as it is undefined

	// this method is used to implement the operation Vector * scale
	// by multiplying each coordinate of the vector by the scalar
	public Vector4f byScalar(float scale) {
		// calculate the coordinates of the new vector
		float newX = x * scale;
		float newY = y * scale;
		float newZ = z * scale;
		float newA = a * scale;
		// return the new gotten vector
		return new Vector4f(newX, newY, newZ, newA);
	}

	// this method is used to implement returning
	// the negative of a Vector
	public Vector4f NegateVector() {
		// get the coordinates of the new vector
		float newX = -x;
		float newY = -y;
		float newZ = -z;
		float newA = -a;
		// return the new gotten vector
		return new Vector4f(newX, newY, newZ, newA);

	}

	// this method is used to get the length of a Vector after calculating
	public float length() {
		// calculate the length of the new vector and return it
		return (float) Math.sqrt(x * x + y * y + z * z + a * a);
	}

	// this method is used to get the Normal of a Vector by dividing
	// the individual coordinates of the vector by the length of the vector
	public Vector4f Normal() {
		float LengthOfTheVector = this.length();
		// return the new gotten vector
		return this.byScalar(1.0f / LengthOfTheVector);
	}


	// this method is used to implement getting the dot product of Vector.Vector
	public float dot(Vector4f v) {
		return (this.x * v.x + this.y * v.y + this.z * v.z + this.a * v.a);
	}

	// Implemented this for you to avoid confusion
	// as we will not normally be using 4 float vector
	public Vector4f cross(Vector4f v) {
		float u0 = (this.y * v.z - z * v.y);
		float u1 = (z * v.x - x * v.z);
		float u2 = (x * v.y - y * v.x);
		float u3 = 0; // ignoring this for now
		return new Vector4f(u0, u1, u2, u3);
	}

}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */