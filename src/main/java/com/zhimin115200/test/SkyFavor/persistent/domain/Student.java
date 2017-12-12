package com.zhimin115200.test.SkyFavor.persistent.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student", catalog = "test")
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected long id;

	@Column(name = "name", nullable = true)
	private String name;
	@Column(name = "age", nullable = true)
	private Integer age;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
