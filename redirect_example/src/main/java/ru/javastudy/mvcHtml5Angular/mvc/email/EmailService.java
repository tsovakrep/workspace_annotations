package ru.javastudy.mvcHtml5Angular.mvc.email;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created for JavaStudy.ru on 28.02.2016.
 */
@Service
public class EmailService {

    /*Email From*/
    public static final String FROM = "from";
    /*Email To*/
    public static final String TO = "to";
    /*Email Subject*/
    public static final String SUBJECT = "subject";
    /*Email BCC*/
    public static final String BCC_LIST = "bccList";
    /*Email CCC*/
    public static final String CCC_LIST = "ccList";

    @Autowired
    private JavaMailSender mailSender; //see application-context.xml

    @Autowired
    private VelocityEngine velocityEngine; //see application-context.xml


    public boolean sendEmail (final String templateName, final Map<String, Object> model) {
        boolean res = false;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    String from = (String) model.get(FROM);
                    String to = (String) model.get(TO);
                    String subject = (String) model.get(SUBJECT);

                    List<String> bccList = (List<String>) model.get(BCC_LIST);
                    //ВАЖНО! ПОСТАВЬТЕ КОДИРОВКУ UTF-8 ИЛИ СООБЩЕНИЯ БУДУТ ?????? ??
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8"); //ENCODING IMPORTANT!
                    message.setFrom(from);
                    message.setTo(to);
                    message.setSubject(subject);
                    message.setSentDate(new Date());
                    if (bccList != null) {
                        for (String bcc : bccList) {
                            message.addBcc(bcc);
                        }
                    }

                    model.put("noArgs", new Object());
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateName, "UTF-8", model);

                    message.setText(text,true);
                }
            };

            mailSender.send(preparator);
            res = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

}
