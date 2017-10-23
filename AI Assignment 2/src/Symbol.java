
public class Symbol {

	String name;
	
	public Symbol() {
		name = "";
	}
	
	public Symbol(String name) {
		this.name = name;
	}
	
	public boolean checkEqual(Symbol s) {
		return false;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		Symbol s = (Symbol) obj;
//		return this.name.equals(s.name);
//	}
	
	
	
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symbol other = (Symbol) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
