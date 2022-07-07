package Assignment_2;

/**
 * Vote Class to store details of a vote
 * @author Daniel Banks
 */
public class Vote {
    
    int candidate;
    long boothId;
    int voter;
    Booth booth;
    int officerId;
    
    /**
     * Vote constructor
     * @param candidate
     * @param voter
     * @param booth
     */
    public Vote(int candidate, int voter, Booth booth){
        this.candidate = candidate;
        boothId = booth.getBoothId();
        this.voter = voter;
        this.booth = booth;
    }
    
    /**
     * gets the candidate tied to this vote
     * @return candidate
     */
    public int getCandidate(){
        return candidate;
    }
    
    /**
     * gets the ID of the booth that generated this vote
     * @return boothId
     */
    public long getBoothId(){
        return boothId;
    }
    
    /**
     * gets the number of the voter that created this vote
     * @return voter
     */
    public int getVoter(){
        return voter;
    }
    
    /**
     * set the candidate for this vote
     * @param candidate
     */
    public void setCandidate(int candidate){
        this.candidate = candidate;
    }
    
    /**
     * set the booth id for this vote
     * @param boothId
     */
    public void setBoothId(int boothId){
        this.boothId = boothId;
    }
    
    /**
     * set the officer id for this vote
     * @param officerId
     */
    public void setOfficerId(int officerId){
        this.officerId = officerId;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Candidate: ").append(candidate).append(" Booth: ").append(boothId);
        
        return str.toString();
    }
}
