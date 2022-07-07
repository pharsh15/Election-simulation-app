package Assignment_2;

/**
 * Candidate Class to store details of a candidate
 * @author Daniel Banks
 */
public class Candidate {
    
    int candidateId;
    int noOfVotes;
    
    /**
     * Candidate Constructor
     * @param candidateId
     */
    public Candidate(int candidateId){
        this.candidateId = candidateId;
        noOfVotes = 0;
    }
    
    /**
     * get ID of this candidate
     * @return candidateId
     */
    public int getCandidateId(){
        return candidateId;
    }
    
    /**
     * get number of votes for this candidate
     * @return noOfVotes
     */
    public int getNoVotes(){
        return noOfVotes;
    }
    
    /**
     * set the id for this candidate
     * @param newId
     */
    public void setCandidateId(int newId){
        candidateId = newId;
    }
    
    /**
     * set the number of votes for this candidate
     * @param votes
     */
    public void setNoVotes(int votes){
        noOfVotes = votes;
    }
    
    /**
     * add a vote to the current candidates number of votes
     */
    public void addVote(){
        noOfVotes++;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Candidate ID: ").append(candidateId).append(" Votes: ").append(noOfVotes);
        return str.toString();
    }
}
