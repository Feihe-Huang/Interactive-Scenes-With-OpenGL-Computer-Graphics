
import java.io.IOException;
import java.nio.FloatBuffer;

import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.Project.gluLookAt;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow
{
	private boolean MouseOnepressed = true;
	private boolean dragMode = false;
	private boolean justTalk = true;
	private boolean stageTwo = false;

	private boolean follow = false;
	/** position of pointer */
	float x = 400, y = 300;
	// control the player movement
	float playerX = 0;
	float playerY = 0;
	float playerZ = 0;
	// control the aircraft movement
	float aircraftX = 0;
	float aircraftY = 0;
	float aircraftZ = 0;

	// control the run action
	boolean playerRun = false;
	boolean playerTalk = false;
	boolean playerJump = false;
	boolean playerDrink = false;
	boolean playerCheers = false;
	boolean playerWave = false;
	boolean playerFlag = false;
	// press E or not
	boolean playerAboard = false;
	// Get on or not
	boolean playerAircraft = false;
	boolean nearAircraft = false;
	// judge on buildings
	boolean onBuilding3 = false;
	boolean onBuilding2 = false;
	boolean onBuilding1 = false;

	boolean usedAircraft = false;

	// building Cheer
	boolean buildingCheer = false;
	boolean buildingCheerOne = false;


	// control the rotation
	boolean turnRight = false;
	boolean turnLeft = false;
	boolean turnFront = false;
	boolean turnBack = false;

	// near buildings
	boolean nearBuilding3 =  false;
	boolean nearBuilding2 =  false;
	boolean nearBuilding1 =  false;

	// aircraft can go or not
	boolean airGo = true;
	boolean playerGo = true;
	boolean playerGoOne = true;

	boolean openLight = false;
	boolean nearLight = false;

	// start rocket
	boolean startRocket = false;
	boolean humanControlRocket = false;

	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime; // beginAnimiation

	Arcball MyArcball = new Arcball();

	// 是否画出来地板
	boolean DRAWGRID = true;

	boolean waitForKeyrelease = true;
	/** Mouse movement */
	int LastMouseX = -1;
	int LastMouseY = -1;

	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.

	int OrthoNumber = 500; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
							// // do not change this for assignment 3 but you can change everything for your
							// project

    int lookNumberIn = 0;
    int lookNumberLeft = 0;
    int lookNumberUp = 0;
	// camera logical change
	int lookPointX = 0;
	int lookPointZ = 0;



	// 跳跃
	boolean up = true;


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

	Sphere sphere = new Sphere();
	Cylinder cylinder = new Cylinder();
	Cube cube = new Cube();
	HalfSphere halfSphere = new HalfSphere();
	Tetrahedron tetrahedron = new Tetrahedron();
	Rocket rocket = new Rocket();
	Aircraft aircraft = new Aircraft();
	TexCube texCube = new TexCube();
	TexCylinder texCylinder = new TexCylinder();
	TexSphere texSphere = new TexSphere();
	Shadow shadow = new Shadow();

	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	Texture texture, texture_1, texture_2, texture_3, texture_4, texture_5, texture_6, texture_7, texture_8, texture_9, texture_10,texture_11, texture_12,texture_13,texture_14,texture_15,texture_16,texture_17,texture_18,texture_19;

	public void init() throws IOException
	{
		// set the picture as the texture
//		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/brick.png"));

		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/golden.png"));
		texture_1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/blue.png"));
		texture_2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/yellow.png"));
		texture_3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/car.png"));
		texture_4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/yellow.png"));
		texture_5 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/ground1.jpeg"));
		texture_6 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/building.jpeg"));
		texture_7 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/2.jpeg"));
		texture_8 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/rBase1.jpeg"));
		texture_9 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/base2.jpeg"));
		texture_10 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/blueBall.jpeg"));
        texture_11 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/star.jpg"));
		texture_12 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/valley.png"));
		texture_13 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/face.jpeg"));
		texture_14 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/fire.jpeg"));

		texture_15 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/a.jpeg"));
		texture_16 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/b.jpeg"));
		texture_17 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/c.jpeg"));
		texture_18 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/d.jpeg"));
		texture_19 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/e.jpeg"));


		System.out.println("Texture loaded okay ");
	}


	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	// support method to aid in converting a java float array into a Floatbuffer
	// which is faster for the opengl layer to process

	public void start()
	{
		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta)
	{
		// rotate quad
		// rotation += 0.01f * delta;

		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPostion = Mouse.getDWheel();

		boolean MouseButonPressed = Mouse.isButtonDown(0);

		if (MouseButonPressed && !MouseOnepressed) {
			MouseOnepressed = true;
			// System.out.println("Mouse drag mode");
			MyArcball.startBall(MouseX, MouseY, 1200, 800);
			dragMode = true;

		} else if (!MouseButonPressed) {
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed = false;
			dragMode = false;
		}

		if (dragMode) {
			MyArcball.updateBall(MouseX, MouseY, 1200, 800);
		}

		if (WheelPostion > 0) {
			OrthoNumber += 15;

		}

		if (WheelPostion < 0) {
			OrthoNumber -= 15;
//			if (OrthoNumber < 610) {
//				OrthoNumber = 610;
//			}

            if (OrthoNumber < 500) {
                OrthoNumber = 500;
            }

			// System.out.println("Orth nubmer = " + OrthoNumber);

		}

		/** rest key is R */
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			MyArcball.reset();

		// aboard or not
		if (Keyboard.isKeyDown(Keyboard.KEY_E) && nearAircraft){
			playerAboard = true;
			buildingCheerOne = false;
			buildingCheer = false;
			usedAircraft = true;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_F)){
			playerAboard = false;
			playerAircraft = false;
			buildingCheerOne = false;
			buildingCheer = false;
		}

		if (!playerAircraft) {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				if (playerGo){
					playerZ += 0.09;
				} else {

				}
				playerRun = true;
				turnFront = true;
				turnBack = false;
				turnLeft = false;
				turnRight = false;
				// remove other actions
				playerTalk = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				playerFlag = false;
				buildingCheerOne = false;
				buildingCheer = false;
				startRocket = false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				playerRun = true;
				playerZ -= 0.09;
				turnBack = true;
				turnFront = false;
				turnRight = false;
				turnLeft = false;
				// remove other actions
				playerTalk = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				playerFlag = false;
				buildingCheerOne = false;
				buildingCheer = false;
				startRocket = false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				playerRun = true;
				if (playerGoOne){
					playerX += 0.09;
				} else {

				}
				turnBack = false;
				turnFront = false;
				turnRight = false;
				turnLeft = true;
				// remove other actions
				playerTalk = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				playerFlag = false;
				buildingCheer = false;
				buildingCheerOne = false;
				startRocket = false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				playerRun = true;
				playerX -= 0.09;
				turnBack = false;
				turnFront = false;
				turnRight = true;
				turnLeft = false;
				// remove other actions
				playerTalk = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerFlag = false;
				playerWave = false;
				buildingCheerOne = false;
				buildingCheer = false;
				startRocket = false;
			}
			// keep still when no key is pressed
			if (!Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_S) && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
				playerRun = false;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_1)) { // talk
				playerTalk = true;
				playerRun = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				playerFlag = false;
				buildingCheer = false;
				buildingCheerOne = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) { // jump
				playerTalk = false;
				playerRun = false;
				playerJump = true;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				buildingCheerOne = false;
				buildingCheer = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_2)) { // drink
				playerTalk = false;
				playerRun = false;
				playerJump = false;
				playerDrink = true;
				playerCheers = false;
				playerWave = false;
				playerFlag = false;
				buildingCheer = false;
				buildingCheerOne = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_3)) { // cheers
				playerTalk = false;
				playerRun = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = true;
				playerWave = false;
				playerFlag = false;
				buildingCheer = false;
				buildingCheerOne = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_4)) { //wave
				playerTalk = false;
				playerRun = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = true;
				playerFlag = false;
				buildingCheer = false;
				buildingCheerOne = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_5)) { // jump and cheer
				playerTalk = false;
				playerRun = false;
				playerJump = true;
				playerDrink = false;
				playerCheers = true;
				playerWave = false;
				playerFlag = false;
				buildingCheerOne = false;
				buildingCheer = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_6)) { // wave flag
				playerTalk = false;
				playerRun = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				buildingCheer = false;
				buildingCheerOne = false;
				startRocket = false;
				playerFlag = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_9) && nearBuilding2 && onBuilding2){
				buildingCheer = true;
				playerTalk = false;
				playerRun = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				playerFlag = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_0) && nearBuilding1 && onBuilding1){
				buildingCheerOne = true;
				playerTalk = false;
				playerRun = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				playerFlag = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_L)){ // start rocket
				startRocket = true;
				humanControlRocket = true;
				playerTalk = false;
				playerRun = false;
				playerJump = false;
				playerDrink = false;
				playerCheers = false;
				playerWave = false;
				buildingCheer = false;
				buildingCheerOne = false;
				playerFlag = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_N) && nearLight){
				openLight = true;

			} else if (Keyboard.isKeyDown(Keyboard.KEY_M) && nearLight){
				openLight = false;
			}
		} else {
			// control the aircraft
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				if (airGo) {
					aircraftY += 0.12;
					playerZ += 0.12;
				} else {

				}
				turnFront = true;
				turnBack = false;
				turnLeft = false;
				turnRight = false;
				buildingCheer = false;
				startRocket = false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				aircraftY -= 0.12;
				playerZ -= 0.12;
				turnBack = true;
				turnFront = false;
				turnRight = false;
				turnLeft = false;
				buildingCheer = false;
				startRocket = false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				aircraftX += 0.12;
				playerX -= 0.12;
				turnBack = false;
				turnFront = false;
				turnRight = false;
				startRocket = false;
				turnLeft = true;
				buildingCheer = false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				aircraftX -= 0.12;
				playerX -= 0.12;
				turnBack = false;
				turnFront = false;
				turnRight = true;
				turnLeft = false;
				startRocket = false;
				buildingCheer = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) { // up
				aircraftZ -= 0.12;
				turnBack = false;
				turnFront = false;
				turnRight = false;
				turnLeft = false;
				startRocket = false;
				buildingCheer = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_TAB)) { // down
				aircraftZ += 0.12  ;
				turnBack = false;
				turnFront = false;
				turnRight = false;
				turnLeft = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_I) && nearBuilding3){ // on building 3
				onBuilding3 = true;
				playerAboard = false;
				playerAircraft = false;
				turnBack = false;
				turnFront = false;
				turnRight = false;
				turnLeft = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_O) && nearBuilding2){ // on building 2
				onBuilding2 = true;
				turnBack = false;
				turnFront = false;
				playerAircraft = false;
				turnRight = false;
				turnLeft = false;
				playerAboard = false;
				startRocket = false;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_P) && nearBuilding1){ // on building 1
				onBuilding1 = true;
				turnBack = false;
				turnFront = false;
				turnRight = false;
				playerAircraft = false;
				turnLeft = false;
				playerAboard = false;
				startRocket = false;
			}
		}

		if (usedAircraft){
			nearLight = true;
		}

		if (waitForKeyrelease) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {

				DRAWGRID = !DRAWGRID;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
					waitForKeyrelease = true;
				} else {
					waitForKeyrelease = false;

				}
			}
		}

		/* to check if key is released */
		if (Keyboard.isKeyDown(Keyboard.KEY_G) == false) {
			waitForKeyrelease = true;
		} else {
			waitForKeyrelease = false;

		}

		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter

		LastMouseX = MouseX;
		LastMouseY = MouseY;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200, 800);
		glMatrixMode(GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

//		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
//		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();
//
		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

		glLight(GL_LIGHT0, GL_POSITION, lightPos); // specify the
													// position
													// of the
													// light
		// glEnable(GL_LIGHT0); // switch light #0 on // I've setup specific materials
		// so in real light it will look abit strange

		glLight(GL_LIGHT1, GL_POSITION, lightPos); // specify the
													// position
													// of the
													// light
		glEnable(GL_LIGHT1); // switch light #0 on
		glLight(GL_LIGHT1, GL_DIFFUSE, Utils.ConvertForGL(spot));

		glLight(GL_LIGHT2, GL_POSITION, lightPos3); // specify
//													// the
//													// position
//													// of the
//													// light
//		glEnable(GL_LIGHT2); // switch light #0 on
//		glLight(GL_LIGHT2, GL_DIFFUSE, Utils.ConvertForGL(grey));
//
		glLight(GL_LIGHT3, GL_POSITION, lightPos4); // specify
//													// the
//													// position
//													// of the
													// light
		glEnable(GL_LIGHT3); // switch light #0 on
		glLight(GL_LIGHT3, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glEnable(GL_LIGHTING); // switch lighting on
		glEnable(GL_DEPTH_TEST); // make sure depth buffer is switched
									// on
		glEnable(GL_NORMALIZE); // normalize normal vectors for safety
		glEnable(GL_COLOR_MATERIAL);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in texture

	}

	public void changeOrth() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
//		glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);


		glFrustum( - OrthoNumber, OrthoNumber, - OrthoNumber * 0.66f, OrthoNumber * 0.66f, 600, 500000);

		glMatrixMode(GL_MODELVIEW);

		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		glGetFloat(GL_MODELVIEW_MATRIX, CurrentMatrix);


		MyArcball.getMatrix(CurrentMatrix);

		glLoadMatrix(CurrentMatrix);


//        // 左右， 上下， 近远
//		gluLookAt(-50, 1300, -2500, -50, 0, 0.0f, 0.0f, 1f, 0.0f);

        // 控制视角变化
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
            lookNumberIn += 15;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            lookNumberLeft += 15;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            lookNumberIn -= 15;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            lookNumberLeft -= 15;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_V)){
            lookNumberUp += 15;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_C)){
            lookNumberUp -= 15;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)){ // change camera
			lookPointX -= 3;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)){ // change camera
			lookPointX += 3;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_Q) && playerAircraft){ // change camera
			lookPointZ += 12;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_TAB) && playerAircraft){ // change camera
			lookPointZ -= 12;
		}

        gluLookAt(-50 + lookNumberLeft + lookPointX, 1300 + lookNumberUp, (float) (-2500 +  lookNumberIn), -50 + lookNumberLeft + lookPointX, 0, 0.0f + lookPointZ, 0.0f, 1f, 0.0f);
    }

	/*
	 * You can edit this method to add in your own objects / remember to load in
	 * textures in the INIT method as they take time to load
	 * 
	 */
	public void renderGL() {
		changeOrth();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glColor3f(0.5f, 0.5f, 1.0f);

		Human human = new Human(texture_1, texture_13, texture_2, texture_3, texture_4, texture_5, texture_18);
		Human human1 = new Human(texture_16, texture_13, texture_2, texture_3, texture_4, texture_5, texture_17);
		Human human2 = new Human(texture_17, texture_13, texture_2, texture_3, texture_4, texture_5, texture_16);
		Human human3 = new Human(texture_18, texture_13, texture_2, texture_3, texture_4, texture_5, texture_15);

		Player player = new Player(texture_15, texture_13, texture_2, texture_3, texture_4, texture_5, texture_19);


		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		float timeHelper1 = delta;
		float timeHelper2 = (float) (delta - 1);
		float timeHelper3 = (float) (delta - 10.3);



		// code to aid in animation for the running human
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		float posn_y = (float) Math.sin(theta);
		float airTime = (float) Math.sin(theta / 1.9); // same as your circle code in your notes


		// world box
		glPushMatrix();
		{
//			glRotatef(90f, 0.0f, 0.0f, 0.0f);

			glTranslatef(0, 0, 0);


			// set the size of the texture cube
			glScalef(100000, 100000, 100000);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();
			texture_11.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// draw the thin cube with texture as grass
			texCube.drawTexCube(false);

			glDisable(GL_TEXTURE_2D);

			glPopMatrix();
		}

		// draw the ground
		drawGround();

		// draw the wall
		glPushMatrix();
		{
			TexCube MyGlobeCube = new TexCube();

			glTranslatef(0, 630.70f, 1278.7f);
			glRotatef(270f, 0.0f, 0.0f, 0.0f);

			// set the size of the texture cube
			glScalef(1875f, 0, 686.68f);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();
			texture_12.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// draw the thin cube with texture as grass
			MyGlobeCube.drawTexCube(false);

			glDisable(GL_TEXTURE_2D);

			glPopMatrix();
		}

		// building one
		glPushMatrix();
		{
			TexCube MyGlobeCube = new TexCube();
			glRotatef(90f, 0.0f, 0.0f, 0.0f);

			glTranslatef(-1342, 473f, -389.7f);


			// set the size of the texture cube
			glScalef(255.73f, 274.83f, 381.06f);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();
			texture_6.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// draw the thin cube with texture as grass
			MyGlobeCube.drawTexCube(false);

			glDisable(GL_TEXTURE_2D);

			// draw the shadow
			glTranslatef(-0.5f,1.5f,1);
			glRotatef(20, 0,0,1);
			glScalef(1, 1.5f, 0);
			shadow.drawShadow("Cube", 0, 0, 0);

			glPopMatrix();
		}

		// building two
		glPushMatrix();
		{
			TexCube MyGlobeCube = new TexCube();
			glRotatef(90f, 0.0f, 0.0f, 0.0f);

			glTranslatef(-604f, 473f, -389.7f);


			// set the size of the texture cube
			glScalef(255.73f, 274.83f, 381.06f);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();
			texture_6.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// draw the thin cube with texture as grass
			MyGlobeCube.drawTexCube(false);

			glDisable(GL_TEXTURE_2D);

			// draw the shadow
			glTranslatef(-0.5f,1.5f,1);
			glRotatef(20, 0,0,1);
			glScalef(1, 1.5f, 0);
			shadow.drawShadow("Cube", 0, 0, 0);

			glPopMatrix();
		}

		// building three
		glPushMatrix();
		{
			TexCube MyGlobeCube = new TexCube();
			glRotatef(90f, 0.0f, 0.0f, 0.0f);

			glTranslatef(141.7f, 473f, -389.7f);


			// set the size of the texture cube
			glScalef(255.73f, 274.83f, 381.06f);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();
			texture_6.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// draw the thin cube with texture as grass
			MyGlobeCube.drawTexCube(false);

			glDisable(GL_TEXTURE_2D);

			// draw the shadow
			glTranslatef(-0.5f,1.5f,1);
			glRotatef(20, 0,0,1);
			glScalef(1, 1.5f, 0);
			shadow.drawShadow("Cube", 0, 0, 0);

			glPopMatrix();
		}

		// rocket base
		glColor3f(grey[0], grey[1], grey[2]);
		glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
		glPushMatrix();
		{
			Stage stage = new Stage();
			glRotatef(90f, 0.0f, 0.0f, 0.0f);

			glTranslatef(1200.8f, 592.8f, -184.6f);

			// set the size of the cylinder
			glScalef(173.9f, 173.9f, 108.2f);

			// draw the thin cube with texture as grass
			stage.drawStage(3f, 2f, 1.5f, 32);

			glPopMatrix();
		}

		// rocket
		glColor3f(white[0], white[1], white[2]);
		glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
		glPushMatrix();
		{
			// Position and Angle
			glRotatef(270f, 0.0f, 0.0f, 0.0f);
			glTranslatef(1200.8f, -552.8f, 192.4f);

			// set the size of the texture cube
			glScalef(25f, 25f, 13f);
			// draw the thin cube with texture as grass


			if (!humanControlRocket) {
				// The rocket rose and fell
				if (timeHelper2 > 6.3 && timeHelper2 < (6.3 + 1.5)) {
					// rocket rise
					glTranslatef(0, 0, (float) (timeHelper2 - 6.3) * 100);

				} else if (timeHelper2 > 7.8 && timeHelper2 < (7.8 + 1.5)) {
					// rocket fell
					glTranslatef(0, 0, (float) (1.5 - (timeHelper2 - 7.8)) * 100);
				} else {
					glTranslatef(0, 0, 0);
				}
			}

			if (humanControlRocket && Math.sin(theta / 3) == 0){
				if (Math.sin(theta / 3) >= 0) {
					glTranslatef(0, 0, -(float) Math.sin(theta / 3) * 100);
				}
			} else if (humanControlRocket && Math.sin(theta / 3) <=0){
				glTranslatef(0, 0, -(float) Math.sin(theta / 3) * 100);
			}

			rocket.drawRocket(delta, true);

			glPopMatrix();
		}

		// line 1
		glColor3f(white[0], white[1], white[2]);
		glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
		glPushMatrix();
		{
			// 位置和角度
			glTranslatef(-20f, -5f, -678.8f);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(1769.26f, 68.88f, 8.99f);
			// draw the thin cube with texture as grass

			cube.drawCube();

			glPopMatrix();
		}

		// line 2
		glColor3f(orange[0], orange[1], orange[2]);
		glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
		glPushMatrix();
		{
			// 位置和角度
			glTranslatef(-28f, -5f, -159f);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(1769.26f, 68.88f, 8.99f);

			// draw the thin cube
			cube.drawCube();

			glPopMatrix();
		}

		// Indicator light
		glPushMatrix();
		{
			TexCylinder texCylinder = new TexCylinder();
			// 位置和角度
			glRotatef(90f, 0.0f, 0.0f, 0.0f);
			glTranslatef(563.24f, 93.7f, -589.22f);

			// set the size of the texture cube
			glScalef(65.77f, 65.77f, 86.52f);
			// draw the thin cube with texture as grass

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			// set the size of the human head
			Color.white.bind();
			texture_12.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			texCylinder.drawTexCylinder(1.2f, 6.6f, 32);
			glDisable(GL_TEXTURE_2D);

			// draw the shadow
			glTranslatef(-0.7f,5.7f,6.7f);
			glRotatef(7, 0,0,1);
			glScalef(1.1f, 6f, 0);
			shadow.drawShadow("Cube", 0, 0, 0);

			glPopMatrix();
		}

		// Indicator light sphere
		glPushMatrix();
		{
			// Indicator light changes color and emits light
			if (timeHelper2 < 4.7){
				glColor3f(red[0], red[1], red[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
			} else if (timeHelper2 > 6.3){
				glColor3f(green[0], green[1], green[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
			} else {
				if (posn_x < 0){
					glColor3f(yellow[0], yellow[1], yellow[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
					// use the light
					FloatBuffer lightPos1 = BufferUtils.createFloatBuffer(4);
					lightPos1.put(0).put(200).put(0).put(1).flip();
					glLight(GL_LIGHT0, GL_POSITION, lightPos1);
					glEnable(GL_LIGHT0); // open the light
				} else if (posn_x > 0){
					glColor3f(yellow[0], yellow[1], yellow[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
					// close the light
					glDisable(GL_LIGHT0);
				}
			}

			if (openLight){
				FloatBuffer lightPos1 = BufferUtils.createFloatBuffer(4);
				lightPos1.put(200).put(200).put(200).put(1).flip();
				glLight(GL_LIGHT0, GL_POSITION, lightPos1);
				glEnable(GL_LIGHT0); // open the light
			} else {
				glDisable(GL_LIGHT0);
			}

			// Position and Angle
			glRotatef(90f, 0.0f, 0.0f, 0.0f);
			glTranslatef(563.24f, 93.7f, -589.22f);

			// set the size
			glScalef(65.77f, 65.77f, 65.77f);

			// set the size of the human head
			sphere.drawSphere(1.8f, 32, 32);

			glPopMatrix();

		}

		// aircraft 1
		glPushMatrix();
		{
			// 位置和角度
			glTranslatef(-1648f, 604f, -668f);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(70f, 70f, 70f);
			// draw the thin cube with texture as grass

			while (timeHelper1 > 1.6){
				timeHelper1 -= 1.6;
			}

			if (timeHelper1 < 0.8){
				glTranslatef( timeHelper1  * 50, 0, 0);

			} else if (timeHelper1 > 0.8){
				glTranslatef((float) ( 40 -(timeHelper1 - 0.8 ) * 50), 0, 0);

			} else {
				glTranslatef(0, 0, 0);
			}

			aircraft.drawAircraft(delta, true, texture_1, texture_7);
			// draw the shadow
			glTranslatef(0,0,8.5f);
			glScalef(1, 1, 0);
			shadow.drawShadow("Sphere", 1.5f, 0, 0);

			glPopMatrix();
		}

		// aircraft2
		glPushMatrix();
		{
			// 位置和角度
			glTranslatef(-1175f, 604f, -668f);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(70f, 70f, 70f);
			// draw the thin cube with texture as grass

			while (timeHelper1 > 1.6){
				timeHelper1 -= 1.6;
			}

			if (timeHelper1 < 0.8){
				glTranslatef( timeHelper1  * 50, 0, 0);

			} else if (timeHelper1 > 0.8){
				glTranslatef((float) ( 40 -(timeHelper1 - 0.8 ) * 50), 0, 0);

			} else {
				glTranslatef(0, 0, 0);
			}
			aircraft.drawAircraft(delta, true, texture_1, texture_7);

			// draw the shadow
			glTranslatef(0,0,8.5f);
			glScalef(1, 1, 0);
			shadow.drawShadow("Sphere", 1.5f, 0, 0);

			glPopMatrix();
		}


		// aircraft 3
		glPushMatrix();
		{
			// 位置和角度
			glTranslatef(1570f, 604f, -140f);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(70f, 70f, 70f);
			// draw the thin cube with texture as grass

			while (timeHelper1 > 1.6){
				timeHelper1 -= 1.6;
			}

			if (timeHelper1 < 0.8){
				glTranslatef( -timeHelper1  * 50, 0, 0);

			} else if (timeHelper1 > 0.8){
				glTranslatef((float) ( -40 +(timeHelper1 - 0.8 ) * 50), 0, 0);

			} else {
				glTranslatef(0, 0, 0);
			}

			aircraft.drawAircraft(delta, true, texture_1, texture_7);
			// draw the shadow
			glTranslatef(0,0,8.5f);
			glScalef(1, 1, 0);
			shadow.drawShadow("Sphere", 1.5f, 0, 0);

			glPopMatrix();
		}

		// aircraft 4
		glPushMatrix();
		{
			// 位置和角度
			glTranslatef(1097f, 604f, -162f);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(70f, 70f, 70f);
			// draw the thin cube with texture as grass

			while (timeHelper1 > 1.6){
				timeHelper1 -= 1.6;
			}

			if (timeHelper1 < 0.8){
				glTranslatef( -timeHelper1  * 50, 0, 0);

			} else if (timeHelper1 > 0.8){
				glTranslatef((float) ( -40 +(timeHelper1 - 0.8 ) * 50), 0, 0);

			} else {
				glTranslatef(0, 0, 0);
			}

			aircraft.drawAircraft(delta, true, texture_1, texture_7);
			// draw the shadow
			glTranslatef(0,0,8.5f);
			glScalef(1, 1, 0);
			shadow.drawShadow("Sphere", 1.5f, 0, 0);

			glPopMatrix();
		}

		// aircraft 5 (land aircraft)
		glPushMatrix();
		{
			// 位置和角度
			glTranslatef(-875f, 110f, -1080f);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(70f, 70f, 70f);
			// draw the thin cube with texture as grass

			float shipZ = (float) (timeHelper2 - 1.45) * 15;
			float shipX = (float) (timeHelper2 - 2.27) * 15;
			float shipY = (float) (timeHelper2 - 2.74) * 15;


			if (timeHelper2 < 1.45){
				// land
				aircraft.drawAircraft(delta, true, texture_1, texture_7);
			} else if (timeHelper2 > 1.45 && timeHelper2 < 2.27 ){
				// 飞船变色, go up
				glTranslatef(0,0, -shipZ);
				aircraft.drawAircraft(delta, true, texture_1, texture_7);
				// draw the shadow
				glTranslatef(0, 0f, shipZ + 1.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1.5f,0,0);
			} else if (timeHelper2 > 2.27 && timeHelper2 < 2.74){
				// go left
				glTranslatef(-shipX,0,(float) -(15 * (2.27-1.45)));

				aircraft.drawAircraft(delta, true, texture_1, texture_7);

				// draw the shadow
				glTranslatef(0, 0f,(float) (15 * (2.27-1.45)+ 1.5f));
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1.5f,0,0);
			} else if (timeHelper2 > 2.74 && timeHelper2 < 4.35){
				// go in
				glTranslatef((float) -(15 * (2.74-2.27)), shipY,(float) -(15 * (2.27-1.45)));
				aircraft.drawAircraft(delta, true, texture_1, texture_7);
				if (timeHelper2 < 3.85) {
					// draw the shadow
					glTranslatef(0, 0, (float) (15 * (2.27 - 1.45)+ 1.5f));
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1.5f, 0, 0);
				} else {
					// draw the shadow
					glTranslatef(0, 0, 2.7f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1.5f, 0, 0);
				}
			} else {
				// land
				glTranslatef((float) -(15 * (2.74-2.27)), (float) (15 * (4.35-2.74)), (float) -(15 * (2.27-1.45)));
				aircraft.drawAircraft(delta, true, texture_1, texture_7);
				// draw the shadow
				glTranslatef(0, 0,2.7f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1.5f,0,0);
			}

			glPopMatrix();
		}

		// aircraft 6 (land aircraft)
		glPushMatrix();
		{
			float aX = -376f;
			float aY = 110f;
			float aZ = -1080f;
			// 位置和角度
			glTranslatef(aX, aY, aZ);
			glRotatef(90,0,0,0);
			// set the size of the texture cube
			glScalef(70f, 70f, 70f);

			glTranslatef((float) -(15 * (2.48-2.27)), (float) (15 * (4.38-2.74)), (float) -(15 * (2.27-1.45)));

			aircraft.drawAircraft(delta, true, texture_1, texture_7);
			// draw the shadow
			glTranslatef(0, 0,2.7f);
			glScalef(1, 1, 0);
			shadow.drawShadow("Sphere", 1.5f,0,0);
			glPopMatrix();
		}

		// player aircraft
		glPushMatrix();
//		{
		float aX = -376f;
		float aY = 110f;
		float aZ = -1080f;
		// 位置和角度
		glTranslatef(aX, aY, aZ);
		glRotatef(90,0,0,0);
		// set the size of the texture cube
		glScalef(70f, 70f, 70f);

		glPushMatrix();
		{
			// draw the shadow
			glTranslatef(0, 0,1.5f);
			glScalef(1, 1, 0);
			glTranslatef(aircraftX, aircraftY, 0);
			shadow.drawShadow("Sphere", 1.5f,0,0);
			glPopMatrix();
		}

		// aircraft move
		glTranslatef(aircraftX, aircraftY, aircraftZ);
//		System.out.println(aircraftX + "++++++" + aircraftY + "+++++++" + aircraftZ);

		if (aircraftX > 4.4f && aircraftX < 12f && aircraftZ < -9.81f && aircraftZ > -13.1f && aircraftY > 14.5 && aircraftY < 26){
			nearBuilding3 = true;
		} else if (aircraftX > -6.84f && aircraftX < 0.36f && aircraftZ < -9.81f && aircraftZ > -13.1f && aircraftY > 14.5 && aircraftY < 26){
			nearBuilding2 = true;
		}
		else if (aircraftX > -18.27f && aircraftX < -10.44f && aircraftZ < -9.81f && aircraftZ > -13.1f && aircraftY > 14.5 && aircraftY < 26){
			nearBuilding1 = true;
		} else {
			nearBuilding3 = false;
			nearBuilding2 = false;
			nearBuilding1 = false;
		}

		// collision detection
		if (aircraftX > -16.29f && aircraftX < 9.54f && aircraftY > 16.65 && aircraftZ > -10.53){
			airGo = false;
		} else {
			airGo = true;
		}


		if (playerAircraft){
			aircraft.drawAircraft(delta, true, texture_14, texture_7);
		} else {
			aircraft.drawAircraft(delta, true, texture_1, texture_7);
		}

		glPopMatrix();
//		}


		// draw human stage
		glPushMatrix();
		{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			glRotatef(90,0,0,0);
			// set the position/coordinate of the human shoulder
			glTranslatef(1224.0f, -1092.0f, -111.4f);
			// set the size of the human head
			Color.white.bind();
			texture_9.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			// set the size of the texture cube
			glScalef(422.90f, 212.19f, 83.37f);
			// draw the thin cube with texture as grass

			texCube.drawTexCube(false);

			glDisable(GL_TEXTURE_2D);

			glPopMatrix();
		}

		// draw human stage floor
		glPushMatrix();
		{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			glRotatef(90,0,0,0);
			// set the position/coordinate of the human shoulder
			glTranslatef(558f, -1092.0f, -111.4f);
			// set the size of the human head
			Color.white.bind();
			texture_9.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			// set the size of the texture cube
			glScalef(250.90f, 212.19f, 83.37f);

			// draw the thin cube with texture as grass
			texCube.drawTexCube(true);
			glDisable(GL_TEXTURE_2D);

			glPopMatrix();
		}

		// draw human stage bar
		glPushMatrix();
		{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			glRotatef(90,0,0,0);
			// set the position/coordinate of the human shoulder
			glTranslatef(1213.4f, -963.60f, -385.7f);
			// set the size of the human head
			Color.white.bind();
			texture_4.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			// set the size of the texture cylinder
			glScalef(38.77f, 38.77f, 109.52f);

			// draw texture cylinder
			texCylinder.drawTexCylinder(1.5f, 2f, 32);
			glDisable(GL_TEXTURE_2D);

			// draw the shadow
			glTranslatef(-2.7f,-2.4f,1.7f);
			glRotatef(-47, 0,0,1);
			glScalef(1.3f, 2.6f, 0);
			shadow.drawShadow("Cube", 0, 0, 0);
			glTranslatef(-0.1f,-1.4f,1.7f);
			glRotatef(90,0,0,1);
			shadow.drawShadow("Sphere", 1.2f, 0, 0);
			glPopMatrix();
		}

		// draw human stage bar sphere
		glPushMatrix();
		{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			glRotatef(90,0,0,0);
			// set the position/coordinate
			glTranslatef(1213.4f, -963.60f, -390.7f);
			// set the size of the human head
			Color.white.bind();

			float changeLight = (float) Math.cos(theta * 2); // same as your circle code in your notes

			// 切换状态
			if (timeHelper2 < 4.7 || timeHelper2 > 6.3){
				texture_10.bind();
			} else if (timeHelper2 >= 4.7 && timeHelper2 <= 6.3){
				if (changeLight > 0){
					texture_4.bind();
				} else {
					texture_10.bind();
				}
			}
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			// set the size of the texture cylinder
			glScalef(38.77f, 38.77f, 38.77f);

			// draw texture cylinder
			texSphere.drawTexSphere(2.2f, 32, 32, null);
			glDisable(GL_TEXTURE_2D);
			glPopMatrix();
		}

		// Control movement time and phase
		{
			if (timeHelper2 < 0.4) {
				justTalk = true;
			} else if (timeHelper2 > 0.4 && timeHelper2 < 2.5) {
				justTalk = false;
				stageTwo = true;
			}
		}

		// the player
		glPushMatrix();
//		{
			// on buildings
			if (nearBuilding3 && onBuilding3){
				glTranslatef(184, 890, 471);
			} else if (nearBuilding2 && onBuilding2){
				glTranslatef(-704, 890, 471);
			} else if (nearBuilding1 && onBuilding1){
				glTranslatef(-1436, 890, 471);
			} else {
				glTranslatef(-241.7f, 122.8f, -350f);
			}

			glScalef(90f, 90f, 90f);
			// whether on buildings
			if ( onBuilding3 && nearBuilding3 || onBuilding2 && nearBuilding2 || onBuilding1 && nearBuilding1 ) {

			} else {
				glTranslatef(playerX + aircraftX, playerY, playerZ + aircraftY);
			}

			// judge if the player get on
			if (!playerAircraft) {
				// draw the player shadow
				glPushMatrix();
				{
					glRotatef(90, 0, 0, 0);
					glTranslatef(-1f, 0f, 1.3f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 0.6f, 0, 0);
					glPopMatrix();
				}
			} else {

			}

			// control the rotated direction
			if (turnBack){
				glRotatef(0,0,0,0);
			} else if (turnFront){
				glRotatef(180,0,1,0);
			} else if (turnLeft){
				glRotatef(-90,0,1,0);
			} else if (turnRight){
				glRotatef(90,0,1,0);
			}

			float newPX = playerX * 90 - 241.7f;
			float newPY = playerY + 122.8f;
			float newPZ = playerZ * 90 - 350f;

			// Set this range to near aircraft
			float newAXR = aX - 234;
			float newAXL = aX + 234;
			float newAXU = aZ + 164;
			float newAXD = aZ - 164;

//		System.out.println(newPX +"++++++" + newPY +"+++++++++" + newPZ);

		// 碰撞检测
		if (newPX > -1734.7974 && newPX < 390.1 && newPZ > 152){
			playerGo = false;
		} else {
			playerGo = true;
		}

		// 禁止上斜坡
		if (newPX > 252.9 && newPZ > -1257.2 && newPZ < -908.9){
			playerGoOne = false;
		} else {
			playerGoOne = true;
		}

		// 判断接近灯
		if (newPX > 373.9 && newPX < 762.7 && newPZ > -90.8){
			nearLight = true;
		} else {
			nearLight = false;
		}


			// judge if near the aircraft or not
			nearAircraft = newPX > newAXR && newPX < newAXL && newPZ < newAXU && newPZ > newAXD;

			// get on or not
			if (nearAircraft && playerAboard){
				playerAircraft = true;
			} else if (!playerAboard){
				playerAircraft = false;
			}

			// change the player actions
			if (playerAircraft){

			} else if (playerRun){
				player.drawPlayer(delta, 3f, false, true, false, false, false, false, false);
			} else if (playerJump && playerCheers){
				if (Math.cos(theta * 6) > 0) {
					glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
				}
				player.drawPlayer(delta, 3f, false, false, false, false, true, true, false);
			} else if (playerTalk){
				player.drawPlayer(delta, 3f, true, false, false, false, false, false, false);
			} else if (playerDrink){
				player.drawPlayer(delta, 3f, false, false, false, true, false, false, false);
			} else if (playerCheers){
				player.drawPlayer(delta, 3f, false, false, false, false, true, false, false);
			} else if (playerWave){
				player.drawPlayer(delta, 3f, false, false, false, false, false, false, true);
			} else if (playerJump){
				if (Math.cos(theta * 6) > 0) {
					glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
				}
				player.drawPlayer(delta, 3f, false, false, false, false, false, true, false);
			} else if (playerFlag){
				player.drawPlayer(delta, 3f, false, false, true, false, false, false, false);
			} else if (buildingCheer || buildingCheerOne){
				glRotatef(90,0,-1,0);
				if (Math.cos(theta * 6) > 0) {
					glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
				}
				player.drawPlayer(delta, -1, false, false, false, false, false, true, false);
			} else if (startRocket){
				player.drawPlayer(delta, 3f, false, false, true, false, false, false, false);
			}

			// when no action is performed, keep still
			if (!playerRun && !playerTalk && !playerDrink && !playerCheers && !playerWave && !playerJump && !playerAircraft && !playerFlag && !buildingCheer && !buildingCheerOne && !startRocket){
				player.drawPlayer(delta, 3f, false, false, false, false, false, false, false);
			}

			glPopMatrix();
//		}

		// the man who move logically
		glPushMatrix();
		{
			glTranslatef(-1684f, 122.8f, -1000f);
			glRotatef(-45, 0,1,0);
			glScalef(90f, 90f, 90f);

			float runX = (float) ((timeHelper2 - 2.27) * 12);
			float runY = (float) ((timeHelper2 - 2.67) * 12);

			// 运动与阶段判定
			if (justTalk){
				// talk
				human.drawHuman(delta, 3f, true, false,false,false, false, false, false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (stageTwo && timeHelper2 < 2.27){
				//drink
				human.drawHuman(delta, 3f, false, false,false,true, false, false, false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (timeHelper2 > 2.27 && timeHelper2 < 2.67f) {
				//  started running up
				glTranslatef(runX, 0, runX);
				glRotatef(135, 0, -1, 0);
				human.drawHuman(delta, 5f, false, true, false, false, false, false, false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1, 0, 0);
			} else if (timeHelper2 > 2.67 && timeHelper2 < 3.55) {
				// Start running to the left
				glTranslatef(runY + (12 * 0.4f), 0,  (12 * 0.4f) - runY);
				glRotatef(45, 0, -1, 0);
				human.drawHuman(delta, 5f, false, true, false, false, false, false, false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1, 0, 0);
			} else if (timeHelper2 > 3.55 && timeHelper2 < 7.8){
				glTranslatef(  12 * 1.28f, 0,  -0.48f * 12);
				glRotatef(45, 0, -1, 0);
				human.drawHuman(delta, 5f, false, false, false, false, true, false, false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1, 0, 0);
			} else if (timeHelper2 > 7.8 && timeHelper2 < 9.3){
				// start jump
				glTranslatef(  12 * 1.28f, 0,  -0.48f * 12);
				glRotatef(45, 0, -1, 0);
				if (Math.cos(theta * 6) > 0) {
					glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
				}
				human.drawHuman(delta, 8f, false, false, false, false, true, true, false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1, 0, 0);
			} else if (timeHelper2 > 9.3){
				// 	Starting periodic motion
				glTranslatef(  12 * 1.28f, 0,  -0.48f * 12);
				glRotatef(45, 0, -1, 0);
				while (timeHelper3 > 1.4){
					timeHelper3 -= 1.4;
				}
				if (timeHelper3 < 0.7){
					glTranslatef( 0, 0, timeHelper3  * 10);
					glRotatef(180, 0, -1, 0);
				} else if (timeHelper3 > 0.7){
					glTranslatef(0, 0, (float) ( 7 -(timeHelper3 - 0.7 ) * 10));
					glRotatef(0, 0, -1, 0);
				} else {
					glTranslatef(0, 0, 0);
				}
				human.drawHuman(delta, 8f, false, true, false, false, false, false, false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1, 0, 0);
			}

			glPopMatrix();
		}

		// the man who use aircraft one
		glPushMatrix();
		{
			glTranslatef(-1464f, 122.8f, -1000f);
			glRotatef(45, 0,1,0);

			glScalef(90f, 90f, 90f);

			if (justTalk){
				// 开始talk
				glTranslatef(0, 0.0f, 0f);
				human1.drawHuman(delta, 8f,true, false, false,false,false, false,false);

				// draw the shadow
				glTranslatef(-1f, 1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (stageTwo){
				// run to aircraft
				float personX = (timeHelper2 - 0.4f) * 10;

				glRotatef(-135, 0,1,0);
				if (timeHelper2 < 0.83){
					// run
					glTranslatef(0 ,0, -personX);
					human1.drawHuman(delta, 8f,false, true,false,false, false, false,false);

					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				} else if (timeHelper2 > 0.83 && timeHelper2 < 1.45){
					// wait for 2
					glTranslatef(0 ,0,(float) -(10 * (0.83-0.4)));
					human1.drawHuman(delta, 8f,false, false, false,false,false, false,false);

					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				} else if (timeHelper2 > 4.35f && buildingCheerOne){
					glTranslatef(16f, 8.5f, -1.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(180,0,1,0);
					if (Math.cos(theta * 6) > 0) {
						glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
					}
					human1.drawHuman(delta, -1, false, false, false, false, false, true, false);
				}
				else if (timeHelper2 > 4.35f && timeHelper2 < 4.7f) {
					// arrive at the building
					glTranslatef(16f, 8.5f, -1.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);

					human1.drawHuman(delta, 8f, false, false, false, false, false, false, true);
					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				} else if (timeHelper2 > 4.7f && timeHelper2 < 7.8f){
					// start cheer
					glTranslatef(16f, 8.5f, -1.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					human1.drawHuman(delta, 8f, false, false, false, false, true, false, false);
					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				} else if (timeHelper2 > 7.8f && timeHelper2 < 9.3f){
					// start to jump
					glTranslatef(16f, 8.5f, -1.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					if (Math.cos(theta * 6) > 0) {
						glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
					}
					human1.drawHuman(delta, 8f, false, false, false, false, true, true, false);
				} else if (timeHelper2 > 9.3f && timeHelper2 < 9.8){
					// start cheer
					glTranslatef(16f, 8.5f, -1.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					human1.drawHuman(delta, 8f, false, false, false, false, true, false, false);
					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				} else if (timeHelper2 > 9.8f){
					// start drink
					glTranslatef(16f, 8.5f, -1.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					human1.drawHuman(delta, 8f, false, false, false, true, false, false, false);
					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				}
			}
			glPopMatrix();
		}

		// The guy who runs to start the rocket
		glPushMatrix();
		{
			glTranslatef(-1684f, 122.8f, -1200f);
			glRotatef(-135, 0,1,0);

			glScalef(90f, 90f, 90f);
			float runX = (float) ((timeHelper2 - 2.27) * 20);
			float runZ = (float) ((timeHelper2 - 3.05) * 20 * 0.332f * 1.414f) + 0.2f;
			float runXNew = (float) ((timeHelper2 - 3.26) * 20) ;


			if (justTalk){
				glTranslatef(0, 0.0f, 0f);
				human2.drawHuman(delta, 2f, false, false, false, true,false,false,false);
				// draw the shadow
				glTranslatef(1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (stageTwo && timeHelper2 < 2.27){
				// 继续喝饮料
				human2.drawHuman(delta, 5f, false, false, false, true,false,false,false);
				// draw the shadow
				glTranslatef(1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (timeHelper2 > 2.27 && timeHelper2 < 3.05){
				// 开始跑向平台
				glTranslatef(-runX,0, -runX);
				glRotatef(45, 0,1,0);
				human2.drawHuman(delta, 5f, false, true, false, false,false,false,false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (timeHelper2 > 3.05 && timeHelper2 < 3.26){
				// 上坡阶段
				glTranslatef(-runX, runZ, -runX);
				glRotatef(45, 0,1,0);
				glRotatef(20, 1,0,0);

				human2.drawHuman(delta, 5f, false, true, false, false,false,false,false);
				// draw the shadow
				glTranslatef(-1f, -1f, -1.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			}
			else if (timeHelper2 > 3.26 && timeHelper2 < 3.42){
				// 到达平台之后
				glTranslatef(-runXNew - 20, (float) ((3.26 - 3.05) * 20 * 0.332 * 1.414f + 0.2f), -runXNew -20);
				glRotatef(45, 0,1,0);

				human2.drawHuman(delta, 5f, false, true, false, false,false,false,false);
				// draw the shadow
				glTranslatef(-1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				glRotatef(45, 0,1,0);

				shadow.drawShadow("Sphere", 1,0,0);
			} else if ( timeHelper2 > 3.42 && timeHelper2 < 4.7){
				// 最后站在平台上
				glTranslatef((float) (-(3.42 - 2.27) * 20),  (float) ((3.26 - 3.05) * 20 * 0.332 * 1.414f + 0.2f), (float) (-(3.42 - 2.27) * 20));
				glRotatef(-45, 0,1,0);
				human2.drawHuman(delta, 5f, false, false, false, false,false,false,true);
				// draw the shadow
				glTranslatef(1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (timeHelper2 > 4.7 && timeHelper2 < 6.3) {
				// Turn on the rocket liftoff switch (wave flag)
				glTranslatef((float) (-(3.42 - 2.27) * 20),  (float) ((3.26 - 3.05) * 20 * 0.332 * 1.414f + 0.2f), (float) (-(3.42 - 2.27) * 20));
				glRotatef(-45, 0,1,0);
				human2.drawHuman(delta, 5f, false, false, true, false,false,false,false);
				// draw the shadow
				glTranslatef(1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			} else if (timeHelper2 > 6.3){
				glTranslatef((float) (-(3.42 - 2.27) * 20),  (float) ((3.26 - 3.05) * 20 * 0.332 * 1.414f + 0.2f), (float) (-(3.42 - 2.27) * 20));
				glRotatef(-45, 0,1,0);
				if (Math.cos(theta * 6) > 0) {
					glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
				}
				human2.drawHuman(delta, 5f, false, false, false, false,true,true,false);
				// draw the shadow
				glTranslatef(1f, -1f, -2.5f);
				glScalef(1, 1, 0);
				shadow.drawShadow("Sphere", 1,0,0);
			}
			glPopMatrix();
		}

		// the man who runs to aircraft 2
		glPushMatrix();
		{
			glTranslatef(-1464f, 122.8f, -1200f);
			glRotatef(135, 0,1,0);

			glScalef(90f, 90f, 90f);
				glRotatef(135, 0,1,0);

				if (buildingCheer){
					glTranslatef(18.5f, 8.5f, -9.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(180,0,1,0);
					if (Math.cos(theta * 6) > 0) {
						glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
					}
					human3.drawHuman(delta, -1, false, false, false,false, false, true, false);
				} else if ( timeHelper2 < 4.7f) {
					// arrive at the building
					glTranslatef(18.5f, 8.5f, -9.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					human3.drawHuman(delta, 8f, false, false, false,false, false, false, true);
					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				} else if (timeHelper2 > 4.7f && timeHelper2 < 7.8f){
					// start to jump
					glTranslatef(18.5f, 8.5f, -9.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					if (Math.cos(theta * 6) > 0) {
						glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
					}
					human3.drawHuman(delta, 8f, false, false, false,false, true, true, false);
				} else if (timeHelper2 > 7.8f && timeHelper2 < 9.3f){
					// start to cheer
					glTranslatef(18.5f, 8.5f, -9.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					human3.drawHuman(delta, 8f, false, false, false,false, true, false, false);
					// draw the shadow
					glTranslatef(-1f, -1f, -2.5f);
					glScalef(1, 1, 0);
					shadow.drawShadow("Sphere", 1,0,0);
				} else if (timeHelper2 > 9.3f){
					// start to jump
					glTranslatef(18.5f, 8.5f, -9.9f); // y (加 屏幕向内) z (+上) x (-右)
					glRotatef(90, 0, 1, 0);
					if (Math.cos(theta * 6) > 0) {
						glTranslatef(0, (float) (2 * Math.cos(theta * 6)), 0);
					}
					human3.drawHuman(delta, 8f, false, false, false,false, true, true, false);
				}

			glPopMatrix();
		}
	}





	public static void main(String[] argv)
	{
		MainWindow hello = new MainWindow();
		hello.start();
	}

	public void drawGround(){
		glPushMatrix();
		{
			TexCube MyGlobeCube = new TexCube();
			glRotatef(90f, 0.0f, 0.0f, 0.0f);

			glTranslatef(0, 0, 0);



			// set the size of the texture cube
			glScalef(1875f, 1275f, 0.01f);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			Color.white.bind();
			texture_5.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			// draw the thin cube with texture as grass
			MyGlobeCube.drawTexCube(false);

			glDisable(GL_TEXTURE_2D);

			glPopMatrix();
		}
	}
}
