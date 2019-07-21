package com.maruani.esgi.summaryapplication.module.sensor

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maruani.esgi.summaryapplication.R
import android.widget.TextView
import android.hardware.SensorManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener




class SensorActivity : AppCompatActivity(), SensorEventListener {

    // System sensor manager instance.
    private lateinit var sensorManager: SensorManager

    // Proximity and light sensors, as retrieved from the sensor manager.
    private var sensorProximity: Sensor? = null
    private var sensorLight: Sensor? = null

    // TextViews to display current sensor values.
    private lateinit var sensorLightTv: TextView
    private lateinit var sensorProximityTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        sensorLightTv = findViewById(R.id.label_light)
        sensorProximityTv = findViewById(R.id.label_proximity)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        val sensorErrorMsg = resources.getString(R.string.error_no_sensor)

        if (sensorProximity == null) {
            sensorProximityTv.text = sensorErrorMsg
        }

        if (sensorLight == null) {
            sensorLightTv.text = sensorErrorMsg
        }
    }

    override fun onStart() {
        super.onStart()
        sensorProximity?.also { sensorManager.registerListener(this, it ,SensorManager.SENSOR_DELAY_NORMAL) }
        sensorLight?.also { sensorManager.registerListener(this, it ,SensorManager.SENSOR_DELAY_NORMAL) }
    }

    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        // The new data value of the sensor.  Both the light and proximity
        // sensors report one value at a time, which is always the first
        // element in the values array.
        val currentValue = event.values[0]

        when (event.sensor.type) {
            Sensor.TYPE_LIGHT -> sensorLightTv.text = resources.getString(R.string.label_light, currentValue)
            Sensor.TYPE_PROXIMITY -> sensorProximityTv.text = resources.getString(R.string.label_proximity, currentValue)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}
