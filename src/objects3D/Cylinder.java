package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void drawCylinder(float radius, float height, int nSegments ) 
	{
		// begin to draw a graphic
		glBegin(GL_TRIANGLES);
		// a loop around circumference of a tube
		for (float i = 0.0F; i < nSegments; i += 1.0) {
			// compute each of the angles of the segments
			float angle = (float) (Math.PI * i * 2.0 / nSegments) ;
			float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

			// compute sin & cosine to get the x, y values of the triangles
			float x1 = (float) Math.sin(angle)*radius;
			float y1 = (float) Math.cos(angle)*radius;
			float x2 = (float) Math.sin(nextAngle)*radius;
			float y2 = (float) Math.cos(nextAngle)*radius;
			// draw top (green) triangles of the cylinder
			glNormal3f(x1, y1, 0); glVertex3f(x1, y1, 0);
			glNormal3f(x2, y2, 0); glVertex3f(x2, y2, height);
			glNormal3f(x1, y1, 0); glVertex3f(x1, y1, height);
			// draw bottom (red) triangles of the cylinder
			glNormal3f(x1, y1, 0); glVertex3f(x1, y1, 0);
			glNormal3f(x2, y2, 0); glVertex3f(x2, y2, 0);
			glNormal3f(x2, y2, 0); glVertex3f(x2, y2, height);
		}
		glEnd();
		// end to draw a graphic
		 
	}
}
