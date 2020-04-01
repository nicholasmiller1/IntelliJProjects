import processing.core.PApplet;

public class Main extends PApplet {

    private int originalXPos = 0;
    private int originalYPos = 0;
    private float currentPosX = 0;
    private float currentPosY = 0;
    private float changeInX;
    private float changeInY;
    private static final int CONSTANT_RADIUS = 100;
    private static final int SCALE = 25;

    float rotx = PI/4;
    float roty = PI/4;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(500, 500, P3D);
    }

    public void setup() {
        background(255);
        camera(0 , 0, 100, 0, 0, 0, 0, -1, 0);
    }

    public void draw() {
        if (mousePressed) {
            changeInX = mouseX - originalXPos;
            changeInY = mouseY - originalYPos;

            changeInX += currentPosX;
            changeInY += currentPosY;

            if (changeInY > 120) {
                changeInY = 120;
                currentPosY = 120;
                originalYPos = mouseY;
            }
            else if (changeInY < -120) {
                changeInY = -120;
                currentPosY = -120;
                originalYPos = mouseY;
            }

            float ytheta = changeInY / 2;

            float orbitRadius = CONSTANT_RADIUS * cos(radians(ytheta));
            float posY = CONSTANT_RADIUS * sin(radians(ytheta));
            float posX = sin(radians(changeInX / 2)) * orbitRadius;
            float posZ = cos(radians(changeInX / 2)) * orbitRadius;

            camera(posX, posY, posZ, 0, 0, 0, 0, -1, 0);
        }
        else {
            originalYPos = mouseY;
            originalXPos = mouseX;
        }

        background(128);
        scale(8);

        // Front
        beginShape(QUADS);
        fill(255,0,0);
        vertex(-1, -1,  3);
        vertex( 1, -1,  3);
        vertex( 1,  1,  3);
        vertex(-1,  1,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(-3, -1,  3);
        vertex( -1, -1,  3);
        vertex( -1,  1,  3);
        vertex(-3,  1,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(1, -1,  3);
        vertex( 3, -1,  3);
        vertex( 3,  1,  3);
        vertex(1,  1,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(-1, 1,  3);
        vertex( 1, 1,  3);
        vertex( 1,  3,  3);
        vertex(-1,  3,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(-1, -3,  3);
        vertex( 1, -3,  3);
        vertex( 1,  -1,  3);
        vertex(-1,  -1,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(-3, 1,  3);
        vertex( -1, 1,  3);
        vertex( -1,  3,  3);
        vertex(-3,  3,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(-3, -3,  3);
        vertex( -1, -3,  3);
        vertex( -1,  -1,  3);
        vertex(-3,  -1,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(1, 1,  3);
        vertex( 3, 1,  3);
        vertex( 3,  3,  3);
        vertex(1,  3,  3);
        endShape();

        beginShape(QUADS);
        fill(255,0,0);
        vertex(1, -3,  3);
        vertex( 3, -3,  3);
        vertex( 3,  -1,  3);
        vertex(1,  -1,  3);
        endShape();

        // ---------------------------------------------------

        // Back
        beginShape(QUADS);
        fill(255,165,0);
        vertex(-1, -1,  -3);
        vertex( 1, -1,  -3);
        vertex( 1,  1,  -3);
        vertex(-1,  1,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(-3, -1,  -3);
        vertex( -1, -1,  -3);
        vertex( -1,  1,  -3);
        vertex(-3,  1,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(1, -1,  -3);
        vertex( 3, -1,  -3);
        vertex( 3,  1,  -3);
        vertex(1,  1,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(-1, 1,  -3);
        vertex( 1, 1,  -3);
        vertex( 1,  3,  -3);
        vertex(-1,  3,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(-1, -3,  -3);
        vertex( 1, -3,  -3);
        vertex( 1,  -1,  -3);
        vertex(-1,  -1,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(-3, 1,  -3);
        vertex( -1, 1,  -3);
        vertex( -1,  3,  -3);
        vertex(-3,  3,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(-3, -3,  -3);
        vertex( -1, -3,  -3);
        vertex( -1,  -1,  -3);
        vertex(-3,  -1,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(1, 1,  -3);
        vertex( 3, 1,  -3);
        vertex( 3,  3,  -3);
        vertex(1,  3,  -3);
        endShape();

        beginShape(QUADS);
        fill(255,165,0);
        vertex(1, -3,  -3);
        vertex( 3, -3,  -3);
        vertex( 3,  -1,  -3);
        vertex(1,  -1,  -3);
        endShape();

        // ---------------------------------------------------

        // Top
        beginShape(QUADS);
        fill(255,255,255);
        vertex(-1,  3,  1);
        vertex( 1,  3,  1);
        vertex( 1,  3, -1);
        vertex(-1,  3, -1);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(-3,  3,  1);
        vertex( -1,  3,  1);
        vertex( -1,  3, -1);
        vertex(-3,  3, -1);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(1,  3,  1);
        vertex( 3,  3,  1);
        vertex( 3,  3, -1);
        vertex(1,  3, -1);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(-1,  3,  -1);
        vertex( 1,  3,  -1);
        vertex( 1,  3, -3);
        vertex(-1,  3, -3);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(-1,  3,  3);
        vertex( 1,  3,  3);
        vertex( 1,  3, 1);
        vertex(-1,  3, 1);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(-3,  3,  -1);
        vertex( -1,  3,  -1);
        vertex( -1,  3, -3);
        vertex(-3,  3, -3);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(-3,  3,  3);
        vertex( -1,  3,  3);
        vertex( -1,  3, 1);
        vertex(-3,  3, 1);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(1,  3,  -1);
        vertex( 3,  3,  -1);
        vertex( 3,  3, -3);
        vertex(1,  3, -3);
        endShape();

        beginShape(QUADS);
        fill(255,255,255);
        vertex(1,  3,  3);
        vertex( 3,  3,  3);
        vertex( 3,  3, 1);
        vertex(1,  3, 1);
        endShape();

        // ---------------------------------------------------

        // Bottom
        beginShape(QUADS);
        fill(255,255,0);
        vertex(-1,  -3,  1);
        vertex( 1,  -3,  1);
        vertex( 1,  -3, -1);
        vertex(-1,  -3, -1);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(-3,  -3,  1);
        vertex( -1,  -3,  1);
        vertex( -1,  -3, -1);
        vertex(-3,  -3, -1);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(1,  -3,  1);
        vertex( 3,  -3,  1);
        vertex( 3,  -3, -1);
        vertex(1,  -3, -1);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(-1,  -3,  -1);
        vertex( 1,  -3,  -1);
        vertex( 1,  -3, -3);
        vertex(-1,  -3, -3);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(-1,  -3,  3);
        vertex( 1,  -3,  3);
        vertex( 1,  -3, 1);
        vertex(-1,  -3, 1);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(-3,  -3,  -1);
        vertex( -1,  -3,  -1);
        vertex( -1,  -3, -3);
        vertex(-3,  -3, -3);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(-3,  -3,  3);
        vertex( -1,  -3,  3);
        vertex( -1,  -3, 1);
        vertex(-3,  -3, 1);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(1,  -3,  -1);
        vertex( 3,  -3,  -1);
        vertex( 3,  -3, -3);
        vertex(1,  -3, -3);
        endShape();

        beginShape(QUADS);
        fill(255,255,0);
        vertex(1,  -3,  3);
        vertex( 3,  -3,  3);
        vertex( 3,  -3, 1);
        vertex(1,  -3, 1);
        endShape();

        // ---------------------------------------------------

        // Left
        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, -1,  1);
        vertex( 3, -1, -1);
        vertex( 3,  1, -1);
        vertex( 3,  1,  1);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, -3,  1);
        vertex( 3, -3, -1);
        vertex( 3,  -1, -1);
        vertex( 3,  -1,  1);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, 1,  1);
        vertex( 3, 1, -1);
        vertex( 3,  3, -1);
        vertex( 3,  3,  1);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, -1,  -1);
        vertex( 3, -1, -3);
        vertex( 3,  1, -3);
        vertex( 3,  1,  -1);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, -1,  3);
        vertex( 3, -1, 1);
        vertex( 3,  1, 1);
        vertex( 3,  1,  3);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, -3,  -1);
        vertex( 3, -3, -3);
        vertex( 3,  -1, -3);
        vertex( 3,  -1,  -1);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, -3,  3);
        vertex( 3, -3, 1);
        vertex( 3,  -1, 1);
        vertex( 3,  -1,  3);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, 1,  -1);
        vertex( 3, 1, -3);
        vertex( 3,  3, -3);
        vertex( 3,  3,  -1);
        endShape();

        beginShape(QUADS);
        fill(0,128,0);
        vertex( 3, 1,  3);
        vertex( 3, 1, 1);
        vertex( 3,  3, 1);
        vertex( 3,  3,  3);
        endShape();

        // ---------------------------------------------------

        // Right
        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, -1,  1);
        vertex( -3, -1, -1);
        vertex( -3,  1, -1);
        vertex( -3,  1,  1);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, -3,  1);
        vertex( -3, -3, -1);
        vertex( -3,  -1, -1);
        vertex( -3,  -1,  1);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, 1,  1);
        vertex( -3, 1, -1);
        vertex( -3,  3, -1);
        vertex( -3,  3,  1);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, -1,  -1);
        vertex( -3, -1, -3);
        vertex( -3,  1, -3);
        vertex( -3,  1,  -1);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, -1,  3);
        vertex( -3, -1, 1);
        vertex( -3,  1, 1);
        vertex( -3,  1,  3);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, -3,  -1);
        vertex( -3, -3, -3);
        vertex( -3,  -1, -3);
        vertex( -3,  -1,  -1);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, -3,  3);
        vertex( -3, -3, 1);
        vertex( -3,  -1, 1);
        vertex( -3,  -1,  3);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, 1,  -1);
        vertex( -3, 1, -3);
        vertex( -3,  3, -3);
        vertex( -3,  3,  -1);
        endShape();

        beginShape(QUADS);
        fill(0,0,255);
        vertex( -3, 1,  3);
        vertex( -3, 1, 1);
        vertex( -3,  3, 1);
        vertex( -3,  3,  3);
        endShape();
    }

    public void mouseReleased() {
        currentPosX = changeInX;
        currentPosY = changeInY;
    }
}
