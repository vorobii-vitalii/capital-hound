package com.site.capitalhound.helpers;

public class CoordinatesHelper {

    public static final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    /**
     *  Calculation of distance between a pair of lat, lng values using Haversine formula
     */
    public static int calculateDistanceInKilometer(double lat1, double lng1, double lat2, double lng2) {

        double latDistance = Math.toRadians(lat1 - lat2);
        double lngDistance = Math.toRadians(lng1 - lng2);

        double a =
                Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }

}
