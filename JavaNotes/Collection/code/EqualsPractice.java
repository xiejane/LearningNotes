/*
* 编写 equals 方法的练习
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EqualsPractice {
	public static void main(String[] args){
		List<Person> list = new ArrayList<>();
		list.add(new Person("Xiao","Ming",18));
		list.add(new Person("Xiao","Hone",25));
		list.add(new Person("Bob","Smith",20));
		boolean exits = list.contains(new Person("Bob","Smith",20));
		System.out.println(exits?"Success!":"Fail"	);
	}
}
class Person{
	String firstName;
	String lastName;
	int age;
	public Person(String firstName, String lastName, int age){
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean equals(Object o){
		if (o instanceof  Person){
			Person p = (Person) o;
			return Objects.equals(this.firstName,p.firstName) &&
					Objects.equals(this.lastName,p.lastName) &&
					this.age == p.age;
		}
		return false;
	}
}
