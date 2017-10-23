import java.util.Arrays;


public class Predicate extends Sentence {

	//String name;
	int arity;
	Term[] terms;
	
	public Predicate() {
		this.name = "";
		this.arity = 0;
		this.terms = new Term[arity];
	}
	
	public Predicate(String name, int arity) {
		this.name = name;
		this.arity = arity;
		this.terms = new Term[arity];
	}
	
	public Predicate(String name, int arity, Term[] terms) {
		this.name = name;
		this.arity = arity;
		this.terms = terms;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + arity;
		result = prime * result + Arrays.hashCode(terms);
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
		Predicate other = (Predicate) obj;
		if (arity != other.arity)
			return false;
		if (!Arrays.equals(terms, other.terms))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		str += this.name + "(";
		for (int i = 0 ; i < arity; i++) {
			str += terms[i].toString();
			if (i != arity - 1) {
				str += ", ";
			}
		}
		str += ")";
		return str;
	}
	
	
	
}
