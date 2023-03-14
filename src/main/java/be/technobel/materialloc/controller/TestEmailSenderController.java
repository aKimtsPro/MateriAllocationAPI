package be.technobel.materialloc.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@RestController
@RequestMapping("/email")
public class TestEmailSenderController {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public TestEmailSenderController(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @GetMapping
    public void sendEmail() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo("alexandre.kimtsaris@bstorm.be");
        helper.setSubject("test email sender");

        Context ctxt = new Context();
        String html = templateEngine.process("email.template.html", ctxt);

        helper.setText(html, true);
        helper.setFrom("materialloc.bot@gmail.com");

        javaMailSender.send(message);

    }

}
