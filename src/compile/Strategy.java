package compile;


/**
 * 代表着经过编译后的策略
 */
public interface Strategy {
	/**
	 * @param CUR 当前回合
	 * @param A 数组，对应原生策略代码中的A[]，是本策略的历史选择
	 * @param B 数组，对应原生策略代码中的B[]，是对手策略的历史选择
	 * @return 本策略在CUR回合作出的选择
	 */
	int getValue(int CUR,int[] A, int [] B);
}

