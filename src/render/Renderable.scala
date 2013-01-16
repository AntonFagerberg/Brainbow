/*
 * Copyright (c) 2013 Anton Fagerberg.
 * www.antonfagerberg.com | anton [a-t] antonfagerberg [d-o-t] com.
 */
package render

trait Renderable {
  /** Render the item centered at the x- & y-coordinate.
    *
    * @param x X-coordinate.
    * @param y Y-coordinate.
    */
  def renderCentered(x: Float, y: Float)
}
