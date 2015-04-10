import java.util.Arrays;

public class Lab1 {

	int x,y;
	
	public Lab1(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Lab1 other = (Lab1) obj;
		if (x != other.x)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lab1 [x=" + x + ", y=" + y + "]";
	}
	
}
