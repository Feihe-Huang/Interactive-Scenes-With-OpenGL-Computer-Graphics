package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;


public class BowHuman {


	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };


	// declare individual textures
	Texture texture, texture_1, texture_2, texture_3, texture_4, texture_5;

	public BowHuman(Texture texture, Texture texture_1, Texture texture_2, Texture texture_3, Texture texture_4, Texture texture_5) {
		this.texture = texture;
		this.texture_1 = texture_1;
		this.texture_2 = texture_2;
		this.texture_3 = texture_3;
		this.texture_4 = texture_4;
		this.texture_5 = texture_5;
	}



	// this method is used to draw a human with animation and texture
	public void drawBowHuman(float delta, boolean GoodAnimation)
	{

		// Initializes spheres and cylinders
		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();


		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation;
		if (GoodAnimation) {
			// set the range of the animation motion
			// when clicking the key "A"
			LimbRotation = (float) Math.cos(theta * 6) * 45;
		} else {
			LimbRotation = 0;
		}

//		// Initializes spheres and cylinders
//		Sphere sphere = new Sphere();
//		Cylinder cylinder = new Cylinder();

		// draw the abdomen of the human
		// pushes current matrix
//		glPushMatrix();
		{
			// set the position/coordinate of the human abdomen
			glTranslatef(0.0f, 0.0f, 0.0f);
			// rotate the cylinder in the correct position
			glRotatef(-100.0f, 0.0f, 0.0f, 0.0f);

			// 控制人物整体的大小
			glScalef(0.55f, 0.55f, 0.55f);

			// draw a cylinder as the human abdomen
//			cylinder.drawCylinder(0.5f, 0.9f, 32);

			// draw the chest of the human
			// set the color (green) of the human chest
			glColor3f(green[0], green[1], green[2]);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
			// pushes current matrix
			glPushMatrix();
			{
				// set the position/coordinate of the human chest
				glTranslatef(0.0f, 0.0f, 0.5f);
				// draw a cylinder as the human chest
				cylinder.drawCylinder(0.5f, 1.0f, 32);

				// draw the neck of the human
				// set the color (orange) of the human neck
//				glColor3f(orange[0], orange[1], orange[2]);
//				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
//				// pushes current matrix
//				glPushMatrix();
//				{
//					// set the position/coordinate of the human chest
//					glTranslatef(0.0f, 0.0f, 0.8f);
//					// draw a cylinder as the human neck
//					cylinder.drawCylinder(0.15f, 0.4f, 32);

					// draw a human head with texture
					glPushMatrix();
					{
						// initializes the textured sphere
						TexSphere MyGlobe = new TexSphere();
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
						// set the position/coordinate of the human shoulder
						glTranslatef(0.0f, 0.0f, 1.5f);
						// set the size of the human head
						Color.white.bind();
						texture_1.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
						// draw the sphere with texture
						MyGlobe.drawTexSphere(0.6f, 32, 32, texture);
						glDisable(GL_TEXTURE_2D);
						glPopMatrix();

					}
					glPopMatrix();

					// draw a human right shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					// pushes current matrix
					glPushMatrix();
					{
						// set the position/coordinate of the human shoulder
						glTranslatef(0.6f, 0.4f, 0.8f);
						// draw the sphere
						sphere.drawSphere(0.3f, 32, 32);

						// draw the human right arm
						// set the color (orange) of the human arm
						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						// pushes current matrix
						glPushMatrix();
						{
							// set the position/coordinate of the human arm
							glTranslatef(0.0f, 0.0f, 0.0f);
							// rotate the cylinder in the correct position
							glRotatef(180.0f, 1.0f, 0.0f, 0.0f);

							// determine the direction of the person's arm swing
							// and determine the range of motion
							if (LimbRotation > 0) {
								// when the right arm lift back
								glRotatef(LimbRotation / 1.3f, 1.0f, 0.0f, 0.0f);
							} else {
								// when the right arm lift front
								glRotatef(LimbRotation * 1.4f, 1.0f, 0.0f, 0.0f);

							}
							// draw the cylinder
							cylinder.drawCylinder(0.15f, 0.8f, 32);

							// draw the human right elbow
							// set the color (blue) of the human elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							// pushes current matrix
							glPushMatrix();
							{
								// set the position/coordinate of the human elbow
								glTranslatef(0.0f, 0.0f, 0.85f);
								// draw the sphere
								sphere.drawSphere(0.2f, 32, 32);

								// draw the human right forearm
								// set the color (orange) of the human forearm
								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								// pushes current matrix
								glPushMatrix();
								{
									// set the position/coordinate of the human forearm
									glTranslatef(0.0f, 0.0f, 0.0f);
									// rotate the cylinder in the correct position
									glRotatef(70.0f, 1.0f, 0.0f, 0.0f);
									// draw the cylinder
									cylinder.drawCylinder(0.1f, 0.63f, 32);

									// draw the human right hand
									// pushes current matrix
									glColor3f(black[0], black[1], black[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.23f, 32, 32);

									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();

					// draw a human right shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					// pushes current matrix
					glPushMatrix();
					{
						// set the position/coordinate of the human shoulder
						glTranslatef(-0.6f, 0.4f, 0.8f);
						// draw the sphere
						sphere.drawSphere(0.3f, 32, 32);


						// draw the human left arm
						// set the color of the arm
						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						glPushMatrix();
						{
							// set the position/coordinate of the human arm
							glTranslatef(0.0f, 0.0f, 0.0f);
							// rotate the cylinder in the correct position
							glRotatef(180.0f, 1.0f, 0.0f, 0.0f);

							// determine the direction of the person's arm swing
							// and determine the range of motion
							if (LimbRotation > 0){
								// left arm lift back
								glRotatef(LimbRotation * 1.4f, -1.0f, 0.0f, 0.0f);
							} else {
								// left arm lift front
								glRotatef( LimbRotation / 1.3f, -1.0f, 0.0f, 0.0f);
							}
							// draw the cylinder
							cylinder.drawCylinder(0.15f, 0.8f, 32);

							// draw teh human left elbow
							// set the color of the elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								// set the position/coordinate of the human elbow
								glTranslatef(0.0f, 0.0f, 0.85f);
								// draw the sphere
								sphere.drawSphere(0.2f, 32, 32);

								// draw the human left forearm
								// set the color of the forearm
								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								// pushes current matrix
								glPushMatrix();
								{
									// set the position/coordinate of the human elbow
									glTranslatef(0.0f, 0.0f, 0.0f);
									// rotate the cylinder in the correct position
									glRotatef(70.0f, 1.0f, 0.0f, 0.0f);
									// draw the cylinder
									cylinder.drawCylinder(0.1f, 0.63f, 32);

									// draw the left hand
									glColor3f(black[0], black[1], black[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										// set the position/coordinate of the human sphere
										glTranslatef(0.0f, 0.0f, 0.75f);
										// draw the sphere
										sphere.drawSphere(0.23f, 32, 32);
									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
//				}
//				glPopMatrix();

				// draw the human pelvis
				// set the color of the human pelvis
				glColor3f(orange[0], orange[1], orange[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
				glPushMatrix();
				{
					// set the position/coordinate of the human pelvis
					glTranslatef(0.0f, 0.0f, 0.2f);
					// draw the sphere
					sphere.drawSphere(0.5f, 32, 32);
					glPopMatrix();
				}

				// draw the left hip with texture
				glColor3f(pink[0], pink[1], pink[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					// set the position/coordinate of the human hip
					glTranslatef(-0.5f, 0.2f, -0.25f);
					// rotate the sphere in the correct position
					glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
					// draw the sphere
					sphere.drawSphere(0.25f, 32, 32);


					// draw the human left high leg
					// set the color of the human leg
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						// set the position/coordinate of the human leg
						glTranslatef(0.0f, 0.0f, 0.0f);
						// rotate the cylinder in the correct position
						glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
						// determine the direction of the person's leg swing
						// and determine the range of motion
						if (LimbRotation > 0){
							// left leg lift front
							glRotatef( (LimbRotation * 1.3f) + 90, 1.0f, 0.0f, 0.0f);
						} else {
							// left leg lift back
							glRotatef( (LimbRotation/1.3f ) + 90, 1.0f, 0.0f, 0.0f);
						}
						// draw the cylinder
						cylinder.drawCylinder(0.15f, 1.1f, 32);

						// draw the left knee with texture
						glColor3f(grey[0], grey[1], grey[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							// set the position of the keen
							glTranslatef(0.0f, 0.0f, 0.95f);
							sphere.drawSphere(0.25f, 32, 32);


							// draw the human left low leg
							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								// set the position/coordinate of the human low leg
								glTranslatef(0.0f, 0.0f, 0.0f);
								// determine the direction of the person's leg swing
								// and determine the range of motion
								if (LimbRotation > 0){
									// right low log lift front
									glRotatef(-LimbRotation/1.5f , 1.0f, 0.0f, 0.0f);
								} else {
									// right low log lift back
									glRotatef(LimbRotation/1.5f  , 1.0f, 0.0f, 0.0f);
								}
								// draw the cylinder
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// draw the human left foot
								// set the color of the sphere
								glColor3f(cyan[0], cyan[1], cyan[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									// set the position/coordinate of the human foot
									glTranslatef(0.0f, 0.0f, 1.0f);
									// rotate the cylinder in the correct position
									glRotatef(90,1.0f,0.0f,0.0f);
									// draw the spheres and the cylinder to form a foot
									sphere.drawSphere(0.35f, 32, 32);

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

				// draw the human right hip
				glColor3f(pink[0], pink[1], pink[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					// set the position/coordinate of the human hip
					glTranslatef(0.5f, 0.2f, -0.25f);
					// rotate the sphere in the correct position
					glRotatef(90.0f, 0f, 0.0f, 0.0f);
					// draw the sphere
					sphere.drawSphere(0.25f, 32, 32);

					// draw the human right high leg
					// set the color of the leg
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						// set the position/coordinate of the human leg
						glTranslatef(0.0f, 0.0f, 0.0f);
						// rotate the cylinder in the correct position
						glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
						// determine the direction of the person's leg swing
						// and determine the range of motion
						if (LimbRotation > 0){
							// right leg lift back
							glRotatef((-LimbRotation / 1.3f) + 90, 1.0f, 0.0f, 0.0f);
						} else {
							// right leg lift front
							glRotatef( (-LimbRotation * 1.3f ) + 90, 1.0f, 0.0f, 0.0f);
						}
						// draw the cylinder
						cylinder.drawCylinder(0.15f, 1.1f, 32);


						// draw the human right knee
						glColor3f(grey[0], grey[1], grey[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							// set the position of the keen
							glTranslatef(0.0f, 0.0f, 0.95f);
							sphere.drawSphere(0.25f, 32, 32);

							// draw the human right low leg
							// set the color
							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								// set the position/coordinate of the human low leg
								glTranslatef(0.0f, 0.0f, 0.0f);
								// determine the direction of the person's leg swing
								// and determine the range of motion
								if (LimbRotation > 0){
									// right low leg lift back
									glRotatef( -LimbRotation/1.5f, 1.0f, 0.0f, 0.0f);
								} else {
									// right low leg lift front
									glRotatef(LimbRotation / 1.5f , 1.0f, 0.0f, 0.0f);
								}
								// draw the cylinder
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// draw the human right foot
								// set color
								glColor3f(cyan[0], cyan[1], cyan[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									// set the position/coordinate of the human low leg
									glTranslatef(0.0f, 0.0f, 1.0f);
									// rotate the leg to the right position
									glRotatef(90,1.0f,0.0f,0.0f);
									// draw the human foot
									sphere.drawSphere(0.35f, 32, 32);
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();
			}
//			glPopMatrix();
		}
	}
}

/*
 * 
 * 
 * }
 * 
 */
