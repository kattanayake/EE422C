import java.util.*;

public class Stars {
	public static void main(String args[]) {
		stars_print(10);
	}

	public static void stars_print(int n) {
		if (n == 0) {
			System.out.println('*');
			return;
		}

		for(int i = 0; i < (n - 1) * 2; i++) {
			System.out.print('*');
		}

		stars_print(n - 1);

	}
}