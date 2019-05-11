
import java.util.ArrayList;
import java.util.HashMap;

public class Speller {

	static HashMap<String, ArrayList<String>> spellCheck(String s) {
		ResultHandlerDOM rhd = new ResultHandlerDOM();
		return rhd.handResult(s);
	}

	static HashMap<String, ArrayList<String>> spellCheck2(String s) {
		ResultHandlerDOM rhd = new ResultHandlerDOM();
		rhd.handResult(s); // test1
		System.out.println(rhd.resultCluster); // test1
		return rhd.handResult(s);
	}

	public static void main(String[] args) {
		/*
		 * long l = System.nanoTime(); System.out.println(Speller.spellCheck2(
		 * "http://fb.ru/post/environment/2019/5/5/94242")); long l2 = System.nanoTime()
		 * - l; System.out.println(l2);
		 */
		int[] i = { 1, 2, 3, 4 };
		System.out.println(i.length);

	}
}
//ЧТОБЫ ПОВЫСИТЬ ТОЧНОСТЬ СЛОВА ДОЛЖНЫ ИДТИ В ТОМ ПОРЯДКЕ, В КАКОМ ОН ИДУТ В ТЕКСТЕ
//Для этого я изменил контейнер на LinkedHashSet, но все равно надо проверять верно ли все работает
// обязательно надо вводить протокол (но это можно обработать). Программа не обрабатывает редиректы