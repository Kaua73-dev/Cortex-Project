package com.api.cortex.service.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private String textRegisterEmailSender(){
        String message = """
                <body style="margin:0;padding:30px;background:#f5f7fb;font-family:Arial,Helvetica,sans-serif;">
                
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="center">
                
                            <table width="500" cellpadding="0" cellspacing="0"
                                   style="background:#fff;border-radius:14px;overflow:hidden;border:1px solid #e5e7eb;">
                
                                <!-- Header -->
                                <tr>
                                    <td align="center" style="background:#4F46E5;padding:30px;">
                                        <h1 style="margin:0;color:#fff;font-size:28px;">
                                             Cortex
                                        </h1>
                                    </td>
                                </tr>
                
                                <!-- Body -->
                                <tr>
                                    <td style="padding:35px;color:#374151;">
                
                                        <h2 style="margin-top:0;color:#111827;">
                                            Bem-vindo! 👋
                                        </h2>
                
                                        <p style="font-size:15px;line-height:1.7;">
                                            Sua conta foi criada com sucesso.
                                            Agora você já pode acessar a plataforma e começar a utilizar todos os recursos do Cortex.
                                        </p>
                
                                        <table cellpadding="0" cellspacing="0" align="center" style="margin:30px auto 10px;">
                                            <tr>
                                                <td bgcolor="#4F46E5" style="border-radius:8px;">
                                                    <a href="//colocar dominio"
                                                       style="display:inline-block;padding:14px 26px;color:#fff;text-decoration:none;font-weight:bold;">
                                                        Acessar Plataforma
                                                    </a>
                                                </td>
                                            </tr>
                                        </table>
                
                                        <p style="margin-top:25px;font-size:13px;color:#6b7280;text-align:center;">
                                            Se você não realizou este cadastro, ignore este e-mail.
                                        </p>
                
                                    </td>
                                </tr>
                
                                <!-- Footer -->
                                <tr>
                                    <td align="center"
                                        style="padding:18px;background:#f9fafb;font-size:12px;color:#9ca3af;">
                                        © 2026 Cortex | KauãDev
                                    </td>
                                </tr>
                
                            </table>
                
                        </td>
                    </tr>
                </table>
                
                </body>
                </html>
                """;
        return message;
    }
    private String textEventCreatedEmailSender(){
        String messageEmail = """
                <html lang="pt-BR">
                <head>
                <meta charset="UTF-8">
                </head>
                <body style="margin:0;padding:0;background:#f4f4f5;font-family:Arial,Helvetica,sans-serif;">
                <table width="100%%" cellpadding="0" cellspacing="0">
                <tr>
                <td align="center" style="padding:40px 20px;">
                <table width="500" cellpadding="0" cellspacing="0"
                style="background:#ffffff;border-radius:12px;padding:40px;box-shadow:0 4px 12px rgba(0,0,0,0.08);">

                <tr>
                <td align="center">
                <h1 style="margin:0;color:#2563eb;font-size:28px;">
                                🎉 Evento cadastrado!
                </h1>
                </td>
                </tr>

                <tr>
                <td style="padding-top:25px;color:#444;font-size:16px;line-height:1.6;text-align:center;">
                Parabéns! Seu evento foi cadastrado com sucesso no <strong>Cortex</strong>.
                        </td>
                </tr>

                <tr>
                <td style="padding-top:15px;color:#666;font-size:15px;line-height:1.6;text-align:center;">
                Nosso sistema já começou a processar suas informações para
                <strong>gerar seus insights finais.</strong>
                Ajudando você a entender melhor seus acontecimentos.
                </td>
              
              
             
                <tr>
                <td style="padding-top:35px;text-align:center;font-size:13px;color:#999;">
                Obrigado por utilizar o Cortex.
                        </td>
                </tr>

                </table>
                </td>
                </tr>
                </table>
                </body>
                </html>
                """;
        return messageEmail;
    }


                    @Async
                    public void emailRegister(String email){
                        try {
                            MimeMessage message = javaMailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

                            helper.setSubject("Bem-vindo ao Cortex!");
                            helper.setTo(email);
                            helper.setText(textRegisterEmailSender(), true);

                            javaMailSender.send(message);

                        } catch (MessagingException e){
                            throw new RuntimeException("Cannot possible send the email: " + e);
                        }
                    }

                    @Async
                    public void emailEventCreated(String email){
                        try {
                            MimeMessage message = javaMailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

                            helper.setSubject("Evento cadastrado com sucesso!");
                            helper.setTo(email);
                            helper.setText(textEventCreatedEmailSender(), true);

                            javaMailSender.send(message);

                        } catch (MessagingException e) {
                            throw new RuntimeException("Cannot possible send the email: " + e);
                        }

                    }




                }
