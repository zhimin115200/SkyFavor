package com.zhimin115200.test.SkyFavor.persistent.impl;

import com.zhimin115200.test.SkyFavor.persistent.BaseDao;
import com.zhimin115200.test.SkyFavor.persistent.DemoDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.Student;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDaoImpl extends BaseDao<Student> implements DemoDao {
	
	public DemoDaoImpl() {
        super();
        setClazz(Student.class);
    }

	@Override
	public String test() {
		return this.findById(1).getName();
	}

}
