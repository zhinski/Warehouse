//Michael Dobrzanski
public class Warehouse {
	private int shelfCount, shelfCapacity;
	//private warehouse spreadsheet [shelves/item code][capacity]
	//use a for loop to find open shelf/shelf already containing item, receive/ship given amount
	//mutildimensional array notes: pg. 364 Ch 7.5 2D array; geeks for geeks
	private int [][] warehouse;
	
	public Warehouse(int shelfCount, int shelfCapacity) {
		this.shelfCount = shelfCount;
		this.shelfCapacity = shelfCapacity;
		warehouse = new int [shelfCount][shelfCapacity]; //find shelf/item code + contents
	}

	public int getShelfCount() {
		return shelfCount;
	}
	public void setShelfCount(int shelfCount) {
		this.shelfCount = shelfCount;
	}

	public int getShelfCapacity() {
		return shelfCapacity;
	}
	
	public void setShelfCapacity(int shelfCapacity) {
		this.shelfCapacity = shelfCapacity;
	}
	
	//receive items; update shelf with item code; cannot exceed shelf capacity
	public int receive(int itemCode, int itemCount) { 
		int intake = 0;
		for (int i = 0; i < shelfCount; i++) {
			if (warehouse[i][0] == itemCode) {
				for (int j = 0; j < shelfCapacity; j++) {
					if (warehouse[i][j] == 0 && itemCount > 0) {
						warehouse[i][j] = itemCode;
						itemCount--;
					}
				}
				intake ++;
			}
		}
		if (intake == 0) {
			for (int i = 0; i < shelfCount; i++) {
				if (warehouse[i][0] == 0) {
					for (int j = 0; j < shelfCapacity; j++) {
						if (warehouse[i][j] == 0 && itemCount > 0) {
							warehouse[i][j] = itemCode;
							itemCount--;
						}
					}
					return itemCount;
				}
			}
		}
		return itemCount;
	}
	//ship requested amount
	//if demand is >= supply, ship all items
	//if capacity = 0, make shelf available to new items (reset shelf to 0)
	//return amount shipped
	public int ship(int itemCode, int itemCount) { 
		int shipTotal = 0;
		for (int i = 0; i < shelfCount; i++) {
			if (warehouse [i][0] == itemCode) {
				for (int j = shelfCapacity - 1; j >= 0 ; j--) {
					if (warehouse[i][j] == itemCode && itemCount > 0) {
						warehouse[i][j] = 0;
						itemCount--;
						shipTotal++;
					}
				}
			}
		}
		return shipTotal;
	}
}
