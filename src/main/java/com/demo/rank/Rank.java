package com.demo.rank;

import com.demo.pojo.Person;

import java.util.List;

/**
 * 人员排序接口
 */
public interface Rank {

    public List<Person> getSortedPerson(List<Person> personList);

}
