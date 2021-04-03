import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @description 模块介绍
 * @Author yangTao
 * @Date 2020/12/4 11/38
 */
public class App {
    //okspydrexyazebgf
    static String contentPath;

    static String addresseePath;

    static String enclosurePath;

    static String fromMail;

    static String fromKey;

    static String host;

    static List<String> addressMailList = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        System.out.println("批量发送邮箱 By：雁不过衡阳");
        Scanner input=new Scanner(System.in);
        // 选择要发送的模板内容路径
        System.out.println("请输入模板地址:");
        contentPath=input.next();
        // 选择批量发送的收件人txt路径
        System.out.println("请输入收件人邮箱的批量发送路径(txt每行一个):");
        addresseePath=input.next();
        // 附件地址
        System.out.println("请输入附件地址:");
        enclosurePath=input.next();
        // 发件人邮箱
        System.out.println("请输入发件人邮箱:");
        fromMail=input.next();
        // 发件人密钥
        System.out.println("请输入发件人密钥:");
        fromKey=input.next();
        // 设置邮箱服务器
        System.out.println("请输入邮箱服务器如:smtp.qq.com、smtp.163.com");
        host=input.next();

        getAddressMail();

        System.out.println("=============批量发送邮箱开始==============");
        for (int i = 0; i < addressMailList.size(); i++) {
            sendMail(addressMailList.get(i));
        }
        System.out.println("=============批量发送邮箱结束==============");
    }

    public static void getAddressMail() throws Exception {
        BufferedReader bre = null;
        try {
            if(addresseePath==null||addresseePath.equals("")){
                throw new Exception("收件人地址路径不能为空");
            }
            bre = new BufferedReader(new FileReader(addresseePath));
            String temp=null;
            System.out.println("=============读取收件人列表开始==============");
            while ((temp = bre.readLine()) != null)
            {
                System.out.println("当前读取邮箱:"+temp);
                addressMailList.add(temp);
            };
            System.out.println("=============读取收件人列表结束==============");
        }catch (Exception e){
            throw new Exception("读取失败");
        }
    }

    public static Boolean sendMail(String addressMail){
        System.out.println("当前发送邮箱:"+addressMail);
        Boolean flag = false;
        try {
            if(contentPath==null||contentPath.equals("")){
                throw new Exception("模板内容路径不能为空");
            }
            if(enclosurePath==null||enclosurePath.equals("")){
                throw new Exception("附件地址不能为空");
            }
            if(addressMail==null||addressMail.equals("")){
                throw new Exception("收件人地址不能为空");
            }
            if(fromMail==null||fromMail.equals("")){
                throw new Exception("发件人邮箱不能为空");
            }
            if(fromKey==null||fromKey.equals("")){
                throw new Exception("发件人邮箱密钥不能为空");
            }
            if(host==null||host.equals("")){
                throw new Exception("邮箱服务器不能为空");
            }

            //获取系统属性
            Properties properties = new Properties();
            //SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //设置系统属性
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");
            //获取发送邮件会话、获取第三方登录授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromMail, fromKey);
                }
            });

            //内容路径
            File file = new File(contentPath);
            String subject = null;
            BufferedReader reader = null;
            StringBuilder sbf = new StringBuilder();

            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            int line=0;
            while ((tempStr = reader.readLine()) != null) {
                if(line==0){
                    subject=tempStr;
                    line++;
                    continue;
                }
                sbf.append(tempStr);
            }
            reader.close();

            Message message = new MimeMessage(session);
            //防止邮件被当然垃圾邮件处理，披上Outlook的马甲
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(fromMail));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressMail));
            //邮件标题
            message.setSubject(subject);
            message.setSentDate(new Date());

            MimeMultipart multipart = new MimeMultipart();

            //文字内容
            MimeBodyPart content = new MimeBodyPart();
            content.setContent(sbf.toString(),"text/html;charset=utf-8");
            //System.out.println("邮箱主题:"+subject);
            //System.out.println("邮箱内容："+sbf.toString());


            //邮件附件
            MimeBodyPart bodyPart = new MimeBodyPart();
            DataSource dataSource = new FileDataSource(enclosurePath);
            bodyPart.setDataHandler(new DataHandler(dataSource));
            bodyPart.setFileName(MimeUtility.encodeText(enclosurePath.substring(enclosurePath.lastIndexOf("\\")+1)));

            multipart.setSubType("mixed");
            multipart.addBodyPart(bodyPart);
            multipart.addBodyPart(content);
            message.setContent(multipart);
            message.saveChanges();
            Transport.send(message);
            System.out.println("\033[31;4m"+addressMail+": mail transports successfully"+"\033[0m");
            flag = true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(addressMail+": mail transports fail");
        }
        return flag;
    }
}
