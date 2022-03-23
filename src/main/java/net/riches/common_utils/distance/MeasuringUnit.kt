package net.riches.common_utils.distance

enum class MeasuringUnit {
    KILOMETERS,
    MILES,
    FEET,
    YARDS,
    METERS;

    fun convertToKilometers(amount: Double): Double {
        return when (this) {
            METERS -> amount / 1000
            YARDS -> amount * 0.0009144
            FEET -> amount / 3280.8399
            MILES -> amount * 1.609344
            else -> {
                amount
            }
        }
    }

    fun convertToMiles(amount: Double): Double {
        return when (this) {
            METERS -> amount / 1609.344
            YARDS -> amount / 1760
            FEET -> amount / 5280
            KILOMETERS -> amount * 0.62137119224
            else -> {
                amount
            }
        }
    }

    fun convertToFeet(amount: Double): Double {
        return when (this) {
            FEET -> amount / 3.2808399
            MILES -> amount * 1609.344
            YARDS -> amount * 0.9144
            KILOMETERS -> amount * 1000
            else -> {
                amount
            }
        }
    }

    fun convertToYards(amount: Double): Double {
        return when (this) {
            FEET -> amount / 3
            MILES -> amount * 1760
            METERS -> amount * 1.0936133
            KILOMETERS -> amount * 1093.6133
            else -> {
                amount
            }
        }
    }
}
