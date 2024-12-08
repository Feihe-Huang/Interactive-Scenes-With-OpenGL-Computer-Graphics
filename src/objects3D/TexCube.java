package objects3D;

import static org.lwjgl.opengl.GL11.*;


import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {

	public TexCube() {

	}

	// Implement using notes and looking at TexSphere
	// this method is used to draw a texturecube
	public void drawTexCube(Boolean thin) {
		// texture coordinates
		float s, t;


		// set the all eight vertices of the cube
		if (thin) {
//			Point4f vertices[] = {new Point4f(-1f, -1f, -0.01f, 0.0f), new Point4f(-1f, -1f, 0.01f, 0.0f),
//					new Point4f(-1f, 1f, -0.01f, 0.0f), new Point4f(-1f, 1f, 0.01f, 0.0f),
//					new Point4f(1f, -1f, -0.01f, 0.0f), new Point4f(1f, -1f, 0.01f, 0.0f),
//					new Point4f(1f, 1f, -0.01f, 0.0f), new Point4f(1f, 1f, 0.01f, 0.0f)};

			Point4f vertices[] = { new Point4f(-1.0f, -1.0f, 1f, 0.0f), new Point4f(-1.0f, -1.0f, 1f, 0.0f),
					new Point4f(-1.0f, 1.0f, 1f, 0.0f), new Point4f(-1.0f, 1.0f, 1f, 0.0f),
					new Point4f(1.0f, -1.0f, -1.0f, 0.0f), new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
					new Point4f(1.0f, 1.0f, -1.0f, 0.0f), new Point4f(1.0f, 1.0f, 1.0f, 0.0f) };


			// set all the 12 faces of the cube
			int faces[][] = { { 0, 4, 5, 1 }, { 0, 2, 6, 4 }, { 0, 1, 3, 2 }, { 4, 6, 7, 5 }, { 1, 5, 7, 3 },
					{ 2, 3, 7, 6 } };

			// begin to draw a graphic
			glBegin(GL_QUADS);

			// draw the cube by using a
			// for loop to set every point on the cube
			for (int face = 0; face < 6; face++)
			{ // per face
				Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
				Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
				Vector4f normal = v.cross(w).Normal();
				// get the normals of the cube to draw the graphic
				glNormal3f(normal.x, normal.y, normal.z);

				// set the value of the texture coordinates
				t = 0.5f ;
				s = 0 ;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);

				// set the value of the texture coordinates
				t =  0.5f  ;
				s =  0.5f  ;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);

				// set the value of the texture coordinates
				t = 0 ;
				s =  0.5f ;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);

				// set the value of the texture coordinates
				t = 0;
				s = 0;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);

			} // per face

			// end to draw a graphic
			glEnd();
		} else {
			Point4f vertices[] = { new Point4f(-1f, -1f, -1f, 0.0f), new Point4f(-1f, -1f, 1f, 0.0f),
				new Point4f(-1f, 1f, -1f, 0.0f), new Point4f(-1f, 1f, 1f, 0.0f),
				new Point4f(1f, -1f, -1f, 0.0f), new Point4f(1f, -1f, 1f, 0.0f),
				new Point4f(1f, 1f, -1f, 0.0f), new Point4f(1f, 1f, 1f, 0.0f) };

			// set all the 12 faces of the cube
			int faces[][] = { { 0, 4, 5, 1 }, { 0, 2, 6, 4 }, { 0, 1, 3, 2 }, { 4, 6, 7, 5 }, { 1, 5, 7, 3 },
					{ 2, 3, 7, 6 } };

			// begin to draw a graphic
			glBegin(GL_QUADS);

			// draw the cube by using a
			// for loop to set every point on the cube
			for (int face = 0; face < 6; face++)
			{ // per face
				Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
				Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
				Vector4f normal = v.cross(w).Normal();
				// get the normals of the cube to draw the graphic
				glNormal3f(normal.x, normal.y, normal.z);

				// set the value of the texture coordinates
				t = 0.5f ;
				s = 0 ;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);

				// set the value of the texture coordinates
				t =  0.5f  ;
				s =  0.5f  ;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);

				// set the value of the texture coordinates
				t = 0 ;
				s =  0.5f ;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);

				// set the value of the texture coordinates
				t = 0;
				s = 0;
				glTexCoord2f(s, t);
				// set the vertexes of the cube
				glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);

			} // per face

			// end to draw a graphic
			glEnd();
		}


	}

}

/*
 * 
 * 
 * }
 * 
 */
