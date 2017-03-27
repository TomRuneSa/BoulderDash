package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.assertEquals;



import org.junit.Test;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.bdobjects.BDPlayer;
import inf101.v17.boulderdash.bdobjects.BDRock;
import inf101.v17.boulderdash.maps.BDMap;
import inf101.v17.datastructures.IGrid;
import inf101.v17.datastructures.MyGrid;
import javafx.scene.input.KeyCode;

public class PlayerTest {
	private BDMap map;

	@Test
	public void pushTest() throws IllegalMoveException{
		IGrid<Character> grid = new MyGrid<>(4, 4, ' ');
		grid.set(2, 2, 'r');
		
		map = new BDMap(grid); 
		BDRock rock = (BDRock) map.get(2,2); 
		
		rock.push(Direction.WEST); // new position should be (1,2) 
		
		assertEquals(rock, map.get(1,2));  
		
		rock.push(Direction.EAST); // new position should be (2,2)
		
		assertEquals(rock, map.get(2,2));  	
 
		
	}
	@Test
	public void playerRockTest(){ // Checks if the rock is being moved when the player walks into a rock. 
		IGrid<Character> grid = new MyGrid<>(5, 5, ' ');
		grid.set(2, 2, 'r');
		grid.set(1, 2, 'p');
		
		map = new BDMap(grid);
		
		BDRock rock = (BDRock) map.get(2,2); 
		BDPlayer player = (BDPlayer) map.get(1,2); 
		player.keyPressed(KeyCode.RIGHT);
		map.step();
		
		
		assertEquals(rock, map.get(3,2));
	}
}
