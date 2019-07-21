package com.maruani.esgi.summaryapplication.module.surface

class FlashlightCone(viewWidth: Int, viewHeight: Int) {

    var x: Int = 0
        private set
    var y: Int = 0
        private set
    val radius: Int

    init {
        x = viewWidth / 2
        y = viewHeight / 2
        // Adjust the radius for the narrowest view dimension.
        radius = if (viewWidth <= viewHeight) x / 3 else y / 3
    }

    /**
     * Update the coordinates of the flashlight cone.
     *
     * @param newX Changed value for x coordinate.
     * @param newY Changed value for y coordinate.
     */
    fun update(newX: Int, newY: Int) {
        x = newX
        y = newY
    }
}