package de.rub.cs.selab22.a14.database;

import java.util.Arrays;
import java.util.List;

/**
 * Data class used to encapsulate generic data to be stored inside the database.
 *
 * @param <T> The Type of data you want to store in your DataPoint.
 *            Usually something like {@link String} or {@link Integer}.
 */
public class DataPoint<T> {

    private List<T> data;

    /**
     * @param <X> Type parameter used to infer the type of elements in this DataPoint.
     *            Can usually be left empty unless required by the type checker.
     * @return A list of data stored in this DataPoint.
     */
    public <X> List<X> getData() {
        return (List<X>) data;
    }

    public DataPoint(T... data) {
        this.data = Arrays.asList(data);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(!(o instanceof DataPoint)) {
            return false;
        }

        DataPoint d = (DataPoint) o;
        return d.getData().equals(this.data);
    }

}
