package qi.scanner;

public enum Token_Type {

	ORIGIN, SCALE, ROT, IS, TO,	// ������

	STEP, DRAW, FOR, FROM,	// ������

	COLOR, RED, BLACK,	// ������

	T,	// ����

	/** �ָ�����*/
	SEMICO, L_BRACKET, R_BRACKET, COMMA,	

	/** �����*/
	PLUS, MINUS, MUL, DIV, POWER,	
	
	/**���� */
	FUNC,	
	
	/**���� */
	CONST_ID,	
	
	/**�ռǺ� */
	NONTOKEN,	
	
	/**����Ǻ� */
	ERRTOKEN	
}
