package objects3D;

import org.lwjgl.opengl.GL11;

public class Stage {

    public Stage() {
    }

    // 画出圆台
    public void drawStage(float radius1, float radius2, float height3, int nSegments) {

        GL11.glBegin(GL11.GL_TRIANGLES);

        for (float i = 0.0f; i < nSegments; i++) { // a loop around circumference of a tube
            float angle = (float) (Math.PI * i * 2.0 / nSegments);
            float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

            //compute sin & cosine
            float x1 = (float) (radius1 * Math.sin(angle)), y1 = (float) (radius1 * Math.cos(angle));
            float x2 = (float) (radius1 * Math.sin(nextAngle)), y2 = (float) (radius1 * Math.cos(nextAngle));

            //底面半径
            float x3 = (float) (radius2 * Math.sin(angle)), y3 = (float) (radius2 * Math.cos(angle));
            float x4 = (float) (radius2 * Math.sin(nextAngle)), y4 = (float) (radius2 * Math.cos(nextAngle));

            //台形
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

