/**
 * Copyright (c) 2013 Anton Fagerberg.
 * anton@antonfagerberg.com | www.antonfagerberg.com
 */
import org.lwjgl.input.Keyboard
import render.Image
import spm.Player

object Main extends Game(1440, 900, "Brainbow, bithces!", 0, 32, 0, 20) {
  private val background = new Image("/Users/anton/Dropbox/SPM/Bilder/grid_no_scale.png", 32f, 20f)
  private val player = new Player

  run()

  def update() {
    player.update()
  }

  def render() {
    background.renderCentered(16f, 10f)
    player.renderCentered(0f, 0f)
  }

  def main(args: Array[String]) {}
}