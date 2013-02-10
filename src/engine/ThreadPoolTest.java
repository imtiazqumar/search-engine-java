/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M Imtiaz
 */


public class ThreadPoolTest {

  public static void main(String[] args) {
      System.out.println(args.length);
    if (args.length != 2) {
      System.out.println("Tests the ThreadPool task.");
      System.out
          .println("Usage: java ThreadPoolTest numTasks numThreads");
      System.out.println("  numTasks - integer: number of task to run.");
      System.out.println("  numThreads - integer: number of threads "
          + "in the thread pool.");
      return;
    }
    int numTasks = Integer.parseInt(args[0]);
    int numThreads = Integer.parseInt(args[1]);

    // create the thread pool
    ThreadPool threadPool = new ThreadPool(numThreads);
    List<String> Count=new ArrayList<String> ();
    for(int k=0;k<15;k++)
    Count.add("Hello");
    

    // run example tasks
    
    for (int i = 0; i < numTasks; i++) {
      threadPool.runTask(createTask("Hello",i));
    }

    // close the pool and wait for all tasks to finish.
    threadPool.join();
  }

  /**
   * Creates a simple Runnable that prints an ID, waits 500 milliseconds, then
   * prints the ID again.
   */
  private static Runnable createTask(String Link,final int taskID) {
    return new Runnable() {
      public void run() {
        System.out.println("Task " + taskID + ": start");
        double j=0;
        for(double i=0;i<99999999;i++)
        {
            j+=i;
        
        }
        System.out.println("Task " + taskID + "j:"+j);
        

        // simulate a long-running task
       

        System.out.println("Task " + taskID + ": end");
      }
    };
  }

}