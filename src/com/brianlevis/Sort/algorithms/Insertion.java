package com.brianlevis.sort.algorithms;

public class Insertion extends Algorithm {

	private int sortedIndex  = 0;
	private int currentIndex = 0;
	//private int localMinimum = 0;
	
	public Insertion() {
		super("Insertion Sort");
		highlight(0);
		select(1);
	}
	
	public void next() {
		if (currentIndex == elements.size()) {
			sorted = true;
			deselect(sortedIndex);
			return;
		}
		
		deselect(currentIndex);
		if (currentIndex + 1 < elements.size()) deselect(currentIndex + 1);
		deselect(sortedIndex);
		if (currentIndex == 0 || get(currentIndex) >= get(currentIndex - 1)) {	
			// Reset procedure: forget about previous data, select new data
			//nextIndex++;
			if (sortedIndex == 0) sortedIndex++;
			currentIndex = sortedIndex + 1;
			select(Math.min(currentIndex, elements.size() - 1));
			highlight(sortedIndex);
		} else {
			// Moving procedure: move, then look at current plus next candidate
			move(currentIndex, currentIndex - 1);
			currentIndex--;
			if(currentIndex == sortedIndex) sortedIndex++;
			select(currentIndex + 1);
			select(currentIndex);
			highlight(sortedIndex);			
		}
	}

}
