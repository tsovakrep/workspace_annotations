package framework.util.chaincasttype;

import java.util.ArrayList;
import java.util.List;

import framework.util.chaincasttype.impl.ByteCast;
import framework.util.chaincasttype.impl.DoubleCast;
import framework.util.chaincasttype.impl.IntegerCast;
import framework.util.chaincasttype.impl.StringCast;

public class FacadeCast {
	
	private static final Integer LAST_PARAMETER_INDEX = 1;
	
	private static final Cast STRING_CAST = new StringCast();
	private static final Cast INTEGER_CAST = new IntegerCast();
	private static final Cast DOUBLE_CAST = new DoubleCast();
	private static final Cast BYTE_CAST = new ByteCast();
	
	private static final List<Cast> CAST_CHAINS = new ArrayList<>();
	static {
		CAST_CHAINS.add(STRING_CAST);
		CAST_CHAINS.add(INTEGER_CAST);
		CAST_CHAINS.add(DOUBLE_CAST);
		CAST_CHAINS.add(BYTE_CAST);
	}
	public static Cast getCastChain() throws Exception {
		
		for (int i = 0; i < CAST_CHAINS.size() - LAST_PARAMETER_INDEX; ++i) {
			CAST_CHAINS.get(i).setNextChain(CAST_CHAINS.get(i + LAST_PARAMETER_INDEX));
		}
		return CAST_CHAINS.get(0);
	}
	
	public static void addCastChain(Cast nextChain) {
		CAST_CHAINS.add(nextChain);
	}
}
