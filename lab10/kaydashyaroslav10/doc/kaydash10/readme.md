Лабораторна робота № 10. Обробка параметризованих контейнерів.

Мета: Розширення функціональності параметризованих класів.
1 ВИМОГИ :
1.1 Розробник :
-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  1-05-2023. 

 1.2 Загальне завдання :
	Розробити параметризовані методи (Generetic Methods) для обробки колекцій об'єктів згідно прикладної задачі.
	Продемонструвати розроблену функціональність (створення, управління та обробку власних контейнерів) в діалоговому та автоматичному режимах.
	Автоматичний режим виконання програми задається параметром командного рядка -auto. Наприклад, java ClassName -auto.
	В автоматичному режимі діалог з користувачем відсутній, необхідні данні генеруються, або зчитуються з файлу.
	Забороняється використання алгоритмів з Java Collections Fraemork.


1.3 Індивідуальне завдання :
	Довідник покупця.Сортування за назвою, за містом в адресі, за спеціалізацією.

2 ОПИС ПРОГРАМИ :
	Программа бере список та добавляє туди обє'кти TradingPoint, потім сортирує список по значеняям з TradingPoint.

2.1 Засоби ООП:
	import java.beans.XMLDecoder;
	import java.beans.XMLEncoder;
	import java.io.*;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.*;

2.2 Ієрархія та структура класів  :
	Має тількі один класс.
   
