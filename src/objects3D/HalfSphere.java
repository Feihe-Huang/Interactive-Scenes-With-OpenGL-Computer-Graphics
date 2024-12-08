package objects3D;

import static org.lwjgl.opengl.GL11.*;

public class HalfSphere {

	public HalfSphere() {

	}

	// Implement using notes and examine Tetrahedron to aid in the coding look at
	// lecture 7 , 7b and 8
	// 7b should be your primary source, we will cover more about circles in later
	// lectures to understand why the code works
	public void drawHalfSphere(float radius, float nSlices, float nSegments) {

		// compute the latitude(phi) and the longitude(theta)
		float inctheta = (float) ((2.0f * Math.PI)/nSlices);
		float incphi = (float) (Math.PI / nSegments);

		// use GL_QUADS instead of GL_TRIANGLES to draw the sphere
		glBegin(GL_QUADS);
		// for loop to draw the sphere
		for(float theta = (float) -Math.PI/2; theta < Math.PI/2; theta += inctheta) {
			for(float phi = (float) -(Math.PI/2.0f); phi < (Math.PI/2.0f); phi += incphi) {

				// set the changeable vertexes to get every point
				// on the sphere to draw the graphic
				float x = (float) (radius * Math.cos(phi) * Math.cos(theta));
				float y = (float) (radius * Math.cos(phi) * Math.sin(theta));
				float z = (float) (radius * Math.sin(phi));
				// another changeable vertex on the sphere
				float x1 =(float) ( radius * Math.cos(theta+inctheta)* Math.cos (phi));
				float y1 = (float) (radius * Math.sin(theta+inctheta) * Math.cos (phi));
				float z1 = (float) (radius * Math.sin(phi));
				// another changeable vertex on the sphere
				float x2 = (float) ( radius * Math.cos(theta+inctheta)* Math. cos (phi+incphi));
				float y2 = (float) ( radius * Math. sin(theta+inctheta) * Math.cos(phi+incphi));
				float z2 = (float) (radius * Math. sin(phi+incphi));
				// another changeable vertex on the sphere
				float x3 = (float) ( radius * Math.cos (theta) * Math.cos (phi+incphi));
				float y3 = (float) (radius * Math.sin(theta) * Math.cos(phi+incphi));
				float z3 = (float) (radius * Math.sin(phi+incphi));

				// set the normals and the vertexes to draw the graphic
				glNormal3f(x,y,z);
				glVertex3f(x,y,z);
				glNormal3f(x1,y1, z1);
				glVertex3f(x1, y1, z1);
				glNormal3f(x2,y2, z2);
				glVertex3f(x2,y2, z2);
				glNormal3f(x3, y3, z3);
				glVertex3f(x3, y3, z3);
			}
		}
		glEnd();
		// end to draw the graphic

	}
}
