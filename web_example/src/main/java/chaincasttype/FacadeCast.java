package chaincasttype;

import java.util.ArrayList;
import java.util.List;

import chaincasttype.ByteCast;
import chaincasttype.CastChain;
import chaincasttype.DoubleCast;
import chaincasttype.IntegerCast;
import chaincasttype.StringCast;

public class FacadeCast {
	
	private static final Integer LAST_PARAMETER_INDEX = 1;
	
	private static final CastChain STRING_CAST = new StringCast();
	private static final CastChain INTEGER_CAST = new IntegerCast();
	private static final CastChain DOUBLE_CAST = new DoubleCast();
	private static final CastChain BYTE_CAST = new ByteCast();
	private static final CastChain CONTROLLER_CAST = new ControllerCast();
	
	
	private static final List<CastChain> CAST_CHAINS = new ArrayList<>();
	static {
		CAST_CHAINS.add(STRING_CAST);
		CAST_CHAINS.add(INTEGER_CAST);
		CAST_CHAINS.add(DOUBLE_CAST);
		CAST_CHAINS.add(BYTE_CAST);
		CAST_CHAINS.add(CONTROLLER_CAST);
	}
	public static CastChain getCastChain() throws Exception {
		
		for (int i = 0; i < CAST_CHAINS.size() - LAST_PARAMETER_INDEX; ++i) {
			CAST_CHAINS.get(i).setNextChain(CAST_CHAINS.get(i + LAST_PARAMETER_INDEX));
		}
		return CAST_CHAINS.get(0);
	}
	
	public static void addCastChain(CastChain nextChain) {
		CAST_CHAINS.add(nextChain);
	}
}
