package objects3D;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Aircraft {

    static float blue[] = {1.0f, 1.0f, 1.0f, 1.0f};
    static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};

    public void drawAircraft(float delta, boolean GoodAnimation, Texture texture, Texture texture_1) {
        glPushMatrix();
        {
            TexSphere texSphere = new TexSphere();
            glScalef(1.0f, 1.0f, 1.0f);
            glTranslatef(0, 0, 0f);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            Color.white.bind();
            texture.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            // draw the sphere with texture
            texSphere.drawTexSphere(1, 32, 32, null);
            glDisable(GL_TEXTURE_2D);
            glPopMatrix();
        }

        glPushMatrix();
        {
            TexSphere texSphere = new TexSphere();
            glTranslatef(0, 0, 0.23f);
            glScalef(2.0f, 2.0f, 0.5f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            Color.white.bind();
            texture_1.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            // draw the sphere with texture
            texSphere.drawTexSphere(1, 32, 32, null);
            glDisable(GL_TEXTURE_2D);
            glPopMatrix();
        }
    }
}
