Лабораторна робота № 3.Утилітарни класи.Обробка масивів і рядків.

        
Мета: Розробка власних утилітарних класів; набуття навичок вирішення прикладних задач з використанням масивів і рядків.

     

1 ВИМОГИ 
1.1 Розробник 

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  29-11-2022. 

1.2 Загальне завдання 

Розробити та продемонструвати консольну програму мовою Java в середовищі Eclipse для вирішення прикладної задачі за номером, що відповідає збільшеному на одиницю залишку від ділення на 15 зменшеного на одиницю номера студента в журналі групи.

При вирішенні прикладних задач використовувати латинку.

Продемонструвати використання об'єктів класу StringBuilder або StringBuffer.

Застосувати функціональну (процедурну) декомпозицію - розробити власні утилітарні класи (особливий випадок допоміжного класу, див. Helper Class) та для обробки даних використовувати відповідні статичні методи.

Забороняється використовувати засоби обробки регулярних виразів: класи пакету java.util.regex (Pattern, Matcher та ін.), а також відповідні методи класу String (matches, replace, replaceFirst, replaceAll, split).




1.3 Індивідуальне завдання 

Ввести текст. З тексту видалити всі символи, крім пропусків, які не є буквами. Пропуски, що повторюються, замінити на одиночні. Між послідовностями літер, де знаходяться розділові знаки, залишити хоча б один пропуск ("a,b,c" -> "a, b, c"). Вивести початковий текст та результат.

 
2 ОПИС ПРОГРАМИ 

Моя программа видаляє усі символи, крім пропусків, проте якщо вони повторюються вона також видаляю один пропуск, між знаками залишає пропуск.

2.1 Засоби ООП:
Java code convention;
JDK;
java.util.Scanner.


2.2 Ієрархія та структура класів  :
Має тількі один класс.
   
2.3 Важливі фрагменти програми:
static void removeSigns() {

        StringBuilder stringBuilder = new StringBuilder();

        char[] chars = str.toString().toCharArray();


        for (int i = 0; i < chars.length; i++) {

            if (Character.isAlphabetic(chars[i]) || chars[i] == ' ' || chars[i] == ',') {
                stringBuilder.append(chars[i]);
            }
        }
        str = stringBuilder.toString();
    }

   
    static void removeDuplicate() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toString().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                break;
            }
            if (chars[i] != chars[i + 1] || Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
            }
        }
        str = stringBuilder.toString();
    }

    static void depthBeetwen() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toString().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                break;
            }
            stringBuilder.append(chars[i]);
            //add space
            if (Character.isAlphabetic(chars[i + 1]) && chars[i] == ',') {
                stringBuilder.append(" ");
            }
        }
        str = stringBuilder.toString();
    }

    
    static void fillString(String s) {
        str = s;
    }
 
 
 
3. ВАРІАНТИ ВИКОРИСТАННЯ:
- дебагер lldb;
- консоль;
- дебагер gdb;
 
 
 
ВИСНОВОК:
При виконанні даної лабораторної роботи було набуто практичного досвіду з розробки власник утилітарних класів та вирішенням прикладних задач з використанням масивів і рядків.