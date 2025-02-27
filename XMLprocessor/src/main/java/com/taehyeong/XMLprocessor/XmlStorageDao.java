package com.taehyeong.XMLprocessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.Marshaller;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Repository
public class XmlStorageDao {

    private final JdbcTemplate jdbcTemplate;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlStorageDao(JdbcTemplate jdbcTemplate, Marshaller marshaller, Unmarshaller unmarshaller) {
        this.jdbcTemplate = jdbcTemplate;
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    // Create: Person 객체를 XML로 마샬링하여 새로운 레코드를 삽입
    public int insertPerson(Person person) throws Exception {
        String xmlData = marshalPersonToXml(person);
        String sql = "INSERT INTO xml_storage (xml_data) VALUES (?)";
        jdbcTemplate.update(sql, xmlData);
        // 삽입된 마지막 ID를 가져오는 방식 (환경에 따라 다르게 처리 가능)
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    // Read: 특정 ID의 레코드를 읽어 Person 객체로 언마샬링
    public Person getPersonById(int id) throws Exception {
        String sql = "SELECT xml_data FROM xml_storage WHERE id = ?";
        try {
            String xmlData = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
            return unmarshalXmlToPerson(xmlData);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Update: Person 객체를 XML로 변환하여 기존 레코드를 업데이트
    public void updatePerson(int id, Person person) throws Exception {
        String xmlData = marshalPersonToXml(person);
        String sql = "UPDATE xml_storage SET xml_data = ? WHERE id = ?";
        jdbcTemplate.update(sql, xmlData, id);
    }

    // Delete: 해당 ID의 레코드를 삭제
    public void deletePerson(int id) {
        String sql = "DELETE FROM xml_storage WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Person 객체를 XML 문자열로 마샬링하는 헬퍼 메서드
    private String marshalPersonToXml(Person person) throws Exception {
        StringWriter writer = new StringWriter();
        marshaller.marshal(person, new StreamResult(writer));
        return writer.toString();
    }

    // XML 문자열을 Person 객체로 언마샬링하는 헬퍼 메서드
    private Person unmarshalXmlToPerson(String xmlData) throws Exception {
        StringReader reader = new StringReader(xmlData);
        return (Person) unmarshaller.unmarshal(new StreamSource(reader));
    }
}
