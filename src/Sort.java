import java.util.ArrayList;
import java.util.Arrays;
public abstract class Sort<Type> {
	Type[]array;
	SortType type=SortType.quick;
	public enum SortType{quick,merge,heap};

	public Sort(Type...array){
		this.array=array;
	}
	public Sort(SortType type,Type...array){
		this.array=array;
		this.type=type;
	}
	@Override
	public String toString() {
		return "Sort [array=" + Arrays.toString(array) + "]";
	}
	private void swap(int i,int j){
		Type temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	public abstract int compare(Type a,Type b);
	
	public void quicksort(int begin,int end){
		if(begin>=end)return;
		Type base=array[begin];
		int i=begin,j=end;
		while(i<=end&&compare(array[i],base)<=0)i++;
		while(j>=begin&&compare(array[j],base)>0)j--;
		for(;i<=j;){
			swap(i,j);
			while(compare(array[i],base)<=0)i++;
			while(compare(array[j],base)>0)j--;
		}
		swap(j,begin);
		quicksort(begin,j-1);
		quicksort(j+1,end);
	}
	private void heapup(int i,int end){
		if(i==0)return;
		int parent=(i-1)/2;
		int sib=parent;
		if(i%2==0)sib=i-1;
		else if(i+1<=end)sib=i+1;
		if(compare(array[i],array[sib])>=0&&compare(array[i],array[parent])>0){swap(parent,i);heapdown(i,end);}
		else if(compare(array[i],array[sib])<=0&&compare(array[sib],array[parent])>0){swap(parent,sib);heapdown(sib,end);}
		heapup(parent,end);
	}
	private void buildup(int end){
		for(int i=end;i>=end/2;i--)
			heapup(i,end);
	}
	private void heapdown(int i,int end){
		if(i>=end)return;
		int l=i*2+1;
		int r=i*2+2;
		if(r>end){
			r=i;
			if(l>end)l=i;
		}
		if(compare(array[l],array[r])>=0&&compare(array[l],array[i])>0){swap(l,i);heapdown(l,end);}
		else if(compare(array[r],array[l])>=0&&compare(array[r],array[i])>0){swap(r,i);heapdown(r,end);}
	}
	public void heapsort(int end){
		buildup(end);
		for(int i=end;i>=0;i--){
			swap(0,i);
			heapdown(0,i-1);
		}
	}
	
	public void insert(int pos,int begin,int end){
		ArrayList<Type>temp=new ArrayList<Type>();
		for(int i=begin;i<end;i++)
			temp.add(array[i]);
		for(int i=begin-1,j=end-1;i>=pos;i--,j--)
			array[j]=array[i];
		for(int i=0;i<temp.size();i++)
			array[i+pos]=temp.get(i);
	}
	public void mergesort(int begin,int end){
		if(begin>=end)return;
		if(begin+1==end){
			if(compare(array[begin],array[end])>0)
				swap(begin,end);
			return;
		}
		int mid=(begin+end)/2;
		mergesort(begin,mid);
		mergesort(mid+1,end);
		for(int i=begin,j=mid+1;i<=j&&i<=end&&j<=end;){
			while(i<=j&&i<=end&&compare(array[i],array[j])<=0)i++;
			if(i>j||i>end)break;
			int pre=j;
			while(j<=end&&compare(array[j],array[i])<=0)j++;
			insert(i,pre,j);
		}
	}
	
	public void sort(){
		switch(type){
		case quick:quicksort(0,array.length-1);
		case merge:mergesort(0,array.length-1);
		case heap:heapsort(array.length-1);
		}
	}
	public static void main(String[]args){
		Double[]array={13.32,3.3,4.1,4.4,2.4,3.2,52.0,3.2,324.6,6.8,324.4,4.,3.,432.,.5,4.,4.32,4.3,53.,24.3};
		Sort<Double> s=new Sort<Double>(array){

			@Override
			public int compare(Double a, Double b) {
				// TODO Auto-generated method stub
				if(a<b)return -1;
				else if(a>b)return 1;
				return 0;
			}};
		s.heapsort(array.length-1);
		System.out.println(s);
	}
}
