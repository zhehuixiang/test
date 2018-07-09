
public class KMP {
	public static void main(String[]args) {
		KMP kmp=new KMP();
		kmp.search("fejwio;groea;jfeiowangreafewafewahgrea;fenfewioa", "few");
	}
	int[] nextArr(String subs) {
		int len = subs.length();
		int[] next =new int[len+1];
		next[0] = next[1] = 0;
		int j = 0;
		for (int i = 1; i < len; i++) {
			//已知前next[1]...next[i]，计算next[i+1]；
			//如果位置i与位置next[i]相等则next[i+1]=next[i]+1
			//否则，分割next[i]长度的字符串，得到其中的最长前后缀,即next[next[i]];
			//因为next数组存储的是长度，所以比较下一个的时候的位置索引为i和next[i]而不是i+1和next[i]+1；
			while (j > 0 && subs.charAt(i) != subs.charAt(j))j = next[j];
			if (subs.charAt(i) == subs.charAt(j))j++;
			next[i + 1] = j;
		}
		return next;
	}
	void search(String str, String subs) {
		int[] next = nextArr(subs);
		int j = 0;
		int len = str.length();
		int subl = subs.length();
		for (int i = 0; i < len; i++) {
			//与计算next数组的过程基本相似，此时后缀在str中，前缀在subs中。
			while (j > 0 && str.charAt(i) != subs.charAt(j))j = next[j];
			if (str.charAt(i) == subs.charAt(j))j++;
			if (j == subl) {
				System.out.println("find position:"+(i-j+1));
				System.out.println(str.substring(i-j+1));
				j = next[j];
			}
		}
	}
}
