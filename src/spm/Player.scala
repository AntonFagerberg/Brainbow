/**
 * Copyright (c) 2013 Anton Fagerberg.
 * anton@antonfagerberg.com | www.antonfagerberg.com
 */
package spm

import render.{Image, Updatable, Renderable}
import org.lwjgl.input.{Controllers, Keyboard}

class Player extends Renderable with Updatable {
  Controllers.create()
  private val sprite = new Image("gfx/megaman.png", 3f, 3f)
  private val presetJumpHeight = 5.5f
  private val presetJumpSpeed = 0.3f
  private val presetSpeedX = 0.2f
  private val presetIgnoreAxisXAbs = 0.2f
  private val presetJumpHover = 15f
  private val gamePad =
    if (Controllers.getControllerCount > 0) Some(Controllers.getController(0))
    else None

  private var jumpStage = 0
  private var startJumpHeight = -1f
  private var positionX = 16f
  private var positionY = 5f
  private var jumpHover = presetJumpHover

  def update() {
    if (gamePad.isDefined && ((gamePad.get.getAxisValue(0) > presetIgnoreAxisXAbs || gamePad.get.getAxisValue(0) < -presetIgnoreAxisXAbs))) {
      positionX += presetSpeedX * gamePad.get.getAxisValue(0)
    } else {
      if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) positionX -= presetSpeedX
      else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) positionX += presetSpeedX
    }

    if (((gamePad.isDefined && gamePad.get.isButtonPressed(11)) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) && (jumpStage == 0 || jumpStage == 1) && positionY < startJumpHeight + presetJumpHeight) {
      jumpStage = 1

      if (positionY < startJumpHeight + presetJumpHeight)
        positionY += presetJumpSpeed

      if (positionY > startJumpHeight + presetJumpHeight)
        positionY = startJumpHeight + presetJumpHeight

      jumpHover = presetJumpHover * (positionY - startJumpHeight) / presetJumpHeight
    } else {
      startJumpHeight = positionY

      if (jumpStage == 1)
        jumpStage = 2

      if (jumpStage == 2 && jumpHover > 0)
        jumpHover -= 1

      if (jumpHover <= 0) {
        if (positionY > 5.5f) positionY -= 0.2f
        else if (positionY < 5.5f) positionY = 5.5f
        else if (!gamePad.get.isButtonPressed(11) && !Keyboard.isKeyDown(Keyboard.KEY_UP)) {
          startJumpHeight = -1f
          jumpStage = 0
          jumpHover = presetJumpHover
        }
      }
    }
  }

  /** Render the item centered at the x- & y-coordinate.
    *
    * @param x X-coordinate.
    * @param y Y-coordinate.
    */
  def renderCentered(x: Float, y: Float) {
    sprite.renderCentered(positionX, positionY)
  }
}
