package edu.lab.back.util.converters;

import edu.lab.back.dtoPojos.XSLPojo;
import edu.lab.back.util.constants.UrlPatterns;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.transform.Result;

public class CustomJaxb2RootElementHttpMessageConverter extends Jaxb2RootElementHttpMessageConverter {

    public CustomJaxb2RootElementHttpMessageConverter(@NonNull final UrlPatterns urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    private final String XML_INSTRUCTION_PROPERTY_NAME = "com.sun.xml.bind.xmlHeaders";

    private UrlPatterns urlPatterns;

    /**
     * Хранить состояние такая себе тема, но ничего больше не придумал пока :(
     */
    private Object targetObj;

    @Override
    protected void writeToResult(final Object o, final HttpHeaders headers, final Result result) throws Exception {
        //TODO Сильно не уверен касаемо скоупа всей херни, так что нужно потом попробовать просто копировать
        //TODO родительскую реализацию и уже там менять. Но пока оставлю так
        this.targetObj = o;
        super.writeToResult(o, headers, result);
    }

    @Override
    protected void customizeMarshaller(Marshaller marshaller) {
        try {
            if (this.targetObj instanceof XSLPojo) {
                final XSLPojo xslPojo = (XSLPojo) this.targetObj;
                final String templateName = xslPojo.getTemplateName();
                final String processingInstruction = String.format(
                        "<?xml-stylesheet type='text/xsl' href='%s/%s.xsl' ?>",
                        this.urlPatterns.getBaseUrl(),
                        templateName
                );
                marshaller.setProperty(XML_INSTRUCTION_PROPERTY_NAME, processingInstruction);
            } else {
                marshaller.setProperty(XML_INSTRUCTION_PROPERTY_NAME, "");
            }
        } catch (PropertyException e) {
            throw new IllegalStateException(e);
        }
    }

}
