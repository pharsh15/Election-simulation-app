package Assignment_2;

import java.util.Arrays;

/**
 * Buffer class that handles inserting and removing votes from the queue
 * @author Daniel Banks
 */
public class Buffer {
    
    private Semaphore mutex = new Semaphore(1);
    
    private Vote[] buffer;
    private int size;
    private int front = 0;
    private int rear = 0;
    Candidate[] candidates;
    
    /**
     * Buffer Constructor
     * @param size
     * @param candidates
     */
    public Buffer(int size, Candidate[] candidates){
        buffer = new Vote[size];
        this.size = size;
        this.candidates = candidates;
    }
    
    /**
     * @return True or False depending on if buffer is empty
     */
    public boolean isEmpty(){
        return rear == front;
    }
    
    /**
     * @return True or False depending on if buffer is full
     */
    public boolean isFull(){
        int diff = rear - front;
        return diff == -1 || diff == (size-1);
    }
    
    /**
     * Method to add a vote to the buffer
     * @param item
     */
    public void insertItem(Vote item){
        if(!isFull()){
            buffer[rear]=item;
            rear = (rear+1)%size;
        }
    }
    
    /**
     * Method to remove a vote form the buffer
     * @return removed vote
     */
    public Vote removeItem(){
        Vote result = null;
        if(!isEmpty()){
            result = buffer[front];
            candidates[result.getCandidate()].addVote();
            buffer[front]=null;
            front = (front+1)%size;
        }
        return result;
    }

    /**
     * Lock the semaphore
     */
    public void mutexDown(){
        mutex.down();
    }
    
    /**
     * release the semaphore
     */
    public void mutexUp(){
        mutex.up();
    }
    
    @Override
    public String toString(){
        return Arrays.toString(buffer);
    }
}
