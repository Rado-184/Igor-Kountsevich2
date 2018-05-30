package com.mycompany.SecondApp;

/**
 * Hello world!
 *
 */
/*
 * 2. Написать консольную утилитку, которая бы читала произвольный файл MS Word (docx) и делала 
 * замену слова ‘personal’ на ‘public’. опять же, путь к файлу-исходнику и файлу-результату 
 * прописать в проперти фйале.
 * */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
public class App 
{
    public static void main( String[] args )
    {
        try {

            FileInputStream in = new FileInputStream(new Util().getPropertyValue("PATH_INPUT"));
            XWPFDocument doc = new XWPFDocument(in);
            XWPFDocument doc1 = new XWPFDocument();
            XWPFParagraph paragraph1 = doc1.createParagraph();
            XWPFRun run = paragraph1.createRun();
            String[] arr = null;

            for (XWPFParagraph p : doc.getParagraphs()) {
                String str = p.getText();
                arr = str.split(" ");
            }

            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("personal")) {
                    arr[i] = "public";
                }
            }

            for (String arr1 : arr) {
                run.setText(arr1 + " ");
            }

            FileOutputStream out = new FileOutputStream(new Util().getPropertyValue("PATH_OUTPUT"));
            doc1.write(out);
            out.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static class Util {

        public String getPropertyValue(String propertyName) {
            String propertyValue = null;
            Properties properties = new Properties();

            try {

                FileReader reader = new FileReader("properties.properties");
                properties.load(reader);
                propertyValue = properties.getProperty(propertyName);

            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            return propertyValue;
        }
    }
    }

