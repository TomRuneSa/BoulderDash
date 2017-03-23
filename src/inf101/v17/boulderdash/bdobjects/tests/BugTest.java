package inf101.v17.boulderdash.bdobjects.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.bdobjects.AbstractBDFallingObject;
import inf101.v17.boulderdash.bdobjects.BDBug;
import inf101.v17.boulderdash.bdobjects.BDDiamond;
import inf101.v17.boulderdash.bdobjects.BDPlayer;
import inf101.v17.boulderdash.bdobjects.IBDObject;
import inf101.v17.boulderdash.maps.BDMap;
import inf101.v17.datastructures.IGrid;
import inf101.v17.datastructures.MyGrid;

public class BugTest {

	private BDMap map;

	@Before
	public void setup() {
	}

	@Test
	public void bugMoves() {
		IGrid<Character> grid = new MyGrid<>(4, 4, ' ');
		grid.set(2, 2, 'b');
		map = new BDMap(grid);

		// find the bug
		Position bugPos = new Position(2, 2);
		IBDObject bug = map.get(bugPos);
		assertTrue(bug instanceof BDBug);

		for (int i = 0; i < 100; i++) {
			map.step();
			if (map.get(bugPos) != bug) { // bug has moved
				// reported position should be different
				assertNotEquals(bugPos, map.getPosition(bug));
				// bug moved –  we're done
				return;
			}

		}

		fail("Bug should have moved in 100 steps!");
	}

	@Test
	public void bugStaysWall() {
		IGrid<Character> grid = new MyGrid<>(4, 4, '*');
		grid.set(2, 2, 'b');
		map = new BDMap(grid);

		// find the bug
		Position bugPos = new Position(2, 2);
		IBDObject bug = map.get(bugPos);
		assertTrue(bug instanceof BDBug);

		for (int i = 0; i < 10; i++) {
			map.step();
			if (map.get(bugPos) != bug) {
				fail("Bug moved");
			}
			assertEquals(bugPos, map.getPosition(bug));

		}
	}

	@Test
	public void bugStaysSand() {
		IGrid<Character> grid = new MyGrid<>(4, 4, '#');
		grid.set(2, 2, 'b');
		map = new BDMap(grid);

		// find the bug
		Position bugPos = new Position(2, 2);
		IBDObject bug = map.get(bugPos);
		assertTrue(bug instanceof BDBug);

		for (int i = 0; i < 10; i++) {
			map.step();
			if (map.get(bugPos) != bug) {
				fail("Bug moved");
			}
			assertEquals(bugPos, map.getPosition(bug));

		}
	}

	@Test
	public void bugMove() {
		IGrid<Character> grid = new MyGrid<>(4, 4, ' ');
		grid.set(2, 2, 'b');
		map = new BDMap(grid);

		Position bugPos = new Position(2, 2);
		IBDObject bug = map.get(bugPos);
		Position west = new Position(1, 2);
		Position north = new Position(1, 3);
		Position east = new Position(2, 3);
		Position south = new Position(2, 2);

		for (int i = 0; i <= 10; i++) {
			map.step();
		}
		assertEquals(map.getPosition(bug), west);

		for (int i = 0; i <= 10; i++) {
			map.step();
		}
		assertEquals(map.getPosition(bug), north);

		for (int i = 0; i <= 10; i++) {
			map.step();
		}
		assertEquals(map.getPosition(bug), east);

		for (int i = 0; i <= 10; i++) {
			map.step();
		}
		assertEquals(map.getPosition(bug), south);

	}

	@Test
	public void bugKillsPlayer() {
		IGrid<Character> grid = new MyGrid<>(4, 4, ' ');
		grid.set(2, 2, 'b');
		grid.set(2, 3, 'p');//The position after 3 steps

		map = new BDMap(grid);
		

		for (int i = 0; i < 40; i++) {
			map.step();			
		}
		//The player should be killed after 40 loops. Checking a random amount
		//of steps after 40 loops.
		if(map.getPlayer().isAlive()){
			fail("Player is still alive");
		}
	}
}
