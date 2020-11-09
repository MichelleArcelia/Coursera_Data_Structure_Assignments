import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javafx.concurrent.Worker;

//-----------------JOB QUEUE-----------------

public class JobQueue {
	
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;


//-------------------MAIN--------------------    

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }
    
//----------------READ DATE------------------    

    private void readData() throws IOException {
    	
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }
    
//-------------WRITE RESPONSE---------------    

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }
    
//-------------------ASSIGN JOBS-------------------      

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
    	
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }
    
//---------------------WORKER----------------------      
    
    private class worker {
    	
    	int id;
    	
    	long nextFreeTime;
    	
    public worker (int id) {
    		
    	this.id = id;
    	
    	nextFreeTime = 0;
    		
    	}
    	
    }
  
//----------------EFFICIENT ASSIGN JOBS--------------  
    
    
    private void efficientAssignJobs() {
    	
    	assignedWorker = new int [jobs.length];
    	
    	startTime = new long[jobs.length];
    	
    	PriorityQueue<Worker> pq = new PriorityQueue<Worker>()numWorkers, new Comparator <Worker>()) {
    		
    		
    	
    	@Override
    	
    	public int compare (Worker w1, Worker w2) {
    		
    		if (w1.nextFreeTime == w2.nextFreeTime)
    			
    			return w1.id - w2.id;
    		
    		else
    			
    			return (int) (w1.nextFreeTime - w2.nextFreeTime);
    	}
}
    	
    	for (int i = 0; i < numWorkers; i++) {
    		
    		pq.offer(new worker(i));
    		
    		
    		
    		
    	}
    		
    		
    	
    	
    }
   
 /*   
  
					
					return (int) (w1.nextFreeTime - w2.nextFreeTime);
            }
        });
		for (int i = 0; i < numWorkers; i++)
            pq.offer(new worker(i));
        for (int i = 0; i < jobs.length; i++) {
            worker freeThread = pq.poll();
            assignedWorker[i] = freeThread.id;
            startTime[i] = freeThread.nextFreeTime;
            freeThread.nextFreeTime += jobs[i];
            pq.offer(freeThread);
        }
	}
    
    
    */
    
    
    

//---------------------SOLVE------------------        
    
    public void solve() throws IOException {
    	
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        //assignJobs();
        
        efficientAssignJobs();
        
        writeResponse();
        out.close();
    }
    
//---------------FAST SCANNER-----------------        

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

//-------------------NEXT---------------------     
        
        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

//-------------------NEXT INT---------------------            
        
        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
