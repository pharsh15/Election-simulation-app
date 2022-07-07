
package Assignment_2;


/**
 * officer class that is used to register votes generated by booths
 * @author Daniel Banks
 */
public class Officer extends Thread {
    
    int numberOfIncrements;
    Buffer buffer;
    int officerId;
    int numOfficers;
    
    /**
     * Officer Constructor
     * @param numberOfIncrements
     * @param buffer
     * @param officerId
     * @param numOfficers
     */
    public Officer(int numberOfIncrements, Buffer buffer, int officerId, int numOfficers){
        this.numberOfIncrements = numberOfIncrements;
        this.buffer = buffer;
        this.officerId = officerId;
        this.numOfficers = numOfficers;
    }
    
    /**
     * Get the ID of current officer
     * @return officerId
     */
    public int getOfficerId(){
        return officerId;
    }
    
    // Officer Thread Run method
    @Override
    public void run(){
            while(Election.officerVoteCounter<numberOfIncrements){
                buffer.mutexDown();
                if(Election.officerVoteCounter<numberOfIncrements){
                    if(buffer.isEmpty()){
                            buffer.mutexUp();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }else{
                        Vote vote = buffer.removeItem();
                        vote.setOfficerId(officerId);
                        Election.voteArray.add(vote);
                        Election.incrementOfficeCounter();
                        System.out.println("Officer: "+officerId+"\t"+" Candidate: "+vote.getCandidate()+"\t"+" Voter: "+vote.getVoter());
                        vote.booth.release();
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
