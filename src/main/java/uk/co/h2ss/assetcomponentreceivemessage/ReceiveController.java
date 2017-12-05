package uk.co.h2ss.assetcomponentreceivemessage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uk.co.h2ss.msg.Message;

@Controller
public class ReceiveController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@GetMapping("/receivemessage")
    public String receiveMessage(Model model) {
		jmsTemplate.setReceiveTimeout(1L);
		List<Message> messages = new ArrayList<Message>();
		Message message;
		while ((message = (Message) jmsTemplate.receiveAndConvert("id2asset")) != null) {
		   messages.add(message);
		}
		  
		model.addAttribute("messages", messages);
		return "messages";
    }
}
