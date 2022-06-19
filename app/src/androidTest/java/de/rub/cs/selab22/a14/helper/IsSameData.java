package de.rub.cs.selab22.a14.helper;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import de.rub.cs.selab22.a14.database.entities.Data;

public class IsSameData extends TypeSafeMatcher<Data> {

    Data other;

    private IsSameData(Data other) {
        this.other = other;
    }

    @Override
    protected boolean matchesSafely(Data item) {
        return  (item.timestamp.equals(other.timestamp)) &&
                (item.sensorIdentifier.equals((other.sensorIdentifier))) &&
                (item.dataPoint.equals(other.dataPoint));
    }

    @Factory
    public static Matcher<Data> isSameData(Data other) {
        return new IsSameData(other);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(this.other.toString());
    }
}
