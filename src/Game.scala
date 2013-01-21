/*
 * Copyright (c) 2013 Anton Fagerberg.
 * www.antonfagerberg.com | anton [a-t] antonfagerberg [d-o-t] com.
 */
import org.lwjgl.opengl.{GL11, DisplayMode, Display}

abstract class Game(width: Int, height: Int, title: String = "Brainbow! Braaaiinnnbows...") {
  private var nextRender = System.currentTimeMillis()

  Display.setTitle(title)
  Display.setDisplayMode(new DisplayMode(width, height))
  Display.create()

  GL11.glMatrixMode(GL11.GL_PROJECTION)
  GL11.glLoadIdentity()
  GL11.glOrtho(-1, 1, -1, 1, -1, 1)
  GL11.glMatrixMode(GL11.GL_MODELVIEW)
  GL11.glLoadIdentity()
  GL11.glDisable(GL11.GL_DEPTH_TEST)
  GL11.glEnable(GL11.GL_TEXTURE_2D)
  GL11.glEnable(GL11.GL_BLEND)
  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

  /** Main loop of the game engine.
    * (Do not override).
    */
  def run() {
    while (!Display.isCloseRequested) {
      if (nextRender <= System.currentTimeMillis()) {
        nextRender = System.currentTimeMillis() + 10
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT)
        update()
        Display.update()
      } else {
        Thread.sleep(nextRender - System.currentTimeMillis())
      }
    }

    Display.destroy()
  }

  def update()
}
