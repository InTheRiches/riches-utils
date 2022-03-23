package net.riches.common_utils.distance

class DistanceUtil {
    fun convertMeasuringUnit(from: MeasuringUnit?, to: MeasuringUnit?, amount: Double): Double {
        when (to) {
            MeasuringUnit.KILOMETERS -> when (from) {
                MeasuringUnit.METERS -> return amount / 1000
                MeasuringUnit.YARDS -> return amount * 0.0009144
                MeasuringUnit.FEET -> return amount / 3280.8399
                MeasuringUnit.MILES -> return amount * 1.609344
            }
            MeasuringUnit.MILES -> when (from) {
                    MeasuringUnit.METERS -> return amount / 1609.344
                    MeasuringUnit.YARDS -> return amount / 1760
                    MeasuringUnit.FEET -> return amount / 5280
                    MeasuringUnit.KILOMETERS -> return amount * 0.62137119224
                    else -> {}
                }
            MeasuringUnit.METERS -> {
                when (from) {
                    MeasuringUnit.FEET -> return amount / 3.2808399
                    MeasuringUnit.MILES -> return amount * 1609.344
                    MeasuringUnit.YARDS -> return amount * 0.9144
                    MeasuringUnit.KILOMETERS -> return amount * 1000
                    else -> {}
                }
            }
            MeasuringUnit.FEET -> {
                when (from) {
                    MeasuringUnit.YARDS -> return amount * 3
                    MeasuringUnit.MILES -> return amount * 5280
                    MeasuringUnit.METERS -> return amount * 3.2808399
                    MeasuringUnit.KILOMETERS -> return amount * 3280.8399
                }
                when (from) {
                    MeasuringUnit.FEET -> return amount / 3
                    MeasuringUnit.MILES -> return amount * 1760
                    MeasuringUnit.METERS -> return amount * 1.0936133
                    MeasuringUnit.KILOMETERS -> return amount * 1093.6133
                }
            }
            MeasuringUnit.YARDS -> when (from) {
                MeasuringUnit.FEET -> return amount / 3
                MeasuringUnit.MILES -> return amount * 1760
                MeasuringUnit.METERS -> return amount * 1.0936133
                MeasuringUnit.KILOMETERS -> return amount * 1093.6133
            }
            else -> {}
        }
        return 0.0
    }

    fun distanceBetween(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double): Double {
        return Math.pow(
            Math.pow(x2 - x1, 2.0) +
                    Math.pow(y2 - y1, 2.0) +
                    Math.pow(z2 - z1, 2.0), 0.5
        )
    }
}