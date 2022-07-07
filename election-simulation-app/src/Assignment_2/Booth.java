
package Assignment_2;

import java.util.Random;

/**
 * Booth Class that is used to generate votes
 * @author Daniel Banks
 */
public class Booth extends Thread {
    
    int numberOfVotes;
    Buffer buffer;
    int boothId;
    Vote vote;
    boolean occupied = false;
    int numCandidates;
    
    /**
     * Booth Constructor
     * @param numberOfVotes
     * @param buffer
     * @param boothId
     * @param numCandidates
     */
    public Booth(int numberOfVotes, Buffer buffer, int boothId, int numCandidates){
        this.numberOfVotes = numberOfVotes;
        this.buffer = buffer;
        this.boothId = boothId;
        this.numCandidates = numCandidates;
    }
    
    /**
     * Set the status of this booth to occupied
     */
    public void occupy(){
        occupied = true;
    }
    
    /**
     * Set the status of this booth to not occupied
     */
    public void release(){
        occupied = false;
    }
    
    /**
     * Get the ID of the current booth
     * @return boothId
     */
    public int getBoothId(){
        return boothId;
    }
    
    // Booth Thread Run method
    @Override
    public void run(){
        while(Election.boothVoteCounter<numberOfVotes){
            try {
                Thread.sleep(Election.randInt(1000, 5000));
            } catch (InterruptedException ex) {
            }
            buffer.mutexDown();
            if(Election.boothVoteCounter<numberOfVotes && !this.occupied){
                if(buffer.isFull()){
                    buffer.mutexUp();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }else{
                    vote = new Vote(new Random().nextInt(numCandidates), Election.incrementVoter(), this);
                    buffer.insertItem(vote);
                    this.occupy();
                    System.out.println("Voter:   "+vote.getVoter()+"\t"+" Candidate: "+vote.getCandidate()+"\t"+" Booth: "+boothId);
                    buffer.mutexUp();
                    try {
                        Thread.sleep(Election.randInt(1000, 5000));
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
                }else{
                    buffer.mutexUp();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
    }

