import util.ReadLineByLine;

public class Test {
	public static void main(String[]args){
		int count=0;
		for(int i=100000000;i<1000000000;i++){
//		System.out.println(Integer.MAX_VALUE);
			int x=i%10000;
			x+=i/100000*10000;
//			System.out.println(i+","+x);
			if(i%x==0)count++;
		}
		System.out.println(count);
	}
}
