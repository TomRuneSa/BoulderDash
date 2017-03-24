package inf101.v17.boulderdash.bdobjects;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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
			InputStream resourceAsStream = getClass().getResourceAsStream("ynt.png");

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
			askedToGo = Direction.WEST;
		} else if (key == KeyCode.RIGHT) {
			askedToGo = Direction.EAST;
		} else if (key == KeyCode.UP) {
			askedToGo = Direction.NORTH;
		} else if (key == KeyCode.DOWN) {
			askedToGo = Direction.SOUTH;
		}
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

	public void tryCatch(Position position) {
		try {
			prepareMove(position);
		} catch (IllegalMoveException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void step() {
		Position playerPos = owner.getPosition(this);

		if (askedToGo != null && owner.canGo(playerPos, askedToGo)) {
			Position nextPos = playerPos.moveDirection(askedToGo);
			if (owner.get(nextPos) instanceof BDDiamond) {
				diamondCnt++;
				try {
					prepareMove(nextPos);
				} catch (IllegalMoveException e) {

				}
			} else if (owner.get(nextPos) instanceof BDRock) {
				if (askedToGo == Direction.WEST || askedToGo == Direction.EAST) {
					BDRock rock = (BDRock) owner.get(nextPos);
					try {
						if (rock.push(askedToGo)) {
							prepareMove(nextPos);
						}
					} catch (IllegalMoveException e) {

					}
				}
			} else if (owner.get(nextPos) instanceof BDBug) {
				kill();
			} 
			else {
				try {
					prepareMove(nextPos);
				} catch (IllegalMoveException e) {
				}
			}

		}
		askedToGo = null;
		super.step();
	}

	@Override
	public boolean isKillable() {
		return true;
	}
}
