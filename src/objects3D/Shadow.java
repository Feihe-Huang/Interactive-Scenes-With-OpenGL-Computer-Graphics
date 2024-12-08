package objects3D;
import GraphicsObjects.Utils;

import static org.lwjgl.opengl.GL11.*;

public class Shadow {

    public Shadow(){

    }

    Sphere sphere = new Sphere();
    Cube cube = new Cube();
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };

    public void drawShadow(String shape, float radius, float width, float height){
       if (shape.equals("Sphere")){

           glColor3f(black[0], black[1], black[2]);
           glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
           glPushMatrix();
           {
               sphere.drawSphere(radius, 32, 32);

           }
               glPopMatrix();
       } else if (shape.equals("Cube")){
           glColor3f(black[0], black[1], black[2]);
           glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
           glPushMatrix();
           {
               cube.drawCube();
           }
           glPopMatrix();
       }

       }
    }
