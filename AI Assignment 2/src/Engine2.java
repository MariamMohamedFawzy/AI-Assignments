import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Engine2 {

	public static boolean trace_mode = false;
	
	public static boolean isTrace_mode() {
		return trace_mode;
	}

	public static void setTrace_mode(boolean trace_mode) {
		Engine2.trace_mode = trace_mode;
	}



	public static char[] variableBank = new char[] {'u', 'v', 'x', 'y', 'z'}; // or var_number
	
	public static char[] constantBank = new char[] {'a', 'b', 'c'}; // or const_number

	public static final String MY_FUNCTION = "myFunction_";
	public static final String MY_VARIABLE = "myVar_";
	
	
	public static final String for_all_symbol = "FOR_ALL";
	public static final String there_exist_symbol = "THERE_EXIST";
	public static final String not_symbol = "NOT";
	public static final String and_symbol = "AND";
	public static final String or_symbol = "OR";
	public static final String implies_symbol = "IMPLIES";
	public static final String if_And_only_if_symbol = "IF_AND_ONLY_IF";
	

	public static Syncategorematic remove_NOT(Syncategorematic s) {
		SymbolType name = s.symbol_name;
		Syncategorematic result = s;
		if (name.equals(SymbolType.NOT)) {
			Symbol param = s.params[0];
			if (param.getClass().equals(Syncategorematic.class)) {
				result = new Syncategorematic();
				Syncategorematic s2 = (Syncategorematic) param;

				if (s2.symbol_name.equals(SymbolType.AND)) {
					s2.symbol_name = SymbolType.OR;
					Symbol[] params = s2.params;
					for (int i = 0; i < params.length; i++) {
						Symbol current = params[i];
						if (params[i].getClass().equals(Syncategorematic.class)) {
							if (((Syncategorematic) params[i]).symbol_name
									.equals(SymbolType.NOTHING)) {
								((Syncategorematic) params[i]).symbol_name = SymbolType.NOT;
								continue;
							}
						}
						Syncategorematic s_temp = new Syncategorematic();
						s_temp.symbol_name = SymbolType.NOT;
						s_temp.params = new Symbol[] { current };
						params[i] = s_temp;
					}
					result = s2;
				} else if (s2.symbol_name.equals(SymbolType.OR)) {
					s2.symbol_name = SymbolType.AND;
					Symbol[] params = s2.params;
					for (int i = 0; i < params.length; i++) {
						Symbol current = params[i];
						if (params[i].getClass().equals(Syncategorematic.class)) {
							if (((Syncategorematic) params[i]).symbol_name
									.equals(SymbolType.NOTHING)) {
								((Syncategorematic) params[i]).symbol_name = SymbolType.NOT;
								continue;
							}
						}
						Syncategorematic s_temp = new Syncategorematic();
						s_temp.symbol_name = SymbolType.NOT;
						s_temp.params = new Symbol[] { current };
						params[i] = s_temp;
					}
					result = s2;
				} else if (s2.symbol_name.equals(SymbolType.FOR_ALL)) {
					s2.symbol_name = SymbolType.THERE_EXIST;
					Symbol[] params = s2.params;
					for (int i = 0; i < params.length; i++) {
						Symbol current = params[i];
						if (params[i].getClass().equals(Syncategorematic.class)) {
							if (((Syncategorematic) params[i]).symbol_name
									.equals(SymbolType.NOTHING)) {
								((Syncategorematic) params[i]).symbol_name = SymbolType.NOT;
								continue;
							}
						}
						Syncategorematic s_temp = new Syncategorematic();
						s_temp.symbol_name = SymbolType.NOT;
						s_temp.params = new Symbol[] { current };
						params[i] = s_temp;
					}
					result = s2;
				} else if (s2.symbol_name.equals(SymbolType.THERE_EXIST)) {
					s2.symbol_name = SymbolType.FOR_ALL;
					Symbol[] params = s2.params;
					for (int i = 0; i < params.length; i++) {
						Symbol current = params[i];
						if (params[i].getClass().equals(Syncategorematic.class)) {
							if (((Syncategorematic) params[i]).symbol_name
									.equals(SymbolType.NOTHING)) {
								((Syncategorematic) params[i]).symbol_name = SymbolType.NOT;
								continue;
							}
						}
						Syncategorematic s_temp = new Syncategorematic();
						s_temp.symbol_name = SymbolType.NOT;
						s_temp.params = new Symbol[] { current };
						params[i] = s_temp;
					}
					result = s2;
				} else if (s2.symbol_name.equals(SymbolType.NOT)) {
					
					Symbol symbol_current = s2.params[0];
					
					if (symbol_current.getClass().equals(Syncategorematic.class)) {
						result = (Syncategorematic) symbol_current;
					} else {
						result.symbol_name = SymbolType.NOTHING;
						result.params = new Symbol[] {symbol_current};
					}
					
				} else if (s2.symbol_name.equals(SymbolType.NOTHING)) {
					s2.symbol_name = SymbolType.NOT;
					result = s2;
				}
			}
		}
		return result;
	}

	public static Syncategorematic remove_IMPLIES(Syncategorematic s) {
		SymbolType name = s.symbol_name;
		Syncategorematic result = new Syncategorematic();
		result.params = new Symbol[2];
		if (name.equals(SymbolType.IMPLIES)) {
			//System.out.println(s);
			Symbol[] params = s.params;

			Symbol first_symbol = params[0];

			Symbol second_symbol = params[1];

			result.symbol_name = SymbolType.OR;

			Syncategorematic s_new = new Syncategorematic();
			
			Syncategorematic first = (Syncategorematic) first_symbol;
			if (first.symbol_name.equals(SymbolType.NOTHING)) {
				//first.symbol_name = SymbolType.NOT;
				s_new.symbol_name = SymbolType.NOT ;
				s_new.params = first.params;
			} else {
				s_new.symbol_name = SymbolType.NOT;
				s_new.params = new Symbol[] { first_symbol };
			}
			
			result.params[0] = s_new;
			result.params[1] = second_symbol;
			//System.out.println(result);
			//System.out.println();
			return result;
		}
		return s;
	}

	public static Syncategorematic remove_IF_AND_ONLY_IF(Syncategorematic s) {
		SymbolType name = s.symbol_name;
		Syncategorematic result = new Syncategorematic();
		if (name.equals(SymbolType.IF_AND_ONLY_IF)) {


			result.symbol_name = SymbolType.AND;

			Syncategorematic s1 = new Syncategorematic();
			s1.symbol_name = SymbolType.IMPLIES;
			s1.params = new Symbol[2];
			s1.params[0] = copy_syncategorematic((Syncategorematic) s.params[0]);
			s1.params[1] = copy_syncategorematic((Syncategorematic) s.params[1]);

			Syncategorematic s2 = new Syncategorematic();
			s2.symbol_name = SymbolType.IMPLIES;
			s2.params = new Symbol[2];
			s2.params[0] = copy_syncategorematic((Syncategorematic) s.params[1]);
			s2.params[1] = copy_syncategorematic((Syncategorematic) s.params[0]);

			result.params = new Symbol[] { s1, s2 };
			return result;
		}
		return s;
	}
	
	public static Syncategorematic copy_syncategorematic(Syncategorematic input) {
		Syncategorematic result = new Syncategorematic();
		result.symbol_name = input.symbol_name;
		result.params = new Symbol[input.params.length];
		for (int i = 0; i < result.params.length; i++) {
			if (input.params[i].getClass().equals(Syncategorematic.class)) {
				result.params[i] = copy_syncategorematic((Syncategorematic) input.params[i]);
				if (((Syncategorematic)input).variable != null) {
					result.variable = (Variable) copy_not_syncategorematic(((Syncategorematic)input).variable);
				}
			} else {
				result.params[i] = copy_not_syncategorematic(input.params[i]);
			}
		}
		return result;
	}

	public static Symbol copy_not_syncategorematic(Symbol input) {
		if (input.getClass().equals(Predicate.class)) {
			Predicate in = (Predicate) input;
			Predicate result = new Predicate();
			result.name = in.name;
			result.arity = in.arity;
			result.terms = new Term[result.arity];
			for (int i = 0; i < result.arity; i++) {
				result.terms[i] = (Term) copy_not_syncategorematic(in.terms[i]);
			}
			return result;
		} else if (input.getClass().equals(FunctionSymbol.class)) {
			FunctionSymbol in = (FunctionSymbol) input;
			FunctionSymbol result = new FunctionSymbol();
			result.name = in.name;
			result.arity = in.arity;
			result.terms = new Term[result.arity];
			for (int i = 0; i < result.arity; i++) {
				result.terms[i] = (Term) copy_not_syncategorematic(in.terms[i]);
			}
			return result;
		} else if (input.getClass().equals(Variable.class)) {
			return new Variable(((Variable)input).name);
		} else if (input.getClass().equals(Constant.class)) {
			return new Constant(((Constant)input).name);
		}  
		return input;
	}
	
	public static Symbol rename_variable_to_function_in_predicate_or_function_symbol(Symbol s, Variable variable_to_replace,
			HashSet<Variable> variables_of_function, int counter) {
		Symbol result = s;
		if (s.getClass().equals(Variable.class)) {
			FunctionSymbol functionSymbol = new FunctionSymbol();
			functionSymbol.name = MY_FUNCTION + counter;
			Term[] terms = (Term[]) variables_of_function.toArray();
			functionSymbol.terms = terms;
			result = functionSymbol;
		} else if (s.getClass().equals(Predicate.class)) {
			Term[] terms = ((Predicate)s).terms;
			for (int i = 0; i < terms.length; i++) {
				Term current = terms[i];
				if (current.getClass().equals(Variable.class) && ((Variable)current).name.equals(variable_to_replace.name)) {
					FunctionSymbol functionSymbol = new FunctionSymbol();
					functionSymbol.name = MY_FUNCTION + counter;
					Term[] terms2 = new Term[variables_of_function.size()];
					int j = 0;
					for (Iterator iterator = variables_of_function.iterator(); iterator
							.hasNext();) {
						Variable variable = (Variable) iterator.next();
						terms2[j] = new Variable(variable.name);
					}
					functionSymbol.arity = terms2.length;
					functionSymbol.terms = terms2;
					terms[i] = functionSymbol;
				} else if (current.getClass().equals(FunctionSymbol.class)) {
					terms[i] = (Term) rename_variable_to_function_in_predicate_or_function_symbol(current, variable_to_replace, variables_of_function, counter);
				}
			}
		} else if (s.getClass().equals(FunctionSymbol.class)) {
			Term[] terms = ((FunctionSymbol)s).terms;
			for (int i = 0; i < terms.length; i++) {
				Term current = terms[i];
				if (current.getClass().equals(Variable.class) && ((Variable)current).name.equals(variable_to_replace.name)) {
					FunctionSymbol functionSymbol = new FunctionSymbol();
					functionSymbol.name = MY_FUNCTION + counter;
					Term[] terms2 = new Term[variables_of_function.size()];
					int j = 0;
					for (Iterator iterator = variables_of_function.iterator(); iterator
							.hasNext();) {
						Variable variable = (Variable) iterator.next();
						terms2[j] = variable;
					}
					functionSymbol.arity = terms2.length;
					functionSymbol.terms = terms2;
					terms[i] = functionSymbol;
				} else if (current.getClass().equals(FunctionSymbol.class)) {
					terms[i] = (Term) rename_variable_to_function_in_predicate_or_function_symbol(current, variable_to_replace, variables_of_function, counter);
				}
			}
		}
		return result;
	}
	
	public static void rename_variables_in_there_exist(Syncategorematic s, Variable variable_to_replace,
			HashSet<Variable> variables_of_function, int counter) {
		
		Symbol[] params = s.params;
	
		for (int i = 0; i < params.length; i++) {
			Symbol current = params[i];
			if (current.getClass().equals(Syncategorematic.class)) {
				rename_variables_in_there_exist((Syncategorematic)current, variable_to_replace, variables_of_function, counter);
			} else if (current.getClass().equals(Variable.class)) {
				FunctionSymbol functionSymbol = new FunctionSymbol();
				functionSymbol.name = MY_FUNCTION + counter;
				Term[] terms = (Term[]) variables_of_function.toArray();
				functionSymbol.arity = terms.length;
				functionSymbol.terms = terms;
				params[i] =  functionSymbol;
			} else if (current.getClass().equals(Predicate.class)|| current.getClass().equals(FunctionSymbol.class)) {
				params[i] = rename_variable_to_function_in_predicate_or_function_symbol(current, variable_to_replace, variables_of_function, counter);
			}
		}
	
	}

	//
	
	public static void rename_variable_in_function_symbol(FunctionSymbol f,
			String old_name, String new_name) {
		Term[] terms = f.terms;
		for (int i = 0; i < terms.length; i++) {
			Term current = terms[i];
			if (current.getClass().equals(Variable.class)) {
				if (((Variable) current).name.equals(old_name)) {
					terms[i] = new Variable(new_name);
				}
			} else if (current.getClass().equals(FunctionSymbol.class)) {
				rename_variable_in_function_symbol((FunctionSymbol) current,
						old_name, new_name);
			}
		}
	}

	
	public static void rename_symbol_not_syncategorematic(Symbol s, Variable v, int counter) {

		if (s.getClass().equals(Variable.class)) {
			if (s.name.equals(v.name)) {
				s.name = MY_VARIABLE + counter;
			}
		} else if (s.getClass().equals(FunctionSymbol.class)) {
			Term[] terms = ((FunctionSymbol) s).terms;
			for (int i = 0; i < terms.length; i++) {
				Term current = terms[i];
				if (current.getClass().equals(Variable.class)) {
					if (((Variable) current).name.equals(v.name)) {
						terms[i] = new Variable(MY_VARIABLE + counter);
					}
				} else if (current.getClass().equals(FunctionSymbol.class)) {
					rename_variable_in_function_symbol(
							(FunctionSymbol) current, v.name, MY_VARIABLE
									+ counter);
				}
			}
		} else if (s.getClass().equals(Predicate.class)) {
			Term[] terms = ((Predicate) s).terms;
			for (int i = 0; i < terms.length; i++) {
				Term current = terms[i];
				if (current.getClass().equals(Variable.class)) {
					if (((Variable) current).name.equals(v.name)) {
						terms[i] = new Variable(MY_VARIABLE + counter);
					}
				} else if (current.getClass().equals(FunctionSymbol.class)) {
					rename_variable_in_function_symbol(
							(FunctionSymbol) current, v.name, MY_VARIABLE
									+ counter);
				}
			}
		}
	
	}
	
	
	public static void rename(Syncategorematic s, Variable v, int counter) {
		if (!s.symbol_name.equals(SymbolType.NOTHING)) {
			Symbol[] params = s.params;
			for (Symbol param : params) {
				if (param.getClass().equals(Syncategorematic.class)) {
					rename((Syncategorematic) param, v, counter);
				} else {
					rename_symbol_not_syncategorematic(param, v, counter);
				}
			}
		} else {
			Symbol param = s.params[0];
			if (param.getClass().equals(Variable.class)) {
				Variable v2 = (Variable) param;
				if (v2.name.equals(v.name)) {
					s.params[0] = new Variable(v.name + counter);
				}
			} else if (param.getClass().equals(FunctionSymbol.class)) {
				Term[] terms = ((FunctionSymbol) param).terms;
				for (int i = 0; i < terms.length; i++) {
					Term current = terms[i];
					if (current.getClass().equals(Variable.class)) {
						if (((Variable) current).name.equals(v.name)) {
							terms[i] = new Variable(MY_VARIABLE + counter);
						}
					} else if (current.getClass().equals(FunctionSymbol.class)) {
						rename_variable_in_function_symbol(
								(FunctionSymbol) current, v.name, MY_VARIABLE
										+ counter);
					}
				}
			} else if (param.getClass().equals(Predicate.class)) {
				Term[] terms = ((Predicate) param).terms;
				for (int i = 0; i < terms.length; i++) {
					Term current = terms[i];
					if (current.getClass().equals(Variable.class)) {
						if (((Variable) current).name.equals(v.name)) {
							terms[i] = new Variable(MY_VARIABLE + counter);
						}
					} else if (current.getClass().equals(FunctionSymbol.class)) {
						rename_variable_in_function_symbol(
								(FunctionSymbol) current, v.name, MY_VARIABLE
										+ counter);
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////
	
	public static Syncategorematic remove_all_if_and_only_if(Syncategorematic input) {
		Syncategorematic result = input;
		if (input.symbol_name.equals(SymbolType.IF_AND_ONLY_IF)) {
			result = remove_IF_AND_ONLY_IF(input);
		}
		Symbol[] params = result.params;
		for (int i = 0; i < params.length; i++) {
			if (params[i].getClass().equals(Syncategorematic.class)) {
				params[i] = remove_all_if_and_only_if((Syncategorematic) params[i]);
			}
		}
		return result;
	}
	
	public static Syncategorematic remove_all_implies(Syncategorematic input) {
		Syncategorematic result = input;
		if (input.symbol_name.equals(SymbolType.IMPLIES)) {
			result = remove_IMPLIES(input);
		}
		Symbol[] params = result.params;
		for (int i = 0; i < params.length; i++) {
			if (params[i].getClass().equals(Syncategorematic.class)) {
				params[i] = remove_all_implies((Syncategorematic) params[i]);
			}
		}
		return result;
	}
	
	public static Syncategorematic remove_all_not(Syncategorematic input) {
		Syncategorematic result = input;
		
		if (input.symbol_name.equals(SymbolType.NOT)) {
			result = remove_NOT(input);
		}
		Symbol[] params = result.params;
		for (int i = 0; i < params.length; i++) {
			if (params[i].getClass().equals(Syncategorematic.class)) {
				params[i] = remove_all_not((Syncategorematic) params[i]);
			}
		}
		return result;
	}
	
	public static Object[] standardize_apart(Syncategorematic syncategorematic, HashSet<Variable> hashSet, int counter) {
		
		Object result[] = new Object[3];
		
		if (syncategorematic.symbol_name.equals(SymbolType.FOR_ALL)
				|| syncategorematic.symbol_name.equals(SymbolType.THERE_EXIST)) {
			Variable variable = syncategorematic.variable;
			if (hashSet.contains(variable)) {
				Variable new_var = new Variable(MY_VARIABLE + counter);
				hashSet.add(new_var);
				syncategorematic.variable = new_var;
				rename(syncategorematic, variable, counter);
				counter++;
			} else {
				hashSet.add(variable);
			}
		}
		
		Symbol[] params = syncategorematic.params;
		
		for (int i = 0; i < params.length; i++) {
			Symbol current = params[i];
			if (current.getClass().equals(Syncategorematic.class)) {
				Object result2[] = standardize_apart((Syncategorematic) current, hashSet, counter);
				params[i] = (Symbol) result2[0];
				counter = (int) result2[1];
				hashSet = (HashSet<Variable>) result2[2];
			}
		}
		
		result[0] = syncategorematic;
		result[1] = counter;
		result[2] = hashSet;
		
		return result;
		
	}
	
	public static Object[] remove_all_there_exist(Syncategorematic input, HashSet<Variable> hashSet, int counter) {
		
		Object[] result = new Object[3];
		
		if (input.symbol_name.equals(SymbolType.FOR_ALL)) {
			hashSet.add(input.variable);
		} else if (input.symbol_name.equals(SymbolType.THERE_EXIST)) {
			if (hashSet.size() > 0) {
				rename_variables_in_there_exist(input, input.variable, hashSet, counter);
			}
			Symbol current_param = input.params[0];
			
			if (current_param.getClass().equals(Syncategorematic.class)) {
				input = (Syncategorematic) current_param;
			} else {
				input.variable = null;
				input.symbol_name = SymbolType.NOTHING;
			}
			
			
			
		}
		
		Symbol[] params = input.params;
		
		for (int i = 0; i < params.length; i++) {
			Symbol current = params[i];
			if (current != null && current.getClass().equals(Syncategorematic.class)) {
				Object[] obj = remove_all_there_exist((Syncategorematic) current, hashSet, counter);
				params[i] = (Symbol) obj[0];
				counter = (int) obj[1];
				hashSet = (HashSet<Variable>) obj[2];
			}
		}
		
		result[0] = input;
		result[1] = counter;
		result[2] = hashSet;
		
		return result;
	}
	
	public static Syncategorematic remove_all_for_all(Syncategorematic input) {
		Syncategorematic result = input;
		
		if (input.symbol_name.equals(SymbolType.FOR_ALL)) {
			result = (Syncategorematic) input.params[0];
		}
	
		Symbol[] params = result.params;
		for (int i = 0; i < params.length; i++) {
			Symbol current = params[i];
			if (current != null && current.getClass().equals(Syncategorematic.class)) {
				params[i] = remove_all_for_all((Syncategorematic) current);
			}
		}
		
		return result;
	}
	
	public static Syncategorematic ClauseForm(String str) {
		Syncategorematic syncategorematic = getSyncategorematicFromString(str);
		
		if (trace_mode) {
			System.out.println("Syncategorematic = \n" + syncategorematic.toString());
		}
		
		syncategorematic = remove_all_if_and_only_if(syncategorematic);
		
		if (trace_mode) {
			System.out.println("Syncategorematic after removing all if_and_only_if = \n" + syncategorematic.toString());
		}
		
		syncategorematic = remove_all_implies(syncategorematic);
		
		if (trace_mode) {
			System.out.println("Syncategorematic after removing all implies = \n" + syncategorematic.toString());
		}
		

		syncategorematic = remove_all_not(syncategorematic);

		if (trace_mode) {
			System.out.println("Syncategorematic after removing all not = \n" + syncategorematic.toString());
		}
		
		Object[] obj = standardize_apart(syncategorematic, new HashSet<Variable>(), 0);
		
		syncategorematic = (Syncategorematic) obj[0];
		
		if (trace_mode) {
			System.out.println("Syncategorematic after removing standardizing apart = \n" + syncategorematic.toString());
		}
		
		Object[] obj2 = remove_all_there_exist(syncategorematic, new HashSet<Variable>(), 0);
		
		
		syncategorematic = (Syncategorematic) obj2[0];
		
		if (trace_mode) {
			System.out.println("Syncategorematic after removing all there_exists = \n" + syncategorematic.toString());
		}
		
		syncategorematic = remove_all_for_all(syncategorematic);
		
		if (trace_mode) {
			System.out.println("Syncategorematic after removing all for_all = \n" + syncategorematic.toString());
		}
		
		return syncategorematic;
		
	}
	
	public static Syncategorematic getSyncategorematicFromString(String str) {
		Syncategorematic result = new Syncategorematic();
		
		
		String str2 = str.trim();
		
		
		if (str2.startsWith(for_all_symbol)) {
			int index_of_bracket = str2.indexOf("[");
			String variable_name = str2.substring(for_all_symbol.length(), index_of_bracket).trim();
			
			Variable variable = new Variable(variable_name);
			
			result.symbol_name = SymbolType.FOR_ALL;
			result.variable = variable;
			
			String remaining_of_str = str2.substring(index_of_bracket + 1, str2.length() - 1).trim();
			
			result.params = new Symbol[]{getSyncategorematicFromString(remaining_of_str)};
			
			
		} else if (str2.startsWith(there_exist_symbol)) {
			int index_of_bracket = str2.indexOf("[");
			String variable_name = str2.substring(there_exist_symbol.length(), index_of_bracket).trim();
			
			Variable variable = new Variable(variable_name);
			
			result.symbol_name = SymbolType.THERE_EXIST;
			result.variable = variable;
			
			String remaining_of_str = str2.substring(index_of_bracket + 1, str2.length() - 1).trim();
			
			result.params = new Symbol[]{getSyncategorematicFromString(remaining_of_str)};
			
			
		} else if (str2.startsWith(implies_symbol)) {
			int index_of_bracket = str2.indexOf("[");
			
			result.symbol_name = SymbolType.IMPLIES;
			
			String remaining_of_str = str2.substring(index_of_bracket + 1, str2.length() - 1).trim();
			
			char c;
			String first_param = "";
			String second_param = "";
			int open_paran = 0;
			int close_paran = 0;
			boolean initialize_first = false;
			int i = 0;
			while(i < remaining_of_str.length()) {
				c = remaining_of_str.charAt(i);
				
				if (c == '(' || c == '[') {
					open_paran++;
				} else if (c == ')' || c == ']') {
					close_paran++;
				} else if (c == ',') {
					if (open_paran == close_paran) {
						if (!initialize_first) {
							initialize_first = true;
							i++;
							continue;
						}
					}
				}
				
				if (!initialize_first) {
					first_param += c;
				} else {
					second_param += c;
				}
				i++;
			}
			
			
			
			
			Symbol[] params = new Symbol[2];
			
			params[0] = getSyncategorematicFromString(first_param.trim());
			params[1] = getSyncategorematicFromString(second_param.trim());
			
			result.params = params;
			
		} else if (str2.startsWith(if_And_only_if_symbol)) {
			int index_of_bracket = str2.indexOf("[");
			
			result.symbol_name = SymbolType.IF_AND_ONLY_IF;
			
			String remaining_of_str = str2.substring(index_of_bracket + 1, str2.length() - 1).trim();
			
			char c;
			String first_param = "";
			String second_param = "";
			int open_paran = 0;
			int close_paran = 0;
			boolean initialize_first = false;
			int i = 0;
			while(i < remaining_of_str.length()) {
				c = remaining_of_str.charAt(i);
				
				if (c == '(' || c == '[') {
					open_paran++;
				} else if (c == ')' || c == ']') {
					close_paran++;
				} else if (c == ',') {
					if (open_paran == close_paran) {
						if (!initialize_first) {
							initialize_first = true;
							i++;
							continue;
						}
					}
				}
				
				if (!initialize_first) {
					first_param += c;
				} else {
					second_param += c;
				}
				i++;
			}
			
			
			
			
			Symbol[] params = new Symbol[2];
			
			params[0] = getSyncategorematicFromString(first_param.trim());
			params[1] = getSyncategorematicFromString(second_param.trim());
			
			result.params = params;
			
		} else if (str2.startsWith(and_symbol)) {
			int index_of_bracket = str2.indexOf("[");
			
			result.symbol_name = SymbolType.AND;
			
			String remaining_of_str = str2.substring(index_of_bracket + 1, str2.length() - 1).trim();
			
			char c;
			ArrayList<String> arr = new ArrayList<String>();
			int index_of_current = 0;
			String current = "";
			int open_paran = 0;
			int close_paran = 0;
			int i = 0;
			while(i < remaining_of_str.length()) {
				c = remaining_of_str.charAt(i);
				
				if (c == '(' || c == '[') {
					open_paran++;
				} else if (c == ')' || c == ']') {
					close_paran++;
				} else if (c == ',') {
					if (open_paran == close_paran) {
						arr.add(index_of_current, current);
						index_of_current++;
						current = "";
						i++;
						continue;
					}
				}
				
				current += c;

				i++;
			}
			
			arr.add(current);
			
			Symbol[] params = new Symbol[arr.size()];
			for (int j = 0; j < arr.size(); j++) {
				params[j] = getSyncategorematicFromString(arr.get(j).trim());
			}
			result.params = params;
		} else if (str2.startsWith(or_symbol)) {
			int index_of_bracket = str2.indexOf("[");
			
			result.symbol_name = SymbolType.OR;
			
			String remaining_of_str = str2.substring(index_of_bracket + 1, str2.length() - 1).trim();
			
			char c;
			ArrayList<String> arr = new ArrayList<String>();
			int index_of_current = 0;
			String current = "";
			int open_paran = 0;
			int close_paran = 0;
			int i = 0;
			while(i < remaining_of_str.length()) {
				c = remaining_of_str.charAt(i);
				
				if (c == '(' || c == '[') {
					open_paran++;
				} else if (c == ')' || c == ']') {
					close_paran++;
				} else if (c == ',') {
					if (open_paran == close_paran) {
						arr.add(index_of_current, current);
						index_of_current++;
						current = "";
						i++;
						continue;
					}
				}
				
				current += c;

				i++;
			}
			
			arr.add(current);
			
			Symbol[] params = new Symbol[arr.size()];
			for (int j = 0; j < arr.size(); j++) {
				params[j] = getSyncategorematicFromString(arr.get(j).trim());
			}
			result.params = params;
			
		} else if (str2.startsWith(not_symbol)) {
			int index_of_bracket = str2.indexOf("[");
			
			result.symbol_name = SymbolType.NOT;
			
			String remaining_of_str = str2.substring(index_of_bracket + 1, str2.length() - 1).trim();
			
			result.params = new Symbol[]{getSyncategorematicFromString(remaining_of_str)};
			
			
		} else {
			Symbol s = Engine.makeASymbol(str2, true);
			result.symbol_name = SymbolType.NOTHING;
			result.params = new Symbol[]{s};
		}
		
		
		
		return result;
	}
	
	public static boolean noSyncategorematicInside(Syncategorematic input) {
		Symbol[] params = input.params;
		
		for (Symbol param: params) {
			if (param.getClass().equals(Syncategorematic.class)) {
				Syncategorematic current = (Syncategorematic) param;
				if (!current.symbol_name.equals(SymbolType.NOTHING) && !current.symbol_name.equals(SymbolType.NOT)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public static Syncategorematic translate_and_contains_or_and_and(Syncategorematic input) {
		Syncategorematic result = input;
		
		// the root is AND
		
		ArrayList<Syncategorematic> free_arr = new ArrayList<Syncategorematic>();
		ArrayList<Syncategorematic> or_arr = new ArrayList<Syncategorematic>();
		ArrayList<Syncategorematic> and_arr = new ArrayList<Syncategorematic>();
		
		ArrayList<Syncategorematic> new_params = new ArrayList<Syncategorematic>();
		
		if (input.symbol_name.equals(SymbolType.AND)) {
			Symbol[] params = input.params;
			for (int i = 0; i < params.length; i++) {
				Syncategorematic current = (Syncategorematic) params[i];
				if (current.symbol_name.equals(SymbolType.AND)) {
					and_arr.add(current);
				} else if (current.symbol_name.equals(SymbolType.OR)) {
					or_arr.add(current);
				} else {
					free_arr.add(current);
				}
			}
		}
		
		for (int i = 0; i < and_arr.size(); i++) {
			Syncategorematic current = and_arr.get(i);
			Symbol [] params = current.params;
			for (Symbol param: params) {
				free_arr.add((Syncategorematic) param);
			}
		}
		
		
		if (or_arr.isEmpty()) {
			input.params = (Symbol[]) free_arr.toArray();
		} else {
			for (Syncategorematic s: or_arr) {
				Syncategorematic temp = new Syncategorematic();
				temp.symbol_name = SymbolType.AND;
				//temp.params = {};
			}
		}
		
		return result;
	}
	
	
	
	public static String print_the_final_output (Syncategorematic input) {
		String str = "";
		
		if (input.symbol_name.equals(SymbolType.OR)) {
			str += "{";
			for (Symbol s:input.params) {
				str += " { " + print_the_final_output((Syncategorematic) s) + " },";
			}
			str = str.substring(0, str.length() - 1);
			str += "}";
		} else if (input.symbol_name.equals(SymbolType.AND)) {
			for (Symbol s:input.params) {
				str += s.toString() + ", ";
			}
			str = str.substring(0, str.length() - 2);
		}
		
		
		return str;
	}
	
	
	
	
	
}
