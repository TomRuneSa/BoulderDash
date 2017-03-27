package inf101.v17.boulderdash.bdobjects;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import inf101.v17.boulderdash.bdobjects.BDRock;

import java.io.InputStream;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;

/**
 * An implementation of the player.
 *
 * @author larsjaffke
 *
 */
public class BDPlayer extends AbstractBDMovingObject implements IBDKillable {

	private ImagePattern image;
	/**
	 * Is the player still alive?
	 */
	protected boolean alive = true;

	/**
	 * The direction indicated by keypresses.
	 */
	protected Direction askedToGo;

	/**
	 * Number of diamonds collected so far.
	 */
	protected int diamondCnt = 0;

	public BDPlayer(BDMap owner) {
		super(owner);

		
	}

	@Override
	public Paint getColor() {
		if(image == null){
			InputStream resourceAsStream = getClass().getResourceAsStream("sonic1.gif");

			image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1.0,1.0, true);
		}
		return image;
	}

	/**
	 * @return true if the player is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	public void keyPressed(KeyCode key) {
		// TODO
		if (key == KeyCode.LEFT) {
			InputStream resourceAsStream = getClass().getResourceAsStream("sonicrotate.gif");
			image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1.0,1.0, true);
			askedToGo = Direction.WEST;
		} else if (key == KeyCode.RIGHT) {
			InputStream resourceAsStream = getClass().getResourceAsStream("sonic1.gif");
			image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1.0,1.0, true);
			askedToGo = Direction.EAST;
		} else if (key == KeyCode.UP) {
			askedToGo = Direction.NORTH;
		} else if (key == KeyCode.DOWN) {
			askedToGo = Direction.SOUTH;
		}
		//Returns the direction corresponding to the keys.
	}

	@Override
	public void kill() {
		this.alive = false;
	}

	/**
	 * Returns the number of diamonds collected so far.
	 *
	 * @return
	 */
	public int numberOfDiamonds() {
		return diamondCnt;
	}

	@Override
	public void step() {
		Position playerPos = owner.getPosition(this);
		//Gets the position

		if (askedToGo != null && owner.canGo(playerPos, askedToGo)) {
			Position nextPos = playerPos.moveDirection(askedToGo);
			//If the key input != null value, and the player can move from 
			//the current location in the direction which is asked for.
			if (owner.get(nextPos) instanceof BDDiamond) {
				diamondCnt++;
				try {
					prepareMove(nextPos);
				} catch (IllegalMoveException e) {

				}
				//If it's a diamond in the next position the diamond count will be upped by one, and a move is prepared.
			} else if (owner.get(nextPos) instanceof BDRock) {
				//If the next position is a rock.
				if (askedToGo == Direction.WEST || askedToGo == Direction.EAST) {
					BDRock rock = (BDRock) owner.get(nextPos);
					//If it's a rock, and the direction the player wants to go is either east or west, 
					//a rock object is created.
					try {
						if (rock.push(askedToGo)) {
							prepareMove(nextPos);
							//If the rock can be pushed, prepare a move.
						}
					} catch (IllegalMoveException e) {

					}
				}
			} else if (owner.get(nextPos) instanceof BDBug) {
				kill();
				//If the next position is a bug, kill the player.
			} 
			else {
				try {
					prepareMove(nextPos);
				} catch (IllegalMoveException e) {
				}
				//If the next position isn't a rock, bug or diamond, a move should be prepared.
			}

		}
		askedToGo = null;
		//Sets "askedToGo" to null, so that the player can move in a new direction 
		//the next time he types a direction
		super.step();
	}

	@Override
	public boolean isKillable() {
		return true;
	}
}
