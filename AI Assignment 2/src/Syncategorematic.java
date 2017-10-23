import java.util.Arrays;


public class Syncategorematic extends Symbol {
	
	SymbolType symbol_name = SymbolType.NOTHING;
	
	Variable variable = null; // could be null
	
	Symbol[] params = null; // could be any function symbol, variable, constant or Syncategorematic
	

	public Syncategorematic() {
		
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(params);
		result = prime * result
				+ ((symbol_name == null) ? 0 : symbol_name.hashCode());
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Syncategorematic other = (Syncategorematic) obj;
		if (!Arrays.equals(params, other.params))
			return false;
		if (symbol_name != other.symbol_name)
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		
		result += symbol_name.toString() + " ";
		
		if (variable != null) {
			result += "(" + variable + ") ";
		}
		result += "[";
		for (Symbol s: params) {
			result += " " + s.toString() + " ,";
		}
		result = result.substring(0, result.length() - 1);
		result += " ]";
		return result;
	}
	
	
	
	
	
	
	
	
}
