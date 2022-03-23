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

    public static double distanceBetween(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.pow((Math.pow(x2 - x1, 2) +
                Math.pow(y2 - y1, 2) +
                Math.pow(z2 - z1, 2)), 0.5);
    }


}
