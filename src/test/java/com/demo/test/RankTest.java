package com.demo.test;

import com.demo.pojo.Person;
import com.demo.rank.Rank;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application.xml" })
public class RankTest {

    @Autowired
    private Rank rank;

    @Test
    public void getSortedPerson() {
        //测试数据
        Person dsz = new Person("董事长", "董事长");
        Person fdsz = new Person("副董事长", "副董事长");
        Person zxds = new Person("执行董事", "执行董事");
        Person zxds2 = new Person("执行董事", "经理, 监事, 执行董事");
        Person js = new Person("监事", "监事");
        Person jl = new Person("经理", "经理");
        Person noPosition = new Person();

        List<Person> testList = new ArrayList<Person>();
        testList.add(noPosition);
        testList.add(dsz);
        testList.add(js);
        testList.add(zxds);
        testList.add(fdsz);
        testList.add(zxds2);
        testList.add(jl);

        //执行方法
        List<Person> methodList = rank.getSortedPerson(testList);

        //正确结果
        List<Person> resultList = new ArrayList<Person>();
        resultList.add(dsz);
        resultList.add(fdsz);
        resultList.add(zxds);
        resultList.add(zxds2);
        resultList.add(js);
        resultList.add(jl);
        resultList.add(noPosition);

        for(int i = 0; i < resultList.size(); i++){
            Assert.assertEquals(resultList.get(i), resultList.get(i));
        }
    }



}
