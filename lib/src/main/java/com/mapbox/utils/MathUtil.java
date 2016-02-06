package com.mapbox.utils;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Very useful class that includes methods that operate on given latitude/longitude points. All
 * formulas within the method use calculations on the basis of a spherical earth. In other words,
 * the formulas don't account for the very slight ellipsoidal effect the earth has. This is still
 * accurate for enough for most purposes and using a spherical model gives errors typically up to
 * 0.3%. You can read more about this error percentage as well as where most of these equations
 * came from by using the links below.
 * <p/>
 * http://gis.stackexchange.com/questions/25494/how-accurate-is-approximating-the-earth-as-a-sphere#25580
 * http://www.movable-type.co.uk/scripts/latlong.html
 *
 */
public class MathUtil {

    private MathUtil(){}

    /**
     * returns in miles
     * @param from
     * @param to
     * @return
     */
    public static double computeDistance(LatLng from, LatLng to) {
        double dLat = Math.toRadians(to.getLatitude() - from.getLatitude());
        double dLon = Math.toRadians(to.getLongitude() - from.getLongitude());
        double lat1 = Math.toRadians(from.getLatitude());
        double lat2 = Math.toRadians(to.getLatitude());

        double a = Math.pow(Math.sin(dLat/2), 2) + Math.pow(Math.sin(dLon/2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double R = 3960;

        double distance = R * c;
        return distance;
    }

    /**
     * Method operates the same as other computeDistance() but has option to convert miles into meters.
     * @param from
     * @param to
     * @param meters
     * @return
     */
    public static double computeDistance(LatLng from, LatLng to, Boolean meters){
        if(meters){
            return computeDistance(from, to) * 0.000621371192237;
        }
        return computeDistance(from, to);
    }

    /**
     * Gives a list of intermediate points between two given LatLng. How many points is determined
     * by the double value smoothness. The larger the value the more points you'll received between
     * the original two given points.
     * <p/>
     * More info at: http://williams.best.vwh.net/avform.htm#Intermediate
     * @param from
     * @param to
     * @param smoothness
     * @return
     */
    public static List<LatLng> pointsBetween(LatLng from, LatLng to, double smoothness){

        List<LatLng> pointsBetween = new ArrayList<>();

        // Check if start and finish Waypoints are the same
        if(from == to) {
            pointsBetween.add(from);
        } else {
            for(int i = 0; i<smoothness; i++) {

                double lat1 = from.getLatitude();
                double lon1 = from.getLongitude();
                double lat2 = to.getLatitude();
                double lon2 = to.getLongitude();
                double f = i * (1 / smoothness);

                lat1 = Math.toRadians(lat1);
                lat2 = Math.toRadians(lat2);
                lon1 = Math.toRadians(lon1);
                lon2 = Math.toRadians(lon2);

                double deltaLat = (lat2 - lat1);
                double deltaLon = (lon2 - lon1);

                double m = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(lat1) * Math.cos(lat2)
                        * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
                double distance = 2 * Math.atan2(Math.sqrt(m), Math.sqrt(1 - m));

                double a = Math.sin((1 - f) * distance) / Math.sin(distance);
                double b = Math.sin(f * distance) / Math.sin(distance);
                double x = a * Math.cos(lat1) * Math.cos(lon1) + b * Math.cos(lat2) * Math.cos(lon2);
                double y = a * Math.cos(lat1) * Math.sin(lon1) + b * Math.cos(lat2) * Math.sin(lon2);
                double z = a * Math.sin(lat1) + b * Math.sin(lat2);
                double lat3 = Math.atan2(z, Math.sqrt((x * x) + (y * y)));
                double lon3 = Math.atan2(y, x);

                pointsBetween.add(new LatLng(Math.toDegrees(lat3), Math.toDegrees(lon3)));
            }
        }
        return pointsBetween;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public static LatLng midPoint(LatLng from, LatLng to){

        double lat1 = from.getLatitude();
        double lon1 = from.getLongitude();
        double lat2 = to.getLatitude();
        double lon2 = to.getLongitude();

        double dLon = Math.toRadians(lon2 - lon1);

        //convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

        return new LatLng(Math.toDegrees(lat3), Math.toDegrees(lon3));
    }

    /**
     * Returns the heading from one LatLng to another LatLng. Headings are
     * expressed in degrees clockwise from North within the range [-180,180).
     * @return The heading in degrees clockwise from north.
     * <p/>
     * http://williams.best.vwh.net/avform.htm#Crs
     */
    public static double computeHeading(LatLng from, LatLng to) {
        double fromLat = Math.toRadians(from.getLatitude());
        double fromLng = Math.toRadians(from.getLongitude());
        double toLat = Math.toRadians(to.getLatitude());
        double toLng = Math.toRadians(to.getLongitude());
        double dLng = toLng - fromLng;
        double heading = Math.atan2(Math.sin(dLng) * Math.cos(toLat),
                Math.cos(fromLat) * Math.sin(toLat) - Math.sin(fromLat) * Math.cos(toLat) * Math.cos(dLng));
        return (Math.toDegrees(heading) >= -180 && Math.toDegrees(heading) < 180) ?
                Math.toDegrees(heading) : ((((Math.toDegrees(heading) + 180) % 360) + 360) % 360 + -180);
    }
}
