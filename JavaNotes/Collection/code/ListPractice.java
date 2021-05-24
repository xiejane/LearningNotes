/*
* 使用 List 的练习题
*/

import java.util.*;

public class ListPractice {
	public static void main(String[] args){
		List<Integer> nums = new ArrayList<>();
		int start=10;
		int end =20;
		for (int i=start;i<end;i++){
			nums.add(i);
		}
		Collections.shuffle(nums);  // 混淆List使其乱序
		int radom = (int) (Math.random()*nums.size());
		int removed = nums.remove(radom);
		System.out.println(removed);

		/* 查找有序List内缺失元素
		* if (removed == missNumber(start,end,nums)){
		* 	System.out.println("Success!");
		* }
		* System.out.println(missNumber(start,end,nums));
		 */

		//查找无序 List的缺失元素
		if (removed == getMissedNum(start,end,nums)){
			System.out.println("Success!");
		}
		System.out.println(getMissedNum(start,end,nums));

	}

	// 查找有序 List 内缺失元素
	public static int missNumber(int start, int end, List<Integer> list){
		for (int i=0;i<list.size();i++){
			if (start+i != list.get(i)){
				return start+i;
			}
		}
		return end-1;
	}

	// 查找乱序 List 内缺失元素
	public static int getMissedNum(int start,int end,List<Integer> list){
		int tag = list.size();
		int count =0;
		while (tag>0){
			if (!list.contains(start+count)){
				return start+count;
			}else {
				tag--;
				count++;
			}

		}
		return end-1;
	}
}