2.3 Важливі фрагменти програми:


	public class Main {
		
		public static class TradingPoint implements Serializable {
			
			private String name;
			
			private String address;
			
			private List<String> phones;
		   
			private String specialization;
			
			private LocalDateTime startTime;
			
			private LocalDateTime endTime;


			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public List getPhones() {
				return phones;
			}

			public void setPhones(List<String> phones) {
				this.phones = phones;
			}

			public String getSpecialization() {
				return specialization;
			}

			public void setSpecialization(String specialization) {
				this.specialization = specialization;
			}

			@Override
			public String toString() {
				return "TradingPoint{" +
						"name='" + name + '\'' +
						", address='" + address + '\'' +
						", phones=" + phones +
						", specialization='" + specialization + '\'' +
						", work time= " + startTime.getDayOfWeek() + " - " + endTime.getDayOfWeek() +
						startTime.format(DateTimeFormatter.ofPattern(" HH:mm ")) + "-" +
						endTime.format(DateTimeFormatter.ofPattern(" HH:mm "))  + '}';
			}

			public LocalDateTime getStartTime() {
				return startTime;
			}

			public void setStartTime(LocalDateTime startTime) {
				this.startTime = startTime;
			}

			public LocalDateTime getEndTime() {
				return endTime;
			}

			public void setEndTime(LocalDateTime endTime) {
				this.endTime = endTime;
			}

			public TradingPoint(String name, String address, List<String> phones, String specialization, LocalDateTime startTime, LocalDateTime endTime) {
				this.name = name;
				this.address = address;
				this.phones = phones;
				this.specialization = specialization;
				this.startTime = startTime;
				this.endTime = endTime;
			}

			public TradingPoint() {
				this.name = "no name";
				this.address = "no address";
				this.phones = List.of("no phones");
				this.specialization = "no specialization";
				this.startTime = LocalDateTime.now();
				this.endTime = LocalDateTime.now();
			}
		}


		public static class LinkedListContainer<T> implements Iterable<T>, Serializable {
			
			private Node<T> head;
		   
			private int size;

			
			public LinkedListContainer() {
				head = null;
				size = 0;
			}
			
			public Node<T> getHead() {
				return head;
			}

			public void setHead(Node<T> head) {
				this.head = head;
			}

			public static class Node<T> implements Serializable {
				private T data;
				private Node<T> next;

				public Node(T data) {
					this.data = data;
					next = null;
				}

				public Node() {

				}
			}

			public int size() {
				return size;
			}

			public boolean isEmpty() {
				return size == 0;
			}
	 contains(Object o) {
				for (T item : this) {
					if (item.equals(o)) {
						return true;
					}
				}
				return false;
			}

		   
			public Iterator<T> iterator() {
				return new LinkedListIterator();
			}

		   
			class LinkedListIterator implements Iterator<T> {
			   
				private Node<T> current;

				public LinkedListIterator() {
					current = head;
				}

				@Override
				public boolean hasNext() {
					return current != null;
				}

				@Override
				public T next() {
					T data = current.data;
					current = current.next;
					return data;
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			}

			public TradingPoint[] toArray() {
				TradingPoint[] arr = new TradingPoint[size];
				int i = 0;
				for (T item : this) {
					arr[i++] = (TradingPoint) item;
				}
				return arr;
			}

			public boolean add(T item) {
				Node<T> newNode = new Node<>(item);
				if (head == null) {
					head = newNode;
				} else {
					Node<T> current = head;
					while (current.next != null) {
						current = current.next;
					}
					current.next = newNode;
				}
				size++;
				return true;
			
			public boolean remove(Object o) {
				if (head == null) {
					return false;
				}
				if (head.data.equals(o)) {
					head = head.next;
					size--;
					return true;
				}
				Node<T> current = head;
				while (current.next != null && !current.next.data.equals(o)) {
					current = current.next;
				}
				if (current.next != null) {
					current.next = current.next.next;
					size--;
					return true;
				}
				return false;
			}

			 public void clear() {
				head = null;
				size = 0;
			}

	   сериализацию(Serialisation)
			public void saveToFile(String fileName) {
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
					oos.writeObject(this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public static LinkedListContainer<TradingPoint> loadFromFile(String fileName) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
					return (LinkedListContainer<TradingPoint>) ois.readObject();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				return null;
			}

			public void saveToFileXml() {
				try {
					BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("safe.xml"));
					XMLEncoder encoder = new XMLEncoder(fos);
				   
					encoder.writeObject(this);
					encoder.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public static LinkedListContainer<TradingPoint> loadFromFileXml() {
				try {
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream("safe.xml"));
					XMLDecoder decoder = new XMLDecoder(bis);
			   LinkedListContainer<TradingPoint> appleCollection = (LinkedListContainer<TradingPoint>) decoder.readObject();
					decoder.close();
					bis.close();
					return appleCollection;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		  
			public void sortByTradingPoint(Comparator<TradingPoint> comparator) {
			   
				TradingPoint[] arr = this.toArray();
				
				Arrays.sort(arr, comparator);
				clear();
				for (TradingPoint tp : arr) {
					add((T) tp);
				}
			}
		}

		static TradingPoint point1 = new TradingPoint("some name", "some address", List.of("0000"), "some thing",
				LocalDateTime.of(2004, 3, 3, 9, 31),
				LocalDateTime.of(2010, 7, 4, 9, 30));
		static TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("123", "321"), "cheese",
				LocalDateTime.of(2002, 3, 1, 10, 30),
				LocalDateTime.of(2003, 4, 5, 18, 0));
		static TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("999"), "apples",
				LocalDateTime.of(2000, 5, 1, 6, 30),
				LocalDateTime.of(2022, 6, 5, 10, 45));

		private static void automatic(LinkedListContainer<TradingPoint> listContainer){

			for (TradingPoint tradingPoint : listContainer) {
				System.out.println(tradingPoint);
			}
			listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
				@Override
				public int compare(TradingPoint tp1, TradingPoint tp2) {
					return tp1.getName().compareTo(tp2.getName());
				}
			});

			System.out.println("After sorting by name");

			for (TradingPoint tradingPoint : listContainer) {
				System.out.println(tradingPoint);
			}

			listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
				@Override
				public int compare(TradingPoint tp1, TradingPoint tp2) {
					return tp1.getAddress().compareTo(tp2.getAddress());
				}
			});

			System.out.println("After sorting by address");

			for (TradingPoint tradingPoint : listContainer) {
				System.out.println(tradingPoint);
			}

			listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
				@Override
				public int compare(TradingPoint tp1, TradingPoint tp2) {
					return tp1.getSpecialization().compareTo(tp2.getSpecialization());
				}
			});

			System.out.println("After sorting by specialization");

			for (TradingPoint tradingPoint : listContainer) {
				System.out.println(tradingPoint);
			}

		}
		public static void main(String[] args) {
			LinkedListContainer<TradingPoint> listContainer = new LinkedListContainer<>();

			listContainer.add(point1);
			listContainer.add(point2);
			listContainer.add(point3);

		
			boolean isEnd = true;

			if (args.length > 0){
				if (Objects.equals(args[0], "-auto")){
					automatic(listContainer);
					isEnd = false;
				}
			}

			if (isEnd) {
				System.out.println("1 - print list");
				System.out.println("2 - sort list by name");
				System.out.println("3 - sort list by address");
				System.out.println("4 - sort list by specialization");
				System.out.println("5 - end");
			}

			Scanner myInput1 = new Scanner(System.in);

			while (isEnd) {
				String numberImp = myInput1.nextLine();
			  
				switch (numberImp) {
					case ("1"):
						for (TradingPoint tradingPoint : listContainer) {
							System.out.println(tradingPoint);
						}
						break;
					case ("2"):
						 listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
							@Override
							public int compare(TradingPoint tp1, TradingPoint tp2) {
								return tp1.getName().compareTo(tp2.getName());
							}
						});
						break;
					case ("3"):
					   listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
							@Override
							public int compare(TradingPoint tp1, TradingPoint tp2) {
								return tp1.getAddress().compareTo(tp2.getAddress());
							}
						});
						break;
					case ("4"):
						listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
							@Override
							public int compare(TradingPoint tp1, TradingPoint tp2) {
								return tp1.getSpecialization().compareTo(tp2.getSpecialization());
							}
						});
						break;
					case ("5"):
						isEnd = false;
						System.out.println("end");
						break;
				 
					default:
						System.out.println("Enter one of the numbers from 1 to 5 for the correct robot");
				}
			}
		}
	}

	 
 
3 ВАРІАНТИ ВИКОРИСТАННЯ:

	- дебагер lldb;
	- консоль;
	- дебагер gdb;
 
ВИСНОВОК:
	При виконанні даної лабораторної роботи було набуто практичного досвіду з розширенням функціональності параметризованих класів.