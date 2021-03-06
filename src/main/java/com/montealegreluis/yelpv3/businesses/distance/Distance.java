/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.montealegreluis.yelpv3.businesses.distance;

import static com.montealegreluis.yelpv3.businesses.distance.UnitOfLength.*;

/**
 * Searching using a coordinate will include the distance from that point to where the business is
 * located
 * <p>
 * Distances are measured in meters by Yelp, but it is more common for a regular user to use miles
 * or kilometers
 */
public class Distance {
    public final UnitOfLength unit;
    public final Double value;

    public static Distance inMeters(double meters) {
        return new Distance(meters, METERS);
    }

    public static Distance inMiles(double miles) {
        return new Distance(miles, MILES);
    }

    public static Distance inKilometers(double kilometers) {
        return new Distance(kilometers, KILOMETERS);
    }

    protected Distance(double value, UnitOfLength unit) {
        this.unit = unit;
        this.value = value;
    }

    /**
     * It is valid to compare distances even if they are using different units of length
     */
    public boolean biggerThan(Distance distance) {
        return value > distance.convertTo(unit).value;
    }

    /**
     * It is valid to compare distances even if they are using different units of length
     */
    public boolean smallerThan(Distance distance) {
        return value < distance.convertTo(unit).value;
    }

    /**
     * It converts the current distance to the unit of length given
     * <p>
     * A conversion will be common in a UI where Kilometers or Miles will be easier to read
     */
    public Distance convertTo(UnitOfLength unit) {
        return new Distance(UnitsConverter.convert(value, this.unit, unit), unit);
    }

    /**
     * The format for distance is:
     * <p>
     * The value with only 2 decimal places followed by the name of the unit in lowercase letters
     * <p>
     * For instance:
     * <p>
     * <code>3.56 kilometers</code>
     */
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.toString().toLowerCase());
    }
}
