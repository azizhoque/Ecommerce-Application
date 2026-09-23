package com.ecom.notification.email;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.ecom.notification.kafka.order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

	private JavaMailSender mailSender;
	
	private SpringTemplateEngine templateEngine;
	
	public void sendPaymentSuccessEmail(
			
			String destinationEmail,
			
			String customerName,
			
			BigDecimal amount,
			
			String orderReference
			) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = 
					new MimeMessageHelper(
							mimeMessage, 
							MimeMessageHelper.MULTIPART_MODE_RELATED, 
							StandardCharsets.UTF_8.name()
							);
			messageHelper.setFrom("contact@azizecom.com");
			final String templateName = EmailTemplate.PAYMENT_CNFIRMATION.getTemplate();
			
			Map<String, Object> variables = new HashMap<>();
			variables.put("customerName", customerName);
			variables.put("amount", amount);
			variables.put("orderReference", orderReference);
			Context context=new Context();
			context.setVariables(variables);
			messageHelper.setSubject(EmailTemplate.PAYMENT_CNFIRMATION.getSubject());
			
			String htmlTemplate = templateEngine.process(templateName, context);
			messageHelper.setText(htmlTemplate, true);
			messageHelper.setTo(destinationEmail);
			mailSender.send(mimeMessage);
			
			log.info("INFO- Email successfully send to %s with template %s", destinationEmail,templateName);
		} catch (MessagingException e) {
			e.printStackTrace();
			log.warn("WARN - Cannot send email to {}",destinationEmail);
		}
		
	}
	
public void sendOrerConfirmationEmail(
			
			String destinationEmail,
			
			String customerName,
			
			BigDecimal amount,
			
			String orderReference,
			
			List<Product> product
			) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = 
					new MimeMessageHelper(
							mimeMessage, 
							MimeMessageHelper.MULTIPART_MODE_RELATED, 
							StandardCharsets.UTF_8.name()
							);
			messageHelper.setFrom("contact@azizecom.com");
			final String templateName = EmailTemplate.ORDER_CNFIRMATION.getTemplate();
			
			Map<String, Object> variables = new HashMap<>();
			variables.put("customerName", customerName);
			variables.put("amount", amount);
			variables.put("orderReference", orderReference);
			variables.put("products", product);
			Context context=new Context();
			context.setVariables(variables);
			messageHelper.setSubject(EmailTemplate.ORDER_CNFIRMATION.getSubject());
			
			String htmlTemplate = templateEngine.process(templateName, context);
			messageHelper.setText(htmlTemplate, true);
			messageHelper.setTo(destinationEmail);
			mailSender.send(mimeMessage);
			
			log.info("INFO- Email successfully send to %s with template %s", destinationEmail,templateName);
		} catch (MessagingException e) {
			e.printStackTrace();
			log.warn("WARN - Cannot send email to {}",destinationEmail);
		}
		
	}
	
	
}
