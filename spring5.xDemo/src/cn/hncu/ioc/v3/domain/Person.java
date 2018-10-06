package cn.hncu.ioc.v3.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* 为演示property标签如何处理各种类型的数据
 */
public class Person {
	private String name;
	private Integer age;
	private MyDate birth;
	private List<String> list;
	private Set<String> set;
	private Map<String, Object> map;
	private Object[] objs;
	
	public Person() {
	}
	

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", birth=" + birth + ", list=" + list + ", set=" + set
				+ ", map=" + map + ", objs=" + Arrays.toString(objs) + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public MyDate getBirth() {
		return birth;
	}

	public void setBirth(MyDate birth) {
		this.birth = birth;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}
	
	
	
}
