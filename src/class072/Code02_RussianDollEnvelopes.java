package class072;

import java.util.Arrays;

// 俄罗斯套娃信封问题
// 给你一个二维整数数组envelopes ，其中envelopes[i]=[wi, hi]
// 表示第 i 个信封的宽度和高度
// 当另一个信封的宽度和高度都比这个信封大的时候
// 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样
// 请计算 最多能有多少个信封能组成一组“俄罗斯套娃”信封
// 即可以把一个信封放到另一个信封里面，注意不允许旋转信封
// 测试链接 : https://leetcode.cn/problems/russian-doll-envelopes/
public class Code02_RussianDollEnvelopes {

	public static int maxEnvelopes(int[][] envelopes) {
		int n = envelopes.length;
		int[] nums = new int[n];
		Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (b[1] - a[1]));
		for (int i = 0; i < n; i++) {
			nums[i] = envelopes[i][1];
		}
		return lengthOfLIS(nums);
	}

	public static int lengthOfLIS(int[] nums) {
		int n = nums.length;
		int[] ends = new int[n];
		int len = 0;
		for (int i = 0, find; i < n; i++) {
			find = bs(ends, len, nums[i]);
			if (find == -1) {
				ends[len++] = nums[i];
			} else {
				ends[find] = nums[i];
			}
		}
		return len;
	}

	public static int bs(int[] ends, int len, int num) {
		int l = 0, r = len - 1, m, ans = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (num <= ends[m]) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

}
