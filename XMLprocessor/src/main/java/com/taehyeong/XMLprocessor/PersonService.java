package com.taehyeong.XMLprocessor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final XmlStorageDao xmlStorageDao;
//    public Person getPerson(String id) {}

    public void savePerson() throws Exception {

        Person person1 = new Person();
        person1.setName("asdf");
        person1.setAge(20);

        xmlStorageDao.insertPerson(person1);

    }

    public Person getPerson(int id) throws Exception {

        return xmlStorageDao.getPersonById(id);

    }


}

