
import java.util.ArrayList;
import java.util.HashMap;

public class Speller {

	static HashMap<String, ArrayList<String>> spellCheck(String s) {
		ResultHandlerDOM rhd = new ResultHandlerDOM();
		return rhd.handResult(s);
	}

	public static void main(String[] args) {
		long l = System.nanoTime();
		System.out.println(Speller.spellCheck("https://glloss.ru/"));
		long l2 = System.nanoTime() - l;
		System.out.println(l2);

	}
}
//НЕ УВИДЕЛ СЛОВО "ДЕШЕВ". ПРОВЕРИТЬ ЕГО. СПЕЛЛЕР ДАННОЕ СЛОВО ЗА ОШИБКУ ПРИНИМАЕТ ТОЛЬКО В КОНТЕКСТЕ
// обязательно надо вводить протокол (но это можно обработать). Программа не обрабатывает редиректы