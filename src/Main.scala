/**
 * Copyright (c) 2013 Anton Fagerberg.
 * anton@antonfagerberg.com | www.antonfagerberg.com
 */
import render.Image

object Main extends Game(1440, 900) {
  val image = new Image("/Users/anton/Downloads/a_a.png")
  val image2 = new Image("/Users/anton/Downloads/a_a2.png")
  run()

  def update() {
    image.renderCentered(-0.5f, 0.5f)
    image2.renderCentered(0.5f, -0.5f)
  }

  def main(args: Array[String]) {}
}