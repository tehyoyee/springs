package com.taehyeong.XMLprocessor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class XmlController {

    private final PersonService personService;

    @GetMapping
    public void createXml() throws Exception {

        personService.savePerson();


    }

    @GetMapping("/{personId}")
    public void getPerson(@PathVariable("personId") Integer personId) throws Exception {

        System.out.println(personService.getPerson(personId).toString());

    }



}
