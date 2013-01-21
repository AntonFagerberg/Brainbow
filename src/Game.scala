/**
 * Copyright (c) 2013 Anton Fagerberg.
 * anton@antonfagerberg.com | www.antonfagerberg.com
 */
import org.lwjgl.opengl.{GL11, DisplayMode, Display}

abstract class Game(width: Int, height: Int, title: String = "Brainbow! Braaaiinnnbows...", left: Int = -1, right: Int = 1, bottom: Int = -1, top: Int = 1) {
  private var nextRender = System.currentTimeMillis()

  Display.setTitle(title)
  Display.setDisplayMode(new DisplayMode(width, height))
  Display.create()

  GL11.glMatrixMode(GL11.GL_PROJECTION)
  GL11.glLoadIdentity()
  GL11.glOrtho(left, right, bottom, top, -1, 1)
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
        render()
        Display.update()
      } else {
        Thread.sleep(nextRender - System.currentTimeMillis())
      }
    }

    Display.destroy()
  }

  def update()
  def render()
}
