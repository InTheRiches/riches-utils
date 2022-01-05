package net.riches.common_utils.distance;

public class DistanceUtil {
    public static double convertMeasuringUnit(MeasuringUnit from, MeasuringUnit to, double amount) {
        switch (to) {
            case KILOMETERS:
                switch (from) {
                    case METERS:
                        return amount / 1000;
                    case YARDS:
                        return amount * 0.0009144;
                    case FEET:
                        return amount / 3280.8399;
                    case MILES:
                        return amount * 1.609344;
                }
                break;
            case MILES:
                switch (from) {
                    case METERS:
                        return amount / 1609.344;
                    case YARDS:
                        return amount / 1760;
                    case FEET:
                        return amount / 5280;
                    case KILOMETERS:
                        return amount * 0.62137119224;
                }
            case METERS:
                switch (from) {
                    case FEET:
                        return amount / 3.2808399;
                    case MILES:
                        return amount * 1609.344;
                    case YARDS:
                        return amount * 0.9144;
                    case KILOMETERS:
                        return amount * 1000;
                }
            case FEET:
                switch (from) {
                    case YARDS:
                        return amount * 3;
                    case MILES:
                        return amount * 5280;
                    case METERS:
                        return amount * 3.2808399;
                    case KILOMETERS:
                        return amount * 3280.8399;
                }
            case YARDS:
                switch (from) {
                    case FEET:
                        return amount / 3;
                    case MILES:
                        return amount * 1760;
                    case METERS:
                        return amount * 1.0936133;
                    case KILOMETERS:
                        return amount * 1093.6133;
                }
        }
        return 0.0;
    }
}
