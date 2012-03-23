package srm114;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class RectangularGrid
{
	public long countRectangles(int width, int height)
	{
		long sum = 0;
		for(int w = 1; w <= width; w++)
			for(int h = 1; h <= width; h++){
				if(w == h)
					continue;
				sum += ((width + 1 - w) * (height + 1 -h));
			}
		return sum;
	}
	
	
	public static void main(String[] args) {
		boolean all_right;
		all_right = true;
		
		int p0;
		int p1;
		long p2;
		
		// ----- test 0 -----
		p0 = 3;
		p1 = 3;
		p2 = 22L;
		RectangularGrid g = new RectangularGrid();
		System.out.println(g.countRectangles(3, 3));
		// ------------------
		
	}
}