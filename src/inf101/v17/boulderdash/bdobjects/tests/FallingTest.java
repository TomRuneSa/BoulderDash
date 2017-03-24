package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.bdobjects.AbstractBDFallingObject;
import inf101.v17.boulderdash.bdobjects.BDDiamond;
import inf101.v17.boulderdash.bdobjects.BDRock;
import inf101.v17.boulderdash.bdobjects.IBDObject;
import inf101.v17.boulderdash.maps.BDMap;
import inf101.v17.datastructures.IGrid;
import inf101.v17.datastructures.MyGrid;
import inf101.v17.boulderdash.bdobjects.BDPlayer;

import javafx.scene.input.KeyCode;
public class FallingTest {

	private BDMap map;

	@Before
	public void setup() {
		IGrid<Character> grid = new MyGrid<>(2, 5, ' ');
		grid.set(0, 4, 'r');
		grid.set(0, 0, '*');
		map = new BDMap(grid);
	}

	@Test
	public void fallingTest2() {
		checkFall(new Position(0, 4));
	}
	
	@Test
	public void fallingKills1() {
		// diamond two tiles above kills player
		IGrid<Character> grid = new MyGrid<>(2, 5, ' ');
		grid.set(0, 4, 'r');
		grid.set(0, 2, 'p');
		grid.set(0, 0, '*');
		map = new BDMap(grid);
		
		
		checkFall(new Position(0, 4));
		checkFall(new Position(0, 3));
		checkFall(new Position(0, 2));
		assertFalse(map.getPlayer().isAlive());
	}

	@Test
	public void restingDoesntKill1() {
		// diamond on top of player doesn't kill player
		
		 
		IGrid<Character> grid = new MyGrid<>(2, 5, ' ');
		grid.set(0, 3, 'r');
		grid.set(0, 2, 'p');
		grid.set(0, 0, '*');
		map = new BDMap(grid);
		
		map.step();
		map.step();
		map.step();
		map.step();
		
	
		assertTrue(map.getPlayer().isAlive());
	
	}

	public void pushTest() throws IllegalMoveException{
		IGrid<Character> grid = new MyGrid<>(4, 4, ' ');
		grid.set(2, 2, 'r');
		
		map = new BDMap(grid); 
		BDRock rock = (BDRock) map.get(2,2); 
		
		rock.push(Direction.WEST); // new position would be (1,2) 
		
		assertEquals(rock, map.get(1,2));  
		
		rock.push(Direction.EAST); // new position would be (2,2)
		
		assertEquals(rock, map.get(2,2));  	
 
		
	}
	public void playerRockTest(){ // sjekker om steinen blir flyttet nar spilleren gar inn i den. 
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
		
	
	@Test
	public void fallingTest1() {
		IBDObject obj = map.get(0, 4);
		assertTrue(obj instanceof BDRock);

		// four steps later, we've fallen down one step
		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(obj, map.get(0, 3));

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(obj, map.get(0, 2));

		map.step();
		map.step();
		map.step();
		map.step();
		assertEquals(obj, map.get(0, 1));

		// wall reached, no more falling
		for (int i = 0; i < 10; i++)
			map.step();
		assertEquals(obj, map.get(0, 1));
	}

	protected Position checkFall(Position pos) {
		IBDObject obj = map.get(pos);
		if (obj instanceof AbstractBDFallingObject) {
			Position next = pos.moveDirection(Direction.SOUTH);
			if (map.canGo(next)) {
				IBDObject target = map.get(next);
				if (target.isEmpty() || target.isKillable()) {
				} else {
					next = pos;
				}
			} else {
				next = pos;
			}

			//map.step(); System.out.println(map.getPosition(object));
			map.step();
			map.step();
			map.step();
			map.step();
			assertEquals(obj, map.get(next));
			return next;
		} else
			return pos;
	}
	

}
