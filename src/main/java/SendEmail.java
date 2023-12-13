import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


public class SendEmail {

    public void mandarCorreo() {
        // El correo gmail de envío
        String correoEnvia = "amaliagd04@gmail.com";
        String claveCorreo = "iurk plax ddai zzof ";

        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        // Obtener la sesion
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEnvia, claveCorreo);
            }
        });

        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia));

            // Los destinatarios
            InternetAddress[] internetAddresses = {
                    new InternetAddress("agdopico28@gmail.com")
            };

            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);

            // Agregar el asunto al correo
            mimeMessage.setSubject("¡Bienvenido!");

            // Crear la parte del mensaje HTML
            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            String mensajeHTML = "<html><body><p>Bienvenido a nuestra aplicación</p>"
                    + "<img src=\"cid:imagen_bienvenida\">"
                    + "</body></html>";
            htmlBodyPart.setContent(mensajeHTML, "text/html");

            // Crear la parte del mensaje con la imagen adjunta
            MimeBodyPart imageBodyPart = new MimeBodyPart();
            imageBodyPart.attachFile("correo.jpg"); // Cambiar la ruta de la imagen
            imageBodyPart.setContentID("<imagen_bienvenida>");

            // Crear el multipart para agregar las partes del mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlBodyPart);
            multipart.addBodyPart(imageBodyPart);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport.send(mimeMessage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Acabo de enviar un correo desde " + correoEnvia);
    }

    public static void main(String[] args) {
        SendEmail correoTexto = new SendEmail();
        correoTexto.mandarCorreo();
    }
}