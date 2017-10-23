import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

import javax.lang.model.type.ArrayType;


public class Engine {
	
	public static boolean trace_mode = false;
	
	public static boolean isTrace_mode() {
		return trace_mode;
	}

	public static void setTrace_mode(boolean trace_mode) {
		Engine.trace_mode = trace_mode;
	}


	public static char[] variableBank = new char[] {'u', 'v', 'x', 'y', 'z'}; // or var_number
	
	public static char[] constantBank = new char[] {'a', 'b', 'c'}; // or const_number
	
	public static Symbol listify(String str) {
		return (makeASymbol(str, true));
	}
	
	public static void printHashMap(HashMap hm) {
		if (hm == null) {
			System.out.println("hashmap is null!");
		}
		for (Object key: hm.keySet()) {
			Object value = hm.get(key);
			System.out.println(key.toString() + " => " + value.toString());	
		}
		
	}
	
	public static HashMap unify1(Object obj1, Object obj2, HashMap hm) {
		
		if (trace_mode) {
			System.out.println("Iteration");
			if (obj1 != null)
				System.out.println("Object 1 = " + obj1.toString());
			else
				System.out.println("Object 1 is null!");
			if (obj2 != null)
				System.out.println("Object 2 = " + obj2.toString());
			else
				System.out.println("Object 2 is null!");
			printHashMap(hm);
			System.out.println(".....");
		}
		
		if (hm == null) {
			return null;
		}
		
		
		if (obj1 == null && obj2 == null) {
			return hm;
		}
		else if (obj1 == null || obj2 == null) {
			return null;
		}
		
		if (obj1.getClass().isArray() && obj2.getClass().isArray()) {
			Term[] a1 = (Term[]) obj1;
			Term[] a2 = (Term[]) obj2;
			if (a1.length != a2.length) {
				return null;
			}
		}
		try {
			
			if (obj1.getClass().isArray() && obj2.getClass().isArray()) {
				return unify1(getRest(obj1), getRest(obj2), unify1(getFirst(obj1), getFirst(obj2), hm));
			}
			
			Symbol s1 = (Symbol)obj1;
			Symbol s2 = (Symbol)obj2;
			if ((s1.getClass().equals(Constant.class) && s2.getClass().equals(Constant.class))
					|| (s1.getClass().equals(Symbol.class) && s2.getClass().equals(Symbol.class))){
				if (s1.equals(s2)) {
					return hm;
				}
			}
			
			if (s1.getClass().equals(Variable.class)) {
				return unifyVar((Variable) s1, s2, hm);
			}
			if (s2.getClass().equals(Variable.class)) {
				return unifyVar((Variable) s2, s1, hm);
			}
			if (s1.getClass().equals(Constant.class) || s2.getClass().isInstance(new Constant())) {
				return null;
			}
			
			
			return unify1(getRest(s1), getRest(s2), unify1(getFirst(s1), getFirst(s2), hm));
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Symbol getFirst(Object s) {
		if (s.getClass().isArray()) {
			Term[] arrTerm = (Term[]) s;
			if (arrTerm.length > 0) {
				return arrTerm[0];
			}
		}
		else if (s.getClass().equals(Predicate.class)) {
			Predicate p = (Predicate) s;
			return new Symbol(p.name);
		}
		else if (s.getClass().equals(FunctionSymbol.class)) {
			FunctionSymbol p = (FunctionSymbol) s;
			return new Symbol(p.name);
		}
		return null;
	}
	
	public static Object getRest(Object s) {
		if (s.getClass().isArray()) {
			Term[] arrTerm = (Term[]) s;
			if (arrTerm.length > 1) {
				Term[] result = new Term[arrTerm.length - 1];
				for (int i = 1; i < arrTerm.length; i++) {
					result[i - 1] = arrTerm[i];
				}
				return result;
				
			}
		}
		else if (s.getClass().equals(Predicate.class)) {
			Predicate p = (Predicate) s;
			return p.terms;
		}
		else if (s.getClass().equals(FunctionSymbol.class)) {
			FunctionSymbol p = (FunctionSymbol) s;
			return p.terms;
		}
		return null;
	}
	
	public static HashMap unifyVar(Variable v, Symbol s, HashMap hm) {
		if (hm.containsKey(v) && !hm.get(v).equals(v)) {
			return unify1((Symbol) hm.get(v), s, hm);
		}
		Symbol t = substitute(hm, s);
		if (symolContainsVariable(t, v)) {
			return null;
		}
		hm.put((Symbol) v, t);
		return hm;
	}
	
	
	
	
	public static Symbol substitute (HashMap hm, Symbol s) {
		if (hm.containsKey(s)) {
			return (Symbol)hm.get(s);
		} else if (s.getClass().equals(Predicate.class)
				|| s.getClass().equals(FunctionSymbol.class)) {
			Term [] terms = null;
			if (s.getClass().equals(Predicate.class)) {
				Predicate predicate = (Predicate) s;
				terms = predicate.terms;
			} else {
				FunctionSymbol functionSymbol = (FunctionSymbol) s;
				terms = functionSymbol.terms;
			}
			for (int i = 0; i < terms.length; i++) {
				terms[i] = (Term)substitute(hm, terms[i]);
			}
			return s;
		}
		return s;
	}
	
	public static boolean symolContainsVariable(Symbol s, Variable v) {
		if (s == null || v == null) {
			return false;
		}
		if (s.getClass().equals(Variable.class)) {
			if (v.equals((Variable) s)) {
				return true;
			}
		} else if (s.getClass().equals(Predicate.class)) {
			Predicate p = (Predicate) s;
			for (int i = 0; i < p.arity; i++) {
				if (symolContainsVariable(p.terms[i], v)) {
					return true;
				}
			}
		} else if (s.getClass().equals(FunctionSymbol.class)) {
			FunctionSymbol p = (FunctionSymbol) s;
			for (int i = 0; i < p.arity; i++) {
				if (symolContainsVariable(p.terms[i], v)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Symbol makeASymbol(String str, boolean first) {
		if (str.length() == 1) {
			if (Arrays.binarySearch(variableBank, str.charAt(0)) != -1) {
				return new Variable(str);
			} else {
				return new Constant(str);
			}
//		} 
//		else if (str.contains("_")) {
//			if (str.indexOf('_') == 3) {
//				return new Variable(str);
//			} else {
//				return new Constant(str);
//			}
		} else {
			int startParIndex = str.indexOf('(');
			String symbolName = str.substring(0, startParIndex);
			String termsStr = str.substring(startParIndex + 1, str.length() - 1).trim();
			Queue<String> termsStack = new LinkedList<String>();
			int startIndexOfTerm = 0;
			int openPar = 0;
			int closePar = 0;
			for (int i = 0; i < termsStr.length(); i++) {
				char c = termsStr.charAt(i);
				if (c == '(') {
					openPar++;
				} else if (c == ')') {
					closePar++;
				} else if (c == ',') {
					if (openPar == closePar) {
						String termStr = termsStr.substring(startIndexOfTerm, i).trim();
						termsStack.add(termStr);
						startIndexOfTerm = i + 1;
					}
				} 
			}
			int lastComma = termsStr.lastIndexOf(',');
			
			if (lastComma == -1) {
				String termStr = termsStr.substring(0, termsStr.length()).trim();
				termsStack.add(termStr);
			}
			else {
				String termStr = termsStr.substring(lastComma + 1, termsStr.length()).trim();
				termsStack.add(termStr);
			}
			int aritySize = termsStack.size();
			Term[] symbolTerms = new Term[aritySize];
			int counter = 0;
			while (!termsStack.isEmpty()) {
				Term currentTerm = (Term) makeASymbol(termsStack.poll(), false);
				symbolTerms[counter++] = currentTerm;
			}
			if (first) {
			Predicate predicate = new Predicate(symbolName, aritySize, symbolTerms);
			return predicate;
		} else {
			FunctionSymbol functionSymbol = new FunctionSymbol(symbolName, aritySize, symbolTerms);
			return functionSymbol;
		}
		}
	}
	
	
	public static HashMap unify(String str1, String str2) {
		return unify1(listify(str1), listify(str2), new HashMap());
	}

}
