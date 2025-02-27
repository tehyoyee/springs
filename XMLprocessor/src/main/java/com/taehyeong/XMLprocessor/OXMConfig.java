package com.taehyeong.XMLprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

@Configuration
public class OXMConfig {

    @Bean
    public Marshaller jaxbMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // 변환할 클래스들을 지정합니다.
        marshaller.setClassesToBeBound(Person.class);
        return marshaller;
    }

    @Bean("unmarshaller")
    public Unmarshaller jaxbUnmarshaller() {
        return (Unmarshaller) jaxbMarshaller();
    }
}
