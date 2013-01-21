/**
 * Copyright (c) 2013 Anton Fagerberg.
 * anton@antonfagerberg.com | www.antonfagerberg.com
 */

import org.lwjgl.input.Keyboard
import render.Image

object Main extends Game(1440, 900) {
  val image = new Image("gfx/alien.png", 0.1f, 0.2f)
  run()

  var positionX = 0f
  var positionY = 0f

  def update() {
    if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) positionX -= 0.01f
    else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) positionX += 0.01f

    if (Keyboard.isKeyDown(Keyboard.KEY_UP)) positionY += 0.01f
    else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) positionY -= 0.01f

    image.renderCentered(positionX, positionY)
  }

  def main(args: Array[String]) {}
}