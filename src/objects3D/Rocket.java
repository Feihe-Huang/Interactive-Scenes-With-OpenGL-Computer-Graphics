package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

import static objects3D.Human.white;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_AMBIENT_AND_DIFFUSE;

public class Rocket {
    static float blue[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };

    public Rocket() {
    }

    public void drawRocket(float delta, boolean GoodAnimation)
    {
//        glPushMatrix();
//        {
//            //center
//            glColor3f(blue[0], blue[1], blue[2]);
//            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
//            glPushMatrix();
//            {
//                Sphere centerSub = new Sphere();
//                centerSub.drawSphere(0.1f, 32, 32);

                glPushMatrix();
                {
                    RocketTop myRocket = new RocketTop();
                    glScalef(1.0f, 1.0f, 1.0f);
                    myRocket.drawRocketTop(3.5f, 2f, 15f, 70f, 7f, 32);

                    glPushMatrix();
                    {
                        glColor3f(brown[0], brown[1], brown[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                        RocketTop mySubRocket1 = new RocketTop();
                        glTranslatef(-5.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket1.drawRocketTop(2.3f, 2f, 15f, 15f, 7f, 32);
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        glColor3f(brown[0], brown[1], brown[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                        RocketTop mySubRocket2 = new RocketTop();
                        glTranslatef(5.0f, 0.0f, 0.0f);
                        glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket2.drawRocketTop(2.3f, 2f, 15f, 15f, 7f, 32);
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        glColor3f(brown[0], brown[1], brown[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                        RocketTop mySubRocket3 = new RocketTop();
                        glTranslatef(0.0f, 5.0f, 0.0f);
                        glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket3.drawRocketTop(2.3f, 2f, 15f, 15f, 7f, 32);
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        glColor3f(brown[0], brown[1], brown[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                        RocketTop mySubRocket4 = new RocketTop();
                        glTranslatef(0.0f, -5.0f, 0.0f);
                        glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket4.drawRocketTop(2.3f, 2f, 15f, 15f, 7f, 32);
                    }
                    glPopMatrix();

                }
                glPopMatrix();

        // draw the fire
        glPushMatrix();
        {
            glColor3f(red[0], red[1], red[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
            glScalef(5.4f, 5.4f, 8.0f);
            glRotatef(0,0,0,0);
            glTranslatef(0, 0, -2.3f);

            Stage stage = new Stage();
            stage.drawStage(1, 0.1f, 2, 32);
        }
        glPopMatrix();

            }


    public static class RocketTop {
            public RocketTop(){

            }
            public void drawRocketTop(float radius1, float radius2, float height1, float height2, float height3, int nSegments){

                    GL11.glBegin(GL11.GL_TRIANGLES);

                    for (float i = 0.0f; i < nSegments; i ++) { // a loop around circumference of a tube
                            float angle = (float) (Math.PI * i * 2.0 / nSegments);
                            float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

                            //compute sin & cosine
                            float x1 = (float) (radius1 * Math.sin(angle)), y1 = (float) (radius1 * Math.cos(angle));
                            float x2 = (float) (radius1 * Math.sin(nextAngle)), y2 = (float) (radius1 * Math.cos(nextAngle));

                            //底面半径
                            float x3 = (float) (radius2 * Math.sin(angle)), y3 = (float) (radius2 * Math.cos(angle));
                            float x4 = (float) (radius2 * Math.sin(nextAngle)), y4 = (float) (radius2 * Math.cos(nextAngle));

                            //锥形盖
                            GL11.glNormal3f(x1, y1, height2+height3);
                            GL11.glVertex3f(x1, y1, height2+height3);

                            GL11.glNormal3f(x1, y1, height2+height3);
                            GL11.glVertex3f(x2, y2, height2+height3);

                            GL11.glNormal3f(0, 0, height1+height2+height3);
                            GL11.glVertex3f(0, 0, height1+height2+height3);

                            //柱形身
                            // draw top triangle
                            GL11.glNormal3f(x1, y1, height3); GL11.glVertex3f(x1, y1, height3);
                            GL11.glNormal3f(x2, y2, height3); GL11.glVertex3f(x2, y2, height2+height3);
                            GL11.glNormal3f(x1, y1, height3); GL11.glVertex3f(x1, y1, height2+height3);

                            // draw bottom triangle
                            GL11.glNormal3f(x1, y1, height3); GL11.glVertex3f(x1, y1, height3);
                            GL11.glNormal3f(x2, y2, height3); GL11.glVertex3f(x2, y2, height3);
                            GL11.glNormal3f(x2, y2, height3); GL11.glVertex3f(x2, y2, height2+height3);

                            //台形底
                            // draw top triangle
                            GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, height3);
                            GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height3);
                            GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x3, y3, 0.0f);

                            // draw bottom triangle
                            GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x3, y3, 0.0f);
                            GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x4, y4, 0.0f);
                            GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height3);

                            GL11.glNormal3f(x1, y1, 0.0f);GL11.glVertex3f(x3, y3, 0.0f);
                            GL11.glNormal3f(x1, y1, 0.0f);GL11.glVertex3f(x4, y4, 0.0f);
                            GL11.glNormal3f(0, 0, 0.0f);GL11.glVertex3f(0.0f, 0.0f, 0.0f);
                    }
                    GL11.glEnd();

            }
    }
}
