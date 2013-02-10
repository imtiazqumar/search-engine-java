/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.IOException;
import java.sql.SQLException;

public class StartCrawler {

    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
        int numThreads = 5, numPages = 5;
        String StartURL = "http://www.google.com";
        Crawler Craw = new Crawler();
        Craw.Start(numThreads, StartURL, numPages);

    }
}
