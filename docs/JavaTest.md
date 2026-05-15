================================================================
CLASS: JavaTest
FILE: src/main/java/JavaTest.java
================================================================

OVERVIEW
--------
JavaTest is a scratch simulation class that models a car moving
horizontally across a 2D screen. The "screen" is represented as a
2D integer array where each cell holds a color value. The class
runs an infinite animation loop — it must be killed externally to stop.

This is NOT a production-ready animation — it uses a raw int[][] as
a pixel buffer instead of a GUI framework (e.g. Swing/JavaFX).


CONSTANTS
----------
  WINDOW_WIDTH  = 600   (columns in the screen buffer)
  WINDOW_HEIGHT = 400   (rows    in the screen buffer)
  CAR_WIDTH     = 50    (width  of the car rectangle)
  CAR_HEIGHT    = 80    (height of the car rectangle)
  SPEED         = 3     (pixels the car moves right per frame)


SCREEN BUFFER
--------------
  int[][] screen = new int[WINDOW_HEIGHT][WINDOW_WIDTH]

  Cell values represent colors:
    127  → blue sky background
    192  → grey road
    255  → red car


HOW THE SIMULATION WORKS
--------------------------
  Initialization:
    1. Fill entire screen with 127 (blue sky).
    2. Set the leftmost column to 192 (grey road strip).
    3. Place the car at horizontal center, bottom of screen:
         carX = WINDOW_WIDTH/2 - CAR_WIDTH/2  = 275
         carY = WINDOW_HEIGHT - CAR_HEIGHT     = 320

  Each frame in the infinite loop:
    1. Clear screen (reset all cells to 127 — sky).
    2. drawCar(carX, carY, screen)  — fills a CAR_WIDTH x CAR_HEIGHT
       rectangle starting at (carX, carY) with value 255 (red).
    3. drawRoad(screen)             — redraws the grey left column.
    4. Move car: carX += 3
    5. Wrap-around: if carX > WINDOW_WIDTH, reset carX = -CAR_WIDTH
       (car re-enters from the left side).
    6. Sleep 50ms to control animation speed (~20 fps).


drawCar LOGIC
--------------
  for i in [0, CAR_WIDTH):
    for j in [0, CAR_HEIGHT):
      if pixel (x+i, y+j) is within screen bounds:
        screen[y+j][x+i] = 255

  The bounds check handles the wrap-around period when
  carX < 0 (car is partially off the left edge) or when
  carX + CAR_WIDTH > WINDOW_WIDTH (partially off the right edge).


drawRoad LOGIC
---------------
  for i in [0, WINDOW_HEIGHT):
    screen[i][0] = 192

  Redraws the single grey column on the left edge of the screen.
  (Note: a road would typically span several columns — this is minimal.)


FRAME ANIMATION EXAMPLE
------------------------
  Frame 1:  carX=275  → car drawn at columns 275-324
  Frame 2:  carX=278  → car drawn at columns 278-327
  ...
  Frame N:  carX=600  → carX resets to -50 (off-screen left)
  Frame N+1:carX=-47  → car starts entering from left; bounds check
                         ensures only visible pixels are drawn.


NOTE ON LIMITATIONS
---------------------
  - The screen buffer is never rendered to a display — this is a
    pure in-memory simulation. No visual output is produced.
  - An infinite loop with Thread.sleep(50) means the JVM never exits
    normally; must be terminated with Ctrl+C or process kill.
  - The road is a single column, not a realistic road surface.
