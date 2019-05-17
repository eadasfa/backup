package qi.scanner;

public enum Token_Type {

	ORIGIN, SCALE, ROT, IS, TO,	// 保留字

	STEP, DRAW, FOR, FROM,	// 保留字

	COLOR, RED, BLACK,	// 保留字

	T,	// 参数

	/** 分隔符号*/
	SEMICO, L_BRACKET, R_BRACKET, COMMA,	

	/** 运算符*/
	PLUS, MINUS, MUL, DIV, POWER,	
	
	/**函数 */
	FUNC,	
	
	/**常数 */
	CONST_ID,	
	
	/**空记号 */
	NONTOKEN,	
	
	/**出错记号 */
	ERRTOKEN	
}
