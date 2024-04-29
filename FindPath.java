import java.io.FileNotFoundException;
import java.io.IOException;

public class FindPath {
	private Map pyramidMap;
	
	public FindPath(String fileName) {
		try {
			pyramidMap = new Map(fileName);
			System.out.println("Trying");
		} catch (InvalidMapCharacterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Hello FileNotFound");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DLStack path() {
		DLStack stack = new DLStack();
		Chamber starting = pyramidMap.getEntrance();
		int numTreasure = pyramidMap.getNumTreasures();
		int treasureFound = 0;
		
		stack.push(starting);
		starting.markPushed();
		
		while(!(stack.isEmpty())) {
			Chamber current = (Chamber) stack.peek();
			
			if(current.isTreasure()){
				treasureFound++;
				if(treasureFound == numTreasure) {
					break;
				}
			}
			
			Chamber c = this.bestChamber(current);
			
			if(c != null) {
				stack.push(c);
				c.markPushed();
			}else {
				Chamber popped = (Chamber) stack.peek();
				popped.markPopped();
				stack.pop();
			}
			
		}
		return stack;
	}
	
	public Map getMap() {
		return pyramidMap;
	}
	
	public boolean isDim(Chamber currentChamber) {
		if(!currentChamber.isLighted() && !currentChamber.isSealed() && currentChamber != null) {
			for(int i = 0; i < 6; i++) {
				if(currentChamber.getNeighbour(i).isLighted()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Chamber bestChamber(Chamber currentChamber) {
		for(int i = 0; i < 6; i++) {
			if(!(currentChamber.getNeighbour(i).isMarked())) {
				if(currentChamber.getNeighbour(i).isTreasure()) {
					return currentChamber.getNeighbour(i);
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			if(!(currentChamber.getNeighbour(i).isMarked())) {
				if(currentChamber.getNeighbour(i).isLighted()) {
					return currentChamber.getNeighbour(i);
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			if(!(currentChamber.getNeighbour(i).isMarked())) {
				if(isDim(currentChamber.getNeighbour(i))) {
					return currentChamber.getNeighbour(i);
				}
			}
		}
		return null;
	}
	
	
}
