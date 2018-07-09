
public class ManacherPalindromic {
	public static void parlindromic(String s){
		if(s.length()==0)return;
		String temp="#";
		for(int i=0;i<s.length();i++){
			temp+=s.substring(i, i+1);
			temp+="#";
		}
		//compute P[];
		int P[]=new int[s.length()*2+1];
		P[0]=0;
		P[1]=1;
		int most_right=2;
		int most_center=1;
		for(int i=2;i<P.length;i++){
			int r=most_center*2>i?Math.min(most_right,i+P[most_center*2-i]):i;
			int l=i*2-r;
			int ret=par(temp,l,r);
			if(ret>most_right){
				most_right=ret;
				most_center=i;
			}
			P[i]=ret-i;
		}
		//return max
		int max=0;
		for(int i=0;i<P.length;i++){
			if(P[i]>P[max])max=i;
		}
		int c=max;
		int p=P[max];
		System.out.println(s.substring(c/2-p/2,c/2+(p+1)/2));
	}
	public static int par(String s,int l,int r){
		int left=l;
		int right=r;
		while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
			left--;
			right++;
		}
		return right-1;
	}
	public static void main(String[]args){
		parlindromic("cabcbabbabcba");
	}
}
