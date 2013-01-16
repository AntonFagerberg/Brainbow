/**
 * Copyright (c) 2013 Anton Fagerberg.
 * anton@antonfagerberg.com | www.antonfagerberg.com
 */
package util

import java.io.FileInputStream
import de.matthiasmann.twl.utils.PNGDecoder
import java.nio.ByteBuffer

class PNG(filename: String) {
  private val inputStream = new FileInputStream(filename)
  private val decoder = new PNGDecoder(inputStream)
  private val imageBuffer = ByteBuffer.allocateDirect(4 * decoder.getWidth * decoder.getHeight)

  decoder.decode(buffer, decoder.getWidth * 4, PNGDecoder.Format.RGBA)
  imageBuffer.flip()
  inputStream.close()

  def width = decoder.getWidth
  def height = decoder.getHeight
  def buffer = imageBuffer
}
