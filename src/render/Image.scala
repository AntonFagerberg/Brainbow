/**
 * Copyright (c) 2013 Anton Fagerberg.
 * anton@antonfagerberg.com | www.antonfagerberg.com
 */
package render

import org.lwjgl.opengl.GL11
import util.PNG

// Filter method: GL11.GL_LINEAR

class Image(filename: String, width: Float, height: Float, scaleFilter: Int = GL11.GL_NEAREST) extends Renderable {
  private val image = new PNG(filename)
  private val textureId = GL11.glGenTextures()
  private val halfHeight = height * 0.5f
  private val halfWidth = width * 0.5f

  GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId)
  GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, scaleFilter)
  GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, scaleFilter)
  GL11.glTexImage2D(
    GL11.GL_TEXTURE_2D,
    0,
    GL11.GL_RGBA,
    image.width,
    image.height,
    0,
    GL11.GL_RGBA,
    GL11.GL_UNSIGNED_BYTE,
    image.buffer
  )

  /** Render the image centered at the x- & y-coordinate.
    *
    * @param x X-coordinate.
    * @param y Y-coordinate.
    */
  def renderCentered(x: Float, y: Float) {
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId)
    GL11.glBegin(GL11.GL_QUADS)
    GL11.glColor4ub(255 toByte, 255 toByte, 255 toByte, 255 toByte)

    GL11.glTexCoord2f(0, 0)
    GL11.glVertex2f(x - halfWidth, y + halfHeight)

    GL11.glTexCoord2f(1, 0)
    GL11.glVertex2f(x + halfWidth, y + halfHeight)

    GL11.glTexCoord2f(1, 1)
    GL11.glVertex2f(x + halfWidth, y - halfHeight)

    GL11.glTexCoord2f(0, 1)
    GL11.glVertex2f(x - halfWidth, y - halfHeight)

    GL11.glEnd()
  }
}
