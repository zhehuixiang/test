import java.util.ArrayList;

import util.ReadLineByLine;

public abstract class EditDist<Type> {
	ArrayList<Type> array1;
	ArrayList<Type> array2;

	EditDist(ArrayList<Type> array1, ArrayList<Type> array2) {
		this.array1 = array1;
		this.array2 = array2;
	}

	abstract double compare(Type a, Type b);

	private int min(double[] a) {
		int ret = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < a[ret])
				ret = i;
		}
		return ret;
	}

	public void edit() {
		double dp[][] = new double[array1.size() + 1][array2.size() + 1];
		int mark[][] = new int[array1.size() + 1][array2.size() + 1];
		for (int i = 0; i <= array1.size(); i++)
			dp[i][0] = i;
		for (int j = 0; j <= array2.size(); j++)
			dp[0][j] = j;
		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array2.size(); j++) {
				double cost[] = new double[3];
				cost[0] = dp[i][j] + compare(array1.get(i), array2.get(j));
				cost[1] = dp[i][j + 1] + 1;
				cost[2] = dp[i + 1][j] + 1;
				switch (min(cost)) {
				case 0:
					mark[i + 1][j + 1] = 0;
					dp[i + 1][j + 1] = cost[0];
					break;
				case 1:
					mark[i + 1][j + 1] = 1;
					dp[i + 1][j + 1] = cost[1];
					break;
				case 2:
					mark[i + 1][j + 1] = 2;
					dp[i + 1][j + 1] = cost[2];
					break;
				}
			}
		}
		ArrayList<ArrayList<Type>> result = new ArrayList<ArrayList<Type>>();
		print(mark, array1.size(), array2.size(), result);
		for (ArrayList<Type> element : result)
			System.out.println(element);
	}

	public void print(int mark[][], int i, int j, ArrayList<ArrayList<Type>> result) {
		ArrayList<Type> temp = new ArrayList<Type>();
		switch (mark[i][j]) {
		case 0:
			if (i == 0 || j == 0)
				return;
			print(mark, i - 1, j - 1, result);
			temp.add(array1.get(i - 1));
			temp.add(array2.get(j - 1));
			result.add(temp);
			break;
		case 1:
			if (i == 0)
				return;
			print(mark, i - 1, j, result);
			temp.add(array1.get(i - 1));
			temp.add(null);
			result.add(temp);
			break;
		case 2:
			if (j == 0)
				return;
			print(mark, i, j - 1, result);
			temp.add(null);
			temp.add(array2.get(j - 1));
			result.add(temp);
			break;
		}
	}

	public static void main(String args[]) {
		ReadLineByLine lbl = new ReadLineByLine();
		ArrayList<String> a1 = new ArrayList<String>();
		ArrayList<String> a2 = new ArrayList<String>();
		while (true) {
			String ret = lbl.readLBL("test1");
			if (ret == null)
				break;
			a1.add(ret);
		}
		while (true) {
			String ret = lbl.readLBL("test2");
			if (ret == null)
				break;
			a2.add(ret);
		}
		EditDist<String> e = new EditDist<String>(a1, a2) {

			@Override
			double compare(String a, String b) {
				// TODO Auto-generated method stub
				if (a.equals(b))
					return 0;
				else
					return 0.5;
			}
		};
		e.edit();
	}
}
