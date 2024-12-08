package objects3D;

import static org.lwjgl.opengl.GL11.*;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


public class Human {


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
	Texture texture, texture_1, texture_2, texture_3, texture_4, texture_5, texture_6;

	public Human(Texture texture,Texture texture_1,Texture texture_2,Texture texture_3, Texture texture_4, Texture texture_5, Texture texture_6) {
		this.texture = texture;
		this.texture_1 = texture_1;
		this.texture_2 = texture_2;
		this.texture_3 = texture_3;
		this.texture_4 = texture_4;
		this.texture_5 = texture_5;
		this.texture_6 = texture_6;
	}



	// this method is used to draw a human with animation and texture
	public void drawHuman(float delta, float handMove, boolean justTalk, boolean startRun, boolean startRocket, boolean startDrink, boolean startCheers, boolean startJump, boolean startWave)
	{

		// Initializes spheres and cylinders
		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();
		Cube cube = new Cube();
		TexCylinder texCylinder = new TexCylinder();


		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation = 0;
		float handRotation1 = 0;
		float drinkRotation = 0;
		float drinkRotation1 = 0;


		if (startRun) {
			// set the range of the animation motion
			// when clicking the key "A"
			LimbRotation = (float) Math.cos(theta * 6) * 45;
		} else if (justTalk){
			handRotation1 = (float) Math.cos(theta * 2.2 + handMove) * 45;
//			handRotation2 = (float) Math.cos(theta * handMove * 6) * 45;
		} else if (startDrink){
			drinkRotation = (float) Math.cos(theta * 1.5 + handMove) * 45 - 55;
			drinkRotation1 = (float) Math.cos(theta * 1.5 + handMove) * 20;

		}


		// draw the abdomen of the human
		// pushes current matrix
//		glPushMatrix();
		{
			// set the position/coordinate of the human abdomen
			glTranslatef(0.0f, 0.0f, 0.0f);
			// rotate the cylinder in the correct position
			glRotatef(-100.0f, 0.0f, 0.0f, 0.0f);

			// cheers wave body
			if (startCheers && Math.cos(-theta * 6) > 0){
				glRotatef((float) (5 * Math.cos(-theta * 6)) , 0.0f, 1.0f, 0.0f);
			}

			// Control the overall size of the character
			glScalef(0.55f, 0.55f, 0.55f);

			// draw a cylinder as the human abdomen
//			cylinder.drawCylinder(0.5f, 0.9f, 32);

			// draw the chest of the human
			// pushes current matrix
			glPushMatrix();
			{
				// set the position/coordinate of the human chest
				glTranslatef(0.0f, 0.0f, 0.5f);
				// draw a cylinder as the human chest
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
				Color.white.bind();
				texture_6.bind();
				glEnable(GL_TEXTURE_2D);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
				texCylinder.drawTexCylinder(0.5f, 1f, 32);
				glDisable(GL_TEXTURE_2D);

				// draw the neck of the human
				// set the color (orange) of the human neck
				glColor3f(orange[0], orange[1], orange[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
				// pushes current matrix
				glPushMatrix();
				{
					// set the position/coordinate of the human chest
					glTranslatef(0.0f, 0.0f, 0.8f);
					// draw a cylinder as the human neck
					cylinder.drawCylinder(0.15f, 0.4f, 32);

					// draw a human head with texture
					glPushMatrix();
					{
						// initializes the textured sphere
						TexSphere MyGlobe = new TexSphere();
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
						// set the position/coordinate of the human shoulder
						glTranslatef(0.0f, 0.0f, 0.8f);
						// set the size of the human head
						Color.white.bind();
						texture.bind();
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
						if (handMove == -1){
							glRotatef( (float) 120, 0f, -1.0f, 0f);
						}
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
							if (LimbRotation > 0 && !justTalk && !startCheers) {
								// when the right arm lift back
								glRotatef(LimbRotation / 1.3f, 1.0f, 0.0f, 0.0f);
							} else if (LimbRotation <= 0 && !justTalk && !startCheers){
								// when the right arm lift front
								glRotatef(LimbRotation * 1.4f, 1.0f, 0.0f, 0.0f);
							} else if (justTalk && handRotation1>0 && !startCheers) {
								// when the right arm lift front
								glRotatef((float) (handRotation1 / 1.3) , 1.0f, 0.0f, 0.0f);
							} else if (startCheers){
								// cheers
								glRotatef((float) (20 * Math.cos(-theta * 6)) , 1.0f, 0.0f, 0.0f);

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
								if (handMove == -1){
									glRotatef(90, 0f, 0.0f, 1.0f);
									if (Math.cos(theta * 6) > 0) {
										glRotatef((float) (45 * (Math.cos(theta * 6))), 0, 0, -1);
									}
								}
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

					// draw a human left shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					// pushes current matrix
					glPushMatrix();
					{
						// set the position/coordinate of the human shoulder
						glTranslatef(-0.6f, 0.4f, 0.8f);

						if (startWave){
						// start wave
						glRotatef( (float) (100f + 5 * (Math.cos(theta * 6))), 0f, 1.0f, 0f);
						} else if (startRocket ){
							// start rocket
							glRotatef( (float)(135 - 45 * Math.cos(theta * 2)), 0f, 1.0f, 0f);
						} else if (startCheers){
							// start cheers
							glRotatef( (float) (90 + 20 * (Math.cos(theta * 6))), 0f, 1.0f, 0f);
						} else if (handMove == -1){
							glRotatef( (float) 120, 0f, 1.0f, 0f);
						}

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
							if (LimbRotation > 0 && !startDrink){
								// left arm lift back
								glRotatef(LimbRotation * 1.4f, -1.0f, 0.0f, 0.0f);
							} else if (LimbRotation <= 0 && !startDrink){
								// left arm lift front
								glRotatef( LimbRotation / 1.3f, -1.0f, 0.0f, 0.0f);
							}
							else if (startDrink ) {
								glRotatef(20,0,-1,0);
								// left arm lift front
								glRotatef( drinkRotation / 1.3f, -1.0f, 0.0f, 0.0f);
							}
							// draw the cylinder
							cylinder.drawCylinder(0.15f, 0.8f, 32);

							// draw the human left elbow
							// set the color of the elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								// set the position/coordinate of the human elbow
								glTranslatef(0.0f, 0.0f, 0.85f);
								// wave hands
								if (startWave){
									glRotatef(-90, 0f, 0.0f, 1.0f);
									glRotatef((float) (23 * (Math.cos(theta * 6))), 1,0,0);
								} else if (startRocket){
									// start rocket wave
									glRotatef(-90, 1f, 0.0f, 0f);
								} else if (startCheers){
									// cheers left
									glRotatef(-90, 0f, 0.0f, 1.0f);
									glRotatef(20, 1,0,0);
								} else if (handMove == -1){
									glRotatef(-90, 0f, 0.0f, 1.0f);
									if (Math.cos(theta * 6) > 0) {
										glRotatef((float) (45 * (Math.cos(theta * 6))), 0, 0, 1);
									}
								}
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

									// drink
									if (startDrink && drinkRotation1<0){
										glRotatef(drinkRotation1/1.3f, -1.0f, 0.0f, 0.0f);
									}
									// draw the cylinder
									cylinder.drawCylinder(0.1f, 0.63f, 32);


									// draw the left hand
									glPushMatrix();
									{
										// set the position/coordinate of the human sphere
										glTranslatef(0.0f, 0.0f, 0.75f);

										// drink
										if (startDrink) {
											glPushMatrix();
											{
												glColor3f(white[0], white[1], white[2]);
												glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
												glTranslatef(0.0f, 0.0f, 0f);
												glRotatef(90,0,0,0);
												cylinder.drawCylinder(0.24f, 0.83f, 32);
												glTranslatef(0.0f, 0.0f, 0.65f);
												cylinder.drawCylinder(0.05f, 0.76f, 32);

											}
											glPopMatrix();
										} else if (startRocket) {
											// start wave rocket
											glPushMatrix();
											{
												glColor3f(black[0], black[1], black[2]);
												glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
												glTranslatef(0.0f, 0.0f, 0f);
												glRotatef(90,0,0,1);
												cylinder.drawCylinder(0.05f, 1.53f, 32);
											}
											glPopMatrix();
												// wave flag
												glPushMatrix();
												{
													glColor3f(red[0], red[1], red[2]);
													glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
													glTranslatef(0.6f, 0.0f,  1.5f);
													glScalef(0.7f,0f,0.4f);
													cube.drawCube();

												}
												glPopMatrix();

										}
										glColor3f(black[0], black[1], black[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
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
				}
				glPopMatrix();

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
								if (LimbRotation > 0 && !startJump){
									// right low log lift front
									glRotatef(-LimbRotation/1.5f , 1.0f, 0.0f, 0.0f);
								} else if (LimbRotation < 0 && !startJump){
									// right low log lift back
									glRotatef(LimbRotation/1.5f  , 1.0f, 0.0f, 0.0f);
								} else if (startJump && Math.cos(theta * 6) > 0){
									glRotatef( (float) ( -80 * (Math.cos(theta * 6))), 1f, 0.0f, 0f);
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

						// draw the human right knee with texture
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
								if (LimbRotation > 0 && !startJump){
									// right low leg lift back
									glRotatef( -LimbRotation/1.5f, 1.0f, 0.0f, 0.0f);
								} else if (LimbRotation <0 && !startJump){
									// right low leg lift front
									glRotatef(LimbRotation / 1.5f , 1.0f, 0.0f, 0.0f);
								} else if (startJump && Math.cos(theta * 6) > 0){
										glRotatef( (float) ( -80 * (Math.cos(theta * 6))), 1f, 0.0f, 0f);
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

