package Assignment_2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Main Class that generates all thread and determines winner 
 * @author Daniel Banks
 */
public class Election {
   
    /**
     * Keep count of the number of votes that have been created
     */
    public static int boothVoteCounter = 0;

    /**
     * Keep count of the number of votes that have been registered
     */
    public static int officerVoteCounter = 0;

    /**
     * ArrayList to store all votes
     */
    public static ArrayList<Vote> voteArray = new ArrayList<>();
    
    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {   
        Scanner scan = new Scanner(System.in);
        
        // Number of Booths
        System.out.println("Enter Number of Booths(2 or more): ");
        int numBooths = scan.nextInt(); // Buffer Size
        while(numBooths<2){
            System.out.println("Number of Booths MUST be 2 or more:");
            numBooths = scan.nextInt();
        }
        
        // Number of Votes
        int numVotes;
        System.out.println("Enter Number of Voters(1 or more): ");
        numVotes = scan.nextInt(); // Number of Cycles
        
        while(numVotes<1){
            System.out.println("Must be 1 or more voters:");
            numVotes = scan.nextInt();
        }
        int numVotesBooth = numVotes;
        
        int numOfficers = 1;
        
        // Number of Officers
        System.out.println("Enter Number of Officers (between 1 - "+(numBooths-1)+"):");
        numOfficers = scan.nextInt(); // Must be lower than booths
        while(numOfficers>numBooths-1||numOfficers<1){
            System.out.println("Number of Officers MUST be between 1 - "+(numBooths-1)+":");
            numOfficers = scan.nextInt();
        }
        int numVotesOfficer = numVotes;
        
        // Number of Candidates
        System.out.println("Enter Number of Candidates: ");
        int numCandidates = scan.nextInt();
        while(numCandidates<1){
            System.out.println("Must be 1 or more Candidates:");
            numCandidates = scan.nextInt();
        }
        System.out.println("");
        Candidate[] candidates;
        candidates = new Candidate[numCandidates];
        for(int i =0; i<numCandidates;i++){
            candidates[i] = new Candidate(i);
        }
        
        // Create Buffer
        Buffer buffer = new Buffer(numBooths, candidates);
        
        ArrayList<Thread> threads = new ArrayList<>();
        // Start booth threads
        for(int i =0; i<numBooths;i++){
            Thread t = new Booth(numVotesBooth,buffer,i, numCandidates);
            threads.add(t);
            t.start();
        }
        
        // Start Officer threads
        for(int i =0; i<numOfficers;i++){
            Thread t = new Officer(numVotesOfficer,buffer,i, numOfficers);
            threads.add(t);
            t.start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("");
        getWinner(candidates);
        
    }
    
    /**
     * Method to determine the winner of the election, or if there is a draw
     * @param candidates
     */
    public static void getWinner(Candidate[] candidates){
        int max = 0;
        Candidate winner = null;
        for(Candidate element: candidates){
            System.out.println(element.toString());
            if(element.getNoVotes()>max){
                max = element.getNoVotes();
                winner = element;
            }
        }
        
        ArrayList<Candidate> winners = new ArrayList<>();
        winners.add(winner);
        for(Candidate element: candidates){
            if(winner.getNoVotes()==element.getNoVotes()&&winner!=element){
                winners.add(element);
            }
        }
        System.out.println("");
        
        // Winner list is > 1 so there are more than one winners, so its a draw
        if(winners.size()>1){
            System.out.println("Draw Between:");
            for(Candidate element: winners){
                System.out.println(element.toString());
            }
        }else{            
            System.out.println("Winner: ");
            System.out.println(winner.toString());
        }
        
    }
    
    /**
     * method to increment the booth vote counter
     * @return boothVoteCounter
     */
    public static int incrementVoter(){
        return boothVoteCounter++;
    }
    
    /**
     * method to increment the officer vote counter
     * @return officerVoteCounter
     */
    public static int incrementOfficeCounter(){
        return officerVoteCounter++;
    }
    
    /**
     * random number generator
     * @param min
     * @param max
     * @return random Number between min and max
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
}
