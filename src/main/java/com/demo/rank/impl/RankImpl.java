package com.demo.rank.impl;

import com.demo.pojo.Person;
import com.demo.rank.Rank;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 人员排序接口实现类
 */
@Component
public class RankImpl implements Rank {
    /**
     * 创建一个map集合用于存放职位的排名，方便后期扩展
     * key 代表职位
     * value 代表该职位的排名
     * value越低，职位越靠前
     */
    private static Map<String, Integer> rankList;
    static {
        rankList = new HashMap<String, Integer>();
        rankList.put("董事长", 1);
        rankList.put("副董事长", 5);
        rankList.put("执行董事", 10);
        rankList.put("监事", 15);
        rankList.put("其他", 50);
        rankList.put(null, 100);
    }

    /**
     * 人员职位的排序方法
     * 使用treemap作为排序集合，将拥有相同排名的人员放入同一个集合中。
     * 最后依次将所有集合整合并返回
     * @param personList
     * @return
     */
    public List<Person> getSortedPerson(List<Person> personList) {
        TreeMap<Integer, List<Person>> totalList = new TreeMap<Integer, List<Person>>();

        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            Integer highestPosition = getHighestPosition(person);
            if (totalList.get(highestPosition) == null){
                totalList.put(highestPosition, new ArrayList<Person>());
            }
            totalList.get(highestPosition).add(person);
        }

        List<Person> result = new ArrayList<Person>();
        Iterator<Map.Entry<Integer, List<Person>>> iterator = totalList.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, List<Person>> next = iterator.next();
            result.addAll(next.getValue());
        }

        return result;
    }


    /**
     * 获得当前人员的最高职位
     * @param person
     * @return
     */
    private Integer getHighestPosition(Person person){
        if(person.getName() == null){
            return rankList.get(null);
        }

        String[] positions = person.getPosition().split(",");
        Integer highestRank = Integer.MAX_VALUE;
        for (int i = 0; i < positions.length; i++) {
            String position = positions[i].trim();
            Integer rank =  rankList.get(position);
            if(rank == null){
                rank = rankList.get("其他");
            }
            if(rank < highestRank){
                highestRank = rank;
            }
        }

        return highestRank;
    }

}