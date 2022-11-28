Лабораторна робота № 1.Структура програми мовою Java. Типи даних, літерали, операції і оператори.

        
Мета: Ознайомлення з JDK платформи Java SE та середовищем розробки Eclipse IDE.
           

1 ВИМОГИ :
1.1 Розробник 

-  Кайдаш Ярослав 
-  студент групи КН-921Д; 
-  28-11-2022. 

1.2 Загальне завдання :

Вирішити три прикладні задачі на мові Java в середовищі Eclipse.

Продемонструвати покрокове виконання програми та результати роботи в режимі налагодження, не використовуючи виведення до консолі.

Виконати компіляцію і запуск програми в командному рядку за допомогою відповідних утиліт JDK.


1.3 Індивідуальне завдання 	:

Обрати тип змінних та встановити за допомогою констант та літералів початкові значення:
 число, що відповідає номеру залікової книжки за допомогою шістнадцяткового літералу;
 число, що відповідає номеру мобільного телефона (починаючи з 380...) за допомогою десяткового літералу;
 число, яке складається з останніх двох ненульових цифр номера мобільного телефону за допомогою двійкового літералу;
 число, яке складається з останніх чотирьох ненульових цифр номера мобільного телефону за допомогою вісімкового літералу;
 визначити збільшене на одиницю значення залишку від ділення на 26 зменшеного на одиницю номера студента в журналі групи;
 символ англійського алфавіту в верхньому регістрі, номер якого відповідає знайденому раніше значенню.

Використовуючи десятковий запис цілочисельного значення кожної змінної знайти і підрахувати кількість парних і непарних цифр.

Використовуючи двійковий запис цілочисельного значення кожної змінної підрахувати кількість одиниць.

 
2 ОПИС ПРОГРАМИ :

	Однакова задача для всіх.

2.1 Засоби ООП:

	Java code convention;
	JDK;

2.2 Ієрархія та структура класів :

	Має тількі один клас.
   
2.3 Важливі фрагменти програми:

private static void countEvenAndOdd() {
	        int even = 0;
	        int odd = 0;
	        //loop through each element of the array
	        for (int i = 0; i < arr.length; i++) {
	            if (arr[i] % 2 == 0) {
	            	even++; //Write down an even number
	            } else {
	                odd++;//Write an odd number
	            }
	        }
	        
	        /*System.out.println("Even numbers:" + even);
	        System.out.println("Odd numbers:" + odd);*/

////////////////////////////////////////////////////////////////////////////

    private static void numberOfOne() {
	        int count = 0;
	        
	        //loop through each element of the array
	        for (int i = 0; i < arr.length; i++) {
	        		//create a variable for subtracting units in the array element numbered [i] To do this, we use the Long.toBinaryString() method, which will return a string that consists of 2 form numbers on this line we use the replaceAll () method, the input parameters of which are regEx and the value to be replaced as a string at the end, we get a string consisting of only 1; we call the length () method and find the length of the string
	            int oneCount = Long.toBinaryString(arr[i]).replaceAll("0", "").length();
	            	//sum the total number of units (count) and the number of units of the given element (oneCount)
	            count += oneCount;
	        }
	        /*System.out.println("Number of units:" + count);*/
	    }
 
 
 
 
3. ВАРІАНТИ ВИКОРИСТАННЯ

- дебагер lldb;
- консоль;
- дебагер gdb;
 
 
ВИСНОВОК 
При виконанні даної лабораторної роботи я ознайомився з JDK платформи Java SE та середовищем розробки Eclipse IDE, та було виконано надане завдання.