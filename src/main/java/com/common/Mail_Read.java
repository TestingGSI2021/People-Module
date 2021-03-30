package com.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

public class Mail_Read {
	


	
	
	public static String Fetch_Content_From_Mail(String mailFolderName,String emailSubjectContent, String emailContent, int lengthOfOTP) {

		            String hostName = "smtp.gmail.com";//change it according to your mail

		            String username = Constants.email;//username 

		            String password = Constants.password;

		            int messageCount;

		            int unreadMsgCount;

		            String emailSubject;

		            Message emailMessage;

		            String searchText=null ;

		            Properties sysProps = System.getProperties();

		            sysProps.setProperty("mail.store.protocol", "imaps");

		            try {

		                Session session = Session.getInstance(sysProps, null);

		                Store store = session.getStore();

		                store.connect(hostName, username, password);

		                Folder emailBox = store.getFolder(mailFolderName);

		                emailBox.open(Folder.READ_WRITE);

		                messageCount = emailBox.getMessageCount();

		                System.out.println("Total Message Count: " + messageCount);

		                unreadMsgCount = emailBox.getUnreadMessageCount();

		                System.out.println("Unread Emails count:" + unreadMsgCount);

		                for(int i=messageCount; i>(messageCount-unreadMsgCount); i--)

		                {

		                    emailMessage = emailBox.getMessage(i);

		                    emailSubject = emailMessage.getSubject();

		                    if(emailSubject.contains(emailSubjectContent.trim()))

		                    {

		                        System.out.println("Mail found");

		                        String line;

		                        StringBuffer buffer = new StringBuffer();

		                        BufferedReader reader = new BufferedReader(new InputStreamReader(emailMessage.getInputStream()));

		                        while ((line = reader.readLine()) != null) {

		                            buffer.append(line);

		                        }

		                        String messageContent=emailContent;
		                        
		                        String mailcontent = toString(emailMessage);
		                        System.out.println(mailcontent);

		                        String result = mailcontent.substring(mailcontent.indexOf(messageContent), mailcontent.length());

		                        searchText = result.substring(messageContent.length(), messageContent.length()+lengthOfOTP);

		                        System.out.println("Textfound : "+ searchText);

		                        emailMessage.setFlag(Flags.Flag.SEEN, true);

		                        break;

		                    }

		                    emailMessage.setFlag(Flags.Flag.SEEN, true);

		                }

		                emailBox.close(true);

		                store.close();


		            } catch (Exception mex) {

		                mex.printStackTrace();

		                System.out.println("OTP Not found ");

		            }

		            return searchText;

		        }
	 public static String toString(Message message) throws MessagingException, IOException {
			Object content = message.getContent();
			if (content instanceof MimeMultipart) {
				MimeMultipart multipart = (MimeMultipart) content;
				if (multipart.getCount() > 0) {
					BodyPart part = multipart.getBodyPart(0);
					content = part.getContent();
				}
			}
			if (content != null) {
				return content.toString();
			}
			return null;
		}
}
