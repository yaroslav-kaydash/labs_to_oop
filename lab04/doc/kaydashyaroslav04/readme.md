Лабораторна робота № 4.Інтерактивні консольні програми для платформи JavaSE.

        
Мета: Реалізація діалогового режиму роботи з користувачем в консольних програмах мовою Java.
     

1 ВИМОГИ 
1.1 Розробник 

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  29-11-2022. 

1.2 Загальне завдання 

Використовуючи програму рішення завдання лабораторної роботи №3, відповідно до прикладної задачі, забезпечити обробку команд користувача у вигляді текстового меню:

введення даних;
перегляд даних;
виконання обчислень;
відображення результату;
завершення програми і т.д.

Забезпечити обробку параметрів командного рядка для визначення режиму роботи програми:

параметр "-h" чи "-help": відображається інформація про автора програми, призначення (індивідуальне завдання), детальний опис режимів роботи (пунктів меню та параметрів командного рядка);
параметр "-d" чи "-debug": в процесі роботи програми відображаються додаткові дані, що полегшують налагодження та перевірку працездатності програми: діагностичні повідомлення, проміжні значення змінних, значення тимчасових змінних та ін.

1.3 Індивідуальне завдання 

-

2 ОПИС ПРОГРАМИ 
Моя программа виводить команди для користувача, після чого він вибирає яку йому використовувати.

2.1 Засоби ООП:
Java code convention;
JDK;
java.util.Objects;
java.util.Scanner.


2.2 Ієрархія та структура класів  :
Має тількі один класс.
   
2.3 Важливі фрагменти програми

private static String str = "";

static void removeSigns() {
      StringBuilder stringBuilder = new StringBuilder();
 variable we make an array char
        char[] chars = str.toString().toCharArray();

      for (int i = 0; i < chars.length; i++) {
         Character.isAlphabetic() - returns true if character(char) is a letter
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

            if (i % 2 == 0 && chars[i] != chars[i + 1] || Character.isAlphabetic(chars[i])) {
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
            // add space
            if (Character.isAlphabetic(chars[i + 1]) && chars[i] == ',') {
                stringBuilder.append(" ");
            }
        }
        str = stringBuilder.toString();
    }

  static void fillString(String s) {
        str = s;
    }


    public static void main(String[] args) {
...
}

 
 
 
3. ВАРІАНТИ ВИКОРИСТАННЯ:
- дебагер lldb;
- консоль;
- дебагер gdb;
 
 
 
ВИСНОВОК
При виконанні даної лабораторної роботи було набуто практичного досвіду з реалізацією діалогового режиму роботи з користувачем в консольних програмах мовою Java.