import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
	
	public static void test_unify() {
		Engine.trace_mode = true;
		HashMap hm = Engine.unify("P(x,g(x),g(f(a)))", "P(f(u),v,v)");
		if (hm != null) {
			int x = hm.size();
			System.out.println(x);

			Iterator it = hm.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		   }
		} else {
			System.out.println("null");
		}
		System.out.println(".........");

		Engine.trace_mode = false;
		hm = Engine.unify("P(a,y,f(y))", "P(z,z,u)");
		if (hm != null) {
			int x = hm.size();
			System.out.println(x);

			Iterator it = hm.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		   }
		} else {
			System.out.println("null");
		}
		System.out.println(".......");
		
		hm = Engine.unify("f(x,g(x),x)", "f(g(u),g(g(z)),z)");
		if (hm != null) {
			int x = hm.size();
			System.out.println(x);

			Iterator it = hm.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		   }
		} else {
			System.out.println("null");
		}
	}

	
	public static void test_cnf() {
		String str = Engine2.for_all_symbol + "x[" + Engine2.and_symbol + "[" + "p(x) , f(x)"  + "]"+ "]";
		//∃x[P(x)∧∀y[Q(y)⇒¬P(x)]]
		String str2 = Engine2.there_exist_symbol + "x[" + Engine2.and_symbol + "[ p(x)," + Engine2.for_all_symbol + "x[" + Engine2.implies_symbol + "[" +
	    "Q(x)," + Engine2.not_symbol + "[" + "p(x)"	+ "]"+ "]" + "]" + "]" + "]";
		 Syncategorematic s = Engine2.ClauseForm(str2);
		 System.out.println(s);
		 
		 
		 
		 
		 
		 // ∀x[P (x) ⇔ (Q(x) ∧ ∃y[Q(y) ∧ R(y, x)])]
		 
		 System.out.println("........");
		 
		String str3 = Engine2.for_all_symbol + "x[" + 
		 Engine2.if_And_only_if_symbol + "[ p(x) , " + 
				Engine2.and_symbol + "[ Q(x) , " + 
				Engine2.there_exist_symbol + "y[" +
				Engine2.and_symbol + "[ Q(y) , R(y, x)"
				+ "]]]]]";
		Engine2.trace_mode = true;
		 Syncategorematic s2 = Engine2.ClauseForm(str3);
		 if (s2 != null) {
			 System.out.println(s2);
		 }
	}
	
	
	public static void main(String[] args) {
		test_unify();
		//test_cnf();
		
	}
	
	
}
