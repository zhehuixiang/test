import java.util.ArrayList;

public class UkkSuffixTree {
	int begin;
	int end;
	String ss;
	ArrayList<UkkSuffixTree> childs;

	public UkkSuffixTree(int begin) {
		this.begin = begin;
		childs = new ArrayList<UkkSuffixTree>();
		end = -1;
	}

	public void done(int end) {
		this.end = end;
	}

	public void print(String s) {
		try {
			if (end == -1)
				System.out.print(s.substring(begin));
			else
				System.out.print(s.substring(begin, end));
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(begin + "," + end);
		}
	}

	public void println(String s) {
		try {
			if (end == -1)
				System.out.println(s.substring(begin));
			else
				System.out.println(s.substring(begin, end));
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(begin + "," + end);
		}
	}

	public static void print(String s, UkkSuffixTree root, int index) {
		root.print(s);
		if (root.childs.size() == 0) {
			System.out.println();
			return;
		}
		int count = 0;
		for (UkkSuffixTree child : root.childs) {
			for (int i = 0; i < index && count != 0; i++)
				System.out.print("\t");
			System.out.print("\t");
			print(s, child, index + 1);
			count++;
		}
	}

	public static void build(String s) {
		UkkSuffixTree point_node = new UkkSuffixTree(-1);// decide which node to
															// insert.
		UkkSuffixTree child = null;// decide which child to insert.
		int point_length = 0;// decide exactly where to insert.
		int remain = 1;// decide how many remained to insert.
		UkkSuffixTree root = point_node;
		for (int i = 0; i < s.length();) {
			if (child == null) {// 匹配child
				for (UkkSuffixTree it : point_node.childs) {
					if (s.charAt(i - point_length) == s.charAt(it.begin)) {
						child = it;
						if (point_length == 0) {
							point_length++;
							remain++;
							i++;
						}
						break;
					}
				}
				if (child == null) {
					point_node.childs.add(new UkkSuffixTree(i - point_length));
					
					if (remain == 1)
						i++;
					else {
						remain--;
						point_length--;
						point_length = remain - 1;
					}
					point_node = root;
				}
			} else {
				if (child.end != -1 && child.end <= child.begin + point_length) {// 搜索到节点的末尾
					point_node = child;
					point_length -= child.end - child.begin;
					child = null;
				} else if (s.charAt(child.begin + point_length) == s.charAt(i)) {
					point_length++;
					remain++;
					i++;
				} else {
					ArrayList<UkkSuffixTree> temp = child.childs;
					int temp_end = child.end;
					UkkSuffixTree temp_child = new UkkSuffixTree(child.begin + point_length);
					temp_child.done(temp_end);
					temp_child.childs = temp;

					child.childs = new ArrayList<UkkSuffixTree>();
					child.done(child.begin + point_length);
					child.childs.add(temp_child);
					child.childs.add(new UkkSuffixTree(i));
					child = null;
					
					if (remain == 1)
						i++;
					else {
						remain--;
						point_length--;
						point_length = remain - 1;
					}
					point_node = root;
				}
			}
		}
		print(s, root, 0);

	}

	public static void main(String[] args) {
		build("fjeiowagieilfljewifjfjewfeilwajfi$");
	}
}