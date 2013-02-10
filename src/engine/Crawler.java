package engine;

import java.io.*;
import java.net.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Crawler {

    public static List<String> Downloaded = new ArrayList<String>();
HTMLTokenizer Tokenizer;
    public Crawler() {
        File file = new File("./Repository/");
        if (!file.exists()) {
            file.mkdir();
        }
        file.setWritable(true);
       Tokenizer=new HTMLTokenizer();

    }

    public void Start(int numThreads, String StartURL, int MaximumPages) throws MalformedURLException, IOException {
        /*  StringTokenizer st = new StringTokenizer("this is a test");
        while (st.hasMoreTokens()) {
        System.out.println(st.nextToken());
        } 
         */


        /*
         * 
        
        Three Categories of Pages
         * Visited: Pages which are downloaded and all the Links on it are also downloaded
        Non Visited: Not Visited Yet
         * Downloaded: Downloaded Pages
         */




        //  int numThreads = Integer.parseInt(args[0]);

        // create the thread pool
        ThreadPool threadPool = new ThreadPool(numThreads);


        // run example tasks





        // close the pool and wait for all tasks to finish.



        List<String> Visited = new ArrayList<String>();
        List<String> NonVist = new ArrayList<String>();
        NonVist.add(StartURL);
        URLHandle urlhandle = new URLHandle();
        URL url;
        int i = 0;

        String value = "";
        boolean terminate = false;
        while (terminate == false) {

            threadPool.runTask(createTask(StartURL));
            Downloaded.add(StartURL);
         
            // threadPool.join();
            System.out.println(Downloaded.size() + " Pages Downloaded");
            i++;

            synchronized (this) {
                if (Downloaded.size() >= MaximumPages) // Maximum Pages to Download
                {
                    terminate = true;
                }
                if (terminate) {
                    break;
                }
            }
            synchronized (this) {
                Visited.add(StartURL);
                NonVist.remove(StartURL);

                url = new URL(StartURL);
                Document doc = Jsoup.parse(url, 0 * 1000);
                Elements links = doc.select("a[href]"); // a with href
                for (Element link : links) {
                    if (!Visited.contains(link.attr("abs:href"))) {
                        NonVist.add(link.attr("abs:href"));
                    }
                }
            }
            Iterator It = NonVist.iterator();
            while (It.hasNext()) {
                synchronized (this) {
                    value = (String) It.next();
                    threadPool.runTask(createTask(value));
                    Downloaded.add(value);
                    
                }

                // threadPool.join();
                if (Downloaded.size() >= MaximumPages) {
                    terminate = true;
                }
                if (terminate) {
                    break;
                }
                i++;


            }
            StartURL = NonVist.get(0);

            if (terminate) {
                break;
            }

        }

    }

    private static Runnable createTask(final String Link) {
        return new Runnable() {

            public void run() {
                try {
                  //  System.out.println("Downloading" + Name);
                   HTMLTokenizer Tokenizer=new HTMLTokenizer();
                   Tokenizer.Tokenize(Link);
                 //   System.out.println("Downloaded" + Name);
                } catch (Exception e) {
                    System.out.println("Child interrupted.");

                }
            }
        };
    }
}

class URLHandle {

    void DownloadURL(String downurl, int Newname) throws InterruptedException {
        URL url;
        InputStream is = null;
        DataInputStream dis;
        String line;
        String Content = "";

        try {
            url = new URL(downurl);

            is = url.openStream();  // throws an IOException
            dis = new DataInputStream(new BufferedInputStream(is));
            while ((line = dis.readLine()) != null) {
                //System.out.println(line);
                Content += line;
            }
        } catch (MalformedURLException mue) {
        } catch (IOException ioe) {
        } finally {
            try {
                is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        File file = new File("./Repository/" + Newname + ".html");

        //String content = "This is the text content";

        try {
            FileOutputStream fop = new FileOutputStream(file);

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = Content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            System.out.println("Downloaded: " + downurl);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
