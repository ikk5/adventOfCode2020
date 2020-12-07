package helperObjects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Coord {

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        return new EqualsBuilder()
                .append(x, coord.x)
                .append(y, coord.y)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(x)
                .append(y)
                .toHashCode();
    }
}
