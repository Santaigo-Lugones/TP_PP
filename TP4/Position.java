package ternilapilli;

import java.util.Objects;

public class Position {
	public int row;
	public int column;
	public Position(int x, int y) {
		row = x;
		column = y;
	}
	
	public boolean equals(Object o) {
		return o != null &&
				(this == o ||
				( getClass() == o.getClass() &&
				((Position)o).row == row &&
				((Position)o).column == column));
	}
	
	public int hashCode() {  
		return Objects.hash(row, column);
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}