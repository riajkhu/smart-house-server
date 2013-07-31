

	import java.util.Date;
	import java.util.Properties;

	import javax.mail.Address;
	import javax.mail.Authenticator;
	import javax.mail.Message;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;

/*
*This class is responsible for transmitting emergency message to the client through email notification
*/

	public class Javamail {



		private String host = "smtp.gmail.com";

		private String username = "projecthouse3@gmail.com";

		private String password = "projecthou";
		private String mail_head_name = "Warning";
		private String mail_head_value = "this is head of this mail";

		private String mail_from = "projecthouse3@gmail.com";
		private String mail_subject = "WARNING";
		private String mail_body;
		private String personalName = "SERVER";

		public Javamail() {
		}


		public void setMailBody(String body){
			this.mail_body = body;
		}

		/*
		* @param takes the receivers email address for mail transmission for emergency house alarm
		*/


		public void send(String mail_to) {
			try {
				Properties props = new Properties();
				Authenticator auth = new Email_Autherticator();
				//props.put("mail.smtp.host", host);
				//props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				Session session = Session.getDefaultInstance(props, auth);

				MimeMessage message = new MimeMessage(session);

				message.setSubject(mail_subject);
				message.setText(mail_body);
				message.setHeader(mail_head_name, mail_head_value);
				message.setSentDate(new Date());
				Address address = new InternetAddress(mail_from, personalName);
				message.setFrom(address);
				Address toAddress = new InternetAddress(mail_to);
				message.addRecipient(Message.RecipientType.TO, toAddress);
				Transport.send(message);
				System.out.println("send ok!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public class Email_Autherticator extends Authenticator {
			public Email_Autherticator() {
				super();
			}

			public Email_Autherticator(String user, String pwd) {
				super();
				username = user;
				password = pwd;
			}

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		}

	}

