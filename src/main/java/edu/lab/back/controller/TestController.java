package edu.lab.back.controller;

import edu.lab.back.dtoPojos.response.ErrorMessagePojo;
import edu.lab.back.service.email.EmailComponent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
public class TestController {

    @NonNull
    private EmailComponent emailComponent;

    @RequestMapping(
        value = "/{arg}",
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ErrorMessagePojo test(
        @PathVariable("arg") final String arg
    ) throws IOException, JAXBException {
//        final JacksonXmlModule module = new JacksonXmlModule();
//        final XmlMapper mapper = new XmlMapper(module);
//        mapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
//        mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
////        mapper.registerModule(Module);
//
        ErrorMessagePojo res = new ErrorMessagePojo("test");
////        final String resStr = mapper.writeValueAsString(res);
//        Logger log = Logger.getLogger("test");
////        log.info(resStr);
//
//        final JAXBContext context = JAXBContext.newInstance(ErrorMessagePojo.class);
//        final Marshaller marshaller = context.createMarshaller();
//
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
//            "<?xml-stylesheet type='text/xsl' href='nameoffile.xsl' ?>");
//
//        final ByteArrayOutputStream os = new ByteArrayOutputStream();
//        marshaller.marshal(res, os);
//        final String resStr = new String(os.toByteArray());
//        os.close();
//
//        log.info(resStr);

//        final ChangeOnField changeOnField = new ChangeOnField();
//        changeOnField.setFieldName("entityFullName");
//        changeOnField.setFieldNewValue(ChangeLogEntity.class.getCanonicalName());
//
//        final ChangesOnTableJson changesOnTable = new ChangesOnTableJson();
//        changesOnTable.addChange(changeOnField);
//
//        this.jmsMessageSender.sendToChangeLog(changesOnTable);
//
//        res.setMessage(changesOnTable.toString());

        this.emailComponent.sendLogToMail("mhanlucky@gmail.com", "TEST", "This is message from Spring boot!!1!");

        return res;
    }

}
