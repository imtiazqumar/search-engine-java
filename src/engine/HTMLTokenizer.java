/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author M Imtiaz
 */
public class HTMLTokenizer{
DB db;
public HTMLTokenizer()
{
    db=new DB();
    
}
void Tokenize(String ur) throws FileNotFoundException, IOException
{

 URL u=new URL(ur);
   Document doc = Jsoup.parse(u, 0*1000);
// Clean the document.
doc = new Cleaner(Whitelist.none()).clean(doc);
// Adjust escape mode
doc.outputSettings().escapeMode(EscapeMode.xhtml);
// Get back the string of the body.
String HTML=doc.body().html();
HTML.replaceAll("(?![@',&])\\p{Punct}", "");
    StringTokenizer st=new StringTokenizer(HTML);
    while (st.hasMoreTokens()) {
        db.InsertToken(ur, st.nextToken());
        
        } 
}


}
