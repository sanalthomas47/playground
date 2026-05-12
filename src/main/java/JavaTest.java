

/**
 * Simulation scratch class that models a car moving horizontally across a 2D screen buffer.
 * The screen is represented as a 2D integer array (pixel color values).
 * Runs an infinite loop — terminate the process to stop.
 */
public class JavaTest {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final int CAR_WIDTH = 50;
    private static final int CAR_HEIGHT = 80;
    private static final int SPEED = 3;

    public static void main(String[] args) {
        // Create a drawing surface (using an array for simplicity - not ideal but native Java)
        int[][] screen = new int[WINDOW_HEIGHT][WINDOW_WIDTH];

        // Initialize the screen with blue sky
        for (int i = 0; i < WINDOW_HEIGHT; i++) {
            for (int j = 0; j < WINDOW_WIDTH; j++) {
                screen[i][j] = 127; // Blue color (SKY_COLOR_RED)
            }
        }

        // Car representation (simplified - just a rectangle)
        int carX = WINDOW_WIDTH / 2 - CAR_WIDTH / 2;
        int carY = WINDOW_HEIGHT - CAR_HEIGHT;

        // Road representation
        for (int i = 0; i < WINDOW_HEIGHT; i++) {
            screen[i][0] = 192; // Grey color (ROAD_COLOR_GRAY)
        }


        // Simulation loop
        while (true) {
            // Clear the screen (redraw everything)
            for (int i = 0; i < WINDOW_HEIGHT; i++) {
                for (int j = 0; j < WINDOW_WIDTH; j++) {
                    screen[i][j] = 127; // Reset to blue sky
                }
            }

            // Draw the car
            drawCar(carX, carY, screen);

            // Draw the road
            drawRoad(screen);


            // Update car position (simple movement)
            carX += SPEED;
            if (carX > WINDOW_WIDTH) {
                carX = -CAR_WIDTH;
            }

            try {
                Thread.sleep(50); // Control the simulation speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void drawCar(int x, int y, int[][] screen) {
        for (int i = 0; i < CAR_WIDTH; i++) {
            for (int j = 0; j < CAR_HEIGHT; j++) {
                if (x + i >= 0 && x + i < WINDOW_WIDTH && y + j >= 0 && y + j < WINDOW_HEIGHT) {
                    screen[y + j][x + i] = 255; // Red color
                }
            }
        }
    }

    private static void drawRoad(int[][] screen) {
        for (int i = 0; i < WINDOW_HEIGHT; i++) {
            screen[i][0] = 192;  // Grey road
        }
    }
}


